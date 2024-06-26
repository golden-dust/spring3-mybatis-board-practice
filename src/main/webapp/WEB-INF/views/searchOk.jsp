<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 조회 결과</title>
</head>
<body>
	<h2>회원 정보</h2>
	<hr>
	<h4 style="color: red;">${failMsg}</h4>
	<p>
		아이디 : ${mDto.mid}<br><br>
		비밀번호 : ${mDto.mpw}<br><br>
		이름 : ${mDto.mname}<br><br>
		이메일 : ${mDto.memail}<br><br>
		가입일 : ${mDto.mdate}<br><br>
	</p>
</body>
</html>