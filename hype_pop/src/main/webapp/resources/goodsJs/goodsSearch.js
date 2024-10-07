document.querySelectorAll('#popUpName').forEach(option => {
    option.addEventListener('click', (event) => {
        event.preventDefault();

        let popUpStoreName = option.textContent; // 텍스트 가져오기

        console.log(popUpStoreName);

        // GET 방식으로 보내줍니다
        location.href = `/hypePop/popUpDetails?storeName=${encodeURIComponent(popUpStoreName)}`;
    });
});