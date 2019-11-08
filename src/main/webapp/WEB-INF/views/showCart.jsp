<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<style>
a.orderBtn { text-decoration:none; padding: 5px; background: #fff; border: 1px solid black; border-radius: 1px;}
.orderBtn:hover { background: #eee;}
</style>
<head>
	<meta charset="UTF-8">
	<title>show cart</title>
</head>

<body>
<jsp:include page="header.jsp" />

<!-- Content 區塊 -->
<div class="container page">
	<div class="row">
		<div class="col-sm-offset-10 col-sm-2">
			<a class="orderBtn" href="<c:url value='/deleteCartItem' />">清空購物車</a>
		</div>
	</div>
	<div class="table-responsive">
		<table class="table">
			<thead>
				<tr>
					<th>產品示意圖</th>
					<th>產品名稱</th>
					<th>單價</th>
					<th>數量</th>
					<th>小計</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="ci" items="${cart.cartItems}">
				<tr>
					<td>
						<img src="<c:url value='/ProductPictures/${ci.product.productId}' />" width="50px" height="100px">
					</td>
					<td>${ci.product.productName}</td>
					<td>${ci.product.unitPrice}</td>
					<td>${ci.count}</td>
					<td>${ci.subtotal}</td>
 
					<td>
						<a class="orderBtn" href="<c:url value='/removeCartItem?productId=${ci.product.productId}' />">刪除</a>
					</td>

				</tr>
				</c:forEach>
				<tr>
					<td align="right" colspan="6">總計:${cart.total}</td>
				</tr>
				<tr>
					<td align="right" colspan="6">
						<form action="<c:url value='/shoppingcart/InsertOrderServlet' />" method="post">
							<label for="address" class="col-sm-4 col-xs-12">地址:</label>
							<div class="col-sm-4 col-xs-12">
								<input type="text" class="form-control" id="address" name="address" placeholder="請輸入地址" required>
							</div>
							<div class="col-sm-4 col-xs-12">
								<button type="submit" class="btn btn-success">結帳去</button>
							</div>
						</form>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>

<jsp:include page="footer.jsp" />
</body>

</html>