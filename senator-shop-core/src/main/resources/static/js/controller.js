var cartApp = angular.module ("cartApp", []);

cartApp.controller("cartCtrl", function ($scope, $http){

    $scope.refreshCart = function (cartId) {
        $http({
            method: 'GET',
            url: '/restCart/cart/0E66CA08793EE0B7AF9A2B4566BE5011'
        }).then(function (response){
            $scope.cart=response;
            console.log(response);
        },function (error){
            console.log("Error when refreshCart request")
        });
        // $http.get('/rest/cart/'+$scope.cartId).success(function (data) {
        //     $scope.cart=data;
        // });
    };

    $scope.clearCart = function () {
        $http({
            method: 'DELETE',
            url: '\'/rest/cart/\'+$scope.cartId'
        }).then(function (response){
            $scope.refreshCart($scope.cartId)
        },function (error){
            console.log("Error when clearCart request")
        });
        // $http.delete('/rest/cart/'+$scope.cartId).success($scope.refreshCart($scope.cartId));
    };

    $scope.initCartId = function (cartId) {
        $scope.cartId = cartId;
        $scope.refreshCart(cartId);
    };

    $scope.addToCart = function (productId) {
        $http({
            method: 'PUT',
            url: '/rest/cart/add/' + productId
        }).then(function (response){
            $scope.refreshCart($http.get('/restCart/cart/cartId'));
            alert("Product successfully added to the cart!")
        },function (error){
            console.log("Error when AddToCart request")
        });
        // $http.put('/rest/cart/add/'+productId).success(function (data) {
        //
        //     // $scope.refreshCart($http.get('/eMusicStore/rest/cart/cartId'));
        //     alert("Product successfully added to the cart!")
        // });
    };

    $scope.removeFromCart = function (productId) {
        $http({
            method: 'PUT',
            url: '/rest/cart/remove/'+productId
        }).then(function (response){
            $scope.refreshCart($http.get('/restCart/cart/cartId'));
            alert("Product successfully added to the cart!")
        },function (error){
            console.log("Error when RemoveFromCart request")
        });
        // $http.put('/rest/cart/remove/'+productId).success(function (data) {
        //     $scope.refreshCart($http.get('/eMusicStore/rest/cart/cartId'));
        // });
    };
});