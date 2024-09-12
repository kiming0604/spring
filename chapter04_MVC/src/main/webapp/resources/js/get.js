//-----CSS 파일 추가
// 1. 파일 경로 설정
const CSS_FILE_PATH = '/resources/css/get.css';
// 2. link 태그 생성
let linkEle = document.createElement('link');
linkEle.rel = 'stylesheet';
linkEle.href = CSS_FILE_PATH;
// 3. head 태그에 link 엘리먼트 추가
document.head.appendChild(linkEle);

const f = document.forms[0];

document.querySelectorAll("button").forEach(btn => {
	btn.addEventListener('click', (e) => { // 오타 수정
		let type = btn.getAttribute('id');
		
		if (type === 'indexBtn') {
			let pageData = getStorageData();
        	let sendData = `pageNum=${pageData.pageNum}&amount=${pageData.amount}`
			
		    location.href = "/board/list?" + sendData;
		} else if (type === 'modifyBtn') {
			let bno = f.bno.value;
			
			console.log(bno);
			
			location.href = '/board/modify?bno=' + bno;
		}else if (type === 'replyBtn') {
			resisterModalPage();
		}else if (type === 'closeModalBtn') {
			closeModal();
		}else if (type === 'addReplyBtn') {
			registerReply();
		}else if (type === 'modifyReplyBtn') {
			modifyReply();
		}else if (type === 'removeReplyBtn') {
			removeReply();
		}
	});
});


// --------댓글 관련 스크립트 영역 --------------

const rs = replyService; // reply.js에서 CRUD를 담당하는 객체 reply.js의 replyService
							// 함수

showList();
// 댓글 목록 가져오는 함수
function showList() {
   let bno = f.bno.value;
   let replyUL = document.querySelector('.chat')
 
   let msg = '';
   
   rs.getList(bno , function(data) {
	data.forEach(vo => {
		msg += ` <li data-rno="${vo.rno}" onclick="modifyModalPage(this)">`;
		msg +=     '<div>';
		msg +=       ' <div class="chat-header">';
		msg +=            `<strong class="primary-font">${vo.replyer}</strong>`;
		msg +=            '<small class="pull-right">' + displayTime(vo.replyDate)+'</small>';
		msg +=      '</div>';
		msg +=      `<p>${vo.reply}</p>`;
		msg +=     '</div>';
		msg +=     `<input type="hidden" data-rno="${vo.rno}" 
									     data-reply="${vo.reply}" 
									     data-replyer="${vo.replyer}" 
									     data-date="${vo.replyDate}"> `;
		
		msg += '</li>';
	});
	
	replyUL.innerHTML = msg;
});
   
}

function displayTime(unixTimeStamp) {
	let myDate = new Date(unixTimeStamp);
	
	let y = myDate.getFullYear();       // 패드스타트를 이용해서 2자리로 만들고 부족한 부분은 0으로 채우는 형식
	let m = String(myDate.getMonth()+1).padStart(2, '0');
	let d = String(myDate.getDate()).padStart(2,'0');
	
	let date = `${y}-${m}-${d}`
		return date;
}


// ------------------ 모달 관련 스크립트 ------------------

const modal = document.querySelector('#modal');
const inputReply = document.querySelector('input[name="reply"]');
const inputReplyer = document.querySelector('input[name="replyer"]');
const inputReplydate = document.querySelector('input[name="replydate"]');
const addyReplyBtn = document.querySelector('#addReplyBtn');
const modifyReplyBtn = document.querySelector('#modifyReplyBtn');
const removeReplyBtn = document.querySelector('#removeReplyBtn');
const closeModalBtn = document.querySelector('#closeModalBtn');

