<%@ include file="/view/common/taglib.jsp" %>

<div class="motopress-wrapper content-holder clearfix">
    <div class="container">
        <div class="row">
            <div class="span12">
                <div class="row">
                    <div class="span9 right right" id="content">
                        <div style="color: #888888">
                            <c:choose>
                                <c:when test="${'SUCCESS' eq payment.status}">
                                    <%--<img src="" alt="">--%>
                                    <h2 class="success_message_title">You're all set!</h2>
                                    <div class="message_highlight">
                                        <span style="float: left;">Order #: ${payment.orderNumber}</span>
                                        <span style="float: right;">Transaction #: ${payment.transactionId}</span>
                                    </div>
                                    <div class="result_message">
                                        Thanks for being awesome,
                                        <br/>
                                        We hope you enjoy your purchase!
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <%--<img src="" alt="">--%>
                                    <h2 class="failed_message_title">Sorry! Something went wrong.</h2>
                                    <div class="message_highlight"><span>Transaction Id: ${payment.transactionId}</span></div>
                                    <div class="result_message">
                                        Transaction failed!&nbsp; Please try again.
                                    </div>
                                </c:otherwise>
                            </c:choose>
                            <div class="message_note">
                                You will receive Mail/SMS from <fmt:message key="app.name"/> with details about your order. you can close this window.
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>