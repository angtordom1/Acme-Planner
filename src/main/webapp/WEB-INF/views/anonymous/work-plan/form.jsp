<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-moment code="anonymous.work-plan.form.label.periodStart" path="periodStart"/>
	<acme:form-moment code="anonymous.work-plan.form.label.periodEnd" path="periodEnd"/>
	<acme:form-double code="anonymous.work-plan.form.label.workload" path="workload"/>
	<acme:form-checkbox code="anonymous.work-plan.form.label.state" path="state"/>
	<acme:form-checkbox code="anonymous.work-plan.form.label.finished" path="finished"/>
	
	<br>
	<h3><acme:message code="anonymous.work-plan.form.label.tasks"/></h3>
	
	<table class="table">
		  	<thead>
		  		<tr>
		  	 		<th scope="col"><acme:message code="manager.work-plan.list.label.periodStart"/></th>
		  	 		<th scope="col"><acme:message code="manager.work-plan.list.label.periodEnd"/></th>
		  	 		<th scope="col"><acme:message code="manager.work-plan.list.label.workload"/></th>
		  	 		<th scope="col"><acme:message code="manager.work-plan.list.label.state"/></th>
		  	 	</tr>
		  	</thead>
		  	<tbody>
		   		<jstl:forEach items="${tasks}" var="task">
			   		<tr>
						<td> <acme:print value="${task.periodStart}"/> </td>
						<td> <acme:print value="${task.periodEnd}"/> </td>
						<td> <acme:print value="${task.workload}"/> </td>
						<td> 
							<jstl:choose>
								<jstl:when test="${task.state == true}">
									<acme:message code="manager.work-plan.list.value.true"/> 
								</jstl:when>
								<jstl:otherwise>
									<acme:message code="manager.work-plan.list.value.false"/> 
								</jstl:otherwise>
							</jstl:choose>
						</td>
					</tr>
				</jstl:forEach>
				
			</tbody>
	  	</table>
    
    <acme:form-return code="anonymous.work-plan.form.button.return"/>	 
	

</acme:form>