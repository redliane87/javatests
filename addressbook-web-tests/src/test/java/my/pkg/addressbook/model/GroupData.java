package my.pkg.addressbook.model;

public class GroupData {
    private final String firstname;
    private final String middlename;
    private final String lastname;
    private final String nickname;
    private final String address;
    private final String email;
    private final String email2;
    private final String email3;
    private final String bday;
    private final String bmonth;
    private final String byear;

    public GroupData(String firstname, String middlename, String lastname, String nickname, String address, String email, String email2, String email3, String bday, String bmonth, String byear) {
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.nickname = nickname;
        this.address = address;
        this.email = email;
        this.email2 = email2;
        this.email3 = email3;
        this.bday = bday;
        this.bmonth = bmonth;
        this.byear = byear;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public String getNickname() {
        return nickname;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getBday() {
        return bday;
    }

    public String getBmonth() {
        return bmonth;
    }

    public String getByear() {
        return byear;
    }
}
