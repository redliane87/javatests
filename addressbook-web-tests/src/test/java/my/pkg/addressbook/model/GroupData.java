package my.pkg.addressbook.model;

public class GroupData {
    private final String id;
    private final String gname;
    private final String gheader;
    private final String gfooter;

    public GroupData(String gname, String gheader, String gfooter) {
        this.id = null;
        this.gname = gname;
        this.gheader = gheader;
        this.gfooter = gfooter;
    }
    public GroupData(String id, String gname, String gheader, String gfooter) {
        this.id = id;
        this.gname = gname;
        this.gheader = gheader;
        this.gfooter = gfooter;
    }

    public String getId() {
        return id;
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
                "id='" + id + '\'' +
                ", gname='" + gname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupData groupData = (GroupData) o;

        if (id != null ? !id.equals(groupData.id) : groupData.id != null) return false;
        return gname != null ? gname.equals(groupData.gname) : groupData.gname == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (gname != null ? gname.hashCode() : 0);
        return result;
    }
}
