<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글쓰기</title>
<style>
	.readonly {
		border: none;
	}
</style>
</head>
<body>
	<h2>글쓰기</h2>
	<hr>
	<form action="writeOk">
		<input type="hidden" name="mid" value="${mid}">
		<input type="hidden" name="bname" value="${mname}">
		<table cellpadding="3">
			<tr>
				<td>아이디</td>
				<td>${mid}</td>				
			</tr>
			<tr>
				<td>이름</td>
				<td>${mname}</td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="btitle" size="48"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea rows="20" cols="50" name="bcontent"></textarea></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="글쓰기">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>