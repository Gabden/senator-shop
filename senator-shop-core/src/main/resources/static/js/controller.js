var cartApp = angular.module ("cartApp", []);

$( document ).ready(function() {
    $(".alert").hide();
    $.ajax({
        url: '/restCart/cart/ajax',
        success: function(data){
            $('#cart-badge').text(data.cartItems.length)
        }
    });

    if(document.getElementById('phone')){
        $('#phone').mask('+7(000)000-0000');
    }

});
cartApp.controller("cartCtrl", function ($scope, $http){

    $("#quantityInCart").bind("change paste keyup", function() {
        var id = $("#productId").val();
        $scope.refreshQuantity(id);
    });

    $scope.refreshCart = function (cartId) {
        $http({
            method: 'GET',
            url:'/restCart/cart/' + cartId
        }).then(function (response){
            console.log(response);
            $('#cart-badge').text(data.cartItems.length)
        },function (error){
            console.log("Error when refreshCart request")
        });
        // $http.get('/rest/cart/'+$scope.cartId).success(function (data) {
        //     $scope.cart=data;
        // });
    };

    $scope.refreshCartBadge = function () {
        $http({
            method: 'GET',
            url:'/restCart/cart/ajax'
        }).then(function (response){
            $('#cart-badge').text(response.data.cartItems.length)
        },function (error){
            console.log("Error when refreshCart request")
        });
    }

    $scope.initCartId = function (cartId) {
        $scope.cartId = cartId;
        $scope.refreshCart(cartId);
    };

    $scope.refreshQuantity = function(productId){
        $http({
            method: 'PUT',
            url: '/restCart/cart/refreshQuantity/' + productId + '?quantity=' + $('#quantityInCart').val(),
            headers: {
                "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8",
            }
        }).then(function (response){
            location.reload();
            console.log("Количество обновлено")

        },function (error){
            console.log('/restCart/cart/add/' + productId + '?quantity=' + $('#quantityInCart').val());
            console.log(error)
        });
    };

    $scope.addToCart = function (productId) {
        var url;
        var quantity = $('#quantityFromSelect').val();
        console.log(productId);
        if (quantity){
            url = '/restCart/cart/add/' + productId + '?quantity=' + quantity

        } else {
            url = '/restCart/cart/add/' + productId + '?quantity=1'
        }
        console.log(url);
        $http({
            method: 'PUT',
            url: url,
            headers: {
                "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8",
            }
        }).then(function (response){
            $scope.refreshCartBadge();
            $(".alert").fadeTo(2000, 500).slideUp(500, function() {
                $(".alert").slideUp(500);})


        },function (error){
            console.log('/restCart/cart/add/' + productId + '?quantity=' + $('#quantityFromSelect').val());
            console.log(error)
        });
        // $http.put('/rest/cart/add/'+productId).success(function (data) {
        //
        //     // $scope.refreshCart($http.get('/eMusicStore/rest/cart/cartId'));
        //     alert("Product successfully added to the cart!")
        // });
    };

    $scope.removeFromCart = function (productId) {
        $http({
            method: 'DELETE',
            url: '/restCart/cart/delete/' + productId
        }).then(function (response){
            $scope.refreshCart($scope.cartId);
            location.reload();
        },function (error){
            console.log("Error when RemoveFromCart request")
        });
        // $http.put('/rest/cart/remove/'+productId).success(function (data) {
        //     $scope.refreshCart($http.get('/eMusicStore/rest/cart/cartId'));
        // });
    };
    $scope.clearCart = function () {
        $http({
            method: 'DELETE',
            url: '/restCart/cart/ajax/clearCart'
        }).then(function (response){
            location.reload();
        },function (error){
            console.log("Error when clearCart request")
        });
        // $http.delete('/rest/cart/'+$scope.cartId).success($scope.refreshCart($scope.cartId));
    };
});