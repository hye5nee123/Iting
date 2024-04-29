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

// 결제하기 버튼 클릭 시
payBtn.addEventListener("click", e => {
	if(memNum == "") {
		errorAlert("로그인이 필요합니다.");
	} else {
		reqSubspInfo(memNum); // 0.구독 DB 조회 및 구독 DB 등록				
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
	if(res.data.subspStCd == "m1"){
		console.log(res.data.subspStCd)
		errorAlert("이미 구독중입니다.", "구독상태를 확인해주세요.");
	}
	else {
		console.log("구독정보 없음, 구독 단건 조회", res.data);
		
		subspInsertReq() // 1.구독정보 등록
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
	
	if(data.retCode == "OK") {
		console.log("구독등록완료");
		
		getBillingKey(); // 2.빌링키 발급
	}
}


/* 1.빌링키 발급 */
// 빌링키 발급 (최초결제)
async function getBillingKey() {
	const issueResponse = await PortOne.requestIssueBillingKey({
	  storeId: "store-10763d2e-c1c0-48c6-896a-adb1563480ac",
	  channelKey: "channel-key-dcae4478-0c7f-4678-a1ee-d4f875495fe6",
	  billingKeyMethod: "EASY_PAY",
	  issueName: monthTlt
	});
	
	if (issueResponse.code != null) {
	  return alert(issueResponse.message);
	}
	
	// 구독 DB 빌링키 저장
	await csrf_axios({
	  method: "PUT",
	  url : "/member/subsp/billing",
	  data: {
	    billingKey: issueResponse.billingKey,
	    subspNum,
	    memNum
	  },
	}).then(res => {
		console.log(res.data.billingKey)
		if(res.data.billingKey != "") {
			billingPayReq(res.data.billingKey) /* 2.빌링키로 결제 */	
		} else{
			console.log("빌링키가 없습니다.");
		}
		
	});
	
}


/* 2.빌링키로 결제 */
// 빌링키 결제 요청
async function billingPayReq(billingKey) {
	// 포트원 빌링키 결제 API 호출
	const paymentResponse = await csrf_axios(
	  {
	    method: "POST",
	    url : `https://api.portone.io/payments/${paymentId}/billing-key`,
	    headers: {
	      "Authorization": `PortOne ${iamportApikey}`
	    },
	    data: {
	      billingKey,
	      orderName: monthTlt,
	      amount: {
	        total: Number(subspPrice)
	      },
	      currency: "KRW"
	    },
	  },
	);
	if (!paymentResponse.ok) {
		sttlInsertReq(paymentId) // 3.결제 DB에 저장
	};
}


/* 3.결제 DB에 저장 */
// 결제 DB 등록 요청
function sttlInsertReq(payId) {
	console.log("클릭됨")
	// 포트원 결제 단건조회 API 호출
	csrf_axios({
		ethod: "GET",
	    url : `https://api.portone.io/payments/${payId}`,
	    headers: {
	      "Authorization": `PortOne ${iamportApikey}`
	    }
	})
	.then(res => sttlInsetRes(res.data))

}
// 결제 DB 등록 응답
function sttlInsetRes(data) {
	let sttlTypCd = "";
	if(data.method.pgProvider == "KAKAOPAY") {
		sttlTypCd = "o1";
	} else {
		sttlTypCd = "o2";
	}
	
	const sttlDt = data.paidAt.substr(0, 10); // "2024-04-15T02:50:53.579937042Z"
	
	let sttlStCd = "";
	if(data.status == "PAID") {
		sttlStCd = "p1";
	}else {
		sttlStCd = "p2";
	}
	
	const allSttlPrice = data.amount.paid;
	const sttlAccpNum = data.id; // "iting_1month_1713149435799"
	const sttlRnd = 1;
	
	let param = {sttlTypCd, sttlDt, sttlStCd, allSttlPrice, subspNum, memNum, sttlAccpNum, sttlRnd}
	
	csrf_axios({
	  method: "POST",
	  url : "/member/sttl/insert",
	  data: param
	})
	.then(res => {
		if(res.data.sttlNum != "") {
			
			// 최종 결제 완료 시 알림
			Swal.fire({
			  title: "구독 결제가 완료되었습니다.",
			  text: "",
			  icon: "success",
			  showCancelButton: true,
			  confirmButtonColor: "#3085d6",
			  cancelButtonColor: "#d33",
			  confirmButtonText: "수강하기",
			  cancelButtonText: "결제내역"
			}).then((result) => {
			  if (result.isConfirmed) {
			    location.href = "/member/lecture/allList";
			  } else {
			  	location.href = "/member/mypage/sttlList";
			  }
			});
		}
		console.log("결제DB입력값" ,res.data);
	})

}