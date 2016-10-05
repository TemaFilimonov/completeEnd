package course.domain;

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
    }
    public Site(String name, long ownerId){
        this.name = name;
        this.ownerId = ownerId;
    }

    @Column(name = "name")
    private String name;

    @Column(name = "owner_id")
    private long ownerId;

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

