 package com.cg.banking.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.cg.banking.beans.Account;
import com.cg.banking.beans.Transaction;
import com.cg.banking.daoservices.AccountDAO;
import com.cg.banking.daoservices.TransactionDAO;
import com.cg.banking.exceptions.AccountBlockedException;
import com.cg.banking.exceptions.AccountNotFoundException;
import com.cg.banking.exceptions.BankingServicesDownException;
import com.cg.banking.exceptions.InsufficientAmountException;
import com.cg.banking.exceptions.InvalidAccountTypeException;
import com.cg.banking.exceptions.InvalidAmountException;
import com.cg.banking.exceptions.InvalidPinNumberException;

@Component("bankingServices")
public class BankingServicesImpl implements BankingServices{	
	Scanner sc = new Scanner(System.in);
	@Autowired
	private AccountDAO accountDao;
	@Autowired
	private TransactionDAO transactionDao;
	@Override
	public Account openAccount(Account account) throws InvalidAmountException, InvalidAccountTypeException, BankingServicesDownException {
		if(account.getAccountType()==null) {
			throw new InvalidAccountTypeException("Invalid Account Type Selection.");
		}
		if(account.getAccountBalance()<500) {
			throw new InvalidAmountException("Invalid Amount for Opening Account.");
		}
		String accountStatus = "ACTIVE";
		int pinNumber = generatePinNumber();
		Map<Long,Transaction> transactions = new HashMap<Long, Transaction>();
		account.setAccountType(account.getAccountType());
		account.setAccountStatus(accountStatus);
		account.setPinNumber(pinNumber);
		account.setAccountBalance(account.getAccountBalance());
		account.setTransactions(transactions);
		account = accountDao.save(account);
		return account;
	}
	@Override
	public float depositAmount(long accountNo, float amount)
			throws AccountBlockedException, AccountNotFoundException, BankingServicesDownException {
		Account account = getAccountDetails(accountNo);
		if(account==null){
			throw new AccountNotFoundException();
		}
		else if(account.getAccountStatus().equalsIgnoreCase("ACTIVE")){
			float newAmount = account.getAccountBalance() + amount;
			account.setAccountBalance(newAmount);
			Transaction transaction = new Transaction(amount,"Deposited",account);
			System.out.println("Transaction: "+ transaction);
			System.out.println("Account Balance: "+account.getAccountBalance());
			accountDao.save(account);
			transaction = transactionDao.save(transaction);
		}
		else {
			System.out.println("Account is Blocked.");
		}
		return account.getAccountBalance();
	}
	@Override
	public float withdrawAmount(long accountNo, float amount, int pinNumber) throws InsufficientAmountException,
			AccountNotFoundException, AccountBlockedException, BankingServicesDownException, InvalidPinNumberException {
		Account account = getAccountDetails(accountNo);
		if(account==null){
			throw new AccountNotFoundException();
		}
		if(account.getAccountStatus().equalsIgnoreCase("ACTIVE")&& account.getAttempt()<=3){
			int attempt = account.getAttempt();
			if(account.getPinNumber()!=pinNumber){
				for(attempt=1;attempt<3;attempt++) {
					System.out.println("Pin Incorrect.");
					System.out.println("Enter Pin Again: ");
					pinNumber = sc.nextInt();
					if(account.getPinNumber()==pinNumber) {
						break;
					}
				}
				if(attempt>=3)
				{
					account.setAccountStatus("BLOCKED");
					throw new AccountBlockedException("Account Blocked due to 3 Unsuccessful Pin attempts.");
				}
			}
			if(account.getAccountBalance() - amount <500) {
				throw new InsufficientAmountException("Insufficient funds.");
			}
			else {
				account.setAccountBalance( account.getAccountBalance()-amount);
				account.setAttempt(0);
				Transaction transaction = new Transaction(amount,"Withdrawn",account);
				accountDao.save(account);
				transaction = transactionDao.save(transaction);
			}
		}
		return account.getAccountBalance();
	}
	@Override
	public boolean fundTransfer(long accountNoTo, long accountNoFrom, float transferAmount, int pinNumber)
			throws InsufficientAmountException, AccountNotFoundException, InvalidPinNumberException,
			BankingServicesDownException, AccountBlockedException {
		Account accTo = getAccountDetails(accountNoTo);
		Account accFrom = getAccountDetails(accountNoFrom);
		if(accTo==null || accFrom==null) {
			throw new AccountNotFoundException();
		}
		if(accFrom.getAccountStatus().equalsIgnoreCase("ACTIVE")&& accFrom.getAttempt()<=3){
			if(accFrom.getPinNumber()!=pinNumber){
				int attempt = accFrom.getAttempt();
				attempt++;
				accFrom.setAttempt(attempt);
				if(attempt>=3)
				{
					accFrom.setAccountStatus("BLOCKED");
				}
			}
			if(accFrom.getAccountBalance() - transferAmount <500) {
				throw new InsufficientAmountException("Insufficient funds.");
			}
			else {
				float newAmount = (accFrom.getAccountBalance() - transferAmount);
				accFrom.setAccountBalance(newAmount);
				newAmount = (accTo.getAccountBalance() + transferAmount);
				accTo.setAccountBalance(newAmount);
				accFrom.setAttempt(0);
				Transaction transactionTo = new Transaction(transferAmount,"Credited",accTo);
				Transaction transactionFrom = new Transaction(transferAmount,"Debited",accFrom);
				accountDao.save(accTo);
				transactionDao.save(transactionTo);
				accountDao.save(accFrom);
				transactionDao.save(transactionFrom);
			}
		}
		return true;
	}
	@Override
	public Account getAccountDetails(long accountNo) throws AccountNotFoundException, BankingServicesDownException {
		return accountDao.findById(accountNo).orElseThrow(()->new AccountNotFoundException("Account Not Found with Account Number : "+ accountNo));
	}
	@Override
	public List<Account> getAllAccountDetails() throws BankingServicesDownException {
		return accountDao.findAll();
	}
	@Override
	public List<Transaction> getAccountAllTransaction(long accountNo)
			throws BankingServicesDownException, AccountBlockedException {
		return transactionDao.findAllById(accountNo);
	}
	@Override
	public int generatePinNumber() throws BankingServicesDownException {
		int pinNumber = (int)(Math.random()*10000);
		return pinNumber;
	}
	@Override
	public int setNewPinNumber(long accountNo) throws AccountNotFoundException, BankingServicesDownException {
		Account account = getAccountDetails(accountNo);
		int pinNumber = account.getPinNumber();
		System.out.println("Input New PIN Number : ");
		int pinNumber1 = sc.nextInt();
		System.out.println("Confirm New PIN Number : ");
		int pinNumber2 = sc.nextInt();
		if(pinNumber1==pinNumber2) {
			pinNumber = pinNumber1;
			account.setPinNumber(pinNumber);
			accountDao.save(account);
			System.out.println("Successful PIN changed.");
		}
		else {
			System.out.println("PIN mismatch.");
		}
		return 0;
	}
}
