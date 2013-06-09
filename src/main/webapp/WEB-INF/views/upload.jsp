<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag"%>
<%@ page pageEncoding="UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<!--[if lt IE 7]>	<html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>		<html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>		<html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->

<head>
	<tag:head pageTitle="Upload" />

	<link rel="stylesheet" href="resources/css/bootstrap/bootstrap-image-gallery.min.css">
	<link rel="stylesheet" href="resources/css/jquery/jquery-fileupload-ui.css">
	<noscript>
		<link rel="stylesheet" href="resources/css/jquery/jquery-fileupload-ui-noscript.css">
	</noscript>

</head>

<body>

	<!--[if lt IE 7]>
			<p class="chromeframe">You are using an out of date browser. Some features of this site may not work properly. Please upgrade to better experience this site.</p>
		<![endif]-->
	<div class="container-fluid">
		<!-- The file upload form used as target for the file upload widget -->
		<form id="fileupload" action="/files/" method="POST" enctype="multipart/form-data">
			<!-- Redirect browsers with JavaScript disabled to the origin page -->
			<noscript>
				<input type="hidden" name="redirect" value="/nojavascript">
			</noscript>

			<!-- The fileupload-buttonbar contains buttons to add/delete files and start/cancel the upload -->
			<div class="row fileupload-buttonbar">
				<div class="span12">
					<!-- The fileinput-button span is used to style the file input field as button -->
					<span class="btn btn-success fileinput-button">
						<i class="icon-plus icon-white"></i><span>Add files...</span>
						<input type="file" name="file" multiple>
					</span>

					<button type="submit" class="btn btn-primary start">
						<i class="icon-upload icon-white"></i> <span>Start upload</span>
					</button>

					<button type="reset" class="btn btn-warning cancel">
						<i class="icon-ban-circle icon-white"></i> <span>Cancel upload</span>
					</button>

					<button type="button" class="btn btn-danger delete">
						<i class="icon-trash icon-white"></i> <span>Delete</span>
					</button>

					<input type="checkbox" class="toggle">
				</div>

				<!-- The global progress information -->
				<div class="span5 fileupload-progress fade">
					<!-- The global progress bar -->
					<div class="progress progress-success progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100">
						<div class="bar" style="width: 0%;"></div>
					</div>
					<!-- The extended global progress information -->
					<div class="progress-extended">&nbsp;</div>
				</div>

			</div>

			<!-- The loading indicator is shown during file processing -->
			<div class="fileupload-loading"></div>
			<br>
			<!-- The table listing the files available for upload/download -->
			<table role="presentation" class="table table-striped">
				<tbody class="files" data-toggle="modal-gallery" data-target="#modal-gallery"></tbody>
			</table>
		</form>

	</div>

	<!-- modal-gallery is the modal dialog used for the image gallery -->
	<div id="modal-gallery" class="modal modal-gallery hide fade"
		data-filter=":odd" tabindex="-1">
		<div class="modal-header">
			<a class="close" data-dismiss="modal">&times;</a>
			<h3 class="modal-title"></h3>
		</div>
		<div class="modal-body">
			<div class="modal-image"></div>
		</div>
		<div class="modal-footer">
			<a class="btn modal-download" target="_blank"> <i
				class="icon-download"></i> <span>Download</span>
			</a> <a class="btn btn-success modal-play modal-slideshow"
				data-slideshow="5000"> <i class="icon-play icon-white"></i> <span>Slideshow</span>
			</a> <a class="btn btn-info modal-prev"> <i
				class="icon-arrow-left icon-white"></i> <span>Previous</span>
			</a> <a class="btn btn-primary modal-next"> <span>Next</span> <i
				class="icon-arrow-right icon-white"></i>
			</a>
		</div>
	</div>

	<div class="push"></div>

	<script>window.jQuery || document.write('<script src="resources/js/jquery/jquery-1.8.0.min.js"><\/script>')</script>
	<script src="resources/js/bootstrap/bootstrap.min.js"></script>
	<script src="resources/js/jquery/jquery.ui.widget.js"></script>
	<script src="resources/js/upload/tmpl.min.js"></script>
	<script src="resources/js/upload/load-image.min.js"></script>
	<script src="resources/js/upload/canvas-to-blob.min.js"></script>
	<script src="resources/js/bootstrap/bootstrap-image-gallery.min.js"></script>
	<script src="resources/js/jquery/jquery.iframe-transport.js"></script>
	<script src="resources/js/jquery/jquery.fileupload.js"></script>
	<script src="resources/js/jquery/jquery.fileupload-fp.js"></script>
	<script src="resources/js/jquery/jquery.fileupload-ui.js"></script>

	<!--[if gte IE 8]><script src="js/cors/jquery.xdr-transport.js"></script><![endif]-->

	<!-- The template to display files available for upload -->
	<script id="template-upload" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-upload fade">
        <td class="preview"><span class="fade"></span></td>
        <td class="name"><span>{%=file.name%}</span></td>
        <td class="size"><span>{%=o.formatFileSize(file.size)%}</span></td>
        {% if (file.error) { %}
            <td class="error" colspan="2"><span class="label label-important">Error</span> {%=file.error%}</td>
        {% } else if (o.files.valid && !i) { %}
            <td>
                <div class="progress progress-success progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0"><div class="bar" style="width:0%;"></div></div>
            </td>
            <td>{% if (!o.options.autoUpload) { %}
                <button class="btn btn-primary start">
                    <i class="icon-upload icon-white"></i>
                    <span>Start</span>
                </button>
            {% } %}</td>
        {% } else { %}
            <td colspan="2"></td>
        {% } %}
        <td>{% if (!i) { %}
            <button class="btn btn-warning cancel">
                <i class="icon-ban-circle icon-white"></i>
                <span>Cancel</span>
            </button>
        {% } %}</td>
    </tr>
{% } %}
	</script>

	<!-- The template to display files available for download -->
	<script id="template-download" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-download fade">
        {% if (file.error) { %}
            <td></td>
            <td class="name"><span>{%=file.name%}</span></td>
            <td class="size"><span>{%=o.formatFileSize(file.size)%}</span></td>
            <td class="error" colspan="2"><span class="label label-important">Error</span> {%=file.error%}</td>
        {% } else { %}
            <td class="preview">{% if (file.thumbnailUrl) { %}
                <a href="${contextPath}/{%=file.url%}" title="{%=file.name%}" data-gallery="gallery" download="{%=file.name%}"><img src="${contextPath}/{%=file.thumbnailUrl%}"></a>
            {% } %}</td>
            <td class="name">
                <a href="${contextPath}/{%=file.url%}" title="{%=file.name%}" data-gallery="{%=file.thumbnailUrl&&'gallery'%}" download="{%=file.name%}">{%=file.name%}</a>
            </td>
            <td class="size"><span>{%=o.formatFileSize(file.size)%}</span></td>
            <td colspan="2"></td>
        {% } %}
        <td>
            <button class="btn btn-danger delete" data-type="{%=file.deleteType%}" data-url="{%=file.deleteUrl%}"{% if (file.delete_with_credentials) { %} data-xhr-fields='{"withCredentials":true}'{% } %}>
                <i class="icon-trash icon-white"></i>
                <span>Delete</span>
            </button>
            <input type="checkbox" name="delete" value="1" class="toggle">
        </td>
    </tr>
{% } %}
	</script>

	<script>
	
		$(function() {
			'use strict';

			// Initialize the jQuery File Upload widget:
			$('#fileupload').fileupload({
				// Uncomment the following to send cross-domain cookies:
				//xhrFields: {withCredentials: true},
				url : '${contextPath}/images'
			});

			$('#fileupload').fileupload('option', {
				maxFileSize : 5000000,
				acceptFileTypes : /(\.|\/)(gif|jpe?g|png)$/i,
				process : [ {
					action : 'load',
					fileTypes : /^image\/(gif|jpeg|png)$/,
					maxFileSize : 20000000 // 20MB
				}, {
					action : 'resize',
					maxWidth : 1440,
					maxHeight : 900
				}, {
					action : 'save'
				} ]
			});

			// Upload server status check for browsers with CORS support:
			$.ajax({
				url : '${contextPath}/images',
				type: 'HEAD'
			}).fail(function() {
				$('<span class="alert alert-error"/>')
					.text('Upload server currently unavailable - '+ new Date()).appendTo('#fileupload');
			});

		});
	</script>

</body>
</html>