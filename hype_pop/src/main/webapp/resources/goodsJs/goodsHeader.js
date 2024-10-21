document.getElementById('searchBTN').addEventListener('click', function() {
    const searchText = document.getElementById('goodsSearchBox').value;
    location.href = `/goodsStore/goodsSearch?searchText=${searchText}`;
});


document.querySelectorAll('div').forEach(btn =>{
   btn.addEventListener('click', (e)=>{
      let type = btn.id;
      console.log(type);
      switch(type){
      case "mainLogoDiv":
         location.href = "/";
         break;
      case "goodsMainLogoDiv":
         location.href = "/goodsStore/goodsMain";
         break;
      case "hamburgerDiv":
          const menu = document.querySelector("#hamburgerList ul");
          
          // 현재 display 스타일을 확인하여 toggle
          if (menu.style.display === "block") {
              menu.style.display = "none"; // 현재가 block이면 none으로 변경
          } else {
              menu.style.display = "block"; // 현재가 block이 아니면 block으로 변경
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
      // ----- 댓글 버튼 관련 스크립트 -----
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