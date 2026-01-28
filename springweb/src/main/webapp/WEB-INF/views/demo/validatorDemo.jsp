<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="springform" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<%-- <jsp:include page="/WEB-INF/vies/common/header.jsp"/> --%>
<style>
div#demo-container {
	width: 50%;
	padding: 15px;
	margin: 0 auto;
	border: 1px solid lightgray;
	border-radius: 10px;
}
.errorMsg {
	color: red;
	font-size: 20px;
	font-weight: bolder;
}
</style>
<section id="content">
	<h2 style="text-align: center">controller 테스트</h2>
	<div id="demo-container">
		<springform:form id="devFrm" method="post" modelAttribute="demo">
			<div class="form-group row">
				<label for="devName" class="col-sm-2 col-form-label">이름</label>
				<div class="col-sm-10">
					<springform:input path="devName" type="text" class="form-control" id="devName" name="devName"/>
					<springform:errors path="devName" cssClass="errorMsg" />
				</div>
			</div>
			<div class="form-group row">
				<label for="devAge" class="col-sm-2 col-form-label">나이</label>
				<div class="col-sm-10">
					<springform:input path="devAge" type="number" class="form-control" id="devAge" name="devAge"/>
					<springform:errors path="devAge" cssClass="errorMsg" />
				</div>
			</div>
			<div class="form-group row">
				<label for="devEmail" class="col-sm-2 col-form-label">이메일</label>
				<div class="col-sm-10">
					<springform:input path="devEmail" type="text" class="form-control" id="devEmail" name="devEmail"/>
					<springform:errors path="devEmail" cssClass="errorMsg" />
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label">성별</label>
				<div class="col-sm-10">
					<div class="form-check form-check-inline">
						<springform:radiobutton path="devGender" class="form-check-input" name="devGender" id="devGender0" value="M"/> 
						<label class="form-check-label" for="devGender0">남</label>
					</div>
					<div class="form-check form-check-inline">
						<springform:radiobutton path="devGender" class="form-check-input" name="devGender" id="devGender1" value="F"/> 
						<label class="form-check-label" for="devGender1">여</label>
					</div>
					<springform:errors path="devGender" cssClass="errorMsg"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label">개발언어</label>
				<div class="col-sm-10">
					<div class="form-check form-check-inline">
						<springform:checkbox path="devLang" class="form-check-input" name="devLang" id="devLang0" value="Java"/> 
						<label class="form-check-label" for="devLang0">Java</label>
					</div>
					<div class="form-check form-check-inline">
						<springform:checkbox path="devLang" class="form-check-input" name="devLang" id="devLang1" value="C"/> 
						<label class="form-check-label" for="devLang1">C</label>
					</div>
					<div class="form-check form-check-inline">
						<springform:checkbox path="devLang" class="form-check-input" name="devLang" id="devLang2" value="Javascript"/> 
						<label class="form-check-label" for="devLang2">Javascript</label>
					</div>
					<springform:errors path="devLang" cssClass="errorMsg"/>
				</div>
			</div>
			<div class="form-group row">
				<label for="devName" class="col-sm-2 col-form-label">생일</label>
				<div class="col-sm-10">
					<springform:input path="birthDay" type="date" class="form-control" id="birthDay" name="birthDay"/>
					<springform:errors path="birthDay" cssClass="errorMsg"/>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-12">
					<button class="btn btn-outline-primary col-sm-12" type="button" onclick="requestTest2('demo.do')">
						validator
					</button>
				</div>
			</div>
		</springform:form>
	</div>
</section>
<script>
	const requestAjax = async () => {
		const response = await fetch("${path}/demo/demo7.do", {
			method:"post",
			headers:{
				"Content-Type":"application/json;charset=utf-8"
			},
			body:JSON.stringify({
				"devName":"김태우","devAge":25,"devEmail":"t@t.com","gender":"남","devLang":["국어","영어","일본어","중국어"]
			})
		});
		if(response.ok){
			const data = await response.json();
			console.log(data);
		}
	}
	const requestTest = (url) => {
		const form=document.getElementById("devFrm");
		form.action=`${path}/demo/\${url}`;
		form.submit();
	}
	const requestTest2 = (url) => {
		const form=document.getElementById("devFrm");
		form.action=`${path}/validator/\${url}`;
		form.submit();
	}
</script>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>