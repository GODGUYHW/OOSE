public class BankAccount extends Person {
    private String accountId;
    private String password;
    private int balance;

    public BankAccount( String name, String surname  , String accId , String accPassword, int accBalance , String idCard, String gender){
        super(idCard,name,surname,gender);
        this.accountId = accId;
        this.password = accPassword;
        this.balance = accBalance;
    }

    public int getBalance(){
        return this.balance;
    }
    public String getAccountName(){
        return this.getName();
    }
    public String getAccountId(){
        return this.accountId;
    }
    public boolean login(String inputLogin, String inputPassword){
        return this.accountId.equals(inputLogin) && this.password.equals(inputPassword);
    }
    public void deposit(int amount) {
        if (amount > 0) {
            this.balance += amount;
            System.out.println("Deposit successful. New balance: " + this.balance);
        } else {
            System.out.println("Deposit failed. Invalid amount.");
        }
    }

    public void withdraw(int amount) {
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
            System.out.println("Withdrawal successful. Remaining balance: " + this.balance);
        } else {
            System.out.println("Withdrawal failed. Insufficient funds.");
        }
    }
}
