<%@include file="../../includes/includes.jsp" %>

<!DOCTYPE html>
<template:template>
    <jsp:attribute name="title">
        <s:message code="label.manter.usuario"/>
    </jsp:attribute>

    <jsp:body>
    	<script type="text/javascript">
	    	function isNumberKey(evt)
	        {
	           var charCode = (evt.which) ? evt.which : event.keyCode;
	           if (charCode > 31 && (charCode < 48 || charCode > 57))
	              return false;
	           return true;
	        }
    	</script>

        <c:url var="manterUsuario" value="/usuario/manter"/>
        <c:url var="listarUsuario" value="/usuario/listar"/>

        <table style="width: 100%;">
            <tr>
                <td>
                    <sf:form method="POST" action="${manterUsuario}" commandName="usuario">
                        <sf:errors path="*" element="div" cssClass="errorblock"/>

                        <c:if test="${not empty usuario_mensagem_negocial}">
                            <div class="errorblock">${usuario_mensagem_negocial}</div>
                        </c:if>

                        <sf:hidden path="id"/>

                        <table style="width: 100%;">
                            <caption style="font-size: larger;font-weight: bolder;"><s:message code="label.manter.usuario"/></caption>
                            <tr>
                                <td>
                                    <table style="width: 100%;" border="1">
                                        <tr>
                                            <th>
                                            	<sf:label path="nome">
                                            		<s:message code="label.nome"/>
                                            	</sf:label>
                                            </th>
                                            <td><sf:input path="nome" placeholder="Thiago" maxlength="100"/></td>
                                        </tr>
                                        <tr>
                                            <th>
                                            	<sf:label path="idade">
                                            		<s:message code="label.idade"/>
                                                </sf:label>
                                            </th>
                                            <td><sf:input path="idade" placeholder="27" onkeypress="return isNumberKey(event);" maxlength="3"/></td>
                                        </tr>
                                        <tr>
                                            <th>
                                            	<sf:label path="email">
                                            		<s:message code="label.email"/>
                                                </sf:label>
                                            </th>
                                            <td><sf:input path="email" placeholder="email@email.com" maxlength="50"/></td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td style="padding-top: 10px;">
                                    <c:if test="${usuario.id eq null}">
                                        <input type="submit" value="<s:message code="label.incluir"/>"/>
                                    </c:if>
                                    <c:if test="${usuario.id ne null}">
                                        <input type="submit" value="<s:message code="label.alterar"/>"/>
                                        <input type="reset" value="<s:message code="label.resetar"/>"/>
                                    </c:if>
									<button type="button" onclick="location.href='${listarUsuario}'">
										<s:message code="label.voltar"/>
									</button>
                                </td>
                            </tr>
                        </table>
                    </sf:form>
                </td>
            </tr>
        </table>
    </jsp:body>
</template:template>
