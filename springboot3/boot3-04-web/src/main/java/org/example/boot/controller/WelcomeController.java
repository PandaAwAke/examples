package org.example.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WelcomeController {

    @GetMapping("/welcome")
    public String hello(@RequestParam("name") String name,
                        Model model) {
        // 逻辑视图名是 welcome
        // 物理视图 = 前缀 + 逻辑视图名 + 后缀
        // 即：classpath:/templates/welcome.html

        // 把页面需要的模板数据放到 model 中
        model.addAttribute("name", name);
        return "welcome";
    }

}
