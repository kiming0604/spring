<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>회원가입</title>
</head>
<body>
    <h2>회원가입</h2>
    <form method="post">
        <div>
            <label for="userId">아이디:</label>
            <input type="text" id="userId" name="userId" required>
        </div>
        <div>
            <label for="userPw">비밀번호:</label>
            <input type="password" id="userPw" name="userPw" required>
        </div>
        <div>
            <label for="userName">이름:</label>
            <input type="text" id="userName" name="userName" required>
        </div>
        <div>
            <input type="hidden" id="auth" name="auth" value="ROLE_USER">
        </div>
        <div>
            <input type="hidden" id="enabled" name="enabled" value="1">
        </div>
        <div>
            <button onclick="send(this.form)">회원가입</button>
        </div>
    </form>
</body>
<script type="text/javascript" src="/resources/js/member.js"></script>
</html>
