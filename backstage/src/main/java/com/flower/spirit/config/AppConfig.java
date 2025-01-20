package com.flower.spirit.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.flower.spirit.entity.BiliConfigEntity;
import com.flower.spirit.entity.ConfigEntity;
import com.flower.spirit.service.BiliConfigService;
import com.flower.spirit.service.ConfigService;
import com.flower.spirit.service.DownloaderService;

/**
 * @author flower
 *程序初始化入口 加载常量信息到应用缓存
 */
@Configuration
public class AppConfig {
	
	@Autowired
	private DownloaderService downloaderService;
	
	@Autowired
	private ConfigService configService;
	

	@Autowired
	private BiliConfigService biliConfigService;
	@PostConstruct
	public void init() {
		downloaderService.renovate();
		ConfigEntity data = configService.getData();
		Global.apptoken =data.getApptoken();
		BiliConfigEntity bili = biliConfigService.getData();
		Global.bilicookies =bili.getBilicookies();
	}

}
