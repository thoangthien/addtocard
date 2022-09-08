var app = angular.module('shopping-cart-app', []);
app.controller('shopping-cart-ctrl', function ($scope, $http) {


	$scope.cart = {
		items: [],
		add(id) {
			var item = this.items.find(item => item.id === id);
			if (item) {
				item.qty+=1;
				alert('đã thêm vào giỏ')
				this.saveToLocalStorage();
			} else
				$http.get(`/rest/products/${id}`).then(response => {
					response.data.qty = 1;
					alert('đã thêm vào giỏ')
					this.items.push(response.data);
					this.saveToLocalStorage();
				})
		},

		remove(id) {
			var index = this.items.findIndex(item => item.id == id);
			this.items.splice(index, 1);
			this.saveToLocalStorage();
		},
		clear() {
			this.items = [];
			this.saveToLocalStorage();

		},
		amt_of(item) { },
		get count() {
			return this.items.map(item => item.qty).reduce((total, qty) => total += qty, 0);

		},
		get amount() {
			return this.items.map(item => item.qty * item.price).reduce((total, qty) => total += qty, 0);
		},
		loadLocalStorage() {
			var json = localStorage.getItem("cart");
			this.items = json ? JSON.parse(json) : [];
		},
		saveToLocalStorage() {
			var json = JSON.stringify(angular.copy(this.items));
			localStorage.setItem("cart", json);
		}
	}
	$scope.cart.loadLocalStorage();

	$scope.order = {
		createdate: new Date(),
		address: "",
		account: {
			username: $("#username").text()
		},
		get orderdetails() {
			return $scope.cart.items.map(item => {
				return {
					product: { id: item.id },
					price: item.price,
					quantity: item.qty
				}
			})
		},
		dac_hang() {
			var order = angular.copy(this);

			$http.post("/rest/orders", order).then(resp => {
				alert("Đặt hàng thành công")
				$scope.cart.clear();
				location.href = "/order/detail/" + resp.data.id;
			}).catch(error => {
				alert("Đặt hàng lỗi")
				console.log(error)
			})
		}
	}
})