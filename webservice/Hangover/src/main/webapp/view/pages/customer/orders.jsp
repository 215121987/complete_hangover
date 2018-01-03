<%@ include file="/view/common/taglib.jsp" %>
<section>
    <div class="tab_title">Orders</div>
    <div class="panel entry-content">
        <p>
        <c:forEach items="${orders}" var="order">
            <section   class="cart-content clearfix">
                <div id="checkout_info-2" class="woocommerce-info clickAble checkout-info  text-uppercase">Order ID: ${order.orderNumber}</div>
                <div id="checkout-info-content-2" class='checkout-info-content'>
                    <c:forEach items="${order.orderItem}" var="orderItem">
                        <section   class="cart-content  text-uppercase clearfix">
                            <div class="cart-main clearfix">
                                <div class="cart-img-container">
                                    <a href='<c:url value="/comm/shop/${orderItem.item.id}.html" />'>
                                        <c:if test="${fn:length(orderItem.item.imageURL) gt 0}">
                                            <img alt="" class="thumb loaded"  src="${orderItem.item.imageURL[0]}">
                                        </c:if>
                                    </a>
                                </div>
                                <div class="cart-right-main">
                                    <div class="row cart-inner-main">
                                        <div class="col-md-7 item-desc">
                                            <div class="heading">
                                                <div class="item-head-main">
                                                    <a class="item-heading" href='<c:url value="/comm/shop/${orderItem.item.id}.html" />'>${orderItem.item.name}</a>
                                                </div>
                                                <div class="items-stock off-edit-hide">
                                                    <span>Size <small class="size">${orderItem.itemSize}</small></span>
                                                    <span class="size-separator">|</span>
                                                    <span>Qty <small class="qty">${orderItem.quantity}</small></span>
                                                </div>
                                            </div>
                                            <div class="delivery off-edit-hide"><span>Delivery by</span> ${orderItem.deliverAt}</div>
                                        </div>
                                        <div class="col-md-2 item-price off-edit-hide">
                                            <span  class="price"><span class="standard-price">${orderItem.price}</span></span>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </section>
                    </c:forEach>
                </div>
            </section>
        </c:forEach>
        </p>
    </div>
</section>