package my.pkg.addressbook.model;

public class GroupData {
    private  int id;
    private  String gname;
    private  String gheader;
    private  String gfooter;

    public GroupData(String gname, String gheader, String gfooter) {
        this.id = 0;
        this.gname = gname;
        this.gheader = gheader;
        this.gfooter = gfooter;
    }
    public GroupData(int id, String gname, String gheader, String gfooter) {
        this.id = id;
        this.gname = gname;
        this.gheader = gheader;
        this.gfooter = gfooter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

        if (id != groupData.id) return false;
        return gname != null ? gname.equals(groupData.gname) : groupData.gname == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (gname != null ? gname.hashCode() : 0);
        return result;
    }
}
