package course.domain;


import org.hibernate.annotations.Type;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 * Created by Nox on 05.10.2016.
 */
@Entity
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;


    public Site(){
        this.name =null;
        this.ownerId= 0;
        this.createDate = null;
        this.editDate = null;
        this.source = null;
        this.tag = null;
    }
    public Site(String name, long ownerId, String createDate, String editDate, String source, List<Tag> tag){
        this.name = name;
        this.ownerId = ownerId;
        this.createDate = createDate;
        this.editDate = editDate;
        this.source = source;
        this.tag = tag;

    }

    @Column(name = "name")
    private String name;

    @Column(name = "owner_id")
    private long ownerId;

    @Column(name = "create_date")
    private String createDate;

    @Column(name = "edit_date")
    private String editDate;

    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "source")
    private  String source;

    @Column(name = "tags")
    private List<Tag> tag;

    public List<Tag> getTag() {
        return tag;
    }

    public void setTag(List<Tag> tag) {
        this.tag = tag;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getEditDate() {
        return editDate;
    }

    public void setEditDate(String editDate) {
        this.editDate = editDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }
}

