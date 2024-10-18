<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="layout/adminHeader.jsp"/>
	
	<form action="#" id="askTypeBox">
		<select id="askType">
			<option value="refund">환불</option>
			<option value="delivery">배송</option>
			<option value="complain">신고</option>
		</select>
	</form>
	
	<div id="askListCat">
		<span id="askNo">문의 번호</span>
		<span id="askCat">문의 유형</span>
		<span id="askTitle">문의 제목</span>
		<span id="askDate">문의 작성 날짜</span>
		<span id="askYn">답변 여부</span>
	</div>

</body>
</html>