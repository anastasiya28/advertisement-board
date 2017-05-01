package myProject.repository.interfaces;

import myProject.model.Comment;

import java.util.List;

public interface CommentDAO {
    List<Comment> getAll();

    Comment getById(Integer id);

    Integer add(Comment comment);

    void update(Comment comment);

    void remove(Comment comment);
}
