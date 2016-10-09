package course.domain;


import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Nox on 05.10.2016.
 */
@Entity @Indexed
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) @DocumentId
    @Column(name = "ID")
    private long id;

    public Site(){}
    public Site(String name, long ownerId, String createDate, String editDate, String tags){
        this.name = name;
        this.ownerId = ownerId;
        this.createDate = createDate;
        this.editDate = editDate;
        this.source = " \"{\"A\":[]}\"";
        this.tags = tags;

    }
    public Site(String name, long ownerId){
        this.name = name;
        this.ownerId = ownerId;
        this.createDate = null;
        this.editDate = null;
        this.source = " \"{\"A\":[]}\"";
        this.tags = null;

    }
    public Site(String name, long ownerId, String createDate, String editDate, String source, String tags){
        this.name = name;
        this.ownerId = ownerId;
        this.createDate = createDate;
        this.editDate = editDate;
        this.source = source;
        this.tags = tags;

    }

    @Column(name = "name")
    @Field(index = Index.YES, analyze= Analyze.YES , store= Store.NO)
    private String name;

    @Column(name = "owner_id")
    private long ownerId;

    @Column(name = "create_date")
    private String createDate;

    @Column(name = "edit_date")
    private String editDate;

    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "source")
    @Field(index = Index.YES, analyze= Analyze.YES , store= Store.NO)
    private  String source;

  //  @OneToMany @IndexedEmbedded
  //  @Type(type = "org.hibernate.type.ListType")
    @Column(name = "tags")
 //   @Field(index = Index.YES, analyze= Analyze.YES , store= Store.NO)
    private String tags;


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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
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

