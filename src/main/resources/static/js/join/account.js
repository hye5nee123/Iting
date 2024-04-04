let accode = ''; // 넣을 코드 데이터 값
		usertypeForm(1); // 첫 화면 폼 그려주기

		let allboxs = document.querySelectorAll('.chk');
		let chkdboxs = document.querySelectorAll('.chk:checked');
		for(box of allboxs){
			box.addEventListener('click', () => {
				if(allboxs.length != chkdboxs.length){
					document.querySelector('#chk_all').checked = false;
				}
			});
		}
		chk_all.addEventListener('click', () => {
			for(obj of allboxs){
				if(chk_all.checked){
					obj.checked = true;
				} else if(!chk_all.checked) {
					obj.checked = false;
				}
			}
		});
		function usertypeForm(num) {
			let form;
			document.getElementsByClassName('radios')[0].innerHTML = ''; // 바뀔때 비워주기용
			if (num == '1') {
				actype = 'b1';
				form = `<div class="checkout__input">
					<p>관심사</p>
					<input type="text" id="intr" name="intr" readonly>
					<button type="button" class="site-btn">관심사 선택</button>
					</div>`;
			}
			else if (num == '2') {
				actype = 'd2';
				form = `<div class="checkout__input">
					<p>이력서 첨부 <span>*</span></p>
					</div>
					<input type="file" accept=".pdf, .xlsx, .hwp" id="file" name="file"/>`;
			}
			document.getElementsByClassName('radios')[0].innerHTML = form;
		}


		// 유효성 검사
		id.addEventListener('blur', ()=>{
			let idstat;
    			if(id.value == ''){
					idstat = `<p>아이디를 입력해주세요</p>`;
				} else if(!(/^[a-zA-Z0-9]{4,20}$/.test(id.value))){
					idstat = `<p>영문 대소문자, 숫자조합 4~20자로 입력해주세요</p>`;
				} else if(axios.get("/idchk", id.value)
								.then(res => res.data) != null){
					idstat = `<p>사용가능한 아이디입니다</p>`;
				}
				document.getElementsByClassName('idchk')[0].innerHTML = idstat;
		});
		function insertAccount(){
			// /^[a-zA-Z0-9]{4,20}$/ 아이디 정규식
			// /^[a-zA-Z0-9]{8,16}/[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]$/g 비밀번호 정규식
			// ^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i 이메일 정규식
			// 유효성검사
			
			if(pw.value == ''){
				alert("비밀번호를 입력해주세요");
				return;
			}
			if(email.value == ''){
				alert("이메일을 입력해주세요");
				return;
			}
			if(phone.value == ''){
				alert("연락처를 입력해주세요");
				return;
			}
			// if(pwchk.value != pw.value){
			// 	alert("비밀번호가 일치하지 않습니다");
			// 	return;
			// }
			let agrees = document.querySelectorAll('.svc:checked');
			if(agrees.length != 2){
				alert("필수 약관에 동의해야합니다.");
				return;
			}
		}