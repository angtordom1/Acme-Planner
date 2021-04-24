<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>

	<acme:form-textbox code="anonymous.work-plan.form.label.title" path="title"/>
	<acme:form-moment code="anonymous.work-plan.form.label.periodStart" path="periodStart"/>
	<acme:form-moment code="anonymous.work-plan.form.label.periodEnd" path="periodEnd"/>
	<acme:form-double code="anonymous.work-plan.form.label.workload" path="workload"/>
	<acme:form-textarea code="anonymous.work-plan.form.label.description" path="description"/>
	<acme:form-checkbox code="anonymous.work-plan.form.label.state" path="state"/>
	<acme:form-checkbox code="anonymous.work-plan.form.label.finished" path="finished"/>

	<acme:form-return code="anonymous.work-plan.form.button.return"/>	

</acme:form>