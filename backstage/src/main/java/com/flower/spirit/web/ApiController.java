package com.flower.spirit.web;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flower.spirit.common.AjaxEntity;
import com.flower.spirit.config.Global;
import com.flower.spirit.service.AnalysisService;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	private AnalysisService analysisService;
	
	private ExecutorService exec = Executors.newFixedThreadPool(1);
	
	
	@RequestMapping("/processingVideos")
	public AjaxEntity processingVideos(String token,String video) throws Exception {
//		 analysisService.processingVideos(token,video);
		exec.execute(() -> {
			try {
				 analysisService.processingVideos(token,video);
			}catch (Exception e) {
				
			}
		});
		return new AjaxEntity(Global.ajax_success, "已提交,等待系统处理", "");
	
	}

}
