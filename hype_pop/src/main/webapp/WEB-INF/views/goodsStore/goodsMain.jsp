<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
/* 전체 레이아웃 */
body {
	margin: 0;
	font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
	background-color: #141414;
	color: white;
}

/* 네비게이션 바 */
#popUpHeader {
	background-color: #141414;
	padding: 10px 20px;
	display: flex;
	justify-content: space-between;
	align-items: center;
}

h1, h2 {
	text-align: center;
	color: #e50914;
	margin-bottom: 20px;
}

/* 굿즈 컨테이너 스타일 */
#hotGoods, #interestGoods1, #interestGoods2, #interestGoods3 {
	display: flex;
	align-items: center;
	justify-content: center;
	margin: 40px 0;
	position: relative;
}

/* 컨테이너를 슬라이더 크기 100%로 설정 */
.goodsContainer1, .goodsContainer2, .goodsContainer3, .goodsContainer4 {
	display: flex;
	overflow: hidden;
	width: 100%;
	height: 300px;
	border-radius: 10px;
	position: relative;
	box-sizing: border-box;
}

/* 한 줄에 4개의 상품이 꽉 차게 보이도록 설정 */
.goodsItem1, .goodsItem2, .goodsItem3, .goodsItem4 {
	background-color: #333;
	color: white;
	width: 25%; /* 4개의 상품이 한 줄에 꽉 차게 */
	height: 100%;
	margin: 0 10px;
	border-radius: 10px;
	padding: 10px;
	box-sizing: border-box;
	transition: transform 0.3s ease;
}

.goodsItem1:hover, .goodsItem2:hover, .goodsItem3:hover, .goodsItem4:hover
	{
	transform: scale(1.01);
}

/* 가격, 좋아요, 이름 등 */
.goodsLike {
	text-align: right;
	color: #fff;
}

.goodsName {
	background-color: #e50914;
	color: white;
	padding: 10px;
	font-weight: bold;
	margin-top: 20px;
	height: 150px;
	overflow: hidden;
	text-overflow: ellipsis;
}

.goodsPrice {
	text-align: right;
	color: #f5c518;
	font-weight: bold;
	margin-top: 10px;
}

/* 좌우 버튼 */
#prevBtn1, #nextBtn1, #prevBtn2, #nextBtn2, #prevBtn3, #nextBtn3,
	#prevBtn4, #nextBtn4 {
	background-color: #e50914;
	border: none;
	color: white;
	font-size: 18px;
	padding: 10px;
	cursor: pointer;
	border-radius: 50%;
	position: absolute;
	top: 50%;
	transform: translateY(-50%);
	z-index: 10;
}

#prevBtn1, #prevBtn2, #prevBtn3, #prevBtn4 {
	left: 10px;
}

