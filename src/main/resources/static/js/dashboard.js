$(document).ready(function() {
	
    $('#search-icon1').click(function() {
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
        var str="All";
        if(selectedValue===str){
			 console.log("hii");
			 $.ajax({
            url: '/searchAllEmp',
            method: 'GET',
            success: function(data) {
				//updateTable(data);
				 $.each(data, function(index, item) {
                    if (item.project === 'Allocated') {
                    $.ajax({
                        url: '/searchAll',
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
		 }
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
     $("#projectType").on("change", function () {
        var selectedValue = $(this).val().trim();
        console.log(selectedValue);
         var tableBody = $('#data-table tbody');
           $.ajax({
            url: '/findByProjectType/'+selectedValue,
            method: 'GET',
            success: function(data) {
				updateTable(data);
				
			},
		     error: function(error) {
                            
               console.error('Error fetching allocated project details:', error);
               
             }	
         
         });
         
      });
    function updateTable(data) {
        var tableBody = $('#data-table tbody');
       tableBody.empty();
        $.each(data, function(index, row) {
			console.log(row); 
            var tableRow = '<tr>' +
                '<td>' + (index + 1) + '</td>' +
                '<td>' + row.employeeName + '</td>' +
                '<td>' + row.employeeId + '</td>' +
                '<td>' + row.project.projectName + '</td>' +
                '<td>' + row.project.projectType + '</td>' +
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