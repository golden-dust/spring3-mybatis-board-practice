<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 탈퇴</title>
</head>
<body>
	<h2>회원 탈퇴</h2>
	<hr>
	<form action="deleteOk">
		아이디 &nbsp;&nbsp;&nbsp;: <input type="text" name="mid"><br>
		비밀번호 : <input type="password" name="mpw"> 
		<input type="submit" value="탈퇴">
	</form>
	<h4 style="color: red;">${deleteFail}</h4>
</body>
</html>