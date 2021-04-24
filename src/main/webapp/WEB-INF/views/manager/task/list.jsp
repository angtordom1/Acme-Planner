<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="manager.task.list.label.title" path="title" width="10%" sortable="false"/>
	<acme:list-column code="manager.task.list.label.periodStart" path="periodStart" width="30%"/>
	<acme:list-column code="manager.task.list.label.periodEnd" path="periodEnd" width="30%"/>
	<acme:list-column code="manager.task.list.label.workload" path="workload" width="20%"/>
	<acme:list-column code="manager.task.list.label.state" path="state" width="10%" sortable="false"/>
</acme:list>