//-------Navigation bar collapse ------------
$(document).ready(function () {
    //Get the button:
    var mybutton = document.getElementById("myBtn");

// When the user scrolls down 20px from the top of the document, show the button
    window.onscroll = function () {
        scrollFunction()
    };

    function scrollFunction() {
        if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
            mybutton.style.display = "block";
        } else {
            mybutton.style.display = "none";
        }
    }

    $('#myBtn').on('click', function () {
        document.body.scrollTop = 0; // For Safari
        document.documentElement.scrollTop = 0; // For Chrome, Firefox, IE and Opera
    });


    $("#sidebar").mCustomScrollbar({
        theme: "minimal"
    });
    $('#products-all').on('click', function () {
        $('#chevron').toggleClass('fa-chevron-right').toggleClass('fa-chevron-down');
    });

    $('#dismiss, .overlay').on('click', function () {
        $('#sidebar').removeClass('active');
        $('.overlay').removeClass('active');
    });

    $('#sidebarCollapse').on('click', function () {
        $('#sidebar').addClass('active');
        $('.overlay').addClass('active');
        $('.collapse.in').toggleClass('in');
        $('a[aria-expanded=true]').attr('aria-expanded', 'false');
    });
    $('#search-button').on('click', function () {
        $('#search-input').toggleClass('d-none');

    }); $('#close-search').on('click', function () {
        $('#search-input').toggleClass('d-none');

    });
    $('#overlay').on('click', function () {
        $('#search-input').toggleClass('d-none');

    });

    $('#current-year').text(new Date().getFullYear())

    $('.count').prop('disabled', true);
    $(document).on('click','.plus',function(){
        $('.count').val(parseInt($('.count').val()) + 1 );
    });
    $(document).on('click','.minus',function(){
        $('.count').val(parseInt($('.count').val()) - 1 );
        if ($('.count').val() == 0) {
            $('.count').val(1);
        }
    });

    //modal

    if (sessionStorage.getItem("story") !== 'true') {
        // sessionStorage.setItem('key', 'value'); pair
        sessionStorage.setItem("story", "true");
        // Calling the bootstrap modal
        $('#exampleModalCenter').modal({
            backdrop: 'static'
        })
    }


});
//---------------------------------------

// counter
$(document).ready(function(){
    $(function () {
        $('[data-toggle="tooltip"]').tooltip()
    })
});