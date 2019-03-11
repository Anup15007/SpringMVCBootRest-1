package com.cg.banking.aspect;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.cg.banking.beans.Account;
import com.cg.banking.exceptions.AccountBlockedException;
import com.cg.banking.exceptions.AccountNotFoundException;
import com.cg.banking.exceptions.BankingServicesDownException;
import com.cg.banking.exceptions.InvalidAmountException;
import com.cg.banking.exceptions.InvalidPinNumberException;

@ControllerAdvice
public class BankingExceptionAscept {
	@ExceptionHandler(InvalidAmountException.class)
	public ModelAndView handledInvalidAmountException(Exception e) {
		ModelAndView modelAndView=new ModelAndView("registrationPage","errorMessage",e.getMessage());
		modelAndView.addObject("account", new Account());
		return modelAndView;
	}
	@ExceptionHandler(AccountNotFoundException.class)
	public ModelAndView handleAccountNotFoundException1(Exception e) {
		 ModelAndView modelAndView = new ModelAndView("findAccountDetailsPage", "errorMessage",e.getMessage());
		 modelAndView.addObject("account",new Account());
		 return modelAndView;
	}
	@ExceptionHandler(BankingServicesDownException.class)
	public ModelAndView handleBankingServicesDownException1(Exception e) {
		ModelAndView modelAndView = new ModelAndView("findAccountDetailsPage", "errorMessage",e.getMessage());
		modelAndView.addObject("account",new Account());
		 return modelAndView;
	}
//	@ExceptionHandler(AccountNotFoundException.class)
//	public ModelAndView handledAccountNotFoundException2(Exception e) {
//		ModelAndView modelAndView=new ModelAndView("depositMoneyPage","errorMessage",e.getMessage());
//		modelAndView.addObject("account", new Account());
//		return modelAndView;
//	}
//	@ExceptionHandler(BankingServicesDownException.class)
//	public ModelAndView handleBankingServicesDownException2(Exception e) {
//		ModelAndView modelAndView =  new ModelAndView("depositMoneyPage","errorMessage",e.getMessage());
//		modelAndView.addObject("account", new Account());
//		return modelAndView;
//	}
//	@ExceptionHandler(AccountBlockedException.class)
//	public ModelAndView handleAccountBlockedException2(Exception e) {
//		ModelAndView modelAndView = new ModelAndView("depositMoneyPage","errorMessage",e.getMessage());
//		modelAndView.addObject("account", new Account());
//		return modelAndView;
//	}
//	@ExceptionHandler(AccountNotFoundException.class)
//	public ModelAndView handledAccountNotFoundException3(Exception e) {
//		ModelAndView modelAndView = new ModelAndView("withdrawMoneyPage","errorMessage",e.getMessage());
//		modelAndView.addObject("account", new Account());
//		return modelAndView;
//	}
//	@ExceptionHandler(BankingServicesDownException.class)
//	public ModelAndView handleBankingServicesDownException3(Exception e) {
//		ModelAndView modelAndView = new ModelAndView("withdrawMoneyPage","errorMessage",e.getMessage());
//		modelAndView.addObject("account", new Account());
//		return modelAndView;
//	}
//	@ExceptionHandler(InvalidPinNumberException.class)
//	public ModelAndView handleInvalidPinNumberException3(Exception e) {
//		ModelAndView modelAndView = new ModelAndView("withdrawMoneyPage","errorMessage",e.getMessage());
//		modelAndView.addObject("account", new Account());
//		return modelAndView;
//	}
//	@ExceptionHandler(AccountBlockedException.class)
//	public ModelAndView handleAccountBlockedException3(Exception e) {
//		ModelAndView modelAndView = new ModelAndView("withdrawMoneyPage","errorMessage",e.getMessage());
//		modelAndView.addObject("account", new Account());
//		return modelAndView;
//	}
//	@ExceptionHandler(AccountNotFoundException.class)
//	public ModelAndView handledAccountNotFoundException4(Exception e) {
//		ModelAndView modelAndView=new ModelAndView("fundTransferPage","errorMessage",e.getMessage());
//		modelAndView.addObject("account", new Account());
//		return modelAndView;
//	}
//	@ExceptionHandler(BankingServicesDownException.class)
//	public ModelAndView handleBankingServicesDownException4(Exception e) {
//		ModelAndView modelAndView = new ModelAndView("fundTransferPage","errorMessage",e.getMessage());
//		modelAndView.addObject("account", new Account());
//		return modelAndView;
//	}
//	@ExceptionHandler(InvalidPinNumberException.class)
//	public ModelAndView handleInvalidPinNumberException4(Exception e) {
//		ModelAndView modelAndView = new ModelAndView("fundTransferPage","errorMessage",e.getMessage());
//		modelAndView.addObject("account", new Account());
//		return modelAndView;
//	}
//	@ExceptionHandler(AccountBlockedException.class)
//	public ModelAndView handleAccountBlockedException4(Exception e) {
//		ModelAndView modelAndView = new ModelAndView("fundTransferPage","errorMessage",e.getMessage());
//		modelAndView.addObject("account", new Account());
//		return modelAndView;
//	}

}
