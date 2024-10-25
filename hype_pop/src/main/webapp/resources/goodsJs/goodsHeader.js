document.getElementById('searchBTN').addEventListener('click', function() {
    performSearch();
});

document.getElementById('goodsSearchBox').addEventListener('keyup', function(event) {
    if (event.key === 'Enter') {
        performSearch();
    }
});

function performSearch() {
    const searchText = document.getElementById('goodsSearchBox').value;
    localStorage.setItem('searchText', searchText);
    location.href = `/goodsStore/goodsSearch`;
}


document.querySelectorAll('div').forEach(btn =>{
	btn.addEventListener('click', (e)=>{
		let type = btn.id;
		switch(type){
		case "mainLogoDiv":
			location.href = "/";
			break;
		case "goodsMainLogoDiv":
			location.href = "/goodsStore/goodsMain";
			break;
		case "hamburgerDiv":
		    const menu = document.querySelector("#hamburgerList ul");
		    
		    if (menu.style.display === "block") {
		        menu.style.display = "none";
		    } else {
		        menu.style.display = "block";
		    }
			break;
		// 알림
		case "noticeDiv":
			break;
		
		}
	})
})

document.querySelectorAll('li').forEach(btn => {
	btn.addEventListener('click',(e)=>{
		let type = btn.id;
		switch (type) {
		case "searchPopUp":
			location.href = "/hypePop/search/noData";
			break;
		case "goodsSearch":
			location.href = "/goodsStore/goodsSearch";
			break;
		case "calendar":
			location.href = "/hypePop/calendar";
			break;
		case "support":
			location.href = "/hypePop/customerMain";
			break;
		case "login":
			location.href = "/member/login";
			break;
		case "signIn":
			location.href = "/member/join";
			break;
	}
	})
})