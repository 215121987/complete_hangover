(function ($) {
    $(function () {
        $.each($('select'), function(){
            var placeHolder = $(this).attr("placeHolder");
            if(null!=placeHolder && undefined != placeHolder && placeHolder=='true'){
                $(this).zelect({ placeholder:'Plz select...' });
            }else{
                $(this).zelect();
            }
        });


        $('body').on('focus',"#dob", function(){
            $(this).datepicker();
            /* $( this).datepicker({
             dateFormat: 'dd/mm/yy',
             changeMonth: true,
             changeYear: true
             } );*/
            $(this).datepicker("option","minDate", "0");
            $(this).datepicker("option","minDate", "-60Y");
            $(this).datepicker("option","maxDate", "-18Y");
            $(this).datepicker("option","dateFormat", "dd/mm/yy");
        });

        //Dropdown cart in header
        $('.cart-holder > h3').click(function () {
            if ($(this).hasClass('cart-opened')) {
                $(this).removeClass('cart-opened').next().slideUp(300);
            } else {
                $(this).addClass('cart-opened').next().slideDown(300);
                $("div.widget_shopping_cart_content").animate({ scrollTop: $("div.widget_shopping_cart_content ul").outerHeight()}, 800);
            }
        });
        //Popup rating content
        $('.star-rating').each(function () {
            rate_cont = $(this).attr('title');
            $(this).append('<b class="rate_content">' + rate_cont + '</b>');
        });

        //Disable cart selection
        (function ($) {
            $.fn.disableSelection = function () {
                return this
                    .attr('unselectable', 'on')
                    .css('user-select', 'none')
                    .on('selectstart', false);
            };
            $('.cart-holder h3').disableSelection();
        })(jQuery);

        //Fix contact form not valid messages errors
        jQuery(window).load(function () {
            jQuery('.wpcf7-not-valid-tip').live('mouseover', function () {
                jQuery(this).fadeOut();
            });

            jQuery('.wpcf7-form input[type="reset"]').live('click', function () {
                jQuery('.wpcf7-not-valid-tip, .wpcf7-response-output').fadeOut();
            });


        });

        // compare trigger
        $(document).on('click', '.cherry-compare', function (event) {
            event.preventDefault();
            button = $(this);
            $('body').trigger('yith_woocompare_open_popup', { response:compare_data.table_url, button:button })
        });

        $('li.product').find('.add_to_cart_button, .cherry-quick-view.btn, .yith-wcwl-add-to-wishlist a, .compare, li > .btn').wrapInner('<span class="tooltip-inner" />');

        $('.products .product').each(function () {
            _this = $(this);
            _this.find('.price > ins').after(_this.find('.price > del'));
            _this.find('h3').after(_this.find('.price'));

            var thisButtonsBlock = $('<div class="buttonsBlock"></div>');
            _this.append(thisButtonsBlock);
            var buttons = _this.find('.add_to_cart_button, div.yith-wcwl-add-to-wishlist, .compare, li> .btn');
            thisButtonsBlock.append(buttons);
        });


        $(document).on('click', '#login', function (event) {
            event.preventDefault();
            var button = $(this);
            $('body').trigger('yith_woocompare_open_popup', { response:'/hangover/login.html?ajax=true', button:button })
        });

        $(document).on('click', '#register', function (event) {
            event.preventDefault();
            var button = $(this);
            $('body').trigger('yith_woocompare_open_popup', { response:'/hangover/register.html?ajax=true', button:button })
        });


        $(document).on('submit', '#login_form', function (event) {
            event.preventDefault();
            var c = $(this).serialize();
            $(this).ajaxSubmit(function (response) {
                if (response.code == "200") {
                    parent.$.fn.colorbox.close();
                    window.top.location.reload();
                } else {
                    var username = document.getElementsByName("j_username")[0];
                    var password = document.getElementsByName("j_password")[0];
                    username.setCustomValidity(response.message);
                    password.setCustomValidity(response.message);
                }
            });
        });


        $(document).on('blur', '#register_form input[name=email]', function (event) {
            var element = this;
            var emailText = $(this).val();
            if(undefined != emailText && null != emailText && emailText.trim() != ''){
                $.get('/hangover/validate/email.html', {email:emailText}, function (response) {
                    if (response.code != "200") {
                        element.setCustomValidity(response.message);
                    }else{
                        element.setCustomValidity('');
                    }
                });
            }

        });

        $(document).on('blur', '#register_form input[name=mobile]', function (event) {
            var element = this;
            var mobileText = $(this).val();
            if(undefined != mobileText && null != mobileText && mobileText.trim() != ''){
                $.get('/hangover/validate/mobile.html', {mobile:mobileText}, function (response) {
                    if (response.code != "200") {
                        element.setCustomValidity(response.message);
                    }else{
                        element.setCustomValidity('');
                    }
                });
            }
        });

        $(document).on('submit', '#register_form', function (event) {
            event.preventDefault();
            $(this).ajaxSubmit(function (response) {
                if (response.code == "200") {
                    //parent.$.fn.colorbox.close();
                    //window.top.location.reload();
                    $(".signup_action").slideUp();
                    $(".signup_action").html("");
                    $('.server_message').html(response.message);
                    $('.server_message').slideDown();
                } else {
                    var fieldError = response.fieldError;
                    for(var i=0;i<fieldError.length;i++){
                        var input = document.getElementsByName(fieldError.key)[0];
                        input.setCustomValidity(fieldError.value);
                    }
                }
            });
        });


        $(document).on('submit', '#address_form', function (event) {
            event.preventDefault();
            var c = $(this).serialize();
            $(this).ajaxSubmit(function (response) {
                if (response.code == "200") {
                    window.top.location.reload();
                } else {
                    showToastMessage(response.message);
                }
            });
        });

        $(document).on('click', '.address_block input[type=radio]', function (event) {
            var addressId = $(this).val();
            var address = $(this).parents(".address_block").find('p:first').html();
            $.get(HANGOVER_USER_DELIVERY_ADDRESS.ajax_url, {addressId:addressId}, function (response) {
                if (response.code == "200") {
                    $('#section-2').find('.checkout-info').find("span").remove();
                    $('#section-2').find('.checkout-info').append(jQuery(document.createElement("span")).append(address));
                    $('#section-2').attr("status", "complete");
                    $('#section-2').find(".checkout-info-content").slideUp();
                    $('#section-3').find(".checkout-info-content").slideDown();
                } else {
                    $(this).attr('checked', false);
                }
            });
        });

        $(".checkout-info").each(function(e){
            toggleCaret(this);
        });

        $(document).on('click', '.checkout-info', function (event) {
            var orderNumber = $(this).parent().attr("order-number");
            $(this).find("span").slideToggle();
            var isOpenable = true;
            for (var i = orderNumber - 1; i >= 1; i--) {
                var status = $('#section-' + i).attr("status");
                isOpenable = undefined == status || null == status || "" == status || status != 'complete' ? false : isOpenable;
            }
            if (isOpenable) {
                var status = $('#section-' + orderNumber).attr("status");
                if (orderNumber == '1' && (undefined == status || status != 'complete')) {
                    $(this).parent().find(".checkout-info-content").slideDown();
                } else if (orderNumber == '3') {
                    $('#section-' + orderNumber).attr("status", "incomplete");
                    $('#section-4').find('.checkout-info').removeClass("clickAble");
                    $('#section-4').find('.checkout-info').addClass("noDrop");
                    $('#section-4').find(".checkout-info-content").slideUp();
                    $(this).parent().find(".checkout-info-content").slideDown();
                } else {
                    $(this).parent().find('.checkout-info-content').slideToggle();
                }
            } else {
                $(this).parent().find(".checkout-info-content").slideUp();
            }
            toggleCaret(this);
        });

        function toggleCaret(element){
            if($(element).find("i").length<=0){
                $(element).prepend('<i class="fa fa-1x">&nbsp;</i>');
            }
            var isOpen = $(element).hasClass("clickAble") && $(element).find("i").hasClass("fa-caret-right");
            if(isOpen){
                $(element).find("i").removeClass("fa-caret-right");
                $(element).find("i").addClass("fa-caret-down");
            }else{
                $(element).find("i").removeClass("fa-caret-down");
                $(element).find("i").addClass("fa-caret-right");
            }
        }

        $(document).on('click', '#proceedToPay', function (event) {
            $(".checkout-info-content").slideUp();
            $('.checkout-info').find("span").slideDown();
            $('#section-3').attr("status", "complete");
            $('#section-4').find('.checkout-info').removeClass("noDrop");
            $('#section-4').find('.checkout-info').addClass("clickAble");
            $('#section-4').find(".checkout-info-content").slideDown();
        });

        $(document).on('change', 'select.item-detail', function (event) {
            var option = $(this).find('option:selected');
            var quantity = option.attr("quantity");
            var price = option.attr("price");
            var parentBlock = $(this).parents(".item-detail-block");
            parentBlock.find(".price del span").html(price);
            parentBlock.find(".price ins span").html(price);
            parentBlock.find(".stock span").html(quantity);
        });




        var getPinCodeFromGeoLocation = function () {
            var zipCode = "";
            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(function (position) {
                    var geocoder = new google.maps.Geocoder();
                    var latLng = new google.maps.LatLng(
                        position.coords.latitude, position.coords.longitude);
                    geocoder.geocode({
                        'latLng':latLng
                    }, function (results, status) {
                        for (var i = 0; i < results[0].address_components.length; i++) {
                            var address = results[0].address_components[i];
                            if (address.types[0] == "postal_code") {
                                zipCode = address.long_name;
                                /*$('#zipcode').html(address.long_name);
                                 $('#location').html(results[0].formatted_address);*/
                            }
                        }
                    });
                });
            }
            return zipCode;
        };

        var customer_address = jQuery.cookie('customer_address');
        if(undefined != customer_address && null != customer_address && "" != customer_address){
            var customerAddressAsJSON = $.parseJSON(customer_address);
            $("#delivery_location").find("span").html(customerAddressAsJSON.zipCode+", "+customerAddressAsJSON.city);
            $("#delivery_location").show();
        }

        $("#delivery_location").on('click', function(e){
            var zipCode = jQuery.cookie('customer_location');
            $('#hangover_zip_code_popup form').find("input[name=zipcode]").val(zipCode);
            open_popup('#hangover_zip_code_popup', true);
        });

        var askForLocation = $('meta[name=askForLocation]').attr("content");
        if(null!=askForLocation && undefined !=askForLocation && askForLocation == 'true'){
            askForPinCode();
        }
        function askForPinCode() {
            var zipCode = jQuery.cookie('customer_location');
            if (undefined == zipCode || null == zipCode || "" == zipCode) {
                /*zipCode = getPinCodeFromGeoLocation();
                 $('#hangover_zip_code_popup form input[name=zipcode]').val(zipCode);*/
                open_popup('#hangover_zip_code_popup', false);
            }
        }


        $(document).on('submit', '#hangover_zip_code_popup form', function (event) {
            event.preventDefault();
            var zipCode = $(this).find("input[name=zipcode]").val();
            var url = HANGOVER_URL['supplier_store'];
            var c ={};
            c['zipCode'] = zipCode;
            c['ajax'] =true;
            c['view']='json';
            var currentForm = this;
            $.get(url,c, function (response) {
                var addresses = $.parseJSON(response);
                if(addresses.length>0){
                    var addr = addresses[0];
                    var address = {};
                    address['zipCode'] = addr.zipCode;
                    address['city'] = addr.city;
                    address['country'] = addr.country;
                    address['state'] = addr.state;
                    $.cookie('customer_location', zipCode, {expires:1, path:"/"});
                    $.cookie('customer_address', JSON.stringify(address), {expires:1, path:"/"});
                    $.magnificPopup.instance.close();
                    window.top.location.reload();
                }else{
                    $(currentForm).find(".invalid_zip_code_message").show();
                }
            });
        });

        var createCookie = function (name, value, days) {
            var expires = "";
            if (days) {
                var date = new Date();
                date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
                expires = date.toGMTString();
            }
            $.cookie(name, value, {expires:expires, secure:true});
        }
    });

    $('#buying_slider_min').change(function () {
        var min = parseInt($(this).val());
        var max = parseInt($('#buying_slider_max').val());
        if (min > max) {
            $(this).val(max);
            $(this).slider('refresh');
        }
    });
    $('#buying_slider_max').change(function () {
        var min = parseInt($('#buying_slider_min').val());
        var max = parseInt($(this).val());

        if (min > max) {
            $(this).val(min);
            $(this).slider('refresh');
        }
    });


    $('#hangover-tabs ul.tabs li a').on('click',function (e) {
        e.preventDefault();
        var urlName = $(this).attr('data_url');
        var c = {tab:$(this).attr("tab"), ajax:true};
        var url = HANGOVER_URL[urlName];
        var currentSelection = this;
        $.get(url,c, function (response) {
            $('#hangover-tab-content').html(response);
            $('#hangover-tabs ul.tabs li').removeClass("active");
            $(currentSelection).parents("li").addClass("active");
        });
    });

    $('.parallax-item a.delete-parallax-item').on('click',function (e) {
        e.preventDefault();
        var urlName = $(this).attr('data_url');
        var id = $(this).attr('data_id');
        var c = {ajax:true};
        var url = HANGOVER_URL[urlName];
        url = url.replace('_id', id);
        var currentSelection = this;
        $.get(url,c, function (response) {
            if(response.code == 200){
                $(currentSelection).parents(".parallax-item").parent().remove();
            }
            showToastMessage(response.message)
        });
    });



    $('.add-parallax-item').on('click',function (e) {
        e.preventDefault();
        var urlName = $(this).attr('data_url');
        var url = HANGOVER_URL[urlName];
        var c = {};
        c['view']=$(this).attr('view');
        c['id']=$(this).attr('data_id');
        c['ajax']=true;
        prepareReqData(c, this);
        /*if(null!= reqData && undefined != reqData){
         var keyVals = reqData.split("-");
         for(var i=0; i<keyVals.length;i++){
         c[keyVals[i].split(":")[0]] =keyVals[i].split(":")[1];
         }
         }*/
        $.get(url,c, function (response) {
            $('#hangover_popup .cherry-quick-view-popup-content').html(response);
            open_popup('#hangover_popup', true);
        });
    });

    $(document).on('submit', 'form.genericMethod', function (event) {
        event.preventDefault();
        $.post($(this).attr("action"), $(this).serialize(), function (response) {
            if (response.code == "200") {
                closePopUp();
                window.top.location.reload();
            } else {
                console.log("error while saving code:- "+ response.code + "  message :- "+ response.message);
            }
        });
    });


    $(document).on('click', 'form.genericMethod input[name=cancel]', function (event){
        closePopUp();
    });


    var timer = 0;
    $('.search-form input[name=filterQuery]').on('keyup',function (e) {
        if (timer) {
            clearTimeout(timer);
        }
        var query = $(this).val();
        var searchOnUI  = $(this).attr("searchOnUI");
        if(null== searchOnUI || undefined==searchOnUI){
            searchOnUI = true;
        }
        if(searchOnUI== 'true'){
            timer = setTimeout(doSearchOnUI(query), 600);
        }else{
            var searchFor = $(this).attr("searchFor");
            var c = {};
            if(null!= searchFor && ""!= searchFor){
                var fieldName = searchFor.split("-");
                for(var i =0; i<fieldName.length;i++){
                    c[fieldName[i]]=query;
                }
            }
            c['view']=$(this).attr("view");
            c['ajax']=true;
            var url = HANGOVER_URL[$(this).attr("data_url")];
            timer = setTimeout(doSearch(url, c), 600);
        }
    });


    $('.edit-cart-item').on('click', function(){
        if($(this).parents('.cart-main').find(".cart-edit").find('form').length>0){
            $(this).parents('.cart-main').find(".cart-edit").slideToggle();
        }else{
            var urlName = $(this).attr("data_url");
            var url = HANGOVER_URL[urlName];
            var c = {};
            prepareReqData(c, this);
            c['ajax'] = 'true';
            c['action']= 'edit-cart-item';
            var currentElement = this;
            $.get(url,c, function (response) {
                $(currentElement).parents('.cart-main').find(".cart-edit").html(response);
                $(currentElement).parents('.cart-main').find(".cart-edit form select").zelect();
                $(currentElement).parents('.cart-main').find(".cart-edit").slideToggle();
            });
        }
    });

    $(document).on('change','.cart-main form select', function(){
        var quantity = $(':selected').attr('quantity');
        $(this).parents('form').find("input[name=quantity]").attr("max", quantity);
    });

    $(document).on('click','.cart-main form input[name=cancel]', function(){
        $(this).parents(".cart-edit").slideUp();
    });

    /*
     $( "#slider-range" ).slider({
     range: true,
     min: 0,
     max: 500,
     values: [ 100, 300 ],
     slide: function( event, ui ) {
     //$( "#amount" ).html( "$" + ui.values[ 0 ] + " - $" + ui.values[ 1 ] );
     $( ".priceRangeInfo input[name=minPrice]" ).val(ui.values[ 0 ]);
     $( ".priceRangeInfo input[name=maxPrice]" ).val(ui.values[ 1 ]);
     //$( "#amount2" ).val(ui.values[ 1 ]);
     }
     });*/
    /*$( "#amount" ).html( "$" + $( "#slider-range" ).slider( "values", 0 ) +
     " - $" + $( "#slider-range" ).slider( "values", 1 ) );*/

    var notH = 1,
        $pop = $('#search_result').hover(function(){notH^=1;});

    $(document).on('mousedown', function( e ){
        if(notH||e.which==27) $pop.stop().slideUp(800);
    });


    $('.search-form input[name=query]').on('keyup',function (e) {
        if (timer) {
            clearTimeout(timer);
        }
        var query = $(this).val();
        if(null != query && query != ""){
            if(query.length>=3){
                var c = {};
                c['query']= query;
                c['view']=$(this).attr("view");
                c['ajax']=true;
                var url = HANGOVER_URL[$(this).attr("data_url")];
                timer = setTimeout(headerSearch(url, c), 600);
            }
        } else{
            $("#search_result").hide();
        }
    });



})(jQuery);


