
// 버튼 클릭 이벤트
document.querySelectorAll('button').forEach(btn => {
    btn.addEventListener('click', e => {
        e.preventDefault();

        let type = btn.getAttribute('id');  // 버튼의 ID 가져오기
        
        
        //비밀번호 변경 버튼(모달)
        if (type === 'newPasswordBtn') {
        	
        	newPasswordModal(); 
            
        	
        //이메일 변경 버튼(모달)
        } else if (type === 'newEmailBtn') {
           
        	
        	newEmailModal();  
        
        //전화번호 변경 버튼(모달)
        } else if (type === 'newPhoneNumberBtn') {
        	
        	newPhoneNumModal();
        	
    	//모달 취소 버튼(모달)	
		} else if (type === 'closeModalBtn') {
			
			closeModal();
		
		//관심사 변경 버튼(모달)
		}else if (type === 'newInterestBtn') {
			
			newInterestModal();

			
		//내 댓글 보기 버튼(페이지)
		}else if (type==='userReplyBtn') {
			
			//location.href = 'member/userReply?userNo=' +userNo ;
			location.href = '/member/userReply';
			
		//장바구니 버튼 클릭(페이지)
		}else if (type === 'goCartBtn') {
			
			location.href = '/member/myCart' ;
		
				
		//내 결제 목록 클릭(페이지)		
		}else if (type === 'paymentListBtn') {
			
			location.href = '/member/paymentList' ;
		
				
		//회원탈퇴 버튼 클릭(confirm)
		}else if (type === 'deleteIdBtn') {
			
			deleteId();
		
	
		}		
				
				
    });
   
});




//------------------------------------마이페이지 관련 스크립트 --------------------------------


function newPasswordModal() {
	
}

function newEmailModal() {
	
}

function newPhoneNumModal() {
	
}

function newInterestModal() {
	
}


function deleteId()	{
	
	const isConfirmed = confirm('회원을 탈퇴하시겠습니까?');
	
	if (isConfirmed) {
       
		alert("회원 탈퇴 요청을 처리합니다...");

	}else {
		
		// 사용자가 "아니오"를 클릭한 경우
		alert("회원 탈퇴가 취소되었습니다.")
	
	}
	
}









