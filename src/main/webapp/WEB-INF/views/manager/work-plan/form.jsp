<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>


	<acme:form-moment code="manager.work-plan.form.label.periodStart"
		path="periodStart" />
	<jstl:if test="${command == 'create' || command =='update'}">
		<acme:print value="${periodStart}" />
	</jstl:if>
	<jstl:if test="${command == 'create' || command =='update'}">
		<table class="table table-sm">
	<caption>
		<acme:message code="manager.work-plan.form.label.recommendations"/>
	</caption>	
			<tr>
				<th scope="row"><acme:message
						code="manager.work-plan.form.label.recommendedYearStart" /></th>
				<td>
					<p id="yearStart">1970</p>
				</td>
			</tr>

			<tr>
				<th scope="row"><acme:message
						code="manager.work-plan.form.label.recommendedMonthStart" /></th>
				<td>
					<p id="monthStart">06</p>
				</td>
			</tr>

			<tr>
				<th scope="row"><acme:message
						code="manager.work-plan.form.label.recommendedDayStart" /></th>
				<td>
					<p id="dayStart">06</p>
				</td>
			</tr>
			
			<tr>
				<th scope="row"><acme:message
						code="manager.work-plan.form.label.hourDayStart" /></th>
				<td>
					<p id="hourStart">8:00</p>
				</td>
			</tr>
		</table>
	</jstl:if>

	<acme:form-moment code="manager.work-plan.form.label.periodEnd"
		path="periodEnd" />
	<jstl:if test="${command == 'create' || command =='update'}">
		<acme:print value="${periodEnd}" />
	</jstl:if>
	<jstl:if test="${command == 'create' || command =='update'}">
		<table class="table table-sm">
			<caption>
		<acme:message code="manager.work-plan.form.label.recommendations"/>
	</caption>	
			<tr>
				<th scope="row"><acme:message
						code="manager.work-plan.form.label.recommendedYearEnd" /></th>
				<td>
					<p id="yearEnd">1970</p>
			<tr>

				<th scope="row"><acme:message
						code="manager.work-plan.form.label.recommendedMonthEnd" /></th>
				<td>
					<p id="monthEnd">06</p>
				</td>
			</tr>
			<tr>
				<th scope="row"><acme:message
						code="manager.work-plan.form.label.recommendedDayEnd" /></th>
				<td>
					<p id="dayEnd">07</p>
				</td>
			</tr>
			
			<tr>
				<th scope="row"><acme:message
						code="manager.work-plan.form.label.hourDayEnd" /></th>
				<td>
					<p id="hourEnd">17:00</p>
				</td>
			</tr>
		</table>
	</jstl:if>

	<jstl:if test="${command == 'show'}">
		<acme:form-double code="manager.work-plan.form.label.workload" path="workload" readonly="true"/>
	</jstl:if>

	<jstl:if test="${command == 'create'}">
		<acme:form-checkbox code="manager.work-plan.form.label.state"
			path="state" />
	</jstl:if>

	<jstl:if test="${command == 'show' || command =='publish'}">
		<acme:form-checkbox code="manager.work-plan.form.label.state"
			path="state" readonly="true" />
	</jstl:if>
	
	<jstl:if test="${command == 'show'}">
		<acme:form-checkbox code="manager.work-plan.form.label.finished" path="finished" readonly="true"/>
	</jstl:if>

	<jstl:if test="${command == 'create' || command =='update'}">
		<acme:form-select code="manager.work-plan.form.label.tasks"
			path="tasks" multiple="true">
			<jstl:forEach items="${tasks}" var="task">
				<acme:form-option
					code="${task.title} - periodStart: ${task.periodStart}, periodEnd: ${task.periodEnd}. isPublic: ${task.state}"
					value="${task}" />
			</jstl:forEach>
		</acme:form-select>
	</jstl:if>

	<jstl:if test="${command == 'show' && finished == 'false'}">
		<acme:form-select code="manager.work-plan.form.label.tasks"
			path="tasks" multiple="true">
			<jstl:forEach items="${allTasks}" var="task">
				<acme:form-option
					code="${task.title} - periodStart: ${task.periodStart}, periodEnd: ${task.periodEnd}. isPublic: ${task.state}"
					value="${task}" />
			</jstl:forEach>
		</acme:form-select>
	</jstl:if>

	<jstl:if test="${command == 'show' || command =='publish'}">
		<br>
		<h3>
			<acme:message code="manager.work-plan.form.label.tasks" />
		</h3>

		<table class="table">
			<thead>
				<tr>
					<th scope="col"><acme:message
							code="manager.work-plan.list.label.periodStart" /></th>
					<th scope="col"><acme:message
							code="manager.work-plan.list.label.periodEnd" /></th>
					<th scope="col"><acme:message
							code="manager.work-plan.list.label.workload" /></th>
					<th scope="col"><acme:message
							code="manager.work-plan.list.label.state" /></th>
				</tr>
			</thead>
			<tbody>
				<jstl:forEach items="${tasks}" var="task">
					<tr>
						<td><acme:print value="${task.periodStart}" /></td>
						<td><acme:print value="${task.periodEnd}" /></td>
						<td><acme:print value="${task.workload}" /></td>
						<td><jstl:choose>
								<jstl:when test="${task.state == true}">
									<acme:message code="manager.work-plan.list.value.true" />
								</jstl:when>
								<jstl:otherwise>
									<acme:message code="manager.work-plan.list.value.false" />
								</jstl:otherwise>
							</jstl:choose></td>
					</tr>
				</jstl:forEach>
			</tbody>
		</table>
	</jstl:if>

	<acme:form-submit test="${command == 'show' && finished == 'false'}"
		code="manager.work-plan.form.button.update"
		action="/manager/work-plan/update" />
	<acme:form-submit test="${command == 'show' && finished == 'false'}"
		code="manager.work-plan.form.button.delete"
		action="/manager/work-plan/delete" />
	<acme:form-submit
		test="${command == 'show' && finished == 'false' && state == 'false'}"
		code="manager.work-plan.form.button.publish"
		action="/manager/work-plan/publish" />
	<acme:form-submit test="${command == 'create'}"
		code="manager.work-plan.form.button.create"
		action="/manager/work-plan/create" />
	<acme:form-submit test="${command == 'update'}"
		code="manager.work-plan.form.button.update"
		action="/manager/work-plan/update" />
	<acme:form-submit test="${command == 'delete'}"
		code="manager.work-plan.form.button.delete"
		action="/manager/work-plan/delete" />

	<acme:form-return code="manager.work-plan.form.button.return" />
