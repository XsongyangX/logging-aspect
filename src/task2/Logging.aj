package task2;

/**
 * The "Logging" aspect records every executed method by printing a message about
 * the action just done.
 * @author Song
 *
 *
 */
public aspect Logging {
	
	//--- Account class ---//
	// Constructor
	after(Account a) : initialization(public Account.new(..)) && !within(Logging) && target(a){ 
		String stringToLog = "Created object " + a; 
		System.out.println(stringToLog);	
	}	
	
	// Account's toString()
	public String Account.toString() {
		
		String returnedString = "Account " + this.accountNumber + " of " 
				+ this.owner.getName() + ": " + this.balanceInCents;
        return returnedString;
	}
	
	// Deposit method in account
	pointcut CallDepositAccount(int amount, Account a):
		call(public void deposit(int)) && args(amount) && target(a);
	
	before(int amount, Account a): CallDepositAccount(amount, a) {
		String stringToLog = "deposit called on object " + a + " with parameter " + amount; 
        System.out.println(stringToLog);
	}
	
	// Withdraw method in account
	pointcut CallWithdrawAccount(int amount, Account a):
		call(public void withdraw(int)) && args(amount) && target(a);
	
	before(int amount, Account a): CallWithdrawAccount(amount, a) {
		 String stringToLog = "withdraw called on object " + a + " with parameter " + amount; //logging
	     System.out.println(stringToLog); //logging
	}
	
	//--- Customer ---//
	// Constructor
	after(Customer a) : initialization(public Customer.new(..)) && !within(Logging) && target(a){ 
		String stringToLog = "Created object " + a; 
		System.out.println(stringToLog);	
	}
	
	// Customer's toString()
    public String Customer.toString() { // ~logging
        return this.name;
    }
}