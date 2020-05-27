package my.pkg.addressbook.tests;

public class GroupData {
    private final String gname;
    private final String gheader;
    private final String gfooter;

    public GroupData(String gname, String gheader, String gfooter) {
        this.gname = gname;
        this.gheader = gheader;
        this.gfooter = gfooter;
    }

    public String getGname() {
        return gname;
    }

    public String getGheader() {
        return gheader;
    }

    public String getGfooter() {
        return gfooter;
    }
}
