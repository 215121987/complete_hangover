<%@ include file="/view/common/taglib.jsp" %>
<div id="content" style="width: 90%; margin: 0 auto;">
    <div class="woocommerce">
        <c:choose>
            <c:when test="${not empty id}">
                <h3 class="text-center">Edit Staff</h3>
            </c:when>
            <c:otherwise>
                <h3 class="text-center">${entity.zipCode} New Staff</h3>
            </c:otherwise>
        </c:choose>
        <div class="col-md-12">
            <form:form action="${pageContext.request.contextPath}/save/SupplierStaffDTO.html?view=hangover/storeLayout"
                       cssClass="genericMethod" commandName="entity" >
                <form:hidden path="storeId" id="storeId" class="input-text"  required="true"/>
                <form:hidden path="supplierId" id="supplierId" class="input-text"  required="true"/>
                <p class="form-row form-row-wide">
                    <label for="name">Name <span class="required">*</span></label>
                    <form:input path="name" id="name" class="input-text"  required="true"/>
                </p>
                <p class="form-row form-row-wide">
                    <label for="email">Email <span class="required">*</span></label>
                    <form:input path="email" id="email" class="input-text"  required="true"/>
                </p>
                <p class="form-row form-row-wide">
                    <label for="mobile">Mobile <span class="required">*</span></label>
                    <form:input path="mobile" id="mobile" class="input-text"  required="true" type="tel" maxlength="12"/>
                </p>
                <c:if test="${empty id}">
                    <p class="form-row form-row-wide">
                        <label for="password">Password <span class="required">*</span></label>
                        <form:password path="password" id="password" class="input-text"  required="true" maxlength="24"/>
                    </p>
                    <p class="form-row form-row-wide">
                        <label for="confirmPassword">Confirm Password <span class="required">*</span></label>
                        <form:password path="confirmPassword" id="confirmPassword" class="input-text"  required="true" maxlength="24"/>
                    </p>
                </c:if>
                <p class="form-row form-row-wide">
                    <label for="startDate">Start Date <span class="required">*</span></label>
                    <form:input path="startDate" id="startDate" class="input-text"  required="true"/>
                </p>
                <p class="form-row">
                    <input type="submit" class="button" name="save" value="Save"/>
                    <input type="button" class="button" name="cancel" value="Cancel"/>
                </p>
            </form:form>
        </div>
    </div>
    <div class="clear"></div>
</div>
