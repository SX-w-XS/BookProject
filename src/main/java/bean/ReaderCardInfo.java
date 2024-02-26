package bean;

/**
 * 借书卡信息
 */
public class ReaderCardInfo {
    private Integer id;//借书卡ID
    private String readerName;//读者名称
    private Integer cardState;//借书卡状态
    private Integer readCount;//借阅书籍数量
    private Integer readTime;//最长借阅时间
    private Integer isDeleted;//是否被删除
    private Integer integral;//积分

    @Override
    public String toString() {
        return "ReaderCard{" +
                "id=" + id +
                ", readerName='" + readerName + '\'' +
                ", cardState=" + cardState +
                ", readCount=" + readCount +
                ", readTime=" + readTime +
                ", isDeleted=" + isDeleted +
                ", integral=" + integral +
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
    public Integer getCardState() {
        return cardState;
    }
    public void setCardState(Integer cardState) {
        this.cardState = cardState;
    }
    public Integer getReadCount() {
        return readCount;
    }
    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }
    public Integer getReadTime() {
        return readTime;
    }
    public void setReadTime(Integer readTime) {
        this.readTime = readTime;
    }
    public Integer getIsDeleted() {
        return isDeleted;
    }
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
    public Integer getIntegral() {
        return integral;
    }
    public void setIntegral(Integer integral) {
        this.integral = integral;
    }
}
