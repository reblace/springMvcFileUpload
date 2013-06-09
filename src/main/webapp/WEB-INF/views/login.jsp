<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="tag"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if lt IE 7]>	<html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>		<html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>		<html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->

	<head>
		<tag:head pageTitle="Login"/>
	</head>

	<!-- HTML Body -->
	<body>
		<!--[if lt IE 7]>
			<p class="chromeframe">You are using an out of date browser. Some features of this site may not work properly. Please upgrade to better experience this site.</p>
		<![endif]-->
		<div class="container">
			<div class="hero-unit">
				<h1>Please sign in.</h1>
				<p>This site requires authentication. Please provide your username and password to proceed.</p>
				
				<c:if test="${not empty param.login_error}">
					<font color="red">Username or password is incorrect. Please try again.</font>
				</c:if>

				<form name="loginForm" action="<c:url value='j_spring_security_check'/>" method="POST">
					<input type='text' name='j_username' placeholder='Username' value='<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>'/><br/>
					<input type='password' name='j_password' placeholder="Password"/><br/>
					<label><input type="checkbox" name="_spring_security_remember_me"/><span> Remember me</span></label>
					<button type="submit" class="btn btn-primary btn-large">Sign In</button>
				</form>
			</div>
		</div>

	</body>
</html>
