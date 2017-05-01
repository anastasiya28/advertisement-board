package myProject.repository.impls;

import myProject.model.Advertisement;
import myProject.repository.interfaces.AdvertisementDAO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("advertisementRepository")
public class AdvertisementDAOImpl implements AdvertisementDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional(readOnly = true)
    public List<Advertisement> getAll() {
        return (List<Advertisement>) sessionFactory.getCurrentSession()
                .createQuery("from Advertisement a ORDER BY a.createOn DESC")
                .getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Advertisement getById(Integer id) {
        return sessionFactory.getCurrentSession().load(Advertisement.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Advertisement> getByNameOrDescription(String text) {
        String hql = "from Advertisement a " +
                " where a.name like '%" + text + "%'" +
                " OR a.description like '%" + text + "%'" +
                " ORDER BY a.createOn DESC";

        return (List<Advertisement>) sessionFactory.getCurrentSession()
                .createQuery(hql).getResultList();
    }

    @Override
    @Transactional
    public Integer add(Advertisement advertisement) {
        return (Integer) sessionFactory.getCurrentSession().save(advertisement);
    }

    @Override
    @Transactional
    public void update(Advertisement advertisement) {
        sessionFactory.getCurrentSession().update(advertisement);
    }

    @Override
    @Transactional
    public void remove(Advertisement advertisement) {
        sessionFactory.getCurrentSession().delete(advertisement);
    }
}
