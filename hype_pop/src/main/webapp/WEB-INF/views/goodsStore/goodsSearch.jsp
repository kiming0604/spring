<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Goods Search Result</title>
<style type="text/css">
/* 전체 레이아웃 */
body {
    margin: 0;
    font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
    background-color: #141414;
    color: #fff;
}

/* 네비게이션 바 */
#popUpHeader {
    background-color: #141414;
    padding: 10px 20px;
    display: flex;
    justify-content: space-between;
    align-items: center;
}

#popUpHeader h1 {
    font-size: 24px;
    color: #e50914;
    font-weight: bold;
    text-transform: uppercase;
}

#popUpHeader nav {
    display: flex;
    gap: 15px;
}

#popUpHeader a {
    color: #fff;
    text-decoration: none;
    font-weight: 500;
    font-size: 16px;
}

#popUpHeader a:hover {
    color: #e50914;
}

/* 검색 결과 섹션 */
#goodsSearchResult {
    padding: 20px;
}

.searchCategory {
    display: flex;
    gap: 15px;
    margin-bottom: 20px;
}

.searchCategory span {
    cursor: pointer;
    font-size: 18px;
    padding: 8px 12px;
    border-radius: 5px;
    background-color: #333;
    transition: background-color 0.3s ease;
}

.searchCategory span:hover {
    background-color: #e50914;
}

/* 굿즈 컨테이너: 한 줄에 2개의 아이템이 배치되도록 설정 */
.goodsContainer {
    display: grid;
    grid-template-columns: repeat(2, 1fr); /* 한 줄에 두 개씩 */
    gap: 20px;
}

/* 굿즈 개별 카드 */
.goodsResult {
    background-color: #222;
    display: flex; /* 이미지를 왼쪽에, 텍스트 정보를 오른쪽에 배치하기 위해 flexbox 사용 */
    padding: 15px;
    border-radius: 10px;
    transition: transform 0.3s ease, background-color 0.3s ease;
    align-items: center;
}

.goodsResult:hover {
    transform: scale(1.05);
    background-color: #333;
}

/* 이미지 스타일 */
.goodsImg {
    width: 150px; /* 이미지 너비 */
    height: 150px; /* 이미지 높이 */
    background-color: #fff; /* 이미지 자리 표시용 배경색 */
    margin-right: 20px;
    border-radius: 10px;
    object-fit: cover; /* 이미지가 영역을 넘어가지 않도록 설정 */
}

/* 텍스트 정보 */
.goodsInfo {
    flex-grow: 1; /* 텍스트 정보가 남은 공간을 채우도록 설정 */
}

.goodsName, .goodsPrice, .goodsExp, .goodsSellDate, .goodsLike {
    margin: 10px 0;
}

.goodsLike, .goodsPrice {
    font-size: 16px;
    font-weight: bold;
}

.goodsName {
    font-size: 18px;
}

.goodsExp {
    font-size: 14px;
    color: #999;
}

.goodsSellDate {
    font-size: 12px;
    color: #666;
}

/* 더보기 버튼 */
#loadMoreBtn {
    display: block;
    margin: 30px auto;
    padding: 10px 20px;
    background-color: #e50914;
    border: none;
    color: white;
    font-size: 16px;
    cursor: pointer;
    border-radius: 5px;
    transition: background-color 0.3s ease;
}

#loadMoreBtn:hover {
    background-color: #f40612;
}

/* 푸터 */
#popUpFooter {
    background-color: #141414;
    padding: 20px;
    text-align: center;
    color: #999;
}

#popUpFooter a {
    color: #fff;
    text-decoration: none;
    margin: 0 10px;
}

#popUpFooter a:hover {
    color: #e50914;
}
</style>
</head>
<body>
	<jsp:include page="layout/popUpHeader.jsp" />
	
<div id="goodsSearchResult">
	<div class="searchCategory">
		<span id="priceHigh">가격 높은순</span>
		<span id="priceLow">가격 낮은순</span>
		<span id="likeHigh">좋아요순</span>
		<span id="replyHigh">후기 많은순</span>
		<span id="newDate">최신순</span>
		<span id="selectCat">관심사 선택하여 검색</span>
	</div>
    <div class="goodsContainer" id="goodsContainer">
        <c:forEach var="vo" items="${searchList}">
            <div class="goodsResult">
                <div class="goodsImg">굿즈 이미지</div>
                <div class="goodsInfo">
                    <input type="hidden" value="${vo.gno}">
                    <div class="goodsLike">좋아요: ${vo.likeCount}</div>
                    <div class="goodsName">상품명: ${vo.gname}</div>
                    <div class="goodsPrice">가격: ${vo.gprice} 원</div>
                    <div class="goodsExp">설명: ${vo.gexp}</div>
                    <div class="goodsSellDate">판매종료일 : ${vo.sellDate}</div>
                    <div class="goodsCategory">굿즈 관심사:
                        <c:if test="${vo.gcat.healthBeauty == 1}">healthBeauty</c:if>
                        <c:if test="${vo.gcat.game == 1}">game</c:if>
                        <!-- 중략 -->
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <button id="loadMoreBtn">더보기</button>
</div>

	<jsp:include page="layout/popUpFooter.jsp" />
	<jsp:include page="layout/goodsNavBar.jsp" />
</body>
<script type="text/javascript" src="/resources/goodsJs/goodsHeader.js"></script>
<script type="text/javascript" src="/resources/goodsJs/goodsSearch.js"></script>
<script type="text/javascript">
    let searchText = "${searchText != null ? searchText : ''}";
</script>
</html>