<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Add Store Form</title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
	
	<script type="text/javascript">

function myFun(loc_id){
	 customerList = new Array();
	var i = 0;
	var j = 0;
	var myDiv = document.getElementById("selectDiv");
	var loc_id = document.getElementById("locId").value;
	document.getElementById("custId").innerHTML = "";
	var select = document.getElementById("custId");
	
	var option = document.createElement("option");
    option.value = "";
    option.text = "-----Select----";
    select.appendChild(option);
	//alert(loc_id)
	<c:forEach var="customer" items='${customers}'>
	//alert("${customer.location}")alert("${customer.location == loc_id}")
    if (("${customer.location}" == loc_id))
    	{
    	//customerList[i++] = "${customer}";
    	
    	var option = document.createElement("option");
        option.value = "${customer.id}";
        option.text = "${customer.firstName}" + "  ${customer.lastName}";
        select.appendChild(option);
    	}
  </c:forEach>
  
  
}

function calculateTotalAmount(){
	var weight = document.getElementById("weight").value;
	var rate = document.getElementById("rate").value;
	document.getElementById("totalAmount").value = parseFloat(weight * rate);
	
	var totalAmount = document.getElementById("totalAmount").value;
	var totalExp = document.getElementById("totalExpense").value;
	
	document.getElementById("dueAmount").value = parseFloat(totalAmount) - parseFloat(totalExp);
}

function calculateTotalExpenses(){
	var quantity = document.getElementById("quantity").value;
	var rate = document.getElementById("expPerItem").value;
	var otherExp = document.getElementById("otherExpense").value;
	document.getElementById("totalExpense").value = parseFloat(quantity * rate) + parseFloat(otherExp);
	
	var totalAmount = document.getElementById("totalAmount").value;
	var totalExp = document.getElementById("totalExpense").value;
	
	document.getElementById("dueAmount").value = parseFloat(totalAmount) - parseFloat(totalExp);
}

function updateSubTotal(){
	var totalAmount = document.getElementById("totalAmount").value;
	var totalExp = document.getElementById("totalExpense").value;
	var percentage = document.getElementById("deductionPercent").value;
	var dueAmount = parseFloat(totalAmount) - parseFloat(totalExp);
	var deductedAmount = (parseFloat(dueAmount) * parseFloat(percentage))/100;
	document.getElementById("dueAmount").value = parseFloat(dueAmount - deductedAmount);
}

</script>
</head>

<body>
 		<div  class="well">
		<div class="none"> </div>
	 	<form:form method="POST" modelAttribute="transaction" class="form-horizontal">
			<form:input type="hidden" path="id" id="id"/>
			<div class="row">
				<div class="form-group col-md-12" id="selectDiv">
					<label class="col-md-3 control-lable" for="locId">Location: </label>
					<div class="col-md-7">
						<select onchange="myFun(this)" name="locId" id="locId">
						<option value="">----Select----</option>
						<c:forEach items="${locations}" var="loc" varStatus="letterCounter">
							<option value="${loc.location}" ${loc.location == locId ? 'selected="selected"' : ''}>${loc.location}</option>
						</c:forEach>
					    </select>
					    <select name="custId" id="custId">
					    <option value="">Select</option>
						<c:forEach items="${customerList}" var="cust" varStatus="letterCounter">
							<option value="${cust.id}">${cust.firstName} ${cust.lastName}</option>
						</c:forEach>
						</select>
						<div class="has-error">
							<form:errors path="custId" class="help-inline"/>
						</div>
						
					</div>
				</div>	
		 
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="activityCreateDate">Creation Date(YYYY-MM-dd)</label>
					<div class="col-md-7">
						<form:input type="date" path="activityCreateDate" id="activityCreateDate" class="input-group-sm input-sm"/>
						<div class="has-error">
							<form:errors path="activityCreateDate" class="help-inline"/>
						</div>
					</div>
				</div>
			
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="productType">Product Type</label>
					<div class="col-md-7">
					 <select name="productType" id="productType">
					    <option value="Dhan">Dhan(Rice)</option>
					    <option value="Gehu">Gehu(Wheat)</option>
					    <option value="Sarso">Sarso(Mustered)</option>
					    <option value="Bajra">Bajra(Bajra)</option>
					    <option value="Jaee">Jaee(Oat)</option>
					    <option value="Gawar">Gawar(Khurti)</option>
					    <option value="Moong">Moong(Moong)</option>
					  </select>
						<div class="has-error">
							<form:errors path="productType" class="help-inline"/>
						</div>
					</div>
				</div>
			
			</div>
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="weight">Weight(Kg Only)</label>
					<div class="col-md-7">
						<form:input type="text" path="weight" id="weight" class="input-group-sm input-sm" onchange="calculateTotalAmount()" value="0"/>
						<div class="has-error">
							<form:errors path="weight" class="help-inline"/>
						</div>
					</div>
					<label class="col-md-3 control-lable" for="rate">Rate per Kg </label>
					<div class="col-md-7">
						<form:input type="text" path="rate" id="rate" class="input-group-sm input-sm"  onchange="calculateTotalAmount()" value="0"/>
						<div class="has-error">
							<form:errors path="rate" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
			
			
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="quantity">No of Piece(10.5 bori)</label>
					<div class="col-md-7">
						<form:input type="text" path="quantity" id="quantity" class="input-group-sm input-sm" value="0"/>
						<div class="has-error">
							<form:errors path="quantity" class="help-inline"/>
						</div>
					</div>
					<label class="col-md-3 control-lable" for="totalAmount">Total Amount </label>
					<div class="col-md-7">
						<form:input type="text" path="totalAmount" id="totalAmount" class="input-group-sm input-sm" readonly="true"/>
						<div class="has-error">
							<form:errors path="totalAmount" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
			
			
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="expPerItem">Kharcha per Piece</label>
					<div class="col-md-7">
						<form:input type="text" path="expPerItem" id="expPerItem" class="input-group-sm input-sm"  onchange="calculateTotalExpenses()" value="0"/>
						<div class="has-error">
							<form:errors path="expPerItem" class="help-inline"/>
						</div>
					</div>
					<label class="col-md-3 control-lable" for="otherExpense">Other Kharcha</label>
					<div class="col-md-7">
						<form:input type="text" path="otherExpense" id="otherExpense" class="input-group-sm input-sm" onchange="calculateTotalExpenses()" value="0"/>
						<div class="has-error">
							<form:errors path="otherExpense" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
			
			
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="deductionPercent">Nagad % rate</label>
					<div class="col-md-7">
						<form:input type="text" path="deductionPercent" id="deductionPercent" class="input-group-sm input-sm" value="0" onchange="updateSubTotal()"/>
						<div class="has-error">
							<form:errors path="deductionPercent" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="totalExpense">Total Kharcha</label>
					<div class="col-md-7">
						<form:input type="text" path="totalExpense" id="totalExpense" class="input-group-sm input-sm" readonly="true"/>
						<div class="has-error">
							<form:errors path="totalExpense" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="dueAmount">Total Baaki</label>
					<div class="col-md-7">
						<form:input type="text" path="dueAmount" id="dueAmount" class="input-group-sm input-sm" readonly="true"/>
						<div class="has-error">
							<form:errors path="dueAmount" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-actions floatRight">
					<c:choose>
						<c:when test="${edit}">
							<input type="submit" value="Update" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/home' />">Cancel</a>
						</c:when>
						<c:otherwise>
							<input type="submit" value="Create" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/home' />">Cancel</a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</form:form>
</div>
</body>
</html>