package com.itheima.bos.service.Impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.itheima.bos.dao.base.CourierRepository;
import com.itheima.bos.domain.base.Courier;
import com.itheima.bos.service.CourierService;

/**  
 * ClassName:CourierServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2018年3月14日 下午9:53:16 <br/>       
 */
@Service
@Transactional
public class CourierServiceImpl implements CourierService {
    @Autowired
    private CourierRepository courierRepository;
    @Override
    public void save(Courier courier) {
          
        courierRepository.save(courier);
       
    }
    @Override
    public Page<Courier> findAll(PageRequest pageRequest) {
          
        // TODO Auto-generated method stub  
        return courierRepository.findAll(pageRequest);
    }
    @Override
    public void updateById(String ids) {
          
        if(StringUtils.isNotEmpty(ids)){
            
            String[] split = ids.split(",");
            
            for(String id :split) {
                
                courierRepository.updateDelTagById(Long.parseLong(id));
            }
        }
        
    }
    @Override
    public Page<Courier> findAll(Specification<Courier> specification, PageRequest pageRequest) {
          
        // TODO Auto-generated method stub  
        return courierRepository.findAll(specification, pageRequest);
    }

}
  
