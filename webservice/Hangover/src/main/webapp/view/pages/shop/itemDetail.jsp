<%@ include file="/view/common/taglib.jsp" %>
<div class="motopress-wrapper content-holder clearfix woocommerce">
    <div class="container">
        <div class="row">
            <div class="span12" id="content">
                <div  id="product-${item.id}" class=" product instock">
                    <%--<span class="onsale">Sale!</span>--%>

                    <div class="product-images">
                        <div class="product-thumbnails">
                            <div class="product-thumbnails_list cycle-slideshow vertical" data-cycle-fx="carousel"
                                 data-cycle-timeout="0"
                                 data-cycle-next=".product-thumbnails_next" data-cycle-prev=".product-thumbnails_prev"
                                 data-cycle-carousel-visible="4" data-cycle-carousel-vertical="true"
                                 data-allow-wrap="false">
                                <c:forEach items="${item.imageURL}" var="image">
                                    <div class="product-thumbnails_item"
                                         data-original-img="" data-large-img="">
                                        <img width="90" height="90" src="${image}" class="attachment-shop_thumbnail"
                                             alt="${item.name}"/>
                                    </div>
                                </c:forEach>
                            </div>
                            <a href="#" class="product-thumbnails_prev">
                                <i class="icon-caret-up"></i>
                            </a>
                            <a href="#"class="product-thumbnails_next">
                                <i class="icon-caret-down"></i>
                            </a>
                        </div>
                        <div class="product-large-image">
                            <%--<img width="370" height="370"
                                 src="http://livedemo00.template-help.com/woocommerce_53953/wp-content/uploads/2013/09/Armand-de-Brignac-Brut-Gold-1.5L-Magnum-4-370x370.png"
                                 class="attachment-shop_single wp-post-image"
                                 alt="Armand de Brignac Brut Gold 1.5L Magnum" itemprop="image"
                                 data-zoom-image="http://livedemo00.template-help.com/woocommerce_53953/wp-content/uploads/2013/09/Armand-de-Brignac-Brut-Gold-1.5L-Magnum-4.png"
                                 title="${item.name}"/>--%>
                            <img width="370" height="370"
                                 src="${item.imageURL[0]}" class="attachment-shop_single wp-post-image"
                                 alt="Armand de Brignac Brut Gold 1.5L Magnum" itemprop="image"
                                 data-zoom-image="${item.imageURL[0]}"  title="${item.name}"/>
                        </div>
                    </div>
                    <div class="summary entry-summary item-detail-block">
                        <h1 itemprop="name" class="product_title entry-title">${item.name}</h1>

                        <%--<div class="woocommerce-product-rating" itemprop="aggregateRating" itemscope
                             itemtype="http://schema.org/AggregateRating">
                            <div class="star-rating" title="Rated 4.50 out of 5">
                                <span style="width:90%"><strong itemprop="ratingValue" class="rating">4.50</strong> out of 5</span>
                            </div>
                            <a href="#reviews" class="woocommerce-review-link" rel="nofollow">(<span
                                    itemprop="ratingCount" class="count">2</span> customer reviews)</a>
                        </div>--%>
                        <div itemprop="offers" itemscope itemtype="http://schema.org/Offer">
                            <p class="price">
                                <del><span class="standard-price">${item.itemDetailList[0].sellingPrice}</span></del>
                                <ins><span class="standard-price">${item.itemDetailList[0].sellingPrice}</span></ins>
                            </p>
                            <%--<meta itemprop="price" content="70"/>
                            <meta itemprop="priceCurrency" content="USD"/>
                            <link itemprop="availability" href="http://schema.org/InStock"/>--%>
                        </div>
                        <div itemprop="description">
                            <p>${item.description}</p>
                        </div>
                        <p class="stock"><span>${item.itemDetailList[0].quantity}</span> in stock</p>
                        <div style="width: 70%;">
                            <form method="post" action="<c:url value="/comm/cart.html" />">
                                <input type="hidden" name="itemId" value="${item.id}"/>
                                <input type="hidden" name="action" value="add-to-cart"/>
                                <div class="custom-select-box" style="width: 30%; float: left;">
                                    <select name="itemDetailId">
                                        <c:forEach items="${item.itemDetailList}" var="detail">
                                            <option value="${detail.id}">${detail.itemSize}Ml&nbsp;&nbsp;${detail.sellingPrice}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div  style="width: 25%;margin-left: 10px; float: left;">
                                    <input type="number" name="quantity" value="1" step="1" min="1" max="${detail.quantity}"/>
                                </div>
                                <div style="margin-left: 15px; float: left;">
                                    <input type="submit" class="button" value="Add to cart"/>
                                    <%--<a href='#' class="button add_to_cart_button product_type_simple">Add to cart</a>--%>
                                </div>
                            </form>
                        </div>

                        <div class="clear"></div>
                        <div class="yith-wcwl-add-to-wishlist">
                            <div class="yith-wcwl-add-button show">
                                <a href='<c:url value="/comm/shortList/add.html"/>' data-product-id="${item.id}"
                                   data-product-type="simple" class="add_to_wishlist">Add to Wishlist</a>
                                <img src="http://livedemo00.template-help.com/woocommerce_53953/wp-admin/images/wpspin_light.gif"
                                     class="ajax-loading" alt="" width="16" height="16" style="visibility:hidden"/>
                            </div>
                            <div class="yith-wcwl-wishlistaddedbrowse hide" style="display:none;">
                                <span class="feedback">Product added!</span>
                                <a href='<c:url value="/comm/shortList.html"/>'>Browse Wishlist</a>
                            </div>
                            <div class="yith-wcwl-wishlistexistsbrowse hide" style="display:none">
                                <span class="feedback">The product is already in the wishlist!</span>
                                <a href='<c:url value="/comm/shortList.html"/>'>Browse Wishlist</a>
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
                        <div class="product_meta">
                            <span class="posted_in">Category: <a href='<c:url value="/comm/category-${item.category.id}/shop.html"/>'
                                                                 rel="tag">${item.category.name}</a>.</span>
                            <%--<span class="tagged_as">Tags:
                                <a href="http://livedemo00.template-help.com/woocommerce_53953/product-tag/sed-blandit-massa/" rel="tag">Sed blandit massa</a>,
                               <a href="http://livedemo00.template-help.com/woocommerce_53953/product-tag/vel-mauris/"
                                 rel="tag">vel mauris</a>.
                            </span>--%>
                        </div>
                        <div class="share-buttons">
                            <div class="share-buttons_item">
                                <a href="#"
                                   data-url="https://www.facebook.com/sharer/sharer.php"
                                   class="share-buttons_link link-facebook"><i class="fa fa-facebook"></i>
                                </a>
                            </div>
                            <div class="share-buttons_item">
                                <a href="#"
                                   data-url="https://twitter.com/intent/tweet"
                                   class="share-buttons_link link-twitter"><i class="fa fa-twitter"></i>
                                </a>
                            </div>
                            <div class="share-buttons_item">
                                <a href="#"
                                   data-url="https://plus.google.com/share"
                                   class="share-buttons_link link-google-plus"><i class="fa fa-google-plus"></i>
                                </a>
                            </div>
                            <div class="share-buttons_item">
                                <a href="#"
                                   data-url="https://pinterest.com/pin/create/bookmarklet/"
                                   class="share-buttons_link link-pinterest"><i class="fa fa-pinterest"></i>
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="woocommerce-tabs" id="hangover_tabs">
                        <ul class="tabs">
                            <li class="description_tab">
                                <a href="#tab-description">Item Info</a>
                            </li>
                            <li class="reviews_tab">
                                <a href="#tab-reviews">Reviews</a>
                            </li>
                            <%--<li class="cherry_wc_video_tab">
                                <a href="#tab-cherry_wc_video">Video</a>
                            </li>--%>
                        </ul>
                        <div class="panel entry-content" id="tab-description">
                            <h2>Item Info</h2>

                            <p>${item.description}</p>
                        </div>
                        <%@ include file="/view/pages/shop/itemReview.jsp" %>
                        <%--<div class="panel entry-content" id="tab-cherry_wc_video">
                            <div class="video-tab-wrap">
                                <iframe width="1140" height="641"
                                        src="https://www.youtube.com/embed/QrN6CswQN7c?feature=oembed&wmode=transparent"
                                        frameborder="0"
                                        allowfullscreen></iframe>
                            </div>
                        </div>--%>
                    </div>
                    <%@ include file="/view/pages/shop/relatedItem.jsp" %>
                </div>
            </div>
        </div>
    </div>
</div>

