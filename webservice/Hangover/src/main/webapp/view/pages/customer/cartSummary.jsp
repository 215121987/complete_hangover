<%@ include file="/view/common/taglib.jsp" %>
<section class=" col-md-3 cart-summery-wrapper">
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
                <div><span class="standard-price"> ${cartSummary.netAmount}</span></div>
            </div>
            <c:if test="${empty placeOrder || placeOrder eq false}">
                <a href='<c:url value="/comm/checkout.html"/>' class="btn btn-primary">PLACE ORDER</a>
            </c:if>
            <div class="summary-heading mob-accordion text-uppercase">order summary
                <span class="accor-icon common-sprite visible-xs-inline-block"></span>
            </div>
            <div class="summary-content mob-accordion-content">
                <span class="left">Price</span>
                <span class="right">
                    <span class="standard-price">${cartSummary.grossAmount}</span>
                </span>
                <c:if test="${cartSummary.discountedAmount gt 0}">
                    <span class="left">Discounts</span>
                    <span class="right">
                        <span class="standard-price">${cartSummary.discountedAmount}</span>
                    </span>
                </c:if>
                <span class="left">Handling Charges</span>
                    <span class="right">
                    <c:choose>
                        <c:when test="${cartSummary.deliveryCharge gt 0}">
                            <span class="standard-price">${cartSummary.deliveryCharge}</span>
                        </c:when>
                        <c:otherwise>
                            <span class="right green-txt">FREE</span>
                        </c:otherwise>
                    </c:choose>
                    </span>
                    <span class="left popover-options">Taxes<div
                            class="content text-uppercase"><p>This is part of VAT/CST, deposited by Hangover
                        to
                        govt. For details
                        refer FAQs.</p></div></span>
                    <span class="right">
                        <span class="standard-price"> ${cartSummary.tax}</span>
                    </span>

                <div class="total">
                    <span class="left">Total</span><span class="right">
                        <span class="standard-price">${cartSummary.netAmount}</span>
                    </span>
                </div>
            </div>
        </div>
    </div>
</section>




