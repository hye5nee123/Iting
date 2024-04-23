const stompClient = new StompJs.Client({
    brokerURL: 'ws://yeacademy.shop/chatserver' // 운영서버 도메인
    // brokerURL: 'ws://15.164.49.2:80/chatserver' // 운영서버 도메인
});

stompClient.activate();

stompClient.onConnect = (frame) => {
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/greetings', (greeting) => {
        showGreeting(JSON.parse(greeting.body).content);
    });
    stompClient.subscribe('/user/topic/message', (greeting) => {
        let vo = JSON.parse(greeting.body);
        Swal.fire({
		  title: "메세지가 도착했습니다. 이동할까요?",
		  icon: "success",
		  showCancelButton: true,
		  confirmButtonColor: "#3085d6",
		  cancelButtonColor: "#d33",
		  confirmButtonText: "확인",
		  cancelButtonText: "취소"
		}).then((result) => {
		  if (result.isConfirmed) {
		  if(vo.userType == 'member'){
	    		location.href = `/member/note/list/${vo.ltNum}`; 
	    	} else {
	    		location.href = `/teacher/note/list/${vo.ltNum}`; 
	    	}
		  }
		});
    });
};