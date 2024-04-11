const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8081/chatserver'
});

stompClient.activate();

stompClient.onConnect = (frame) => {
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/greetings', (greeting) => {
        showGreeting(JSON.parse(greeting.body).content);
    });
    stompClient.subscribe('/user/topic/message', (greeting) => {
        alert(greeting.body);
        location.href = "/member/note/list";
    });
};