$(document).ready(function () {
    //check authenticated
    if (s) {
        console.log('anon do nothing');
    } else {
        console.log('cs: ');
        console.log(cartSize);
        fetchBill();
    }
    // document.getElementById("cart-table").scrollIntoView({ block: "center", behavior: "smooth" });
});

var checkoutCart = null;
function fetchBill() {
    fetch(`/api/cdws/?ss=${sid}`,
        { method: 'GET' }
    ).then(res => {
        if (res.status == 200) {
            /* 		showAlert(true, 'Đã thêm vào giỏ hàng'); */
            return res.json()

        } else {
            alert('error');
        }
    }).then(
        data => {
            let listcart = listOfCartDetail(data);
            theCart = listcart;
            if (listcart.length > 0)
                updateOrderDetail(listcart);
        }
    )
}
function updateOrderDetail(listcart) {
    let tbl = document.getElementById("order-table");
    let tbody = document.querySelector(".order-body");
    console.log('body: ');
    console.log(tbody);
    let counter = 0;
    let total = 0;
    let index = 0;

    while (tbody.children.length > 1) { tbody.removeChild(tbody.lastChild) }
    listcart.forEach(
        function (item) {
            let tr = tbody.firstElementChild;
            let sctr = tr.cloneNode(true);
            tbody.appendChild(sctr);
            let lastedItem = tbody.lastElementChild;
            let isDiscount = item.product.discount != null;
            let rawprice = parseFloat(item.product.price);
            let price = isDiscount ? (rawprice - (rawprice * (parseInt(item.product.discount) * 0.01))) : rawprice;

            //         console.log(item.product.name);
            lastedItem.setAttribute("data-cart-index", index); index += 1;
            lastedItem.getElementsByClassName("item-name")[0].innerText = item.product.name;
            lastedItem.getElementsByClassName("item-quantity")[0].innerText = 'x ' + item.quantity;
            // total += price * item.quantity;
            let pTotal = price * item.quantity;
            total += pTotal;
            lastedItem.getElementsByClassName("ptotal")[0].innerText = toCurrency(pTotal);
        })
    tbody.removeChild(tbody.getElementsByTagName('tr')[0]);
    // document.getElementById("mini-cart-counter").innerText = counter;
    addTotalSection(tbody);
    tbody.querySelector("#total").innerText = toCurrency(total);
    tbody.querySelector("#subtotal").innerText = toCurrency(0);
    checkoutCart = listcart;
}

function addTotalSection(tbody) {
    //    '<tr> <td> <h3 class="order-h3">Tax</h3> </td> <td> <h3 class="order-h3">$0.00</h3> </td></tr>',
    let arr = [
        '<tr> <td><h3 class="order-h3">Subtotal</h3></td><td><h3 id="subtotal" class="order-h3">$0.00</h3></td></tr>',

        '<tr><td> <h3 class="order-h3" >Total</h3></td><td><h3 id="total" class="order-h3">$220.00</h3></td></tr>'
    ];
    arr.forEach(i => tbody.insertAdjacentHTML('beforeend', i));


}

function placeOrder() {
    fetch(`/account/checkout/check?ss=${sid}`,
        {
            method: 'POST',
            headers: {
                'Accept': 'application/json, text/plain, */*',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(checkoutCart)
        }
    ).then(res => {
        if (res.status == 200) {
            // input.value = parseInt(ammount) + parseInt(1);
            res.text().then(data => {
                showAlert(true, "Thanh toán thành công");
                setTimeout(() => {
                    window.location.href = root + "/";
                }, 2000);
            })
        } else {
            res.text().then(data => showAlert(false, data));

        }
    }).then(error =>
        console.log(error)
    );
}
