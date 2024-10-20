<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>굿즈 스토어 상세 페이지</title>
<style>
/* 전체 페이지 레이아웃 */
body {
	font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
	background-color: #141414;
	color: #fff;
	margin: 0;
	padding: 0;
}

h1, h2, p, span {
	color: #fff;
}

/* 굿즈 상세 레이아웃 */
.goodsDetails {
	width: 80%;
	margin: 30px auto;
	display: flex;
	flex-direction: row;
	border-radius: 10px;
	background-color: #222;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.5);
	overflow: hidden;
}

#goodsBanner, #goodsInfo {
	width: 50%;
	padding: 20px;
	box-sizing: border-box;
}

#goodsBanner {
	background-color: #333;
	text-align: center;
	display: flex;
	justify-content: center;
	align-items: center;
}

#goodsBanner img {
	max-width: 100%;
	height: auto;
	border-radius: 10px;
}

#goodsInfo {
	background-color: #181818;
	padding: 20px;
}

#goodsInfo span {
	display: block;
	margin-bottom: 15px;
	font-size: 18px;
}

/* 수량 조정 버튼 */
.quantityBar {
	display: flex;
	align-items: center;
	margin: 20px 0;
}

.quantityBar button {
	width: 40px;
	height: 40px;
	background-color: #444;
	color: #fff;
	border: none;
	font-size: 20px;
	cursor: pointer;
	transition: background-color 0.3s;
}

.quantityBar button:hover {
	background-color: #888;
}

#quantity {
	width: 60px;
	text-align: center;
	border: 1px solid #333;
	font-size: 18px;
	background-color: #222;
	color: #fff;
	margin: 0 10px;
}

.totalPrice {
	font-size: 22px;
	font-weight: bold;
	color: #e50914;
}

/* 버튼 스타일 */
.actionButtons {
	display: flex;
	gap: 15px;
}

.actionButtons button {
	padding: 15px 20px;
	background-color: #e50914;
	color: white;
	border: none;
	font-size: 18px;
	cursor: pointer;
	transition: background-color 0.3s;
}

.actionButtons button:hover {
	background-color: #c3070a;
}

.actionButtons button.directPurchase {
	background-color: #f44336;
}

/* 굿즈 상세 이미지 */
.goodsDetailImg {
	width: 80%;
	margin: 30px auto;
	text-align: center;
	background-color: #222;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.5);
}

/* 별점 스타일 */
.starRating span {
	font-size: 30px;
	cursor: pointer;
}

.starRating span:hover, .starRating span.active {
	color: gold;
}

#userReviews {
	width: 80%;
	margin: 0 auto 30px auto; /* 중앙 정렬 */
	padding: 20px;
	background-color: #222;
	border-radius: 10px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.5);
}

/* 후기 제목 스타일 */
#userReviews h2 {
	color: #fff;
	font-size: 28px; /* 크기를 살짝 키움 */
	font-weight: 600; /* 글자 두께를 조금 더 두껍게 */
	margin-bottom: 20px; /* 제목과 댓글 목록 사이 간격 */
	text-align: left; /* 제목을 왼쪽 정렬 */
	padding-bottom: 10px;
	border-bottom: 2px solid #e50914; /* 아래에 구분선 추가 */
}

/* 댓글 목록 */
.myChat {
	list-style-type: none;
	padding: 0;
	color: #fff;
}

.myChat li {
	background-color: #333;
	padding: 15px;
	margin-bottom: 10px;
	border-radius: 8px;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
}

/* 유저 리뷰 스타일 */
#userReviews ul {
	list-style-type: none;
	padding: 0;
}

#userReviews ul li {
	background-color: #333;
	padding: 15px;
	margin-bottom: 10px;
	border-radius: 8px;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
	color: #fff;
	width: 80%; /* 댓글 영역의 너비를 80%로 설정 */
	margin: 0 auto 10px; /* 가운데 정렬 */
	position: relative; /* 케밥 메뉴 위치를 위한 설정 */
}

.reviewItem {
	margin-bottom: 20px;
}

.reviewContent {
	width: 80%;
	display: inline-block; /* 댓글 내용을 차지하는 영역 */
}

/* 평균 별점 영역 */
.avgStarRating {
	display: flex;
	align-items: center;
	margin: 20px auto;
	width: 50%;
	justify-content: space-between;
}

/* 케밥 메뉴 */
.kebabMenu {
	position: absolute;
	top: 15px;
	right: 10px; /* 우측에서 10px 떨어지게 */
	cursor: pointer;
}

.menuOptions {
	visibility: hidden;
	position: absolute;
	right: 0;
	top: 30px;
	background: white;
	box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.5);
	padding: 10px;
	border-radius: 8px;
	z-index: 1000;
}

.menuOptions button {
	display: block;
	width: 100%;
	padding: 10px;
	margin-bottom: 5px;
	cursor: pointer;
	background-color: #e50914;
	color: white;
	border: none;
	border-radius: 5px;
	text-align: center;
}

#chkLike {
	background-color: white;
}

.menuOptions button:hover {
	background-color: #c3070a;
}

.menuOptions .deleteBtn {
	background-color: #f44336;
}

#reviewForm {
	display: flex;
	flex-direction: column;
	align-items: center; /* 가로 축 중앙 정렬 */
	justify-content: center; /* 세로 축 중앙 정렬 */
	width: 80%; /* 폼 너비 설정 */
	margin: 30px auto; /* 가운데 정렬을 위해 자동 마진 */
	background-color: #222;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.5);
}

#reviewText {
	width: 100%;
	background-color: #333;
	color: #fff;
	border: 1px solid #444;
	border-radius: 5px;
	padding: 10px;
	margin-bottom: 15px;
	font-size: 16px;
}

