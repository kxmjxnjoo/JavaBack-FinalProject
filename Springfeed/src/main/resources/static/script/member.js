$(document).ready(function () {  
  $("#loginFrm").submit(function (event) {
    var formData = {
      userid: $("#userid").val(),
      password: $("#password").val(),
      superheroAlias: $("#superheroAlias").val(),
    };

    $.ajax({
      type: "POST",
      url: "/login",
      data: formData,
      dataType: "json",
      encode: true,
    }).done(function (data) {
      if(data == 1){
      location.href="/";
      } else {
      	alert('다시 로그인하세요')
      }
    });

    event.preventDefault();
  });
});