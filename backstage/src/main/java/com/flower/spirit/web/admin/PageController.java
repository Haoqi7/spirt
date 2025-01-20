package com.flower.spirit.web.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.flower.spirit.config.Global;
import com.flower.spirit.entity.BiliConfigEntity;
import com.flower.spirit.entity.ConfigEntity;
import com.flower.spirit.service.BiliConfigService;
import com.flower.spirit.service.ConfigService;


@Controller
@RequestMapping(value = "/admin")
public class PageController {
	

	@Autowired
	private ConfigService configService;
	
	@Autowired
	private BiliConfigService biliConfigService;
	
	@RequestMapping(value = "/admin")
	public String admin(HttpServletRequest request) {
		if(request.getSession().getAttribute(Global.user_session_key) ==  null) {
			return "admin/login";
		}else {
			return "admin/index";
		}
	}
	@RequestMapping(value = "/login")
	public String login(HttpServletRequest request) {
		if(request.getSession().getAttribute(Global.user_session_key) !=  null) {
			return "redirect:/admin/index";
		}
		return "admin/login";
	}

	@RequestMapping(value = "/index")
	public String index() {
		return "admin/index";
	}
	
	@RequestMapping(value = "/welcome")
	public String welcome() {
		return "admin/welcome";
	}
	@RequestMapping(value = "/loginOut")
	public String loginOut(HttpServletRequest request) {
		request.getSession().setAttribute(Global.user_session_key, null);
		return "admin/login";
	}
	@RequestMapping(value = "/userList")
	public String userList() {
		return "admin/userList";
	}
	@RequestMapping(value = "/addUser")
	public String addUser() {
		return "admin/addUser";
	}
	
	@RequestMapping(value = "/downLoaderList")
	public String downLoaderList() {
		return "admin/downLoaderList";
	}
	@RequestMapping(value = "/config")
	public String config(Model model) {
		ConfigEntity config = configService.getData();
		BiliConfigEntity bili = biliConfigService.getData();
		model.addAttribute("bili", bili);
		model.addAttribute("config", config);
		return "admin/config";
	}
	@RequestMapping(value = "/videoDataList")
	public String videoDataList() {
		return "admin/videoDataList";
	}
}
