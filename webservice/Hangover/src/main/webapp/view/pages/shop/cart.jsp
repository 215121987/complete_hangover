<%@ include file="/view/common/taglib.jsp" %>
<meta content="true" name="askForLocation"/>
<div class="motopress-wrapper content-holder clearfix">
    <div class="container">
        <div class="row cart">
            <div class="span12" id="content">
                <section class="col-md-8 cart-left-cotent clearfix">
                    <div class="heading-wrapper row text-uppercase">
                         <span class="col-md-3 shop-more-link hidden-xs">
                             <a data-gaq-event="Continue_shopping" href="<c:url value="/comm/shop.html" />">Continue Shopping</a>
                         </span>

                        <div class="col-md-4 cart-heading text-center">YOUR SHOPPING BAG
                            <span class="cart-items">[ ${fn:length(cart)} ] </span>
                        </div>
                         <span class="col-md-3 check-info text-right">
                             <a data-gaq-event="Cart~$~Check_delivery_info~$~Initiate"
                                data-target="#check-delivery-modal"
                                data-url="/cart/check-delivery-Info" data-custom-class="check-delivery"
                                href="javascript:void(0)">
                                 Check delivery info</a>
                         </span>
                    </div>
                    <div class="product-heading row text-uppercase hidden-xs">
                        <span class="col-md-5 col-sm-8">Products</span>
                        <span class="col-md-2 col-sm-5 delivery-txt visible-lg-screen text-right">Delivery by</span>
                        <span class="col-md-3 col-sm-4 text-right">total Amount</span>
                    </div>
                    <article class="row">
                        <c:forEach items="${cart}" var="cartItem">
                            <section data-config-sku="FL055MA72QPNINDFAS" data-sku="FL055MA72QPNINDFAS-6233134"
                                     class="cart-content  text-uppercase clearfix">
                                <div class="cart-main clearfix">
                                    <div class="cart-img-container">
                                        <a href='<c:url value="/comm/shop/${cartItem.itemId}.html" />'>
                                            <img alt="" class="thumb loaded" src="${cartItem.imageURL}">
                                        </a>
                                    </div>
                                    <div class="cart-right-main">
                                        <div class="row cart-inner-main">
                                            <div class="col-md-8 col-sm-9 col-xs-12 col-xxs-12 item-desc">
                                                <div class="heading">
                                                    <div class="item-head-main">
                                                        <a class="item-heading"
                                                           href='<c:url value="/comm/shop/${cartItem.itemId}.html" />'>${cartItem.name}</a>
                                                            <%--<a class="item-brief text-capitalize" href="/Flying_Machine-Black-Printed-Round-Neck-T-Shirt-1909427.html">Black Printed Round Neck T-Shirt</a>--%>
                                                    </div>
                                                    <div class="items-stock off-edit-hide">
                                                        <span>Size <small class="size">${cartItem.size}</small></span>
                                                        <span class="size-separator">|</span>
                                                        <span>Qty <small class="qty">${cartItem.quantity}</small></span>
                                                        <a  class="edit edit-cart-item" req_data="itemId:${cartItem.itemId}-itemDetailId:${cartItem.itemDetailId}"
                                                           data_url="cart_url">EDIT </a>
                                                    </div>
                                                </div>
                                                <div class="delivery off-edit-hide"><span>Delivery by</span> Mon, 7th
                                                    Mar
                                                </div>
                                            </div>
                                            <div class="col-md-3 col-sm-3 col-xs-12 col-xxs-12 item-price off-edit-hide">
                                                <span class="price"><span
                                                        class="standard-price">${cartItem.price* cartItem.quantity}</span></span>
                                            </div>
                                            <div class="cart-edit hide clearfix">
                                                <%--<form action="" method="post" id="change-cart-item-detail">
                                                    <input type="hidden" name="itemId" value="${cartItem.itemId}"/>
                                                    <input type="hidden" name="itemDetailId" value="${cartItem.itemDetailId}"/>
                                                    <div class="col-md-10">
                                                        <div class="" style="margin-right: 20px; float: left;">
                                                            <input type="number" min="1" max="99" step="1" name="quantity" maxlength="2" value="${cartItem.quantity}"/>
                                                        </div>
                                                        <div class="custom-select-box" style="margin-right: 20px;width: 40%;float:left; ">
                                                            <select id="size"  name="itemDetailId">
                                                                <c:forEach begin="300" step="300" end="1200" var="size">
                                                                    <c:choose>
                                                                        <c:when  test="${size eq cartItem.size}">
                                                                            <option value="${size}" selected="selected">${size}</option>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <option value="${size}">${size}</option>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-5">
                                                        <input type="submit" name="submit" value="Save" class="btn"/>
                                                        <input type="button" name="cancel" value="cancel" class="btn"/>
                                                    </div>
                                                </form>--%>
                                            </div>
                                            <div class="row col-md-9 item-btn-wrapper">
                                                <div class="item-modified clearfix">
                                                    <div class="off-edit-hide">
                                                        <div class="item-modified-links">
                                                            <div class="yith-wcwl-add-to-wishlist">
                                                                <div class="yith-wcwl-add-button show">
                                                                    <a href='<c:url value="/comm/shortList/add.html"/>'
                                                                       data-product-id="${cartItem.itemId}"
                                                                       data-product-type="simple"
                                                                       class="add_to_wishlist">Add to Wishlist</a>
                                                                    <img src="http://livedemo00.template-help.com/woocommerce_53953/wp-admin/images/wpspin_light.gif"
                                                                         class="ajax-loading" alt="" width="16"
                                                                         height="16" style="visibility:hidden"/>
                                                                </div>
                                                                <div class="yith-wcwl-wishlistaddedbrowse hide"
                                                                     style="display:none;">
                                                                    <span class="feedback">Product added!</span>
                                                                    <a href='<c:url value="/comm/shortList.html" />'>Browse
                                                                        Wishlist</a>
                                                                </div>
                                                                <div class="yith-wcwl-wishlistexistsbrowse hide"
                                                                     style="display:none">
                                                                    <span class="feedback">The product is already in the wishlist!</span>
                                                                    <a href='<c:url value="/comm/shortList.html"/>'>Browse
                                                                        Wishlist</a>
                                                                </div>
                                                                <div style="clear:both"></div>
                                                            </div>
                                                                <%--<a data-content=".movetoshortlist-warning"  class="save move-to-shortlist" href="javascript:void(0)">
                                                                    <span class="save-later-icon common-sprite"></span>SAVE
                                                                </a>--%>
                                                            <span class="separator">|</span>
                                                            <a  class="remove" href="<c:url value="/comm/cart/delete/${cartItem.itemId}/${cartItem.itemDetailId}.html"/>">
                                                                <span class="delete-icon common-sprite"></span>REMOVE
                                                            </a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                            </section>
                        </c:forEach>
                    </article>
                </section>
                <%@ include file="/view/pages/customer/cartSummary.jsp" %>

               <%-- <section class="col-md-3 cart-summery-wrapper">
                    &lt;%&ndash;<div class="bg-summary"></div>&ndash;%&gt;
                    <div class="cart-summery clearfix">
                        <div class="col-md-12 col-sm-6 col-xs-12 col-xxs-12 text-center">
                            <div data-gaq-event="Cart~$~Apply_coupon~$~Initiate" data-target="#apply-voucher-modal"
                                 data-url="/cart/coupon" data-mobile-url="/cart/coupon" data-custom-class="apply-coupon"
                                 class="btn btn-primary text-uppercase">apply coupons
                            </div>
                            <div class="reedem-payback-isGuest"></div>
                        </div>
                        <div class="col-md-12 col-sm-6 col-xs-12 col-xxs-12 odr-summary">
                            <div class="total-amt">TOTAL &nbsp;
                                <div><span class="rupee">R</span> 6136.20</div>
                            </div>
                            <a href='<c:url value="/comm/checkout.html"/>' class="btn btn-primary"
                               data-gaq-event="Cart~$~Place_order~$~|6">PLACE ORDER</a>

                            <div class="summary-heading mob-accordion text-uppercase">order summary<span
                                    class="accor-icon common-sprite visible-xs-inline-block"></span></div>
                            <div class="summary-content mob-accordion-content"><span class="left">Price</span><span
                                    class="right"><span
                                    class="standard-price">5844.00</span></span><span
                                    class="left">Handling Charges</span><span
                                    class="right green-txt">FREE</span><span class="left popover-options">Taxes<div
                                    class="content text-uppercase"><p>This is part of VAT/CST, deposited by Jabong to
                                govt. For details
                                refer FAQs.</p></div></span><span class="right"><span
                                    class="standard-price">  292.20</span></span>

                                <div class="total"><span class="left">Total</span><span class="right"><small
                                        class="rupee">R
                                </small> 6136.20</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>--%>


            </div>
        </div>
    </div>
