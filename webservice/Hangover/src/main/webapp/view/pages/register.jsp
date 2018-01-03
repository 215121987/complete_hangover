<%@ include file="/view/common/taglib.jsp" %>
<style type="text/css">
    input[type="file"]{
        padding: 0!important;
    }
</style>
<div id="content" style="width: 75%; margin: 0 auto;">
    <div class="woocommerce">
        <div class="server_message"></div>
        <div class="signup_action col-md-11">
            <h3 class="text-center">SIGN UP</h3>
            <div class="col-md-6">
                <form method="post" class="user" id="register_form" action='<c:url value="/register.html" />' enctype="multipart/form-data">
                    <p class="form-row form-row-wide">
                        <label for="name">Name <span class="required">*</span></label>
                        <input class="input-text" type="text" name="name" id="name" required/>
                    </p>
                    <p class="form-row form-row-wide">
                        <label for="mobile">Mobile Number <span class="required">*</span></label>
                        <input class="input-text" type="tel" name="mobile" maxlength="10" id="mobile" required/>
                        <label for="mobile" class="label_info">Will send password to this number.</label>
                    </p>
                    <p class="form-row form-row-wide">
                        <label for="email">Email</label>
                        <input class="input-text" type="email" name="email" id="email"/>
                    </p>
                    <p class="form-row form-row-wide">
                        <label for="dob">Date of Birth <span class="required">*</span></label><%-- pattern="(0[1-9]|1[0-9]|2[0-9]|3[01]).(0[1-9]|1[012]).[0-9]{4}"--%>
                        <input type="text" class="input-text" name="dob" id="dob" required="true"/>
                        <label for="dob" class="label_info">Age must be greater than 18.</label>
                    </p>
                    <p class="form-row form-row-wide">
                        <label for="ageProof">Age Proof Id <span class="required">*</span></label>
                        <input type="file" class="input-text" name="ageProof" accept=".png,.jpg,.jpeg" id="ageProof" required="true"/>
                        <label for="ageProof" class="label_info">Only .png,.jpg and .jpeg file supports. </label>
                        <label for="ageProof" class="label_info">Note: After successful registration, it will take 24 hour to very your age.
                            <BR> You can place an order only when your age verification done.
                        </label>
                    </p>
                    <p class="form-row form-row-wide">
                        <input  type="checkbox" name="TCConfirm" id="TCConfirm" required/>
                        <label for="TCConfirm">Agree to T&C</label>
                    </p>
                    <p class="form-row">
                        <input type="submit" class="button" name="signUp" value="Sign Up"/>
                    </p>
                </form>
            </div>
                            <span class="col-separator">
                                  OR
                            </span>
            <div class="col-sm-4 signup_other">
                <a  class="signup-g"  href="#">
                    <small></small>
                    <span>sign up with google</span></a>
                <a  class="signup-fb" href="#">
                    <small></small>
                    <span>sign up with facebook</span></a>
            </div>
        </div>
    </div>
    <div class="clear"></div>
</div>
