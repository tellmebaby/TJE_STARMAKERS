let page = 0; // page 변수 초기화
const type = 'starCard';
let isLoading = false;


$(document).ready(function () {



// toggleCheckboxClass 함수 정의
function toggleCheckboxClass(checkbox) {
const category = checkbox.attr('id');
if (checkbox.is(':checked')) {
console.log(category + "이 체크되었어");
checkbox.parent().addClass('checked'); // 체크된 경우 부모 요소에 'checked' 클래스 추가
} else {
checkbox.parent().removeClass('checked'); // 체크 해제된 경우 부모 요소에서 'checked' 클래스 제거
}
}


// ID 값을 저장할 배열 초기화
let checkedIds = [];

// 체크박스 이벤트 리스너 설정
document.querySelectorAll('.hide-check').forEach(checkbox => {
    checkbox.addEventListener('change', (event) => {
        const checkboxId = event.target.id; // 체크박스의 ID 값 가져오기
        console.log(`${checkboxId} 체크박스가 ${event.target.checked ? '체크되었습니다.' : '체크 해제되었습니다.'}`);
        toggleCheckboxClass($(event.target));
        
        // 체크되었을 때
        if (event.target.checked) {
            // 배열에 아이디 추가
            checkedIds.push(checkboxId);
        } else { // 체크 해제되었을 때
            // 배열에서 해당 아이디 제거
            checkedIds = checkedIds.filter(id => id !== checkboxId);
        }
        
        // 텍스트 설정 함수 호출
        setTextForTypeTileText(checkedIds.join(', ')); // 배열을 문자열로 변환하여 텍스트 설정 함수 호출
    });
});

// 페이지 로드 시 URL에서 키워드 추출
const urlParams = new URLSearchParams(window.location.search);
const option = urlParams.get('option');
const keyword = urlParams.get('keyword');
console.log('가져온 옵션값이다 : ' + option);


if (option && option.trim() !== "") {
// 해당 카테고리 체크박스를 선택
console.log('옵션값이 있어서 체크박스 선택 실행합니다');
const checkbox = $(`#${option}`);
checkbox.prop('checked', true);
toggleCheckboxClass(checkbox);
}

// 검색어가 있을 경우 초기 검색 수행
if (keyword) {
// 검색어를 검색 입력란에 출력
$('#searchInput').val(keyword);
// 카드 리스트를 불러오는 함수 호출
loadMoreCards(true, keyword);
} else {
// 카드 리스트를 불러오는 함수 호출
loadMoreCards(true);
}

// 체크박스 변경 이벤트
$('input[type="checkbox"]').change(function () {
loadMoreCards(true);
});

// 검색 폼 제출 이벤트
$('#searchForm').submit(function (event) {
event.preventDefault();
const keyword = $('#searchInput').val();
console.log("Search submitted. Keyword:", keyword);
loadMoreCards(true, keyword);
});

// 스크롤 이벤트
$(window).scroll(function () {
if ($(window).scrollTop() + $(window).height() >= $(document).height()) {
loadMoreCards();
}
});



function applyStyles() {
$('.card-text p span').css({
'background-color': '',
'color': '',
'font-size': '',
// 필요한 다른 스타일 속성들도 여기에 추가할 수 있습니다.
});
}

function loadMoreCards(resetPage = false, keyword = '') {
let isLoading = false; // isLoading 변수 초기화

if (isLoading) return;
isLoading = true;

if (resetPage) page = 0;
page += 1;

console.log("Loading more cards...");
console.log("Page:", page);
console.log("Keyword:", keyword);

$.ajax({
url: `/page/starCard/starList/api`,
method: 'GET',
data: {
type: type,
page: page,
eventOngoing: $('#eventOngoing').is(':checked'),
eventExpired: $('#eventExpired').is(':checked'),
eventUpcoming: $('#eventUpcoming').is(':checked'),
instagram: $('#instagram').is(':checked'),
youtube: $('#youtube').is(':checked'),
chzzk: $('#chzzk').is(':checked'),
afreeca: $('#afreeca').is(':checked'),
food: $('#food').is(':checked'),
travel: $('#travel').is(':checked'),
game: $('#game').is(':checked'),
music: $('#music').is(':checked'),
animal: $('#animal').is(':checked'),
workOut: $('#workOut').is(':checked'),
asmr: $('#asmr').is(':checked'),
fashion: $('#fashion').is(':checked'),
keyword: keyword
},  
success: function (data) {
console.log("Data received:", data);

if (resetPage) $('#starList').empty();

data.forEach(star => {
const categories = star.category1.split(',');
const icon1 = categories.includes('afreeca') ? '<img src="/img/icon/afreeca.png" alt="아이콘1" class="content-icon">' : '';
const icon2 = categories.includes('chzzk') ? '<img src="/img/icon/chzzk.png" alt="아이콘2" class="content-icon">' : '';
const icon3 = categories.includes('youtube') ? '<img src="/img/icon/youtube.png" alt="아이콘3" class="content-icon">' : '';
const icon4 = categories.includes('instagram') ? '<img src="/img/icon/instagram.png" alt="아이콘4" class="content-icon">' : '';

const categories2 = star.category2.split(',');
const musicBtn = categories2.includes('music') ? '<a href="#" class="btn btn-custom" data-category="music">#음악</a>' : '';
const travelBtn = categories2.includes('travel') ? '<a href="#" class="btn btn-custom" data-category="travel">#여행</a>' : '';
const foodBtn = categories2.includes('food') ? '<a href="#" class="btn btn-custom" data-category="food">#음식</a>' : '';
const gameBtn = categories2.includes('game') ? '<a href="#" class="btn btn-custom" data-category="game">#게임</a>' : '';
const animalBtn = categories2.includes('animal') ? '<a href="#" class="btn btn-custom" data-category="animal">#동물</a>' : '';
const exerciseBtn = categories2.includes('workOut') ? '<a href="#" class="btn btn-custom" data-category="workOut">#운동</a>' : '';
const fashionBtn = categories2.includes('fashion') ? '<a href="#" class="btn btn-custom" data-category="fashion">#패션</a>' : '';
const asmrBtn = categories2.includes('asmr') ? '<a href="#" class="btn btn-custom" data-category="asmr">#ASMR</a>' : '';

const cardHtml = `
<div class="col-md-2 mb-4">
<div id="card-effect" class="card ${star.card === '유료홍보' ? 'effect' : ''}" data-no="${star.starNo}">
${star.card === '유료홍보' ? '<div class="card-overlay"></div>' : ''}
<div class="card ${star.card === '유료홍보' ? 'prime' : 'standard'}" ondblclick="animateCard(this)">
<div class="card custom-card" style="background-image: url('/file/img/${star.imgNo}');">
<span class="star">&#9733;</span>
<div class="top-container">
<div class="left-content">
${icon1}
${icon2}
${icon3}
${icon4}
</div>
<div class="right-content" data-no="${star.starNo}">
<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-three-dots-vertical" viewBox="0 0 16 16">
<path d="M9.5 13a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0m0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0m0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0"/>
</svg>
</div>
</div> 
<div class="overlay" style="background-image: url('/file/img/${star.imgNo}');"></div>
<div class="card-body">
<h5 class="card-title">
<img src="/file/img/${star.userImgId}" alt="작성자 아이콘" class="author-icon">
${star.title}
</h5>
<div class="card-text">${star.content}</div>
<div class="bottom-container">
<div class="field-links">
${musicBtn}
${travelBtn}
${foodBtn}
${gameBtn}
${animalBtn}
${exerciseBtn}
${fashionBtn}
${asmrBtn}
</div>
<div class="star-links liked">
    <i id="changeStar" class="fa-regular fa-star"></i>
    <span class="count">${star.likes}</span>
</div>
</div>
</div>
</div>
</div>
</div>
</div>
`;
$('#starList').append(cardHtml);

});

 // applyStyles 함수 호출
 applyStyles();
//  $('.card').on('dblclick', likeCard);

isLoading = false;
},
error: function () {
console.error("Failed to load more cards.");
isLoading = false;
}
});
}

// 초기 카드 로드
loadMoreCards();


// 검색 폼 제출 이벤트
$('#searchForm').submit(function (event) {
event.preventDefault();
const keyword = $('#searchInput').val();
console.log("Search submitted. Keyword:", keyword);
loadMoreCards(true, keyword);
});

// 체크박스 변경 이벤트
$('input[type="checkbox"]').change(function () {
loadMoreCards(true);
});


// 클릭 이벤트
$('.type-sub').on('click', function () {
    const checkbox = $(this).find('.hide-check');
    const checkboxId = checkbox.attr('id'); // 체크박스의 ID 값 가져오기
    checkbox.prop('checked', !checkbox.prop('checked'));
    $(this).toggleClass('checked', checkbox.prop('checked'));
    // Trigger the change event to ensure the filtering works correctly
    checkbox.trigger('change');

    // 체크 상태에 따라 배열에 추가 또는 삭제
    if (checkbox.prop('checked')) {
        // 배열에 아이디 추가
        checkedIds.push(checkboxId);
    } else {
        // 배열에서 해당 아이디 제거
        checkedIds = checkedIds.filter(id => id !== checkboxId);
    }

    // 텍스트 설정 함수 호출
    setTextForTypeTileText(checkedIds.join(', ')); // 배열을 문자열로 변환하여 텍스트 설정 함수 호출
});

// a 태그 클릭 시 체크박스 선택 및 change 이벤트 트리거
$(document).on('click', '.btn-custom', function(event) {
event.stopPropagation(); // 이벤트 전파 중지
event.preventDefault();
const category = $(this).data('category');
const checkbox = $(`#${category}`);

if (checkbox.length) {
// 체크박스의 상태 변경
checkbox.prop('checked', !checkbox.prop('checked'));

// 체크박스의 상태에 따라 CSS 클래스 토글
if (checkbox.is(':checked')) {
console.log(category + "이 체크되었어");
checkbox.parent().addClass('checked'); // 체크된 경우 부모 요소에 'checked' 클래스 추가
} else {
checkbox.parent().removeClass('checked'); // 체크 해제된 경우 부모 요소에서 'checked' 클래스 제거
}

// Trigger the change event to ensure the filtering works correctly
checkbox.trigger('change');
}
});


// 카드 공유하기 이벤트
$(document).on({
    mouseenter: function(event) {
    var cardNo = $(this).data('no'); // .right-content의 data-no 속성값 가져오기
    console.log('Card No:', cardNo); // cardNo 값을 콘솔에 출력
    var clipboardIcon = $('<i class="bi bi-clipboard2-plus-fill clipboard-icon"></i>'); // 새로운 클립보드 아이콘 생성
    $(this).find('.bi-three-dots-vertical').hide(); // 기존 아이콘 숨기기
    $(this).append(clipboardIcon); // 새로운 클립보드 아이콘 추가
    $('.clipboard-icon').addClass('rotate-180');
    clipboardIcon.data('cardNo', cardNo); // 클립보드 아이콘에 cardNo 속성 설정
    console.log('Clipboard Icon:', clipboardIcon); // 클립보드 아이콘을 콘솔에 출력
    },
    mouseleave: function() {
    $('.clipboard-icon').remove(); // 모든 클립보드 아이콘 제거
    $(this).find('.bi-three-dots-vertical').show(); // 기존 아이콘 다시 보이기
    }
    }, '.right-content'); 

$(document).on('click', '.right-content', function(event) {
event.stopPropagation(); // 이벤트 전파 중지
var cardNo = $(this).find('.clipboard-icon').data('cardNo'); // 클립보드 아이콘의 cardNo 속성 가져오기
var textArea = document.createElement("textarea");
textArea.value = 'localHost:8080/page/starCard/starRead?starNo=' + cardNo;
document.body.appendChild(textArea);
textArea.select();
document.execCommand('copy');
document.body.removeChild(textArea);
alert('클립보드에 복사되었습니다.');
});
});


