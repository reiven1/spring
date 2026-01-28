<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<section id="content">
<style>
table#tbl-dev {
	margin: 0 auto;
	width: 50%;
}
</style>
	<table class="table" id="tbl-dev">
		<tr>
			<th scope="col">아이디</th>
			<td>${member.userId }</td>

		</tr>
		<tr>
			<th>비밀번호</th>
			<td>${member.password}</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>${member.name }</td>
		</tr>
		<tr>
			<th>성별</th>
			<td>${member.gender }</td>
		</tr>
		<tr>
			<th>나이</th>
			<td>${member.age }</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${member.email }</td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td>${member.phone }</td>
		</tr>
		<tr>
			<th>주소</th>
			<td>${member.address }</td>
		</tr>
		<tr>
			<th>취미</th>
			<c:forEach var="hobby" items="${member.hobby }">
				<td>${hobby }</td>
			</c:forEach>
		</tr>
		<tr>
			<th>가입일</th>
			<td>${member.enrollDate }</td>
		</tr>
	</table>
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />