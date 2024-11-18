document.getElementById('bankTransferButton').addEventListener('click', function () {
    const dropdown = document.getElementById('customDropdown');
    dropdown.classList.toggle('hide'); // 드롭다운 표시/숨기기
});

// 옵션을 선택했을 때
const options = document.querySelectorAll('.dropdown-option');
options.forEach(option => {
    option.addEventListener('click', function () {
        const selectedBank = this.textContent; // 선택한 계좌의 텍스트 값
        document.getElementById('accountName').textContent = selectedBank; // 계좌 표시 업데이트
        document.getElementById('selectedAccount').classList.remove('hide'); // 계좌 표시 영역 보이기
        document.getElementById('customDropdown').classList.add('hide'); // 드롭다운 숨기기
    });
});


//배송지 주소 api
function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("sample6_extraAddress").value = extraAddr;
            
            } else {
                document.getElementById("sample6_extraAddress").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample6_postcode').value = data.zonecode;
            document.getElementById("sample6_address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("sample6_detailAddress").focus();
        }
    }).open();
}



////결제하기 버튼 이벤트
//document.getElementById('kakaopay').addEventListener('click', function() {
//    fetch("/purchase/api/kakaopay.cls", {
//        method: 'POST',
//        headers: {
//            'Content-Type': 'application/json'
//        },
//    })
//    .then(response => response.json()) // JSON 형식으로 응답 처리
//    .then(data => {
//        if(data.status === 'success') { // 응답의 status 확인
//            console.log("카카오페이 결제 성공");
//            alert(data.tid); // tid가 포함되어 있어야 함
//        } else {
//            console.log("카카오페이 결제 실패");
//        }
//    })
//    .catch(err => console.log(err));
//});