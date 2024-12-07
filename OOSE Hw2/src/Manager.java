public class Manager extends Person {
    private String accountId;
    private String password;

    public Manager( String name, String surname  , String accId , String accPassword , String idCard, String gender){
        super(idCard,name,surname,gender);
        this.accountId = accId;
        this.password = accPassword;
    }
    public boolean login(String inputLogin, String inputPassword){
        return this.accountId.equals(inputLogin) && this.password.equals(inputPassword);
    }
}
