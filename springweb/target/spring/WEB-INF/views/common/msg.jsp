<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<section id="content">
	<script>
		alert("${msg}");
		location.replace("${pageContext.request.contextPath}${loc}");
	</script>
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />