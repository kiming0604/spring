//-----CSS 파일 추가
// 1. 파일 경로 설정
const CSS_FILE_PATH = '/resources/css/register.css';
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
	if (!f.writer.value) {
		alert("작성자가 없어요! 누가 적은걸까요..?");
		f.writer.focus();
		return;
	}
	if (!f.content.value) {
		alert("내용이 없다면! 글은 필요가 없어요!");
		f.content.focus();
		return;
	}
	
	f.submit();
	
}


document.querySelectorAll('button').forEach(a => {
	a.addEventListener('click' , () => {
		 
		
		let btnId = a.id;
		
		console.log(btnId);
		
		switch (btnId) {
		case "registerBtn":
			
			    f.action = "/board/register";
			    submitForm();
			
			break;
			
		case "resetBtn":
			
		    f.reset();
		
		break;
		
		  case "indexBtn":
              let pageData = getStorageData();
             
              console.log(pageData); // pageData 확인
             
              let sendData = `pageNum=${pageData.pageNum}&amount=${pageData.amount}`;
              
              location.href = "/board/list?" + sendData;
              
              break;
		
		}
		
		
	});
});