package by.zemich.newsms.api.dao;

import by.zemich.newsms.core.domain.Comment;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {

    @Override
    void deleteById(UUID id);
}