function setTextForTypeTileText(text) {
    // .typeTileText 클래스의 모든 요소를 선택합니다.
    var elements = document.querySelectorAll('.typeTileText');

    // 각 요소의 텍스트 값을 설정합니다.
    elements.forEach(function(element) {
        console.log("Setting text:", text); // 콘솔에 설정된 텍스트 출력
        const mapping = {
            'MUSIC': '음악',
            'GAME': '게임',
            'FOOD': '음식',
            'TRAVEL': '여행',
            'ANIMAL': '동물',
            'WORKOUT': '운동',
            'EVENTONGOING': '진행중카드',
            'EVENTEXPIRED': '종료된카드',
            'EVENTUPCOMING': '예정카드',
            'CHZZK': '치지직',
            'AFREECA': '아프리카',
            'ASMR': 'ASMR',
            'FASHION': '패션',
            'INSTAGRAM':'INSTAGRAM',
            'YOUTUBE':'YOUTUBE'
        };

        const newText = Array.from(new Set(text.split(',').map(item => mapping[item.trim().toUpperCase()]).filter(Boolean))).map(item => `#${item}`).join(' '); // 수정된 부분
        element.textContent = newText;
    });

    
}

function animateCard(card) {

    toggleIconClass();

    console.log("더블 클릭 이벤트 발생!");

    const star = card.querySelector('.star');
    star.style.display = 'inline'; // 이모티콘 표시

    // 카드 요소의 위치 및 크기 정보 가져오기
    const rect = card.getBoundingClientRect();

    // 마우스 포인터 위치 계산
    const x = event.clientX - rect.left;
    const y = event.clientY - rect.top;

    // 이모티콘 위치 설정
    star.style.left = `${x}px`;
    star.style.top = `${y}px`;

    // 애니메이션 실행 및 이모티콘 숨김 처리
    star.style.animation = 'burst 0.5s forwards';

    setTimeout(function() {
        star.style.display = 'none'; // 이모티콘 숨김
    }, 500); // 0.5초 (애니메이션의 총 시간)
}


 // 더블클릭 이벤트 핸들러
