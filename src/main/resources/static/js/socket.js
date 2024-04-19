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
        let vo = JSON.parse(greeting.body);
        if(confirm("메세지가 도착했습니다. 이동할까요?")){
        	if(vo.userType == 'member'){
        		location.href = `/member/note/list/${vo.ltNum}`; 
        	} else {
        		location.href = `/teacher/note/list/${vo.ltNum}`; 
        	}
        }
    });
};