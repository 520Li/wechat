package com.lac.wechat.controller;

import com.lac.wechat.service.ApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * ClassName: IndexController <br/>
 *
 * @author lac
 * @version 1.0
 * @date 2019/12/30 0030 - 22:41
 */
@Controller
@Slf4j
@RequestMapping("/search")
public class IndexController {

    @Autowired
    private ApiService apiService;

    @RequestMapping("/list.do")
    public String index(ModelMap map) {
        map.putAll(apiService.fetchJsSdk());
        return "/search.html";
    }
}
