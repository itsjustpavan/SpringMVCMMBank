package com.cg.app.account.validator;



import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mycompany.app.account.SavingsAccount;

@Component
public class SavingsAccountValidator implements Validator  {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		SavingsAccount account=(SavingsAccount) target;
		if(account.getBankAccount().getAccountHolderName().length()<2)
		{
			errors.rejectValue("bankAccount.accountHolderName","accountHolderName.length", "Name should be more than 2 characters");
		}
		if(account.getBankAccount().getAccountBalance()<0)
		{
			errors.rejectValue("bankAccount.accountBalance", "accountBalance.minimum", "Amount should be greater than zero");
		}
	}

}
