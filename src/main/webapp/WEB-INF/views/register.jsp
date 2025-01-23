<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script src="/resources/js/jquery-3.7.1.min.js"></script>
<style>
button{
border: 0;
background-color: #f7f8f9;
cursor: pointer;
font-size: 16px; 
border: 1px solid black;
}
a{
text-decoration: none;
color: black;
font-size: 16px; 
border: 1px solid black;
background-color: #f7f8f9;
}
input{
background-color: #f7f8f9;
width: 250px;
}
label{
display: block;
width: 70px;
}
#signup{
cursor: pointer;
font-size: 16px; 
border: 1px solid black;
width: 100px;
}
#main{
width: 500px;
height: 580px;
margin: 0 auto;
margin-top: 100px;
border: 1px solid black;
}
#input{
padding-left: 100px;   
}
#register{
display: flex;
justify-content: center; /* 수평 중앙 */
align-items: center;     /* 수직 중앙 */
gap: 8px;
}
.err_signup{
display: flex;
justify-content: center; /* 수평 중앙 */
}

</style>
 
<meta id="_csrf" name="_csrf" content="${_csrf.token}"/>
<meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}"/>

</head>
<body>
<div id="main">
  <div id="input">
       <p>
        <label for="code">회사코드</label>
        <input type="text" id="code" placeholder="6~20자의 영문와 숫자">
        <button type="button" id="codecheck">중복확인</button> 
        <br>
        <span class="err_code"></span>
      </p>

      <select id="grade">   
      <c:forEach var="grade" items="${gradelist}">
     	<option value="${grade.gradeName}">${grade.gradeName}</option>
       </c:forEach>
      </select>
        <br>
       <span class="err_grade"></span>
        
      <p>
        <label for="name">이름</label>
        <input type="text" id="name" placeholder="1~10자의 한글과 영문">
        <br>
        <span class="err_name"></span>
      </p>
     
      <p>
        <label for="id">아이디</label>
        <input type="text" id="id" placeholder="영문으로 시작하는 6~20자의 영문과 숫자">
        <button type="button" id="idcheck">중복확인</button> 
        <br>
        <span class="err_id"></span>
      </p>
      
      <p>
        <label for="password">비밀번호</label>
        <input type="text" id="password" placeholder="4~16자의 영문,숫자,특수문자 포함"> 
        <br>
        <span class="err_pwd"></span>
      </p>
   
   </div>

   <div id="register">
 
        <form method="post" action="/login" id="signupform">
 
       <input type="hidden" name="username">
       <input type="hidden" name="password">
       <input type="submit" value="회원가입" id="signup">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
      
        </form>
       
       <a href="/">취소</a>
   </div>
        <br>
        <span class="err_signup"></span>
</div>

<script src ="/resources/js/register.js"></script>
</body>
</html>