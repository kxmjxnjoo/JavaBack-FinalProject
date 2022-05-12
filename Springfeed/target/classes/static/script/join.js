

<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
    function checkId(){
        var id = $('#joinId').val(); //id값이 "id"인 입력란의 값을 저장
        $.ajax({
            url:'/join/idCheck', //Controller에서 인식할 주소
            type:'post', //POST 방식으로 전달
            data:{userid:id},
            success:function(cnt){
                if(cnt != 1) {
                	$('#id_box #id_ok').css("display", "inline-block");
                	$('#id_box #id_already').css("display", "none");
                } else {
                	$('#id_box #id_already').css("display", "inline-block");
                	$('#id_box #id_ok').css("display", "none");
                }
            },
            error:function(){
                alert("에러입니다");
            }
        });
    };
    
    
    function checkPhone(){
        var phone = $('#joinPhone').val(); //id값이 "id"인 입력란의 값을 저장
        $.ajax({
            url:'/join/idCheck', //Controller에서 인식할 주소
            type:'post', //POST 방식으로 전달
            data:{phone:phone},
            success:function(cnt){
                console.log("처리 성공");
                if(cnt != 1) {
                	$('#phone_box #id_ok').css("display", "inline-block");
                	$('#phone_box #id_already').css("display", "none");
                } else {
                	$('#phone_box #id_already').css("display", "inline-block");
                	$('#phone_box #id_ok').css("display", "none");
                }
            },
            error:function(){
                alert("에러입니다");
            }
        });
    };
    
    function checkEmail(){
        var email = $('#joinEmail').val(); //id값이 "id"인 입력란의 값을 저장
        $.ajax({
            url:'/join/idCheck', //Controller에서 인식할 주소
            type:'post', //POST 방식으로 전달
            data:{email:email},
            success:function(cnt){
                console.log("처리 성공");
                if(cnt != 1) {
                	$('#email_box #id_ok').css("display", "inline-block");
                	$('#email_box #id_already').css("display", "none");
                } else {
                	$('#email_box #id_already').css("display", "inline-block");
                	$('#email_box #id_ok').css("display", "none");
                }
            },
            error:function(){
                alert("에러입니다");
            }
        });
    };
    
    function checkPwd(){
        var pwd = $('#joinPwd').val(); //id값이 "id"인 입력란의 값을 저장
        $.ajax({
            url:'/join/idCheck', //Controller에서 인식할 주소
            type:'post', //POST 방식으로 전달
            data:{password:pwd},
            success:function(cnt){
                console.log("처리 성공");
                if(cnt != 1) {
                	$('#pwd_box #id_ok').css("display", "inline-block");
                	$('#pwd_box #id_already').css("display", "none");
                	$('#pwd_box #message').css("display", "none");
                } else {
                	$('#pwd_box #id_already').css("display", "inline-block");
                	$('#pwd_box #message').css("display", "inline-block");
                	$('#pwd_box #id_ok').css("display", "none");
                }
            },
            error:function(){
                alert("에러입니다");
            }
        });
    };
    
    function checkName(){
        var name = $('#joinName').val(); //id값이 "id"인 입력란의 값을 저장
        $.ajax({
            url:'/join/idCheck', //Controller에서 인식할 주소
            type:'post', //POST 방식으로 전달
            data:{name:name},
            success:function(cnt){
                console.log("처리 성공");
                if(cnt != 1) {
                	$('#name_box #id_ok').css("display", "inline-block");
                	$('#name_box #id_already').css("display", "none");
                } else {
                	$('#name_box #id_already').css("display", "inline-block");
                	$('#name_box #id_ok').css("display", "none");
                }
            },
            error:function(){
                alert("에러입니다");
            }
        });
    };
</script>