headerSearch = function(url, c){

    $.get(url,c, function (response) {
        $("#search_result ul").html("");
        $("#search_result").find(".no-more-item").remove();
        $("#search_result").slideDown(800);
        var responseAsHTML = $('<div>').html(response);
        if($(responseAsHTML).find("li").length>0){
            $(responseAsHTML).find("li").each(function(){
                $(this).find('select').zelect();
                $("#search_result ul").append(this);
            });
        }else{
            $("#search_result ul").after($(responseAsHTML).find("div")[0].outerHTML);
        }
    });
};

var doSearch = function(url, c){
    $.get(url,c, function (response) {
        $('#entity_content').slideUp();
        $('#entity_content').html("");
        $('#entity_content').html(response);
        $('#entity_content').slideDown(300);
    });
};

var doSearchOnUI = function(query){
    $('#entity_content .item-block').each(function(){
        var keep = false;
        $(this).find('.searchable').each(function(){
            if($(this).html().toLowerCase().indexOf(query.toLowerCase()) != -1){
                keep=true;
            }
        });
        if(keep){
            $(this).fadeIn();
        }else{
            $(this).fadeOut();
        }
    });
};


var prepareReqData = function(c, element){
    var reqData =  $(element).attr('req_data');
    if(null!= reqData && undefined != reqData){
        var keyVals = reqData.split("-");
        for(var i=0; i<keyVals.length;i++){
            c[keyVals[i].split(":")[0]] =keyVals[i].split(":")[1];
        }
    }
};


