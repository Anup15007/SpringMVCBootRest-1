package com.cg.payroll.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.cg.payroll.beans.Associate;
import com.cg.payroll.beans.BankDetails;
import com.cg.payroll.beans.Salary;
import com.cg.payroll.daoservices.AssociateDAO;

import com.cg.payroll.exceptions.AssociateDetailNotfoundException;
@Component("payrollServices")
public class PayrollServicesImpl implements PayrollServices{
	@Autowired
	private AssociateDAO associateDao;
	@Override
	public Associate acceptAssociateDetails(Associate associate) {
		associate = associateDao.save(associate);
		return associate;
	}
	@Override
	public int calculateNetSalary(int associateId) throws AssociateDetailNotfoundException {
		Associate associate = getAssociateDetails(associateId);
		associate.getSalary().setHra((int)(associate.getSalary().getBasicSalary()*0.3));
		associate.getSalary().setConveyenceAllowance((int)(associate.getSalary().getBasicSalary()*0.3));
		associate.getSalary().setOtherAllowance((int)(associate.getSalary().getBasicSalary()*0.25));
		associate.getSalary().setPersonalAllowance((int)(associate.getSalary().getBasicSalary()*0.2));
		associate.getSalary().setEpf(1000);
		associate.getSalary().setCompanyPf(1000);
		int grossMonthlySalary = associate.getSalary().getBasicSalary()+associate.getSalary().getPersonalAllowance()+associate.getSalary().getOtherAllowance()+associate.getSalary().getEpf()+associate.getSalary().getCompanyPf();
		associate.getSalary().setGrossSalary(grossMonthlySalary);
		int grossYearlySalary = grossMonthlySalary * 12;
		int yearlyTaxes = 0;
		if(grossYearlySalary>=250000 && grossYearlySalary<500000){
			if(associate.getYearlyInvestmentUnder80c()>150000){
				associate.setYearlyInvestmentUnder80c(150000);
			}
		}
		if(grossYearlySalary>=500000 && grossYearlySalary<1000000){
			if(associate.getYearlyInvestmentUnder80c()>200000){
				associate.setYearlyInvestmentUnder80c(200000);
			}
		}
		if(grossYearlySalary<250000) {
			yearlyTaxes = 0;
			associate.getSalary().setMonthlyTax(yearlyTaxes/12);
		}
		else if(grossYearlySalary>=250000 && grossYearlySalary<500000) {
			yearlyTaxes = (int)((grossYearlySalary - 250000 - associate.getYearlyInvestmentUnder80c())*0.1);		
			associate.getSalary().setMonthlyTax(yearlyTaxes/12);
		}
		else if(grossYearlySalary>=500000 && grossYearlySalary<1000000) {
			yearlyTaxes = (int)((250000 - associate.getYearlyInvestmentUnder80c())*0.1) + (int)((grossYearlySalary - 500000)*0.2);			
			associate.getSalary().setMonthlyTax(yearlyTaxes/12);
		}
		else if(grossYearlySalary>=1000000) {
			yearlyTaxes = (int)((250000 - associate.getYearlyInvestmentUnder80c())*0.1) + (int)(500000*0.2) + (int)((grossYearlySalary - 1000000)*0.3);		
			associate.getSalary().setMonthlyTax(yearlyTaxes/12);
		}
		int netSalary = grossMonthlySalary - associate.getSalary().getMonthlyTax() - associate.getSalary().getCompanyPf() - associate.getSalary().getEpf();
		associate.getSalary().setNetSalary(netSalary);
		associateDao.save(associate);
		return netSalary;
	}
	@Override
	public Associate getAssociateDetails(int associateId) throws AssociateDetailNotfoundException {
		return associateDao.findById(associateId).orElseThrow(()->new AssociateDetailNotfoundException("Associate Details Not Found for AssociateId "+associateId));
	}
	@Override
	public List<Associate> getAllAssociateDetails() {
		return associateDao.findAll();
	}
	@Override
	public List<Associate> getAllAssociateByName(String firstName) {
		return associateDao.getAllAssociatesByName(firstName);
	}
}
