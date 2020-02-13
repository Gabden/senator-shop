$(document).ready(function () {
    $("select").bind("change paste keyup", function () {
        var orderId = this.id;
        var status = this.value;
        var url = '/admin/customerOrder/update/' + orderId + '?status=' + status;
        $.ajax({
            url: url,
            method: 'PUT',
            contentType: 'application/json',
            success: function (result) {
                location.reload();
            },
            error: function (request, msg, error) {
                console.log(msg)
            }
        });

    });

    $("input").bind("change paste keyup", function () {
        var id = this.id;
        var quantity = this.value;
        var url = '/admin/customerOrder/item/update/' + id + '?q=' + quantity;
        $.ajax({
            url: url,
            method: 'PUT',
            contentType: 'application/json',
            success: function (result) {
                location.reload();
            },
            error: function (request, msg, error) {
                console.log(msg)
            }
        });

    });

    $("[data-item]").bind("click", function () {
        var id = this.dataset.item;
        var url = '/admin/customerOrder/delete/item/' + id;
        $.ajax({
            url: url,
            method: 'DELETE',
            contentType: 'application/json',
            success: function (result) {
                location.reload();
            },
            error: function (request, msg, error) {
                console.log(msg)
            }
        });
    });

    $("[data-order]").bind("click", function () {
        var id = this.dataset.order;
        var url = '/admin/customerOrder/delete/' + id;
        console.log(url);
        $.ajax({
            url: url,
            method: 'DELETE',
            contentType: 'application/json',
            success: function (result) {
                location.reload();
            },
            error: function (request, msg, error) {
                console.log(msg)
            }
        });
    });


});