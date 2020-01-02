package com.lac.wechat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommunityController {

    @RequestMapping("first/index.do")
    public String index(){
        return "/menu_01/index.html";
    }


    @RequestMapping("/second/guide.do")
    public String guide(){
        return "/menu_02/guide.html";
    }

}
