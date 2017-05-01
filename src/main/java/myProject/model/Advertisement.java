package myProject.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "advertisement")

public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "createOn")
    private Date createOn;

    @Column(name = "description")
    private String description;

    //    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "advertisement")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "advertisement")
    private List<Comment> comments;

    public Advertisement() {
        this.createOn = new Date();
    }

    public Integer getId() {
        return id;
    }

    public Advertisement setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Advertisement setName(String name) {
        this.name = name;
        return this;
    }

    public Date getCreateOn() {
        return createOn;
    }

    public Advertisement setCreateOn(Date createOn) {
        this.createOn = createOn;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Advertisement setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public Advertisement setComments(List<Comment> comments) {
        this.comments = comments;
        return this;
    }

    public Advertisement commentAdd(Integer id, Comment comment) {
        comments.add(id, comment);
        return this;
    }

    public void commentRemove(Integer id) {
        comments.remove(id);
    }

    @Override
    public String toString() {
        return "\nAdvertisement{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createOn=" + createOn +
                ", description='" + description + '\'' +
                ", comments=" + comments +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Advertisement that = (Advertisement) o;

        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        if (!createOn.equals(that.createOn)) return false;
        if (!description.equals(that.description)) return false;
        return comments.equals(that.comments);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + ((name == null) ? 0 : name.hashCode());
        result = 31 * result + ((createOn == null) ? 0 : createOn.hashCode());
        result = 31 * result + ((description == null) ? 0 : description.hashCode());
        result = 31 * result + ((comments == null) ? 0 : comments.hashCode());
        return result;
    }
}
