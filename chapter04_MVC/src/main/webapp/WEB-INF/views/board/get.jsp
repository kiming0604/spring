<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <jsp:include page="../layout/header.jsp"/>   
   
   <div class="page-header">
      <h1>게시글 화면</h1>
   </div>
   <div class="panel-body">
      <form method="POST">
         <table>
            <tbody>
               <tr>
                  <th>글 번호</th>
                  <td><input type="text" name="bno" value="${vo.bno}" readonly></td>
               </tr>
               <tr>
                  <th>제목</th>
                  <td><input type="text" name="title" value="${vo.title }" readonly></td>
               </tr>
               <tr>
                  <th>작성자</th>
                  <td><input type="text" name="writer" value="${vo.writer }" readonly></td>
               </tr>
               <tr>
                  <th>내용</th>
                  <td>
                     <textarea rows="10" cols="76" name="content" readonly>${vo.content }</textarea>
                  </td>
               </tr>
            </tbody>
         </table>
      </form>
      <div class="panel-body-btns">
         <button type="button" class="btn btn-sec" id="modifyBtn">수정</button>
         <button type="button" class="btn btn-fir" id="indexBtn">목록으로 이동</button>
      </div>
   </div>
   <div class="panel-footer">
      <div class="panel-footer-header">
         <div class="panel-footer-title">
            <a href="mainPage">댓글</a>
         </div>
         <div class="panel-footer-register">
            <button type="button" class="btn btn-sec" id="replyBtn">댓글 달기</button>
         </div>
      </div>
      <div class="panel-footer-body">
         <ul class="chat">
            <li data-rno="10">
               <div>
                  <div class="chat-header">
                     <strong class="primary-font">작성자</strong>
                     <small class="pull-right">0000-00-00</small>
                  </div>
                  <p>내용</p>
               </div>
            </li>
         </ul>
      </div>
   </div>

   <jsp:include page="../layout/footer.jsp"/>
   <script type="text/javascript" src="/resources/js/reply.js"></script>   
   <script type="text/javascript" src="/resources/js/get.js"></script>   
</body>
</html>