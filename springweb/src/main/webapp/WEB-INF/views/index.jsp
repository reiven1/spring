<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<section id="content">
	<h2>ajax로 활용하기</h2>
	<button onclick="getBoards()">게시글</button> <br>
	<input type="text" id="userId_">
	<button onclick="getMember()">회원조회</button>
	<div id="container"></div>
	<script>
		const getBoards = async () => {
			const response = await fetch("${pageContext.request.contextPath}/ajax/board");
			if(response.ok){
				const data = await response.json();
				console.log(data);
			}
		}
		const getMember = async () => {
			const userId = userId_.value;
			const response = await fetch("${pageContext.request.contextPath}/ajax/member?userId="+userId);
			if(response.ok){
				const member = await response.json();
				console.log(member);
			}
		}
	</script>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>