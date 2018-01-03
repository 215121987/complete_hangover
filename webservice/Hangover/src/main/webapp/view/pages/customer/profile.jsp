<%@ include file="/view/common/taglib.jsp" %>
<div class="motopress-wrapper content-holder clearfix woocommerce">
    <article class="main-banner clearfix">
        <div class="page-title text-uppercase">
            <span class="profile-logo"></span>
            Profile
        </div>
        <figure class="bnr-R-second-img">
            <img class="loaded" alt="Dashboard" data-src-1280="https://static1.jabong.com/live/images/dashboard_banner.jpg" data-src-1024="https://static1.jabong.com/live/images/dashboard_banner.jpg" data-src-768="https://static1.jabong.com/live/images/dashboard_banner.jpg" data-src-500="https://static1.jabong.com/live/images/dashboard-480x200.jpg" data-src-320="https://static1.jabong.com/live/images/dashboard-480x200.jpg" src="https://static1.jabong.com/live/images/dashboard_banner.jpg">
            <span class="black-overlay"></span>
        </figure>
    </article>
    <div class="container">
        <div class="row">
            <div class="span12" id="content">
                <div class="hangover_tabs text-uppercase" id="hangover-tabs">
                    <ul class="tabs">
                        <li class="description_tab active">
                            <a href="" data_url="profile_url" tab="personalInfo">Personal Info</a>
                        </li>
                        <li class="reviews_tab">
                            <a href="" data_url="profile_url" tab="savedCards">Saved Cards</a>
                        </li>
                        <li class="cherry_wc_video_tab">
                            <a href="" data_url="profile_url" tab="savedAddresses">Saved Addresses</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="span12" >
                <div class="tab-content" id="hangover-tab-content">
                    <c:choose>
                        <c:when test="${tab eq 'savedCards'}">
                            <%@ include file="/view/pages/customer/savedCards.jsp" %>
                        </c:when>
                        <c:when test="${tab eq 'savedAddresses'}">
                            <%@ include file="/view/pages/customer/deliveryAddress.jsp" %>
                        </c:when>
                        <c:otherwise>
                            <%@ include file="/view/pages/customer/personalInfo.jsp" %>
                        </c:otherwise>
                    </c:choose>

                </div>
            </div>
        </div>
    </div>
</div>