//  function handleCardDoubleClick(cardElement, event) {
//     var $card = $(cardElement); // 현재 클릭된 카드 요소를 저장

//     clearTimeout(clickTimer); // 기존의 클릭 타이머를 제거하여 단일 클릭을 방지
//     isDoubleClick = true;

//     var userNo = "[[${session.user != null ? session.user.userNo : ''}]]";
//     var starNo = $card.attr("data-no");
//     var csrfToken = "[[${_csrf.token}]]";

//     if (userNo === '') {
//         alert("로그인을 하시오!");
//         return;
//     }

//     // AJAX 요청을 통해 서버에 좋아요 상태를 변경
//     $.ajax({
//         url: '/page/like', // 서버의 좋아요 상태를 변경하는 API 엔드포인트
//         method: 'POST',
//         data: {
//             userNo: userNo,
//             starNo: starNo
//         },
//         beforeSend: function (xhr) {
//             // CSRF 토큰을 요청 헤더에 추가
//             xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
//         },
//         success: function (response) {
//             // 좋아요 아이콘 클래스 토글
//             $card.find('.star-links').toggleClass('liked');
//             // 애니메이션 실행
//             animateCard(cardElement, event);
//         },
//         error: function (xhr, status, error) {
//             console.error("좋아요 상태 변경 실패:", error);
//         }
//     });
// }

