<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.spam.form.label.umbral" path="umbral"/>
	<acme:form-hidden path="spamWords"/>
	
	<acme:form-submit test="${command == 'show'}" code="administrator.spam.form.button.update" action="/administrator/spam/update"/>
	<acme:form-submit test="${command == 'update'}" code="administrator.spam.form.button.update" action="/administrator/spam/update"/>

</acme:form>