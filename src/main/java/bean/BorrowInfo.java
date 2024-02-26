package bean;

/**
 * 借阅记录
 */
public class BorrowInfo {
    private Integer id;//编号
    private String readerName;//读者姓名
    private String bookName;//借阅书籍名称
    private String borrowTime;//借阅时间
    private String backTime;//归还时间
    private Integer borrowState;//借阅状态记录 0 借阅中 1 已归还

    @Override
    public String toString() {
        return "BorrowInfo{" +
                "id=" + id +
                ", readerName='" + readerName + '\'' +
                ", bookName='" + bookName + '\'' +
                ", borrowTime=" + borrowTime +
                ", backTime=" + backTime +
                ", borrowState=" + borrowState +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReaderName() {
        return readerName;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(String borrowTime) {
        this.borrowTime = borrowTime;
    }

    public String getBackTime() {
        return backTime;
    }

    public void setBackTime(String backTime) {
        this.backTime = backTime;
    }

    public Integer getBorrowState() {
        return borrowState;
    }

    public void setBorrowState(Integer borrowState) {
        this.borrowState = borrowState;
    }
}
