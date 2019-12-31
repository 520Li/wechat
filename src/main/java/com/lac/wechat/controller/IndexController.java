package com.lac.wechat.controller;

import com.lac.wechat.service.ApiService;
import com.lac.wechat.utils.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
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
    public String index(HttpServletRequest request,ModelMap map) {
        map.putAll(apiService.fetchJsSdk(request.getRequestURL().toString()));
        return "/search.html";
    }
}
