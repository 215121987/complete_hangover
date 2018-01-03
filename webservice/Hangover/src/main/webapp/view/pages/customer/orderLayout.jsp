<%@ include file="/view/common/taglib.jsp" %>
<div class="motopress-wrapper content-holder clearfix woocommerce">
    <article class="main-banner clearfix">
        <div class="page-title text-uppercase">
            <span class="profile-logo"></span>
            Your Orders
        </div>
        <figure class="bnr-R-second-img">
            <img class="loaded" alt="Dashboard" data-src-1280="https://static1.jabong.com/live/images/dashboard_banner.jpg" data-src-1024="https://static1.jabong.com/live/images/dashboard_banner.jpg" data-src-768="https://static1.jabong.com/live/images/dashboard_banner.jpg" data-src-500="https://static1.jabong.com/live/images/dashboard-480x200.jpg" data-src-320="https://static1.jabong.com/live/images/dashboard-480x200.jpg" src="https://static1.jabong.com/live/images/dashboard_banner.jpg">
            <span class="black-overlay"></span>
        </figure>
    </article>
    <div class="container" <%--style="margin-top: -150px;"--%>>
        <div class="row">
            <%--<h3 class="text-uppercase text-center clearfix">
                <span class="page-heading">My Account</span>
            </h3>--%>
            <div class="span12" id="content">
                <div class="hangover_tabs text-uppercase" id="hangover-tabs">
                    <ul class="tabs">
                        <li class="description_tab active">
                            <a href="" data_url="order_url" tab="order">Order</a>
                        </li>
                        <li class="reviews_tab">
                            <a href="" data_url="order_url" tab="openOrder">Open Order</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="span12" >
                <div class="tab-content" id="hangover-tab-content">
                    <%@ include file="/view/pages/customer/orders.jsp" %>
                </div>
            </div>
        </div>
    </div>
</div>
