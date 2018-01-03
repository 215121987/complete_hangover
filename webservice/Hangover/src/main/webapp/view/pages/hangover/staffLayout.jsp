<%@ include file="/view/common/taglib.jsp" %>
<div class="motopress-wrapper content-holder clearfix">
    <article class="main-banner clearfix">
        <div class="page-title text-uppercase">
            <span class="profile-logo"></span>
            Staffs
        </div>
        <figure class="bnr-R-second-img">
            <img class="loaded" alt="Dashboard" data-src-1280="https://static1.jabong.com/live/images/dashboard_banner.jpg" data-src-1024="https://static1.jabong.com/live/images/dashboard_banner.jpg" data-src-768="https://static1.jabong.com/live/images/dashboard_banner.jpg" data-src-500="https://static1.jabong.com/live/images/dashboard-480x200.jpg" data-src-320="https://static1.jabong.com/live/images/dashboard-480x200.jpg" src="https://static1.jabong.com/live/images/dashboard_banner.jpg">
            <span class="black-overlay"></span>
        </figure>
    </article>
    <div class="container minus_container" >
        <div class="row">
            <div class="span12" id="content">
                <div class="action_block col-xs-12">
                    <div class="search-wrapper">
                        <div class="search-form search-form__h clearfix" id="search-header">
                            <input type="text" name="filterQuery" data_url="supplier_staff" searchOnUI="true" searchFor="name-email-mobile"
                                   view="hangover/staffList" placeholder="Search Staff" class="search-form_it"/>
                            <i class="fa fa-search fa-2x"></i>
                        </div>
                    </div>
                    <div class="action_button">
                        <a href="" class="add-parallax-item" data_url="add_staff" req_data="supplierId:1" view="hangover/supplierStaffForm" title="Add Staff">
                            <i class="fa fa-plus fa-3x"></i>
                        </a>
                    </div>
                </div>
                <div id="entity_content">
                    <%@ include file="/view/pages/hangover/staffList.jsp" %>
                </div>
                <div class="clear"></div>
            </div>
        </div>
    </div>
</div>
