package com.mycompany.app.account.dao;

import java.sql.SQLException;
import java.util.List;

import com.mycompany.app.account.SavingsAccount;
import com.mycompany.app.exception.AccountNotFoundException;

public interface SavingsAccountDAO {

	SavingsAccount createNewAccount(SavingsAccount account)
			throws ClassNotFoundException, SQLException;

	SavingsAccount getAccountById(int accountNumber)
			throws ClassNotFoundException, SQLException,
			AccountNotFoundException;

	SavingsAccount deleteAccount(int accountNumber)
			throws ClassNotFoundException, SQLException,
			AccountNotFoundException;

	SavingsAccount searchAccount(int accountNumber)
			throws ClassNotFoundException, SQLException,
			AccountNotFoundException;

	List<SavingsAccount> getAllSavingsAccount() throws ClassNotFoundException,
			SQLException;

	SavingsAccount updateAccount(SavingsAccount account) throws SQLException,
			ClassNotFoundException;

	double checkBalance(int accountNumber) throws ClassNotFoundException,
			SQLException, AccountNotFoundException;

	void updateBalance(int accountNumber, double currentBalance)
			throws ClassNotFoundException, SQLException;
	
	List<SavingsAccount> searchAccountByHolderName(String holderName) throws ClassNotFoundException, SQLException;
	List<SavingsAccount> sortByAccountHolderName() throws ClassNotFoundException, SQLException;
	List<SavingsAccount> sortByAccountHolderNameInDescendingOrder() throws ClassNotFoundException, SQLException;
	List<SavingsAccount> sortByAccountBalance() throws ClassNotFoundException, SQLException;
	List<SavingsAccount> sortByBalanceRange(int minimumBalance,
			int maximumBalance) throws ClassNotFoundException, SQLException;
	List<SavingsAccount> sortByBalanceRangeInDescendingOrder(
			int minimumBalanceValue, int maximumBalanceValue) throws ClassNotFoundException, SQLException;

	void commit() throws SQLException;

}
