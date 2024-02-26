package bean;

/**
 * 用户信息
 */
public class UserInfo {
    private Integer id;//编号
    private String userName;//用户名
    private String userSex;//性别
    private String birthday;//生日
    private String identify;//身份证号码
    private String tel;//电话号码
    private String email;//邮箱
    private String password;//密码
    private Integer integral;//积分
    private int isDeleted;//是否删除 0 已删除 1 未删除
    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", sex='" + userSex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", identify='" + identify + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", integral=" + integral +
                ", isDeleted=" + isDeleted +
                '}';
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserSex() {
        return userSex;
    }
    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }
    public String getBirthday() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public String getIdentify() {
        return identify;
    }
    public void setIdentify(String identify) {
        this.identify = identify;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Integer getIntegral() {
        return integral;
    }
    public void setIntegral(Integer integral) {
        this.integral = integral;
    }
    public int getIsDeleted() {
        return isDeleted;
    }
    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }
}