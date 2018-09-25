<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@	taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="/JEE_Exam_6/home">Strona główna</a>
<br>
	<form:form method="POST" modelAttribute="user">

		<div>
			Imię
			<form:input path="firstName" />
			<form:errors path="firstName" cssClass="error" />
		</div>

		<div>
			Nazwisko
			<form:input path="lastName" />
			<form:errors path="lastName" cssClass="error" />
		</div>

		<div>
			Email
			<form:input path="email" type="email" />
			<form:errors path="email" cssClass="error" />
		</div>

		<div>
			<input type="submit" value="Dodaj użytkownika" />
		</div>

	</form:form>
</body>
</html>