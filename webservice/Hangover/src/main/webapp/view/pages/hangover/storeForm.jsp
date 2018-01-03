<%@ include file="/view/common/taglib.jsp" %>
<div id="content" style="width: 90%; margin: 0 auto;">
    <div class="woocommerce">
        <c:choose>
            <c:when test="${not empty id}">
                <h3 class="text-center">${entity.companyName}'s Edit Store</h3>
            </c:when>
            <c:otherwise>
                <h3 class="text-center">${entity.companyName}'s New Store</h3>
            </c:otherwise>
        </c:choose>
        <div class="col-md-12">
            <form:form action="${pageContext.request.contextPath}/save/SupplierStoreDTO.html?view=hangover/storeLayout" cssClass="genericMethod" commandName="entity" >
                <form:hidden path="id" id="id" class="input-text"  required="true"/>
                <form:hidden path="supplierId" id="supplierId" class="input-text"  required="true"/>
                <p class="form-row form-row-wide">
                    <label for="zipCode">Zip Code <span class="required">*</span></label>
                    <form:input path="zipCode" id="zipCode" class="input-text"  required="true" maxlength="6" type="tel"/>
                </p>
                <p class="form-row form-row-wide">
                    <label for="address">Address <span class="required">*</span></label>
                    <form:input path="address" id="address" class="input-text"  required="true"/>
                </p>
                <p class="form-row form-row-wide">
                    <label for="street">Street <span class="required">*</span></label>
                    <form:input path="street" id="street" class="input-text"  required="true"/>
                </p>
                <p class="form-row form-row-wide">
                    <label for="city">City <span class="required">*</span></label>
                    <form:input path="city" id="city" class="input-text"  required="true"/>
                </p>
                <p class="form-row form-row-wide">
                    <label for="state">State <span class="required">*</span></label>
                    <form:input path="state" id="state" class="input-text"  required="true"/>
                </p>
                <p class="form-row form-row-wide">
                    <label for="country">Country <span class="required">*</span></label>
                    <form:input path="country" id="country" class="input-text"  required="true"/>
                </p>
                <p class="form-row form-row-wide">
                    <label for="country">Contact Person <span class="required">*</span></label>
                    <form:input path="storeContactPerson" id="storeContactPerson" class="input-text"  required="true"/>
                </p>
                <p class="form-row form-row-wide">
                    <label for="country">Contact Number <span class="required">*</span></label>
                    <form:input path="storeContactNumber" id="storeContactNumber" class="input-text"  required="true" type="tel" maxlength="12"/>
                </p>
                <p class="form-row form-row-wide">
                    <label for="country">Is Main Branch <span class="required">*</span></label>
                    <form:checkbox path="mainBranch" id="mainBranch"   required="true"/>
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
