<%@ include file="/view/common/taglib.jsp" %>
<c:choose>
    <c:when test="${not empty items  && fn:length(items) gt 0}">
        <c:forEach items="${items}" var="item">
            <li class="post-${item.id} product instock" style="width: 200px;">
                <a href='<c:url value="/comm/shop/${item.id}.html" />'>
                    <div class="cherry-thumb-wrap">
                        <%--<span class="onsale">Sale!</span>--%>
                        <c:choose>
                            <c:when test="${not empty item.imageURL}">
                                <img width="300" height="300" src="${item.imageURL[0]}" class="attachment-shop_catalog wp-post-image" alt="${item.name}"/>
                            </c:when>
                            <c:otherwise>
                                <img width="300" height="300" src="" class="attachment-shop_catalog wp-post-image" alt="${item.name}"/>
                            </c:otherwise>
                        </c:choose>
                        <span class="btn cherry-quick-view" data-product="${item.id}">Quick view</span>
                    </div>
                    <h3>${item.name}</h3>
                        <%--<div class="star-rating" title="Rated 4.00 out of 5">
                            <span style="width:80%">
                                <strong  class="rating">4.00</strong> out of 5
                            </span>
                        </div>--%>
                        <%--<span class="price">
                            <span class="standard-price">${item.itemDetailList[0].sellingPrice}</span>
                        </span>--%>
                        <%--<span class="size">${item.itemDetailList[0].size}</span>--%>
                </a>
                <div style="display: block;width: 80%;">
                    <form action="<c:url value="/comm/cart.html" />" method="post" class="cart_form">
                        <input type="hidden" name="itemId" value="${item.id}"/>
                        <div class="custom-select-box" style="width: 65%; float: left;">
                            <select name="itemDetailId">
                                <c:forEach items="${item.itemDetailList}" var="detail">
                                    <option value="${detail.id}">${detail.itemSize}&nbsp;&nbsp;${detail.sellingPrice}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div  style="width: 20%; float: right;">
                            <input type="number" name="quantity" value="1" step="1" min="1" max="${detail.quantity}"/>
                        </div>
                            <%--<div class="" style="display: block;width:30%;float: left;">
                                <span class="size">${item.itemDetailList[0].itemSize}Ml</span>
                            </div>
                            <div class="" style="display: block;width: 55%; float: left;">
                                <span class="standard-price">${item.itemDetailList[0].sellingPrice}</span>
                                <span class="standard-price">${item.itemDetailList[0].sellingPrice}</span>
                            </div>--%>
                        <div class="">
                            <%--<span class="offer">Offer (50%)</span>--%>
                        </div>
                    </form>
                </div>
                                                      <%--
                       data-item_id="${item.id}" data-product_sku="" data-quantity="1" data-item_detail_id ="${item.itemDetailList[0].id}"--%>
                    <%-- <a href='<c:url value="/comm/shop/${item.id}.html" />' class='btn'>Details</a>--%>
                <div class="product-list-buttons">
                    <a href='<c:url value="/comm/cart.html" />' rel="nofollow"
                       class="button add_to_cart_button product_type_simple">Add to cart</a>
                        <%--<a href="/woocommerce_53953/shop/?orderby=popularity&amp;action=yith-woocompare-add-product&amp;id=1938&amp;_wpnonce=2a4f8c3f4d"
                            class="compare" data-product_id="${item.id}">Compare
                        </a>--%>
                    <div class="yith-wcwl-add-to-wishlist">
                        <div class="yith-wcwl-add-button show">
                            <a href='<c:url value="/comm/shortList.html" />'
                               data-product-id="${item.id}" data-product-type="simple" class="add_to_wishlist">Add to Wishlist
                            </a>
                            <img src='<c:url value="/images/banner/wpspin-light.gif" />'
                                 class="ajax-loading" id="add-items-ajax-loading-${item.id}" alt="" width="16" height="16"
                                 style="visibility:hidden"/>
                        </div>
                        <div class="yith-wcwl-wishlistaddedbrowse hide" style="display:none;">
                            <span class="feedback">Product added!</span>
                            <a href='<c:url value="/comm/shortList.html" />'>Browse  Wishlist</a>
                        </div>
                        <div class="yith-wcwl-wishlistexistsbrowse hide" style="display:none">
                            <span  class="feedback">The product is already in the wishlist!</span>
                            <a href='<c:url value="/comm/shortList.html" />'>Browse Wishlist</a>
                        </div>
                        <div style="clear:both"></div>
                        <div class="yith-wcwl-wishlistaddresponse"></div>
                    </div>
                    <div class="clear"></div>
                </div>
            </li>
        </c:forEach>

    </c:when>
    <c:otherwise>
        <div class="text-uppercase text-center no-more-item"> Sorry!! No More Item...</div>
    </c:otherwise>
</c:choose>