/**
 * 
 */
var app = angular.module("shopping-cart-app", []);
app.controller("shopping-cart-ctrl", function ($scope, $http) {

    $scope.cart = {
        items: [],
        //thêm vào giỏ
        add(id) {
            var item = this.items.find(item => item.id == id);
            if (item) {
                item.qty++;
                this.saveToLocalStorage();
            } else {
                $http.get(`/rest/product/${id}`).then(resp => {
                    resp.data.qty = 1;
                    this.items.push(resp.data);
                    this.saveToLocalStorage();
                })
            }
        },
        //xóa 1 product
        remove(id) {
            var index = this.items.findIndex(item => item.id == id); //tìm
            this.items.splice(index, 1); //xóa
            this.saveToLocalStorage(); //lưu lại
        },
        //xóa sạch product
        clear() {
            this.items = [];
            this.saveToLocalStorage();
        },
        //
        saveToLocalStorage() {
            var json = JSON.stringify(angular.copy(this.items));
            localStorage.setItem("cart", json);
        },
        //tổng mặc hàng trong cart
        get count() {
            return this.items
                .map(item => item.qty)
                .reduce((total, qty) => total += qty, 0);
        },
        //tổng tiền
        get amount() {
            return this.items
                .map(item => item.qty * item.price)
                .reduce((total, qty) => total += qty, 0);
        },
        //load cart từ localStorage
        loadFormLocalStorage() {
            var json = localStorage.getItem("cart");
            this.items = json ? JSON.parse(json) : [];
        }
    }
    $scope.cart.loadFormLocalStorage();

    $scope.order = {
        createdate: new Date(),
        address: "",
        account: {
            username: document.getElementById("username")
        },
        get orderdetails() {
            return $scope.cart.items.map(item => {
                return {
                    product: { id: item.id },
                    price: item.price,
                    quantity: item.qty
                }
            });
        },

        dac_hang() {
            var order = angular.copy(this);
            $http.post("/rest/orders", order).then(resp => {
                alert("Đặc hàng thành công");
                $scope.cart.clear();
                location.href = "/order/detail/" + resp.data.id;
            }).catch(error => {
                alert("Đặt hàng lỗi")
				console.log(error)
            })
        }
    }
})