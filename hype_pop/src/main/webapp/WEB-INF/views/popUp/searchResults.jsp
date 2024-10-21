<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>

<body>
  <h1>검색 결과 페이지</h1>
  <p>검색한 데이터: ${searchData}</p>
  <br>
  
  <div class="searchConditions">
    <h3>
      <span id="arrayByDis">거리순</span>
      <span id="arrayByLike">좋아요 순</span>
      <span id="arrayByLatest">최신순</span>
      <span id="arrayByRating">별점순</span>
      <button id="selectInterestsBtn">관심사 선택</button>
    </h3>
  </div>

  <!-- 관심사 종류 버튼들 -->
  <div id="interestButtons" style="display: none;">
    <button class="interestBtn">헬스&뷰티</button>
    <button class="interestBtn">게임</button>
    <button class="interestBtn">문화</button>
    <button class="interestBtn">쇼핑</button>
    <button class="interestBtn">문구</button>
    <button class="interestBtn">키즈</button>
    <button class="interestBtn">디자인</button>
    <button class="interestBtn">식품</button>
    <button class="interestBtn">인테리어</button>
    <button class="interestBtn">정책</button>
    <button class="interestBtn">캐릭터</button>
    <button class="interestBtn">체험</button>
    <button class="interestBtn">콜라보</button>
    <button class="interestBtn">방송</button>
  </div>

  <table class="searchResultStore">
    <tr>
      <td id="popUpStoreImg">
        <h1>팝업스토어 배너 이미지</h1>
      </td>
      <td id="popUpStoreInfo">
        <span id="popUpName">팝업스토어 이름</span>  <span id="likeCount">좋아요 수</span>
        <h3>팝업스토어 주소</h3> <br> <br>
        <h3>
          관심사: <span>관심사 1</span>, <span>관심사 2</span>, <span>관심사 3</span>  
        </h3>
        <h3>
        <%= new SimpleDateFormat("yyyy-MM-dd").format(new Date()) %>   
        </h3>
      </td>
    </tr>
  </table>
