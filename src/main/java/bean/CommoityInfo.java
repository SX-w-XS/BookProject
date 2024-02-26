package bean;

import java.util.Date;

public class CommoityInfo {
    private Integer Cid;
    private String Cname;

    private Integer Cprice;
    private Integer Ccount;
    private Integer Cnumber;
    private Date Cpudate;
    private String Cintroduction;

    private Integer Cstate;

    public Integer getCstate() {
        return Cstate;
    }

    public void setCstate(Integer cstate) {
        this.Cstate = cstate;
    }

    public Integer getCid() {
        return Cid;
    }

    public void setCid(Integer cid) {
        Cid = cid;
    }

    public String getCname() {
        return Cname;
    }

    public void setCname(String cname) {
        Cname = cname;
    }

    public Integer getCprice() {
        return Cprice;
    }

    public void setCprice(Integer cprice) {
        Cprice = cprice;
    }

    public Integer getCcount() {
        return Ccount;
    }

    public void setCcount(Integer ccount) {
        Ccount = ccount;
    }

    public Integer getCnumber() {
        return Cnumber;
    }

    public void setCnumber(Integer cnumber) {
        Cnumber = cnumber;
    }

    public Date getCpudate() {
        return Cpudate;
    }

    public void setCpudate(Date cpudate) {
        Cpudate = cpudate;
    }

    public String getCintroduction() {
        return Cintroduction;
    }

    public void setCintroduction(String cintroduction) {
        Cintroduction = cintroduction;
    }

    @Override
    public String toString() {
        return "CommoityInfo{" +
                "Cid=" + Cid +
                ", Cname='" + Cname + '\'' +
                ", Cprice=" + Cprice +
                ", Ccount=" + Ccount +
                ", Cnumber=" + Cnumber +
                ", Cpudate=" + Cpudate +
                ", Cintroduction='" + Cintroduction + '\'' +
                ", cstate=" + Cstate +
                '}';
    }

}