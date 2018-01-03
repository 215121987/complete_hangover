<%@ include file="/view/common/taglib.jsp" %>
<c:choose>
    <c:when test="${not empty items  && fn:length(items) gt 0}">
        <c:forEach items="${items}" var="item">
            <li class="product">
                <div class="item_wrapper">
                    <a href='<c:url value="/comm/shop/${item.id}.html" />'>
                        <div style="float: left;">
                            <img src="${item.imageURL[0]}" height="60px" width="60px" alt=""/>
                        </div>
                    </a>
                    <div class="item_detail">
                        <form action="<c:url value="/comm/cart.html" />" method="post" class="cart_form">
                            <input type="hidden" name="itemId" value="${item.id}"/>
                            <div class="item_name">
                                    ${item.name}
                            </div>
                            <div class="item_size">
                                <div class="custom-select-box" style="width: 100px;">
                                    <select name="itemDetailId">
                                        <c:forEach items="${item.itemDetailList}" var="detail">
                                            <option value="${detail.id}">${detail.itemSize}Ml&nbsp;&nbsp;${detail.sellingPrice}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="item_qty">
                                <input type="number" name="quantity" value="1" step="1" min="1" max="${detail.quantity}"/>
                            </div>
                            <div class="add_to_cart">
                                <a href='<c:url value="/comm/cart.html" />' class="btn btn-primary add_to_cart_button product_type_simple"
                                   style="padding: 5px">Add To Cart</a>
                            </div>
                        </form>
                    </div>
                </div>
            </li>
        </c:forEach>

    </c:when>
    <c:otherwise>
        <div class="text-uppercase text-center no-more-item"> Sorry!! No Item Found...</div>
    </c:otherwise>
</c:choose>