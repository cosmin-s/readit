package mysoftwarefactory.readit.model;

import javax.persistence.*;

import java.util.Date;

@Entity
public class Post
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10000)
    private String content;

    private Date created;

    //a post can refer to the owning thread or to a parent post if parent is not null
    private Long parent;

    @Column(name = "thread_post_id")
    private Long threadPostId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public Long getThreadPostId() {
        return threadPostId;
    }

    public void setThreadPostId(Long threadPostId) {
        this.threadPostId = threadPostId;
    }
}
