document.addEventListener('DOMContentLoaded', function() {
	const searchByIdForm = document.getElementById('searchByIdForm');
	const searchByNameForm = document.getElementById('searchByNameForm');

	// Assuming you have some event listeners to capture the selected dates
	const fromDateInput = document.getElementById('fromDateInput');
	const toDateInput = document.getElementById('toDateInput');


	searchByIdForm.addEventListener('submit', function(event) {
		event.preventDefault();
		const searchTerm = document.getElementById('searchByIdInput').value;

		// Make an AJAX request to the Spring Boot backend with path variable
		fetch(`/history/search/id/${searchTerm}`)
			.then(response => response.json())
			.then(data => {
				// Call the function to update the table with the fetched data
				updateTable(data);
			})
			.catch(error => console.error('Error:', error));
	});

		searchByNameForm.addEventListener('submit', function(event) {
		event.preventDefault();
		const searchTerm = document.getElementById('searchByNameInput').value;

		// Make an AJAX request to the Spring Boot backend with path variable
		fetch(`/history/search/name/${searchTerm}`)
			.then(response => response.json())
			.then(data => {
				// Call the function to update the table with the fetched data
				updateTable(data);
			})
			.catch(error => console.error('Error:', error));
	});
	//search by id

	const searchByDateForm = document.getElementById('searchByDateForm');
    searchByDateForm.addEventListener('submit', function(event) {
        event.preventDefault();
        const fromDate = fromDateInput.value;
        const toDate = toDateInput.value;
        fetch(`/history/search/dateRange?fromDate=${fromDate}&toDate=${toDate}`)
            .then(response => response.json())
            .then(data => {
                updateTable(data);
            })
            .catch(error => console.error('Error:', error));
    });



	// Function to update the HTML table with the fetched data
	function updateTable(data) {
		// Get the table body element
		var tableBody = document.getElementById('tableBody');

		// Clear existing rows
		tableBody.innerHTML = '';

		// Loop through the fetched data and add rows to the table
		data.forEach(function(rowData) {
			var row = tableBody.insertRow();

			// Add cells with data
			row.insertCell(0).innerHTML = rowData.employeeId;
			row.insertCell(1).innerHTML = rowData.employeeName;
			row.insertCell(2).innerHTML = rowData.project.projectName;
			row.insertCell(3).innerHTML = rowData.project.project_type;
			row.insertCell(4).innerHTML = rowData.project.client;
			row.insertCell(5).innerHTML = rowData.project.manager;
			row.insertCell(6).innerHTML = rowData.department;
			row.insertCell(7).innerHTML = rowData.status;
			row.insertCell(8).innerHTML = rowData.project.project_startdate;
			row.insertCell(9).innerHTML = rowData.project.project_enddate;
		});
	}
});