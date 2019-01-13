package com.mycompany.app.account.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.app.account.SavingsAccount;
import com.mycompany.app.account.dao.SavingsAccountDAO;
import com.mycompany.app.account.dao.SavingsAccountDAOImpl;
import com.mycompany.app.account.factory.AccountFactory;
import com.mycompany.app.account.util.DBUtil;
import com.mycompany.app.exception.AccountNotFoundException;
import com.mycompany.app.exception.InsufficientFundsException;
import com.mycompany.app.exception.InvalidInputException;

@Service
@Transactional
public class SavingsAccountServiceImpl implements SavingsAccountService {

	private AccountFactory factory;
	
	@Autowired
	private SavingsAccountDAO savingsAccountDAO;

	public SavingsAccountServiceImpl() {
		factory = AccountFactory.getInstance();
		savingsAccountDAO = new SavingsAccountDAOImpl();
	}

	@Override
	public SavingsAccount createNewAccount(String accountHolderName,
			double accountBalance, boolean salary)
			throws ClassNotFoundException, SQLException {
		SavingsAccount account = factory.createNewSavingsAccount(
				accountHolderName, accountBalance, salary);
		savingsAccountDAO.createNewAccount(account);
		return null;
	}

	@Override
	public List<SavingsAccount> getAllSavingsAccount()
			throws ClassNotFoundException, SQLException {
		return savingsAccountDAO.getAllSavingsAccount();
	}


	public void deposit(SavingsAccount account, double amount)
			throws ClassNotFoundException, SQLException {
		
			double currentBalance = account.getBankAccount()
					.getAccountBalance();
			currentBalance += amount;
			savingsAccountDAO.updateBalance(account.getBankAccount()
					.getAccountNumber(), currentBalance);
			// savingsAccountDAO.commit();
		
	}

	public SavingsAccount withdraw(SavingsAccount account, double amount)
			throws ClassNotFoundException, SQLException {
		double currentBalance = account.getBankAccount().getAccountBalance();
		
			currentBalance -= amount;
			savingsAccountDAO.updateBalance(account.getBankAccount()
					.getAccountNumber(), currentBalance);
			// savingsAccountDAO.commit();
			return account;
		
	}


	public void fundTransfer(SavingsAccount sender, SavingsAccount receiver,
			double amount) throws ClassNotFoundException, SQLException {
		/*
		 * try { withdraw(sender, amount); deposit(receiver, amount); DBUtil.commit(); }
		 * catch (InvalidInputException | InsufficientFundsException e) {
		 * e.printStackTrace(); DBUtil.rollback(); } catch (Exception e) {
		 * e.printStackTrace(); DBUtil.rollback(); }
		 */
		deposit(receiver, amount);
		withdraw(sender, amount);
		
	}

	@Override
	public SavingsAccount getAccountById(int accountNumber)
			throws ClassNotFoundException, SQLException,
			AccountNotFoundException {
		return savingsAccountDAO.getAccountById(accountNumber);
	}

	@Override
	public SavingsAccount deleteAccount(int accountNumber)
			throws ClassNotFoundException, SQLException,
			AccountNotFoundException {
		// TODO Auto-generated method stub
		return savingsAccountDAO.deleteAccount(accountNumber);
	}

	@Override
	public double checkBalance(int accountNumber)
			throws ClassNotFoundException, SQLException,
			AccountNotFoundException {
		// TODO Auto-generated method stub
		return savingsAccountDAO.checkBalance(accountNumber);
	}

	@Override
	public SavingsAccount searchAccount(int accountNumber)
			throws ClassNotFoundException, SQLException,
			AccountNotFoundException {
		// TODO Auto-generated method stub
		return savingsAccountDAO.searchAccount(accountNumber);
	}

	@Override
	public SavingsAccount updateAccount(SavingsAccount savingsaccount)
			throws SQLException, ClassNotFoundException {

		return savingsAccountDAO.updateAccount(savingsaccount);
	}

	@Override
	public List<SavingsAccount> sortByAccountHolderName() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SavingsAccount> sortByAccountHolderNameInDescendingOrder() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SavingsAccount> sortByAccountBalance() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SavingsAccount> sortByBalanceRange(int minimumBalance, int maximumBalance)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SavingsAccount> sortByBalanceRangeInDescendingOrder(int minimumBalanceValue, int maximumBalanceValue)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
