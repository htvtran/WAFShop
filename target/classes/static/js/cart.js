
$(document).ready(function () {
  // var sid = /*[[${#session.id}]]*/ ""; 
  // alert(s);

  if (s) {
    console.log('anon do nothing');
  } else {
    // api/getSessionID?sid
    fetch(`/api/getSessionID?sid=${sid}`,
      { method: 'GET' }
    ).then(res => {
      if (res.status == 200) {
        res.text().then(data => {
          localStorage.setItem('shoppingSession', data);
        })
      }
    })

    fetchMiniCart();
  }
});

function fetchMiniCart() {
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
        updateMiniCart(listcart);
    }
  )
}

function addCart(e) {
  console.log(e.getAttribute('data-prod-id'));

  let pid = e.getAttribute('data-prod-id');

  if (s) { showLoginPromt(); }
  else {
    fetch(`/api/addtocart?pid=${pid}&ss=${sid}`,
      { method: 'GET' }
    ).then(res => {
      if (res.status == 200) {
        return res.text();
        // return res.json()
      } else {
        alert("lõi")
      }
    }).then(data => {
      console.log(data)
      showAlert(true, data);
      fetchMiniCart();
      // fetchMiniCart();
    }).then(error =>
      console.log(error)
    );
  }
}





// function updateMiniCart(listcart) {
//   let ul = document.getElementById("miniList");
//   let counter = 0;
//   let total = 0;
//   listcart.forEach(
//     function (item) {
//       let li = ul.firstElementChild;

//       let scli = li.cloneNode(true);
//       ul.appendChild(scli);
//       let lastedItem = ul.lastElementChild;
//       console.log(item.product.name);

//       lastedItem.querySelector(".mini-item-name").innerHTML = item.product.name;
//       lastedItem.querySelector(".mini-item-price").innerHTML = toCurrency(item.product.price);
//       lastedItem.querySelector(".mini-item-quantity").innerHTML = 'x' + item.quantity;
//       lastedItem.querySelector(".mini-item-image").src = "/product/image/" + item.product.image + "";
//       counter += item.quantity;
//       total += item.product.price * item.quantity;
//       showTotal(total)
//     })
//   ul.removeChild(ul.getElementsByTagName('li')[0]);
//   document.getElementById("mini-cart-counter").innerText = counter;


// }

// function showTotal(x) {
//   // x = x.toLocaleString('it-IT', { style: 'currency', currency: 'VND' });
//   document.getElementById("mini-cart-price").innerText = toCurrency(x);
// }

// function toCurrency(cur) {
//   return cur.toLocaleString('it-IT', { style: 'currency', currency: 'VND' });;
// }

// function addCart(e) {
//   console.log(e.getAttribute('data-prod-id'));


  // fetch(`/api/cdws/?ss=${sid}`,
  //   { method: 'GET' }
  // ).then(res => {
  //   if (res.status == 200) {
  //     /* 		showAlert(true, 'Đã thêm vào giỏ hàng'); */
  //     return res.json()

  //   } else {
  //     alert('error');
  //   }
  // })
// }

