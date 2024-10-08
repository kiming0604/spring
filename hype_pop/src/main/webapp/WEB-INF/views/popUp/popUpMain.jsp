<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="popUpRecommend"> 
    <h1>현재 인기있는 팝업스토어</h1>
      <!-- 슬라이드 컨테이너 -->
    <div class="slider-container">
        <button id="leftArrow" class="arrow" style="display: none;">&#9664;</button> <!-- 왼쪽 화살표, 시작 시 숨김 -->
        <div id="hotPopUpStore" class="slider">
            <c:forEach var="popUp" items="${popUps}" varStatus="status">
                <span class="popUpItem" id="hpStore${status.index + 1}">${popUp.psName}</span>
                <c:if test="${status.index < popUps.size() - 1}">&nbsp;</c:if>
            </c:forEach>
        </div>
        <button id="rightArrow" class="arrow">&#9654;</button> <!-- 오른쪽 화살표 -->
    </div>
    <br>
    <h1>핫한 관심사로 추천!</h1>
    <h2>관심사1</h2> 
    <div id="hotCatPopUpStore1">
        <span id="hcpStore1_1">너무 핫한 팝업스토어1</span> &nbsp; 
        <span id="hcpStore1_2">너무 핫한 팝업스토어2</span> &nbsp; 
        <span id="hcpStore1_3">너무 핫한 팝업스토어3</span> &nbsp;
        <span id="hcpStore1_4">너무 핫한 팝업스토어4</span>
    </div>
    <br>
    <h2>관심사2</h2> 
    <div id="hotCatPopUpStore2">
        <span id="hcpStore2_1">너무 핫한 팝업스토어5</span> &nbsp; 
        <span id="hcpStore2_2">너무 핫한 팝업스토어6</span> &nbsp; 
        <span id="hcpStore2_3">너무 핫한 팝업스토어7</span> &nbsp;
        <span id="hcpStore2_4">너무 핫한 팝업스토어8</span>
    </div>
    <br>
    <h2>관심사3</h2> 
    <div id="hotCatPopUpStore3">
        <span id="hcpStore3_1">너무 핫한 팝업스토어9</span> &nbsp; 
        <span id="hcpStore3_2">너무 핫한 팝업스토어10</span> &nbsp; 
        <span id="hcpStore3_3">너무 핫한 팝업스토어11</span> &nbsp;
        <span id="hcpStore3_4">너무 핫한 팝업스토어12</span>
    </div>
    
</div>
<br>
<br>
<br>
<div id="pupUpStoreMap">
	<h1>
		<span id="MapAPI">지도 API</span>
	</h1>
</div>

