/**
 * payment.js
 */

import apiConfig from "../js/apikey.js";
const { iamportApikey } = apiConfig;

const monthTlt = "아이팅 1개월 구독권";
const yearTlt = "아이팅 12개월 구독권";

const subspTypCd = subspMonthlyFrm.subspTypCd.value;
const subspStCd = subspMonthlyFrm.subspStCd.value;
const subspPrice = subspMonthlyFrm.subspPrice.value;

const paymentId =  'iting_1month_' + new Date().getTime();

let subspNum = "";


// 로그인한 회원번호
const memNum = "[[ ${session.usernum} ]]";
//console.log(memNum);


// 결제하기 버튼 클릭 시
payBtn.addEventListener("click", e => {
	if(memNum == "") {
		errorAlert("로그인이 필요합니다.", "");
	} else {
		//reqSubspInfo(memNum); // 0.구독 DB 조회
		
		getBillingKey(memNum); // 1.빌링키 발급
				
		// 3. 구독 DB 등록
		// subspInsertReq(); // 구독 등록 요청
		
		// 4. 결제 DB 등록
		
	}
	
	
})


/* 0.구독 DB 조회 */
// 구독 정보 단건조회 요청
function reqSubspInfo(memNum) {
	axios({
		method : 'get',
		url : "/member/subsp/" + memNum,
	})
	.then(res => resSubspInfo(res))
	
}

//구독 정보 단건조회 응답
function resSubspInfo(res) {
	if(res.data.subspStCd == "구독중"){
		errorAlert("이미 구독중입니다.", "구독상태를 확인해주세요.");
	}
	else {
		console.log("구독정보 없음, 구독 단건 조회", res.data);
		subspInsertReq() // 구독정보 등록
	}
}

// 구독 등록 요청
function subspInsertReq() {
	
	let param = { subspTypCd, subspStCd, subspPrice, memNum }
	
	csrf_axios({
			method: 'POST',
			url: '/member/subsp/insert',
			data: param,
		
			})
			.then(res => subspInsertRes(res.data))
}
// 등록 응답
function subspInsertRes(data) {
	subspNum = data.subspNum;
	console.log('구독 등록 : ', data);
	if(data.retCode == "OK") {
		alert("구독등록완료");
	}
}


/* 1.빌링키 발급 */
// 빌링키 발급 (최초결제)
async function getBillingKey(memNum) {
	const issueResponse = await PortOne.requestIssueBillingKey({
	  storeId: "store-10763d2e-c1c0-48c6-896a-adb1563480ac",
	  channelKey: "channel-key-dcae4478-0c7f-4678-a1ee-d4f875495fe6",
	  billingKeyMethod: "EASY_PAY",
	  issueName: monthTlt
	});
	// 빌링키가 제대로 발급되지 않은 경우 에러 코드가 존재
	if (issueResponse.code != null) {
	  return alert(issueResponse.message);
	}
	
	console.log("=======빌링키======" + issueResponse.billingKey)
	
	// 서버에 빌링키를 전달
	await csrf_axios({
	  method: "POST",
	  url : "/member/sttl/billing",
	  data: {
	    billingKey: issueResponse.billingKey,
	    memNum
	  },
	}).then(res => {
		//console.log(res.data.billingKey)
		if(res.data.billingKey != "") {
			billingPayReq(res.data.billingKey) /* 2.빌링키로 결제 */	
		} else{
			alert("빌링키가 없습니다.");
		}
		
	});
	
}
/* 2.빌링키로 결제 */
// 빌링키 결제 요청
async function billingPayReq() {
	// 포트원 빌링키 결제 API 호출
	const paymentResponse = await csrf_axios(
	  {
	    method: "POST",
	    url : `https://api.portone.io/payments/${paymentId}/billing-key`,
	    headers: {
	      "Authorization": `PortOne ${iamportApikey}`
	    },
	    data: {
	      billingKey : "billing-key-018ecb06-11b1-a11b-b0e9-50f245bb2b19",
	      orderName: monthTlt,
	      amount: {
	        total: subspPrice,
	      },
	      currency: "KRW"
	    },
	  },
	);
	if (!paymentResponse.ok) console.log(paymentResponse);
}


payBtnTest.addEventListener("click", e => {
	billingPayReq();

});


/* 결제 연동 테스트 */
// 결제 요청 (테스트)
async function TestPayRes() {
	const response = await PortOne.requestPayment({
		  // Store ID 설정
		  storeId: "store-10763d2e-c1c0-48c6-896a-adb1563480ac",
		  // 채널 키 설정
		  channelKey: "channel-key-dcae4478-0c7f-4678-a1ee-d4f875495fe6",
		  paymentId: paymentId,
		  orderName: monthTlt,
		  totalAmount: subspPrice,
		  currency: "CURRENCY_KRW",
		  payMethod: "EASY_PAY"
		});
	
	console.log("====결제결과=====" + response);
	
	 if (response.code != null) {
	    // 오류 발생
	    return alert(response.message);
	 }
}