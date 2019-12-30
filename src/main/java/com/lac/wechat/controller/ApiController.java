package com.lac.wechat.controller;


import com.alibaba.fastjson.JSONObject;
import com.lac.wechat.service.ApiService;
import com.lac.wechat.service.MenuService;
import com.lac.wechat.service.MessageService;
import com.lac.wechat.template.Message;
import com.lac.wechat.utils.EncryptUtil;
import com.lac.wechat.utils.FastJsonUtil;
import com.lac.wechat.utils.RequestUtil;
import com.lac.wechat.utils.XmlUtil;
import com.lac.wechat.vo.AccessToken;
import com.lac.wechat.vo.GetParam;
import com.lac.wechat.vo.JsapiTicket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class ApiController {

    @Value("${token}")
    private String token;
    @Autowired
    private ApiService apiService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private MenuService menuService;

    /**
     * 校验服务器的有效性
     *
     * @param param
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public void index(GetParam param, HttpServletRequest request, HttpServletResponse response) throws Exception {
        boolean flag = checkParam(param);
        if (flag) {
            response.getWriter().println(param.getEchostr());
        } else {
            response.getWriter().println("认证失败");
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void message(GetParam param, HttpServletRequest request, HttpServletResponse response) throws Exception {
        boolean flag = checkParam(param);
        if (!flag) {
            response.sendError(500, "认证失败");
        }
        String xmlStr = RequestUtil.ReadAsChars(request);
        //log.info("{}", xmlStr);
        String jsonStr = XmlUtil.xml2Json(xmlStr).toString();
        //log.info("{}", jsonStr);

        //获取接收消息
        Message message = formatJsonStr(jsonStr);
        //自定义回复消息
        String replyTemplate = messageService.getReplyTemplate(message);

        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(replyTemplate);


    }

    /*
     * @param jsonStr
     * @return
     */
    private Message formatJsonStr(String jsonStr) {
        Map<Object, Object> map = FastJsonUtil.stringToCollect(jsonStr);
        Message message = JSONObject.parseObject(JSONObject.toJSONString(map.get("xml")), Message.class);
        return message;
    }


    /*
     * 校验参数
     *
     * @param param
     * @return
     */
    private boolean checkParam(GetParam param) {
        List<String> list = Arrays.asList(param.getTimestamp(), param.getNonce(), token);
        list.sort(String::compareTo);
        String result = list.stream().collect(Collectors.joining(""));
        result = EncryptUtil.getSha1(result);
        return result.equals(param.getSignature());
    }


    /**
     * 测试
     *
     * @return
     */
    @RequestMapping("/test")
    @ResponseBody
    public JsapiTicket test() throws Exception {
        //AccessToken accessToken = apiService.fetchAccessToken();
        menuService.getMenu();
        JsapiTicket jsapiTicket = apiService.fetchJsapiTicket();
        return jsapiTicket;
    }


}
