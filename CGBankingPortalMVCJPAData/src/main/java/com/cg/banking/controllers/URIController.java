package com.cg.banking.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cg.banking.beans.Account;

@Controller
public class URIController {
	private Account account;
	@RequestMapping(value= {"/","index"})
	public String getIndexPage(){
		return "indexPage";
	}
	@RequestMapping("/registration")
	public String getRegistrationPage() {
		return "registrationPage";
	}
	@RequestMapping("/findAccountDetails")
	public String getAccountDetailsPage() {
		return "getAccountDetailsPage";
	}
	@RequestMapping("/getDepositMoney")
	public String getDepositMoneyPage(){
		return "depositMoneyPage";
	}
	@RequestMapping("/getWithdrawMoney")
	public String getWithrawMoneyPage(){
		return "withdrawMoneyPage";
	}
	@RequestMapping("/getFundTransfer")
	public String getFundTransferPage(){
		return "fundTransferPage";
	}
	@RequestMapping("/getAccountTransactions")
	public String getAccountTransactionsPage(){
		return "accountTransactionsPage";
	}
	@ModelAttribute
	public Account getAccount() {
		account = new Account();
		return account;
	}
}