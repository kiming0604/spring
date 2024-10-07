<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>굿즈 스토어 상세 페이지</title>
    <style>
        .goodsDetails {
            width: 100%;
            display: flex;
            justify-content: space-between;
            align-items: flex-start;
        }

        #goodsBanner {
            width: 50%;
            background-color: #f0f0f0;
            text-align: center;
            padding: 20px;
        }

        #goodsInfo {
            width: 45%;
            display: flex;
            flex-direction: column;
            justify-content: flex-start;
        }

        #goodsInfo span {
            display: block;
            margin-bottom: 10px;
        }

        .quantityBar {
            display: flex;
            align-items: center;
            margin: 10px 0;
        }

        .quantityBar button {
            width: 30px;
            height: 30px;
            background-color: #ddd;
            border: none;
            font-size: 18px;
            cursor: pointer;
        }

        #quantity {
            width: 60px;
            text-align: center;
            border: 1px solid #ccc;
            margin: 0 10px;
        }

        .price, .totalPrice {
            font-size: 18px;
            margin-bottom: 10px;
        }

        .actionButtons {
            display: flex;
            gap: 10px; /* 버튼 사이 간격 조정 */
        }

        .actionButtons button {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
            font-size: 16px;
        }

        .actionButtons button.directPurchase {
            background-color: #f44336;
        }

        .goodsDetailImg {
            margin-top: 30px;
        }

        .StarRating span {
            font-size: 30px;
            cursor: pointer;
        }

        .StarRating span:hover,
        .StarRating span.active {
            color: gold;
        }

        #userReviews {
            margin-top: 30px;
        }

        #reviewForm {
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <h1>굿즈 스토어 상세 페이지</h1>
    <p>검색한 상품: ${goodsName}</p>

    <!-- 헤더 포함 -->
    <jsp:include page="layout/popUpHeader.jsp" />

    <div class="goodsDetails">
        <h1 id="goodsBanner">굿즈 배너 이미지</h1>

        <div id="goodsInfo">
            <span id="goodsLike">좋아요</span>
            <span id="goodsName">상품명: ${goodsName}</span>
            <span id="goodsPrice">가격 : 20000</span>
            <span id="goodsDes"> 엄청나게 길고 쓸데없지만 괜히 사고싶게 만드는 설명글 </span>
            <span id="endDate">판매 종료일: 2024-10-10</span>

            <!-- 수량 조정 바 -->
            <div class="quantityBar">
                <button id="decreaseBtn">-</button>
                <input type="text" id="quantity" value="1" readonly />
                <button id="increaseBtn">+</button>
            </div>

            <!-- 총 가격 -->
            <div class="totalPrice">
                총 가격: <span id="totalPrice">20000</span>원
            </div>

            <!-- 장바구니, 바로 결제 버튼 -->
            <div class="actionButtons">
                <button id="addToCart">장바구니 담기</button>
                <button id="directPurchase">바로 결제</button>
            </div>
        </div>
    </div>
    
    <div class="goodsDetailImg">
        <h1>굿즈 상세 이미지</h1>
        <!-- 여기에 굿즈 상세 이미지가 들어갑니다 -->
    </div>
    
    <!-- 리뷰 작성 폼 -->
    <form id="reviewForm" action="/submitReview" method="post">
        <div class="StarRating" id="newReviewStars">
            <span data-value="1">★</span>
            <span data-value="2">★</span>
            <span data-value="3">★</span>
            <span data-value="4">★</span>
            <span data-value="5">★</span>
        </div>
        <p id="selectedRating">선택한 별점: 0</p>
        <textarea id="reviewText" name="reviewText" placeholder="후기를 작성해주세요..." rows="5" cols="50"></textarea>
        <input type="hidden" id="rating" name="rating" value="0">
        <input type="submit" value="등록하기">
    </form>

    <div id="userReviews">
        <h2>내가 남긴 후기</h2>
        <div id="reviewList">
            <!-- 리뷰 목록이 동적으로 여기에 추가됩니다. -->
        </div>
    </div>

    <!-- 푸터 포함 -->
    <jsp:include page="layout/popUpFooter.jsp" />
    <jsp:include page="layout/goodsNavBar.jsp" />
</body>
<script type="text/javascript" src="/resources/goodsJs/goodsDetail.js"></script>
</html>
