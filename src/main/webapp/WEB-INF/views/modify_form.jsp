<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글수정</title>
<style>
	.readonly {
		border: none;
	}
</style>
</head>
<body>
	<h2>글수정</h2>
	<hr>
	<form action="modifyOk">
		<input type="hidden" name="bnum" value="${post.bnum}">
		<input type="hidden" name="mid" value="${post.mid}">
		<input type="hidden" name="bname" value="${post.bname}">
		<table cellpadding="3">
			<tr>
				<td>아이디</td>
				<td>${post.mid}</td>				
			</tr>
			<tr>
				<td>이름</td>
				<td>${post.bname}</td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="btitle" value="${post.btitle}"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea rows="20" cols="50" name="bcontent">${post.bcontent}</textarea></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" value="취소" onClick="javascript:window.history.back()">
					<input type="submit" value="글수정">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>