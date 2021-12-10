package mysoftwarefactory.readit.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tag
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "tagList"/*, cascade = CascadeType.PERSIST*/)
    private List<ThreadPost> threadPostList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
