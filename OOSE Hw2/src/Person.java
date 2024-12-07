public class Person {
    private String idCard;
    private String name;
    private String surname;
    private String gender;

    public Person(String idCard, String name, String surname, String gender){
        this.idCard = idCard;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
    }
    
    public String getIdCard(){
        return this.idCard;
    }
    public String getName(){
        return this.name +" "+ this.surname;
    }
    public String getGender(){
        return this.gender;
    }
}
