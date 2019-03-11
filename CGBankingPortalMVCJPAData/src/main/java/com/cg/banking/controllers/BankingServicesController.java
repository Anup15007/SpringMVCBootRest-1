package com.cg.banking.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.cg.banking.beans.Account;
import com.cg.banking.beans.Transaction;
import com.cg.banking.exceptions.AccountBlockedException;
import com.cg.banking.exceptions.AccountNotFoundException;
import com.cg.banking.exceptions.BankingServicesDownException;
import com.cg.banking.exceptions.InsufficientAmountException;
import com.cg.banking.exceptions.InvalidAccountTypeException;
import com.cg.banking.exceptions.InvalidAmountException;
import com.cg.banking.exceptions.InvalidPinNumberException;
import com.cg.banking.services.BankingServices;

@Controller
public class BankingServicesController {
	@Autowired
	BankingServices bankingServices;
	@RequestMapping("/openAccount")
	public ModelAndView openAccount(@ModelAttribute Account account) throws InvalidAmountException, InvalidAccountTypeException, BankingServicesDownException{
		account = bankingServices.openAccount(account);
		return new ModelAndView("registrationSuccessPage","account",account);
	}
	@RequestMapping("/getAccountDetails")
	public ModelAndView getAccountDetails(@RequestParam long accountNo) throws AccountNotFoundException, BankingServicesDownException{
		Account account = bankingServices.getAccountDetails(accountNo);
		return new ModelAndView("getAccountDetailsPage","account",account);
	}
	@RequestMapping("/depositAmount")
	public ModelAndView depositMoney(@RequestParam long accountNo, float accountBalance) throws AccountBlockedException, AccountNotFoundException, BankingServicesDownException, InvalidAmountException {
		float updatedBalance=bankingServices.depositAmount(accountNo,accountBalance);
		return new ModelAndView("showBalancePage","updatedBalance", updatedBalance);
	}
	@RequestMapping("/withrawAmount")
	public ModelAndView withrawAmount(@RequestParam long accountNo, float accountBalance,int pinNumber) throws InsufficientAmountException, AccountNotFoundException, AccountBlockedException, BankingServicesDownException, InvalidPinNumberException {
		float updatedBalance=bankingServices.withdrawAmount(accountNo,accountBalance,pinNumber);
		return new ModelAndView("showBalancePage","updatedBalance", updatedBalance);
	}
	@RequestMapping("/fundTransfer")
	public ModelAndView fundTransfer(@RequestParam long accountNoTo, float accountBalance,int pinNumber, long accountNoFrom)throws InsufficientAmountException, AccountNotFoundException, InvalidPinNumberException,BankingServicesDownException, AccountBlockedException {
		boolean fundTransferStatus=bankingServices.fundTransfer(accountNoTo,accountNoFrom,accountBalance,pinNumber);
		return new ModelAndView("fundTransferPage","fundTransferStatus", fundTransferStatus);
	}
	@RequestMapping("/getAllTransaction")
	public ModelAndView getAccountAllTransaction(@RequestParam long accountNo)throws BankingServicesDownException, AccountBlockedException {
		List<Transaction> transactions = bankingServices.getAccountAllTransaction(accountNo);
		return new ModelAndView("accountTransactionPage","transactions", transactions );
	}
}
