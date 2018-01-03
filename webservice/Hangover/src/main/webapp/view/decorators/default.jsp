<!DOCTYPE html>
<%@ include file="/view/common/taglib.jsp" %>
<head>
<%@ include file="/view/common/meta.jsp" %>
<link rel="stylesheet" type="text/css" media="all" href='<c:url value="/style/lib/bootstrap-custom.css" />'/>
<link rel="stylesheet" type="text/css" media="all" href='<c:url value="/style/lib/bootstrap.min-3.3.5.css" />'/>
<link rel="stylesheet" type="text/css" media="all" href='<c:url value="/style/responsive.css" />'/>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<%--<link rel="stylesheet" type="text/css" media="all" href='<c:url value="/style/lib/camera.css" />'/>--%>
<link rel="stylesheet" type="text/css" media="all" href='<c:url value="/style/style.css" />'/>
<link rel="stylesheet" type="text/css" media="all" href='<c:url value="/style/style-4-2-7.css" />'/>
<link rel="stylesheet" type="text/css" media="all" href='<c:url value="/style/cart.css" />'/>
<link rel='stylesheet' id='flexslider-css' href='<c:url value="/style/lib/flexslider.css" />' type='text/css'
      media='all'/>
<%--<link rel='stylesheet' id='owl-carousel-css' href='<c:url value="/style/lib/owl.carousel.css" />' type='text/css'
media='all'/>--%>
<%--<link rel='stylesheet' id='owl-theme-css' href='<c:url value="/style/lib/owl.theme.css" />' type='text/css'
media='all'/>--%>
<link rel='stylesheet' id='font-awesome-css'
      href='//netdna.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.css?ver=4.6.3' type='text/css'
      media='all'/>
<%--<link rel='stylesheet' id='font-awesome-css' href='<c:url value="/style/lib/font-awesome.css" />' type='text/css'
media='all'/>--%>
<link rel='stylesheet' id='cherry-plugin-css' href='<c:url value="/style/lib/cherry-plugin.css" />' type='text/css'
      media='all'/>
<link rel='stylesheet' id='cherry-parallax-css' href='<c:url value="/style/lib/parallax.css" />' type='text/css'
      media='all'/>
<link rel='stylesheet' id='contact-form-7-css' href='<c:url value="/style/styles.css" />' type='text/css'
      media='all'/>

<link rel='stylesheet' id='contact-form-7-css' href='<c:url value="/style/hangover.css" />' type='text/css'
      media='all'/>

<link rel='stylesheet' id='woocommerce-layout-css' href='<c:url value="/style/hangover-layout.css" />'
      type='text/css' media='all'/>
<link rel='stylesheet' id='woocommerce-general-css' href='<c:url value="/style/woocommerce.css" />' type='text/css'
      media='all'/>
<link rel='stylesheet' id='jquery-colorbox-css' href='<c:url value="/style/lib/colorbox.css" />' type='text/css'
      media='all'/>
<%--<link rel='stylesheet' id='mailchimp-for-wp-checkbox-css' href='<c:url value="/style/lib/checkbox.min.css" />'
type='text/css' media='all'/>--%>
<link rel='stylesheet' id='theme53953-css' href='<c:url value="/style/main-style.css" />' type='text/css'
      media='all'/>
<link rel='stylesheet' id='magnific-popup-css' href='<c:url value="/style/lib/magnific-popup.css" />' type='text/css'
      media='all'/>
<link rel='stylesheet' id='mailchimp-for-wp-form-css' href='<c:url value="/style/lib/form.min.css" />' type='text/css'
      media='all'/>
<!--[if lt IE 9]>
<link rel='stylesheet' id='theme_ie-css' href='<c:url value="/style/ie.css"/>' type='text/css' media='all'/>
<![endif]-->
<%--<link rel='stylesheet' id='cherry_prettyPhoto_css-css' href='<c:url value="/style/lib/prettyPhoto.css" />'
type='text/css' media='all'/>--%>
<script type="text/javascript">
    var yith_wcwl_plugin_ajax_web_url = 'http://localhost:8080/hangover/comm/shortList/add/1.html';
    /*var login_redirect_url = 'http://livedemo00.template-help.com/woocommerce_53953/wp-login.php?redirect_to=%2Fwoocommerce_53953%2Fshop%2F%3Forderby%3Dpopularity';*/
