<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="anonymous.task.list.label.title" path="title" width="10%"/>
	<acme:list-column code="anonymous.task.list.label.creationDate" path="creationDate" width="10%"/>
	<acme:list-column code="anonymous.task.list.label.periodStart" path="periodStart" width="10%"/>
	<acme:list-column code="anonymous.task.list.label.periodEnd" path="periodEnd" width="10%"/>
	<acme:list-column code="anonymous.task.list.label.workload" path="workload" width="10%"/>
	<acme:list-column code="anonymous.task.list.label.link" path="link" width="10%"/>
	<acme:list-column code="anonymous.task.list.label.description" path="description" width="40%"/>
</acme:list>