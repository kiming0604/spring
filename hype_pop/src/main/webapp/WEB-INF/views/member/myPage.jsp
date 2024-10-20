<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        /* Header */
        header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px 20px;
            background-color: #eee;
            border-bottom: 2px solid #ccc;
        }

        header .home-btn, header .search-btn {
            background-color: #f5a9bc;
            border: none;
            padding: 10px;
            cursor: pointer;
        }

        header .search-bar {
            flex-grow: 1;
            margin: 0 20px;
        }

        header .search-bar input {
            width: 100%;
            padding: 10px;
            border: none;
            border-bottom: 2px solid #ccc;
            outline: none;
        }

        /* Form Layout */
        .form-section {
            width: 60%;
            margin: 20px auto;
            font-size: 14px;
        }

        .form-section h2 {
            text-align: center;
            margin-bottom: 20px;
            font-size: 24px;
        }

        .form-item {
            margin-bottom: 20px;
            position: relative;
        }

        .form-item label {
            display: block;
            font-size: 14px;
            margin-bottom: 5px;
        }

        .form-item input {
            width: 100%;
            padding: 5px 0;
            border: none;
            border-bottom: 2px solid #ccc;
            outline: none;
        }

        .form-item button {
            position: absolute;
            top: 30px;
            right: 0;
            padding: 5px 10px;
            background-color: #ccc;
            border: none;
            cursor: pointer;
            font-size: 12px;
        }

        .comment-btn {
            text-align: right;
            margin: 20px 0;
        }

        /* Image Grid for Stores and Goods */
        .image-grid {
            display: grid;
            grid-template-columns: repeat(4, 1fr);
            gap: 10px;
            margin: 20px 0;
        }

        .image-item {
            position: relative;
            text-align: center;
        }

        .image-item img {
            width: 100%;
            height: auto;
            cursor: pointer;
        }

        .image-item button {
            position: absolute;
            top: 5px;
            right: 5px;
            background-color: red;
            color: white;
            border: none;
            cursor: pointer;
        }

        /* Button section */
        .btn-section {
            display: flex;
            justify-content: space-between;
            margin: 30px 0;
        }

        /* Footer */
        footer {
            text-align: center;
            margin: 40px 0;
        }

        /* Navigation Bar */
        nav {
            position: fixed;
            bottom: 0;
            left: 0;
            width: 100%;
            background-color: #ccc;
            text-align: center;
        }

        nav a {
            margin: 10px;
            text-decoration: none;
            color: black;
        }
    </style>

</head>
<body>
<header>
        <button class="home-btn">홈</button>
        <div class="search-bar">
            <input type="text" placeholder="검색">
        </div>
        <button class="search-btn">검색</button>
    </header>

    <div class="form-section">
        <h2>마이페이지</h2>
        
        <div class="form-item">
            <label for="name">이름</label>
            <input type="text" id="name" placeholder="이름을 입력하세요">
        </div>
        
        <div class="form-item">
            <label for="id">아이디</label>
            <input type="text" id="id" placeholder="아이디를 입력하세요">
        </div>
        
        <div class="form-item">
            <label for="password">비밀번호</label>
            <input type="password" id="password" placeholder="비밀번호를 입력하세요">
            <button type="button" class="btn btn-sec" id="newPasswordBtn">비밀번호 변경</button>
        </div>
        
        <div class="form-item">
            <label for="email">이메일</label>
            <input type="email" id="email" placeholder="이메일을 입력하세요">
            <button type="button" class="btn btn-sec" id="newEmailBtn">이메일 변경</button>

        </div>
        
        <div class="form-item">
            <label for="phone">전화번호</label>
            <input type="text" id="phone" placeholder="전화번호를 입력하세요">
            <button type="button" class="btn btn-sec" id="newPhoneNumberBtn">전화번호 변경</button>
        </div>
        
        <div class="form-item">
            <label for="interest">관심사</label>
            <input type="text" id="interest" placeholder="관심사를 입력하세요">
            <button type="button" class="btn btn-sec" id="newInterestBtn">관심사 변경</button>

        </div>

        <div class="comment-btn">
            <button type="button" class="btn btn-sec" id="userReplyBtn">내 댓글 보기</button>
        </div>
    </div>

    <div class="form-section">
        <h3>좋아요한 팝업스토어</h3>
        <div class="image-grid">
            <div class="image-item">
                <img src="popup1.jpg" alt="팝업스토어1" onclick="goToPopupDetail(1)">
                <button onclick="removePopup(1)">X</button>
            </div>
            <div class="image-item">
                <img src="popup2.jpg" alt="팝업스토어2" onclick="goToPopupDetail(2)">
                <button onclick="removePopup(2)">X</button>
            </div>
            <div class="image-item">
                <img src="popup3.jpg" alt="팝업스토어3" onclick="goToPopupDetail(3)">
                <button onclick="removePopup(3)">X</button>
            </div>
            <div class="image-item">
                <img src="popup4.jpg" alt="팝업스토어4" onclick="goToPopupDetail(4)">
                <button onclick="removePopup(4)">X</button>
            </div>
        </div>
        
        <h3>좋아요한 굿즈</h3>
        <div class="image-grid">
            <div class="image-item">
                <img src="goods1.jpg" alt="굿즈1" onclick="goToGoodsDetail(1)">
                <button onclick="removeGoods(1)">X</button>
            </div>
            <div class="image-item">
                <img src="goods2.jpg" alt="굿즈2" onclick="goToGoodsDetail(2)">
                <button onclick="removeGoods(2)">X</button>
            </div>
            <div class="image-item">
                <img src="goods3.jpg" alt="굿즈3" onclick="goToGoodsDetail(3)">
                <button onclick="removeGoods(3)">X</button>
            </div>
            <div class="image-item">
                <img src="goods4.jpg" alt="굿즈4" onclick="goToGoodsDetail(4)">
                <button onclick="removeGoods(4)">X</button>
            </div>
        </div>
    </div>

    <div class="btn-section">
        <button type="button" class="btn btn-sec" id="goCartBtn">장바구니</button>
        <button type="button" class="btn btn-sec" id="paymentListBtn">내 결제 목록</button>
        <button type="button" class="btn btn-sec" id="deleteIdBtn" style="background-color: red; color: white;">회원 탈퇴</button>
    </div>

    <footer>footer</footer>

    <nav>
        <a href="#">팝업스토어 검색</a>
        <a href="#">굿즈 검색</a>
        <a href="#">내 주변</a>
        <a href="#">캘린더</a>
        <a href="#">로그인</a>
    </nav>

   
<!--     <script>
        function goToPopupDetail(id) {
            // 팝업스토어 상세 페이지로 이동
            window.location.href = "/popupDetail?id=" + id;
        }

        function goToGoodsDetail(id) {
            // 굿즈 상세 페이지로 이동
            window.location.href = "/goodsDetail?id=" + id;
        }

        function removePopup(id) {
            // 팝업스토어 좋아요 삭제 로직 구현
            alert("팝업스토어 " + id + " 삭제됨");
        }

        function removeGoods(id) {
            // 굿즈 좋아요 삭제 로직 구현
            alert("굿즈 " + id + " 삭제됨");
        }
    </script> -->
    
     <script type="text/javascript" src="/resources/memberJs/myPage.js"></script>   
    
    
</body>

</html>
