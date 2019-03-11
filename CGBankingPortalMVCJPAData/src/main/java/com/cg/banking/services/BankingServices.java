package com.cg.banking.services;

import java.util.List;

import com.cg.banking.beans.Account;
import com.cg.banking.beans.Transaction;
import com.cg.banking.exceptions.AccountBlockedException;
import com.cg.banking.exceptions.AccountNotFoundException;
import com.cg.banking.exceptions.BankingServicesDownException;
import com.cg.banking.exceptions.InsufficientAmountException;
import com.cg.banking.exceptions.InvalidAccountTypeException;
import com.cg.banking.exceptions.InvalidAmountException;
import com.cg.banking.exceptions.InvalidPinNumberException;

public interface BankingServices {
Account openAccount(Account account)
		throws InvalidAmountException, InvalidAccountTypeException, BankingServicesDownException;
float depositAmount(long accountNo, float amount) 
		throws AccountBlockedException, AccountNotFoundException, BankingServicesDownException;
float withdrawAmount(long accountNo, float amount, int pinNumber) 
		throws InsufficientAmountException, AccountNotFoundException, AccountBlockedException, BankingServicesDownException, InvalidPinNumberException;
boolean fundTransfer(long accountNoTo, long accountNoFrom, float transferAmount, int pinNumber) 
		throws InsufficientAmountException, AccountNotFoundException, InvalidPinNumberException, BankingServicesDownException, AccountBlockedException;
Account getAccountDetails(long accountNo) 
		throws AccountNotFoundException, BankingServicesDownException;
int generatePinNumber() throws BankingServicesDownException;
int setNewPinNumber(long accountNo) throws AccountNotFoundException;
List<Account> getAllAccountDetails() 
		throws BankingServicesDownException;
List<Transaction> getAccountAllTransaction(long accountNo) 
		throws BankingServicesDownException, AccountBlockedException;
} 
