<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="administrator.spamWord.list.label.word" path="word"/>
	<acme:list-column code="administrator.spamWord.list.label.size" path="size"/>
</acme:list>
<acme:form>
	<acme:form-return code="administrator.spamWord.list.button.create" action="/administrator/spam-word/create"/>
</acme:form>
