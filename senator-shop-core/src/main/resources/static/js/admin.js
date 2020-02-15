$(document).ready(function () {
        $.ajax({
            url: '/admin/orders/quantity',
            success: function (data) {
                $('#new-order-badge').text(data)
            },
            error: function (request, msg, error) {
                console.log(msg)
            }
        });
    }
);