var open_popup = function (elementId, needCloseBtn) {
    if ($.isFunction(jQuery.fn.magnificPopup)) {
        $.magnificPopup.open({
            items:{
                src:elementId
            },
            type:'inline',
            showCloseBtn:needCloseBtn,
            /*closeOnContentClick:needCloseBtn,*/
            closeOnBgClick:needCloseBtn,
            enableEscapeKey:needCloseBtn,
            mainClass:'my-mfp-slide-bottom'
        }, 0);
    }

};

var closePopUp = function(){
    $.magnificPopup.instance.close();
}

var showToastMessage = function (message){
    var b = jQuery("#yith-wcwl-popup-message");
    jQuery("#yith-wcwl-message").html(message);
    b.css("margin-left", "-" + jQuery(b).width() + "px").fadeIn();
    window.setTimeout(function () {
            b.fadeOut()
        },
        2E3);
};



function response(obj){
    console.log(obj);
}
var getAddressInfoByZipCode = function(zip){
    var addr = {};
    if(zip.length >= 5 && typeof google != 'undefined'){
        var geocoder = new google.maps.Geocoder();
        geocoder.geocode({ 'address': zip }, function(results, status){
            if (status == google.maps.GeocoderStatus.OK){
                if (results.length >= 1) {
                    for (var ii = 0; ii < results[0].address_components.length; ii++){
                        var street_number = route = street = city = state = zipcode = country = formatted_address = '';
                        var types = results[0].address_components[ii].types.join(",");
                        if (types == "street_number"){
                            addr.street_number = results[0].address_components[ii].long_name;
                        }
                        if (types == "route" || types == "point_of_interest,establishment"){
                            addr.route = results[0].address_components[ii].long_name;
                        }
                        if (types == "sublocality,political" || types == "locality,political" || types == "neighborhood,political" || types == "administrative_area_level_3,political"){
                            addr.city = (city == '' || types == "locality,political") ? results[0].address_components[ii].long_name : city;
                        }
                        if (types == "administrative_area_level_1,political"){
                            addr.state = results[0].address_components[ii].short_name;
                        }
                        if (types == "postal_code" || types == "postal_code_prefix,postal_code"){
                            addr.zipCode = results[0].address_components[ii].long_name;
                        }
                        if (types == "country,political"){
                            addr.country = results[0].address_components[ii].long_name;
                        }
                    }
                    addr.success = true;
                    for (name in addr){
                        console.log('### google maps api ### ' + name + ': ' + addr[name] );
                    }
                    response(addr);
                } else {
                    response({success:false});
                    addr.success = false;
                }
            } else {
                response({success:false});
                addr.success = false;
            }
        });
    } else {
        response({success:false});
        addr.success = false;
    }
    return addr;
} ;





