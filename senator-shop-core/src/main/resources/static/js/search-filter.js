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
    // LOGIC OF SEARCH CATEGORY

    //LOGIC OF SEARCH ALCOHOL
    var alcoholCheckbox = $('#filter-alcohol');

    alcoholCheckbox.click(function() {
        if ($(this).is(':checked')) {
            $('#btn-filter-type-alcohol').toggle('d-none');

            $('#filter-wine-checkbox').prop('checked', false);
            $('#filter-strong-checkbox').prop('checked', false);

        } else {
            $('#btn-filter-type-alcohol').toggle('d-none');

        }
    });

    //LOGIC OF SEARCH ALCOHOL ---------

    //LOGIC OF SEARCH WINE
    var alcoholWineCheckbox = $('#filter-wine-checkbox');

    alcoholWineCheckbox.click(function() {
        if ($(this).is(':checked')) {
            $('#search-region-wrapper').toggle('d-none');
            $('#search-color-wrapper').toggle('d-none');
            $('#search-sugar-wrapper').toggle('d-none');
            $('#search-sort-wrapper').toggle('d-none');


        } else {
            $('#search-region-wrapper').toggle('d-none');
            $('#search-color-wrapper').toggle('d-none');
            $('#search-sugar-wrapper').toggle('d-none');
            $('#search-sort-wrapper').toggle('d-none');

        }
    });
    //LOGIC OF SEARCH WINE

    //LOGIC OF SEARCH STRONG
    var alcoholStrongCheckbox = $('#filter-strong-checkbox');

    alcoholStrongCheckbox.click(function() {
        if ($(this).is(':checked')) {
            $('#strong-type-wrapper').toggle('d-none');
            $('#strong-mature-wrapper').toggle('d-none');



        } else {
            $('#strong-type-wrapper').toggle('d-none');
            $('#strong-mature-wrapper').toggle('d-none');


        }
    });
    //LOGIC OF SEARCH STRONG






});