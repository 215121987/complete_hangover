<%@ include file="/view/common/taglib.jsp" %>
<div class="signin_action col-md-11" >
<security:authorize access="!isAuthenticated()">

        <div class="col-md-6">
            <form method="post" class="login" id="login_form" action='<c:url value='/j_spring_security_check' />'>
                <p class="form-row form-row-wide">
                    <label for="j_username">Email Or Mobile Number <span class="required">*</span></label>
                    <input class="input-text" type="text" name="j_username" id="j_username" required/>
                </p>
                <p class="form-row form-row-wide">
                    <label for="j_password">Password <span class="required">*</span></label>
                    <input class="input-text" type="password" name="j_password" id="j_password" required/>
                </p>
                <p class="form-row form-row-wide text-right">
                    <a href="<c:url value="/forgot/password/form.html"/>">Lost your password?</a>
                </p>
                <p class="form-row">
                    <input type="hidden" name="http_referer" value="${referer}" />
                    <input type="submit" class="button" name="login" value="Login" />
                </p>

            </form>
        </div>
                            <span class="col-separator">
                                  OR
                            </span>
        <div class="col-sm-4 signup_other">
            <a data-gaq-event="Header~$~Sign_in~$~login_google"  class="signup-g"  href="/customer/account/googlesignin/callback/">
                <small></small> <span>sign in with google</span></a>
            <a data-gaq-event="Header~$~Sign_in~$~login_facebook"
               class="signup-fb" href="/customer/account/fbsignin/">
                <small></small>
                <span>sign in with facebook</span></a>
        </div>

</security:authorize>
<security:authorize access="isAuthenticated()">
       <p>You are already logged in as <security:authentication property="principal.username"/>, please <a href='<c:url value="/j_spring_security_logout" />'> Click here</a> to logout</p>
</security:authorize>
</div>
