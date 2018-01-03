<%@ include file="/view/common/taglib.jsp" %>
<%--<div class="motopress-wrapper content-holder clearfix">
    <article class="main-banner clearfix">
        <div class="page-title text-uppercase">
            <span class="profile-logo"></span>
            My WishList
        </div>
        <figure class="bnr-R-second-img">
            <img class="loaded" alt="Dashboard" data-src-1280="https://static1.jabong.com/live/images/dashboard_banner.jpg" data-src-1024="https://static1.jabong.com/live/images/dashboard_banner.jpg" data-src-768="https://static1.jabong.com/live/images/dashboard_banner.jpg" data-src-500="https://static1.jabong.com/live/images/dashboard-480x200.jpg" data-src-320="https://static1.jabong.com/live/images/dashboard-480x200.jpg" src="https://static1.jabong.com/live/images/dashboard_banner.jpg">
            <span class="black-overlay"></span>
        </figure>
    </article>
    <div class="container minus_container" >
        <div class="row">
            <div class="span12" id="content">
                <div class="action_block col-xs-12">
                    <div class="search-wrapper">
                        <div class="search-form search-form__h clearfix" id="search-header">
                            <input type="text" name="filterQuery" data_url="supplier_staff" searchOnUI="true" searchFor="name-email-mobile"
                                   view="hangover/staffList" placeholder="Search Staff" class="search-form_it"/>
                            <i class="fa icon-search icon-2x"></i>
                        </div>
                    </div>
                    <div class="action_button">
                        <a href="" class="add-parallax-item" data_url="add_staff" req_data="supplierId:1" view="hangover/supplierStaffForm" title="Add Staff">
                            <i class="fa icon-plus-sign icon-3x"></i>
                        </a>
                    </div>
                </div>
                <div id="entity_content">
                    <c:choose>
                        <c:when test="${not empty entities}">
                            <c:forEach items="${entities}" var="staff">
                                <div class="col-md-4 item-block">
                                    <div class="parallax-item sans-shadow text-center">
                                        <i class="fa fa-align-left fa-3x"></i>
                                        <h4 class="searchable">Name:- ${staff.user.name}</h4>
                                        <div class="item-info">
                                            <h4 class="searchable">Name:- ${staff.user.name}</h4>
                                            <ul class="item-meta">
                                                <li>
                                                    <a href="" class="add-parallax-item" data_url="add_staff" req_data="id:${staff.id}" view="hangover/supplierStaffForm" title="Add To Cart">
                                                        <i class="fa icon-plus-sign icon-2x"></i>
                                                    </a>
                                                </li>
                                                <li>
                                                    <a href="" class="delete-parallax-item" data_url="delete_wishList_item" data_id="${staff.id}"
                                                       title="Remove Item"><i class="fa icon-remove-sign icon-2x"></i></a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <div class="text-uppercase text-center no-more-item"> Sorry!! No Item...</div>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="clear"></div>
            </div>
        </div>
    </div>
</div>--%>





<div class="motopress-wrapper content-holder clearfix">
    <div class="container">
        <div class="row">
            <div class="span12" data-motopress-wrapper-type="content">
                <div class="row">
                    <div class="span9 right right" id="content" >
                        <div id="post-2022" class="page post-2022 type-page status-publish hentry">
                            <%--<div class="woocommerce-message">
                                <a class="button wc-forward"
                                   href="http://livedemo00.template-help.com/woocommerce_53953/cart/">View Cart</a>
                                "Antica Formula Carpano Vermouth 1L" was successfully added to your cart.
                            </div>--%>
                            <div id="yith-wcwl-messages"></div>
                            <form id="yith-wcwl-form"  action="" method="post">
                                <c:forEach items="${wishLists}" var="wishList">
                                    <h2>${wishList.name}</h2>
                                    <table class="shop_table cart wishlist_table" cellspacing="0">
                                        <thead>
                                        <tr>
                                            <th class="product-remove"></th>
                                            <th class="product-thumbnail"></th>
                                            <th class="product-name"><span class="nobr">Product Name</span></th>
                                            <th class="product-price"><span class="nobr">Unit Price</span></th>
                                            <th><span class="nobr">Stock Status</span></th>
                                            <th><span class="nobr"></span></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:choose>
                                            <c:when test="${not empty wishList.wishListItems && fn:length(wishList.wishListItems)>0}">
                                                <c:forEach items="${wishList.wishListItems}" var="wishListItem">
                                                    <tr id="yith-wcwl-row-${wishListItem.id}">
                                                        <td class="product-remove">
                                                            <div>
                                                                <a href="javascript:void(0)" onclick="remove_item_from_wishlist( );"
                                                                   class="remove" title="Remove this product">&times;</a>
                                                            </div>
                                                        </td>
                                                        <td class="product-thumbnail">
                                                            <a href='<c:url value="/comm/shop/${wishListItem.item.id}.html"/>'>
                                                                <img width="90" height="90"
                                                                     src="${wishListItem.item.imageURL[0]}"
                                                                     class="attachment-shop_thumbnail wp-post-image"
                                                                     alt="${wishListItem.item.name}"/> </a>
                                                        </td>
                                                        <td class="product-name">
                                                            <a href='<c:url value="/comm/shop/${wishListItem.item.id}.html" />'>${wishListItem.item.name}</a>
                                                        </td>
                                                        <td class="product-price">
                                                            <span class="amount">&#36;70.00</span></td>
                                                        <td class="product-stock-status">
                                                            <span class="wishlist-in-stock">In Stock</span></td>
                                                        <td class="product-add-to-cart">
                                                            <a class="add_to_cart button alt" href="javascript:void(0);"
                                                               onclick="check_for_stock();">Add to Cart</a></td>
                                                    </tr>
                                                </c:forEach>
                                            </c:when>
                                            <c:otherwise>
                                                <tr id="yith-wcwl-row">
                                                    <td colspan="6"class="product-name">
                                                        No Item in Wish List
                                                    </td>
                                                </tr>
                                            </c:otherwise>
                                        </c:choose>
                                        </tbody>
                                    </table>
                                </c:forEach>
                            </form>
                            <div class="clear"></div>
                            <!--.pagination-->
                        </div>
                        <!--post-->
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>