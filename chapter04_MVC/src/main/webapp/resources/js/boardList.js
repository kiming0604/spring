//-----CSS 파일 추가
// 1. 파일 경로 설정
const CSS_FILE_PATH = '/resources/css/boardList.css';
// 2. link 태그 생성
let linkEle = document.createElement('link');
linkEle.rel = 'stylesheet';
linkEle.href = CSS_FILE_PATH;
// 3. head 태그에 link 엘리먼트 추가
document.head.appendChild(linkEle);


document.querySelectorAll('#registerBtn').forEach(a => {
	a.addEventListener('click' , () => {
		
		event.preventDefault(); 
		
		let btnId = a.id;
		
		console.log(btnId);
		
		
		if (btnId != null && btnId === 'registerBtn') {
			location.href = '/board/register';
		}
	});
});

document.querySelectorAll('tbody a').forEach(a => {
	a.addEventListener('click' , () => {
		
		event.preventDefault(); 
		
		let bno = a.getAttribute("href");
		
		console.log(bno);
		
		location.href = '/board/get?bno=' + bno;
	});
});

let pageNum = new URLSearchParams(location.search).get('pageNum');
let amount = new URLSearchParams(location.search).get('amount');

if (!pageNum || !amount){
	pageNum = 1;
	amount = 5;
}
setStorageData(pageNum, amount);

document.querySelectorAll(".page-nation li a").forEach(a => {
	a.addEventListener('click', e =>{
	e.preventDefault();
	
	pageNum =  a.getAttribute('href');
	setStorageData(pageNum , amount)
	
	let sendData = `pageNum=${pageNum}&amount=${amount}`;
	location.href = `/board/list?${sendData}`;
	
	});
});

