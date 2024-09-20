// css 파일 추가
// 1. 파일 경로 설정
const CSS_FILE_PATH = '/resources/css/register.css';
// 2. link 태그 생성
let linkEle = document.createElement('link');
linkEle.rel = 'stylesheet';
linkEle.href = CSS_FILE_PATH;
// 3. head 태그에 link 엘리먼트 추가
document.head.appendChild(linkEle);

// 폼 객체 생성
let f = document.forms[0];

//console.log(f);

document.querySelectorAll('.panel-body-btns button').forEach( a => {
   a.addEventListener('click', e => {
      
      if(e.target.id === 'registerBtn'){
         register();
      }else if(e.target.id === 'removeBtn'){
         f.reset();
      }else if(e.target.id === 'indexBtn'){
         let pageData = getStorageData();
         let sendData = `pageNum=${pageData.pageNum}&amount=${pageData.amount}`;
         location.href = `/board/list?${sendData}`;
      }
   
   })
});

function register(){
   
   if(f.title.value == ''){
      alert("제목을 입력하세요");
      return;
   }
   if(f.writer.value == ''){
      alert("작성자를 입력하세요");
      return;
   }
   if(f.content.value == ''){
      alert("내용을 입력하세요");
      return;
   }
   
   let str = '';
   document.querySelectorAll('.uploadResult ul li').forEach( (li, index) => {
      let path = li.getAttribute('path');
      let uuid = li.getAttribute('uuid');
      let fileName = li.getAttribute('fileName');
      
      str += `<input type="hidden" name="attachList[${index}].uploadPath" value="${path}" />`;
      str += `<input type="hidden" name="attachList[${index}].uuid" value="${uuid}" />`;
      str += `<input type="hidden" name="attachList[${index}].fileName" value="${fileName}" />`;
   });
   
//   f.innerHTML += str;
   f.insertAdjacentHTML('beforeend', str);
   console.log(f);
   
   f.action = '/board/register';
   f.submit();
}