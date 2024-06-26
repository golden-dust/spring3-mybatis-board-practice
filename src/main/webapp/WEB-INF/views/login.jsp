<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 로그인</title>
</head>
<body>
	<h2>회원 로그인</h2>
	<hr>
	<h4 style="color: red;">${loginFail}</h4>
	<form action="loginOk">
		아이디 : <input type="text" name="mid"><br><br>
		비밀번호 : <input type="password" name="mpw"><br><br>
		<input type="submit" value="로그인">
	</form>
</body>
</html>