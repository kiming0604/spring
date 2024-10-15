<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 팝업스토어 배너 및 좋아요 수 -->
<div class="popUpbanner">
    <table>
        <tr>
            <td>
                <h1>
                    <span id="bannerImg">팝업스토어 배너</span>
                </h1>
            </td>
            <td><span id="totalLikeCount">좋아요 수: ${storeInfo.likeCount}</span></td>
        </tr>
    </table>
</div>

<!-- 팝업스토어 정보 -->
<table class="popUpStoreInfo">
    <tr>
        <td id="popUpStoreInfo">
            <span id="popUpName">${storeInfo.psName}</span>
            <span id="likeCount">좋아요</span>
            <h3 id="category">
                관심사: <span>${storeInfo.interest}</span>
            </h3>
            <h3 id="popUpStoreAdd">${storeInfo.psAddress}</h3>
        </td>
    </tr>
    <tr>
        <td>
            <h3>${storeInfo.psExp}</h3>
        </td>
    </tr>
</table>

<!-- 팝업스토어 위치 지도 -->
<div id="popUpMap">팝업스토어가 포커스되어있는 지도</div>

<!-- 기타 정보 -->
<div id="popUpETCInfo">
    <h3>주최사 정보: ${storeInfo.comInfo}</h3>
    <h3>교통편: ${storeInfo.transInfo}</h3>
    <h3>주차 가능 여부: ${storeInfo.parkingInfo}</h3>
    <h3>팝업스토어 SNS 주소: ${storeInfo.snsAd}</h3>
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
    <textarea id="reviewText" name="reviewText" placeholder="후기를 작성해주세요..." rows="5"></textarea>
    <input type="hidden" id="rating" name="rating" value="0">
    <input type="hidden" id="psNo" name="psNo" value="${storeInfo.psNo}">
    <input type="hidden" id="userNo" name="userNo" value="5">
    <input type="button" value="등록하기" onclick="send(this.form)">
</form>

<!-- 내가 남긴 후기 -->
<div id="userReviews">
    <h2>내가 남긴 후기</h2>
    <div id="reviewList">
        <!-- 동적으로 리뷰 목록 추가 -->
    </div>
</div>

<!-- 인기 상품 배너 -->
<table class="hitGoods">
    <tr>
        <td><span id="popUpGoods1">인기상품 배너1</span></td>
        <td><span id="popUpGoods2">인기상품 배너2</span></td>
        <td><span id="popUpGoods3">인기상품 배너3</span></td>
    </tr>
    <tr>
        <td id="popUpGoodsInfo1">엄청난 굿즈 1 30000원</td>
        <td id="popUpGoodsInfo2">엄청난 굿즈 2 40000원</td>
        <td id="popUpGoodsInfo3">많이 비싼 굿즈 3 40000원</td>
    </tr>
</table>
