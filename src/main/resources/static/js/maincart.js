
$(document).ready(function () {
  //check authenticated
  if (s) {
    console.log('anon do nothing');
  } else {
    // api/getSessionID?sid
    // fetch(`/api/getSessionID?sid=${sid}`,
    //   { method: 'GET' }
    // ).then(res => {
    //   if (res.status == 200) {
    //     res.text().then(data => {
    //       localStorage.setItem('shoppingSession', data);
    //     })
    //   }
    // })

    // fetch(`/api/cdws/?ss=${sid}`,
    //   { method: 'GET' }
    // ).then(res => {
    //   if (res.status == 200) {
    //     /* 		showAlert(true, 'Đã thêm vào giỏ hàng'); */
    //     return res.json()

    //   } else {
    //     alert('error');
    //   }
    // }).then(
    //   data => {
    //     let listcart = listOfCartDetail(data);
    //     updateMainCart(listcart);
    //   }

    // )
    fetchMainCart(false);
  }
  document.getElementById("cart-table").scrollIntoView({ block: "center", behavior: "smooth" });
});

function fetchMainCart(isUpdate) {
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
        updateMainCart(listcart);
      console.log("isUpdate: ");
      console.log(isUpdate);
      return theCart;
    }

  ).then(tc => {
    console.log("thec cart: ");
    console.log(tc);
  })
}


function updateMainCart(listcart) {
  let tbl = document.getElementById("cart-table");
  let tbody = document.querySelector(".cart-body");

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
      console.log(item.product.name);

      let aLink = lastedItem.getElementsByTagName("a")[0];
      aLink.setAttribute("data-row-id", item.product.id);
      aLink.href = aLink.href + "" + item.product.id;
      lastedItem.getElementsByClassName("up")[0].setAttribute("data-row-id", item.product.id);
      lastedItem.getElementsByClassName("down")[0].setAttribute("data-row-id", item.product.id);
      lastedItem.querySelector(".cart-item-name").innerText = item.product.name;
      lastedItem.querySelector(".quantity").firstElementChild.value = item.quantity;
      lastedItem.getElementsByTagName("a")[0].setAttribute("data-row-id", item.product.id);
      lastedItem.querySelector(".cart-item-price").innerHTML = toCurrency(item.product.price);

      // lastedItem.querySelector(".mini-item-quantity").innerHTML = 'x' + item.quantity;
      lastedItem.querySelector(".cart-item-image").src = "/product/image/" + item.product.image + "";
      lastedItem.setAttribute("data-cart-index", index);
      index += 1;
      counter += item.quantity;
      total += item.product.price * item.quantity;
      showTotal(total)
    })
  tbody.removeChild(tbody.getElementsByTagName('tr')[0]);
  // document.getElementById("mini-cart-counter").innerText = counter;


}

function showTotal(x) {
  // x = x.toLocaleString('it-IT', { style: 'currency', currency: 'VND' });
  document.getElementById("mini-cart-price").innerText = toCurrency(x);
}

function toCurrency(cur) {
  return cur.toLocaleString('it-IT', { style: 'currency', currency: 'VND' });;
}

function increase(e) {
  let id = e.getAttribute("data-row-id");
  sendIncrease(e, id);
}

function reduce(e) {
  let id = e.getAttribute("data-row-id");
  sendReduce(e, id);
  fetchMainCart(true);
  console.log(theCart.length);
}


function sendIncrease(e, pid) {
  btn = e;
  let input = btn.parentElement.firstElementChild;
  let ammount = input.value;

  fetch(`/api/addtocart?pid=${pid}&ss=${sid}`,
    { method: 'GET' }
  ).then(res => {
    if (res.status == 200) {
      input.value = parseInt(ammount) + parseInt(1);
      return res.text();

    } else {
      showAlert(false, "Lỗi tăng số lượng")
    }
  }).then(data => {

  }).then(error =>
    console.log(error)
  );

}

function sendReduce(e, pid) {
  btn = e;
  let input = btn.parentElement.firstElementChild;
  let ammount = input.value;

  fetch(`${root}/api/reducetocart?pid=${pid}&ss=${sid}`,
    { method: 'GET' }
  ).then(res => {
    if (res.status == 200) {
      input.value = parseInt(ammount) - parseInt(1);
      return res.text();

    } else {
      showAlert(false, "Lỗi tăng số lượng")
    }
  }).then(data => {

  }).then(error =>
    console.log(error)
  );

}

function getTheCartItemBy(index) {
  let cd = theCart[index];
  return cd;
}

var urow;
function updateCartItem(e) {
  urow = e.parentElement.parentElement.parentElement;

  let cartIndex = urow.getAttribute("data-cart-index")
  let cartItem = getTheCartItemBy(cartIndex);
  let quantityInput = urow.getElementsByClassName('quantity')[0].querySelector('input[name="qty"]')
  let qty = quantityInput.value;
  // console.log(quantityInput.value);
  // console.log(cartItem);
  sendUpdate(cartItem, qty);
}

function sendUpdate(cartItem, qty) {
  console.log('udpate :' + JSON.stringify(cartItem, null, 4));
  console.log('qty  = ' + qty);
  cartItem.quantity = qty;
  fetch(`/api/updateItem`,
    {
      method: 'POST',
      headers: {
        'Accept': 'application/json, text/plain, */*',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(cartItem)
    }
  ).then(res => {
    if (res.status == 200) {
      // input.value = parseInt(ammount) + parseInt(1);
      res.text().then(data => {
        console.log(data)
        fetchMainCart(true);
      })
    } else {
      res.text().then(data => showAlert(false, data));

    }
  }).then(error =>
    console.log(error)
  );
}

function removeCartItem(e) {
  urow = e.parentElement.parentElement.parentElement;

  let cartIndex = urow.getAttribute("data-cart-index")
  let cartItem = getTheCartItemBy(cartIndex);
  fetch(`/api/deleteItem`,
    {
      method: 'POST',
      headers: {
        'Accept': 'application/json, text/plain, */*',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(cartItem)
    }
  ).then(res => {
    if (res.status == 200) {
      // input.value = parseInt(ammount) + parseInt(1);
      res.text().then(data => {
        console.log(data)
        fetchMainCart(true);
        console.log(theCart.length);
      })
    } else {
      res.text().then(data => showAlert(false, data));

    }
  }).then(error =>
    console.log(error)
  );

}





