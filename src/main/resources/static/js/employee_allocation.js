$(document).ready(function() {
            $("#employeeId").on("blur", function() {
                var employeeId = $(this).val();
                console.log("Employee Id"+employeeId);
                if (employeeId.trim() !== '') {
                    // Make an AJAX call to retrieve data based on the employeeId
                    $.ajax({
                        url: '/hello/' + employeeId, // Use the correct endpoint
                        type: 'GET',
                        success: function(data) {
                            if (data) {
								console.log("hii");
                                $("#employeeName").val(data.employeeName);
                                $("#department").val(data.department);
                                //console.log(data.project.projectName);
                               // $("#dropdown").val(data.project.projectName);
                                // Handle other fields similarly
                            } else {
                                alert("No data found for the given employee ID");
                            }
                        },
                        error: function() {
                            alert("Error fetching employee data");
                        }
                    });
                }
            });
               
       $("#dropdown, #projectAllocation").on("change", function () {
        var selectedValue = $(this).val().trim();
        console.log("Selected Value: " + selectedValue);
        if (selectedValue !== '') {
            fetchDataBasedOnDropdown(selectedValue);
        }
    });
    
       function fetchDataBasedOnDropdown(selectedValue) {
        $.ajax({
            url: '/api/' + selectedValue, // Replace with the correct endpoint based on the selected dropdown
            type: 'GET',
            success: function (data) {
                if (data) {
                    console.log("Dropdown Data:", data);
                     $("#reportingManager").val(data.manager);
                     $("#client").val(data.client);
                    // Update the UI with the details from the dropdown data
                    // For example, if the data has a property 'projectName', you can do:
                    // $("#projectDetails").text("Project Details: " + data.projectName);
                } else {
                    alert("No data found for the selected value");
                }
            },
            error: function () {
                alert("Error fetching data for the selected value");
            }
        });
       }


        });