</div>


<%--<section data-config-sku="AD004MA90QMRINDFAS" data-sku="AD004MA90QMRINDFAS-5096385"
         class="cart-content  text-uppercase clearfix">
    <div class="cart-main clearfix">
        <div class="cart-img-container"><a href="Adidas-Cricket-Perform-White-Solid-Polo-T-Shirt-1575409.html"><img
                alt=""
                zoom="http://static2.jassets.com/p/Adidas-Cricket-Perform-White-Solid-Polo-T-Shirt-8924-9045751-1-undefined.jpg"
                data-src-1280="http://static2.jassets.com/p/Adidas-Cricket-Perform-White-Solid-Polo-T-Shirt-8924-9045751-1-new_cart.jpg"
                data-src-1024="http://static2.jassets.com/p/Adidas-Cricket-Perform-White-Solid-Polo-T-Shirt-8924-9045751-1-new_cart.jpg"
                data-src-768="http://static2.jassets.com/p/Adidas-Cricket-Perform-White-Solid-Polo-T-Shirt-8924-9045751-1-new_cart.jpg"
                data-src-500="http://static2.jassets.com/p/Adidas-Cricket-Perform-White-Solid-Polo-T-Shirt-8924-9045751-1-new_cart.jpg"
                data-src-320="http://static2.jassets.com/p/Adidas-Cricket-Perform-White-Solid-Polo-T-Shirt-8924-9045751-1-new_cart.jpg"
                class="thumb loaded"
                src="http://static2.jassets.com/p/Adidas-Cricket-Perform-White-Solid-Polo-T-Shirt-8924-9045751-1-new_cart.jpg"></a>
        </div>
        <div class="cart-right-main">
            <div class="row cart-inner-main">
                <div class="col-md-8 col-sm-9 col-xs-12 col-xxs-12 item-desc">
                    <div class="heading">
                        <div class="item-head-main"><a class="item-heading"
                                                       href="/Adidas-Cricket-Perform-White-Solid-Polo-T-Shirt-1575409.html">Adidas </a><span
                                class="popover-options"><span
                                data-content="&lt;p&gt;	Item available for gift wrap &lt;/p&gt;"
                                data-placement="right" data-trigger="hover" data-container="body" title=""
                                class="gift-icon-small common-sprite btn-popover m-remove-popover"
                                data-original-title=""></span><div class="content text-uppercase"><p> Item available for
                            gift wrap </p></div></span><a class="item-brief text-capitalize"
                                                          href="/Adidas-Cricket-Perform-White-Solid-Polo-T-Shirt-1575409.html">Cricket
                            Perform White Solid Polo T-Shirt</a></div>
                        <div class="items-stock off-edit-hide"><span>Size <small class="size">M</small></span><span
                                class="size-separator">|</span><span>Qty <small class="qty">1</small></span><a
                                data-option-selected="6" class="edit"
                                data-gaq-event="Cart~$~quantity_edit~$~AD004MA90QMRINDFAS-5096385|edit|1"
                                href="javascript:void(0)">EDIT</a></div>
                    </div>
                    <div class="delivery off-edit-hide"><span>Delivery by</span> Fri, 4th Mar</div>
                </div>
                <div class="col-md-4 col-sm-3 col-xs-12 col-xxs-12 item-price off-edit-hide"><span
                        class="strike-through"><span class="standard-price">1499</span></span> <span class="price"><span
                        class="standard-price">974</span></span><span class="discount">(-35%)</span></div>
                <div class="row col-md-12 col-sm-12 col-xs-12 col-xxs-12 item-btn-wrapper">
                    <div class="item-modified clearfix">
                        <div class="off-edit-hide">
                            <div class="item-modified-links"><a data-content=".movetoshortlist-warning"
                                                                data-target="#showmore-modal"
                                                                data-custom-class="movetoshortlist-warning"
                                                                data-gaq-event="Cart~$~Move_to_wishlist~$~AD004MA90QMRINDFAS-5096385"
                                                                class="save move-to-shortlist"
                                                                href="javascript:void(0)"><span
                                    class="save-later-icon common-sprite"></span>SAVE</a><span
                                    class="separator">|</span><a
                                    data-gaq-event="Cart~$~Remove_SKU~$~AD004MA90QMRINDFAS-5096385" class="remove"
                                    href="javascript:void(0)"><span class="delete-icon common-sprite"></span>REMOVE</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="cart-edit on-edit-hide clearfix">
                    <div class="col-md-3 col-sm-3 col-xs-12 col-xxs-12">
                        <div class="select-box jabong-dropdown select-quantity">
                            <div class="custom-select-boxs">SELECT QUANTITY<span class="change-value">1</span></div>
                            <select id="" data-valid="" name="" class="form-control " data-rule=""></select>

                            <p class="error"></p></div>
                    </div>
                    <div class="col-md-9 col-sm-9 col-xs-12 col-xxs-12 edit-btn">
                        <button data-gaq-event="Cart~$~quantity_edit~$~AD004MA90QMRINDFAS-5096385|save|1"
                                class="btn btn-primary save-btn" type="button">SAVE
                        </button>
                        <button data-gaq-event="Cart~$~quantity_edit~$~AD004MA90QMRINDFAS-5096385|cancel|1"
                                data-oldvalue="1" class="btn btn-filled cancel-btn" type="button">CANCEL
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>--%>




