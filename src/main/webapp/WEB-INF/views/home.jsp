<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title heres </title>
<script src="/resources/js/jquery-3.7.1.min.js"></script>

<style >
input{
background-color: #f7f8f9;
cursor: pointer;
font-size: 16px; 
border: 1px solid black;
}
#main {
margin-top: 200px; 
}
h2{
font-size: 60px;
}
form{
display: flex;
justify-content: center; /* 수평 중앙 */
align-items: center;     /* 수직 중앙 */
}
#home{
display: flex;
justify-content: center; /* 수평 중앙 */
align-items: center;     /* 수직 중앙 */ 
}
#join {
margin-top: 20px;
display: flex;
justify-content: center; /* 수평 중앙 */
align-items: center;     /* 수직 중앙 */
}
a{
padding: 2px;
border: 1px solid black;
text-decoration: none;
color: black;
background-color:#f7f8f9;
}
</style>
<meta id="_csrf" name="_csrf" content="${_csrf.token}"/>
<meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}"/>
</head>
<body>

<div id="main">

<div id="home">
<h2>
	환영합니다.
</h2>
</div>

<form method="post" action="/login">
<table class="table">
  <tr>
    <th>아이디</th><td><input type="text" name="username"></td>
  </tr>
  <tr>
    <th>비밀번호</th><td><input type="password" name="password"></td>
  </tr>
  <tr align="center">
    <td colspan="2">    
     <input type="submit" value="로그인">
<br>
<span><c:out value="${error}"/></span>
<span><c:out value="${logout}"/></span>
    </td>
  </tr>
</table>
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<div id="join">
<a href="/register">회원 가입</a>
</div>

</div>

</body>
</html>