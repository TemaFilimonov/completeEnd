package course.domain;

import javax.persistence.*;

/**
 * Created by Nox on 05.10.2016.
 */
@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;


    public Tag(){
        this.tagValue =null;
        //this.siteId= 0;
    }
    public Tag(String tagValue){
        this.tagValue = tagValue;
       // this.siteId = siteId;
    }

    @Column(name = "page_body")
    private String tagValue;
/*

    @Column(name = "site_id")
    private long siteId;
*/

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTagValue() {
        return tagValue;
    }

    public void setTagValue(String tagValue) {
        this.tagValue = tagValue;
    }

/*    public long getSiteId() {
        return siteId;
    }

    public void setSiteId(long siteId) {
        this.siteId = siteId;
    }*/
}
