package my.pkg.addressbook.model;

public class ContactData {
    private String fName;
    private String midName;
    private String lastName;
    private String nickName;
    private String address;
    private String mobPhone;
    private String email;
    private String group;

    public ContactData(String fName, String midName, String lastName, String nickName, String address, String mobPhone, String email, String group) {
        this.fName = fName;
        this.midName = midName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.address = address;
        this.mobPhone = mobPhone;
        this.email = email;
        this.group = group;
    }

    public String getfName() {
        return fName;
    }

    public String getMidName() {
        return midName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getAddress() {return address;}

    public String getMobPhone() {
        return mobPhone;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() { return group; }
}
