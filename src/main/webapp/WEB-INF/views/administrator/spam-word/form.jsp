<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<h3><strong><acme:message code="administrator.spamWord.form.label.reminder"/></strong></h3><br>
	<acme:form-textbox code="administrator.spamWord.form.label.word" path="word"/>
	<acme:form-integer code="administrator.spamWord.form.label.size" path="size"/>
	
	<acme:form-submit test="${command == 'show'}" code="administrator.spamWord.form.button.update" action="/administrator/spam-word/update"/>
	<acme:form-submit test="${command == 'show'}" code="administrator.spamWord.form.button.delete" action="/administrator/spam-word/delete"/>
	<acme:form-submit test="${command == 'create'}" code="administrator.spamWord.form.button.create" action="/administrator/spam-word/create"/>
	<acme:form-submit test="${command == 'update'}" code="administrator.spamWord.form.button.update" action="/administrator/spam-word/update"/>
	<acme:form-submit test="${command == 'delete'}" code="administrator.spamWord.form.button.delete" action="/administrator/spam-word/delete"/>	

</acme:form>