public class BankAccount {
    private String account;
    private String id;
    private String password;
    private int balance;
    
    public BankAccount(String accName,String accId, String accPass, int accBalance){
        this.account = accName;
        this.id = accId;
        this.password = accPass;
        this.balance = accBalance;
    }
    // ตรวจสอบว่ามีเงินเพียงพอและลดยอดเงิน
    public boolean checkBalance(int amount) {
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
            return true;
        }
        return false; // กรณีเงินไม่พอหรือจำนวนเงินไม่ถูกต้อง
    }

    public int getBalance() {
        return this.balance;
    }

    public boolean verifyPassword(String accPassword) {
        return this.password.equals(accPassword);
    }

    public boolean verifyId(String accId) {
        return this.id.equals(accId);
    }
}
