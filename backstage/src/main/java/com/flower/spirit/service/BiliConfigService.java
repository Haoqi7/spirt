package com.flower.spirit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flower.spirit.common.AjaxEntity;
import com.flower.spirit.config.Global;
import com.flower.spirit.dao.BiliConfigDao;
import com.flower.spirit.entity.BiliConfigEntity;
import com.flower.spirit.entity.VideoDataEntity;

@Service
public class BiliConfigService {
	
	@Autowired
	private BiliConfigDao BiliConfigDao;
	
	public BiliConfigEntity getData() {
		List<BiliConfigEntity> findAll = BiliConfigDao.findAll();
		if(findAll.size() == 0) {
			BiliConfigEntity biliConfigEntity = new BiliConfigEntity();
			BiliConfigDao.save(biliConfigEntity);
			return biliConfigEntity;
		}
		return findAll.get(0);
	}

	/**
	 * @param entity
	 * @return
	 */
	public AjaxEntity updateBiliConfig(BiliConfigEntity entity) {
		BiliConfigDao.save(entity);
		Global.bilicookies = entity.getBilicookies();
		return new AjaxEntity(Global.ajax_success, "操作成功", entity);
	}

}
