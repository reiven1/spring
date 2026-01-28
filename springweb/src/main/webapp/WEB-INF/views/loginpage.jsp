<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<section id="content">
	<div class="modal-content">
		<div class="modal-header">
			<h5 class="modal-title" id="loginModalLabel">로그인</h5>
		</div>
		<form action="${pageContext.request.contextPath }/loginend.do" method="post">
		<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
			<div class="modal-body">
				<input type="text" name="userId" class="form-control" placeholder="아이디입력" required><br> 
				<input type="password" name="password" class="form-control" placeholder="패스워드입력" required>
			</div>
			<div class="modal-footer">
				<button type="submit" class="btn btn-outline-success">로그인</button>
				<button type="button" class="btn btn-outline-success" data-dismiss="modal">취소</button>
			</div>
		</form>
	</div>
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />