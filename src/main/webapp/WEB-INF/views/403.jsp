<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag"%>
<%@ page pageEncoding="UTF-8"%>

<tag:errorPage message="Not Authorized">

	<div class="messageContainer">
		<h1>Not Authorized <span>:(</span></h1>
		<p>Sorry, but you do not have permissions to view this page.</p>
	</div>

</tag:errorPage>