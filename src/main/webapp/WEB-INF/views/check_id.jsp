<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복 확인</title>
</head>
<body>
	<%
		String idFlag = request.getAttribute("idFlag").toString();
		if (idFlag.equals("1")) {  // 사용 중인 아이디
	%>
		<script type="text/javascript">
			alert("입력하신 아이디는 이미 존재하는 아이디입니다.\n다른 아이디로 가입해주세요.");
			history.go(-1);
		</script>
	<%
		} else {  // 가입 가능 아이디
	%>
		<script>
			alert("입력하신 아이디는 가입 가능한 아이디입니다.");
			history.go(-1);
		</script>
	<%
		}
	%>
</body>
</html>