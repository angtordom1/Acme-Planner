<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="false">
	<acme:list-column code="manager.work-plan.list.label.periodStart" path="periodStart" width="20%"/>
	<acme:list-column code="manager.work-plan.list.label.periodEnd" path="periodEnd" width="20%"/>
	<acme:list-column code="manager.work-plan.list.label.workload" path="workload" width="20%"/>
	<acme:list-column code="manager.work-plan.list.label.state" path="state" width="15%" sortable="false"/>
</acme:list>