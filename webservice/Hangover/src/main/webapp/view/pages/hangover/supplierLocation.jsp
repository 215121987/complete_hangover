<%@ include file="/view/common/taglib.jsp" %>
<div id="content" style="width: 75%; margin: 0 auto;">
    <div class="woocommerce">
        <h2 class="text-center">Supplier Location</h2>
        <div class="signup_message"></div>
        <div class="col-md-6">
            <form method="post" class="login" id="register_form" action='<c:url value="/admin/supplier/location/save.html" />'>
                <p class="form-row form-row-wide">
                    <label for="zipCode">Zip Code <span class="required">*</span></label>
                    <input class="input-text" type="tel" maxlength="6" name="zipCode" id="zipCode" required/>
                </p>
                <p class="form-row form-row-wide">
                    <label for="address">Address <span class="required">*</span></label>
                    <input class="input-text" type="text" name="address" id="address" required/>
                </p>
                <p class="form-row form-row-wide">
                    <label for="street">Street <span class="required">*</span></label>
                    <input class="input-text" type="text" name="street" id="street" required/>
                </p>
                <p class="form-row form-row-wide">
                    <label for="city">City <span class="required">*</span></label>
                    <input class="input-text" type="text" name="city" id="city" required/>
                </p>
                <p class="form-row form-row-wide">
                    <label for="state">State <span class="required">*</span></label>
                    <input class="input-text" type="text" name="state" id="state" required/>
                </p>
                <p class="form-row form-row-wide">
                    <label for="country">Country <span class="required">*</span></label>
                    <input class="input-text" type="text" name="country" id="country" required/>
                </p>
                <p class="form-row">
                    <input type="submit" class="button" name="Save" value="Save"/>
                </p>
            </form>
        </div>
    </div>
    <div class="clear"></div>
</div>
