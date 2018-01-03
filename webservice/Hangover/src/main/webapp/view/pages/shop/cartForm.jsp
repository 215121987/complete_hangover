<%@ include file="/view/common/taglib.jsp" %>
<form action="<c:url value='/comm/cart.html?action=edit-cart-item' />" method="post" id="change-cart-item-detail">
    <input type="hidden" name="id" value="${cartItem.id}"/>
    <input type="hidden" name="itemId" value="${cartItem.itemId}"/>
    <div class="col-md-10">
        <div class="" style="margin-right: 20px; float: left;">
            <c:forEach items="${item.itemDetailList}" var="detail">
                <c:if test="${detail.id eq cartItem.itemDetailId}">
                    <input type="number" min="1" max="${detail.quantity}" step="1" name="quantity" maxlength="2" value="${cartItem.quantity}"/>
                </c:if>
            </c:forEach>
        </div>
        <div class="custom-select-box" style="margin-right: 20px;width: 40%;float:left; ">
            <select id="itemDetailId"  name="itemDetailId">
                <c:forEach items="${item.itemDetailList}" var="detail">
                    <c:choose>
                        <c:when  test="${detail.id eq cartItem.itemDetailId}">
                            <option value="${detail.id}" quantity="${detail.quantity}" selected="selected">${detail.itemSize}&nbsp;&nbsp;${detail.sellingPrice}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${detail.id}" quantity="${detail.quantity}">${detail.itemSize}&nbsp;&nbsp;${detail.sellingPrice}</option>
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
</form>