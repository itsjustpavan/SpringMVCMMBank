package com.mycompany.app.account.service;

import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.mycompany.app.account.SavingsAccount;
import com.mycompany.app.exception.InsufficientFundsException;
import com.mycompany.app.exception.InvalidInputException;

@Aspect
@Component
public class SavingsAccountValidation {
	Logger logger = Logger.getLogger(SavingsAccountValidation.class.getName());

	@Around("execution(* com.mycompany.app.account.service.SavingsAccountServiceImpl.withdraw(..))")
	public void withDrawValidation(ProceedingJoinPoint pjp) throws Throwable {
		/*
		 * logger.info("Around before loging method"); logger.info("Function name is:" +
		 * pjp.getSignature()); logger.info("Parameters are");
		 */
		Object[] params = pjp.getArgs();
		double amount = (double) params[1];
		SavingsAccount currentBalance = (SavingsAccount) params[0];
		double currentbalance = currentBalance.getBankAccount().getAccountBalance();
		if (amount > 0 && amount <= currentbalance) {
			pjp.proceed();
		} else {
			logger.info("please enter correct amount");
			throw new InsufficientFundsException("Insufficient funds");

		}
	}
	
	@Around("execution(* com.mycompany.app.account.service.SavingsAccountServiceImpl.deposit(..))")
	public void depositValidation(ProceedingJoinPoint pjp) throws Throwable {
		Object[] params = pjp.getArgs();
		double amount = (double) params[1];
		if(amount > 0)
		{
			pjp.proceed();
			logger.info("deposited succesfully");
		}
		else
		{
			logger.info("Invalid amount please enter correct amount");
			throw new InvalidInputException("Invalid input");
		}
	}

		
	}
