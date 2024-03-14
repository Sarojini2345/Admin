$(document).ready(function() {

    $('#search-icon1').click(function() {
        $("#dept, #projectType, #client, #empName, #manager, #dp, #dropdown").val("");
        console.log("hi");
        var empId = $('input[name="empId"]').val();
        
        var tableBody = $('#data-table tbody');
        // Clear previous table rows
        tableBody.empty();
        // Make an API request with the empId
        $.ajax({
            url: '/benchEmployee/' + empId,
            method: 'GET',
            success: function(data) {
                console.log(data);
                // Check if data is an empty object
                if (Object.keys(data).length === 0 && data.constructor === Object) {
                    // Show alert if no bench employee found
                    alert("No Bench Employee with this Id");
                } else {
                    // Construct table row if bench employee found
                    const lastAllocationDate = new Date(data.all_end_date);
                    const today = new Date();
                    const diffTime = Math.abs(today - lastAllocationDate);
                    const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));
                    // Create a new table row
                    var newRow = $('<tr>');
                    // Add table data cells for each field
                    newRow.append($('<td>').text(data.employee_id));
                    newRow.append($('<td>').text(data.employee_name));
                    newRow.append($('<td>').text(data.designation));
                    newRow.append($('<td>').text(data.all_end_date));
                    newRow.append($('<td>').text(diffDays));
                    // Append the new row to the table body
                    tableBody.append(newRow);
                }
            },
            error: function(error) {
                console.error('Error fetching data from API:', error);
            }
        });
    });
       
        $('#search-icon2').click(function() {
        $(" #empName, #employeeId").val("");
        console.log("hi");
        var dept = $('input[name="dept"]').val();
        var tableBody = $('#data-table tbody');
        console.log("emp",dept);
        // Clear previous table rows
        tableBody.empty();
        // Make an API request with the empId
        $.ajax({
            url: '/benchEmployees/' + dept,
            method: 'GET',
            success: function(data) {
                console.log(data);
                // Check if data is an empty object
                if (!data || (Array.isArray(data) && data.length === 0)) {
                    // Show alert if no bench employee found
                    alert("No Bench Employee in the given department");
                } else {
					 data.forEach(function(employee) {
                    // Construct table row if bench employee found
                    const lastAllocationDate = new Date(employee.all_end_date);
                    const today = new Date();
                    const diffTime = Math.abs(today - lastAllocationDate);
                    const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));
                    // Create a new table row
                    var newRow = $('<tr>');
                    // Add table data cells for each field
                    newRow.append($('<td>').text(employee.employee_id));
                    newRow.append($('<td>').text(employee.employee_name));
                    newRow.append($('<td>').text(employee.designation));
                    newRow.append($('<td>').text(employee.all_end_date));
                    newRow.append($('<td>').text(diffDays));
                    // Append the new row to the table body
                    tableBody.append(newRow);
                    });
                }
            },
            error: function(error) {
                console.error('Error fetching data from API:', error);
            }
        });
    });
       
       
        $('#search-icon3').click(function() {
        $("#dept, #projectType, #client, #empName, #manager, #dp, #dropdown").val("");
        console.log("hi");
        var empName = $('input[name="empName"]').val();
        var tableBody = $('#data-table tbody');
        console.log("emp",empName);
        // Clear previous table rows
        tableBody.empty();
        // Make an API request with the empId
        $.ajax({
            url: '/benchEmployee/name/' + empName,
            method: 'GET',
            success: function(data) {
                console.log(data);
                // Check if data is an empty object
                if (!data || (Array.isArray(data) && data.length === 0)) {
                    // Show alert if no bench employee found
                    alert("No Bench Employee with this name");
                } else {
					 data.forEach(function(employee) {
                    // Construct table row if bench employee found
                    const lastAllocationDate = new Date(employee.all_end_date);
                    const today = new Date();
                    const diffTime = Math.abs(today - lastAllocationDate);
                    const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));
                    // Create a new table row
                    var newRow = $('<tr>');
                    // Add table data cells for each field
                    newRow.append($('<td>').text(employee.employee_id));
                    newRow.append($('<td>').text(employee.employee_name));
                    newRow.append($('<td>').text(employee.designation));
                    newRow.append($('<td>').text(employee.all_end_date));
                    newRow.append($('<td>').text(diffDays));
                    // Append the new row to the table body
                    tableBody.append(newRow);
                    });
                }
            },
            error: function(error) {
                console.error('Error fetching data from API:', error);
            }
        });
    });
});
function toggleSidebar() {
	const sidebar = document.getElementById('sidebar');
	const maincontent = document.getElementById('maincontent');
	const menuItems = document.querySelectorAll('.menu-item');

	// Toggle minimized class for sidebar and maincontent
	sidebar.classList.toggle('minimized');
	maincontent.classList.toggle('minimized');

	// Toggle minimized class for menu items
	menuItems.forEach(item => {
		item.classList.toggle('minimized');
	});
}