<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<style>
table#tbl-dev {
	margin: 0 auto;
	width: 50%;
}
</style>
<section id="content">
	${sessionScope.teacher }
	<table class="table" id="tbl-dev">
		<tr>
			<th scope="col">이름</th>
			<td>${demo.devName }</td>

		</tr>
		<tr>
			<th>나이</th>
			<td>${demo.devAge}</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${demo.devEmail }</td>
		</tr>
		<tr>
			<th>성별</th>
			<td>${demo.gender }</td>
		</tr>
		<tr>
			<th>개발가능언어</th>
			<td>
				<ul>
					<c:forEach var="l" items="${demo.devLang }">
						<li>${l }</li>
					</c:forEach>
				</ul>
			</td>
		</tr>
	</table>
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />