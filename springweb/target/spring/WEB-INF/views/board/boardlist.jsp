<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<section id="board-container" class="container">
        <p>총 ${totalContents }건의 게시물이 있습니다.</p>
        <button class="btn btn-outline-danger"
         onclick="location.assign('${pageContext.request.contextPath}/board/boardwrite.do')">글쓰기</button>
        <table id="tbl-board" class="table table-striped table-hover">
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
                <th>첨부파일</th>
                <th>조회수</th>
            </tr>
            <c:if test="${boards.isEmpty() }">
            	<tr>
            		<td colspan="6">조회된 데이터가 없습니다</td>
            	</tr>
            </c:if>
            <c:if test="${not boards.isEmpty() }">
            	<c:forEach var="b" items="${boards }">
            		<tr>
            			<td>${b.boardNo }</td>
            			<td>
	            			<a href="${pageContext.request.contextPath }/board/boardview.do?boardNo=${b.boardNo}">
	            				${b.boardTitle }	
	            			</a>
            			</td>
            			<td>${b.boardWriter }</td>
            			<td>${b.boardDate }</td>
            			<td></td>
            			<td>${b.boardReadcount }</td>
            		</tr>
            	</c:forEach>
            </c:if>
        </table> 
        <div>
        	${pageBar }
        </div>
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>