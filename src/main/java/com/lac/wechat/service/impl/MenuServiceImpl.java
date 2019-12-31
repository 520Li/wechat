package com.lac.wechat.service.impl;


import com.lac.wechat.service.ApiService;
import com.lac.wechat.service.MenuService;
import com.lac.wechat.utils.HttpClientUtil;
import com.lac.wechat.vo.AccessToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Service
@Slf4j
public class MenuServiceImpl implements MenuService {

    @Value("${getMenu}")
    private String getMenuUrl;
    @Value("${delMenu}")
    private String delMenuUrl;
    @Autowired
    private ApiService apiService;


    /**
     * 更改菜单
     */
    @Override
    public void getMenu() throws Exception {
        delMenu();
        FileChannel inChannel = FileChannel.open(Paths.get("./src/main/resources/json/menu.json"), StandardOpenOption.READ);
        ByteBuffer buf = ByteBuffer.allocate(4096);
        inChannel.read(buf);
        buf.flip();
        byte[] dst = new byte[buf.limit()];
        buf.get(dst);
        buf.clear();
        inChannel.close();
        log.info(new String(dst));
        String result = HttpClientUtil.sendJsonStr(getMenuUrl, new String(dst));
        //log.info(result);
    }

    /**
     * 删除菜单
     */
    private void delMenu() {
        AccessToken accessToken = apiService.fetchAccessToken();
        getMenuUrl = String.format(getMenuUrl,accessToken.getAccess_token());
        delMenuUrl = String.format(delMenuUrl,accessToken.getAccess_token());
        String result = HttpClientUtil.doGet(delMenuUrl, null);
        //log.info(result);
    }
}
