<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.spamWord.form.label.word" path="word"/>
	<acme:form-integer code="administrator.spamWord.form.label.size" path="size"/>
	
	<acme:form-submit test="${command == 'show'}" code="administrator.spamWord.form.button.update" action="/administrator/spamWord/update"/>
	<acme:form-submit test="${command == 'show'}" code="administrator.spamWord.form.button.delete" action="/administrator/spamWord/delete"/>
	<acme:form-submit test="${command == 'create'}" code="administrator.spamWord.form.button.create" action="/administrator/spamWord/create"/>
	<acme:form-submit test="${command == 'update'}" code="administrator.spamWord.form.button.update" action="/administrator/spamWord/update"/>
	<acme:form-submit test="${command == 'delete'}" code="administrator.spamWord.form.button.delete" action="/administrator/spamWord/delete"/>	

</acme:form>