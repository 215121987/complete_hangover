<%@ include file="/view/common/taglib.jsp" %>
<section>
    <div class="tab_title text-uppercase">Personal Info</div>
    <div class="entry-content">
        <div class="col-md-8">
            <div class="col-md-2">
                <label class="">Name</label>
            </div>
            <div class="col-md-4">
                <label class=""> ${user.name}</label>
            </div>
        </div>
        <div class="col-md-8">
            <div class=" col-md-2">
                <label class="">Email</label>
            </div>
            <div class="col-md-4">
                <label class=""> ${user.email}</label>
            </div>
        </div>
        <%--<div class="col-md-8">
            <div class=" col-md-2">
                <label class="">Password</label>
            </div>
            <div class="col-md-4">
                <label class=""> ${user.password}</label>
            </div>
        </div>--%>
        <div class="col-md-8">
            <div class=" col-md-2">
                <label class="">Mobile</label>
            </div>
            <div class="col-md-4">
                <label class="">${user.mobile}</label>
            </div>
        </div>
        <div class="col-md-8">
            <div class=" col-md-2">
                <label class="">Date of Birth</label>
            </div>
            <div class="col-md-4">
                <label class="">${user.dob}</label>
            </div>
        </div>
    </div>
</section>
