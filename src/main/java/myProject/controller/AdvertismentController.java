package myProject.controller;

import myProject.PageGenerator;
import myProject.model.Advertisement;
import myProject.model.Comment;
import myProject.repository.interfaces.AdvertisementDAO;
import myProject.repository.interfaces.CommentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@ResponseBody
@Transactional
public class AdvertismentController {
    @Autowired
    private AdvertisementDAO advertisementDAO;

    @Autowired
    private CommentDAO commentDAO;

    @SuppressWarnings("SameReturnValue")
    @RequestMapping(value = "/advert/list", method = RequestMethod.GET, produces = {"text/html; charset=UTF-8"})
    @ResponseBody
    public String showAllAdverts() {
        Map<String, Object> map = new HashMap<>();
        map.put("adverts", advertisementDAO.getAll());
        return PageGenerator.instance().getPage("advert_list.html", map);
    }

    @SuppressWarnings("SameReturnValue")
    @RequestMapping(value = "/advert/search", method = RequestMethod.GET,
            produces = {"text/html; charset=UTF-8"}, params = {"text"})
    @ResponseBody
    public String searchAdverts(@RequestParam(value = "text") String text) {
        Map<String, Object> map = new HashMap<>();
        map.put("adverts", advertisementDAO.getByNameOrDescription(text));
        return PageGenerator.instance().getPage("advert_list.html", map);
    }

    @SuppressWarnings("SameReturnValue")
    @RequestMapping(value = "/advert/new", method = RequestMethod.GET, produces = {"text/html; charset=UTF-8"})
    @ResponseBody
    public String showNewAdvertFrom() {
        return PageGenerator.instance().getPage("advert_new.html", null);
    }

    @SuppressWarnings("SameReturnValue")
    @RequestMapping(value = "/advert/new", method = RequestMethod.POST,
            produces = {"text/html; charset=UTF-8"}, params = {"name", "description"})
    public ModelAndView addNewAdvert(@RequestParam(value = "name") String name, @RequestParam(value = "description") String description) {
        Advertisement advertisement = new Advertisement()
                .setName(name)
                .setDescription(description);
        Integer id = advertisementDAO.add(advertisement);
        advertisement.setId(id);
        return new ModelAndView("redirect:/advert/list");
    }

    @SuppressWarnings("SameReturnValue")
    @RequestMapping(value = "/advert/{id}/details", method = RequestMethod.GET, produces = {"text/html; charset=UTF-8"})
    @ResponseBody
    public String showAdvertDetails(@PathVariable("id") Integer id) {
        Map<String, Object> map = new HashMap<>();
        map.put("advert", advertisementDAO.getById(id));
        return PageGenerator.instance().getPage("advert_details.html", map);
    }


    @SuppressWarnings("SameReturnValue")
    @RequestMapping(value = "/advert/{id}/comment/new", method = RequestMethod.GET, produces = {"text/html; charset=UTF-8"})
    @ResponseBody
    public String showNewCommentFrom() {
        return PageGenerator.instance().getPage("comment_new.html", null);
    }

    @SuppressWarnings("SameReturnValue")
    @RequestMapping(value = "/advert/{id}/comment/new", method = RequestMethod.POST,
            produces = {"text/html; charset=UTF-8"}, params = {"text"})
    public ModelAndView addNewComment(@PathVariable("id") Integer id, @RequestParam(value = "text") String text) {
        ModelAndView modelAndView = new ModelAndView("redirect:/advert/" + id + "/details");
        if(text.isEmpty()) return modelAndView; //если кооментарий пустой, то его не сохраняем

        Advertisement advertisement = advertisementDAO.getById(id);
        Comment comment = new Comment().setAdvertisement(advertisement)
                .setText(text);
        commentDAO.add(comment);
        advertisementDAO.update(advertisement);
        return modelAndView;
    }
}
