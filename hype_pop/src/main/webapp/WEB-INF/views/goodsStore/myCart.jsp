<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
 <jsp:include page="layout/popUpHeader.jsp" />


<h1>내 장바구니 입니다</h1>

<button id="purchaseBTN">결제하기</button>

 <jsp:include page="layout/popUpFooter.jsp" />
 <jsp:include page="layout/goodsNavBar.jsp" />
</body>
<script type="text/javascript" src="/resources/goodsJs/myCart.js"></script>
</html>