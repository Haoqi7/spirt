package com.flower.spirit.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.flower.spirit.entity.ConfigEntity;



@Repository
@Transactional
public interface ConfigDao extends PagingAndSortingRepository<ConfigEntity, Integer>, JpaSpecificationExecutor<ConfigEntity>{

	
	public List<ConfigEntity> findAll();

	

}
