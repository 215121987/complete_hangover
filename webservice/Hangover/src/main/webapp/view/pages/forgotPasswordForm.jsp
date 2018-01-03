<%@ include file="../common/taglib.jsp" %>
<style type="text/css">
    #message{
        text-align: center;
    }
</style>
<div id="content" style="width: 75%; margin: 0 auto;">
    <div class="woocommerce">
        <h3 class="text-center"><fmt:message key="label.Reset.Password"/></h3>
        <div class="signin_message"></div>
        <div class="col-md-6">
            <form action="<c:url value='/forgot/password.html' />" method="post">
                <p class="form-row form-row-wide">
                    <label for="username">Email or Mobile Number <span class="required">*</span></label>
                    <input class="input-text" type="text" name="username" id="username" required/>
                </p>
                <p class="form-row">
                    <input type="submit" class="button" value="<fmt:message key="label.Send"/>" title="<fmt:message key="label.Send"/>">
                    <a href="<c:url value="/login.html"/>" class="button" title="<fmt:message key="label.Cancel"/>"><fmt:message key="label.Cancel"/></a>
                </p>
            </form>
        </div>
    </div>
    <div class="clear"></div>
</div>




<%--<div class="center_form">
    <div class="form_grid">
        <form action="<c:url value='/forgot/password.html' />" method="post">
            <div class="grid_row">
                <div class="grid_column">
                    <h2><fmt:message key="label.Reset.Password"/> </h2>
                </div>
            </div>
            <div class="grid_row">
                <div class="grid_column">
                    <input type="email" name="email" placeholder="Email" required/>
                </div>
            </div>
            <div class="grid_row">
                <input type="submit" class="button" value="<fmt:message key="label.Send"/>" title="<fmt:message key="label.Send"/>">
                <a href="<c:url value="/login.html"/>" class="button" title="<fmt:message key="label.Cancel"/>"><fmt:message key="label.Cancel"/></a>
            </div>
        </form>
    </div>
</div>--%>
