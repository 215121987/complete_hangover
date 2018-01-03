<%@ include file="/view/common/taglib.jsp" %>
<div class="motopress-wrapper content-holder clearfix">
    <div class="container">
        <div class="row cart">
            <div class="span12" id="content">
                <div class="empty-cart text-center">
                    <c:choose>
                        <c:when test="${not empty ageVerified && ageVerified eq false}">
                            <span class="empty-msg">
                                Sorry for inconvenience! Your age verification is not yet done!
                                <BR>
                                You can place an order only when age verification complete.
                            </span>
                            <hr>
                        </c:when>
                        <c:otherwise>
                            <span class="empty-icon common-sprite"></span><span
                                class="text-uppercase empty-msg">your shopping bag is empty</span>
                            <hr>
                            <p class="add-item-msg">You can add items from your saved products by clicking on Add to cart
                                button</p>

                            <p><span class="or">OR</span><span class="get-started"> Get started with options below</span></p>
                            <ul>
                                <li><a href="#">Whiskey</a></li>
                                <li><a href="#">Wine</a></li>
                                <li><a href="#">Vodka</a></li>
                                <li class="border-none"><a href="#">Beer</a></li>
                            </ul>
                        </c:otherwise>
                    </c:choose>

                </div>
            </div>
        </div>
    </div>
</div>