function ajaxindicatorstart(text){
    if(jQuery('body').find('#resultLoading').attr('id') != 'resultLoading'){
        jQuery('body').append('<div id="resultLoading" style="display:none"><div><img src="ajax-loader.gif"><div>'+text+'</div></div><div class="bg"></div></div>');
    }

    jQuery('#resultLoading').css({
        'width':'100%',
        'height':'100%',
        'position':'fixed',
        'z-index':'10000000',
        'top':'0',
        'left':'0',
        'right':'0',
        'bottom':'0',
        'margin':'auto'
    });

    jQuery('#resultLoading .bg').css({
        'background':'#000000',
        'opacity':'0.7',
        'width':'100%',
        'height':'100%',
        'position':'absolute',
        'top':'0'
    });

    jQuery('#resultLoading>div:first').css({
        'width': '250px',
        'height':'75px',
        'text-align': 'center',
        'position': 'fixed',
        'top':'0',
        'left':'0',
        'right':'0',
        'bottom':'0',
        'margin':'auto',
        'font-size':'16px',
        'z-index':'10',
        'color':'#ffffff'

    });

    jQuery('#resultLoading .bg').height('100%');
    jQuery('#resultLoading').fadeIn(300);
    jQuery('body').css('cursor', 'wait');
}

function ajaxindicatorstop(){
    jQuery('#resultLoading .bg').height('100%');
    jQuery('#resultLoading').fadeOut(300);
    jQuery('body').css('cursor', 'default');
}

jQuery(document).ajaxStart(function () {
    //show ajax indicator
    ajaxindicatorstart('loading data.. please wait..');
}).ajaxStop(function () {
//hide ajax indicator
        ajaxindicatorstop();
    });