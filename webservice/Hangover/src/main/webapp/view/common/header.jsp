<%@ include file="/view/common/taglib.jsp" %>
<style type="text/css">
    .logoo{
        color: #fff;
        text-shadow: #ccc 0 1px 0, #c9c9c9 0 2px 0, #bbb 0 3px 0, #b9b9b9 0 4px 0, #aaa 0 5px 0,rgba(0,0,0,.1) 0 6px 1px, rgba(0,0,0,.1) 0 0 5px, rgba(0,0,0,.3) 0 1px 3px, rgba(0,0,0,.15) 0 3px 5px, rgba(0,0,0,.2) 0 5px 10px, rgba(0,0,0,.2) 0 10px 10px, rgba(0,0,0,.1) 0 20px 20px;
        font-size: 28px;  text-transform: uppercase; /*font-weight: bold;*/
        /*text-shadow: #969696 1px 3px 0, #aba8a8 1px 13px 5px;*/
    }


</style>
<header class="motopress-wrapper header">
<div class="container ">
<div class="row">
<div class="span12">
<div class="logo_box">
    <div class="row">
        <div class="span12">
            <div class="header-wrapper1">
                <div class="logo-wrapper">
                    <div class="logo pull-left">
                        <a href="<c:url value="/comm/home.html"/>" class="logo_h logo_h__img" >
                            <%--<img width="200px" src=""
                      alt="Liquor At Door" title="Beverage store"><span class="beta" style="color: #009587;margin-left: 5px;">Beta</span>--%>
                            <span class="logoo">Liquor@Door</span>
                            <span class="beta" style="color: #009587;font-weight: bold;left: -20%;position: absolute;z-index: 3;top: 10px;transform:rotate(-45deg);">Beta</span>
                        </a>
                        <p class="logo_tagline">Beverage store</p>
                    </div>
                </div>
                <div class="search-wrapper">
                    <div class="search-form search-form__h clearfix">
                        <form id="search-header" class="navbar-form" method="get" autocomplete="off"
                              action="<c:url value="/comm/search.html"/>"
                              accept-charset="utf-8">
                            <input type="text" name="query" placeholder="" view="shop/searchItem" data_url="search_item" class="search-form_it">
                            <input type="submit" value="" id="search-form_is"
                                   class="search-form_is btn btn-primary">
                        </form>
                        <div id="search_result" >
                            <ul>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="delivery_location_block">
                    <div class="delivery_location_wrapper lfloat hide" id="delivery_location" title="Delivery Location">
                        <i class="fa fa-map-marker" style="font-size: 15px;"></i>
                        <span class="delivery_location"></span>
                    </div>
                    <security:authorize access="isAuthenticated()">
                        <div class="login_detail rfloat" style="margin-left: 5px;">| Welcome: ${sessionScope.fullName}</div>
                    </security:authorize>
                </div>
                <div class="menu-wrapper" style="list-style: none;float: right;">
                    <ul>
                        <li>
                            <div class="menu_block dropdown">
                                <div class="menu_title" style="display: block;">
                                    <i class="fa fa-user fa-1x"></i>
                                    <i class="fa fa-sort-desc fa-1x"></i>
                                    <span>Account</span>
                                </div>

                                <div class="cherry-wc-account_content">
                                    <ul class="cherry-wc-account_list">
                                        <security:authorize access="isAuthenticated()">
                                            <li class="cherry-wc-account_list_item myaccount">
                                                <a href='<c:url value="/account.html"/>'>My Account</a>
                                            </li>
                                            <li class="cherry-wc-account_list_item orders">
                                                <a href='<c:url value="/account/order.html"/>'>My Order</a>
                                            </li>
                                            <%--<li class="cherry-wc-account_list_item credit">
                                                <a href='<c:url value="/account/credit.html"/>'>Credit</a>
                                            </li>--%>
                                        </security:authorize>
                                        <%--<li class="cherry-wc-account_list_item cherry-compare"><a href="#">Compare</a></li>--%>
                                        <li class="cherry-wc-account_list_item cherry-wishlist">
                                            <a href='<c:url value="/comm/shortList.html"/>'>Wishlist</a>
                                        </li>
                                    </ul>
                                    <div class="cherry-wc-account_auth" >
                                        <security:authorize access="isAuthenticated()">
                                            <a href='<c:url value="/j_spring_security_logout"/>' class='not-logged' id="logout">Sign out</a>
                                        </security:authorize>
                                        <security:authorize access="!isAuthenticated()">
                                            <a href='<c:url value="/login.html?ajax=true"/>' class='not-logged' id="login">Sign In</a>
                                        </security:authorize>
                                    </div>
                                    <security:authorize access="!isAuthenticated()">
                                        <div class="cherry-wc-account_auth" >
                                            <a href='<c:url value="/register.html?ajax=true"/>' class='not-logged' id="register">Sign Up</a>
                                        </div>
                                    </security:authorize>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="menu_block">
                                <div class="menu_title" style="display: block;">
                                    <i class="fa fa-question fa-1x "></i>
                                    <br>
                                    <span>Help</span>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="menu_block">
                                <div class="menu_title" style="display: block;">
                                    <a href='<c:url value="/comm/shop.html"/>' id="shop">
                                        <i class=" hanover-icon icon-nhelpfilled"></i>
                                        <span>Shop</span>
                                    </a>
                                </div>
                            </div>
                        </li>
                        <security:authorize access="hasAnyRole('ROLE_SUPER_ADMIN','ROLE_ADMIN', 'ROLE_SUPPLIER','ROLE_STAFF')">
                            <li>
                                <div class="menu_block dropdown">
                                    <div class="menu_title" style="display: block;">
                                        <i class="fa fa-cogs fa-1x"></i>
                                        <i class="fa fa-sort-desc fa-1x"></i>
                                        <span>Settings</span>
                                    </div>

                                    <div class="cherry-wc-account_content">
                                        <ul class="cherry-wc-account_list">
                                            <security:authorize access="hasAnyRole('ROLE_SUPER_ADMIN','ROLE_ADMIN')">
                                                <li class="cherry-wc-account_list_item myaccount">
                                                    <a href='<c:url value="/SupplierEntity.html?view=hangover/supplierLayout"/>'>Suppliers</a>
                                                </li>
                                            </security:authorize>
                                            <security:authorize access="hasAnyRole('ROLE_SUPER_ADMIN','ROLE_ADMIN')">
                                                <li class="cherry-wc-account_list_item orders">
                                                    <a href='<c:url value="/SupplierStoreEntity.html?view=hangover/storeLayout"/>'>Stores</a>
                                                </li>
                                            </security:authorize>
                                            <security:authorize access="hasAnyRole('ROLE_SUPPLIER')">
                                                <li class="cherry-wc-account_list_item orders">
                                                    <a href='<c:url value="/SupplierStoreEntity/${supplierId}/supplier.html?view=hangover/storeLayout"/>'>Stores</a>
                                                </li>
                                            </security:authorize>
                                            <security:authorize access="hasAnyRole('ROLE_SUPER_ADMIN','ROLE_ADMIN','ROLE_SUPPLIER')">
                                                <li class="cherry-wc-account_list_item credit">
                                                    <a href='<c:url value="/SupplierStaffEntity/${supplierId}/supplier.html?view=hangover/staffLayout"/>'>Staffs</a>
                                                </li>
                                            </security:authorize>

                                                <%--<li class="cherry-wc-account_list_item cherry-compare"><a href="#">Compare</a></li>--%>
                                                <%--<li class="cherry-wc-account_list_item cherry-wishlist">
                                                    <a href='<c:url value="/comm/shortList.html"/>'>Wishlist</a>
                                                </li>--%>
                                        </ul>
                                    </div>
                                </div>
                            </li>
                        </security:authorize>
                    </ul>
                </div>
                <%--<div class="cherry-wc-account dropdown">
                    <div class="cherry-wc-account_title" style="display: block;">
                        <i class="icon-expand-arrow"></i>
                        <span>Account</span>
                    </div>

                    <div class="cherry-wc-account_content">
                        <ul class="cherry-wc-account_list">
                            <li class="cherry-wc-account_list_item orders"><a
                                    href="http://livedemo00.template-help.com/woocommerce_53953/my-account/">Orders</a>
                            </li>
                            &lt;%&ndash;<li class="cherry-wc-account_list_item cherry-compare"><a href="#">Compare</a></li>&ndash;%&gt;
                            <li class="cherry-wc-account_list_item cherry-wishlist"><a
                                    href='<c:url value="/comm/shortList.html"/>'>Wishlist</a>
                            </li>
                        </ul>
                        <div class="cherry-wc-account_auth" >
                            <security:authorize access="isAuthenticated()">
                                <a href='<c:url value="/j_spring_security_logout"/>' class='not-logged' id="logout">Sigh out</a>
                            </security:authorize>
                            <security:authorize access="!isAuthenticated()">
                                <a href='<c:url value="/login.html?ajax=true"/>' class='not-logged' id="login">Log In/Register</a>
                            </security:authorize>
                        </div>
                    </div>
                </div>--%>
                <%--<div class="shop-nav">
                    <ul id="shopnav" class="shop-menu">
                        <li id="menu-item-2025"
                            class="cherry-compare menu-item menu-item-type-custom menu-item-object-custom menu-item-2025">
                            <a href="#">Compare</a></li>
                        <li id="menu-item-2024"
                            class="menu-item menu-item-type-post_type menu-item-object-page menu-item-2024"><a
                                href='<c:url value="/comm/shortList.html"/>'>Wishlist</a>
                        </li>
                        <li id="menu-item-2029"
                            class="menu-item menu-item-type-post_type menu-item-object-page menu-item-2029"><a
                                href="http://livedemo00.template-help.com/woocommerce_53953/delivery/">Delivery</a>
                        </li>
                        <li id="menu-item-2033"
                            class="menu-item menu-item-type-post_type menu-item-object-page menu-item-2033"><a
                                href="http://livedemo00.template-help.com/woocommerce_53953/help/">Help</a></li>
                    </ul>
                </div>--%>

                <div class="cart-wrapper">
                    <div id="woocommerce_widget_cart-3" class="visible-all-devices cart-holder"><h3>Cart<span
                            class="cart-items">0</span></h3>

                        <div class="widget_shopping_cart_content"></div>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>
</div>

<%--<div class="header-wrapper2">
    <div class="logo-wrapper">
        <!-- BEGIN LOGO -->
        <div class="logo pull-left">
            <a href="http://livedemo00.template-help.com/woocommerce_53953/" class="logo_h logo_h__img"><img
                    src="http://livedemo00.template-help.com/woocommerce_53953/wp-content/themes/theme53953/images/logo.png"
                    alt="Alcoholic" title="Beverage store"></a>

            <p class="logo_tagline">Beverage store</p>
        </div>
    </div>
    <address class="header_schedule">
        <div id="text-7">
            <div class="textwidget"><p>Hours: 6am-4pm PST M-Th; </p>

                <p>6am-3pm PST Fri</p>
            </div>
        </div>
    </address>
    <address class="header_phone">
        <div id="text-6"><h4>Free phone:</h4>

            <div class="textwidget">800-2345-6789</div>
        </div>
    </address>
    <div class="clearfix"></div>
    <%@ include file="/view/common/menu.jsp" %>
</div>--%>
</div>
</div>
</div>
</header>

