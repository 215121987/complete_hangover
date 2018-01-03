<%@ include file="/view/common/taglib.jsp" %>
<c:forEach items="${cart}" var="cartItem">
    <section   class="cart-content  text-uppercase clearfix">
        <div class="cart-main clearfix">
            <div class="cart-img-container">
                <a href='<c:url value="/comm/shop/${cartItem.itemId}.html" />'>
                    <img alt="" class="thumb loaded"  src="${cartItem.imageURL}">
                </a>
            </div>
            <div class="cart-right-main">
                <div class="row cart-inner-main">
                    <div class="col-md-8 col-sm-9 col-xs-12 col-xxs-12 item-desc">
                        <div class="heading">
                            <div class="item-head-main">
                                <a class="item-heading" href='<c:url value="/comm/shop/${cartItem.itemId}.html" />'>${cartItem.name}</a>
                                    <%--<a class="item-brief text-capitalize" href="/Flying_Machine-Black-Printed-Round-Neck-T-Shirt-1909427.html">Black Printed Round Neck T-Shirt</a>--%>
                            </div>
                            <div class="items-stock off-edit-hide">
                                <span>Size <small class="size">${cartItem.size}</small></span>
                                <span class="size-separator">|</span>
                                <span>Qty <small class="qty">${cartItem.quantity}</small></span>
                            </div>
                        </div>
                        <div class="delivery off-edit-hide"><span>Delivery by</span> Mon, 7th Mar</div>
                    </div>
                    <div class="col-md-3 col-sm-3 col-xs-12 col-xxs-12 item-price off-edit-hide">
                        <span  class="price"><span class="standard-price">${cartItem.price*cartItem.quantity}</span></span>
                    </div>

                </div>
            </div>
        </div>
    </section>
</c:forEach>
<section   class="cart-action  text-uppercase clearfix">
    <div class="cart-main clearfix">
        <div class="col-md-10" style="margin: 15px;">
            <div data-gaq-event="Proceed-To-Pay" id="proceedToPay" class="btn btn-primary text-uppercase">Proceed To Pay</div>
            <a data-option-selected="4" style="margin-left: 20px;" class="edit" href='<c:url value="/comm/cart.html" />'>EDIT BAG</a>
        </div>

    </div>
</section>