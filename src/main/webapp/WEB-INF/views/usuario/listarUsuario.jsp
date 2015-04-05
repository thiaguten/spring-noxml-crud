<%@include file="../../includes/includes.jsp" %>

<!DOCTYPE html>
<template:template>
    <jsp:attribute name="title">
        <spring:message code="label.listar.usuario"/>
    </jsp:attribute>

    <jsp:body>
        <c:url var="pesquisarUsuario" value="/usuario/pesquisar"/>
        <c:url var="adicionarUsuario" value="/usuario/novo"/>
        <c:url var="alterarUsuario" value="/usuario/alterar"/>
        <c:url var="removerUsuario" value="/usuario/remover"/>

        <table style="width: 100%;">
            <tr>
                <td>
                    <form:form method="POST" action="${pesquisarUsuario}" commandName="usuario">
                        <form:errors path="*" element="div" cssClass="errorblock"/>

                        <c:if test="${not empty usuario_mensagem_negocial}">
                            <div class="errorblock">${usuario_mensagem_negocial}</div>
                        </c:if>

                        <table style="width: 100%;">
                            <caption><spring:message code="label.pesquisar.usuario"/></caption>
                            <tr>
                                <td>
                                    <table style="width: 100%;" border="1">
                                        <tr>
                                            <th>
                                            	<form:label path="nome">
                                            		<spring:message code="label.nome"/>
                                                </form:label>
                                            </th>
                                            <td><form:input path="nome" maxlength="100"/></td>
                                        </tr>
                                        <tr>
                                            <th>
                                            	<form:label path="idade">
                                            		<spring:message code="label.idade"/>
                                                </form:label>
                                            </th>
                                            <td><form:input path="idade" maxlength="3"/></td>
                                        </tr>
                                        <tr>
                                            <th>
                                            	<form:label path="email">
                                            		<spring:message code="label.email"/>
                                                </form:label>
                                            </th>
                                            <td><form:input path="email" maxlength="50"/></td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td style="padding-top: 10px;">
                                    <input type="submit" value="<spring:message code="label.pesquisar"/>"/>
                                    <input type="reset" value="<spring:message code="label.resetar"/>"/>
                                </td>
                            </tr>
                        </table>
                    </form:form>
                </td>
            </tr>
            <tr>
                <td>
                    <table style="width: 100%;">
                        <caption><spring:message code="label.listar.usuario"/></caption>
                        <tr>
                            <td>
                                <table style="width: 100%;" border=1>
                                    <thead>
                                    <tr>
                                        <th><spring:message code="label.id"/></th>
                                        <th><spring:message code="label.nome"/></th>
                                        <th><spring:message code="label.idade"/></th>
                                        <th><spring:message code="label.email"/></th>
                                        <th colspan=2><spring:message code="label.acao"/></th>
                                    </tr>
                                    </thead>
                                    <tbody align="center">
                                    <c:forEach items="${usuarios}" var="usr">
                                        <tr>
                                            <td><c:out value="${usr.id}"/></td>
                                            <td><c:out value="${usr.nome}"/></td>
                                            <td><c:out value="${usr.idade}"/></td>
                                            <td><c:out value="${usr.email}"/></td>
                                            <td>
                                                <a href="${alterarUsuario}/${usr.id}">
                                                    <img width="16" height="16"
                                                         title="<spring:message code="label.alterar" />"
                                                         alt="<spring:message code="label.alterar" />"
                                                         src="<c:url value="/resources/img/edit.gif" />">
                                                </a>
                                            </td>
                                            <td>
                                                <a href="${removerUsuario}/${usr.id}">
                                                    <img width="16" height="16"
                                                         title="<spring:message code="label.excluir" />"
                                                         alt="<spring:message code="label.excluir" />"
                                                         src="<c:url value="/resources/img/delete.gif" />">
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td style="padding-top: 10px;">
	                            <form action="${adicionarUsuario}">
	                            	<input type="submit" value="<spring:message code="label.adicionar.usuario"/>"/>
	                            </form>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </jsp:body>
</template:template>