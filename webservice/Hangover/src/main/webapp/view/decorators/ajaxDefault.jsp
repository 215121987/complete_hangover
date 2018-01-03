<!DOCTYPE html>
<%@ include file="/view/common/taglib.jsp" %>
<head>
    <%@ include file="/view/common/meta.jsp" %>
    <link rel="stylesheet" type="text/css" media="all" href='<c:url value="/style/bootstrap-custom.css" />'/>
    <link rel="stylesheet" type="text/css" media="all" href='<c:url value="/style/bootstrap.min-3.3.5.css" />'/>
    <link rel="stylesheet" type="text/css" media="all" href='<c:url value="/style/responsive.css" />'/>
    <link rel="stylesheet" type="text/css" media="all" href='<c:url value="/style/camera.css" />'/>
    <link rel="stylesheet" type="text/css" media="all" href='<c:url value="/style/style.css" />'/>
    <link rel="stylesheet" type="text/css" media="all" href='<c:url value="/style/style-4-2-7.css" />'/>
    <link rel="stylesheet" type="text/css" media="all" href='<c:url value="/style/cart.css" />'/>
    <link rel='stylesheet' id='flexslider-css' href='<c:url value="/style/flexslider.css" />' type='text/css'
          media='all'/>
    <link rel='stylesheet' id='owl-carousel-css' href='<c:url value="/style/owl.carousel.css" />' type='text/css'
          media='all'/>
    <link rel='stylesheet' id='owl-theme-css' href='<c:url value="/style/owl.theme.css" />' type='text/css'
          media='all'/>
    <link rel='stylesheet' id='font-awesome-css'
          href='//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css?ver=3.2.1' type='text/css'
          media='all'/>
    <link rel='stylesheet' id='cherry-plugin-css' href='<c:url value="/style/cherry-plugin.css" />' type='text/css'
          media='all'/>
    <link rel='stylesheet' id='cherry-parallax-css' href='<c:url value="/style/parallax.css" />' type='text/css'
          media='all'/>
    <link rel='stylesheet' id='contact-form-7-css' href='<c:url value="/style/styles.css" />' type='text/css'
          media='all'/>
    <link rel='stylesheet' id='woocommerce-layout-css' href='<c:url value="/style/hangover-layout.css" />'
          type='text/css' media='all'/>
    <link rel='stylesheet' id='woocommerce-general-css' href='<c:url value="/style/woocommerce.css" />' type='text/css'
          media='all'/>
    <link rel='stylesheet' id='jquery-colorbox-css' href='<c:url value="/style/colorbox.css" />' type='text/css'
          media='all'/>
    <link rel='stylesheet' id='mailchimp-for-wp-checkbox-css' href='<c:url value="/style/checkbox.min.css" />'
          type='text/css' media='all'/>
    <link rel='stylesheet' id='theme53953-css' href='<c:url value="/style/main-style.css" />' type='text/css'
          media='all'/>
    <link rel='stylesheet' id='magnific-popup-css' href='<c:url value="/style/magnific-popup.css" />' type='text/css'
          media='all'/>
    <link rel='stylesheet' id='mailchimp-for-wp-form-css' href='<c:url value="/style/form.min.css" />' type='text/css'
          media='all'/>
    <!--[if lt IE 9]>
    <link rel='stylesheet' id='theme_ie-css' href='<c:url value="/style/ie.css"/>' type='text/css' media='all'/>
    <![endif]-->
    <link rel='stylesheet' id='cherry_prettyPhoto_css-css' href='<c:url value="/style/prettyPhoto.css" />'
          type='text/css' media='all'/>

    <script type='text/javascript' src='<c:url value="/js/jquery-2.1.4.js" />'></script>
    <script type='text/javascript' src='<c:url value="/js/jquery.easing.1.3.js" />'></script>
    <script type='text/javascript' src='<c:url value="/js/jquery.elastislide.js" />'></script>
    <%--<script type='text/javascript' src='<c:url value="/js/jquery-ui.min.js" />'></script>--%>
    <script type='text/javascript'
            src='//maps.googleapis.com/maps/api/js?v=3.exp&#038;sensor=false&#038;ver=4.2.7'></script>
    <script type='text/javascript' src='<c:url value="/js/jquery-migrate-1.2.1.min.js" />'></script>
    <script type='text/javascript' src='<c:url value="/js/swfobject.js" />'></script>
    <script type='text/javascript' src='<c:url value="/js/modernizr.js" />'></script>
    <script type='text/javascript' src='<c:url value="/js/custom.js" />'></script>
    <script type='text/javascript' src='<c:url value="/js/bootstrap.min.js" />'></script>
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

        h3 {
            font: bold 14px/25px Roboto;
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
    <title><decorator:getProperty property="page.titleText"/><decorator:getProperty property="page.pageTitle"/></title>
    <decorator:head/>
    <%--<script src='<c:url value="/js/jquery.mobile.customized.min.js" />' type="text/javascript"></script>--%>



</head>
<body id="doc" <decorator:getProperty property="body.id" writeEntireProperty="true"/>
<decorator:getProperty
        property="body.class"
        writeEntireProperty="true"/>>
<div id="motopress-main" class="main-holder">

    <div id="main_body">
        <div class="motopress-wrapper content-holder clearfix">
            <div class="container">
                <div class="row">
                    <div class="span12">
                        <decorator:body/>
                    </div>
                </div>
            </div>
        </div>
        <%-- <decorator:body/>--%>
    </div>

</div>

</body>
<script type='text/javascript' src='<c:url value="/js/jquery.flexslider-min.js"/>'></script>
<script type='text/javascript' src='<c:url value="/js/cherry-plugin.js" />'></script>
<script type='text/javascript' src='<c:url value="/js/jquery.mousewheel.min.js" />'></script>
<script type='text/javascript' src='<c:url value="/js/jquery.simplr.smoothscroll.min.js" />'></script>
<script type='text/javascript' src='<c:url value="/js/device.min.js" />'></script>
<script type='text/javascript' src='<c:url value="/js/cherry.parallax.js" />'></script>
<script type='text/javascript' src='<c:url value="/js/script.js" />'></script>
<script type='text/javascript' src='<c:url value="/js/jquery.form.min.js" />'></script>
<script type='text/javascript' src='<c:url value="/js/scripts.js" />'></script>
<script type='text/javascript' src='<c:url value="/js/add-to-cart.min.js" />'></script>
<script type='text/javascript' src='<c:url value="/js/jquery.blockUI.min.js" />'></script>
<script type='text/javascript' src='<c:url value="/js/hangover.min.js" />'></script>
<script type='text/javascript' src='<c:url value="/js/jquery.cookie.min.js" />'></script>
<script type='text/javascript' src='<c:url value="/js/cart-fragments.min.js" />'></script>
<script type='text/javascript' src='<c:url value="/js/woocompare.js" />'></script>
<script type='text/javascript' src='<c:url value="/js/jquery.colorbox-min.js" />'></script>
<script type='text/javascript' src='<c:url value="/js/jquery.yith-wcwl.js" />'></script>
<script type='text/javascript' src='<c:url value="/js/superfish.js" />'></script>
<script type='text/javascript' src='<c:url value="/js/jquery.mobilemenu.js" />'></script>
<script type='text/javascript' src='<c:url value="/js/jquery.magnific-popup.min.js" />'></script>
<script type='text/javascript' src='<c:url value="/js/jplayer.playlist.min.js" />'></script>
<script type='text/javascript' src='<c:url value="/js/jquery.jplayer.min.js" />'></script>
<script type='text/javascript' src='<c:url value="/js/tmstickup.js?ver=1.0.0" />'></script>
<script type='text/javascript' src='<c:url value="/js/device.min.js?ver=1.0.0" />'></script>
<script type='text/javascript' src='<c:url value="/js/jquery.zaccordion.min.js" />'></script>
<script type='text/javascript' src='<c:url value="/js/camera.min.js" />'></script>
<script type='text/javascript' src='<c:url value="/js/custom-script.js" />'></script>
<script type='text/javascript' src='<c:url value="/js/jquery.prettyPhoto.min.js" />'></script>
<script type='text/javascript' src='<c:url value="/js/parallaxSlider.js" />'></script>
<script type='text/javascript' src='<c:url value="/js/jquery.elevatezoom.min-1.2.1.js" />'></script>
<script type='text/javascript' src='<c:url value="/js/single-product.min-2.1.12.js" />'></script>
<script type='text/javascript' src='<c:url value="/js/jquery.cycle2.min-1.2.1.js" />'></script>
<script type='text/javascript' src='<c:url value="/js/jquery.cycle2.carousel.min-1.2.1.js" />'></script>

</html>