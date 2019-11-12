<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<style>
#emptyCart {
	display: block;
	margin-top:150px;
	margin-bottom:100px;
	margin-left: auto;
	margin-right: auto;
	width: 50%;
}
</style>
<head>
<meta charset="UTF-8">
<title>Cart</title>
</head>

<body>
	<jsp:include page="header.jsp" />

	<!-- Content 區塊 -->
	<div id="emptyCart">
		<a href="/FunBar/shoppingCart"><img src="images/emptyCart.png" style="height: 367px" alt="emptyCart" title="back to shop"></a>
		<h2>There is no any product in your cart.</h2>
	</div>
	<jsp:include page="footer.jsp" />
</body>

</html>