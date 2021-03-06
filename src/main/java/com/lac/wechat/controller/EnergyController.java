package com.lac.wechat.controller;

import com.lac.wechat.domain.BusBuildingInfo;
import com.lac.wechat.service.BuildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/energy")
public class EnergyController {

    @Autowired
    private BuildService buildService;

    @RequestMapping("/list.do")
    @ResponseBody
    public List<BusBuildingInfo> list() {
        return buildService.getEnergyList();
    }

}
