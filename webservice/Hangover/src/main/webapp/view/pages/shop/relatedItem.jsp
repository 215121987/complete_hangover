<%@ include file="/view/common/taglib.jsp" %>
<div class="related products">
    <h3>Related Products</h3>
    <ul class="products">
        <%@ include file="/view/pages/shop/shopItem.jsp" %>
        <%--<c:forEach items="${items}" var="item">
            <li class=" product  instock">
                <a href='<c:url value="/comm/shop/${item.id}.html"/>'>
                    <div class="cherry-thumb-wrap">
                        <img width="300" height="300" src="" class="attachment-shop_catalog wp-post-image"
                             alt="${item.name}"/>
                        <span class="btn cherry-quick-view" data-product="${item.id}">Quick view</span></div>
                    <h3>Bundaberg Rum MDC Black Barrel</h3>

                    <div class="star-rating" title="Rated 4.00 out of 5">
                        <span style="width:80%"> <strong class="rating">4.00</strong> out of 5</span>
                    </div>
                    <span class="price"><span class="amount">&#36;120.00</span></span>
                </a>
                <a href='<c:url value="/comm/cart.html" />' rel="nofollow" data-product_id="${item.id}"
                   data-product_sku="" class="button add_to_cart_button product_type_simple">Add to cart</a>
                <a href='<c:url value="/comm/shop/${item.id}.html" />' class='btn'>Details</a>

                <div class="product-list-buttons">
                    &lt;%&ndash;<a href="/woocommerce_53953/product/product-14/?action=yith-woocompare-add-product&amp;id=1946&amp;_wpnonce=2a4f8c3f4d"
                   class="compare" data-product_id="1946">Compare</a>&ndash;%&gt;
                    <div class="yith-wcwl-add-to-wishlist">
                        <div class="yith-wcwl-add-button show">
                            <a href='<c:url value="/comm/wishlist.html" />' data-product-id="${item.id}"
                               data-product-type="simple" class="add_to_wishlist">Add to Wishlist</a>
                            <img src="http://livedemo00.template-help.com/woocommerce_53953/wp-admin/images/wpspin_light.gif"
                                 class="ajax-loading" alt="" width="16" height="16" style="visibility:hidden"/>
                        </div>
                        <div class="yith-wcwl-wishlistaddedbrowse hide" style="display:none;"><span
                                class="feedback">Product added!</span>
                            <a href='<c:url value="/comm/wishlist.html" />'>Browse Wishlist</a>
                        </div>
                        <div class="yith-wcwl-wishlistexistsbrowse hide" style="display:none"><span class="feedback">The product is already in the wishlist!</span>
                            <a href='<c:url value="/comm/wishlist.html" />'>Browse Wishlist</a>
                        </div>
                        <div style="clear:both"></div>
                        <div class="yith-wcwl-wishlistaddresponse"></div>
                    </div>
                    <div class="clear"></div>
                    <script type="text/javascript">
                        if (!jQuery('#yith-wcwl-popup-message').length) {
                            jQuery('body').prepend(
                                    '<div id="yith-wcwl-popup-message" style="display:none;">' +
                                            '<div id="yith-wcwl-message"></div>' +
                                            '</div>'
                            );
                        }
                    </script>
                </div>
            </li>
        </c:forEach>--%>
    </ul>
</div>