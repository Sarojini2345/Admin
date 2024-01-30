$(document).ready(function() {
    $('.search-icon').click(function() {
        var empId = $('input[name="empId"]').val();
        var empName = $('input[name="empName"]').val();

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

        // Make an API request with the empName
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
