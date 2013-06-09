<%@tag description="Basic Template Error Page" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag"%>
<%@attribute name="message" required="true"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<link rel="icon" href="resources/favicon.ico" type="image/x-icon">
		<link rel="shortcut icon" href="resources/favicon.ico" type="image/x-icon">
		<link rel="stylesheet" href="resources/css/bootstrap/bootstrap.min.css">
		<link rel="stylesheet" href="resources/css/bootstrap/bootstrap-responsive.min.css">

		<meta charset="utf-8">
		<title>${message} :(</title>
		<style>
			::-moz-selection {
				background: #b3d4fc;
				text-shadow: none;
			}

			::selection {
				background: #b3d4fc;
				text-shadow: none;
			}

			body {
				background: #f0f0f0;
			}

			div.html {
				padding: 30px 10px;
				font-size: 20px;
				line-height: 1.4;
				color: #737373;
				-webkit-text-size-adjust: 100%;
				-ms-text-size-adjust: 100%;
			}

			div.html,
			input {
				font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
			}

			div.body {
				max-width: 500px;
				_width: 500px;
				padding: 30px 20px 50px;
				border: 1px solid #b3b3b3;
				border-radius: 4px;
				margin: 0 auto;
				box-shadow: 0 1px 10px #a7a7a7, inset 0 1px 0 #fff;
				background: #fcfcfc;
			}

			h1 {
				margin: 0 10px;
				font-size: 50px;
				text-align: center;
			}

			h1 span {
				color: #bbb;
			}

			h3 {
				margin: 1.5em 0 0.5em;
			}

			p {
				margin: 1em 0;
			}

			ul.suggestions {
				padding: 0 0 0 40px;
				margin: 1em 0;
			}

			.messageContainer {
				max-width: 380px;
				_width: 380px;
				margin: 0 auto;
			}

		</style>
		<link rel="stylesheet" href="resources/css/style.css">
		<script src="resources/js/modernizr/modernizr-2.6.1-respond-1.1.0.min.js"></script>
	</head>
	<body>

		<div class="html">
			<div class="body">
				<jsp:doBody/>
			</div>
		</div>

	<script>window.jQuery || document.write('<script src="resources/js/jquery/jquery-1.8.0.min.js"><\/script>')</script>
	<script src="resources/js/bootstrap/bootstrap.min.js"></script>

	</body>
</html>
