package myProject.repository.interfaces;

import myProject.model.Advertisement;

import java.util.List;

public interface AdvertisementDAO {
    List<Advertisement> getAll();

    List<Advertisement> getByNameOrDescription(String text);

    Advertisement getById(Integer id);

    Integer add(Advertisement advertisement);

    void update(Advertisement advertisement);

    void remove(Advertisement advertisement);
}
