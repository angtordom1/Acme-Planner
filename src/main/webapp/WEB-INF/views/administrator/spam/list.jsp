<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="administrator.spam.list.label.umbral" path="umbral" width="10%"/>
	<acme:list-column code="administrator.spam.list.label.spamWords" path="spamWords" width="90%"/>
	
	<!-- Boton para mandar a la vista de la lista de palabras spam, por testear -->
	<button type="button" onclick="/administrator/spamWord/list">"administrator.spam.list.button.listWords"</button>
</acme:list>