<%@include file="../../includes/includes.jsp" %>

<!DOCTYPE html>
<template:template>
    <jsp:attribute name="title">
        <s:message code="label.listar.usuario"/>
    </jsp:attribute>

    <jsp:body>
        <c:url var="pesquisarUsuario" value="/usuario/pesquisar"/>
        <c:url var="adicionarUsuario" value="/usuario/novo"/>
        <c:url var="alterarUsuario" value="/usuario/alterar"/>
        <c:url var="removerUsuario" value="/usuario/remover"/>

        <table style="width: 100%;">
            <tr>
                <td>
                    <sf:form method="POST" action="${pesquisarUsuario}" commandName="usuario">
                        <sf:errors path="*" element="div" cssClass="errorblock"/>

                        <c:if test="${not empty usuario_mensagem_negocial}">
                            <div class="errorblock">${usuario_mensagem_negocial}</div>
                        </c:if>

                        <table style="width: 100%;">
                            <caption style="font-size: larger;font-weight: bolder;"><s:message
                                    code="label.pesquisar.usuario"/></caption>
                            <tr>
                                <td>
                                    <table style="width: 100%;" border="1">
                                        <tr>
                                            <th>
                                                <sf:label path="nome">
                                                    <s:message code="label.nome"/>
                                                </sf:label>
                                            </th>
                                            <td><sf:input path="nome" maxlength="100"/></td>
                                        </tr>
                                        <tr>
                                            <th>
                                                <sf:label path="idade">
                                                    <s:message code="label.idade"/>
                                                </sf:label>
                                            </th>
                                            <td><sf:input path="idade" onkeypress="return isNumberKey(event);"
                                                          maxlength="3"/></td>
                                        </tr>
                                        <tr>
                                            <th>
                                                <sf:label path="email">
                                                    <s:message code="label.email"/>
                                                </sf:label>
                                            </th>
                                            <td><sf:input path="email" maxlength="50"/></td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td style="padding-top: 10px;">
                                    <input type="submit" value="<s:message code="label.pesquisar"/>"/>
                                    <input type="reset" value="<s:message code="label.resetar"/>"/>
                                </td>
                            </tr>
                        </table>
                    </sf:form>
                </td>
            </tr>
            <tr>
                <td>
                    <table style="width: 100%;">
                        <caption style="font-size: larger;font-weight: bolder;"><s:message
                                code="label.listar.usuario"/></caption>
                        <tr>
                            <td>
                                <table style="width: 100%;" border=1>
                                    <thead>
                                    <tr>
                                        <th><s:message code="label.id"/></th>
                                        <th><s:message code="label.nome"/></th>
                                        <th><s:message code="label.idade"/></th>
                                        <th><s:message code="label.email"/></th>
                                        <th colspan=2><s:message code="label.acao"/></th>
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
                                                         title="<s:message code="label.alterar" />"
                                                         alt="<s:message code="label.alterar" />"
                                                         src="<c:url value="/resources/img/edit.gif" />">
                                                </a>
                                            </td>
                                            <td>
                                                <a href="${removerUsuario}/${usr.id}">
                                                    <img width="16" height="16"
                                                         title="<s:message code="label.excluir" />"
                                                         alt="<s:message code="label.excluir" />"
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
                                    <input type="submit" value="<s:message code="label.adicionar.usuario"/>"/>
                                </form>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </jsp:body>
</template:template>