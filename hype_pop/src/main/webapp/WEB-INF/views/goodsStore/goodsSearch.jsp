<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
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

	<table class="searchResultGoods">
		<tr>
			<td id="goodsImg">
				<h1>굿즈 이미지</h1>
			</td>
			<td id="goodsInfo"><span id="goodsName">굿즈 이름</span> 
			<span id="goodslLikeCount">좋아요 수</span>
				<h3>가격 : 20000</h3>
				<h3>되게 길지만 사고 싶게 만드는 굿즈 설명글</h3>
				<h3>
					관심사: <span>관심사 1</span>, <span>관심사 2</span>, <span>관심사 3</span>
				</h3>
				<h3>
					<%= new SimpleDateFormat("yyyy-MM-dd").format(new Date()) %>
				</h3></td>
		</tr>
	</table>
	<jsp:include page="layout/popUpFooter.jsp" />
	<jsp:include page="layout/goodsNavBar.jsp" />
</body>
</html>