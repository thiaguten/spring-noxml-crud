<%@include file="../../includes/includes.jsp" %>

<!DOCTYPE html>
<template:template>
    <jsp:attribute name="title">
        <spring:message code="label.manter.usuario"/>
    </jsp:attribute>

    <jsp:body>
        <c:url var="manterUsuario" value="/usuario/manter"/>

        <table style="width: 100%;">
            <tr>
                <td>
                    <form:form method="POST" action="${manterUsuario}" commandName="usuario">
                        <spring:hasBindErrors name="usuario">
                            <form:errors path="*" element="div" cssClass="errorblock"/>
                        </spring:hasBindErrors>

                        <c:if test="${not empty usuario_mensagem_negocial}">
                            <div class="errorblock">${usuario_mensagem_negocial}</div>
                        </c:if>

                        <form:hidden path="id"/>

                        <table style="width: 100%;">
                            <caption><spring:message code="label.manter.usuario"/></caption>
                            <tr>
                                <td>
                                    <table style="width: 100%;" border="1">
                                        <tr>
                                            <th><form:label path="nome"><spring:message
                                                    code="label.nome"/></form:label></th>
                                            <td><form:input path="nome" maxlength="100"/></td>
                                        </tr>
                                        <tr>
                                            <th><form:label path="idade"><spring:message
                                                    code="label.idade"/></form:label></th>
                                            <td><form:input path="idade" maxlength="3"/></td>
                                        </tr>
                                        <tr>
                                            <th><form:label path="email"><spring:message
                                                    code="label.email"/></form:label></th>
                                            <td><form:input path="email" maxlength="50"/></td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <c:if test="${usuario.id eq null}">
                                        <input type="submit" value="<spring:message code="label.incluir"/>"/>
                                    </c:if>
                                    <c:if test="${usuario.id ne null}">
                                        <input type="submit" value="<spring:message code="label.alterar"/>"/>
                                        <input type="reset" value="<spring:message code="label.resetar"/>"/>
                                    </c:if>
                                    <span class="button">
                                        <a href="<c:url value="/usuario/listar"/>"><spring:message
                                                code="label.voltar"/></a>
                                    </span>
                                </td>
                            </tr>
                        </table>
                    </form:form>
                </td>
            </tr>
        </table>
    </jsp:body>
</template:template>