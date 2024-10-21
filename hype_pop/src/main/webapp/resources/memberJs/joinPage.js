function checkUserId() {
	const f = document.forms[0];
    
	console.log(f);
	
	const userId = f.userId.value;
	
	if (!userId) {
		alert("아이디를 입력하세요!");
		f.userId.focus();
		return;
	}
	
	fetch('/member/api/checkUserId?userId=' + userId)
		.then(response => response.text())
		.then(data => {
			if(data === 'ok'){
				alert('사용가능한 아이디입니다.');
			}else{
				alert('중복된 아이디입니다.');
			}
		})
		.catch(err => console.log(err));
    
//    $.ajax({
//        type: 'post',
//        url: '/member/api/checkUserId',
//        data: { 'userId': userId },
//        success: function(response) {
//            console.log("사용자 ID 확인 성공:", response);
//        },
//        error: function() {
//            alert("에러입니다.");
//        }
//    });
}


//빈 값 검증

function formSubmit(){
	
const f = document.forms[0];	
console.log(f);

const userId = f.userId.value;
const userPw = f.userPw.value;
const passwordCheck = f.passwordCheck.value;
const userEmail = f.userEmail.value;
const userName = f.userName.value;
const userNumber = f.userNumber.value;
const selectedInterests = [];

if (!userId) {
	alert("아이디를 입력하세요!");
	f.userId.focus();
	return;
	}
if (!userPw) {
	alert("비밀번호를 입력하세요!");
	f.userPw.focus();
	return;
	}
if (passwordCheck!== userPw) {
	alert("비밀번호가 같지 않습니다!");
	f.passwordCheck.focus();
	return;
}
if (!userEmail) {
	alert("이메일을 입력하세요!");
	f.userEmail.focus();
	return;
	}
if (!userName) {
	alert("이름을 입력하세요!");
	f.userName.focus();
	return;
	}
if (!userNumber) {
	alert("전화번호를 입력하세요!");
	f.userNumber.focus();
	return;
	}

if (selectedInterests.length === 0) {
    alert('관심사를 선택하세요!'); // 경고 메시지
    return; // 경고 후 종료
}

console.log(selectedInterests.length);

}





// 관심사 선택 버튼 클릭 이벤트
document.getElementById('interestBtn').addEventListener('click', function() {
    const selectedInterests = [];

    // 모달 표시
    const interestModal = document.getElementById('userInterest');
    interestModal.style.display = 'flex'; // Flexbox로 중앙 정렬

    // 각 관심사 박스들에 대한 클릭 이벤트 처리
    document.querySelectorAll('.interestBox').forEach(function(interestBox) {
        interestBox.addEventListener('click', function() {
            const interest = interestBox.getAttribute('data-interest');
            const isSelected = selectedInterests.includes(interest);

            if (!isSelected) {
                selectedInterests.push(interest);
                interestBox.classList.add('selectedBox'); // 선택된 스타일 클래스 추가
            } else {
                const index = selectedInterests.indexOf(interest);
                selectedInterests.splice(index, 1);
                interestBox.classList.remove('selectedBox'); // 선택 해제 시 클래스 제거
            }
            updateInterestDisplay();
        });
    });

    // 선택한 관심사 업데이트 함수
    function updateInterestDisplay() {
        const interestsContainer = document.getElementById('selectedInterests');
        interestsContainer.innerHTML = ''; // 기존 내용을 비우고 새로 추가

        selectedInterests.forEach(function(interest) {
            const selectedBox = document.createElement('div');
            selectedBox.className = 'selectedInterestBox'; // 스타일 클래스 추가
            selectedBox.innerText = interest; // 관심사 이름 추가
            interestsContainer.appendChild(selectedBox); // 컨테이너에 추가
        });
    }
    

    // 모달 닫기 함수
    function closeModal() {
        interestModal.style.display = 'none'; // 모달 숨기기
    }

    // 확인 버튼 클릭 이벤트
    document.getElementById('confirmButton').addEventListener('click', function() {
        
    
    	
    	if (selectedInterests.length < 3) {
            alert('최소 3개 이상 선택하세요');
            return; // 경고 후 종료
        }

        // 선택된 관심사를 숨겨진 input 필드에 추가
//        const interestsInput = document.createElement('input');
//        interestsInput.type = 'hidden';
//        interestsInput.name = 'userInterest'; // 서버에서 받을 때 사용할 이름
//        interestsInput.value = selectedInterests.join(','); // 선택된 관심사들을 문자열로 변환
//        document.querySelector('form').appendChild(interestsInput); // 폼에 추가
        
        closeModal(); // 모달 닫기
    });
});

//비동기로 개인정보 처리방침  띄우기

//모달 열기
function openPolicyModal() {
	
	const modalBox= document.getElementById('policyModal');

	if (modalBox) {
		modalBox.style.display = "block";
	} else {
		console.error('ID에 해당하는 모달 요소를 찾을 수 없습니다');
	}
}

//모달 닫기
function closePolicyModal() {
	
	const modalBox= document.getElementById('policyModal');

	if (modalBox) {
		modalBox.style.display = "none";
	} else {
		console.error('ID에 해당하는 모달 요소를 찾을 수 없습니다');
	}
}

function policyModal(type) {
    console.log("type: " + type);

    // REST Controller로 보내기
    fetch(`/member/api/getPolicyContent?type=${type}`, {
        method: 'GET',
        headers: {
            'Accept': 'application/json; charset=UTF-8' // 서버에 JSON 응답을 요청
        }
    })
    
        .then(response => {
            // 서버 응답이 ok일 때만 데이터 처리
            if (!response.ok) {
                throw new Error("네트워크 응답에 문제가 있습니다");
            }
            return response.text();
        })
        .then(data => {
            // 모달 내용 업데이트
        	console.log(data);
            document.getElementById('modalContent').innerHTML = data;
            openPolicyModal();
        })
        .catch(error => {
            console.error('데이터를 가져오는 중 오류가 발생했습니다:', error);
            document.getElementById('modalContent').innerHTML = "내용을 불러오는 데 실패했습니다.";
        });
}














//style이랑 클릭이벤트로 개인정보 처리방침  띄우기

//function openModal(modalId) {
//    document.getElementById(modalId).style.display = "block";
//}
//
//function closeModal(modalId) {
//    document.getElementById(modalId).style.display = "none";
//}
//
//// 모달 클릭 시 닫기
//window.onclick = function(event) {
//    var modals = document.getElementsByClassName('modal');
//    for (var i = 0; i < modals.length; i++) {
//        if (event.target == modals[i]) {
//            modals[i].style.display = "none";
//        }
//    }
//}