package bean;

import java.util.Date;

public class BookInfo {
     private Integer bId; //数据编号
     private String bName; //书籍名称
     private String Author; //作者
     private String Publish; //出版社
     private String Isbn; //书籍编码
     private String Introduction; //书籍介绍
     private float Price; //书籍价格
     private Date Pudate; //出版日期
     private Integer Pressmark; //书架号
     private Integer State; //书籍状态  0 空闲 1借阅 2下架 3 其他
     private Integer Type; //书记类型 0 小说动漫 1 科普读物 2 科学技术 3 医学 4 艺术 5 军事

     private Integer Delete;

     public Integer getDelete() {
          return Delete;
     }

     public void setDelete(Integer delete) {
          Delete = delete;
     }

     public Integer getbId() {
          return bId;
     }

     public void setbId(Integer bId) {
          this.bId = bId;
     }

     public String getbName() {
          return bName;
     }

     public void setbName(String bName) {
          this.bName = bName;
     }

     public String getAuthor() {
          return Author;
     }

     public void setAuthor(String author) {
          Author = author;
     }

     public String getPublish() {
          return Publish;
     }

     public void setPublish(String publish) {
          Publish = publish;
     }

     public String getIsbn() {
          return Isbn;
     }

     public void setIsbn(String isbn) {
          Isbn = isbn;
     }

     public String getIntroduction() {
          return Introduction;
     }

     public void setIntroduction(String introduction) {
          Introduction = introduction;
     }

     public float getPrice() {
          return Price;
     }

     public void setPrice(float price) {
          Price = price;
     }

     public Date getPudate() {
          return Pudate;
     }

     public void setPudate(Date pudate) {
          Pudate = pudate;
     }

     public Integer getPressmark() {
          return Pressmark;
     }

     public void setPressmark(Integer pressmark) {
          Pressmark = pressmark;
     }

     public Integer getState() {
          return State;
     }

     public void setState(Integer state) {
          State = state;
     }

     public Integer getType() {
          return Type;
     }

     public void setType(Integer type) {
          Type = type;
     }

     @Override
     public String toString() {
          return "BookInfo{" +
                  "bId=" + bId +
                  ", bName='" + bName + '\'' +
                  ", Author='" + Author + '\'' +
                  ", Publish='" + Publish + '\'' +
                  ", Isbn='" + Isbn + '\'' +
                  ", Introduction='" + Introduction + '\'' +
                  ", Price=" + Price +
                  ", Pudate=" + Pudate +
                  ", Pressmark=" + Pressmark +
                  ", State=" + State +
                  ", Type=" + Type +
                  ", Delete=" + Delete +
                  '}';
     }

}
