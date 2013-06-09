<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag"%>
<%@ page pageEncoding="UTF-8"%>

<tag:errorPage message="Not Found">

	<div class="messageContainer">
		<h1>Not found <span>:(</span></h1>
		<p>Sorry, but the page you were trying to view does not exist.</p>
		<p>It looks like this was the result of either:</p>
		<ul class="suggestions">
			<li>a mistyped address</li>
			<li>an out-of-date link</li>
		</ul>
	</div>

</tag:errorPage>


