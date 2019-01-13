package com.mycompany.app.account.service;

import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Before;

public class SavingsAccountLogger {
Logger logger=Logger.getLogger(SavingsAccountLogger.class.getName());

@Before("execution(* com.mycompany.app.account.service.SavingsAccountServiceImpl.withdraw(..)")
public void log() {
	logger.info("Before -Starting withdraw method");
}
@After("execution(* com.mycompany.app.account.service.SavingsAccountServiceImpl.withdraw(..)")
public void log2()
{
	logger.info("Withdraw successful");
}

@AfterThrowing(pointcut=("execution(* com.mycompany.app.account.service.*.*(..))"),throwing="exe")
public void log2(Exception exe)
{
	logger.info("Exception is :"+exe.toString());
}

@Before("execution(* com.mycompany.app.account.service.SavingsAccountServiceImpl.deposit(..)")
public void log3() {
	logger.info("Before -Starting withdraw method");
}
@After("execution(* com.mycompany.app.account.service.SavingsAccountValidation.deposit(..)")
public void log4()
{
	logger.info("Withdraw successful");
}

@AfterThrowing(pointcut=("execution(* com.mycompany.app.account.service.*.*(..))"),throwing="exe")
public void log5(Exception exeOne)
{
	logger.info("Exception is :"+exeOne.toString());
}
}
