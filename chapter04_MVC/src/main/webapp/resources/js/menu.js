// 메뉴의 a태그에 클릭 이벤트
//<a href="boardList">게시판</a>
// 눌려진 객체에서 href 속성 값을 통해 화면 이동
// board/list


document.querySelectorAll('.menu a').forEach(a => {
	a.addEventListener('click' , () => {
		
		event.preventDefault(); 
		
		let hrefValue = a.getAttribute('href'); 
		
		console.log(hrefValue);
		
		
		if (hrefValue === 'boardList') {
			let pageData = getStorageData();
        	let sendData = `pageNum=${pageData.pageNum}&amount=${pageData.amount}`
			
		    location.href = "/board/list?" + sendData;
		}
	});
});

document.querySelectorAll('.header-title a').forEach(a => {
	a.addEventListener('click' , () => {
		
		event.preventDefault(); 
		
		let hrefValue = a.getAttribute('href'); 
		
		console.log(hrefValue);
		
		
		if (hrefValue === 'mainPage') {
			location.href = '/';
		}
	});
});
function setStorageData(pageNum,amount) {
	let pageData = {
		pageNum : pageNum,
		amount : amount
	}
	localStorage.setItem('page_data' , JSON.stringify(pageData));
}

function getStorageData() {
	return JSON.parse(localStorage.getItem('page_data'));
	
}