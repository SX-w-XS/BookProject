package bean;

public class DiscussInfo {
    private Integer id;//评论ID
    private String content;//评论内容
    private String releaseTime;//发表时间
    private String name;//评论者名字

    @Override
    public String toString() {
        return "DiscussInfo{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", releaseTime='" + releaseTime + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
