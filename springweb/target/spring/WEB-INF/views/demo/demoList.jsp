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
	<table class="table">
		<tr>
			<th scope="col">번호</th>
			<th scope="col">이름</th>
			<th scope="col">나이</th>
			<th scope="col">이메일</th>
			<th scope="col">성별</th>
			<th scope="col">개발가능언어</th>
		</tr>
		<c:forEach var="d" items="${demos }">
			<tr>
				<td>${d.devNo }</td>
				<td>${d.devName }</td>
				<td>${d.devAge }</td>
				<td>${d.devGender }</td>
				<td>${d.devEmail }</td>
				<td>
					<c:forEach var="l" items="${d.devLang }">
						<ul>
							<li>${l }</li>						
						</ul>
					</c:forEach>
				</td>
			</tr>
		</c:forEach>
	</table>
	<div>${pagebar }</div>
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />