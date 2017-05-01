package myProject.repository.impls;

import myProject.model.Comment;
import myProject.repository.interfaces.CommentDAO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("commentRepository")
public class CommentDAOImpl implements CommentDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getAll() {
        return (List<Comment>) sessionFactory.getCurrentSession().createQuery("from comment").list();
    }

    @Override
    @Transactional(readOnly = true)
    public Comment getById(Integer id) {
        return (Comment) sessionFactory.getCurrentSession()
                .createQuery("from comment where id = " + id)
                .uniqueResult();
    }

    @Override
    @Transactional(readOnly = false)
    public Integer add(Comment comment) {
        Integer id = (Integer) sessionFactory.getCurrentSession().save(comment);
        comment.setId(id);
        return id;
    }

    @Override
    @Transactional(readOnly = false)
    public void update(Comment comment) {
        sessionFactory.getCurrentSession().update(comment);
    }

    @Override
    @Transactional(readOnly = false)
    public void remove(Comment comment) {
        sessionFactory.getCurrentSession().delete(comment);
    }
}
