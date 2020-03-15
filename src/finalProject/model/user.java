package finalProject.model;

public class user {
    //6email/sdt/diachi
    private String user;
    private String password;
    private String name;
    private String email;
    private String sdt;

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String toString(int address) {
        return user + '#' + password + "#" + name + '#' + email + '#' + address
                + '#' + sdt + "#1#1#1#a#In#Out\n";
    }

    public void setUsername(String user) {
        this.user=user;
    }
}
