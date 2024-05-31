document.addEventListener('DOMContentLoaded', function() {
    fetch('/get-user-img-id')
        .then(response => response.json())
        .then(data => {
            if (data.userImgId) {
                let userImgId = data.userImgId;
                console.log('User Image ID:', userImgId);
                // 이제 userImgId를 사용하여 필요한 작업을 수행할 수 있습니다.
                var imageUrl = '/file/img/' + userImgId;
                document.getElementById('thumbnail').src = imageUrl;
            } else {
                console.error('Error:', data.error);
            }
        })
        .catch(error => console.error('Fetch error:', error));
});


// 이미지를 업데이트하는 함수
function updateImage() {
    fetch('/get-user-img-id')
        .then(response => response.json())
        .then(data => {
            if (data.userImgId) {
                let userImgId = data.userImgId;
                console.log('User Image ID:', userImgId);
                // 이제 userImgId를 사용하여 필요한 작업을 수행할 수 있습니다.
                var imageUrl = '/file/img/' + userImgId;
                document.getElementById('thumbnail').src = imageUrl;
            } else {
                console.error('Error:', data.error);
            }
        })
        .catch(error => console.error('Fetch error:', error));
}

// 페이지 로드시 이미지 업데이트 함수 호출
document.addEventListener('DOMContentLoaded', updateImage);