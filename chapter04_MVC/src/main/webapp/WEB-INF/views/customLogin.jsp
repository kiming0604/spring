<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Custom Login Page</title>
</head>
<body>
    <h1>Custom Login Page</h1>
    <h2>${error}</h2>
    <h2>${logout}</h2>
    
    <form action="login" method="post">
        <div>
            <input type="text" name="username" placeholder="사용자 이름" required>
        </div>
        <div>
            <input type="password" name="password" placeholder="비밀번호" required>
        </div>
        <div>
            <input type="checkbox" name="remember-me">아이디를... 기억해줘요
        </div>
        <div>
            <input type="submit" value="전송">
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    </form>
</body>
</html>
