import java.util.ArrayList;
import java.util.Scanner;

public class ATM implements ATMAction {
    private ArrayList<BankAccount> accounts;
    private ArrayList<Manager> managers;

    public static void main(String[] args) {
        boolean isManagerCreated = false;
        Scanner scanner = new Scanner(System.in);
        ATM atm = new ATM();

        // สร้าง Manager
        System.out.print("Create the manager [Y/N]: ");
        String create = scanner.nextLine();
        if (create.equalsIgnoreCase("y")) {
            isManagerCreated = true;
            System.out.print("Enter Manager Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Manager Surname: ");
            String surname = scanner.nextLine();
            System.out.print("Enter Manager ID Card: ");
            String managerIdCard = scanner.nextLine();
            System.out.print("Enter Manager Gender [Male/Female]: ");
            String managerGender = scanner.nextLine();
            System.out.print("Enter Manager Account ID: ");
            String managerAccountId = scanner.nextLine();
            System.out.print("Enter Manager Account Password: ");
            String managerPassword = scanner.nextLine();

            Manager manager = new Manager(name, surname, managerAccountId, managerPassword, managerIdCard,
                    managerGender);
            atm.addManagerAccount(manager);
            System.out.println("Manager account created successfully!");

            // ล็อกอิน Manager
            System.out.println("Manager Log in to use ATM");
            System.out.print("Manager ID: ");
            String loginId = scanner.nextLine();
            System.out.print("Account Password: ");
            String loginPass = scanner.nextLine();
            if (manager.login(loginId, loginPass)) {
                System.out.println("Login successful!");
                // สร้างบัญชีธนาคาร
                System.out.print("Step 1. Enter the number of accounts to create: ");
                int accountCount = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                for (int i = 1; i <= accountCount; i++) {
                    System.out.println("Creating account #" + i);
                    System.out.print("Name: ");
                    String accname = scanner.nextLine();
                    System.out.print("Surname: ");
                    String surnameAcc = scanner.nextLine();
                    System.out.print("ID Card: ");
                    String idCard = scanner.nextLine();
                    System.out.print("Gender [Male/Female]: ");
                    String gender = scanner.nextLine();
                    System.out.print("Account ID : ");
                    String accountId = scanner.nextLine();
                    System.out.print("Password : ");
                    String password = scanner.nextLine();
                    System.out.print("Initial Balance: ");
                    int balance = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    BankAccount account = new BankAccount(accname, surnameAcc, accountId, password, balance, idCard,
                            gender);
                    atm.addAccount(account);
                    System.out.println("Account added successfully!");
                }
                // ให้ผู้ใช้ล็อกอินเพื่อทำธุรกรรม
                boolean running = true;
                while (running) {
                    System.out.println("Please log in to an account to use ATM services.");
                    System.out.print("Enter Account ID: ");
                    String accountId = scanner.nextLine();
                    System.out.print("Enter Account Password: ");
                    String accountPassword = scanner.nextLine();

                    BankAccount loggedInAccount = atm.findAccountById(accountId);
                    if (loggedInAccount != null && loggedInAccount.login(accountId, accountPassword)) {
                        System.out.println("Login successful! Welcome, " + loggedInAccount.getAccountName());
                        boolean loggedIn = true;

                        // ATM Menu
                        while (loggedIn) {
                            System.out.println("\nATM Menu:");
                            System.out.println("1. Check Balance");
                            System.out.println("2. Deposit Money");
                            System.out.println("3. Withdraw Money");
                            System.out.println("4. Transfer Money");
                            System.out.println("5. Exit");

                            System.out.print("Choose an option: ");
                            int choice = scanner.nextInt();
                            scanner.nextLine(); // Consume newline

                            switch (choice) {
                                case 1:
                                    System.out.println("Checking balance...");
                                    atm.checkable(loggedInAccount);
                                    break;
                                case 2:
                                    System.out.print("Enter amount to deposit: ");
                                    int depositAmount = scanner.nextInt();
                                    scanner.nextLine(); // Consume newline
                                    System.out.println("Depositing money...");
                                    atm.depositable(loggedInAccount, depositAmount);
                                    break;
                                case 3:
                                    System.out.print("Enter amount to withdraw: ");
                                    int withdrawAmount = scanner.nextInt();
                                    scanner.nextLine(); // Consume newline
                                    System.out.println("Withdrawing money...");
                                    atm.withdrawable(loggedInAccount, withdrawAmount);
                                    break;
                                case 4:
                                    System.out.print("Enter recipient Account ID: ");
                                    String recipientId = scanner.nextLine();
                                    System.out.print("Enter amount to transfer: ");
                                    int transferAmount = scanner.nextInt();
                                    scanner.nextLine(); // Consume newline

                                    BankAccount recipientAccount = atm.findAccountById(recipientId);
                                    if (recipientAccount != null) {
                                        System.out.println("Transferring money...");
                                        atm.transferable(loggedInAccount, recipientAccount, transferAmount);
                                    } else {
                                        System.out.println("Recipient account not found. Transfer failed.");
                                    }
                                    break;
                                case 5:
                                    loggedIn = false;
                                    System.out.println("Thank you for using the ATM. Goodbye!");
                                    break;
                                default:
                                    System.out.println("Invalid option. Please try again.");
                            }
                        }
                    }
                }
            } else {
                System.out.println("Login failed. Exiting system.");
            }
        } else {
            System.out.println("Manager not created. Exiting system.");
        }

        scanner.close();
    }

