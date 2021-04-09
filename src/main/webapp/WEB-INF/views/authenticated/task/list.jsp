<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="authenticated.task.list.label.title" path="title" width="20%"/>
	<acme:list-column code="authenticated.task.list.label.creationDate" path="creationDate" width="20%"/>
	<acme:list-column code="authenticated.task.list.label.periodStart" path="periodStart" width="20%"/>
	<acme:list-column code="authenticated.task.list.label.periodEnd" path="periodEnd" width="20%"/>
	<acme:list-column code="authenticated.task.list.label.workload" path="workload" width="20%"/>
</acme:list>