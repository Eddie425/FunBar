<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>login</title>
<link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css' />">
<script src="<c:url value='/js/jquery-1.12.4.js' />"></script>
<script src="<c:url value='/js/bootstrap.min.js' />"></script>
</head>
<style>
* {
	font-family: 微軟正黑體;
}

html, body {
	width: 100%;
	height: 100%;
	display: flex;
	justify-content: center;
	align-items: center;
	background-color: #eee;
}

.login {
	background-color: #fff;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
	padding: 20px;
	margin-bottom: 10px;
	font-weight: bold;
}

.registerBox {
	background-color: #fff;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
	padding: 10px;
	font-weight: bold;
}

.registerBox a {
	text-decoration: none;
}

.error {
	color: red
}
</style>

<body>
	<meta charset="UTF-8">
	<link rel="stylesheet"
		href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
</head>

<body>

	<form action="findyou" method="post">
                <fieldset>
                    <div class="form-group">
                        <label for="memberPwd">密碼:</label>
                        <input  type="text" class="form-control" name="memberPwd" placeholder="請輸入新密碼">
                        <span class="error">${errorMsg.errUserName}</span>
                    </div>  
                </fieldset>
                <div class="button">
						<input type="hidden" name="memberId" value="${one.memberId}" />
						<input type="submit" class="btn btn-outline-secondary" value="送出">
						<td><a href="${pageContext.request.contextPath}">
							<button type="button" class="btn btn-outline-secondary">回首頁</button>
					</a></td>
            </form>
		</body>
</html>