<%@ include file="/view/common/taglib.jsp" %>
<div class="motopress-wrapper content-holder clearfix">
    <article class="main-banner clearfix">
        <div class="page-title text-uppercase">
            <span class="profile-logo"></span>
            My Account
        </div>
        <figure class="bnr-R-second-img">
            <img class="loaded" alt="Dashboard" data-src-1280="https://static1.jabong.com/live/images/dashboard_banner.jpg" data-src-1024="https://static1.jabong.com/live/images/dashboard_banner.jpg" data-src-768="https://static1.jabong.com/live/images/dashboard_banner.jpg" data-src-500="https://static1.jabong.com/live/images/dashboard-480x200.jpg" data-src-320="https://static1.jabong.com/live/images/dashboard-480x200.jpg" src="https://static1.jabong.com/live/images/dashboard_banner.jpg">
            <span class="black-overlay"></span>
        </figure>
    </article>
    <div class="container minus_container">
        <div class="row">
            <div class="span12" id="content" style="background-color: #ffffff;margin-top: 20px;">
                <div class="col-md-11 category-block">
                    <h4 class="category-heading">
                        <span class="icon-st"></span>
                        ORDERS
                        <span class="visible-sm-block visible-xs-block accordian-icon close"></span>
                    </h4>
                    <div class="category-wrapper clearfix">
                        <ul class="category-list-block">
                            <li>
                                <a href="<c:url value="/account/order.html?tab=order" />">ORDERS</a>
                            </li>
                            <li>
                                <a href="<c:url value="/account/order.html?tab=openOrder" />">OPEN ORDERS</a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-md-11 category-block">
                    <h4 class="category-heading">
                        <span class="fa icon-credit-card icon-2x"></span>
                        CREDITS & COUPONS
                        <span class="visible-sm-block visible-xs-block accordian-icon close"></span>
                    </h4>
                    <div class="category-wrapper clearfix">
                        <ul class="category-list-block">
                            <li>
                                <a href="<c:url value="/account/coupon.html?tab=coupon" />">COUPONS</a>
                            </li>
                            <li>
                                <a href="<c:url value="/account/credit.html?tab=credit" />">CREDITS</a>
                            </li>
                        </ul>
                        <article class="gift-card-block">
                            <p>
                                Have a Gift Card?
                                <br>
                                Convert your Gift Card to Hangover Credits
                            </p>
                                <a href="<c:url value="/account/giftCards.html" />">ADD GIFT CREDITS</a>
                        </article>
                    </div>
                </div>
                <div class="col-md-11 category-block">
                    <h4 class="category-heading">
                        <span class="icon-profile"></span>
                        PROFILE
                        <span class="visible-sm-block visible-xs-block accordian-icon close"></span>
                    </h4>
                    <div class="category-wrapper clearfix">
                        <ul class="category-list-block">
                            <li>
                                <a href="<c:url value="/account/profile.html?tab=personalInfo" />">PERSONAL INFO </a>
                            </li>
                            <li>
                                <a href="<c:url value="/account/profile.html?tab=savedCards" />">SAVED CARDS</a>
                            </li>
                            <li>
                                <a href="<c:url value="/account/profile.html?tab=savedAddresses" />">SAVED ADDRESSES</a>
                            </li>

                        </ul>
                    </div>
                </div>
                <div class="clear"></div>
            </div>
        </div>
    </div>
</div>
