<%@ include file="../common/taglib.jsp" %>
<script type="text/javascript">
    jQuery(document).ready(function ($) {
        if (!device.mobile() && !device.tablet()) {
            liteModeSwitcher = false;
        } else {
            liteModeSwitcher = true;
        }
        if ($.browser.msie && parseInt($.browser.version) < 9) {
            liteModeSwitcher = true;
        }
        jQuery('#parallax-slider').parallaxSlider({
            parallaxEffect:"parallax_effect_normal",
            parallaxInvert:false, animateLayout:"simple-fade-eff",
            duration:1500, autoSwitcher:true, autoSwitcherDelay:10000,
            scrolling_description:true, slider_navs:false,
            slider_pagination:"buttons_pagination",
            liteMode:liteModeSwitcher
        });
    });
</script>
<div id="parallax-slider" class="parallax-slider">
    <ul class="baseList">
        <c:forEach items="${home.generalOffer}" var="offer">
            <li data-preview="${offer.imageURL}" data-thumb-url="${offer.imageURL}"
                data-ulr-link="">
                <div class="slider_caption">
                    <div class="caption_wrapper">
                        <div class="caption_up">
                            <div class="caption_up_content">
                                <c:out value="${offer.title}" escapeXml="false"/>
                            </div>
                        </div>
                        <div class="caption_down">
                            <a class="round-button" href="shop"></a>
                            <div class="caption_down_content">
                                <c:out value="${offer.subTitle}" escapeXml="false"/>
                            </div>
                        </div>
                    </div>
                </div>
            </li>
        </c:forEach>
    </ul>
</div>
<div class="motopress-wrapper content-holder clearfix">
    <div class="container">
        <div class="row">
            <div class="span12">
                <div class="row">
                    <div class="span12" data-motopress-type="static">
                        <div class="slider_off"><!--slider off--></div>
                    </div>
                </div>
                <div class="row">
                    <div class="span12">
                        <div id="post-203" class="page post-203 type-page status-publish hentry">
                            <section class="parallax-box image-parallax-box parallax1">
                                <div class="parallax-content">
                                    <div class="partners_wrapper">
                                        <div class="partners_wrapper_wrap_inner">
                                            <ul>
                                                <c:forEach items="${home.brands}" var="brand">
                                                    <li>
                                                        <a href="${brand.url}">
                                                        <img class="alignnone size-full wp-image-2176"
                                                            src="${brand.logo}"
                                                            alt="${brand.name}" width="233" height="144"/>
                                                        </a>
                                                    </li>
                                                </c:forEach>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="banners_wrapper">
                                        <div class="banners_wrapper_wrap_inner">
                                            <div class="row ">
                                                <div class="span5 ">
                                                    <div class="extra-wrap">
                                                        <c:out value="${home.expressOffer.title}" escapeXml="false"/>
                                                    </div>
                                                </div>
                                                <div class="span7 ">
                                                    <div class="banner-wrap ">
                                                        <figure class="featured-thumbnail">
                                                            <a href="#" title="${home.expressOffer.title}">
                                                                <img src="${home.expressOffer.imageURL}" title="${home.expressOffer.title}"
                                                                     alt=""/>
                                                            </a>
                                                        </figure>
                                                        <div class="banner-wrap_content">
                                                            <c:out value="${home.expressOffer.subTitle}" escapeXml="false"/>
                                                            <div class="link-align banner-btn">
                                                                <a href="#" title="Go" class="btn btn-link"
                                                                   target="_self">Go</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!-- .banner-wrap (end) -->
                                                </div>
                                            </div>
                                            <!-- .row (end) -->
                                        </div>
                                    </div>
                                    <div class="clear"></div>
                                </div>
                                <div class="parallax-bg" data-parallax-type="image"
                                     data-img-url="${home.bannerURL}"
                                     data-speed="normal" data-invert="false">

                                </div>
                            </section>
                        </div>
                        <div class="content_box banners_wrapper2 ">
                            <div class="row ">
                                <div class="span8 ">
                                    <div class="custom_banner1">
                                        <div class="custom_banner1_wrap_inner">
                                            <div class="extra-wrap">
                                                <c:out value="${home.specificOffer.subTitle}" escapeXml="false"/>
                                                <div class="clear"></div>
                                                <!-- .clear (end) -->
                                                <c:out value="${home.specificOffer.title}" escapeXml="false"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="span4 ">
                                    <div class="custom_banner2">
                                        <div class="custom_banner2_wrap_inner">
                                            <h3>
                                                <a href="<c:url value="/comm/shop.html" />"><c:out value="${home.specificOffer.description}" escapeXml="false"/></a>
                                            </h3>
                                        </div>
                                    </div>
                                    <!-- .row (end) -->
                                </div>
                            </div>
                            <div class="clear"></div>
                        </div>
                        <!-- .content_box (end) -->
                        <%@ include file="shop/services.jsp" %>
                        <%--<%@ include file="shop/featureProduct.jsp" %>--%>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
