package pojo;

public class User {
    private Integer uid;
    private String name;
    private Integer password;
    private Integer remeber;


    public User(){}

    public User(Integer uid, String name, Integer password, Integer remeber) {
        this.uid = uid;
        this.name = name;
        this.password = password;
        this.remeber = remeber;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPassword() {
        return password;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }

    public Integer getRemeber() {
        return remeber;
    }

    public void setRemeber(Integer remeber) {
        this.remeber = remeber;
    }

    @Override
    public String toString() {
        return uid + "\t\t" + name + "\t\t" + password + "\t\t" + remeber;
    }
}
