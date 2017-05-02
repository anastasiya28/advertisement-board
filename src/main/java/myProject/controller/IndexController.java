package myProject.controller;

import myProject.PageGenerator;
import myProject.repository.interfaces.AdvertisementDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {
    @Autowired
    private AdvertisementDAO advertisementDAO;

    @SuppressWarnings("SameReturnValue")
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = {"text/html; charset=UTF-8"})
    @ResponseBody
    @Transactional
    public String homePage() {
//        Map<String, Object> map = new HashMap<>();
//        map.put("adverts", advertisementDAO.getAll());
//        map.put("var1", advertisementDAO.getById(1));
        return PageGenerator.instance().getPage("index.html", null);
    }
}
