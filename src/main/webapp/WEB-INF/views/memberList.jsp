<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 리스트 조회</title>
</head>
<body>
	<h2>회원 리스트</h2>
	<hr>
	<h4>총 회원수는 ${memberCount}명 입니다.</h4>
	<table border="1" cellspacing="0" cellpadding="3" style="border: 1px dotted lightgrey;">
		<tr>
			<th>번호</th>
			<th>아이디</th>
			<th>이름</th>
			<th>이메일</th>
			<th>가입일</th>
		</tr>
		<c:forEach items="${memberList}" var="member" varStatus="number">
		<tr>
			<td>${number.count}</td>
			<td>${member.mid}</td>
			<td>${member.mname}</td>
			<td>${member.memail}</td>
			<td>${member.mdate}</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>