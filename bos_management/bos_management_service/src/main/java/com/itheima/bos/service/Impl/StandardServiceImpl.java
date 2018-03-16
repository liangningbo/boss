package com.itheima.bos.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.base.StandardRepository;
import com.itheima.bos.domain.base.Standard;
import com.itheima.bos.service.StandardService;

/**  
 * ClassName:StandardServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2018年3月14日 下午3:24:17 <br/>       
 */
@Service
@Transactional
public class StandardServiceImpl implements StandardService {
    @Autowired
    private StandardRepository standardRepository;
    @Override
    public void save(Standard standard) {
          
       standardRepository.save(standard)  ;
        
    }
    @Override
    public Page<Standard> findAll(PageRequest pageRequest) {
          
        // TODO Auto-generated method stub  
        return standardRepository.findAll(pageRequest);
    }
}
  
