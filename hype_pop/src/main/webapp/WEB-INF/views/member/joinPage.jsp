<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원가입</title>
<!-- CSS 링크 추가 -->
<link rel="stylesheet" type="text/css"
	href="/resources/css/joinPage.css">
</head>
<body>

	<header>
		<a href="/home" class="home-link">홈으로</a>
		<!-- 홈으로 가는 링크 -->
		<h1>회원가입</h1>
	</header>

	<div class="container">
		<form action="join" method="post" onsubmit="return formSubmit()">
			<div class="input-group">
				<label for="userId">아이디</label> <input type="text" id="userId"
					name="userId" placeholder="아이디를 입력하세요.">
				<button type="button" id="duplicateCheckButton"
					onclick="checkUserId()">중복 확인</button>
			</div>
			<!-- 아이디 중복 확인 버튼 -->

			<div class="input-group">
				<label for="password">비밀번호</label> <input type="password"
					id="userPw" name="userPw"  placeholder="비밀번호를 입력하세요.">
			</div>

			<div class="input-group">
				<label for="passwordCheck">비밀번호 확인</label> <input type="password"
					id="passwordCheck" name="passwordCheck" 
					placeholder="비밀번호를 다시 입력하세요.">
			</div>

			<div class="input-group">
				<label for="userEmail">이메일</label> <input type="email"
					id="userEmail" name="userEmail" placeholder="이메일을 입력하세요.">
			</div>

			<div class="input-group">
				<label for="userName">이름</label> <input type="text" id="userName"
					name="userName"  placeholder="이름을 입력하세요.">
			</div>

			<div class="input-group">
				<label for="userNumber">전화번호</label> <input type="tel"
					id="userNumber" name="userNumber"
					placeholder="전화번호를 입력하세요.">
			</div>

			<!-- 관심사 선택 버튼 -->
			<div>
				<label>관심사</label>
				<button type="button" id="interestBtn">관심사 선택</button>
			</div>



			<!-- 모달 창  -->
			<div id="userInterest" name="userInterest" class="userInterest"
				style="display: none;">
				<div class="modal-background" onclick="closeModal()"></div>
				<!-- 배경 클릭 시 모달 닫기 -->
				<div class="interestBoxContainer">
					<!-- 	<input type="checkbox" style="display: none;"> -->
					<div>
						<p>최소 3개 이상 선택하세요.</p>
					</div>
					<!-- 관심사 박스 -->
					<div class="interestBox" data-interest="헬스/뷰티">
						<label for="healthBeauty">
						<input type="checkbox" class="interestBox" name="healthBeauty" value="1"
							style="display: none;"> 헬스/뷰티</label>
					</div>
					<div class="interestBox" data-interest="게임">
						<label for="game"><input type="checkbox" name="game"
							value="1" style="display: none;"> 게임</label>
					</div>
					<div class="interestBox" data-interest="문화">
						<label for="culture"><input type="checkbox" name="culture"
							value="1" style="display: none;"> 문화</label>
					</div>
					<div class="interestBox" data-interest="쇼핑">
						<label for="shopping"><input type="checkbox"
							name="shopping" value="1" style="display: none;"> 쇼핑</label>
					</div>
					<div class="interestBox" data-interest="문구">
						<label for="stationery"><input type="checkbox"
							name="stationery" value="1" style="display: none;"> 문구</label>
					</div>
					<div class="interestBox" data-interest="키즈">
						<label for="kids"><input type="checkbox" name="kids"
							value="1" style="display: none;"> 키즈</label>
					</div>
					<div class="interestBox" data-interest="디자인">
						<label for="design"><input type="checkbox" name="design"
							value="1" style="display: none;"> 디자인</label>
					</div>
					<div class="interestBox" data-interest="식품">
						<label for="food"><input type="checkbox" name="food"
							value="1" style="display: none;"> 식품</label>
					</div>
					<div class="interestBox" data-interest="인테리어">
						<label for="interior"><input type="checkbox"
							name="interior" value="1" style="display: none;"> 인테리어</label>
					</div>
					<div class="interestBox" data-interest="정책">
						<label for="policy"><input type="checkbox" name="policy"
							value="1" style="display: none;"> 정책</label>
					</div>
					<div class="interestBox" data-interest="캐릭터">
						<label for="character"><input type="checkbox"
							name="character" value="1" style="display: none;"> 캐릭터</label>
					</div>
					<div class="interestBox" data-interest="체험">
						<label for="experience"><input type="checkbox"
							name="experience" value="1" style="display: none;"> 체험</label>
					</div>
					<div class="interestBox" data-interest="콜라보">
						<label for="collaboration"><input type="checkbox"
							name="collaboration" value="1" style="display: none;">
							콜라보</label>
					</div>
					<div class="interestBox" data-interest="방송">
						<label for=""broadcast"><input type="checkbox"
							name="broadcast" value="1" style="display: none;"> 방송</label>
					</div>
					<div>
				<button id="confirmButton" class="confirm-button">확인</button>
				</div>
				</div>

			</div>
			

			
			

		<!-- 선택된 관심사 출력 영역 -->
			 <div class="selectedInterests" id="selectedInterests"></div>
		
	
	<!-- 동의 항목 -->
	<div class="agreement">
	    <label>개인정보 처리 방침 동의</label>
	    <label class="toggle-switch">
	        <input type="checkbox" id="privacy" required>
	        <span class="slider"></span>
	    </label>
	    <button class="modal-trigger" onclick="policyModal('privacy')" >&gt;</button>
	</div>
	<div class="agreement">
	    <label>위치 정보 사용 동의 </label>
	    <label class="toggle-switch">
	        <input type="checkbox" id="location" required>
	        <span class="slider"></span>
	    </label>
	    <button class="modal-trigger" onclick="policyModal('location')" >&gt;</button>
	</div>
	<div class="agreement">
	    <label>알림 수신 동의</label>
	    <label class="toggle-switch">
	        <input type="checkbox" id="notification" required>
	        <span class="slider"></span>
	    </label>
	    <button class="modal-trigger" onclick="policyModal('notification')" >&gt;</button>
	</div>
	
	
		<button type="submit" id="joinBtn" onclick="formSubmit()">회원가입 버튼</button>
		</form>
	</div>
	
	
	
	
	<!--동의 내용 모달  -->
	
	<!--개인정보 처리 방침 동의 모달  -->
	<div id="policyModal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closePolicyModal()">&times;</span>
        <div id="modalContent"></div>
    </div>
	</div>
	
	<!--위치기반서비스 동의 모달  -->
	<div id="locationModal" class="modal">
		<div class="modal-content">
			<span class="close" onclick="closeModal()">&times;</span>
			 <div id="modalContent"></div>
	 </div>
	</div>
	
	
	<!--마케팅 알림 수신 동의 안내  -->
	
	<div id="notificationModal" class="modal">
		<div class="modal-content">
			<span class="close" onclick="closeModal()">&times;</span>
			 <div id="modalContent"></div>
			
		</div>
	</div>
	
	



			
		<!-- 동의 안내(style과 클릭이벤트로 동의 안내문 띄우기) -->
		<!-- <div class="agreement">
		    <label>개인정보 처리 방침 동의</label>
		    <label class="toggle-switch">
		        <input type="checkbox" id="privacyPolicy" required>
		        <span class="slider"></span>
		    </label>
		    <span class="modal-trigger" onclick="openModal('privacyPolicyModal')"> &gt; </span>
		</div>
		<div class="agreement">
		    <label>위치 정보 사용 동의</label>
		    <label class="toggle-switch">
		        <input type="checkbox" id="locationPolicy" required>
		        <span class="slider"></span>
		    </label>
		    <span class="modal-trigger" onclick="openModal('locationPolicyModal')"> &gt; </span>
		</div>
		<div class="agreement">
		    <label>알림 수신 동의</label>
		    <label class="toggle-switch">
		        <input type="checkbox" id="notificationPolicy" required>
		        <span class="slider"></span>
		    </label>
		    <span class="modal-trigger" onclick="openModal('notificationPolicyModal')"> &gt; </span>
		</div>
		
		모달창
		<div id="privacyPolicyModal" class="modal">
		    <div class="modal-content">
		        <span class="close" onclick="closeModal('privacyPolicyModal')">&times;</span>
		        <h2>개인정보 처리 방침</h2>
		        <p>여기에 개인정보 처리 방침 내용을 입력하세요.</p>
		    </div>
		</div>
		
		<div id="locationPolicyModal" class="modal">
		    <div class="modal-content">
		        <span class="close" onclick="closeModal('locationPolicyModal')">&times;</span>
		        <h2>위치 정보 사용 방침</h2>
		        <p>여기에 위치 정보 사용 방침 내용을 입력하세요.</p>
		    </div>
		</div>
		
		<div id="notificationPolicyModal" class="modal">
		    <div class="modal-content">
		        <span class="close" onclick="closeModal('notificationPolicyModal')">&times;</span>
		        <h2>알림 수신 방침</h2>
		        <p>여기에 알림 수신 방침 내용을 입력하세요.</p>
		    </div>
		</div>
			 -->

		<script type="text/javascript" src="/resources/memberJs/joinPage.js"></script>
</body>
</html>
