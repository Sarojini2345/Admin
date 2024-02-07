package com.ldtech.serviceImpl;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ldtech.dao.DashboardRepository;

import com.ldtech.entity.EmployeeAllocation;
import com.ldtech.service.IHistoryService;

@Service
public class HistoryServiceImpl implements IHistoryService {
	
	@Autowired
	private DashboardRepository historyRepo;

	@Override
	public List<EmployeeAllocation> searchByEmployeeId(String searchTerm) {
		
		List<EmployeeAllocation> listIds = historyRepo.findByEmployeeIdContainingIgnoreCase(searchTerm);
		// TODO Auto-generated method stub
		return listIds;
	}

	@Override
	public List<EmployeeAllocation> searchByEmployeeName(String searchTerm) {
		
		List<EmployeeAllocation> listNames = historyRepo.findByEmployeeNameContainingIgnoreCase(searchTerm);
		// TODO Auto-generated method stub
		return listNames;
	}

//	@Override
//	public List<EmployeeAllocation> findByEmployeeIdContainingIgnoreCaseAndProject_ProjectStartdateBetweenAndProject_ProjectEnddateBetween(
//			String searchTerm, Date fromDate, Date toDate) {
//		// TODO Auto-generated method stub
//		return historyRepo.findByEmployeeIdContainingIgnoreCaseAndProject_Project_startdateBetweenAndProject_Project_enddateBetween(searchTerm, fromDate, toDate);
//	}

	@Override
	public List<EmployeeAllocation> findByEmployeeNameContainingIgnoreCaseAndProject_ProjectStartdateBetweenAndProject_ProjectEnddateBetween(
			String searchTerm, Date fromDate, Date toDate) {
		// TODO Auto-generated method stub
		return historyRepo.findByEmployeeNameAndProjectDates(searchTerm, fromDate, toDate);
	}


//	@Override
//	public List<EmployeeAllocation> searchByEmployeeIdOrName(String searchTerm) {
//		// TODO Auto-generated method stub
//		List<EmployeeAllocation> list = historyRepo.findByEmployeeIdOrEmployeeNameContainingIgnoreCase(searchTerm, searchTerm);
//		return list;
//	}

}
