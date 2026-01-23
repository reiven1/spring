<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<div id="board-container">
	<input type="text" class="form-control" placeholder="제목" name="boardTitle" id="boardTitle" required value="${board.boardTitle }"> 
	<input type="text" class="form-control" name="boardWriter" readonly required value="${board.boardWriter }">
	<c:forEach var="a" items="${board.files }">
		<button type="button" class="btn btn-outline-success btn-block" onclick="download('${a.originalFilename}','${a.renamedFilename }')">${a.originalFilename }</button>
	</c:forEach>
	<textarea class="form-control" name="boardContent" placeholder="내용" required>${board.boardContent }</textarea>
</div>
<script>
	function download(oriname, rename) {
		location.assign("${pageContext.request.contextPath}/board/download?ori="+oriname+"&re="+rename);
	}
</script>
<style>
div#board-container {
	width: 400px;
	margin: 0 auto;
	text-align: center;
}

div#board-container input, div#board-container button {
	margin-bottom: 15px;
}

div#board-container label.custom-file-label {
	text-align: left;
}
</style>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />