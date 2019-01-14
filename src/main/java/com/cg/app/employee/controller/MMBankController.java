package com.cg.app.employee.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cg.app.employee.pojo.Employee;
import com.mycompany.app.account.SavingsAccount;
import com.mycompany.app.account.service.SavingsAccountService;
import com.mycompany.app.exception.AccountNotFoundException;

@Controller
public class MMBankController {

	@Autowired
	private SavingsAccountService savingsAccountService;

	boolean flag;

	@RequestMapping("/")
	public String home() {
		return "index";
	}

	@RequestMapping("/addNewAccountForm")
	public String createNewAccount() {
		return "addNewSavingsAccount";
	}

	@RequestMapping("/addnewSavingsAccountForm")
	public String creatingNewAccount(@RequestParam("account_hn") String account_hn,
			@RequestParam("salary") boolean salary, @RequestParam("accountbalance") double accountbalance)
			throws ClassNotFoundException, SQLException {
		savingsAccountService.createNewAccount(account_hn, accountbalance, salary);

		return "redirect:getAll";

	}

	@RequestMapping("/getAll")
	public String getAllSavingAccountDetails(Model model) throws ClassNotFoundException, SQLException {
		List<SavingsAccount> account = savingsAccountService.getAllSavingsAccount();
		model.addAttribute("account", account);
		return "AccountDetails";
	}

	@RequestMapping("/update")
	public String updateAccount() {
		return "updateForm";
	}

	@RequestMapping("/updateAccount")

	public String updateAccountDetails(Model model, @RequestParam("accountNumber") int accountNumber)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
		SavingsAccount account = savingsAccountService.getAccountById(accountNumber);
		model.addAttribute("account", account);
		return "updateAccountDetails";

	}

	@RequestMapping("/updatedAccountDetails")

	public String updateAllAccountDetails(Model model, @RequestParam("accNo") int accNo,
			@RequestParam("accHolderName") String accHolderName, @RequestParam("accBalance") double accBalance,
			@RequestParam("rdSal") boolean rdSal)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
		SavingsAccount account = savingsAccountService.getAccountById(accNo);
		account.getBankAccount().setAccountHolderName(accHolderName);
		account.setSalary(rdSal);
		savingsAccountService.updateAccount(account);
		return "redirect:getAll";
	}

	@RequestMapping("/search")
	public String searchingAcccountForm() {
		return "searchForm";
	}

	@RequestMapping("/searchAccountDetails")
	public String searchAccountDetailsMethod(@RequestParam("accountNumber") int accountNumber, Model model)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
		SavingsAccount account = savingsAccountService.getAccountById(accountNumber);
		model.addAttribute("account", account);
		return "searchAccountResult";
	}

	@RequestMapping("/delete")
	public String deleteAccount() {
		return "deleteForm";
	}

	@RequestMapping("deleteAccount")
	public String deletingAccount(@RequestParam("accountNumber") int accountNumber)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
		savingsAccountService.deleteAccount(accountNumber);
		return "redirect:getAll";
	}

	@RequestMapping("/withdraw")
	public String withdrawForm() {
		return "withDrawForm";
	}

	@RequestMapping("/withdrawBalance")

	public String withdrawBalanceMethod(@RequestParam("accountNumber") int accountNumber,
			@RequestParam("amount") double amount, Model model)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
		SavingsAccount account = savingsAccountService.getAccountById(accountNumber);
		savingsAccountService.withdraw(account, amount);
		return "redirect:getAll";
	}

	@RequestMapping("/checkbalance")

	public String checkingBalance() {
		return "checkCurrentBalance";
	}

	@RequestMapping("checkingCurrentBalance")
	public String checkingBalanceResult(@RequestParam("accountNumber") int accountNumber, Model model)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
		SavingsAccount account = savingsAccountService.getAccountById(accountNumber);
		double balance = account.getBankAccount().getAccountBalance();
		model.addAttribute("balance", balance);
		return "currentBalance";

	}

	@RequestMapping("/deposit")
	public String depositForm() {
		return "Deposit";
	}

	@RequestMapping("/depositMoney")
	public String depositingMoney(@RequestParam("accountNumber") int accountNumber,
			@RequestParam("amount") double amount, Model model)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
		SavingsAccount account = savingsAccountService.getAccountById(accountNumber);
		savingsAccountService.deposit(account, amount);
		return "redirect:getAll";

	}

	@RequestMapping("/transfer")
	public String transferForm() {
		return "TransferMoneyForm";
	}

	@RequestMapping("/transferMoneyDetails")
	public String transferMoneyMethod(@RequestParam("senderAccountNumber") int senderAccountNumber,
			@RequestParam("receiverAccountNumber") int receiverAccountNumber, @RequestParam("amount") double amount)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
		SavingsAccount senderAccount = savingsAccountService.getAccountById(senderAccountNumber);
		SavingsAccount receiverAccount = savingsAccountService.getAccountById(receiverAccountNumber);
		savingsAccountService.fundTransfer(senderAccount, receiverAccount, amount);
		return "index";
	}

	@RequestMapping("/sortByName")
	public String sortByName(Model model) throws ClassNotFoundException, SQLException {
		flag = !flag;
		Collection<SavingsAccount> account;
		account = savingsAccountService.getAllSavingsAccount();
		ArrayList<SavingsAccount> accountsNameList = new ArrayList<SavingsAccount>(account);
		Collections.sort(accountsNameList, new Comparator<SavingsAccount>() {
			@Override
			public int compare(SavingsAccount arg0, SavingsAccount arg1) {
				int result = arg0.getBankAccount().getAccountHolderName()
						.compareTo(arg1.getBankAccount().getAccountHolderName());
				if (flag == true) {
					return result;
				} else
					return -result;
			}
		});
		model.addAttribute("account", accountsNameList);
		return "AccountDetails";
	}

	@RequestMapping("/sortByBalance")
	public String sortByBalance(Model model) throws ClassNotFoundException, SQLException {
		flag = !flag;
		Collection<SavingsAccount> account = savingsAccountService.getAllSavingsAccount();
		ArrayList<SavingsAccount> accountsBalList = new ArrayList<SavingsAccount>(account);
		Collections.sort(accountsBalList, new Comparator<SavingsAccount>() {
			@Override
			public int compare(SavingsAccount arg0, SavingsAccount arg1) {
				int result = (int) (arg0.getBankAccount().getAccountBalance()
						- arg1.getBankAccount().getAccountBalance());
				if (flag == true) {
					return result;
				} else
					return -result;
			}
		});
		model.addAttribute("account", accountsBalList);
		return "AccountDetails";
	}
}
