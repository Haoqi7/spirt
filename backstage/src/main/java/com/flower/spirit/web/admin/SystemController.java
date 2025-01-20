package com.flower.spirit.web.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.flower.spirit.common.AjaxEntity;
import com.flower.spirit.config.Global;
import com.flower.spirit.service.SystemService;




@Controller
@RequestMapping(value = "/admin/system")
public class SystemController {

	
	@Autowired
	private SystemService systemService;
	
	@RequestMapping("/uploadFile")
	@ResponseBody
	public AjaxEntity uploadFile(MultipartFile file,HttpServletRequest request,HttpServletResponse response) {
		String uploadFile = systemService.uploadFile(file, request, "system");
		return new AjaxEntity(Global.ajax_success,Global.ajax_option_success,uploadFile);
	}
}
