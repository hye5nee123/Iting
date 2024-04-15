// 변수 담기
let pwv;
let pwChkv;
let mailv;
let phonev;

// 유효성 검사
let pwAccep = false;
let mailAccep = false;
let phoneAccep = false;
let SMSAccep = false;

function pwCheck() {
	pwv = pw.value;
	pwChkv = pwchk.value;
	let statPw = ``;
	let statPwChk = ``;
	pwAccep = false;
	if (pwv == '') {
		statPw = `<p>비밀번호를 입력해주세요</p>`;
	} else if (!(/^[a-zA-Z0-9]{8,16}$/.test(pwv))) {
		statPw = `<p>영문 대소문자, 숫자조합 8~16자로 입력해주세요</p>`;
	} else if (pwv == pwChkv) {
		statPwChk = `<p>일치</p>`;
		pwAccep = true;
	} else if (pwChkv != '') {
		statPwChk = `<p>비밀번호가 일치하지 않습니다</p>`;
	}
	document.getElementsByClassName('pwchkstr')[0].innerHTML = statPw;
	document.getElementsByClassName('pwchkchkstr')[0].innerHTML = statPwChk;
}
function mailCheck() {
	mailv = email.value;
	let statMail = ``;
	mailAccep = false;
	if (mailv == '') {
		statMail = `<p>이메일을 입력해주세요</p>`;
	} else if (!(/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i.test(mailv))) {
		statMail = `<p>이메일 형식이 맞지 않습니다.</p>`;
	} else {
		mailAccep = true;
	}
	document.getElementsByClassName('mailchkstr')[0].innerHTML = statMail;
}
function phoneCheck() {
	phonev = phone.value;
	let statphone = ``;
	phoneAccep = false;
	if (phonev == '') {
		statphone = `<p>휴대전화번호를 입력해주세요</p>`;
	} else if (!(/^(01[016789]{1})-?[0-9]{3,4}-?[0-9]{4}$/.test(phonev))) {
		statphone = `<p>휴대전화번호 형식이 맞지 않습니다.</p>`;
	} else if (!SMSAccep) {
		statphone = `<p>인증 필요</p>`;
		phoneAccep = true;
	}
	document.getElementsByClassName('phonechkstr')[0].innerHTML = statphone;
}


function chkInput() {
	// 유효성검사
	if(pwFormOn){
		if (pw.value == '') {
			alert("비밀번호를 입력해주세요");
			return;
		}
		if (pwchk.value != pw.value) {
			alert("비밀번호가 일치하지 않습니다.");
			return;
		} else if (!pwAccep) {
			alert("비밀번호가 올바르지 않습니다. 다시 확인해주세요.");
			return;
		}
	}
	/*if (email.value == '') {
		alert("이메일을 입력해주세요");
		return;
	} else if (!mailAccep) {
		alert("이메일 형식이 올바르지 않습니다. 다시 확인해주세요.");
		return;
	}*/
	if(phoneFormOn){
		if (phone.value == '') {
			alert("휴대전화번호를 입력해주세요");
			return;
		} else if (!phoneAccep) {
			alert("휴대전화번호가 올바르지 않습니다. 다시 확인해주세요.");
			return;
		} else if (!SMSAccep) {
			alert("휴대전화번호를 인증해주세요");
			return;
		}
	}
}

function setValue() {
	/*let nickv = nick.value;
	let addrv = sample6_address.value;
	let dadrv = sample6_detailAddress.value;*/

	let intr = '';
	if (actype == 'b1') {
		intr = ocmenu.value;
	}
	let param = {
		idv: id.value,
		pwv: pw.value,
		nickv: nick.value,
		addrv: sample6_address.value,
		dadrv: sample6_detailAddress.value,
		mailv: email.value,
		phonev: phone.value,
		actype: actype,
		intr: intr
	};
}
