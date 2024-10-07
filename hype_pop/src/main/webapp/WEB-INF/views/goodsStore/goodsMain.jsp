<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>굿즈 메인 페이지 입니다</h1>
	<jsp:include page="layout/popUpHeader.jsp" />
    
    <h1>현재 인기있는 팝업스토어</h1>
    <div id="hotGoods">
		<span id="hotGoods1">인기 넘치는 굿즈1</span> &nbsp; 
		<span id="hotGoods2">인기 넘치는 굿즈2</span> &nbsp; 
		<span id="hotGoods3">인기 넘치는 굿즈3</span> &nbsp;
	    <span id="hotGoods4">인기 넘치는 굿즈4</span>
	</div>
	
	<jsp:include page="layout/popUpFooter.jsp" />
	<jsp:include page="layout/goodsNavBar.jsp" />ㄴ	
</body>
</html>