<%--
<%@ include file="/view/common/taglib.jsp" %>
<div class="widget_shopping_cart_content" style="display: block;">
    <c:set var="total" scope="session" value="0"/>
    <ul class="cart_list product_list_widget ">
        <c:forEach items="${cartItems}" var="cartItem">
            <li>
                <a href='<c:url value="/comm/shop/${cartItem.item.id}.html" />'>
                    <img height="90" width="90" alt="${cartItem.item.name}" class="attachment-shop_thumbnail wp-post-image" src="${cartItem.item.description}">
                    ${cartItem.item.name}
                </a>
                <span class="quantity">${cartItem.quantity} × <span class="amount">Rs ${cartItem.itemDetail.sellingPrice}</span></span>
                <c:set var="total" scope="session" value="${total + cartItem.quantity*cartItem.itemDetail.sellingPrice}"/>
            </li>
        </c:forEach>
    </ul>
    <p class="total"><strong>Subtotal:</strong> <span class="amount">Rs. ${total}</span></p> ⁄€‹›ﬁﬂ‡°·‚—
    <p class="buttons">
        <a class="button wc-forward" href="http://livedemo00.template-help.com/woocommerce_53953/cart/">View Cart</a>
        <a class="button checkout wc-forward" href="http://livedemo00.template-help.com/woocommerce_53953/checkout/">Checkout</a>
    </p>
</div>--%>
