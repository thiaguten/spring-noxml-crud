<%@tag description="template" language="java" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ attribute name="title" fragment="true" %>
<%@ attribute name="header" fragment="true" %>
<%@ attribute name="menu" fragment="true" %>
<%@ attribute name="footer" fragment="true" %>

<jsp:useBean id="data" class="java.util.Date"/>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>::
        <jsp:invoke fragment="title"/>
        ::</title>
    <link href="<c:url value="/resources/css/normalize.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
</head>
<body>
<div id="container">

    <!-- header -->
    <div id="header">
			<span style="float: right;">
				<strong><s:message code="label.idioma"/>:</strong>
				<a href="?lang=en"><s:message code="label.ingles"/></a>
				 | 
				 <a href="?lang=pt_BR"><s:message code="label.portugues"/></a>
			</span>
        <jsp:invoke fragment="header"/>
    </div>

    <!-- menu -->
    <div id="menu">
        <jsp:invoke fragment="menu"/>
    </div>

    <!-- body -->
    <div id="body">
        <jsp:doBody/>
    </div>

    <!-- footer -->
    <div id="footer">
        <jsp:invoke fragment="footer"/>
        <hr/>
        <div style="text-align: center;">
            <code>&#169;<fmt:formatDate pattern="yyyy" value="${data}"/> Thiago Gutenberg</code>
        </div>
    </div>

</div>
<script src="<c:url value="/resources/js/default.js" />"></script>
</body>
</html>