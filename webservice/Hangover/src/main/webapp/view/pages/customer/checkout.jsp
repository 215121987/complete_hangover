<%@ include file="/view/common/taglib.jsp" %>
<meta content="true" name="askForLocation"/>
<div class="motopress-wrapper content-holder clearfix">
    <div class="container">
        <div class="row cart">
            <div class="span12" id="content">
                <section class="col-md-8 cart-left-cotent clearfix">
                    <div class="heading-wrapper col-md-12">
                         <span class="col-md-3 col-sm-3 col-xs-12 col-xxs-12 shop-more-link hidden-xs text-uppercase">
                             <a data-gaq-event="Continue_shopping" href='<c:url value="/comm/cart.html" />'>View Bag</a>
                         </span>
                        <div class="col-md-5 col-sm-8 col-xs-12 col-xxs-12 cart-heading text-center">SECURED CHECKOUT</div>
                    </div>
                    <div class="clear"></div>
                    <article class="">
                        <div class="woocommerce">
                            <section id="section-1" class="auth_section" order-number="1" <security:authorize
                                    access="isAuthenticated()">status="complete"</security:authorize>>
                                <div id="checkout_info-1" class="woocommerce-info clickAble checkout-info">Email Address
                                    <security:authorize access="isAuthenticated()">
                                        <span><security:authentication property="principal.username"/></span>
                                        <%--<span class="woocommerce-info-action">
                                             <a href='' class="btn btn-primary">Change</a>
                                        </span>--%>
                                    </security:authorize>
                                </div>
                                <div id="checkout-info-content-1"
                                     class='checkout-info-content <security:authorize access="isAuthenticated()"> hide</security:authorize>'>
                                    <%@ include file="/view/pages/loginForm.jsp" %>
                                </div>
                            </section>
                            <div class="clear"></div>
                            <section id="section-2" class="address_section" order-number="2"
                                     <c:if test="${not empty deliveryAddressId}">status="complete"</c:if>>
                                <div id="checkout_info-2" class="woocommerce-info clickAble checkout-info">Address</div>
                                <div id="checkout-info-content-2" class='checkout-info-content hide'>
                                    <security:authorize access="isAuthenticated()">
                                        <%@ include file="/view/pages/customer/deliveryAddress.jsp" %>
                                    </security:authorize>
                                </div>
                            </section>
                            <div class="clear"></div>
                            <section id="section-3" class="review_section " order-number="3" status="incomplete">
                                <div id="checkout_info-3" class="woocommerce-info clickAble checkout-info">Order review</div>
                                <div id="checkout-info-content-3" class='checkout-info-content hide'>
                                    <%@ include file="/view/pages/customer/reviewOrder.jsp" %>
                                </div>
                            </section>
                            <div class="clear"></div>
                            <section id="section-4" class="payment_section" order-number="4">
                                <div id="checkout_info-4" class="woocommerce-info checkout-info noDrop">Payment</div>
                                <div id="checkout-info-content-4" class='checkout-info-content hide'>
                                    <%@ include file="/view/pages/customer/payment.jsp" %>
                                </div>
                            </section>
                            <div class="clear"></div>
                        </div>
                        <div class="clear"></div>
                    </article>
                </section>
                <%@ include file="/view/pages/customer/cartSummary.jsp" %>
                <%--<section class=" col-md-3 cart-summery-wrapper">
                    &lt;%&ndash;<div class="bg-summary"></div>&ndash;%&gt;
                    <div class="cart-summery clearfix">
                        <div class="col-md-12 text-center">
                            <div data-gaq-event="Cart~$~Apply_coupon~$~Initiate" data-target="#apply-voucher-modal"
                                 data-url="/cart/coupon" data-mobile-url="/cart/coupon" data-custom-class="apply-coupon"
                                 class="btn btn-primary text-uppercase">apply coupons
                            </div>
                            <div class="reedem-payback-isGuest"></div>
                        </div>
                        <div class="col-md-12  odr-summary">
                            <div class="total-amt">TOTAL &nbsp;
                                <div><span  class="standard-price">  ${cartSummary.netAmount}</span></div>
                            </div>
                            <div class="summary-heading mob-accordion text-uppercase">order summary<span
                                    class="accor-icon common-sprite visible-xs-inline-block"></span></div>
                            <div class="summary-content mob-accordion-content"><span class="left">Price</span>
                                <span class="right">
                                    <span class="standard-price">${cartSummary.grossAmount}</span>
                                </span>
                                <c:if test="${cartSummary.discountedAmount gt 0}">
                                    <span class="left">Discounts</span>
                                    <span class="standard-price">${cartSummary.discountedAmount}</span>
                                </c:if>
                                <span class="left">Handling Charges</span>
                                <c:choose>
                                    <c:when test="${cartSummary.deliveryCharge gt 0}">
                                        <span class="standard-price">${cartSummary.deliveryCharge}</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span  class="right green-txt">FREE</span>
                                    </c:otherwise>
                                </c:choose>
                                <span class="left popover-options">Taxes<div
                                    class="content text-uppercase"><p>This is part of VAT/CST, deposited by Hangover to
                                govt. For details
                                refer FAQs.</p></div></span>
                                <span class="right">
                                    <span  class="standard-price">  ${cartSummary.tax}</span>
                                </span>

                                <div class="total">
                                    <span class="left">Total</span><span class="right">
                                    <span  class="standard-price">${cartSummary.netAmount}</span>
                                   </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>--%>
            </div>
        </div>
    </div>
</div>






