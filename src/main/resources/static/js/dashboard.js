$(document).ready(function() {

	$('#search-icon1').click(function() {
		$("#dept, #projectType, #client, #empName, #manager, #dp, #dropdown").val("");
		var empId = $('input[name="empId"]').val();
		//  var tableBody = $('#data-table tbody');
		//  tableBody.empty();
		// Make an API request with the empId
		$.ajax({
			url: '/searchAllById/' + empId,
			method: 'GET',
			success: function(data) {
				$.each(data, function(index, item) {
					if (item.project === 'Allocated') {
						$.ajax({
							url: '/getAllocatedEmployee/' + empId,
							method: 'GET',
							success: function(allocatedProjectData) {
								console.log('Allocated Project Details:', allocatedProjectData);

								updateTable(allocatedProjectData);
								//location.reload();
							},
							error: function(error) {
								console.error('Error fetching allocated project details:', error);
							}
						});
					} else {
						updateTable(data);
						// location.reload();
					}
				});
			},
			error: function(error) {
				console.error('Error fetching data from API:', error);
			}
		});


	});
	// Make an API request with the empName
	$('#search-icon3').click(function() {
		$("#dept, #projectType, #client, #employeeId, #manager, #dp, #dropdown").val("");
		var empName = $('input[name="empName"]').val();
		var tableBody = $('#data-table tbody');
		$.ajax({
			url: '/searchAllByName/' + empName,
			method: 'GET',
			success: function(data) {

				//console.log("oye");
				var allocatedEmployees = [];
				var nonAllocatedEmployees = [];

				$.each(data, function(index, item) {
					if (item.project === 'Allocated') {
						allocatedEmployees.push(item);
					} else {
						nonAllocatedEmployees.push(item);
					}
				});
				console.log(allocatedEmployees.length);
				console.log(nonAllocatedEmployees.length);
				if (allocatedEmployees.length > 0) {
					$.ajax({
						url: '/searchByNames/' + empName,
						method: 'GET',
						success: function(allocatedProjectData) {
							console.log('Allocated Project Details:', allocatedProjectData);
							updateTable(allocatedProjectData);
						},
						error: function(error) {
							console.error('Error fetching allocated project details:', error);
						}
					});
				}

				if (nonAllocatedEmployees.length > 0) {
					console.log(nonAllocatedEmployees);
					//  if (data.length === 1) {
					// Empty the table only if there is a single employee
					//     tableBody.empty();
					//  }

					updateTable(nonAllocatedEmployees);
				}
				if (allocatedEmployees.length === 0 && nonAllocatedEmployees.length === 0) {
					alert('No employees found with the given name.');
				}

			},
			//tableBody.empty();
			// tableBody.empty();

			error: function(error) {
				console.error('Error fetching data from API:', error);
			}
		});
	});

	$('#search-icon2').click(function() {
		$("#employeeId, #projectType, #client, #empName, #manager, #dp, #dropdown").val("");
		var department = $('input[name="dept"]').val();
		$.ajax({
			url: '/searchAllByDept/' + department,
			method: 'GET',
			success: function(data) {
				$.each(data, function(index, item) {
					if (item.project === 'Allocated') {
						$.ajax({
							url: '/searchAllByDepts/' + department,
							method: 'GET',
							success: function(allocatedProjectData) {
								console.log('Allocated Project Details:', allocatedProjectData);
								updateTable(allocatedProjectData);
								//updateTable(data);
							},
							error: function(error) {
								console.error('Error fetching allocated project details:', error);
							}
						});
					} else {
						updateTable(data);
					}
				});
			},
			error: function(error) {
				console.error('Error fetching data from API:', error);
			}
		});
	});
	$('#search-icon4').click(function() {
		$("#employeeId, #projectType, #client, #empName, #dept, #dp, #dropdown").val("");
		var manager = $('input[name="manager"]').val();
		$.ajax({
			url: '/searchAllByManager/' + manager,
			method: 'GET',
			success: function(data) {
				console.log("manager ", data);
				var filteredData = [];

				data.forEach(function(employee) {
					var projects = employee.project.filter(function(project) {
						return project.manager === manager;
					});

					if (projects.length > 0) {
						// Create a new object containing only relevant employee and project data
						var filteredEmployee = {
							id: employee.id,
							employeeId: employee.employeeId,
							employeeName: employee.employeeName,
							department: employee.department,
							status: employee.status,
							project: projects
						};

						filteredData.push(filteredEmployee);
					}
				});
				console.log("filteredData ", filteredData);
				updateTable(filteredData); // Pass filtered data to the updateTable function
			},
			error: function(error) {
				console.error('Error fetching data from API:', error);
			}
		});
	});

	$('#search-icon5').click(function() {
		$("#employeeId, #projectType, #dept, #empName, #manager, #dp, #dropdown").val("");
		var client = $('input[name="client"]').val();
		$.ajax({
			url: '/searchAllByClient/' + client,
			method: 'GET',
			success: function(data) {
				var filteredData = data.filter(function(employee) {
					// Check if any project matches the client name
					return employee.project.some(function(project) {
						return project.client === client;
					});
				});

				updateTable(filteredData);
			},
			error: function(error) {
				console.error('Error fetching data from API:', error);
			}
		});
	});
	$("#dropdown").on("change", function() {
		$("#employeeId, #projectType, #client, #empName, #manager, #dp, #dept").val("");
		var selectedValue = $(this).val().trim();
		var tableBody = $('#data-table tbody');
		console.log("Selected Value: " + selectedValue);
		if (selectedValue !== '') {
			$.ajax({
				url: '/searchAllByProject/' + selectedValue,
				method: 'GET',
				success: function(data) {
					// Filter the data to include only employees with the selected project
					var filteredData = [];

					data.forEach(function(employee) {
						var date = employee.allDate;
						var projects = employee.project.filter(function(project) {
							return project.projectName === selectedValue;
						});

						if (projects.length > 0 && date.length > 0) {
							// Create a new object containing only relevant employee and project data
							var filteredEmployee = {
								id: employee.id,
								employeeId: employee.employeeId,
								employeeName: employee.employeeName,
								department: employee.department,
								status: employee.status,
								project: projects,
								dates: date

							};
							console.log("filtered employee " + filteredEmployee);
							filteredData.push(filteredEmployee);
						}
					});
					// Update the table with the filtered data
					console.log(filteredData)
					updateTable(filteredData);
				},
				error: function(error) {
					console.error('Error fetching data from API:', error);
				}
			});
		} else {
			tableBody.empty();
			alert("Plz select correct Project name from dropdown");
		}
	});


	$("#dp").on("change", function() {
		$("#employeeId, #projectType, #client, #empName, #manager, #dept, #dropdown").val("");
		var selectedValue = $(this).val().trim();
		var tableBody = $('#data-table tbody');
		console.log("Selected Value: " + selectedValue);
		var str = "All";
		if (selectedValue === str) {
			console.log("hii");
			$.ajax({
				url: '/searchAllEmp',
				method: 'GET',
				success: function(data) {
					updateTable(data, str);
				},
				error: function(error) {
					console.error('Error fetching data from API:', error);
				}
			});
		} else {
			$.ajax({
				url: '/searchAllByStatus/' + selectedValue,
				method: 'GET',
				success: function(data) {
					updateTable(data);
				},
				error: function(error) {
					console.error('Error fetching data from API:', error);
				}
			});
		}
	});
	$("#projectType").on("change", function() {
		$("#employeeId, #dept, #client, #empName, #manager, #dp, #dropdown").val("");
		var selectedValue = $(this).val().trim();
		console.log(selectedValue);
		var tableBody = $('#data-table tbody');
		if (selectedValue !== "") {
			console.log(selectedValue);
			//var tableBody = $('#data-table tbody');
			$.ajax({
				url: '/findByProjectType/' + selectedValue,
				method: 'GET',
				success: function(data) {
					updateTable(data);
				},
				error: function(error) {
					console.error('Error fetching allocated project details:', error);
				}

			});
		}
		else {
			tableBody.empty();
			alert("select proper allocation");
		}

	});
	//	function updateTable(data,str) {
	//    var tableBody = $('#data-table tbody');
	//    tableBody.empty();
	//    $.each(data, function(index, row) {
	//        var lastProject = row.project[row.project.length - 1]; // Get the last assigned project
	//        console.log("last project " + lastProject.projectName);
	//
	//        var allocateButton = row.status.toLowerCase() === 'inactive' ? '<button id="acc" class="mx-2">Allocate</button>' : '';
	//        var deallocateButton = row.status.toLowerCase() === 'active' ? '<button id="deac" class="mx-2">Deallocate</button>' : '';
	//
	//        var tableRow = '<tr>' +
	//            '<td>' + (index + 1) + '</td>' +
	//            '<td>' + row.employeeName + '</td>' +
	//            '<td>' + row.employeeId + '</td>' +
	//            '<td>' + row.project + '</td>' +
	//            '<td>' + lastProject.projectType + '</td>' + // Access project_type property
	//            '<td>' + lastProject.client + '</td>' +
	//            '<td>' + lastProject.manager + '</td>' +
	//            '<td>' + row.department + '</td>' +
	//            '<td>' + row.status + '</td>' +
	//            '<td>' + lastProject.project_startdate + '</td>' +
	//            '<td>' + lastProject.project_enddate + '</td>' +
	//            '<td>' + allocateButton + deallocateButton + '</td>' +
	//            '</tr>';
	//        tableBody.append(tableRow);
	//    });
	//}

	function updateTable(data, str) {
		if (str === 'All') {
			$('#projectAllocation, #client, #manager, #allocationStartDate, #allocationEndDate, #action').css('display', 'none');
		} else {
			// Ensure they are visible if str is not "All"
			$('#projectAllocation, #client, #manager, #allocationStartDate, #allocationEndDate, #action').css('display', 'table-cell');
		}

		var tableBody = $('#data-table tbody');
		tableBody.empty();
		$.each(data, function(index, row) {
			var lastProject = row.project[row.project.length - 1]; // Get the last assigned project
			// Check if row.dates exists and is an array
			if (Array.isArray(row.dates) && row.dates.length > 0) {
				// Access the last element of row.dates safely
				var lastDate = row.dates[row.dates.length - 1];
				console.log("last date " + lastDate.all_endDate);
				// Rest of your code...
			} else {
				console.error("Row dates property is not an array or is empty.");
			}
			console.log("last project " + lastProject.projectName);
			/*console.log("row " + lastDate.all_endDate);*/
			var allocateButtonStyle = 'background-color: green; color: white;'; // Example styling for "Allocate" button
			var deallocateButtonStyle = 'background-color:red; color: white;';

			var allocateButton = str !== 'All' && row.status.toLowerCase() === 'inactive' ? '<button id="acc" class="mx-2" style="' + allocateButtonStyle + '">Allocate</button>' : '';
			var deallocateButton = str !== 'All' && row.status.toLowerCase() === 'active' ? '<button id="deac" class="mx-2" style="' + deallocateButtonStyle + '">Deallocate</button>' : '';
			var project = str === 'All' ? '<td>' + row.project + '</td>' : '<td>' + lastProject.projectName + '</td>';

			var projectAllocationColumn = str !== 'All' ? '<td>' + lastProject.projectType + '</td>' : '';
			var clientColumn = str !== 'All' ? '<td>' + lastProject.client + '</td>' : '';
			var managerColumn = str !== 'All' ? '<td>' + lastProject.manager + '</td>' : '';
			var startDateColumn = str !== 'All' ? '<td>' + lastProject.project_startdate + '</td>' : '';
			var endDateColumn = str !== 'All' ? '<td>' + lastProject.project_enddate + '</td>' : '';

			var buttonsColumn = str === 'All' ? '' : allocateButton + deallocateButton;

			var tableRow = '<tr>' +
				'<td>' + (index + 1) + '</td>' +
				'<td>' + row.employeeName + '</td>' +
				'<td>' + row.employeeId + '</td>' +
				project +
				projectAllocationColumn +
				clientColumn +
				managerColumn +
				'<td>' + row.department + '</td>' +
				'<td>' + row.status + '</td>' +
				startDateColumn +
				endDateColumn +
				'<td>' + buttonsColumn + '</td>' +
				'</tr>';
			tableBody.append(tableRow);
		});
	}



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