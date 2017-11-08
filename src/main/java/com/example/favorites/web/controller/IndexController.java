package com.example.favorites.web.controller;

import com.example.favorites.web.aop.LoggerManage;
import com.example.favorites.web.domain.User;
import com.example.favorites.web.domain.view.IndexCollectorView;
import com.example.favorites.web.service.CollectorService;
import com.example.favorites.web.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by cuiyy on 2017/10/25.
 */
@RequestMapping("/")
@Controller
public class IndexController extends BaseController {
    @Autowired
    RedisService redisService;

    @Autowired
    CollectorService collectorService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @LoggerManage(description = "首页")
    public String index(Model model) {
        IndexCollectorView indexCollectorView = (IndexCollectorView) redisService.getObject("collector");
        if (indexCollectorView == null) {
            indexCollectorView = collectorService.getCollectors();
            redisService.setObject("collector", indexCollectorView);
        }

        model.addAttribute("collector", indexCollectorView);
        User user = super.getUser();
        if (null != user) {
            model.addAttribute("user", user);
        }

        return "index";
    }
}
