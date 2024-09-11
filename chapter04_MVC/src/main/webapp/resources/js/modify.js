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


function submitForm() {
	if (!f.title.value) {
		alert("제목을 입력하셔야 합니다!");
		f.title.focus();
		return;
	}
	if (!f.content.value) {
		alert("내용이 없다면! 글은 필요가 없어요!");
		f.content.focus();
		return;
	}
	
	f.submit();
	
}


document.querySelectorAll(".panel-body-btns button").forEach(btn => {
	btn.addEventListener('click', (e) => { // 오타 수정
		let type = btn.getAttribute('id');
		
		if (type === 'indexBtn') {
			let pageData = getStorageData();
        	let sendData = `pageNum=${pageData.pageNum}&amount=${pageData.amount}`
		    location.href = "/board/list?" + sendData;
		} else if (type === 'modifyBtn') {
			 f.action = "/board/modify";
			 submitForm();
		}else if (type === 'removeBtn') {
         
			if (confirm("진짜 삭제하시겠습니까?")) {
			
        	  f.action = "/board/remove";
        	  let bnoEle = f.bno;
        	  f.innerHTML = '';
        	  f.appendChild(bnoEle);
        	  f.submit();			
		}		
		}
	});
});