</script>
<script type='text/javascript' src='<c:url value="/js/lib/jquery-2.1.4.js" />'></script>
<%--<script type='text/javascript' src='<c:url value="/js/lib/jquery.easing.1.3.js" />'></script>--%>
<%--<script type='text/javascript' src='<c:url value="/js/lib/jquery.elastislide.js" />'></script>--%>
<script type='text/javascript' src='<c:url value="/js/lib/jquery-ui.min.js" />'></script>
<%--<script type='text/javascript'
src='//maps.googleapis.com/maps/api/js?v=3.exp&#038;sensor=false&#038;ver=4.2.7'></script>--%>
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&libraries=places,visualization&sensor=false"></script>
<script type='text/javascript' src='<c:url value="/js/lib/jquery-migrate-1.2.1.min.js" />'></script>
<%--<script type='text/javascript' src='<c:url value="/js/lib/swfobject.js" />'></script>--%>
<%--<script type='text/javascript' src='<c:url value="/js/lib/modernizr.js" />'></script>--%>
<script type='text/javascript' src='<c:url value="/js/custom.js" />'></script>
<script type='text/javascript' src='<c:url value="/js/lib/bootstrap.min.js" />'></script>
<script type='text/javascript' src='<c:url value="/js/shop.js" />'></script>
<script type='text/javascript' src='<c:url value="/js/lib/zelect.js" />'></script>

<style type="text/css">
    img.wp-smiley,
    img.emoji {
        display: inline !important;
        border: none !important;
        box-shadow: none !important;
        height: 1em !important;
        width: 1em !important;
        margin: 0 .07em !important;
        vertical-align: -0.1em !important;
        background: none !important;
        padding: 0 !important;
    }
</style>
<style>
    .wishlist_table .add_to_cart, .yith-wcwl-add-button > a.button.alt {
        border-radius: 16px;
        -moz-border-radius: 16px;
        -webkit-border-radius: 16px;
    }
</style>
<style type='text/css'>
    body {
        background-color: #ffffff
    }

    .header {
        background-color: #0c0c0c
    }

</style>
<style type='text/css'>
    h1 {
        font: normal 48px/48px Roboto;
        color: #0c0c0c;
    }

    h2 {
        font: normal 48px/48px Roboto;
        color: #0c0c0c;
    }

    h3, h1 {
        /*font: bold 14px/25px Roboto;*/
        color: #009587;
    }

    h4 {
        font: bold 14px/25px Roboto;
        color: #009587;
    }

    h5 {
        font: bold 14px/25px Roboto;
        color: #009587;
    }

    h6 {
        font: bold 14px/25px Roboto;
        color: #009587;
    }

    body {
        font-weight: normal;
    }

    .logo_h__txt, .logo_link {
        font: normal 72px/72px Roboto;
        color: #ffffff;
    }

    .sf-menu > li > a {
        font: normal 20px/20px Roboto;
        color: #ffffff;
    }

    .nav.footer-nav a {
        font: bold 14px/20px Roboto;
        color: #009587;
    }
</style>
<title><fmt:message key="app.tag.line"/></title>
<decorator:head/>
<!--[if gte IE 9]><!-->
<script src="<c:url value="/js/lib/jquery.mobile.customized.min.js" />" type="text/javascript"></script>
<script type="text/javascript">
    jQuery(function () {
        jQuery('.sf-menu').mobileMenu({defaultText:"Navigate to..."});
    });
</script>
<!--<![endif]-->
<script type="text/javascript">
    // Init navigation menu
    jQuery(function () {
        // main navigation init
        jQuery('ul.sf-menu').superfish({
            delay:1000, // the delay in milliseconds that the mouse can remain outside a sub-menu without it closing
            animation:{
                opacity:"show",
                height:"show"
            }, // used to animate the sub-menu open
            speed:"normal", // animation speed
            autoArrows:false, // generation of arrow mark-up (for submenu)
            disableHI:true // to disable hoverIntent detection
        });

        //Zoom fix
        //IPad/IPhone
        var viewportmeta = document.querySelector && document.querySelector('meta[name="viewport"]'),
                ua = navigator.userAgent,
                gestureStart = function () {
                    viewportmeta.content = "width=device-width, minimum-scale=0.25, maximum-scale=1.6, initial-scale=1.0";
                },
                scaleFix = function () {
                    if (viewportmeta && /iPhone|iPad/.test(ua) && !/Opera Mini/.test(ua)) {
                        viewportmeta.content = "width=device-width, minimum-scale=1.0, maximum-scale=1.0";
                        document.addEventListener("gesturestart", gestureStart, false);
                    }
                };
        scaleFix();
    });
