$(document).ready(function() {
    $('#search-icon1').click(function() {
        var empId = $('input[name="empId"]').val();
       

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
		 var empName = $('input[name="empName"]').val();
		$.ajax({
            url: '/searchAllByName/' + empName,
            method: 'GET',
            success: function(data) {
                $.each(data, function(index, item) {
                    if (item.project === 'Allocated') {
                    $.ajax({
                        url: '/searchByNames/' + empName,
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
	$('#search-icon2').click(function() {
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
		 var manager = $('input[name="manager"]').val();
		$.ajax({
            url: '/searchAllByManager/' + manager,
            method: 'GET',
            success: function(data) {
				updateTable(data);
            },
            error: function(error) {
                console.error('Error fetching data from API:', error);
            }
        });		
	});
	 $('#search-icon5').click(function() {
		 var client = $('input[name="client"]').val();
		$.ajax({
            url: '/searchAllByClient/' + client,
            method: 'GET',
            success: function(data) {
				updateTable(data);
            },
            error: function(error) {
                console.error('Error fetching data from API:', error);
            }
        });		
	});
	$("#dropdown").on("change", function () {
        var selectedValue = $(this).val().trim();
         var tableBody = $('#data-table tbody');
        console.log("Selected Value: " + selectedValue);
         if (selectedValue !== '') {
		$.ajax({
            url: '/searchAllByProject/' + selectedValue,
            method: 'GET',
            success: function(data) {
				updateTable(data);
            },
            error: function(error) {
                console.error('Error fetching data from API:', error);
            }
         });	 
		 }
		 else{
			    tableBody.empty();			  
			 alert("Plz select correct Project name from dropdown");
		 }
     });
     $("#dp").on("change", function () {
        var selectedValue = $(this).val().trim();
         var tableBody = $('#data-table tbody');
        console.log("Selected Value: " + selectedValue);
         if (selectedValue !== '') {
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
		 else{
			    tableBody.empty();			  
			 alert("Plz select correct Project name from dropdown");
		 }
     });
    function updateTable(data) {
        var tableBody = $('#data-table tbody');
        tableBody.empty();

        $.each(data, function(index, row) {
            var tableRow = '<tr>' +
                '<td>' + (index + 1) + '</td>' +
                '<td>' + row.employeeName + '</td>' +
                '<td>' + row.employeeId + '</td>' +
                '<td>' + row.project.projectName + '</td>' +
                '<td>' + row.project.project_type + '</td>' +
                '<td>' + row.project.client + '</td>' +
                '<td>' + row.project.manager + '</td>' +
                '<td>' + row.department + '</td>' +
                '<td>' + row.status + '</td>' +
                '<td>' + row.project.project_startdate + '</td>' +
                '<td>' + row.project.project_enddate + '</td>' +
                '</tr>';
            tableBody.append(tableRow);
      });
    }
});