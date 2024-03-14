document.addEventListener('DOMContentLoaded', function() {
	const searchByIdForm = document.getElementById('searchByIdForm');
	const searchByNameForm = document.getElementById('searchByNameForm');

	searchByIdForm.addEventListener('submit', function(event) {
		document.getElementById('searchByNameInput').value = '';
		event.preventDefault();		
		const searchTerm = document.getElementById('searchByIdInput').value;
		
		//document.getElementById('searchByNameInput').value = '';
		
		fetch(`/history/search/id/${searchTerm}`)
			.then(response => response.json())
			.then(data => {
				updateTable(data);
			})
			.catch(error => console.error('Error:', error));
	});

	searchByNameForm.addEventListener('submit', function(event) {
		event.preventDefault();
		document.getElementById('searchByIdInput').value = '';
		const searchTerm = document.getElementById('searchByNameInput').value;

		fetch(`/history/search/name/${searchTerm}`)
			.then(response => response.json())
			.then(data => {
				console.log("history name " + data);
				updateTable(data);
			})
			.catch(error => console.error('Error:', error));
	});
	
		
	function updateTable(data) {
		var tableBody = document.getElementById('tableBody');
		tableBody.innerHTML = '';

		data.forEach(function(rowData) {
			var projects = rowData.project;
            console.log("data "+rowData);
            var date = rowData.allDate;
            console.log(date);
			// If employee is associated with multiple projects, create additional rows
			if (projects.length > 0 && date.length >0) {
				projects.forEach(function(project, index) {
					var row = tableBody.insertRow();
					if (index === 0) { // For the first project, include employee details in the current row
						row.insertCell(0).innerHTML = rowData.employeeId;
						row.insertCell(1).innerHTML = rowData.employeeName;
					} else { // For subsequent projects, leave the employee details blank
						row.insertCell(0).innerHTML = rowData.employeeId;
						row.insertCell(1).innerHTML = rowData.employeeName;
					}
                    console.log(rowData.allDate.all_startDate);
					// Insert project details
					row.insertCell(2).innerHTML = project.projectName;
					row.insertCell(3).innerHTML = project.projectType;
					row.insertCell(4).innerHTML = project.client || 'N/A';
					row.insertCell(5).innerHTML = project.manager || 'N/A';
					row.insertCell(6).innerHTML = rowData.department || 'N/A';
//					row.insertCell(7).innerHTML = project.status || 'N/A';
					row.insertCell(7).innerHTML = date[index].all_startDate;
					row.insertCell(8).innerHTML = date[index].all_endDate;
				});
			} else { // If employee has no projects
				var row = tableBody.insertRow();
				row.insertCell(0).innerHTML = rowData.employeeId;
				row.insertCell(1).innerHTML = rowData.employeeName;
				row.insertCell(2).innerHTML = 'N/A';
				row.insertCell(3).innerHTML = 'N/A';
				row.insertCell(4).innerHTML = 'N/A';
				row.insertCell(5).innerHTML = 'N/A';
				row.insertCell(6).innerHTML = rowData.department || 'N/A';
//				row.insertCell(7).innerHTML = project.status || 'N/A';
				row.insertCell(7).innerHTML = 'N/A';
				row.insertCell(8).innerHTML = 'N/A';
			}
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