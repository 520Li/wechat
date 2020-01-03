package com.lac.wechat.controller;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.lac.wechat.domain.User;
import com.lac.wechat.service.UserService;
import com.lac.wechat.utils.SendMessageUtil;
import com.lac.wechat.vo.Message;
import com.lac.wechat.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.UUID;

@Controller
@Slf4j
public class CommunityController {
    @Autowired
    private UserService userService;


    /**
     * 实名注册页面
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
        return "/system/signup.html";
    }

    /**
     * 实名注册
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/signup.do", method = RequestMethod.POST)
    @ResponseBody
    public Result signup(User user, HttpSession session) {
        if (StringUtils.isBlank(user.getUserId())) {
            return new Result(false, "认证失败!");
        }
        boolean flag = userService.checkIphone(user);
        if (!flag) {
            return new Result(false, "该手机号已实名认证!");
        }
        userService.insertUser(user);
        session.setAttribute("login_user", user);
        return new Result(true, "认证成功！");
    }

    /**
     * 图片上传
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/file.do", method = RequestMethod.POST)
    @ResponseBody
    public Result upload(HttpServletRequest request) {

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("file");
        FileChannel outChannel = null;
        try {
            String oldName = file.getOriginalFilename();
            String suffix = oldName.substring(oldName.lastIndexOf("."));
            if (!suffix.equalsIgnoreCase(".jpg") && !suffix.equalsIgnoreCase(".png") && !suffix.equalsIgnoreCase(".gif") && !suffix.equalsIgnoreCase(".jpeg")) {
                return new Result(false, "只能上传格式为 .jpg /.png / .jpeg / .gif 的图片");
            }
            //后缀名
            String newName = UUID.randomUUID().toString().replace("-", "").toLowerCase() + suffix;
            outChannel = FileChannel.open(Paths.get("./target/classes/static/images/signup/" + newName),
                    StandardOpenOption.WRITE, StandardOpenOption.CREATE);
            ByteBuffer buf = ByteBuffer.allocate((int) file.getSize());
            buf.put(file.getBytes());
            buf.flip();
            outChannel.write(buf);
            buf.clear();

            // 数据库需要保存：相对路径
            String relativePath = "/images/signup/" + newName;
            return new Result(true, "上传成功！", relativePath);
        } catch (Exception e) {
            log.error("{}", e.getMessage());
            return new Result(false, "上传图片失败");
        } finally {
            try {
                if (outChannel != null) {
                    outChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    /**
     * 发送短信验证码
     *
     * @param mobile
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/sendSms.do", method = RequestMethod.POST)
    @ResponseBody
    public Result sendSms(String mobile, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isBlank(mobile)) {
            return new Result(false, "手机号不能为空！");
        }

        String code = SendMessageUtil.getSixNum();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        Message message = new Message(mobile, "紫萝园", "SMS_181851483", jsonObject.toJSONString());
        try {
            SendSmsResponse sendSmsResponse = SendMessageUtil.sendSms(message);
            if ("OK".equalsIgnoreCase(sendSmsResponse.getCode())) {
                return new Result(true, "短信发送成功！", code);
            } else {
                log.error("短信发送失败原因：{}", sendSmsResponse.getMessage());
                return new Result(true, "短信发送失败！");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new Result(true, "短信发送失败！");
        }


    }


    @RequestMapping("/second/appointment.do")
    public String appointment(HttpServletRequest request) {
        return "/menu_02/appointment.html";
    }

    @RequestMapping("/second/apply.do")
    public String apply(HttpServletRequest request) {
        return "/menu_02/apply.html";
    }


    /**
     * 咱。社区
     *
     * @return
     */
    @RequestMapping("/first/index.do")
    public String index(HttpServletRequest request) {
        return "/menu_01/index.html";
    }


    /**
     * 办事指南
     *
     * @return
     */
    @RequestMapping("/second/guide.do")
    public String guide() {
        return "/menu_02/guide.html";
    }

}
