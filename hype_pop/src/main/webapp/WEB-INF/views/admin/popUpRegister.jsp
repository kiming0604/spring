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
   
   <form method="POST" action="/admin/psRegister" enctype="multipart/form-data" onsubmit="return popStoreRegister(this);">
       <div id="popUpimg" style="cursor: pointer;">팝업스토어 이미지</div>
       <input type="file" id="fileInput" name="imageFile" style="display: none;">
       <div id="uploadedImages"></div>
       
       <div id="psLatitude">위도 <input type="text" name="latitude"></div>
       <div id="psLongitude">경도 <input type="text" name="longitude"></div>
       
       <div id="storeName">팝업스토어 이름 <input type="text" name="psName"></div>
       <div id="cats">팝업스토어 카테고리
          <select id="storeCat" name="psCat" multiple>
              <option value="1">헬스/뷰티</option>
              <option value="1">게임</option>
              <option value="1">문화</option>
              <option value="1">쇼핑</option>
              <option value="1">문구</option>
              <option value="1">키즈</option>
              <option value="1">디자인</option>
              <option value="1">식품</option>
              <option value="1">인테리어</option>
              <option value="1">정책</option>
              <option value="1">캐릭터</option>
              <option value="1">체험</option>
              <option value="1">콜라보</option>
              <option value="1">방송</option>
          </select>
      </div>
       <div id="startDate">시작일 <input type="text" name="psStartDate"></div>
       <div id="endDate">종료일 <input type="text" name="psEndDate"></div>
       <div id="address">주소 <input type="text" name="psAddress"></div>
       <div id="snsAddress">SNS 주소 <input type="text" name="snsAd"></div>
       <div id="company">주최사 정보 <input type="text" name="comInfo"></div>
       <div id="transfer">교통편 <input type="text" name="transInfo"></div>
       <div id="parking">주차장 정보 <input type="text" name="parkinginfo"></div>
       <div id="storeExp">설명글 <input type="text" name="psExp"></div>
       
       <!-- <div id="psRegister">
           <button type="submit" id="psRegisterBtn">등록하기</button>
       </div> -->
   </form>   
   
<script type="text/javascript" src="/resources/adminJs/admin.js"></script>  
<script type="text/javascript" src="/resources/adminJs/adminPopUp.js"></script>  
 
</body>
</html>
