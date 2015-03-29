<%@tag description="template" language="java" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ attribute name="title" fragment="true" %>
<%@ attribute name="header" fragment="true" %>
<%@ attribute name="footer" fragment="true" %>

<jsp:useBean id="data" class="java.util.Date"/>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>::<jsp:invoke fragment="title"/>::</title>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
</head>
<body>
<div id="container">
    <div id="header">
        <jsp:invoke fragment="header"/>
    </div>
    <div id="body">
        <jsp:doBody/>
    </div>
    <div id="footer">
        <jsp:invoke fragment="footer"/>
        <hr/>
        <div style="text-align:center;">
        	<spring:message code="label.idioma"/>: 
       		<a href="?lang=en"><spring:message code="label.ingles"/></a>
       		 | 
       		<a href="?lang=pt_BR"><spring:message code="label.portugues"/></a>
        	<br/><br/>
        	&#169; <fmt:formatDate pattern="yyyy" value="${data}"/> Thiago Gutenberg
        </div>
    </div>
</div>
</body>
</html>