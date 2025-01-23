const token = $("meta[name='_csrf']").attr("content");
const header = $("meta[name='_csrf_header']").attr("content");

const signupButton = document.querySelector('#signup');
const codecheckButton = document.querySelector('#codecheck');
const idcheckButton = document.querySelector('#idcheck');

let codepass = false;
let codedupass = false;
let idpass = false;
let iddupass = false;
let passwordpass = false;
let namepass = false;

const errMsg = {
     code: { 
    invalid: "6~20자의 영문와 숫자만 사용 가능합니다",
    success: "사용 가능한 코드입니다",
    fail: "사용할 수 없는 코드입니다"
  },
   name: {
	invalid:"1~10자의 한글과 영문만 사용 가능합니다"
  },
  id: { 
    invalid: "영문으로 시작하는 6~20자의 영문와 숫자만 사용 가능합니다",
    success: "사용 가능한 아이디입니다",
    fail: "사용할 수 없는 아이디입니다"
  },
  password: {
	invalid:"4~16자의 영문, 숫자, 특수문자를 모두 포함한 비밀번호를 입력해주세요",
    success: "사용 가능한 비밀번호입니다",
    fail: "사용 가능한 비밀번호입니다"
  },
  signup:{
  fail: "형식에 맞게 입력해주세요"
  }
}

const codeInput = document.querySelector('#code')
const codeError = document.querySelector('.err_code')

codeInput.addEventListener('change', () => {
  const codeRegExp = /^[a-zA-Z0-9]{6,20}$/
  
  if(codeRegExp.test(codeInput.value)) { // 유효성 검사 성공
    codeError.textContent = ""
    codepass = true
    codedupass = false
    console.log(codepass)
  } else { // 유효성 검사 실패
    codeError.textContent = errMsg.code.invalid
    codepass = false
    codedupass = false
    console.log(codepass)
  }
 
});

const nameInput = document.querySelector('#name')
const nameError = document.querySelector('.err_name')

nameInput.addEventListener('change', () => {
  const nameRegExp = /^[a-zA-Zㄱ-ㅎ가-힣]{1,10}$/
  
  if(nameRegExp.test(nameInput.value)) { // 유효성 검사 성공
    nameError.textContent = ""
    namepass = true
    namedupass = false
    
  } else { // 유효성 검사 실패
    nameError.textContent = errMsg.name.invalid
    namepass = false
    namedupass = false
  }
 
});

const idInput = document.querySelector('#id')
const idError = document.querySelector('.err_id')

idInput.addEventListener('change', () => {
  const idRegExp = /^[a-zA-Z]+[a-zA-Z0-9]{5,19}$/
  
  if(idRegExp.test(idInput.value)) { // 유효성 검사 성공
    idError.textContent = ""
    idpass = true
    iddupass = false
  } else { // 유효성 검사 실패
    idError.textContent = errMsg.id.invalid
    idpass = false
    iddupass = false
  }
 
});

const passwordInput = document.querySelector('#password')
const passwordError = document.querySelector('.err_pwd')

passwordInput.addEventListener('change', () => {
  const passwordRegExp = /^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{4,16}$/ // 4~16자의 영문, 숫자, 특수문자를 모두 포함한 비밀번호를 입력해주세요
  if(passwordRegExp.test(passwordInput.value)) { // 유효성 검사 성공
    passwordError.textContent = ""
    passwordpass = true;
  } else { // 유효성 검사 실패
    passwordError.textContent = errMsg.password.invalid
    passwordpass = false;
  }
 
});

codecheckButton.addEventListener('click', () => {

if(codepass==true){

$.ajax({
    url: '/member/codeckeck',
    type : 'POST',
      data : {
            code: $('#code').val()         
        },beforeSend : function(xhr){
		xhr.setRequestHeader(header, token);
	},
    success: function (result) {
        console.log(result);
        if(result==1){
        
        codeError.textContent = errMsg.code.fail ;
        }
        else{
        codeError.textContent = errMsg.code.success;
        codedupass=true
        }
       

    },
    error: function ( err) {
    }
});

}

else{
codeError.textContent = errMsg.code.invalid

}


});

$(document).on("change", "select[name='approvalhierarchy']", function(e) {
        console.log("선택");
        if (e.target.value!=="") { 
       line=e.target.value;
       console.log(line);
         $(this).prop("readOnly", true); 
         addlinecheck="true";
          
      } 
});


idcheckButton.addEventListener('click', () => {

if(idpass==true){

$.ajax({
    url: '/member/idckeck',
    type : 'POST',
      data : {
            id: $('#id').val()         
        },beforeSend : function(xhr){
		xhr.setRequestHeader(header, token);
	} ,         
    success: function (result) {
        console.log(result);
        if(result==1){
        
        idError.textContent = errMsg.id.fail ;
        }
        else{
        idError.textContent = errMsg.id.success;
        iddupass=true
        }
       

    },
    error: function ( err) {
    }
});

}

else{
idError.textContent = errMsg.id.invalid

}

});

const signupError = document.querySelector('.err_signup')

signupButton.addEventListener('click', () => {
    
    event.preventDefault();
    
 if(codepass == true 
     && codedupass == true 
     && idpass == true
     && iddupass == true
     && passwordpass == true
     && namepass == true){   
    
        let data = {
            code: $('#code').val(),
            userName:  $('#name').val(),
            grade:  $('#grade').val(),
            userId:  $('#id').val(),
            userPw:  $('#password').val()
        };
   
$.ajax({
    url: '/member/signup',
    type : 'POST',
      data : JSON.stringify(data) ,
      contentType : "application/json;charset=utf-8",beforeSend : function(xhr){
		xhr.setRequestHeader(header, token);
	} ,
    success: function (result, status, xhr) {
       
        const form = document.getElementById('signupform');
        
        $('input[name=username]').attr('value', $('#id').val()); 
        
        $('input[name=password]').attr('value', $('#password').val()); 
           
        form.submit();
       
    },
    error: function (xhr, status, err) {
    }
});
    }
    
    else{
    console.log(codepass);
    console.log(codedupass);
    console.log(idpass);
    console.log(iddupass);
    console.log(passwordpass);
    console.log(namepass);
    
    signupError.textContent = errMsg.signup.fail
    
    
    }
    
    
    
});