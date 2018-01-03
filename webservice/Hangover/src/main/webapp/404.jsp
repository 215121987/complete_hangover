<%@ include file="/view/common/taglib.jsp" %>
<%--<page:applyDecorator name="default">--%>
    <title><fmt:message key="404.title"/></title>
    <div class="motopress-wrapper content-holder clearfix">
        <div class="container">
            <div class="row">
                <div class="span12" data-motopress-wrapper-file="404.php" data-motopress-wrapper-type="content">
                    <div class="row error404-holder">
                        <div class="span7 error404-holder_num" data-motopress-type="static"> 404</div>
                        <div class="span5" data-motopress-type="static">
                            <div class="hgroup_404">
                                <h1>Sorry!</h1>
                                <h2>Page Not Found</h2>
                            </div>
                            <h4>The page you are looking for might have been removed, had its name changed, or is temporarily unavailable.</h4>
                            <p>Please try using our search box below to look for information on the internet.</p>
                            <div class="search-form">
                                <form id="searchform" method="get" action="#" accept-charset="utf-8">
                                    <input type="text" value="" name="s" id="s" class="search-form_it">
                                    <input type="submit" value="search" id="search-submit" class="search-form_is btn btn-primary">
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<%--</page:applyDecorator>--%>
