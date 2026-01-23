<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<div id="board-container">
	<form name="boardFrm" action="${pageContext.request.contextPath }/board/insertboardend.do" method="post" enctype="multipart/form-data">
		<input type="text" class="form-control" placeholder="제목" name="boardTitle" id="boardTitle" required> 
		<input type="text" class="form-control" placeholder="아이디 (4글자이상)" name="boardWriter" value="${loginMember.userId}" readonly required>
		<div class="input-group mb-e" style="padding: 0px">
			<button class="btn btn-outline-dark" type="button" onclick="addFile()">추가</button>
			<button class="btn btn-outline-danger" type="button" onclick="delFile()">삭제</button>
		</div>
		<div id="basic-file" class="input-group mb-3" style="padding: 0px;">
			<div class="input-group-prepend" style="padding: 0px;">
				<span class="input-group-text">첨부파일1</span>
			</div>
			<div class="custom-file">
				<input type="file" class="custom-file-input" name="upFile" id="upFile1"> 
				<label class="custom-file-label" for="upFile1">파일을 선택하세요</label>
			</div>
		</div>
		<textarea class="form-control" name="boardContent" placeholder="내용" required></textarea>
		<br> 
		<input type="submit" class="btn btn-outline-success" value="저장">
	</form>
</div>

<style>
div#board-container {
	width: 400px;
	margin: 0 auto;
	text-align: center;
}

div#board-container input {
	margin-bottom: 15px;
}
</style>
<script>
	$("input[type=file]").change(e=>{
		const fileName = e.target.files[0].name;
		$(e.target).next().text(fileName);
	});
	
	const [addFile,delFile] = (() => {
		let count = 2;
		const addFile = () => {
			if (count <= 3) {
				const $form = $("#basic-file").clone(true);
				$form.find("span.input-group-text").text(`첨부파일\${count}`);
				$form.find("label.custom-file-label").text("파일을 선택하세요").attr("for", 'upFlie'+count);
				$form.find("input[type='file']").attr("id", 'upFile'+count).val("");
				$("textarea[name='boardContent']").before($form);
				count++;
			} else {
				alert("첨부파일은 3개까지 가능합니다.");
			}
		};
		const delFile = () => {
			if (count!=2) {
				$("textarea[name='boardContent']").prev().remove();
				count--;
			}
		};
		return [addFile,delFile];
	})()
</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />