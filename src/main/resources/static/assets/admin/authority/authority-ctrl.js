app.controller('authority-ctrl', function ($scope, $http) {
	$scope.roles = [];
	$scope.admins = [];
	$scope.authorities = [];

	$scope.initialize = function () {
		$http.get('/rest/roles').then(response => {
			$scope.roles = response.data;
		})

		$http.get('/rest/accounts?admins=true').then(response => {
			$scope.admins = response.data;
			console.log($scope.admins);
		})
		$http.get('/rest/authorities?admins=true').then(response => {
			$scope.authorities = response.data;
		}).catch(err => {
			console.log(err);
			$location.path("/unauthorized");
		})
	}

	$scope.authority_of = function (acc, role) {
		console.log(acc);
		if ($scope.authorities) {
			return $scope.authorities.find(ur => ur.account.username == acc.username && ur.role.id == role.id);
		}
	}

	$scope.authority_change = function (acc, role) {
		var authority = $scope.authority_of(acc, role);
		if (authority) {
			$scope.revoke_authority(authority);
		} else {
			authority = { account: acc, role: role };
			$scope.grant_authority(authority);
		}
	}
	// Thêm mới quyền
	$scope.grant_authority = function (authority) {
		$http.post(`/rest/authorities`, authority).then(response => {
			$scope.authorities.push(response.data);
			alert('Cấp quyền thành công');

		}).catch(error => {
			alert('Cấp quyền thất bại')
			console.log('ERROR', error);
		})
	}

	$scope.revoke_authority = function (authority) {
		$http.delete(`/rest/authorities/${authority.id}`).then(response => {
			var index = $scope.authorities.findIndex(a => a.id == authority.id);
			$scope.authorities.slice(index, 1);
			alert('Thu hồi quyền thành công');

		}).catch(error => {
			alert('Thu hồi quyền thất bại');
			console.log('ERROR', error);
		})
	}
	$scope.initialize();
})