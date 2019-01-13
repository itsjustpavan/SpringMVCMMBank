package com.mycompany.app.account.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mycompany.app.account.SavingsAccount;

public class DAOMapper implements RowMapper<SavingsAccount>{
	public SavingsAccount mapRow(ResultSet resultSet, int i) throws SQLException {

		int accountNumber=resultSet.getInt(1);
		String accountHolderName=resultSet.getString("account_hn");
		double accountBalance=resultSet.getDouble(3);
		boolean salary=resultSet.getBoolean("salary");
		SavingsAccount savingsAccount=new SavingsAccount(accountNumber,accountHolderName,accountBalance,salary);
		return savingsAccount;
	}

	

}
