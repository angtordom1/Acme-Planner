<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="false">
	<acme:list-column code="anonymous.task.list.label.title" path="title" width="25%" sortable="false"/>
	<acme:list-column code="anonymous.task.list.label.periodStart" path="periodStart" width="25%"/>
	<acme:list-column code="anonymous.task.list.label.periodEnd" path="periodEnd" width="25%"/>
	<acme:list-column code="anonymous.task.list.label.workload" path="workload" width="25%"/>
</acme:list>