</script>
<script type="text/javascript">
    jQuery(document).ready(function () {
        if (!device.mobile() && !device.tablet()) {
            jQuery('header .logo_box').tmStickUp({
                correctionSelector:jQuery('#wpadminbar'), listenSelector:jQuery('.listenSelector'), active:true, pseudo:true                });
            /*jQuery('#sidebar').tmStickUp({
             correctionSelector:jQuery('#wpadminbar'), listenSelector:jQuery('.listenSelector'), active:true, pseudo:true});*/
        }
    });
</script>


</head>
<body id="doc" class="has_shop" <decorator:getProperty property="body.id" writeEntireProperty="true"/>
        <decorator:getProperty
                property="body.class"
                writeEntireProperty="true"/>>
<div id="motopress-main" class="main-holder">
    <div id="header">
        <%@ include file="../common/header.jsp" %>
    </div>
    <div id="main_body">
        <%--<div class="motopress-wrapper content-holder clearfix">
            <div class="container">
                <div class="row">
                    <div class="span12">
                        <decorator:body/>
                    </div>
                </div>
            </div>
        </div>--%>
        <decorator:body/>
    </div>
    <div id="footer">
        <%@ include file="../common/footer.jsp" %>
    </div>
</div>
<div id="dialog" style="display: none">
    <div id="hangover_zip_code_popup" class="cherry-quick-view-popup mfp-hide" style="width: 600px;">
        <span href="#" class="mfp-close">&times;</span>
        <div class="cherry-quick-view-popup-content">
            <div class="zipcode_block text-center">
                <div class="zipCode_message " >
                    Share pincode to get faster delivery<BR/>
                    & best deals for your location.
                </div>
                <div class="zip_code_form" >
                    <form action='<c:url value="/save/zipCode.html" />'>
                        <p class="form-row form-row-wide">
                            <input class="input-text" type="tel" maxlength="6" name="zipcode" id="zipcode" required="true" />
                            <input type="submit" class="button" name="save" value="Save" style="line-height: 22px;margin-top: -10px;"/>
                        </p>
                        <p class="invalid_zip_code_message">
                            Sorry! We don't have service at this location.
                        </p>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div id="hangover_popup" class="cherry-quick-view-popup mfp-hide">
        <span href="#" class="mfp-close">&times;</span>
        <div class="cherry-quick-view-popup-content">

        </div>
    </div>
</div>
<div id="back-top-wrapper" class="visible-desktop">
    <p id="back-top">
        <a href="#top"><span></span></a>
    </p>
</div>
<script type='text/javascript'>
    /* <![CDATA[ */
    var cherry_wc_data = {"ajax_url":"\/hangover\/comm\/shop/1.html", "nonce":"a725f827c1", "loading":"Loading..."};
    var yith_wcwl_plugin_ajax_web_url = '<c:url value="/comm/shortList/add.html" />';
    /* ]]> */
</script>
<script type='text/javascript'>
    /* <![CDATA[ */
    var wc_add_to_cart_params = {"ajax_url":"\/hangover\/comm\/cart.html","ajax_loader_url":"\/\/livedemo00.template-help.com\/woocommerce_53953\/wp-content\/plugins\/woocommerce\/assets\/images\/ajax-loader@2x.gif","i18n_view_cart":"View Cart","cart_url":"http:\/\/localhost:8080\/hangpver\/comm\/cart.html\/","is_cart":"","cart_redirect_after_add":"no"};
    /* ]]> */
</script>
<script type='text/javascript'>
    /* <![CDATA[ */
    var wc_cart_fragments_params = {"ajax_url":"\/hangover\/comm\/cart.html","fragment_name":"wc_fragments"};
    var wc_single_product_params = {"i18n_required_rating_text":"Please select a rating","review_rating_required":"yes"};
    var HANGOVER_USER_DELIVERY_ADDRESS = {"ajax_url":'<c:url value="/comm/delivery/address.html" />'};
    var HANGOVER_URL = {"profile_url":'<c:url value="/account/profile.html" />', "order_url":'<c:url value="/account/order.html" />',
        "supplier":'<c:url value="/SupplierEntity.html" />', "delete_supplier":'<c:url value="/delete/SupplierEntity/_id.html" />',
        "add_supplier": '<c:url value="/SupplierDTO/form.html" />',"add_store":'<c:url value="/SupplierStoreDTO/form.html" />',
        "delete_store":'<c:url value="/delete/SupplierStoreEntity/_id.html" />',
        "supplier_store":'<c:url value="/SupplierStoreEntity.html" />', "add_staff": '<c:url value="/SupplierStaffDTO/form.html" />',
        "cart_url":'<c:url value="/comm/cart.html" />', "search_item" :'<c:url value="/comm/search.html"/>'};
    /* ]]> */