#nextBtn1, #nextBtn2, #nextBtn3, #nextBtn4 {
	right: 10px;
}
</style>
</head>
<body>
	<jsp:include page="layout/popUpHeader.jsp" />
	<h1>현재 인기있는 굿즈</h1>
	<div id="hotGoods">
		<button id="prevBtn1">◀</button>
		<div class="goodsContainer1" id="goodsContainer1">
			<c:forEach var="vo" items="${likeGoods}">
				<div class="goodsItem1">
					<input type="hidden" value="${vo.gno}">
					<div class="goodsLike">${vo.likeCount}</div>
					<div class="goodsName">${vo.gname}</div>
					<div class="goodsPrice">${vo.gprice}</div>
				</div>
			</c:forEach>
		</div>
		<button id="nextBtn1">▶</button>
	</div>
	<h1>관심사별 인기 목록</h1>
	<h2>
		<c:choose>
			<c:when test="${categoryOne == 'healthBeauty'}">건강 & 뷰티</c:when>
			<c:when test="${categoryOne == 'game'}">게임</c:when>
			<c:when test="${categoryOne == 'culture'}">문화</c:when>
			<c:when test="${categoryOne == 'shopping'}">쇼핑</c:when>
			<c:when test="${categoryOne == 'supply'}">문구</c:when>
			<c:when test="${categoryOne == 'kids'}">키즈</c:when>
			<c:when test="${categoryOne == 'design'}">디자인</c:when>
			<c:when test="${categoryOne == 'foods'}">식품</c:when>
			<c:when test="${categoryOne == 'interior'}">인테리어</c:when>
			<c:when test="${categoryOne == 'policy'}">정책</c:when>
			<c:when test="${categoryOne == 'character'}">캐릭터</c:when>
			<c:when test="${categoryOne == 'experience'}">체험</c:when>
			<c:when test="${categoryOne == 'collaboration'}">콜라보</c:when>
			<c:when test="${categoryOne == 'entertainment'}">방송</c:when>
		</c:choose>
	</h2>
	<div id="interestGoods1">
		<button id="prevBtn2">◀</button>
		<div class="goodsContainer2" id="goodsContainer2">
			<c:forEach var="vo" items="${interestOneNotLogin}">
				<div class="goodsItem2">
					<input type="hidden" value="${vo.gno}">
					<div class="goodsLike">${vo.likeCount}</div>
					<div class="goodsName">${vo.gname}</div>
					<div class="goodsPrice">${vo.gprice}</div>
				</div>
			</c:forEach>
		</div>
		<button id="nextBtn2">▶</button>
	</div>
	<h2>
		<c:choose>
			<c:when test="${categoryTwo == 'healthBeauty'}">건강 & 뷰티</c:when>
			<c:when test="${categoryTwo == 'game'}">게임</c:when>
			<c:when test="${categoryTwo == 'culture'}">문화</c:when>
			<c:when test="${categoryTwo == 'shopping'}">쇼핑</c:when>
			<c:when test="${categoryTwo == 'supply'}">문구</c:when>
			<c:when test="${categoryTwo == 'kids'}">키즈</c:when>
			<c:when test="${categoryTwo == 'design'}">디자인</c:when>
			<c:when test="${categoryTwo == 'foods'}">식품</c:when>
			<c:when test="${categoryTwo == 'interior'}">인테리어</c:when>
			<c:when test="${categoryTwo == 'policy'}">정책</c:when>
			<c:when test="${categoryTwo == 'character'}">캐릭터</c:when>
			<c:when test="${categoryTwo == 'experience'}">체험</c:when>
			<c:when test="${categoryTwo == 'collaboration'}">콜라보</c:when>
			<c:when test="${categoryTwo == 'entertainment'}">방송</c:when>
		</c:choose>
	</h2>
	<div id="interestGoods2">
		<button id="prevBtn3">◀</button>
		<div class="goodsContainer3" id="goodsContainer3">
			<c:forEach var="vo" items="${interestTwoNotLogin}">
				<div class="goodsItem3">
					<input type="hidden" value="${vo.gno}">
					<div class="goodsLike">${vo.likeCount}</div>
					<div class="goodsName">${vo.gname}</div>
					<div class="goodsPrice">${vo.gprice}</div>
				</div>
			</c:forEach>
		</div>
		<button id="nextBtn3">▶</button>
	</div>
	<h2>
		<c:choose>
			<c:when test="${categoryThree == 'healthBeauty'}">건강 & 뷰티</c:when>
			<c:when test="${categoryThree == 'game'}">게임</c:when>
			<c:when test="${categoryThree == 'culture'}">문화</c:when>
			<c:when test="${categoryThree == 'shopping'}">쇼핑</c:when>
			<c:when test="${categoryThree == 'supply'}">문구</c:when>
			<c:when test="${categoryThree == 'kids'}">키즈</c:when>
			<c:when test="${categoryThree == 'design'}">디자인</c:when>
			<c:when test="${categoryThree == 'foods'}">식품</c:when>
			<c:when test="${categoryThree == 'interior'}">인테리어</c:when>
			<c:when test="${categoryThree == 'policy'}">정책</c:when>
			<c:when test="${categoryThree == 'character'}">캐릭터</c:when>
			<c:when test="${categoryThree == 'experience'}">체험</c:when>
			<c:when test="${categoryThree == 'collaboration'}">콜라보</c:when>
			<c:when test="${categoryThree == 'entertainment'}">방송</c:when>
		</c:choose>
	</h2>
	<div id="interestGoods3">
		<button id="prevBtn4">◀</button>
		<div class="goodsContainer4" id="goodsContainer4">
			<c:forEach var="vo" items="${interestThreeNotLogin}">
				<div class="goodsItem4">
					<input type="hidden" value="${vo.gno}">
					<div class="goodsLike">${vo.likeCount}</div>
					<div class="goodsName">${vo.gname}</div>
					<div class="goodsPrice">${vo.gprice}</div>
				</div>
			</c:forEach>
		</div>
		<button id="nextBtn4">▶</button>
	</div>
	<jsp:include page="layout/popUpFooter.jsp" />
	<jsp:include page="layout/goodsNavBar.jsp" />
</body>
<script type="text/javascript" src="/resources/goodsJs/goodsHeader.js"></script>
<script type="text/javascript" src="/resources/goodsJs/goodsMain.js"></script>
</html>