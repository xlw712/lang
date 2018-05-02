package com.levin.lang.login.controller;

import com.levin.lang.login.dto.TbUser;
import com.levin.lang.login.service.UserService;
import com.levin.lang.login.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;

@Controller
@PropertySource(value = {"classpath:application.properties"}, encoding = "utf-8")
public class LoginController {
    @Value("${welcome.message:dev}")
    private String name;


    @Autowired
    private UserService userService;


    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String welcome(@RequestParam Map<String, String> requestParam, Model model, HttpSession session) {
        session.setAttribute("appId", "spring_01");
        //传参
        for (Map.Entry<String, String> entry : requestParam.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        boolean isDev = false;
        Class<LoginController> wcc = LoginController.class;
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
            return "404.html";
        }

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam Map<String, String> requestParam, Model model, HttpSession session) {
        TbUser user = userService.getUserByName("张三");
        System.out.println(user.toString());
        model.addAttribute("maxcore", Runtime.getRuntime().availableProcessors());
        return "main";
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public String query(@RequestParam Map<String, String> requestParam, Model model, HttpSession session) {
        model.addAttribute("maxcore", Runtime.getRuntime().availableProcessors());
        return "main";
    }

    @RequestMapping(value = "/monitor", method = RequestMethod.GET)
    public void monitor(HttpServletResponse response) throws IOException {
        response.sendRedirect("http://localhost:8888/druid/404.html");
    }
}
