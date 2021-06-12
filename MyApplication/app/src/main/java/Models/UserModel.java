package Models;

public class UserModel {

    private String name;


    public UserModel(String name, String identification, String country) {
        this.name = name;
        this.identification = identification;
        this.country = country;


    }

    private UserModel(){}

    private String identification;
    private String country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


}


