<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri = "http://www.springframework.org/security/tags" prefix = "sec" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="#">
<link rel="stylesheet" href="/resources/css/header.css">
<title>MemberBoard</title>
</head>
<body>
	<div class="wrap">
		<div class="header">
			<div class="header-title">
				<a href="mainPage">MemberBoard</a>
			</div>
			<div class="header-member">
					
			<sec:authorize access="isAuthenticated()">
		     	<button type="button" class="header-btn" onclick="logoutPage()">로그아웃</button>
			</sec:authorize>
				<sec:authorize access="isAnonymous()">
				<button type="button" class="header-btn" onclick="loginPage()">로그인</button>
			    </sec:authorize>
				<button type="button" class="header-btn" onclick="joinPage()">회원가입</button>
			</div>
			<div class="menu">
				<a href="boardList">게시판</a>
			</div>
		</div>
		<div class="main">