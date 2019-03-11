package com.cg.banking.daoservices;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.cg.banking.beans.Account;
import com.cg.banking.beans.Transaction;

public interface AccountDAO extends JpaRepository<Account, Long>{
	@Query(value = "from Account a where a.accountType  = :accountType")
	List<Transaction> findAllAccountByAccountType(String accountType);
}
