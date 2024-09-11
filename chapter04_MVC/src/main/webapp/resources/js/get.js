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

document.querySelectorAll(".panel-body-btns button").forEach(btn => {
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
		}
	});
});


// --------댓글 관련 스크립트 영역 --------------

const rs = replyService; //reply.js에서 CRUD를 담당하는 객체 reply.js의 replyService 함수

showList();
//댓글 목록 가져오는 함수
function showList() {
   let bno = f.bno.value;
   let replyUL = document.querySelector('.chat')
 
   let msg = '';
   
   rs.getList(bno , function(data) {
	data.forEach(vo => {
		msg += ` <li data-rno="${vo.rno}">`;
		msg +=     '<div>';
		msg +=       ' <div class="chat-header">';
		msg +=            `<strong class="primary-font">${vo.replyer}</strong>`;
		msg +=            `<small class="pull-right">${vo.replyDate}</small>`;
		msg +=      '</div>';
		msg +=      `<p>${vo.reply}</p>`;
		msg +=     '</div>';
		msg += '</li>';
	});
	
	replyUL.innerHTML = msg;
});
   
}







//  json 방식으로 정보 전송
//rs.add({
//    reply: '루돌프? 으음 nono 썰매',
//    replyer: '산타 할아버지가 타고 다니는것은?',
//    bno: f.bno.value
//}, function(result) {
//    alert("result : " + result);
//});

//
//rs.getList(f.bno.value, function(data) {
//	data.forEach(vo => {
//		console.log(vo);
//	});
//});
//rs.remove(7, function(data) {
//    console.log(data)
//});
//let rvo = {
//	    rno: 10,  // 댓글 번호
//	    reply: "댓글이 바뀌는 마법"
//
//}
//rs.update(10, rvo , function(data) {
//console.log(data)
//});
//rs.get(10, function(data) {
//   console.log(data);
//});