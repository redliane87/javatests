package my.pkg.addressbook.tests;

public class ContactData {
    private final String fName;
    private final String midName;
    private final String lastName;
    private final String nickName;
    private final String mobPhone;
    private final String email;

    public ContactData(String fName, String midName, String lastName, String nickName, String mobPhone, String email) {
        this.fName = fName;
        this.midName = midName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.mobPhone = mobPhone;
        this.email = email;
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

    public String getMobPhone() {
        return mobPhone;
    }

    public String getEmail() {
        return email;
    }
}