</script>
</body>
<%--<script type='text/javascript' src='<c:url value="/js/lib/jquery.flexslider-min.js"/>'></script>--%>
<script type='text/javascript' src='<c:url value="/js/lib/cherry-plugin.js" />'></script>
<%--<script type='text/javascript' src='<c:url value="/js/lib/jquery.mousewheel.min.js" />'></script>--%>
<%--<script type='text/javascript' src='<c:url value="/js/lib/jquery.simplr.smoothscroll.min.js" />'></script>--%>
<script type='text/javascript' src='<c:url value="/js/lib/device.min.js" />'></script>
<script type='text/javascript' src='<c:url value="/js/lib/cherry.parallax.js" />'></script>

<script type='text/javascript' src='<c:url value="/js/script.js" />'></script>
<script type='text/javascript' src='<c:url value="/js/lib/jquery.form.min.js" />'></script>
<script type='text/javascript' src='<c:url value="/js/scripts.js" />'></script>
<script type='text/javascript' src='<c:url value="/js/add-to-cart.min.js" />'></script>
<script type='text/javascript' src='<c:url value="/js/lib/jquery.blockUI.min.js" />'></script>
<%--<script type='text/javascript' src='<c:url value="/js/hangover.min.js" />'></script>--%>
<script type='text/javascript' src='<c:url value="/js/lib/jquery.cookie.min.js" />'></script>
<script type='text/javascript' src='<c:url value="/js/cart-fragments.min.js" />'></script>
<script type='text/javascript' src='<c:url value="/js/woocompare.js" />'></script>
<script type='text/javascript' src='<c:url value="/js/lib/jquery.colorbox-min.js" />'></script>
<script type='text/javascript' src='<c:url value="/js/jquery.yith-wcwl.js" />'></script>
<script type='text/javascript' src='<c:url value="/js/lib/superfish.js" />'></script>
<script type='text/javascript' src='<c:url value="/js/lib/jquery.mobilemenu.js" />'></script>
<script type='text/javascript' src='<c:url value="/js/lib/jquery.magnific-popup.min.js" />'></script>
<%--
<script type='text/javascript' src='<c:url value="/js/lib/jplayer.playlist.min.js" />'></script>
<script type='text/javascript' src='<c:url value="/js/lib/jquery.jplayer.min.js" />'></script>
--%>
<script type='text/javascript' src='<c:url value="/js/lib/tmstickup.js?ver=1.0.0" />'></script>
<%--<script type='text/javascript' src='<c:url value="/js/lib/device.min.js?ver=1.0.0" />'></script>--%>
<%--<script type='text/javascript' src='<c:url value="/js/lib/jquery.zaccordion.min.js" />'></script>--%>
<%--<script type='text/javascript' src='<c:url value="/js/lib/camera.min.js" />'></script>--%>
<script type='text/javascript' src='<c:url value="/js/custom-script.js" />'></script>
<%--<script type='text/javascript' src='<c:url value="/js/lib/jquery.prettyPhoto.min.js" />'></script>--%>
<script type='text/javascript' src='<c:url value="/js/lib/parallaxSlider.js" />'></script>
<script type='text/javascript' src='<c:url value="/js/lib/jquery.elevatezoom.min-1.2.1.js" />'></script>
<script type='text/javascript' src='<c:url value="/js/single-product.min-2.1.12.js" />'></script>
<script type='text/javascript' src='<c:url value="/js/lib/jquery.cycle2.min-1.2.1.js" />'></script>
<script type='text/javascript' src='<c:url value="/js/lib/jquery.cycle2.carousel.min-1.2.1.js" />'></script>
<%--<script type='text/javascript' src='<c:url value="/js/lib/jquery.mobile-1.0.min.js" />'></script>--%>

