// 비어있는 요소 복사
let uploadDiv = document.querySelector('.uploadDiv');
// cloneNode(true) : true => 하위 노드까지 복사
// cloneNode(true) : false => 하위 노드는 복사 X
let cloneObj = uploadDiv.firstElementChild.cloneNode(true);

// file data 검증 과정
const regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
const MAX_SIZE = 5242880; //5MB

function checkFile(fileName, fileSize) {
   if(fileSize >= MAX_SIZE){
      alert("파일 사이즈가 너어무 큽니다.");
      return false;
   }
   if(regex.test(fileName)){
      alert("잘못된 파일 확장자 입니다.");
      return false;
   }
   return true;
}


// upload 버튼 이벤트
document.querySelector('input[type="file"]').addEventListener('change', () => {
   
   const formData = new FormData();
   const inputFile = document.querySelector('input[type=file]');
   const files = inputFile.files;
   
   for(let i=0; i<files.length; i++){
//      if(checkFile(files[i].name, files[i].size) == true){
//         formData.append("uploadFile", files[i]);
//      }
      
      if(!checkFile(files[i].name, files[i].size)){
         return false;
      }
      
      formData.append("uploadFile", files[i]);
   }
   
   fetch('/uploadAsyncAction', {
      method : 'post',
      body : formData
   })
      .then(response => response.json())
      .then(json => {
         console.log(json);
         
//         uploadDiv.replaceChild(cloneObj.cloneNode(true), uploadDiv.firstElementChild);
         // uploadDiv 라는 부모의 자식(Child) 요소를 교체(replace)한다
         // 기존 요소 : firstElementChild / 바꿀 요소 : cloneObj.cloneNode(true)
         //                         cloneNode 가 true 라면 하위의 모든 요소를 변경한다.
         //                                  false 라면 cloneObj 요소 하나만 변경한다.
         inputFile.value = '';
         
         showUploadFile(json);
         
      })
      .catch(err => console.log(err));
});



let uploadResult = document.querySelector('.uploadResult ul');
function showUploadFile(uploadResultArr) {
   
   if(!uploadResultArr || uploadResultArr.length==0) return;
   
   let str = '';
   uploadResultArr.forEach( file => {
      let fileCallPath = encodeURIComponent(file.uploadPath + "/" + file.uuid + "_" + file.fileName);
      
//      str += `<li><a href='/download?fileName=${fileCallPath}'>${file.fileName}</a><span data-file=${fileCallPath}> X </span></li>`;
      str += `<li path="${file.uploadPath}" uuid="${file.uuid}" fileName="${file.fileName}"><a>${file.fileName}</a><span data-file=${fileCallPath}> X </span></li>`;
   });
   
   uploadResult.innerHTML = str;
}

uploadResult.addEventListener('click', e => {
   
   if(e.target.tagName === 'SPAN'){
      let targetFile = e.target.getAttribute('data-file');
      
      fetch('/deleteFile', {
         method : 'post',
         body : targetFile,
         headers : {'Content-type' : 'text/plain'}
      })
         .then(response => response.text())
         .then(result => {
            console.log(result);
            
            // 해당 태그 삭제
            let targetLi = e.target.closest('li');
            targetLi.parentNode.removeChild(targetLi);
         })
         .catch(err => console.log(err));
   }
});