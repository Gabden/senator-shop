//-------Navigation bar collapse ------------
$(document).ready(function () {
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
});
//---------------------------------------