<script>
    (function ($) {
        $(window).load(function () {
            if ($('.widget_shopping_cart_content').is(':empty')) {
                $('.widget_shopping_cart_content').text('No products in the cart.');
            }
        });
    })(jQuery);
</script>

<script type="text/javascript">
    jQuery(document).ready(function ($) {
        if (!device.mobile() && !device.tablet()) {
            liteModeSwitcher = false;
        } else {
            liteModeSwitcher = true;
        }
        if ($.browser.msie && parseInt($.browser.version) < 9) {
            liteModeSwitcher = true;
        }

        jQuery('#parallax-slider-56be2bc3bc7bb').parallaxSlider({
            parallaxEffect:"parallax_effect_normal", parallaxInvert:false, animateLayout:"simple-fade-eff", duration:1500, autoSwitcher:true, autoSwitcherDelay:10000, scrolling_description:true, slider_navs:false, slider_pagination:"buttons_pagination", liteMode:liteModeSwitcher
        });
        /*var zipCode = "";
         if(undefined == zipCode || null== zipCode || ""== zipCode){
         $('body').trigger( 'hangover_open_popup', { response: '', elementId: 'asdafd' } );
         }*/
        $('body').trigger( 'hangover_open_popup', { response: '', elementId: 'asdafd' } );
    });
    if (!jQuery('#yith-wcwl-popup-message').length) {
        jQuery('body').prepend(
                '<div id="yith-wcwl-popup-message" style="display: none;">' +
                        '<div id="yith-wcwl-message"></div>' +
                        '</div>'
        );
    }
</script>
</html>


<%--



 <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&libraries=places,visualization&sensor=false"></script>