    public ATM() {
        this.accounts = new ArrayList<>();
        this.managers = new ArrayList<>();
    }

    public BankAccount findAccountById(String accountId) {
        for (BankAccount account : accounts) {
            if (account.getAccountId().equals(accountId)) {
                return account;
            }
        }
        return null;
    }

    @Override
    public void addManagerAccount(Manager account) {
        this.managers.add(account);
    }

    @Override
    public void addAccount(BankAccount account) {
        this.accounts.add(account);
    }

    // ตรวจสอบเงินคงเหลือ
    @Override
    public boolean checkable(BankAccount loggedInAccount) {
        if (loggedInAccount == null) {
            System.out.println("No account is currently logged in.");
            return false;
        }
        System.out
                .println("Account: " + loggedInAccount.getAccountName() + ", Balance: " + loggedInAccount.getBalance());
        return true;
    }

    // การถอนเงิน
    @Override
    public boolean withdrawable(BankAccount loggedInAccount, int amount) {
        if (loggedInAccount == null) {
            System.out.println("No account is currently logged in.");
            return false;
        }
        if (amount > 0 && loggedInAccount.getBalance() >= amount) {
            loggedInAccount.withdraw(amount);
            return true;
        }
        System.out.println("Withdrawal failed. Insufficient funds or invalid amount.");
        return false;
    }

    // การฝากเงิน
    @Override
    public boolean depositable(BankAccount loggedInAccount, int amount) {
        if (loggedInAccount == null) {
            System.out.println("No account is currently logged in.");
            return false;
        }
        if (amount > 0) {
            loggedInAccount.deposit(amount);
            return true;
        }
        System.out.println("Deposit failed. Invalid amount.");
        return false;
    }

    // การโอนเงิน
    @Override
    public boolean transferable(BankAccount loggedInAccount, BankAccount recipient, int amount) {
        if (loggedInAccount == null) {
            System.out.println("No account is currently logged in.");
            return false;
        }
        if (recipient == null) {
            System.out.println("Recipient account not found.");
            return false;
        }
        if (amount > 0 && loggedInAccount.getBalance() >= amount) {
            loggedInAccount.withdraw(amount);
            recipient.deposit(amount);
            System.out.println("Transfer successful. Transferred " + amount + " to " + recipient.getAccountName());
            return true;
        }
        System.out.println("Transfer failed. Insufficient funds or invalid amount.");
        return false;
    }
}
