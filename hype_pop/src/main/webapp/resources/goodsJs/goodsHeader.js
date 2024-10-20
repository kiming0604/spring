document.getElementById('searchBTN').addEventListener('click', function() {
    const searchText = document.getElementById('goodsSearchBox').value;
    location.href = `/goodsStore/goodsSearch?searchText=${searchText}`;
});

//햄버거 메뉴 클릭 이벤트
document.querySelector("#hamburgerBTN").addEventListener('click', (event) => {
    const menu = document.querySelector("#hamburgerList ul");
    
    // 현재 display 스타일을 확인하여 toggle
    if (menu.style.display === "block") {
        menu.style.display = "none"; // 현재가 block이면 none으로 변경
    } else {
        menu.style.display = "block"; // 현재가 block이 아니면 block으로 변경
    }
});

document.querySelector('#goodsMainLogo').addEventListener('click', ()=>{
	location.href = '/goodsStore/goodsMain'
})