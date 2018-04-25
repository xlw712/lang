package com.levin.lang.controller;

import com.levin.lang.dao.TbUserMapper;
import com.levin.lang.dto.TbUser;
import com.levin.lang.service.UserService;
import com.levin.lang.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.util.Map;

@Controller
@PropertySource(value = {"classpath:application.properties"}, encoding = "utf-8")
public class WelComeController {
    @Value("${welcome.message:dev}")
    private String name;


    @Autowired
    private  UserService userService;


    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String welcome(@RequestParam Map<String, String> requestParam, Model model, HttpSession session) {
        session.setAttribute("appId", "spring_01");
        //传参
        for (Map.Entry<String, String> entry : requestParam.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        boolean isDev = false;
        Class<WelComeController> wcc = WelComeController.class;
        Field[] fields = wcc.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Value.class)) {
                Value v = field.getAnnotation(Value.class);
                isDev = v.value().contains("dev") ? true : false;
            }
        }
        UserVo user = new UserVo();
        user.setName("xiaoliwen");
        if (isDev) {
            model.addAttribute("name", "xiaoliwen");
            model.addAttribute("user", user);
            return "index";
        } else {
            return "index.html";
        }

    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String Main(@RequestParam Map<String, String> requestParam, Model model, HttpSession session) {
        model.addAttribute("maxcore", Runtime.getRuntime().availableProcessors());
        System.out.println(userService.getUser(1).getUsername());
        return "main";
    }
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public String QueryMain(@RequestParam Map<String, String> requestParam, Model model, HttpSession session) {
        model.addAttribute("maxcore", Runtime.getRuntime().availableProcessors());
        return "main";
    }

}
