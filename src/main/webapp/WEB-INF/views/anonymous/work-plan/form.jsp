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
	
	<br>
	<h3><acme:message code="anonymous.work-plan.form.label.tasks"/></h3>
	
	<table class="table">
	  	<thead>
	  		<tr>
	  	 		<th scope="col"><acme:message  code="anonymous.work-plan.list.label.title"/></th>
	  	 		<th scope="col"><acme:message code="anonymous.work-plan.list.label.periodStart"/></th>
	  	 		<th scope="col"><acme:message code="anonymous.work-plan.list.label.periodEnd"/></th>
	  	 		<th scope="col"><acme:message code="anonymous.work-plan.list.label.workload"/></th>
	  	 	</tr>
	  	</thead>
	  	<tbody>
	   		<jstl:forEach items="${tasks}" var="task">
		   		<tr>
					<td> <acme:print value="${task.title}"/> </td>
					<td> <acme:print value="${task.periodStart}"/> </td>
					<td> <acme:print value="${task.periodEnd}"/> </td>
					<td> <acme:print value="${task.workload}"/> </td>
				</tr>
			</jstl:forEach>
			
		</tbody>
  	</table>
    
     
	

</acme:form>