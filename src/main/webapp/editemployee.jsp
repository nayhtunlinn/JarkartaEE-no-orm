<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>


<div id="editEmployeeModal" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<form action="<%=request.getContextPath()%>/update" method="POST">
				<div class="modal-header">
					<h4 class="modal-title">Edit Employee</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label>Name</label> <input type="text" ng-model="employee.name" name="name" class="form-control"
							required>
					</div>
					<div class="form-group">
						<label>Email</label> <input type="email" class="form-control" ng-model="employee.email" name="email"
							required>
					</div>
					<div class="form-group">
						<label>Address</label>
						<textarea class="form-control" ng-model="employee.address" name="address" required></textarea>
					</div>
					<div class="form-group">
						<label>Phone</label> <input type="text" class="form-control" ng-model="employee.phone" name="phone"
							required>
					</div>
				</div>
				<div class="modal-footer">
					<input type="hidden" name="id" value="{{employee.id}}"/>
					<input type="button" class="btn btn-default" data-dismiss="modal"
						value="Cancel"> <input type="submit" class="btn btn-info"
						value="Save">
				</div>
			</form>
		</div>
	</div>
</div>