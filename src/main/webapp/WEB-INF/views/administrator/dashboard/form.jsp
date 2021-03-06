<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 


<h2>
	<acme:message code="administrator.dashboard.form.title.general-indicators"/>
</h2>

<table class="table table-sm">
	<caption>
		<acme:message code="administrator.dashboard.form.title.general-indicators"/>
	</caption>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.public-tasks-amount"/>
		</th>
		<td>
			<acme:print value="${publicTasksAmount}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.private-tasks-amount"/>
		</th>
		<td>
			<acme:print value="${privateTasksAmount}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.finished-tasks-amount"/>
		</th>
		<td>
			<acme:print value="${finishedTasksAmount}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.unfinished-tasks-amount"/>
		</th>
		<td>
			<acme:print value="${unfinishedTasksAmount}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.average-period-execution"/>
		</th>
		<td>
			<fmt:formatNumber value="${averagePeriodExecution}" maxFractionDigits="0"/>   
			<acme:message code="administrator.dashboard.form.label.hours"/>
			<fmt:formatNumber value="${averagePeriodExecution}" maxIntegerDigits="0" pattern=".00" var="v1"/>
			${fn:replace(fn:replace(v1, ".", " "), ",", " ")}
			<acme:message code="administrator.dashboard.form.label.minutes"/>
		</td>
	</tr>		
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviation-period-execution"/>
		</th>
		<td>
			<fmt:formatNumber value="${deviationPeriodExecution}" maxFractionDigits="0"/>   
			<acme:message code="administrator.dashboard.form.label.hours"/>
			<fmt:formatNumber value="${deviationPeriodExecution}" maxIntegerDigits="0" pattern=".00" var="v2"/>
			${fn:replace(fn:replace(v2, ".", " "), ",", " ")}
			<acme:message code="administrator.dashboard.form.label.minutes"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minimun-period-execution"/>
		</th>
		<td>
			<fmt:formatNumber value="${minimunPeriodExecution}" maxFractionDigits="0"/> 
			<acme:message code="administrator.dashboard.form.label.hours"/>  
			<fmt:formatNumber value="${minimunPeriodExecution}" maxIntegerDigits="0" pattern=".00" var="v3"/>
			${fn:replace(fn:replace(v3, ".", " "), ",", " ")}
			<acme:message code="administrator.dashboard.form.label.minutes"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maximun-period-execution"/>
		</th>
		<td>
			<fmt:formatNumber value="${maximunPeriodExecution}" maxFractionDigits="0"/> 
			<acme:message code="administrator.dashboard.form.label.hours"/>  
			<fmt:formatNumber value="${maximunPeriodExecution}" maxIntegerDigits="0" pattern=".00" var="v4"/>
			${fn:replace(fn:replace(v4, ".", " "), ",", " ")}
			<acme:message code="administrator.dashboard.form.label.minutes"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.average-workload"/>
		</th>
		<td>
			<fmt:formatNumber value="${averageWorkload}" maxFractionDigits="0"/>   
			<acme:message code="administrator.dashboard.form.label.hours"/>
			<fmt:formatNumber value="${averageWorkload}" maxIntegerDigits="0" pattern=".00" var="v5"/>
			${fn:replace(fn:replace(v5, ".", " "), ",", " ")}
			<acme:message code="administrator.dashboard.form.label.minutes"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviation-workload"/>
		</th>
		<td>
			<fmt:formatNumber value="${deviationWorkload}" maxFractionDigits="0"/>  
			<acme:message code="administrator.dashboard.form.label.hours"/> 
			<fmt:formatNumber value="${deviationWorkload}" maxIntegerDigits="0" pattern=".00" var="v6"/>
			${fn:replace(fn:replace(v6, ".", " "), ",", " ")}
			<acme:message code="administrator.dashboard.form.label.minutes"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minimun-workload"/>
		</th>
		<td>
			<fmt:formatNumber value="${minimunWorkload}" maxFractionDigits="0"/>
			<acme:message code="administrator.dashboard.form.label.hours"/>   
			<fmt:formatNumber value="${minimunWorkload}" maxIntegerDigits="0" pattern=".00" var="v7"/>
			${fn:replace(fn:replace(v7, ".", " "), ",", " ")}
			<acme:message code="administrator.dashboard.form.label.minutes"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maximun-workload"/>
		</th>
		<td>
			<fmt:formatNumber value="${maximunWorkload}" maxFractionDigits="0"/>   
			<acme:message code="administrator.dashboard.form.label.hours"/>
			<fmt:formatNumber value="${maximunWorkload}" maxIntegerDigits="0" pattern=".00" var="v8"/>
			${fn:replace(fn:replace(v8, ".", " "), ",", " ")}
			<acme:message code="administrator.dashboard.form.label.minutes"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.workplans-amount"/>
		</th>
		<td>
			<acme:print value="${workPlansAmount}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.unpublished-workplans-amount"/>
		</th>
		<td>
			<acme:print value="${unpublishedWorkPlansAmount}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.published-workplans-amount"/>
		</th>
		<td>
			<acme:print value="${publishedWorkPlansAmount}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.unfinished-workplans-amount"/>
		</th>
		<td>
			<acme:print value="${unfinishedWorkPlansAmount}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.finished-workplans-amount"/>
		</th>
		<td>
			<acme:print value="${finishedWorkPlansAmount}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.private-workplans-amount"/>
		</th>
		<td>
			<acme:print value="${privateWorkPlansAmount}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.public-workplans-amount"/>
		</th>
		<td>
			<acme:print value="${publicWorkPlansAmount}"/>
		</td>
	</tr>					
</table>

<h2>
	<acme:message code="administrator.dashboard.form.title.workplan-statuses"/>
</h2>

<div>
	<canvas id="canvas"></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		
		var data = {
			labels : [
					"TOTAL", "PUBLISHED", "UNPUBLISHED"
			],
			datasets : [
				{
					data : [
						<jstl:out value="${workPlansAmount}"/>,
						<jstl:out value="${publishedWorkPlansAmount}"/>,
						<jstl:out value="${unpublishedWorkPlansAmount}"/>
					]
				}
			]
		};
		var options = {
			scales : {
				yAxes : [
					{
						ticks : {
							suggestedMin : 0.0,
							suggestedMax : <jstl:out value="${workPlansAmount}"/>
						}
					}
				]
			},
			legend : {
				display : false
			}
		};
		
		var canvas, context;
		
		canvas = document.getElementById("canvas");
		context = canvas.getContext("2d");
		new Chart(context, {
			type : "bar",
			data : data,
			options : options
		});
	});
</script>