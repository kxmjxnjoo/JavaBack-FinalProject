
function loginCheck(){
	var formdata = new FormData($('#loginFrm')[0]);
	var userid = document.getElementById("userid").value;
	var password = document.getElementById("password").value;
	formdata.append("userid", userid);
	formdata.append("password", password);
	
    $.ajax({
        url:'/login', 
        type:'post', 
        data:formdata,
        timeout: 10000,
        contentType : false,
        processData : false,
        
        success:function(cnt){
            if(cnt != 1) {
            	console.log("cnt = " + cnt);
            } else {
            	console.log("cnt = 1");
            	location.href = "/";
            }
        },
        error:function(){
            alert("에러입니다");
        }
    });
}; 

