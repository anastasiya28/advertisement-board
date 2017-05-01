package myProjectTest;

import myProject.model.Advertisement;
import myProject.repository.interfaces.AdvertisementDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ContextConfiguration(classes = TestConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class SmokeTest {
    @Autowired
    private AdvertisementDAO advertisementDAO;

    @Autowired
    private HibernateTransactionManager transactionManager;


    @Test
    @Commit
    @Transactional
    public void addTest() {
        Advertisement ad = new Advertisement();
        ad.setName("Снимаю, порчу...");
        ad.setDescription("Быстро, качественно, недорого ;) ");
        System.out.println(advertisementDAO.add(ad));
        System.out.println(advertisementDAO.getAll());
        transactionManager.getSessionFactory().getCurrentSession().flush();
    }

    @Test
    @Transactional
    public void getAllTest() {
        List<Advertisement> list = advertisementDAO.getAll();
        System.out.println(list);
    }

    @Test
    @Transactional
    public void getByIdTest() {
        System.out.println(advertisementDAO.getById(1));
        System.out.println(advertisementDAO.getById(2));
    }

    @Test
    @Transactional
    public void findByTextTest() {
        System.out.println(advertisementDAO.getByNameOrDescription("Хороший"));
    }
}
