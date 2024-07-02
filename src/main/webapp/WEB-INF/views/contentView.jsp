<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글</title>
<style>
	.medium {
		color: grey;
		font-size: 10pt;
	}
	
	.small {
		color: grey;
		font-size: 8pt;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<h2>게시글</h2>
	<hr>
	${viewFail}
	<table cellpadding="3">
		<tr>
			<td width="300">${post.btitle}</td>
			<td class="small">${post.bdate}</td>
		</tr>
		<tr>
			<td colspan="2" class="small">${post.bname}(${post.mid}) | 조회수 : ${post.bhit}</td>
		</tr>
	</table>
	<hr>
	<table cellpadding="3">
		<tr>
			<td colspan="2">${post.bcontent}</td>
		</tr>
		
		<tr>
			<td colspan="2">
				<form>
					<!-- 
					자바시작 
						String idCheck = request.getAttribute("idCheck").toString();
						if (idCheck.equals("1") 
					자바 끝
					 -->
					<c:if test="${idMatch == 1}">
						<input type="hidden" name="mid" value="${post.mid}">
						<input type="hidden" name="bnum" value="${post.bnum}">
						<input type="submit" value="수정" formaction="modify-post"> 
						<input type="submit" value="삭제" formaction="delete-post"> 
					</c:if>
					<input type="button" value="목록" onClick="window.location.href='boardlist'">
				</form>
			</td>
		</tr>
	</table><br>
	${wrongId}
	<form action="commentOk">
		<input type="hidden" name="bnum" value="${post.bnum}">
		<textarea rows="4" cols="60" name="ctext" placeholder="댓글을 입력해주세요"></textarea> 
		<input type="submit" value="등록">
	</form><br>
	<hr>
	<table cellpadding="3">
		<c:forEach items="${comments}" var="comment">
			<tr>
				<table cellpadding="3">
					<tr>
						<td colspan="3">${comment.ctext}</td>
					</tr>
					<tr>
						<td>
							<span class="medium">${comment.mid}</span>
						</td>
						<td>
							<span class="small">${comment.cdate}</span>
						</td>
						<td>
						<div>
							<%
								String sid = (String) session.getAttribute("sid");
							%>
							<c:if test="${comment.mid eq sid}">
							<form>
								<input type="hidden" name="bnum" value="${post.bnum}">
								<input type="hidden" name="cid" value="${comment.cid}">
								<button class="commentButtons" type="button" name="modifyBtn" id="${comment.cid}">수정</button>  
								<button class="commentButtons" type="submit" formaction="commentDeleteOk">삭제</button>
							</form>
							</c:if>
							<div>
								<table class="modifyForm" style="display: none;">
								<tr>
									<td>
										<form action="commentUpdateOk">
											<input type="hidden" name="bnum" value="${post.bnum}">
											<input type="hidden" name="cid" value="${comment.cid}">
											<textarea name="ctext">${comment.ctext}</textarea>
											<button type="submit">수정</button> 
											<button type="button" name="cancelBtn" class="cancelBtn">취소</button>
										</form>
									</td>
								</tr>	
								</table>	
							</div>
						</div>
						</td>
					</tr>
					
				</table>
			</tr>
		</c:forEach>
	</table>
	<script type="text/javascript">
		$('document').ready ( function() {
			$('.modifyBtn').click(function() {
				$(this).children('table.modifyForm').show();
				}
			});
			
			$('.cancelBtn').click(function() {
				$('.modifyForm').hide();
			});
		} );
		
	</script>
</body>
</html>