$('document').ready(function () {

    // LOGIC OF SEARCH CATEGORY
    var filterAll = $('#filter-all');
    if(filterAll.is(':checked')){
        $('#filter-alcohol-wrapper').toggle('d-none');
        $('#filter-dishes-wrapper').toggle('d-none');
        $('#filter-drinks-wrapper').toggle('d-none');
        $('#filter-gifts-wrapper').toggle('d-none');
        $('#filter-oil-wrapper').toggle('d-none');
    }
    filterAll.click(function() {
        if ($(this).is(':checked')) {
            $('#filter-alcohol-wrapper').toggle('d-none');
            $('#filter-dishes-wrapper').toggle('d-none');
            $('#filter-drinks-wrapper').toggle('d-none');
            $('#filter-gifts-wrapper').toggle('d-none');
            $('#filter-oil-wrapper').toggle('d-none');

            $('#filter-alcohol').prop('checked', false);
            $('#filter-dishes').prop('checked', false);
            $('#filter-drinks').prop('checked', false);
            $('#filter-gifts').prop('checked', false);
            $('#filter-oil').prop('checked', false);
        } else {
            $('#filter-alcohol-wrapper').toggle('d-none');
            $('#filter-dishes-wrapper').toggle('d-none');
            $('#filter-drinks-wrapper').toggle('d-none');
            $('#filter-gifts-wrapper').toggle('d-none');
            $('#filter-oil-wrapper').toggle('d-none');
        }
    });
        

});