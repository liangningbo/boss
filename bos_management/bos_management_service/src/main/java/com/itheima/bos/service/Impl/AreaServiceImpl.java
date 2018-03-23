package com.itheima.bos.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.base.AreaRepository;
import com.itheima.bos.domain.base.Area;
import com.itheima.bos.service.AreaService;

/**  
 * ClassName:AreaServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2018年3月15日 下午10:41:24 <br/>       
 */
@Service
@Transactional
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaRepository areaRepository;
    
    @Override
    public void save(List<Area> list) {
          
        areaRepository.save(list) ;
        
    }

    @Override
    public Page<Area> findAll(PageRequest pageRequest) {
          
        // TODO Auto-generated method stub  
        return areaRepository.findAll(pageRequest);
    }

    @Override
    public List<Area> findByQ(String q) {
          
        q = "%"+q.toUpperCase()+"%";
        // TODO Auto-generated method stub  
        return areaRepository.findQ(q);
    }

}
  
