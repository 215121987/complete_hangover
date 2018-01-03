<%@ include file="/view/common/taglib.jsp" %>
<head>
    <title></title>

</head>
<body>
<center><h4>Please do not refresh this page...</h4></center>
<div style="display: none;">
    <form action="${gatewayDetail.actionURL}" method="POST" name="go_for_payment">
       <c:forEach items="${gatewayDetail.params}" var="gateway">
           <input type="text" name="${gateway.name}" value="${gateway.value}"/>
       </c:forEach>
       <input type="submit"/>
        <script type="text/javascript">
            document.go_for_payment.submit();
        </script>
    </form>
</div>
</body>
