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
    private LocalDateTime time;
    private String title;
    private String text;

    @Embedded
    private Author author;

    @Setter(AccessLevel.NONE)
    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Comment> comments = new ArrayList<>();

    public boolean addComment(Comment comment) {
        return comments.add(comment);
    }

}
