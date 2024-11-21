<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>결제 목록</title>
<style>
body {
/* General styles */
body {
    font-family: 'Noto Sans KR', Arial, sans-serif;
    margin: 0;
    padding: 0;
    background-color: #ffe6f0; /* Light background */
    color: #333;
}

/* Navbar styles */
.navbar {
    background-color: #ffffff;
    color: #333;
    padding: 15px 20px;
    border-bottom: 1px solid #e5e5e5;
    display: flex;
    justify-content: space-between;
}

.navbar ul {
    list-style: none;
    margin: 0;
    padding: 0;
    display: flex;
}

.navbar li {
    margin-right: 15px;
}

.navbar a {
    color: #333;
    text-decoration: none;
    font-weight: bold;
    font-size: 14px;
}

.navbar a:hover {
    color: #0078ff;
}

/* Header styles */
.purchase-header {
    text-align: center;
    margin: 20px 0;
    font-size: 24px;
    font-weight: bold;
    color: #333;
}

/* Main content styles */
.purchase-list {
    max-width: 900px;
    margin: 0 auto;
    display: flex;
    flex-direction: column;
    gap: 20px;
    padding: 10px;
}

.purchase-order {
    background: #ffffff;
    border: 1px solid #e5e5e5;
    border-radius: 10px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    padding: 15px;
    position: relative;
}

.order-id-box {
    font-size: 14px;
    font-weight: bold;
    color: #0078ff;
    margin-bottom: 10px;
}

.delivery-info {
    font-size: 14px;
    color: #666;
    margin-bottom: 10px;
}

.purchase-item {
    display: flex;
    align-items: flex-start;
    gap: 15px;
    padding-top: 10px;
}

.item-image img {
    width: 80px;
    height: 80px;
    object-fit: cover;
    border-radius: 8px;
    border: 1px solid #ddd;
}

.item-details {
    flex-grow: 1;
}

.item-details h3 {
    font-size: 16px;
    margin: 0 0 5px;
}

.item-details p {
    font-size: 14px;
    color: #666;
    margin: 2px 0;
}

/* Load more button */
#loadMoreBtn {
    background-color: #0078ff;
    color: white;
    padding: 10px 20px;
    font-size: 14px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    text-align: center;
    display: block;
    margin: 20px auto;
}

#loadMoreBtn:hover {
    background-color: #005fcc;
}

/* Footer styles */
.footer {
    text-align: center;
    padding: 20px;
    font-size: 14px;
    color: #666;
    background-color: #ffffff;
    border-top: 1px solid #e5e5e5;
}
.navbar ul li a {
    line-height: 5; /* 텍스트 줄 간격을 늘려서 아래로 내려줌 */
}


</style>
</head>
<body>
   <div class="container">
      <nav class="navbar">
         <ul>
          <li>
            <img src="/resources/images/mainLogo.png" alt="메인 로고" id="mainLogo">
        </li>
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
