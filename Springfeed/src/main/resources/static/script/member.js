function loginCheck() {
	let idbox = document.getElementById("userid").value
	let pwdbox = document.getElementById("userpwd").value
				
	if(idbox == "") {
		alert("아이디를 입력해 주세요")
		return false
	}
	if(pwdbox == "") {
		alert("비밀번호를 입력해 주세요")
		return false
	}
	
	return true
	
}	