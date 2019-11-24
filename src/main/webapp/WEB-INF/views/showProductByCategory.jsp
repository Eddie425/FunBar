<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Shopping Center</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="<c:url value="/css/shoppingCart.css" />" >
</head>


<body>
 <jsp:include page="header.jsp" />

 <!-- Content 區塊 -->
 <div class="container" style="margin-top: 150px">
  <div class="row" style="float: left">
   <div class="col-md-3">
    <div class="list-group" style="width: 150px">
     <a href="${pageContext.request.contextPath}/shoppingCart?index=1"
      class="list-group-item">All Products</a>
     <c:forEach var='category' items='${categoryList}'>
      <a href="${pageContext.request.contextPath}/shoppingCart/${category}?index=1"
       class="list-group-item">${category}</a>
     </c:forEach>
    </div>
    <div style="margin-top: 5px">
   <a href="<c:url value='/showCart' />"><button class="btn btn-info">My Cart</button></a>
    </div>
   </div>
  </div>

  
 </div>
 <!-- .container -->

  <div class="row">
   <c:forEach var="pb" items="${shoppingCart}" begin="0" step="1" varStatus="i" >
    <div class="prodlist">
     <p>${pb.productDetail}</p>
     <p class="prodtitle">${pb.productName}</p>
     <figure>
     <a href="<c:url value='/product?id=${pb.productId}' />" ">
      <img src="<c:url value='/ProductPicture/${pb.productId}'/>" />
     </a> 
     </figure>
     <figcaption>
      <p>Unit Price:<span style="color: #FF44AA; font-weight: bold">$ ${pb.unitPrice}</span>
      </p>
      <p>
      discount：<span style="color: #CE0000; font-weight: bold;">${pb.discount}</span>
      </p>

     </figcaption>

    </div>
    <!-- .prodlist -->
   </c:forEach>
  </div>
  <ul class="pagination justify-content-center">
<%-- 			<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/shoppingCart/${category}?index=1" aria-label="Previous">  --%>
<!-- 			<span aria-hidden="true">&laquo;</span> <span class="sr-only">Previous</span></a></li> -->
			
			
			<c:forEach  begin="1" step="1" end="${listCount}" varStatus="i" >
				<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/shoppingCart/${category}?index=${i.index}">${i.index}</a></li>		
			</c:forEach>
			
		
<%-- 			<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/shoppingCart/${category}?index=${listCount}" aria-label="Next">  --%>
<!-- 			<span aria-hidden="true">&raquo;</span> <span class="sr-only">Next</span></a></li> -->
		</ul>
  
 </div>
 <!-- .container -->


 <jsp:include page="footer.jsp" />


</body>
</html>