<%@ include file="/view/common/taglib.jsp" %>
<meta content="true" name="askForLocation"/>
<div class="motopress-wrapper content-holder clearfix woocommerce">
    <div class="container">
        <div class="row">
            <div class="sidebar span2" id="sidebar" data-motopress-type="static-sidebar">
                <%@ include file="/view/pages/shop/shopFilter.jsp" %>
            </div>
            <div class="span10 right" id="content">
                <form class="woocommerce-ordering" method="get">
                    <div class="custom-select-box">
                        <select name="orderBy" class="orderby">
                            <option value="default">Default sorting</option>
                            <option value="popularity" selected='selected'>Sort by popularity</option>
                            <option value="rating">Sort by average rating</option>
                            <option value="date">Sort by newness</option>
                            <option value="price-asc">Sort by price: low to high</option>
                            <option value="price-desc">Sort by price: high to low</option>
                        </select>
                    </div>
                </form>
                <ul class="products">
                    <%@ include file="/view/pages/shop/shopItem.jsp" %>
                    <%--<c:choose>
                        <c:when test="${not empty items  && fn:length(items) gt 0}">
                            <c:forEach items="${items}" var="item">
                                <li class="post-${item.id} product instock" style="width: 200px;">
                                    <a href='<c:url value="/comm/shop/${item.id}.html" />'>
                                        <div class="cherry-thumb-wrap">
                                            <span class="onsale">Sale!</span>
                                            <img width="300" height="300" src="http://livedemo00.template-help.com/woocommerce_53953/wp-content/uploads/2013/09/Antica-Formula-Carpano-Vermouth-1L-4-300x300.png" class="attachment-shop_catalog wp-post-image" alt="${item.name}"/>
                                            <span class="btn cherry-quick-view" data-product="${item.id}">Quick view</span>
                                        </div>
                                        <h3>${item.name}</h3>
                                            &lt;%&ndash;<div class="star-rating" title="Rated 4.00 out of 5">
                                                <span style="width:80%">
                                                    <strong  class="rating">4.00</strong> out of 5
                                                </span>
                                            </div>&ndash;%&gt;
                                            &lt;%&ndash;<span class="price">
                                                <span class="standard-price">${item.itemDetailList[0].sellingPrice}</span>
                                            </span>&ndash;%&gt;
                                            &lt;%&ndash;<span class="size">${item.itemDetailList[0].size}</span>&ndash;%&gt;
                                    </a>
                                    <div style="display: block;width: 80%;">
                                        <form action="<c:url value="/comm/cart.html" />" method="post" class="cart_form">
                                            <input type="hidden" name="itemId" value="${item.id}"/>
                                            <div class="custom-select-box" style="width: 65%; float: left;">
                                                <select name="itemDetailId">
                                                    <c:forEach items="${item.itemDetailList}" var="detail">
                                                        <option value="${detail.id}">${detail.itemSize}Ml&nbsp;&nbsp;${detail.sellingPrice}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div  style="width: 20%; float: right;">
                                                <input type="number" name="quantity" value="1" step="1" min="1" max="${detail.quantity}"/>
                                            </div>
                                                &lt;%&ndash;<div class="" style="display: block;width:30%;float: left;">
                                                    <span class="size">${item.itemDetailList[0].itemSize}Ml</span>
                                                </div>
                                                <div class="" style="display: block;width: 55%; float: left;">
                                                    <span class="standard-price">${item.itemDetailList[0].sellingPrice}</span>
                                                    <span class="standard-price">${item.itemDetailList[0].sellingPrice}</span>
                                                </div>&ndash;%&gt;
                                            <div class="">
                                                <span class="offer">Offer (50%)</span>
                                            </div>
                                        </form>
                                    </div>

                                        &lt;%&ndash; <a href='<c:url value="/comm/shop/${item.id}.html" />' class='btn'>Details</a>&ndash;%&gt;
                                    <div class="product-list-buttons">
                                        <a href='<c:url value="/comm/cart.html" />' rel="nofollow"
                                           data-item_id="${item.id}" data-product_sku="" data-quantity="1" data-item_detail_id ="${item.itemDetailList[0].id}"
                                           class="button add_to_cart_button product_type_simple">Add to cart</a>
                                            &lt;%&ndash;<a href="/woocommerce_53953/shop/?orderby=popularity&amp;action=yith-woocompare-add-product&amp;id=1938&amp;_wpnonce=2a4f8c3f4d"
                                                class="compare" data-product_id="${item.id}">Compare
                                            </a>&ndash;%&gt;
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
                            <div class="text-uppercase text-center empty-cart"> Sorry!! No Item found...</div>
                        </c:otherwise>
                    </c:choose>--%>
                </ul>
                <div class="text-center">
                    <a class="btn btn-primary moreItems hidden">More Items</a>
                </div>
            </div>

            <%--including side pannel--%>
            <%--<%@ include file="/view/pages/shop/sidePanel.jsp" %>--%>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function(e){
        $(window).scroll(function(e){
            e.preventDefault();
            updateItemPaneOnScroll();
        });



        /* var TOKEN_SEPARATOR = "";
         var processSeparator = function(){
         TOKEN_SEPARATOR =  ""==TOKEN_SEPARATOR?TOKEN_SEPARATOR="--":TOKEN_SEPARATOR;
         };

         var pageNumber = 1;
         var requestParam = {};
         var refreshFilter = function(){
         var brandNames = "";
         $('#shopFilterPanel input[name=brandName]:checked').each(function(){
         brandNames = brandNames+TOKEN_SEPARATOR+$(this).val();
         processSeparator();
         });
         var sizes = "";
         TOKEN_SEPARATOR = "";
         $('#shopFilterPanel input[name=size]:checked').each(function(){
         sizes = sizes+TOKEN_SEPARATOR+$(this).val();
         processSeparator();
         });
         var discounts = "";
         TOKEN_SEPARATOR = "";
         $('#shopFilterPanel input[name=discount]:checked').each(function(){
         discounts = discounts+TOKEN_SEPARATOR+$(this).val();
         processSeparator();
         });
         requestParam["brand"] = brandNames;
         requestParam["sizes"] = sizes;
         requestParam["discount"] = discounts;
         };


         $('#shopFilterPanel input[type=checkbox]').on("change",function(){
         refreshFilter();
         pageNumber =1;
         $("ul.products").html("");
         updateItemPane();
         });

         var inProgress = false;
         var oTop = $('ul.products').offset().top+ $('ul.products').outerHeight(true)- $('ul.products li:last').outerHeight(true);
         $(window).scroll(function(){
         var pTop = $(window).scrollTop();
         console.log( pTop + ' - ' + oTop );
         if( pTop > oTop){
         oTop = $(window).scrollTop();
         if(pageNumber<=5 && !inProgress){
         inProgress =true;
         updateItemPane();
         }
         }
         });

         var updateItemPane = function(){
         var firstCiId = 1;
         requestParam["page"] = pageNumber;
         $.get('/hangover/comm/shop.html',requestParam,function(response){
         var items = response.items;
         for(var i=0; i<items.length; i++){
         var item  = items[i];
         firstCiId = item.id;
         var itemLI = $(document.createElement("li")).addClass("post-"+item.id+" product instock")
         .append(itemDetail(item.id, item.name, item.detail.size, item.detail.price)).append(itemButtonList(item.id, item.detail.id));
         itemLI.find('.add_to_cart_button, .cherry-quick-view.btn, .yith-wcwl-add-to-wishlist a, .compare, li > .btn').wrapInner('<span class="tooltip-inner" />');
         $("ul.products").append(itemLI);
         }
         oTop = $('ul.products').offset().top+ $('ul.products').outerHeight(true)- $('ul.products li:last').outerHeight(true);
         $('html, body').animate({
         'scrollTop' : $(".post-"+firstCiId).position().top
         });
         pageNumber=pageNumber+1;
         inProgress = false;
         });
         };

         $(".moreItems").click(function(e){
         updateItemPane();
         });

         var wishList = function(itemId){
         return $(document.createElement("div")).addClass("yith-wcwl-add-to-wishlist")
         .append($(document.createElement("div")).addClass("yith-wcwl-add-button show")
         .append($(document.createElement("a")).attr("href","#").attr("data-product-id", itemId).addClass("add_to_wishlist").append("Add To Wishlist"))
         .append($(document.createElement("img")).attr("src", "/hangover/images/banner/wpspin-light.gif").addClass("ajax-loading hide").attr("id", "add-items-ajax-loading-"+ itemId)
         .attr("alt","").attr("width", "16").attr("height", "16")))
         .append($(document.createElement("div")).addClass("yith-wcwl-wishlistaddedbrowse hide").append($(document.createElement("span")).addClass("feedback").append("Product added!"))
         .append($(document.createElement("a")).attr("href", "#").append("Browse  Wishlist")))
         .append($(document.createElement("div")).addClass("yith-wcwl-wishlistexistsbrowse hide").append($(document.createElement("span")).addClass("feedback").append("The product is already in the wishlist!"))
         .append($(document.createElement("a")).attr("href", "#").append("Browse Wishlist")))
         .append($(document.createElement("div")).addClass("clear"));
         };
         var addToCart = function(itemId, ItemDetailId){
         return  $(document.createElement("a")).addClass("button add_to_cart_button product_type_simple")
         .attr("href","#").attr("data-item_id", itemId).attr("data-quantity", 1).attr("data-item_detail_id", ItemDetailId).append("Add to cart");
         };

         var itemDetail = function(itemId, itemName, itemSize, price){
         var itemA =  '<a href="'+itemId+'">'+
         '<div class="cherry-thumb-wrap">'+
         '<span class="onsale">Sale!</span>'+
         ' <img width="300" height="300" src="http://livedemo00.template-help.com/woocommerce_53953/wp-content/uploads/2013/09/Antica-Formula-Carpano-Vermouth-1L-4-300x300.png" class="attachment-shop_catalog wp-post-image" alt="'+itemName+'"/>'+
         '<span class="btn cherry-quick-view" data-product="'+itemId+'">Quick view</span> '+
         '</div> '+
         '<h3>'+itemName+'</h3> '+
         '<div style="display: block;width: 80%;">'+
         '<div class="" style="display: block;width:30%;float: left;"> '+
         '<span class="size">'+itemSize+'</span> '+
         '</div>'+
         '<div class="" style="display: block;width: 55%; float: left;">'+
         '<span class="standard-price">'+price+'</span>'+
         '<span class="standard-price">'+price+'</span> '+
         '</div>'+
         '<div class="" style="display: block;width: 15%; float: left;">'+
         '<span class="offer">(50%)</span>'+
         '</div>'+
         ' </div>'+

         '</a>';
         return itemA;
         };

         var itemButtonList = function(itemId,itemDetailId){
         return $(document.createElement("div")).addClass("buttonsBlock").append(addToCart(itemId, itemDetailId)).append(wishList(itemId));
         }; */

    });
</script>