navigator.geolocation.getCurrentPosition(function (position) {
            var geocoder = new google.maps.Geocoder();
            var latLng   = new google.maps.LatLng(
                position.coords.latitude, position.coords.longitude);
            geocoder.geocode({
                'latLng': latLng
            }, function (results, status) {

                    alert("  city" + JSON.stringify(results));
                for (var i = 0; i < results[0].address_components.length; i++) {
                    var address = results[0].address_components[i];

                    if (address.types[0] == "postal_code") {

                        $('#zipcode').html(address.long_name);
                        $('#location').html(results[0].formatted_address);

                    }
                }
            });
        });


 output



 [
   {
      "address_components":[
         {
            "long_name":"1719",
            "short_name":"1719",
            "types":[
               "street_number"
            ]
         },
         {
            "long_name":"4th B Cross Road",
            "short_name":"4th B Cross Rd",
            "types":[
               "route"
            ]
         },
         {
            "long_name":"HAL 3rd Stage",
            "short_name":"HAL 3rd Stage",
            "types":[
               "neighborhood",
               "political"
            ]
         },
         {
            "long_name":"Hal",
            "short_name":"Hal",
            "types":[
               "sublocality_level_3",
               "sublocality",
               "political"
            ]
         },
         {
            "long_name":"New Tippasandra",
            "short_name":"New Tippasandra",
            "types":[
               "sublocality_level_1",
               "sublocality",
               "political"
            ]
         },
         {
            "long_name":"Bengaluru",
            "short_name":"Bengaluru",
            "types":[
               "locality",
               "political"
            ]
         },
         {
            "long_name":"Bangalore Urban",
            "short_name":"Bangalore Urban",
            "types":[
               "administrative_area_level_2",
               "political"
            ]
         },
         {
            "long_name":"Karnataka",
            "short_name":"KA",
            "types":[
               "administrative_area_level_1",
               "political"
            ]
         },
         {
            "long_name":"India",
            "short_name":"IN",
            "types":[
               "country",
               "political"
            ]
         },
         {
            "long_name":"560075",
            "short_name":"560075",
            "types":[
               "postal_code"
            ]
         }
      ],
      "formatted_address":"1719, 4th B Cross Rd, HAL 3rd Stage, Hal, New Tippasandra, Bengaluru, Karnataka 560075, India",
      "geometry":{
         "location":{
            "lat":12.9695745,
            "lng":77.64932829999998
         },
         "location_type":"ROOFTOP",
         "viewport":{
            "south":12.9682255197085,
            "west":77.6479793197085,
            "north":12.97092348029151,
            "east":77.65067728029157
         }
      },
      "place_id":"ChIJeREl3gEUrjsRsBz9-c5UihA",
      "types":[
         "street_address"
      ]
   },
   {
      "address_components":[
         {
            "long_name":"Ibixa Nilayam",
            "short_name":"Ibixa Nilayam",
            "types":[
               "premise"
            ]
         },
         {
            "long_name":"1st Cross Road",
            "short_name":"1st Cross Rd",
            "types":[
               "route"
            ]
         },
         {
            "long_name":"HAL 3rd Stage",
            "short_name":"HAL 3rd Stage",
            "types":[
               "neighborhood",
               "political"
            ]
         },
         {
            "long_name":"Hal",
            "short_name":"Hal",
            "types":[
               "sublocality_level_3",
               "sublocality",
               "political"
            ]
         },
         {
            "long_name":"New Tippasandra",
            "short_name":"New Tippasandra",
            "types":[
               "sublocality_level_1",
               "sublocality",
               "political"
            ]
         },
         {
            "long_name":"Bengaluru",
            "short_name":"Bengaluru",
            "types":[
               "locality",
               "political"
            ]
         },
         {
            "long_name":"Bangalore Urban",
            "short_name":"Bangalore Urban",
            "types":[
               "administrative_area_level_2",
               "political"
            ]
         },
         {
            "long_name":"Karnataka",
            "short_name":"KA",
            "types":[
               "administrative_area_level_1",
               "political"
            ]
         },
         {
            "long_name":"India",
            "short_name":"IN",
            "types":[
               "country",
               "political"
            ]
         },
         {
            "long_name":"560075",
            "short_name":"560075",
            "types":[
               "postal_code"
            ]
         }
      ],
      "formatted_address":"Ibixa Nilayam, 1st Cross Rd, HAL 3rd Stage, Hal, New Tippasandra, Bengaluru, Karnataka 560075, India",
      "geometry":{
         "location":{
            "lat":12.9697485,
            "lng":77.64837650000004
         },
         "location_type":"ROOFTOP",
         "viewport":{
            "south":12.9683995197085,
            "west":77.64702751970844,
            "north":12.9710974802915,
            "east":77.64972548029152
         }
      },
      "place_id":"ChIJp1mP_QEUrjsRGFoERk47ojA",
      "types":[
         "premise"
      ]
   },
   {
      "address_components":[
         {
            "long_name":"Hal",
            "short_name":"Hal",
            "types":[
               "sublocality_level_3",
               "sublocality",
               "political"
            ]
         },
         {
            "long_name":"New Tippasandra",
            "short_name":"New Tippasandra",
            "types":[
               "sublocality_level_1",
               "sublocality",
               "political"
            ]
         },
         {
            "long_name":"Bengaluru",
            "short_name":"Bengaluru",
            "types":[
               "locality",
               "political"
            ]
         },
         {
            "long_name":"Bangalore Urban",
            "short_name":"Bangalore Urban",
            "types":[
               "administrative_area_level_2",
               "political"
            ]
         },
         {
            "long_name":"Karnataka",
            "short_name":"KA",
            "types":[
               "administrative_area_level_1",
               "political"
            ]
         },
         {
            "long_name":"India",
            "short_name":"IN",
            "types":[
               "country",
               "political"
            ]
         }
      ],
      "formatted_address":"Hal, New Tippasandra, Bengaluru, Karnataka, India",
      "geometry":{
         "bounds":{
            "south":12.968119,
            "west":77.6469181,
            "north":12.973079,
            "east":77.65203759999997
         },
         "location":{
            "lat":12.9705598,
            "lng":77.65003300000001
         },
         "location_type":"APPROXIMATE",
         "viewport":{
            "south":12.968119,
            "west":77.6469181,
            "north":12.973079,
            "east":77.65203759999997
         }
      },
      "place_id":"ChIJBySRbaoWrjsRTBwAtBhrFy4",
      "types":[
         "sublocality_level_3",
         "sublocality",
         "political"
      ]
   },
   {
      "address_components":[
         {
            "long_name":"HAL 3rd Stage",
            "short_name":"HAL 3rd Stage",
            "types":[
               "neighborhood",
               "political"
            ]
         },
         {
            "long_name":"Hal",
            "short_name":"Hal",
            "types":[
               "sublocality_level_3",
               "sublocality",
               "political"
            ]
         },
         {
            "long_name":"Puttappa layout",
            "short_name":"Puttappa layout",
            "types":[
               "sublocality_level_2",
               "sublocality",
               "political"
            ]
         },
         {
            "long_name":"New Tippasandra",
            "short_name":"New Tippasandra",
            "types":[
               "sublocality_level_1",
               "sublocality",
               "political"
            ]
         },
         {
            "long_name":"Bengaluru",
            "short_name":"Bengaluru",
            "types":[
               "locality",
               "political"
            ]
         },
         {
            "long_name":"Bangalore Urban",
            "short_name":"Bangalore Urban",
            "types":[
               "administrative_area_level_2",
               "political"
            ]
         },
         {
            "long_name":"Karnataka",
            "short_name":"KA",
            "types":[
               "administrative_area_level_1",
               "political"
            ]
         },
         {
            "long_name":"India",
            "short_name":"IN",
            "types":[
               "country",
               "political"
            ]
         }
      ],
      "formatted_address":"HAL 3rd Stage, Hal, Puttappa layout, New Tippasandra, Bengaluru, Karnataka, India",
      "geometry":{
         "bounds":{
            "south":12.959791,
            "west":77.64563810000004,
            "north":12.9828131,
            "east":77.66145219999999
         },
         "location":{
            "lat":12.9682741,
            "lng":77.65026390000003
         },
         "location_type":"APPROXIMATE",
         "viewport":{
            "south":12.959791,
            "west":77.64563810000004,
            "north":12.9828131,
            "east":77.66145219999999
         }
      },
      "place_id":"ChIJrbTS4v8TrjsRRkmWWe2g7WU",
      "types":[
         "neighborhood",
         "political"
      ]
   },
   {
      "address_components":[
         {
            "long_name":"New Tippasandra",
            "short_name":"New Tippasandra",
            "types":[
               "sublocality_level_1",
               "sublocality",
               "political"
            ]
         },
         {
            "long_name":"Bengaluru",
            "short_name":"Bengaluru",
            "types":[
               "locality",
               "political"
            ]
         },
         {
            "long_name":"Bangalore Urban",
            "short_name":"Bangalore Urban",
            "types":[
               "administrative_area_level_2",
               "political"
            ]
         },
         {
            "long_name":"Karnataka",
            "short_name":"KA",
            "types":[
               "administrative_area_level_1",
               "political"
            ]
         },
         {
            "long_name":"India",
            "short_name":"IN",
            "types":[
               "country",
               "political"
            ]
         },
         {
            "long_name":"560075",
            "short_name":"560075",
            "types":[
               "postal_code"
            ]
         }
      ],
      "formatted_address":"New Tippasandra, Bengaluru, Karnataka 560075, India",
      "geometry":{
         "bounds":{
            "south":12.9668091,
            "west":77.64508000000001,
            "north":12.987042,
            "east":77.66887930000007
         },
         "location":{
            "lat":12.9717571,
            "lng":77.65519289999997
         },
         "location_type":"APPROXIMATE",
         "viewport":{
            "south":12.9668091,
            "west":77.64508000000001,
            "north":12.987042,
            "east":77.66887930000007
         }
      },
      "place_id":"ChIJkSWIPasWrjsRs1zz-INh_80",
      "types":[
         "sublocality_level_1",
         "sublocality",
         "political"
      ]
   },
   {
      "address_components":[
         {
            "long_name":"Bengaluru",
            "short_name":"Bengaluru",
            "types":[
               "locality",
               "political"
            ]
         },
         {
            "long_name":"Bangalore Urban",
            "short_name":"Bangalore Urban",
            "types":[
               "administrative_area_level_2",
               "political"
            ]
         },
         {
            "long_name":"Karnataka",
            "short_name":"KA",
            "types":[
               "administrative_area_level_1",
               "political"
            ]
         },
         {
            "long_name":"India",
            "short_name":"IN",
            "types":[
               "country",
               "political"
            ]
         },
         {
            "long_name":"560001",
            "short_name":"560001",
            "types":[
               "postal_code"
            ]
         }
      ],
      "formatted_address":"Bengaluru, Karnataka 560001, India",
      "geometry":{
         "bounds":{
            "south":12.7342888,
            "west":77.37919809999994,
            "north":13.173706,
            "east":77.88268089999997
         },
         "location":{
            "lat":12.9715987,
            "lng":77.59456269999998
         },
         "location_type":"APPROXIMATE",
         "viewport":{
            "south":12.7342888,
            "west":77.37919809999994,
            "north":13.173706,
            "east":77.88268089999997
         }
      },
      "place_id":"ChIJbU60yXAWrjsR4E9-UejD3_g",
      "types":[
         "locality",
         "political"
      ]
   },
   {
      "address_components":[
         {
            "long_name":"560075",
            "short_name":"560075",
            "types":[
               "postal_code"
            ]
         },
         {
            "long_name":"Bengaluru",
            "short_name":"Bengaluru",
            "types":[
               "locality",
               "political"
            ]
         },
         {
            "long_name":"Bangalore Urban",
            "short_name":"Bangalore Urban",
            "types":[
               "administrative_area_level_2",
               "political"
            ]
         },
         {
            "long_name":"Karnataka",
            "short_name":"KA",
            "types":[
               "administrative_area_level_1",
               "political"
            ]
         },
         {
            "long_name":"India",
            "short_name":"IN",
            "types":[
               "country",
               "political"
            ]
         }
      ],
      "formatted_address":"Bengaluru, Karnataka 560075, India",
      "geometry":{
         "bounds":{
            "south":12.9594257,
            "west":77.64665639999998,
            "north":12.9837859,
            "east":77.68402979999996
         },
         "location":{
            "lat":12.9706322,
            "lng":77.65293029999998
         },
         "location_type":"APPROXIMATE",
         "viewport":{
            "south":12.9594257,
            "west":77.64665639999998,
            "north":12.9837859,
            "east":77.68402979999996
         }
      },
      "place_id":"ChIJ-ay2Kv4TrjsRaahJMXgD2iQ",
      "types":[
         "postal_code"
      ]
   },
   {
      "address_components":[
         {
            "long_name":"Bangalore Urban",
            "short_name":"Bangalore Urban",
            "types":[
               "administrative_area_level_2",
               "political"
            ]
         },
         {
            "long_name":"Karnataka",
            "short_name":"KA",
            "types":[
               "administrative_area_level_1",
               "political"
            ]
         },
         {
            "long_name":"India",
            "short_name":"IN",
            "types":[
               "country",
               "political"
            ]
         }
      ],
      "formatted_address":"Bangalore Urban, Karnataka, India",
      "geometry":{
         "bounds":{
            "south":12.65785,
            "west":77.32866999999999,
            "north":13.22497,
            "east":77.83501000000001
         },
         "location":{
            "lat":12.9700247,
            "lng":77.65361250000001
         },
         "location_type":"APPROXIMATE",
         "viewport":{
            "south":12.65785,
            "west":77.32866999999999,
            "north":13.22497,
            "east":77.83501000000001
         }
      },
      "place_id":"ChIJ_Q7NCkQWrjsRn2yP7O-T8Fg",
      "types":[
         "administrative_area_level_2",
         "political"
      ]
   },
   {
      "address_components":[
         {
            "long_name":"Karnataka",
            "short_name":"KA",
            "types":[
               "administrative_area_level_1",
               "political"
            ]
         },
         {
            "long_name":"India",
            "short_name":"IN",
            "types":[
               "country",
               "political"
            ]
         }
      ],
      "formatted_address":"Karnataka, India",
      "geometry":{
         "bounds":{
            "south":11.593352,
            "west":74.09288019999997,
            "north":18.4411689,
            "east":78.58601010000007
         },
         "location":{
            "lat":15.3172775,
            "lng":75.71388839999997
         },
         "location_type":"APPROXIMATE",
         "viewport":{
            "south":11.593352,
            "west":74.09288019999997,
            "north":18.4411689,
            "east":78.58601010000007
         }
      },
      "place_id":"ChIJj0i_N0xaozsR1Xx10YzS8UE",
      "types":[
         "administrative_area_level_1",
         "political"
      ]
   },
   {
      "address_components":[
         {
            "long_name":"India",
            "short_name":"IN",
            "types":[
               "country",
               "political"
            ]
         }
      ],
      "formatted_address":"India",
      "geometry":{
         "bounds":{
            "south":6.7535159,
            "west":68.1623859,
            "north":35.5087008,
            "east":97.39556100000004
         },
         "location":{
            "lat":20.593684,
            "lng":78.96288000000004
         },
         "location_type":"APPROXIMATE",
         "viewport":{
            "south":6.7535159,
            "west":68.1628852,
            "north":35.5087008,
            "east":97.39556100000004
         }
      },
      "place_id":"ChIJkbeSa_BfYzARphNChaFPjNc",
      "types":[
         "country",
         "political"
      ]
   }
]





--%>