// 더블클릭 애니메이션 함수
// function animateCard(card, event) {
//     console.log("더블 클릭 이벤트 발생!");

//     const star = card.querySelector('.star');
//     star.style.display = 'inline'; // 이모티콘 표시

//     // 카드 요소의 위치 및 크기 정보 가져오기
//     const rect = card.getBoundingClientRect();

//     // 마우스 포인터 위치 계산
//     const x = event.clientX - rect.left;
//     const y = event.clientY - rect.top;

//     // 이모티콘 위치 설정
//     star.style.left = `${x}px`;
//     star.style.top = `${y}px`;

//     // 애니메이션 실행 및 이모티콘 숨김 처리
//     star.style.animation = 'burst 0.5s forwards';

//     setTimeout(function() {
//         star.style.display = 'none'; // 이모티콘 숨김
//     }, 500); // 0.5초 (애니메이션의 총 시간)
// }

// // 카드의 더블클릭 이벤트 리스너 설정
// $(document).on('dblclick', '.card', function (e) {
//     handleCardDoubleClick(this, e);

//     // 이벤트 전파 중지 및 기본 동작 방지
//     e.preventDefault();
//     e.stopPropagation();
// });

// 아이콘 클릭 이벤트 리스너 설정
// $(document).on('click', '.star-links i', function (e) {
//     // 아이콘의 클래스 변경
//     $(this).toggleClass('fa-regular fa-solid');

//     // 이벤트 전파 및 기본 동작 방지
//     e.preventDefault();
//     e.stopPropagation();
// });

function toggleIconClass() {
    // 아이콘의 클래스 변경
    $('.star-links i').toggleClass('fa-regular fa-solid');
}

// function likeCard(e) {
//     clearTimeout(clickTimer);
//     isDoubleClick = true;

//     var userNo = "[[${session.user != null ? session.user.userNo : ''}]]";
//     var starNo = $(this).attr("data-no");
//     var csrfToken = "[[${_csrf.token}]]";

//     if (userNo === '') {
//         alert("로그인을 하시오!");
//         return;
//     }

//     $.ajax({
//         url: '/page/like', // 서버의 좋아요 상태를 변경하는 API 엔드포인트
//         method: 'POST',
//         data: {
//             userNo: userNo,
//             starNo: starNo
//         },
//         beforeSend: function (xhr) {
//             // 💍 CSRF 토큰을 요청 헤더에 추가
//             xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
//         },
//         success: function (response) {
//             // 하트 모양 이펙트 추가
//             let heart = $('<div class="heart"></div>');
//             $('body').append(heart);
//             heart.css({
//                 top: e.pageY - 25,
//                 left: e.pageX - 25
//             });

//             setTimeout(() => {
//                 heart.remove();
//             }, 1000);

//             // 좋아요 아이콘 클래스 토글
//             $(this).find('.fa-thumbs-up').toggleClass('fa-regular fa-solid');
//         }.bind(this), // this 바인딩
//         error: function (xhr, status, error) {
//             console.error("좋아요 상태 변경 실패:", error);
//         }
//     });
// }


