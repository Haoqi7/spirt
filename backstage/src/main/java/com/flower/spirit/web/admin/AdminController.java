package com.flower.spirit.web.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flower.spirit.common.AjaxEntity;
import com.flower.spirit.common.RequestEntity;
import com.flower.spirit.entity.BiliConfigEntity;
import com.flower.spirit.entity.ConfigEntity;
import com.flower.spirit.entity.DownloaderEntity;
import com.flower.spirit.entity.UserEntity;
import com.flower.spirit.entity.VideoDataEntity;
import com.flower.spirit.service.BiliConfigService;
import com.flower.spirit.service.ConfigService;
import com.flower.spirit.service.DownloaderService;
import com.flower.spirit.service.SystemService;
import com.flower.spirit.service.UserService;
import com.flower.spirit.service.VideoDataService;


@RestController
@RequestMapping("/admin/api")
public class AdminController {
	
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private UserService userService;
	
	
	@Autowired
	private DownloaderService downloaderService;
	
	
	@Autowired
	private ConfigService configService;

	
	@Autowired
	private VideoDataService videoDataService;
	
	@Autowired
	private BiliConfigService biliConfigService;
	
	/**  
	
	 * <p>Title: login</p>  
	
	 * <p>Description:用户管理员登录 </p>  
	
	 * @param userEntity
	 * @return  
	
	 */  
	@PostMapping(value = "/login")
	public AjaxEntity login(UserEntity userEntity,HttpServletRequest request) {
		return systemService.loginUser(userEntity,request);
	}
/**  
	
	 * <p>Title: findUserList</p>  
	
	 * <p>Description:分页获取管理员的列表 </p>  
	
	 * @param res
	 * @param request
	 * @return  
	
	 */  
	@PostMapping(value = "/findUserList")
	public AjaxEntity findUserList(RequestEntity res,HttpServletRequest request) {
		return userService.findUserList(res);
	}
	
	/**  
	
	 * <p>Title: addUser</p>  
	
	 * <p>Description: 添加用户</p>  
	
	 * @param userEntity
	 * @param request
	 * @return  
	
	 */  
	@PostMapping(value = "/addUser")
	public AjaxEntity addUser(UserEntity userEntity,HttpServletRequest request) {
		return userService.addUser(userEntity);
	}
	
	@GetMapping(value = "/delUser")
	public AjaxEntity delUser(UserEntity userEntity,HttpServletRequest request) {
		return userService.delUser(userEntity);
	}
	
	@PostMapping(value = "/finddownLoaderList")
	public AjaxEntity finddownLoaderList(RequestEntity res,HttpServletRequest request) {
		return downloaderService.finddownLoaderList(res);
	}
	
	@GetMapping(value = "/deleteDownLoader")
	public AjaxEntity delDownLoader(DownloaderEntity downloaderEntity,HttpServletRequest request) {
		return downloaderService.delDownLoader(downloaderEntity);
	}
	
	@PostMapping(value = "/addDownLoader")
	public AjaxEntity addDownLoader(DownloaderEntity downloaderEntity,HttpServletRequest request) {
		return downloaderService.addDownLoader(downloaderEntity);
	}
	@GetMapping(value = "/getDownLoader")
	public AjaxEntity getDownLoader(DownloaderEntity downloaderEntity,HttpServletRequest request) {
		return downloaderService.getDownLoader(downloaderEntity);
	}
	
	@PostMapping(value = "/saveConfig")
	public AjaxEntity saveConfig(ConfigEntity configEntity,HttpServletRequest request) {
		return configService.saveConfig(configEntity);
	}
	@PostMapping(value = "/findVideoDataList")
	public AjaxEntity findVideoDataList(VideoDataEntity videoDataEntity,HttpServletRequest request) {
		return videoDataService.findPage(videoDataEntity);
	}
	
	@GetMapping(value = "/deleteVideoData")
	public AjaxEntity deleteVideoData(VideoDataEntity downloaderEntity,HttpServletRequest request) {
		return videoDataService.deleteVideoData(downloaderEntity);
	}
	
	@PostMapping(value = "/updateVideoData")
	public AjaxEntity updateVideoData(VideoDataEntity downloaderEntity,HttpServletRequest request) {
		return videoDataService.updateVideoData(downloaderEntity);
	}
	
	
	@PostMapping(value = "/updateBiliConfig")
	public AjaxEntity updateBiliConfig(BiliConfigEntity biliConfigEntity,HttpServletRequest request) {
		return biliConfigService.updateBiliConfig(biliConfigEntity);
	}
} 
