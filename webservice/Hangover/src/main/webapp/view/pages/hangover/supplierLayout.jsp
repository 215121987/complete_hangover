<%@ include file="/view/common/taglib.jsp" %>
<div class="motopress-wrapper content-holder clearfix">
    <article class="main-banner clearfix">
        <div class="page-title text-uppercase">
            <span class="profile-logo"></span>
            Suppliers
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
                            <input type="text" name="filterQuery" data_url="supplier" searchOnUI="false" searchFor="companyName"
                                   view="hangover/supplierList" placeholder="Search Supplier" class="search-form_it"/>
                            <i class="fa fa-search fa-2x"></i>
                        </div>
                    </div>
                    <div class="action_button">
                        <a href="" class="add-parallax-item" data_url="add_supplier" view="hangover/supplierForm" title="Add Supplier">
                            <i class="fa fa-plus fa-3x"></i>
                        </a>
                    </div>
                </div>
                <div id="entity_content">
                    <%@ include file="/view/pages/hangover/supplierList.jsp" %>
                    <%--<c:forEach items="${entities}" var="supplier">
                        <div class="col-md-4 item-block">
                            <div class="parallax-item sans-shadow text-center">
                                <i class="fa fa-align-left fa-3x"></i>
                                <h4 class="searchable">${supplier.companyName}</h4>
                                <p>${supplier.description}. Hangover is a online alcoholic Shop. Hangover is a online alcoholic Shop.Hangover is a online alcoholic Shop.Hangover is a online alcoholic Shop.</p>
                                <div class="item-info">
                                    <h4>${supplier.companyName}<span>(${supplier.code})</span></h4>
                                    <p>${supplier.contactPerson}:-<i class="fa fa-mobile-phone fa-1x"></i>${supplier.contactNumber}</p>
                                    <ul class="item-meta">
                                        <li><a href="" data_id="${supplier.id}"><i class="fa fa-google-plus fa-2x"></i></a></li>
                                        <li><a href="" data_id="${supplier.id}"><i class="fa fa-google-plus fa-2x"></i></a></li>
                                        <li><a href="" data_id="${supplier.id}" title="New Location"><i class="fa fa-plus-sign fa-2x"></i></a></li>
                                        <li><a href="" class="delete-parallax-item" data_url="delete_supplier" data_id="${supplier.id}" title="Remove Supplier"><i class="fa fa-remove-sign fa-2x"></i></a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </c:forEach>--%>
                </div>
                <div class="clear"></div>
            </div>
        </div>
    </div>
</div>
