package by.zemich.newsms.core.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString(exclude = "comments")
@EqualsAndHashCode(exclude = "comments")
public class News {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Setter(AccessLevel.NONE)
    private UUID id;
    private Author author;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "news"
    )

    private List<Comment> comments = new ArrayList<>();
    private LocalDateTime time;
    private String title;
    private String text;

    public boolean addComment(Comment comment) {
        if (!comments.contains(comment)) {
            comment.setNews(this);
            return comments.add(comment);
        }
        return false;
    }

    public void removeComment(Comment comment) {
        comment.setNews(null);
        comments.remove(comment);
    }

    public void removeAllComments() {
        this.comments.forEach(comment -> comment.setNews(null));
    }


}
