$(document).ready(function() {
    const token = $("meta[name='_csrf']").attr("content");
    const header = $("meta[name='_csrf_header']").attr("content");

    function fetchMessages() {
        $.ajax({
            type: 'GET',
            url: '/message/getMessagesByUser',
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
        chatMessages.empty();

        messages.forEach(function(message) {
            const messageElement = $('<div class="direct-chat-msg"></div>');
            const messageContent = $('<div class="direct-chat-text"></div>').text(message.content);
            const messageInfo = $('<div class="direct-chat-infos clearfix"></div>');
            const messageTimestamp = $('<span class="direct-chat-timestamp float-right"></span>').text(message.timestamp);

            if (message.code === 'toAdmin') {
                messageElement.addClass('right');
                messageInfo.append($('<span class="direct-chat-name float-right"></span>').text('User'));
                messageElement.append($('<img class="direct-chat-img" src="admin/dist/img/user3-128x128.jpg" alt="message user image">'));
            } else if (message.code === 'toUser') {
                messageElement.addClass('left');
                messageInfo.append($('<span class="direct-chat-name float-left"></span>').text('Admin'));
                messageElement.append($('<img class="direct-chat-img" src="admin/dist/img/user1-128x128.jpg" alt="message user image">'));
            }

            messageInfo.append(messageTimestamp);
            messageElement.append(messageInfo);
            messageElement.append(messageContent);

            chatMessages.append(messageElement);
        });

        chatMessages.scrollTop(chatMessages[0].scrollHeight);
    }

    fetchMessages();

    $('#messageForm').submit(function(event) {
        event.preventDefault();

        var formData = {
            content: $('#chatInput').val(),
            code: $('input[name="code"]').val(),
            _csrf: $('#inputCsrf').val()
        };

        console.log('Form data to send:', formData);

        $.ajax({
            type: 'POST',
            url: '/message/insertToAdmin',
            contentType: 'application/json',
            data: JSON.stringify(formData),
            beforeSend: function(xhr) {
                if (header && token) {
                    console.log("헤더 토큰:", header, token);  // 추가된 로그
                    xhr.setRequestHeader(header, token);
                }
            },
            success: function(response) {
                console.log('Message sent successfully:', response);
                alert("SUCCESS");
                $('#chatInput').val('');

                var newMessage = $('<div class="direct-chat-msg right"></div>');
                var newMessageInfo = $('<div class="direct-chat-infos clearfix"></div>');
                newMessageInfo.append($('<span class="direct-chat-name float-right"></span>').text('User'));
                newMessageInfo.append($('<span class="direct-chat-timestamp float-right"></span>').text(new Date().toLocaleTimeString()));
                newMessage.append($('<img class="direct-chat-img" src="admin/dist/img/user3-128x128.jpg" alt="message user image">'));
                newMessage.append(newMessageInfo);
                newMessage.append($('<div class="direct-chat-text"></div>').text(formData.content));

                $('#chatMessages').append(newMessage);

                $('#chatMessages').scrollTop($('#chatMessages')[0].scrollHeight);
            },
            error: function(xhr, status, error) {
                console.error('Error sending message:', status, error);
                console.log(xhr.responseText);
                alert("FAIL");
            }
        });
    });
});
