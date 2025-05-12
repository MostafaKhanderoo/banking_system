package app.cfg;

import app.entity.*;
import app.repository.*;
import app.repository.impl.*;
import app.service.*;
import app.service.impl.*;

public class ApplicationContext {
    private ApplicationContext() {
    }

    private static final Class<Account> account;
    private static final AccountRepository accountRepository;
    private static final AccountService accountService;

    private static final Class<Bank> bank;
    private static final BankRepository bankRepository;
    private static final BankService bankService;

    private static final Class<Boss> boss;
    private static final BossRepository bossRepository;
    private static final BossService bossService;


    private static final Class<CreditCard> creditCard;
    private static final CreditCardRepository creditCardRepository;
    private static final CreditCardService creditCardService;

    private static final Class<Customer> customer;
    private static final CustomerRepository customerRepository;
    private static final CustomerService customerService;

    private static final Class<Employee> employee;
    private static final EmployeeRepository employeeRepository;
    private static final EmployeeService employeeService;

    private static final Class<Transaction> transaction;
    private static final TransactionRepository transactionRepository;
    private static final TransactionService transactionService;

    private static final Class<AccountTransaction>accountTransaction;
    private static final AccountTransactionRepository accountTransactionRepository;
    private static final AccountTransactionService accountTransactionService;
    static {
        account = Account.class;
        accountRepository = new AccountRepositoryImpl(account);
        accountService = new AccountServiceImpl(accountRepository);

        bank = Bank.class;
        bankRepository = new BankRepositoryImpl(bank);
        bankService = new BankServiceImpl(bankRepository);

        boss = Boss.class;
        bossRepository = new BossRepositoryImpl(boss);
        bossService = new BossServiceImpl(bossRepository);

        creditCard = CreditCard.class;
        creditCardRepository = new CreditCardRepositoryImpl(creditCard);
        creditCardService = new CreditCardServiceImpl(creditCardRepository);

        customer = Customer.class;
        customerRepository = new CustomerRepositoryImpl(customer);
        customerService = new CustomerServiceImpl(customerRepository);

        employee = Employee.class;
        employeeRepository = new EmployeeRepositoryImpl(employee);
        employeeService = new EmployeeServiceImpl(employeeRepository);


        transaction = Transaction.class;
        transactionRepository = new TransactionRepositoryImpl(transaction);
        transactionService = new TransactionServiceImpl(transactionRepository);

        accountTransaction = AccountTransaction.class;
        accountTransactionRepository =new AccountTransactionRepositoryImpl(accountTransaction);
        accountTransactionService = new AccountTransactionServiceImpl(accountTransactionRepository);
    }
    public static AccountService getAccountService(){
        return accountService;
    }
    public static BankService getBankService(){
        return bankService;
    }
    public static BossService getBossService(){
        return bossService;
    }
    public static CreditCardService getCreditCardService(){
        return creditCardService;
    }
    public static CustomerService getCustomerService(){
        return customerService;
    }
    public static EmployeeService getEmployeeService(){
        return employeeService;
    }
    public  static TransactionService getTransactionService(){
        return transactionService;
    }
    public static AccountTransactionService getAccountTransactionService(){return accountTransactionService;}


}
