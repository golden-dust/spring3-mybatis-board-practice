<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<body>
	<h2>전체 글 목록</h2>
	<hr>
	<table border="1" cellpadding="3">
		<tr>
			<th>번호</th>
			<th width="400">제목</th>
			<th>아이디</th>
			<th>이름</th>
			<th>조회수</th>
			<th>작성일</th>
		</tr>
		<c:forEach items="${boardList}" var="post">
			<tr>
				<td>${post.bnum}</td>
				<td><a href="post?bnum=${post.bnum}">${post.btitle}</a></td>
				<td>${post.mid}</td>
				<td>${post.bname}</td>
				<td>${post.bhit}</td>
				<td>${post.bdate}</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="6">
				<button value="글쓰기" onClick="window.location.href='write'">글쓰기</button>
			</td>
		</tr>
	</table>
</body>
</html>