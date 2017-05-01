package myProject.model;

import javax.persistence.*;

@Entity
@Table(name = "comment")

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "advertisementId")
    private Advertisement advertisement;

    public Comment() {
    }


    public Integer getId() {
        return id;
    }

    public Comment setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getText() {
        return text;
    }

    public Comment setText(String text) {
        this.text = text;
        return this;
    }

    public Advertisement getAdvertisement() {
        return advertisement;
    }

    public Comment setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
        return this;
    }

    @Override
    public String toString() {
        return "\nComment{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (!id.equals(comment.id)) return false;
        if (!text.equals(comment.text)) return false;
        return advertisement.equals(comment.advertisement);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + ((text == null) ? 0 : text.hashCode());
        result = 31 * result + ((advertisement == null) ? 0 : advertisement.hashCode());
        return result;
    }
}
