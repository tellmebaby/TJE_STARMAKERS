document.getElementById('chatButton').addEventListener('click', function() {
    this.classList.toggle('open');
});

document.getElementById('chatInput').addEventListener('click', function(event) {
    event.stopPropagation();
});

document.addEventListener('click', function(event) {
    const chatButton = document.getElementById('chatButton');
    if (!chatButton.contains(event.target)) {
        chatButton.classList.remove('open');
    }
});


const token = $("meta[name='_csrf']").attr("content")
const header = $("meta[name='_csrf_header']").attr("content");

$(document).ready(function() {
    $('#messageForm').submit(function(event) {
        event.preventDefault(); // 폼의 기본 제출 동작을 막음

        var formData = {
            content: $('#chatInput').val(),
            code: $('input[name="code"]').val(),
            _csrf: $('#inputCsrf').val()
        };

        console.log('Form data to send:', formData); // 콘솔에 전송 데이터 출력

        $.ajax({
            type: 'POST',
            url: '/message/insertToAdmin',
            contentType: 'application/json',
            data: JSON.stringify(formData),
            beforeSend: function(xhr) {
                xhr.setRequestHeader( header, token); // CSRF 토큰을 헤더에 포함
            },
            success: function(response) {
                console.log('Message sent successfully:', response); // 성공 시 응답 출력
                // 메시지 전송 성공 시 추가 작업 수행
                $('#chatInput').val(''); // 입력창 비우기
            },
            error: function(xhr, status, error) {
                console.error('Error sending message:', status, error); // 에러 시 상태 및 에러 메시지 출력
                console.log(xhr.responseText);
                // 메시지 전송 실패 시 에러 처리
            }
        });
    });
});


$(document).ready(function() {
    const token = $("meta[name='_csrf']").attr("content");
    const header = $("meta[name='_csrf_header']").attr("content");

    function fetchMessages() {
        $.ajax({
            type: 'GET',
            url: '/message/getMessagesByUser', // 서버에서 메시지를 가져오는 엔드포인트
            success: function(response) {
                console.log('Messages fetched successfully:', response);
                renderMessages(response);
            },
            error: function(xhr, status, error) {
                console.error('Error fetching messages:', status, error);
                console.log(xhr.responseText);
            }
        });
    }

    function renderMessages(messages) {
        const chatMessages = $('#chatMessages');
        chatMessages.empty(); // 기존 메시지 비우기

        messages.forEach(function(message) {
            const messageElement = $('<p></p>').text(message.content);
            if (message.code === 'toAdmin') {
                messageElement.css('text-align', 'right');
            } else if (message.code === 'toUser') {
                messageElement.css('text-align', 'left');
            }
            chatMessages.append(messageElement);
        });

        // 스크롤을 가장 아래로 이동
        chatMessages.scrollTop(chatMessages[0].scrollHeight);
    }

    // 10초마다 메시지 가져오기
    setInterval(fetchMessages, 10000);

    // 초기 메시지 로드
    fetchMessages();

    // 메시지 전송
    $('#messageForm').submit(function(event) {
        event.preventDefault(); // 폼의 기본 제출 동작을 막음

        var formData = {
            content: $('#chatInput').val(),
            code: $('input[name="code"]').val(),
            _csrf: $('#inputCsrf').val()
        };

        console.log('Form data to send:', formData); // 콘솔에 전송 데이터 출력

        $.ajax({
            type: 'POST',
            url: '/message/insertToAdmin',
            contentType: 'application/json',
            data: JSON.stringify(formData),
            beforeSend: function(xhr) {
                xhr.setRequestHeader(header, token); // CSRF 토큰을 헤더에 포함
            },
            success: function(response) {
                console.log('Message sent successfully:', response); // 성공 시 응답 출력
                // 메시지 전송 성공 시 추가 작업 수행
                $('#chatInput').val(''); // 입력창 비우기
                
                // 새 메시지 바로 표시
                var newMessage = $('<p></p>').text(formData.content);
                newMessage.css('text-align', 'right'); // 보낸 메시지이므로 오른쪽 정렬
                $('#chatMessages').append(newMessage);
                
                // 스크롤을 가장 아래로 이동
                $('#chatMessages').scrollTop($('#chatMessages')[0].scrollHeight);
            },
            error: function(xhr, status, error) {
                console.error('Error sending message:', status, error); // 에러 시 상태 및 에러 메시지 출력
                console.log(xhr.responseText);
                // 메시지 전송 실패 시 에러 처리
            }
        });
    });
});