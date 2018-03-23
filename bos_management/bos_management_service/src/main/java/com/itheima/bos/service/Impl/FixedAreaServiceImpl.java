package com.itheima.bos.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.base.FixedAreaRepository;
import com.itheima.bos.domain.base.FixedArea;
import com.itheima.bos.service.FixedAreaService;

/**  
 * ClassName:FixedAreaServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2018年3月21日 下午7:53:20 <br/>       
 */
@Service
@Transactional
public class FixedAreaServiceImpl implements FixedAreaService {
    @Autowired
    private FixedAreaRepository fixedAreaRepository;
    @Override
    public Page<FixedArea> findall(PageRequest pageRequest) {
          
        // TODO Auto-generated method stub  
        return fixedAreaRepository.findAll(pageRequest);
    }
    @Override
    public void save(FixedArea fixedArea) {
          
        fixedAreaRepository.save(fixedArea);  
        
    }


}
  
