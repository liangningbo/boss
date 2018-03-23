package com.itheima.bos.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.base.SubAreaRepository;
import com.itheima.bos.domain.base.SubArea;

/**  
 * ClassName:SubAreaService <br/>  
 * Function:  <br/>  
 * Date:     2018年3月18日 下午3:48:34 <br/>       
 */
@Service
@Transactional
public class SubAreaService implements com.itheima.bos.service.SubAreaService {
@Autowired    
private SubAreaRepository subAreaRepository;
    @Override
    public Page<SubArea> findAll(PageRequest pageRequest) {

        return subAreaRepository.findAll(pageRequest);
    }
    @Override
    public void save(SubArea subArea) {
          
        subAreaRepository.saveAndFlush(subArea) ;
        
    }

}
  
