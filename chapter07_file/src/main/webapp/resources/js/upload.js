// 비어 있는 요소 복사
let uploadDiv = document.querySelector('.uploadDiv');
// cloneNode(true) : true -> 하위 노드까지 복사 할 것인지 
let cloneObj = uploadDiv.firstElementChild.cloneNode(true);

const regex = new RegExp("(.*?)\\.(exe|sh|zip|alz)$");
const MAX_SIZE = 5242880; // 5MB

function checkExtension(fileName, fileSize) {
    if (fileSize >= MAX_SIZE) {
        alert("파일 크기가 허벌나게 커버린당게요");
        return false;
    }
    if (regex.test(fileName)) {
        alert("아따.. 고 종류으 파일은 업로드가 안된당게요");
        return false;
    }
    return true;
}

const inputFile = document.querySelector('input[type=file]');
const uploadButton = document.querySelector('#uploadBtn'); // 버튼을 추가하세요.

uploadButton.addEventListener('click', () => { // 버튼 클릭 시 파일 업로드
    const formData = new FormData();
    const files = inputFile.files;

    console.log(files);

    for (let i = 0; i < files.length; i++) {
        const fileName = files[i].name;
        const fileSize = files[i].size;

        if (!checkExtension(fileName, fileSize)) {
            return false;
        }
        formData.append("uploadFile", files[i]);
    }

    fetch('/uploadAsyncAction', {
        method: 'post',
        body: formData
    })
    .then(response => response.json())
    .then(json => {
        console.log(json);
        inputFile.value = ''; // 파일 입력 필드를 비움
        showUploadfile(json); // 업로드된 파일 리스트 보여주기
    })
    .catch(err => console.log(err));
});

let uploadResult = document.querySelector('.uploadResult ul');
function showUploadfile(uploadResultArr) {
    let str = '';

    if (!uploadResultArr || uploadResultArr.length === 0) {
        return;
    }

    uploadResultArr.forEach(file => {
        let fileCallPath = encodeURIComponent(file.uploadPath + "/" + file.uuid + "_" + file.fileName);
        str += `<li path="${file.uploadPath}" uuid="${file.uuid}" fileName="${file.fileName}">`;
        str += `<a>${file.fileName}</a>`;
        str += `<span data-file='${fileCallPath}'> X </span>`; // X 버튼 추가
        str += `</li>`;
    });

    uploadResult.innerHTML = str;
}

uploadResult.addEventListener('click', (e) => {
    if (e.target.tagName === 'SPAN') {
        let targetFile = e.target.getAttribute('data-file');

        fetch('/deleteFile', {
            method: 'post',
            body: targetFile,
            headers: {
                'Content-type': 'text/plain'
            }
        })
        .then(response => response.text())
        .then(result => {
            console.log(result);
            let targetLi = e.target.closest('li');
            targetLi.parentNode.removeChild(targetLi);
        })
        .catch(err => console.log(err));
    }
});
