<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>

	<acme:form-textbox code="anonymous.task.form.label.title" path="title"/>
	<acme:form-moment code="anonymous.task.form.label.periodStart" path="periodStart"/>
	<acme:form-moment code="anonymous.task.form.label.periodEnd" path="periodEnd"/>
	<acme:form-double code="anonymous.task.form.label.workload" path="workload"/>
	<acme:form-url code="anonymous.task.form.label.link" path="link"/>
	<acme:form-textarea code="anonymous.task.form.label.description" path="description"/>
	<acme:form-checkbox code="anonymous.task.form.label.state" path="state"/>
	<acme:form-checkbox code="anonymous.task.form.label.finished" path="finished"/>

	<acme:form-return code="anonymous.task.form.button.return"/>	

</acme:form>