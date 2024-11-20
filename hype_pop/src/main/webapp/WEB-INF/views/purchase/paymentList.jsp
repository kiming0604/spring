<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>결제 목록</title>
<style>
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	background-color: #ffe6f0; /* 연한 핑크 배경 */
	color: #333; /* 기본 텍스트 색 */
}

.navbar {
	background-color: #ff66b2; /* 진한 핑크 */
	color: white;
	padding: 10px;
	border-bottom: 3px solid #00bfff; /* 하단 블루 포인트 */
}

.navbar ul {
	list-style-type: none;
	padding: 0;
}

.navbar li {
	display: inline;
	margin-right: 15px;
}

.navbar a {
	color: white;
	text-decoration: none;
	font-weight: bold;
}

.navbar a:hover {
	color: #ffe6f0; /* 연한 핑크로 변경 */
}

.purchase-header {
	text-align: center;
	margin: 20px 0;
	font-size: 24px; /* 글자 크기 */
	font-weight: bold; /* 글자 두껍게 */
	color: #ff66b2; /* 핑크색 */
	border-bottom: 3px solid #00bfff; /* 하단 블루 밑줄 */
	padding-bottom: 10px; /* 하단 여백 */
}

.purchase-list {
	display: flex;
	flex-direction: column;
	align-items: center;
	gap: 10px; /* 항목 간의 간격을 일정하게 유지 */
	width: 80%; /* 상품 목록 div 너비를 80%로 설정 */
	max-width: 1000px; /* 최대 너비 */
	margin: 0 auto; /* 중앙 정렬 */
}

.purchase-order {
	border: 2px solid #ff66b2; /* 핑크 테두리 */
	border-radius: 8px; /* 둥근 테두리 */
	margin-bottom: 10px; /* 주문 간 간격 */
	padding: 10px;
	position: relative;
	width: 100%; /* 주문 항목의 너비를 100%로 설정 */
	max-width: 800px; /* 최대 너비 */
	background-color: #fff5fa; /* 연한 핑크 배경 */
}

.order-id-box {
	position: absolute;
	top: 10px;
	left: 10px;
	font-size: 14px; /* 작은 폰트 크기 */
	background-color: #00bfff; /* 블루 배경 */
	padding: 5px 10px;
	border-radius: 12px;
	font-weight: bold;
	color: white;
}

.purchase-item {
	display: flex;
	gap: 20px;
	padding-top: 20px; /* 상품 항목에 간격 추가 */
}

.item-details {
	flex-grow: 1;
}

.image-payList {
	width: 100px;
	height: 100px;
	background-color: #ffe6f0; /* 연한 핑크 */
	border-radius: 10px;
	background-size: cover;
	background-position: center;
	border: 2px solid #ff66b2; /* 핑크 테두리 */
}

.pagination a {
	margin: 0 5px;
	color: #00bfff; /* 블루 링크 */
	font-weight: bold;
	text-decoration: none;
}

.pagination a:hover {
	color: #ff66b2; /* 핑크로 변경 */
}

#loadMoreBtn {
	background-color: #ff66b2; /* 핑크 */
	color: white; /* 글자 색 */
	padding: 10px 20px; /* 패딩 */
	border: 1px solid #00bfff; /* 블루 테두리 */
	border-radius: 5px; /* 둥근 테두리 */
	font-size: 16px; /* 글자 크기 */
	cursor: pointer; /* 커서 스타일 */
	transition: background-color 0.3s ease, color 0.3s ease; /* 효과를 위한 트랜지션 */
}

#loadMoreBtn:hover {
	background-color: #ff99cc; /* 밝은 핑크 */
	color: white;
}

.footer {
	text-align: center;
	padding: 20px;
	background-color: #ff66b2; /* 핑크 배경 */
	color: white;
	position: relative;
	bottom: 0;
	width: 100%;
}

</style>
</head>
<body>
	<div class="container">
		<nav class="navbar">
			<ul>
			    <li><a href="/hypePop/popUpMain">홈으로</a></li>
				<li><a href="/goodsStore/goodsSearch">굿즈스토어</a></li>
				<li><a href="/member/myPage?userNo=2">마이페이지</a></li>
			
			</ul>
		</nav>
		<br><br><br>
		<header class="purchase-header">
			<h2>결제 목록</h2>
		</header>
		<br><br>

		<section class="purchase-list" id="purchase-list-container">
			<c:set var="previousOrderId" value="" />
			<c:forEach var="item" items="${getPayList}">
				<!-- 주문 번호가 바뀔 때만 새로운 주문 항목을 시작 -->
				<c:if test="${previousOrderId != item.orderId}">
					<div class="purchase-order">
						<div class="order-id-box">주문 번호: ${item.orderId}</div><br><br>
				</c:if>

				<div class="purchase-item">
					<c:if test="${not empty item.gimg}">
						<c:forEach var="img" items="${item.gimg}">
							<div class="image-payList" id="item-${item.gno}" data-file-name="${img.uuid}_${img.fileName}">
								<c:if test="${not empty goods.gname}">
									<img alt="${goods.gname}" id="goodsBannerImg1" />
								</c:if>
							</div>
						</c:forEach>
					</c:if>

					<div class="item-details">
						<h3 class="item-name">상품명: ${item.gname}</h3>
						<p class="item-quantity">수량: ${item.camount} </p>
						<p class="item-price">가격: ${item.gprice * item.camount}원</p>
						<p class="item-date">구매 날짜: ${item.gbuyDate}</p>
						<p class="item-status">상품 현황: ${item.gsituation}</p>
					</div>
				</div>
				<!-- purchase-item div 닫기 -->

				<!-- 주문 번호를 마지막으로 설정하여 다음 항목에서 비교할 수 있게 합니다 -->
				<c:set var="previousOrderId" value="${item.orderId}" />
			</c:forEach>
		</section>

		<button id="loadMoreBtn" data-page="1" style="display: block; margin: 20px auto;">더보기</button>

		 <footer class="footer">
        <p>© 2024 hypePop.</p>
    </footer>
	</div> <!-- container div 끝 -->

	<script type="text/javascript" src="/resources/purchaseJs/paymentList.js"></script>
</body>


</html>
