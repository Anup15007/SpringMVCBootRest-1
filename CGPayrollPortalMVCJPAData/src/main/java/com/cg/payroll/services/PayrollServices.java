package com.cg.payroll.services;

import java.util.List;
import com.cg.payroll.beans.Associate;
import com.cg.payroll.beans.BankDetails;
import com.cg.payroll.beans.Salary;
import com.cg.payroll.exceptions.AssociateDetailNotfoundException;

public interface PayrollServices {
	Associate acceptAssociateDetails(Associate associate);
	int calculateNetSalary(int associateId)throws AssociateDetailNotfoundException;
	Associate getAssociateDetails(int associateId)throws AssociateDetailNotfoundException;
	List<Associate>getAllAssociateDetails();
	public List<Associate> getAllAssociateByName(String firstName);
}