function openModal() {
	modal.style.display = "block";
}
function closeModal() {
	modal.style.display = "none";
	
}
// 댓글 등록 창 함수
function resisterModalPage() {
	
	// 보여질 목록 수정
	 regReplyModalStyle();

	// input 입력창 내용 초기화
	 inputReply.value = '';
	 inputReplyer.value = '';
	
	
	openModal();

	
	
}
// 등록창 스타일 변경 함수
function regReplyModalStyle() {
	addyReplyBtn.classList.remove("hide");
	modifyReplyBtn.classList.add("hide");
	removeReplyBtn.classList.add("hide");
	           // 클로시스트로 부모 찾기
	inputReplydate.closest('div').classList.add("hide");
	inputReplyer.removeAttribute('readonly');

	
}
// 댓글 등록 실행 함수
function registerReply() {
	 
	if (inputReply.value == '' || inputReplyer.value == '') {
		alert('내용을 입력해 주세용');
		return;
	}
	
	 rs.add(
			 {
	            reply: inputReply.value,
	            replyer: inputReplyer.value,
	            bno: f.bno.value
	 }, function(result) {
	 console.log("result : " + result);
	 closeModal();
	 showList();
	
	 }
   );

}

let rno;
let rvo ={};
// 댓글 클릭시 나오는 이벤트
function modifyModalPage(li) {
    modifyReplyModalStyle();
    
    // li 요소 안의 input[type="hidden"] 요소 가져오기
    let hiddenInput = li.querySelector('input[type="hidden"]');
    
    // 각 데이터를 가져와주고
    rno = hiddenInput.getAttribute('data-rno');
    let reply = hiddenInput.getAttribute('data-reply');
    let replyer = hiddenInput.getAttribute('data-replyer');
                          // 지금 이 상태의 data-date는 String일것이기 때문에 넘버로 형변환 해준다 
    let date = displayTime(Number(hiddenInput.getAttribute('data-date')));  
    // date 값을 displayTime 함수로 변환시켜주면

    // 콘솔에 변환된 값이 출력 된다
    console.log(rno, reply, replyer, date);
    
    inputReply.value = reply;
    inputReplyer.value = replyer;
    inputReplydate.value = date;
  
    
    
    openModal();
}


// 댓글 클릭시 나오는 모달 스타일 변경 함수
function modifyReplyModalStyle() {
	modifyReplyBtn.classList.remove("hide");
	removeReplyBtn.classList.remove("hide");
	addyReplyBtn.classList.add("hide");
	inputReplydate.closest('div').classList.remove("hide");
	// 리드 온리를 부여해서 수정이 안되게 해야함
	inputReplyer.setAttribute('readonly' , true);
	inputReplydate.setAttribute('readonly' , true);
}
// 실제 수정 함수
function modifyReply() {
 // 수정할 내용 필수 입력 검증
    if (!inputReply.value) {
        alert('댓글을 입력해 주세용');
        return;
    }

    // rvo에 새롭게 입력된 댓글 내용 업데이트
      rvo.rno = rno;
      rvo.reply = inputReply.value;
      

    // 실제 update 기능 실행
    rs.update(rvo, function(data) {
        console.log(data);
        // update 성공시 댓글 리스트 출력, 모달 창 닫기
        showList();
        closeModal();
    });
}
function removeReply() {

    if (confirm('댓글을 삭제하시겠습니까?')) {
        rs.remove(rno, function(data) {
            console.log(data);
            // 삭제 작업이 완료된 후 댓글 목록을 업데이트
            showList();
            closeModal();
        });
    }
}


// json 방식으로 정보 전송
// rs.add({
// reply: '루돌프? 으음 nono 썰매',
// replyer: '산타 할아버지가 타고 다니는것은?',
// bno: f.bno.value
// }, function(result) {
// alert("result : " + result);
// });

//
// rs.getList(f.bno.value, function(data) {
// data.forEach(vo => {
// console.log(vo);
// });
// });
// rs.remove(7, function(data) {
// console.log(data)
// });
// let rvo = {
// rno: 10, // 댓글 번호
// reply: "댓글이 바뀌는 마법"
//
// }
// rs.update(10, rvo , function(data) {
// console.log(data)
// });
// rs.get(10, function(data) {
// console.log(data);
// });
