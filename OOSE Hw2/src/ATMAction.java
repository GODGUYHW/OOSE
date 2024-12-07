public interface ATMAction {
    boolean checkable(BankAccount loggedInAccount);

    boolean withdrawable(BankAccount loggedInAccount, int amount);

    boolean depositable(BankAccount loggedInAccount, int amount);

    boolean transferable(BankAccount loggedInAccount, BankAccount recipient, int amount);

    void addAccount(BankAccount account);

    void addManagerAccount(Manager account);
}
