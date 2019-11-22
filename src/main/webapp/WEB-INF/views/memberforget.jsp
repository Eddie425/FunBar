<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix='form' uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
 <meta charset="UTF-8">
 <title>login</title>
 <link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css' />">
 <script src="<c:url value='/js/jquery-1.12.4.js' />"></script>
 <script src="<c:url value='/js/bootstrap.min.js' />"></script>
 <script type="text/javascript">
 function enterSomething(){
	 document.getElementById("user").value='zuo';
	 document.getElementById("memberPwd").value='password';
 }
 </script>
</head>
<style>
    * {
        font-family: 微軟正黑體;
    }

    html,
    body {
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
    
    .error { color: red}
</style>

<body>
<c:set var="go" value="" scope="session"></c:set>
	<jsp:include page="header.jsp" />
	
    <div class="row">
        <div class="login col-sm-12">
            <h2>FunBar</h2>
            <form action="forget" method="post">
                <fieldset>
                    <div class="form-group">
                        <label for="memberId">帳號:</label>
                        <input id="user" type="text" class="form-control" name="memberId" placeholder="請輸入帳號">
                        <span class="error">${errorMsg.errUserName}</span>
                    </div>
                   
                </fieldset>
                <button type="submit" class="btn btn-info col-md-12">尋找密碼</button>
            </form>
        </div>

        
    </div>
</body>
</html>