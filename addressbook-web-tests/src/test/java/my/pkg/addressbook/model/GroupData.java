package my.pkg.addressbook.model;

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

    @Override
    public String toString() {
        return "GroupData{" +
                "gname='" + gname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupData groupData = (GroupData) o;

        return gname != null ? gname.equals(groupData.gname) : groupData.gname == null;
    }

    @Override
    public int hashCode() {
        return gname != null ? gname.hashCode() : 0;
    }
}
