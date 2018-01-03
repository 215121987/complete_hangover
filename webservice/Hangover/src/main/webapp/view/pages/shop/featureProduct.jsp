<%@ include file="/view/common/taglib.jsp" %>
<div class="products_wrapper">
    <div class="products_wrapper_wrap_inner">
        <h2>Featured products</h2>
        <div class="woocommerce columns-3">
            <ul class="products">
                <c:if test="${ not empty itemList}">
                    <c:forEach items="${itemList}" var="item">
                        <li class="first post-1958 product type-product status-publish has-post-thumbnail product_cat-vodka product_cat-whisky product_tag-etiam-dictum product_tag-mauris-posuere product_tag-vel-mauris featured shipping-taxable purchasable product-type-simple product-cat-vodka product-cat-whisky product-tag-etiam-dictum product-tag-mauris-posuere product-tag-vel-mauris instock">
                            <a href="http://livedemo00.template-help.com/woocommerce_53953/product/product-15/">
                                <div class="cherry-thumb-wrap">
                                    <span class="onsale">Sale!</span>
                                    <img width="300" height="300"
                                         src="http://livedemo00.template-help.com/woocommerce_53953/wp-content/uploads/2013/09/Antica-Formula-Carpano-Vermouth-1L-4-300x300.png"
                                         class="attachment-shop_catalog wp-post-image"
                                         alt="Antica Formula Carpano Vermouth 1L 4"/>
                                    <span class="btn cherry-quick-view" data-product="1958">Quick view</span>
                                </div>
                                <h3>Antica Formula Carpano Vermouth 1L</h3>

                                <div class="star-rating" title="Rated 5.00 out of 5">
                                    <span style="width:100%">
                                        <strong class="rating">5.00</strong> out of 5
                                    </span>
                                </div>
                                <span class="price">
                                    <span class="amount">&#36;${item.price}</span>
                                </span>
                            </a>
                            <a href="/woocommerce_53953/?add-to-cart=1958" rel="nofollow" data-product_id="1958"
                               data-product_sku="" class="button add_to_cart_button product_type_simple">Add to cart</a>
                            <a href='http://livedemo00.template-help.com/woocommerce_53953/product/product-15/'
                               class='btn'>Details</a>

                            <div class="product-list-buttons">
                                <a href="/woocommerce_53953/?action=yith-woocompare-add-product&amp;id=1958&amp;_wpnonce=4ff55504d9"
                                   class="compare" data-product_id="1958">Compare</a>

                                <div class="yith-wcwl-add-to-wishlist">
                                    <div class="yith-wcwl-add-button show">
                                        <a href="http://livedemo00.template-help.com/woocommerce_53953/wp-content/plugins/yith-woocommerce-wishlist/yith-wcwl-ajax.php?action=add_to_wishlist&#038;add_to_wishlist=1958"
                                           data-product-id="1958" data-product-type="simple" class="add_to_wishlist">Add
                                            to Wishlist</a>
                                        <img src="http://livedemo00.template-help.com/woocommerce_53953/wp-admin/images/wpspin_light.gif"
                                             class="ajax-loading" id="add-items-ajax-loading" alt="" width="16"
                                             height="16"
                                             style="visibility:hidden"/>
                                    </div>
                                    <div class="yith-wcwl-wishlistaddedbrowse hide" style="display:none;">
                                        <span class="feedback">Product added!</span>
                                        <a href="http://livedemo00.template-help.com/woocommerce_53953/wishlist/">Browse
                                            Wishlist</a>
                                    </div>
                                    <div class="yith-wcwl-wishlistexistsbrowse hide" style="display:none">
                                        <span class="feedback">The product is already in the wishlist!</span>
                                        <a href="http://livedemo00.template-help.com/woocommerce_53953/wishlist/">Browse
                                            Wishlist</a>
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
                    </c:forEach>
                </c:if>
            </ul>
        </div>
    </div>
</div>
<div class="clear"></div>