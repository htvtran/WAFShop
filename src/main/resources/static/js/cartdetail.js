// ----------------------------GOLBAL VARIABLES----------------
var theCart = null;
var root = location.protocol + '//' + location.host;
// ----------------------------GOLBAL VARIABLES----------------

// ==================DECLARE POJO
function ShoppingSession() {
  this.id = "";
  this.curSession = "";
  this.total = "";
}
function Product() {
  this.id = "";
  this.name = "";
  this.image = "";
  this.discount = ""
  this.description = "";
  this.amount = "";
  this.subcat = new Subcat();
  this.deleted = "";
}
function Subcat() {
  this.id = "";
  this.name = "";
  this.category = new Category();
}

function Category() {
  this.id = "";
  this.categoryName = "";
}

function Size() {
  this.id = "";
  this.name = "";
}

function Color() {
  this.name = "";
}

function Supplier() {
  this.id = "";
  this.name = "";
  this.address = "";
  this.phone_number = "";
  this.email = "";
}

function CartDetail(obj) {
  this.shopSession = obj.shopSession;
  this.product = obj.product;
  this.quantity = obj.quantity;
  this.id = obj.id;
}



function convert(item) {
  // parse to json string
  var jsonStr = JSON.stringify(item);

  // parse json string into JavaScript Object
  var object = JSON.parse(jsonStr);

  cartDetail = new CartDetail(object);
  // console.log(cartDetail);
  return cartDetail;
  // console.log(object.getProduct().getId());
}

function listOfCartDetail(arrObj) {
  let list = [];

  arrObj.forEach(element => list.push(convert(element)));
  console.log(list);
  return list;
}


function updateMiniCart(listcart) {
  let ul = document.getElementById("miniList");
  let counter = 0;
  let total = 0;
  listcart.forEach(
    function (item) {

      let li = ul.firstElementChild;


      let scli = li.cloneNode(true);

      ul.appendChild(scli);



      let lastedItem = ul.lastElementChild;

      console.log(item.product.name);
      lastedItem.setAttribute("data-cart-obj", item);
      lastedItem.querySelector(".mini-item-name").innerHTML = item.product.name;
      lastedItem.querySelector(".mini-item-price").innerHTML = toCurrency(item.product.price);
      lastedItem.querySelector(".mini-item-quantity").innerHTML = 'x' + item.quantity;
      lastedItem.querySelector(".mini-item-image").src = "/product/image/" + item.product.image + "";
      counter += item.quantity;
      total += item.product.price * item.quantity;
      showTotal(total)
    })
  ul.removeChild(ul.getElementsByTagName('li')[0]);
  document.getElementById("mini-cart-counter").innerText = counter;


}

function showTotal(x) {
  // x = x.toLocaleString('it-IT', { style: 'currency', currency: 'VND' });
  document.querySelector(".mini-total-price").innerText = toCurrency(x);
  document.querySelector("#mini-cart-price").innerText = toCurrency(x);
}

function toCurrency(cur) {
  return cur.toLocaleString('it-IT', { style: 'currency', currency: 'VND' });;
}


function showAlert(status, text) {
  Swal.fire({
    toast: true,
    position: 'top-right',
    showConfirmButton: false,
    timer: 1500,
    title: text,
    // text: status ? name : '',

    background: '#fff',
    // footer: '<a href="">Why do I have this issue?</a>',
    icon: status ? 'success' : 'error'
  }
  )
};

function showLoginPromt() {
  Swal.fire({
    title: 'Find What You Need, Sweet!!!',
    text: 'Login in or Create new account to continued',
    imageUrl: 'https://img.wallpapersafari.com/desktop/1680/1050/70/10/VQWxKr.jpg',
    imageWidth: 400,
    imageHeight: 200,
    confirmButtonText:
      '<i class="fa fa-user-plus" aria-hidden="true"></i> Create new account',
    imageAlt: 'Custom image',
    footer: '<div>Already have an account? Login <a style="color:maroon;" href="/login"> here</a> </div> '
  })

}






// var json2 = [{
//   shopSession: {
//     id: 7,
//     curSession: "6D5CBB4B55EFDD268106C6435948BA3A",
//     total: 6
//   },
//   product: {
//     id: 3,
//     name: "Áo khoác Cardigan nâu",
//     price: 239000,
//     image: "man_cardigan3.jpg",
//     description: "Áo khoác cadigan nam chất cotton tổ ong cao cấp, dễ mặc dễ phối đồ, hợp mọi thời đại",
//     amount: 100,
//     deleted: false,
//     subcat: {
//       id: 10,
//       name: "Cardigan",
//       category: {
//         id: 1,
//         categoryName: "Men's Clothing"
//       }
//     },
//     size: {
//       id: 3,
//       name: "L"
//     },
//     color: {
//       name: "Brown"
//     },
//     supplier: {
//       id: 1,
//       name: "Xưởng Sỉ Quần Áo ANN",
//       address: "68 Đường C12, Phường 13, Quận Tân Bình, TP. HCM",
//       phone_number: "0913268406",
//       email: "ann2707@gmail.com.vn"
//     }
//   },
//   quantity: 1
// },
// {
//   shopSession: {
//     id: 7,
//     curSession: "6D5CBB4B55EFDD268106C6435948BA3A",
//     total: 6
//   },
//   product: {
//     id: 3,
//     name: "Áo khoác Cardigan nâu",
//     price: 239000,
//     image: "man_cardigan3.jpg",
//     description: "Áo khoác cadigan nam chất cotton tổ ong cao cấp, dễ mặc dễ phối đồ, hợp mọi thời đại",
//     amount: 100,
//     deleted: false,
//     subcat: {
//       id: 10,
//       name: "Cardigan",
//       category: {
//         id: 1,
//         categoryName: "Men's Clothing"
//       }
//     },
//     size: {
//       id: 3,
//       name: "L"
//     },
//     color: {
//       name: "Brown"
//     },
//     supplier: {
//       id: 1,
//       name: "Xưởng Sỉ Quần Áo ANN",
//       address: "68 Đường C12, Phường 13, Quận Tân Bình, TP. HCM",
//       phone_number: "0913268406",
//       email: "ann2707@gmail.com.vn"
//     }
//   },
//   quantity: 1
// }
// ]