</acme:form>

<script>
	document.getElementById('tasks').addEventListener('change',function(){
	    var selected = [];
	    var min = [];
	    var max = [];
	    for (var option of document.getElementById('tasks').options)
	    {
	        if (option.selected) {
	        	var periodStart = option.innerText.split(',')[0].split(":")[1].split(" ")[1];
	        	var periodEnd = option.innerText.split(',')[1].split(" ")[2];
        		var earliestDate = new Date(periodStart);
        		var latestDate = new Date(periodEnd);
        		
	        	if(min.length==0){
	        		min.push(earliestDate);
	        	}
	        	
	        	else{
	        		if(min[0] >= earliestDate){
	        			min.pop();
	        			min.push(earliestDate);
	        		}
	        	}
	        	
	        	if(max.length==0){
	        		max.push(latestDate);
	        	}
	        	
	        	else{
	        		if(max[0] < latestDate){
	        			max.pop();
	        			max.push(latestDate);
	        		}
	        		
	        	}
	        }
	    }
	    
	    max[0].setDate(max[0].getDate() + 1);
	    min[0].setDate(min[0].getDate() - 1);
	    document.getElementById("yearStart").innerHTML = min[0].getFullYear();
	    document.getElementById("monthStart").innerHTML = min[0].getMonth() + 1;
	    document.getElementById("dayStart").innerHTML = min[0].getDate();
	    
	    document.getElementById("yearEnd").innerHTML = max[0].getFullYear();
	    document.getElementById("monthEnd").innerHTML = max[0].getMonth() + 1;
	    document.getElementById("dayEnd").innerHTML = max[0].getDate();
	});
	</script>
