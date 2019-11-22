<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>活動參加者建議</title>
<style>
    body,
    title {
      font-family: "微軟正黑體";
    }

    td {
      width: 200px;
    }
    
    table {
      table-layout: fixed;
    }
    .container{
		font-family:微軟正黑體;
		margin: 100px auto 150px auto; width: 80%;
	}
  </style>
</head>
<body>

<jsp:include page="admin_header.jsp" />
<div class="container">
   <c:forEach var="eventName" items="${sus}">
						<a href="${pageContext.request.contextPath}/Suggestions/${eventName}"
							class="list-group-item">${eventName}</a>
					</c:forEach>
  </div>


  <jsp:include page="admin_footer.jsp" />


</body>
</html>