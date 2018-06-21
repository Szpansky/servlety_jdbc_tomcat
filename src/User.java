public class User {
    private String email;
    private String password;
    private String level;

    public User(String email, String password, String level) {
        this.email = email;
        this.password = password;
        this.level = level;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevel() {
         return level;
    }
}
