<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>


	<acme:form-moment  code="manager.work-plan.form.label.periodStart" path="periodStart"/>
	<acme:form-moment  code="manager.work-plan.form.label.periodEnd" path="periodEnd"/>
	<jstl:if test="${command == 'show'}">
		<acme:form-checkbox code="manager.work-plan.form.label.workload" path="workload"/>
	</jstl:if>
	<acme:form-checkbox code="manager.work-plan.form.label.state" path="state"/>

	
	
	<jstl:if test="${command == 'create' || command =='update'}">
	<acme:form-select code="manager.work-plan.form.label.tasks" path="tasks" multiple="true">
		<jstl:forEach items="${tasks}" var="task">
			<acme:form-option code="${task}" value="${task}"/>
		</jstl:forEach>
	</acme:form-select>
  	</jstl:if>
	
	<jstl:if test="${command == 'show'}">
	<br>
	<h3><acme:message code="manager.work-plan.form.label.tasks"/></h3>
	
	<table class="table">
	  	<thead>
	  		<tr>
	  	 		<th scope="col"><acme:message code="manager.work-plan.list.label.title"/></th>
	  	 		<th scope="col"><acme:message code="manager.work-plan.list.label.periodStart"/></th>
	  	 		<th scope="col"><acme:message code="manager.work-plan.list.label.periodEnd"/></th>
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
  	</jstl:if>
  	
    
    <acme:form-submit test="${command == 'show' && finished == 'false'}" code="manager.work-plan.form.button.update" action="/manager/work-plan/update"/>
	<acme:form-submit test="${command == 'show' && finished == 'false'}" code="manager.work-plan.form.button.delete" action="/manager/work-plan/delete"/>
	<acme:form-submit test="${command == 'create'}" code="manager.work-plan.form.button.create" action="/manager/work-plan/create"/>
	<acme:form-submit test="${command == 'update'}" code="manager.work-plan.form.button.update" action="/manager/work-plan/update"/>
	<acme:form-submit test="${command == 'delete'}" code="manager.work-plan.form.button.delete" action="/manager/work-plan/delete"/>		  
	<acme:form-return code="manager.work-plan.form.button.return"/>

</acme:form>