#addReply {
	background-color: #e50914;
	color: #fff;
	border: none;
	padding: 10px 20px;
	font-size: 16px;
	cursor: pointer;
	border-radius: 5px;
	transition: background-color 0.3s;
}

#addReply:hover {
	background-color: #c3070a;
}

.starRating {
	display: flex;
	justify-content: center; /* 별점도 중앙 정렬 */
	margin-bottom: 10px;
}

.starRating span {
	font-size: 30px;
	cursor: pointer;
	color: gold;
}

#selectedRating {
	margin-bottom: 15px;
	text-align: center;
}
.editCommentInput {
    width: 100%;  /* 입력 필드 너비를 100%로 설정 */
    padding: 10px;  /* 적절한 패딩 추가 */
    margin-bottom: 10px;  /* 하단 간격 추가 */
    font-size: 16px;  /* 글자 크기 */
    border: 1px solid #444;  /* 테두리 색상 */
    border-radius: 5px;  /* 모서리를 둥글게 */
    background-color: #222;  /* 배경색 */
    color: #fff;  /* 텍스트 색상 */
}
.styledButton {
    padding: 10px 20px;  /* 버튼 패딩 */
    font-size: 16px;  /* 글자 크기 */
    cursor: pointer;  /* 마우스 포인터 변경 */
    border: none;  /* 테두리 없음 */
    border-radius: 5px;  /* 둥근 모서리 */
    margin-right: 10px;  /* 버튼 간의 간격 */
    transition: background-color 0.3s;  /* 배경색 전환 애니메이션 */
}

/* 수정 완료 버튼 */
.saveEditBtn {
    background-color: #e50914;  /* 넷플릭스 스타일 빨간색 */
    color: white;  /* 글자 색상 흰색 */
}

.saveEditBtn:hover {
    background-color: #c3070a;  /* 더 어두운 빨간색으로 변경 */
}

/* 수정 취소 버튼 */
.cancelEditBtn {
    background-color: #444;  /* 회색 배경 */
    color: white;  /* 글자 색상 흰색 */
}

.cancelEditBtn:hover {
    background-color: #888;  /* 더 밝은 회색으로 변경 */
}

.pagination {
    text-align: center;
    margin-top: 20px;
}
.pagination button {
    background-color: #e50914; /* 넷플릭스 빨간색 */
    color: white;
    border: none;
    padding: 10px 20px;
    margin: 0 5px;
    font-size: 16px;
    cursor: pointer;
    border-radius: 5px;
    transition: background-color 0.3s;
}

.pagination button.active {
    background-color: #c3070a; /* 현재 페이지는 더 어두운 빨간색 */
    font-weight: bold;
}

.pagination button:hover {
    background-color: #f44336; /* 버튼 호버 시 밝은 빨간색 */
}
</style>
</head>
<body>
	<jsp:include page="layout/popUpHeader.jsp" />
	<div class="goodsDetails">
		<div id="goodsBanner">굿즈 배너 이미지</div>
		<div id="goodsInfo">
			<span id="goodsLike">좋아요: ${goods.likeCount }회</span>
			<span id="goodsName">상품명: ${goods.gname }</span>
			<span id="goodsPrice">가격: ${goods.gprice }</span>
			<span id="goodsDes"> ${goods.gexp } </span>
			<span id="endDate">판매 종료일: ${goods.sellDate }</span>
			<!-- 수량 조정 바 -->
			<div class="quantityBar">
				<button id="decreaseBtn">-</button>
				<input type="text" id="quantity" value="1" readonly />
				<button id="increaseBtn">+</button>
			</div>
			<div class="totalPrice">
				총 가격: <span id="totalPrice"></span>
			</div>
			<div class="actionButtons">
				<button id="addToCart">장바구니 담기</button>
				<button id="directPurchase" class="directPurchase">바로 결제</button>
				<button id="chkLike"><img id="likeIcon" src="/resources/images/emptyHeart.png" alt="Like" width="24"></button>
			</div>
		</div>
	</div>
	<div class="goodsDetailImg">
		<h1>굿즈 상세 이미지</h1>
	</div>
	<form id="reviewForm" method="post">
		<div class="starRating" id="newReviewStars">
			<span dataValue="1">★</span>
			<span dataValue="2">★</span>
			<span dataValue="3">★</span>
			<span dataValue="4">★</span>
			<span dataValue="5">★</span>
		</div>
		<p id="selectedRating">선택한 별점: 0</p>
		<textarea id="reviewText" name="reviewText" placeholder="후기를 작성해주세요..." rows="5" cols="50"></textarea>
		<input type="hidden" id="rating" name="rating" value="0">
		<input type="button" id="addReply" value="등록하기">
	</form>
	<div class="avgStarRating" id="avgReviewStars">
		<span class="avgStarString">평균 별점:</span>
		<div id="avgStarsContainer"></div>
	</div>
	<div id="userReviews">
		<h2>후기들</h2>
		<ul class="myChat">
			<!-- 댓글 목록이 여기에 추가됩니다 -->
		</ul>
		<ul class="allChat">
		
		</ul>
	</div>
	<div class="pagination"></div>
	<jsp:include page="layout/popUpFooter.jsp" />
	<jsp:include page="layout/goodsNavBar.jsp" />
</body>
<script type="text/javascript" src="/resources/goodsJs/gReply.js"></script>
<script type="text/javascript" src="/resources/goodsJs/goodsDetail.js"></script>
<script type="text/javascript" src="/resources/goodsJs/goodsHeader.js"></script>
<script>
    var goodsPrice = ${goods.gprice};
    console.log(goodsPrice);
</script>
</html>