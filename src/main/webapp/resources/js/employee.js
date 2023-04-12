$(document).ready(function() {

   $('#table_id').DataTable();
	// Activate tooltip
	$('[data-toggle="tooltip"]').tooltip();

	// Select/Deselect checkboxes
	var checkbox = $('table tbody input[type="checkbox"]');
	$("#selectAll").click(function() {
		if (this.checked) {
			checkbox.each(function() {
				this.checked = true;
			});
		} else {
			checkbox.each(function() {
				this.checked = false;
			});
		}
	});
	checkbox.click(function() {
		if (!this.checked) {
			$("#selectAll").prop("checked", false);
		}
	});


	$('#deleteBtn').click(function() {

		var deletedEmployees = [];
		$("input:checkbox[class='employeeCheckBox']:checked").each(function() {
			deletedEmployees.push($(this).val());
		});

		deletedEmployees = deletedEmployees.join(",");
		var employeeIds = deletedEmployees.toString();

		$.ajax(
			{
				url: '/Employee-Crud-App/delete',
				async: false,
				type: "POST",
				data: { "employeeIds": employeeIds },
				success: function(data, textStatus, jqXHR) {
					if (data != null) {
						response = data;
					} else {
						response = '';
					}
					window.location.href = '/Employee-Crud-App/';
				},
				error: function(jqXHR, textStatus, errorThrown) {
					console.log("something went wrong==>", errorThrown);
					response = '';
					alert('exception,errorThrown==>' + errorThrown);
				}
			}
		);

	});
});