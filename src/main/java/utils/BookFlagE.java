package utils;

/**
 * 书籍工具类:
 * 用于判断书籍状态
 */
public enum BookFlagE {
    FREE("空闲",0),BORROW("借阅",1),DOWN("下架",2),OTHER("其他",3);
    public String name;
    public int code;
    BookFlagE(String name, int code){
        this.name = name;
        this.code = code;
    }
}
