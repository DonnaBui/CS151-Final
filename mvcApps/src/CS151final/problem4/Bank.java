package CS151final.problem4;


class Account extends Dispatcher {
	Double balance = 0.0;
	public Double deposit(Double amt) {
		balance += amt;
		return balance;
	}
	public Double withdraw(Double amt) {
		balance -= amt;
		return balance;
	}
}

public class Bank {

	public static void main(String[] args) {
		Account savings = new Account();
		System.out.println("balance = $" + savings.dispatch("deposit", 100.0));
		System.out.println("balance = $" + savings.dispatch("withdraw", 80.50));
	}
	/*
	Prints:
	  balance = $100.0
      balance = $19.5
    */

}
