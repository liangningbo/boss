package com.itheima.bos.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.itheima.bos.domain.base.Standard;

/**  
 * ClassName:StandardService <br/>  
 * Function:  <br/>  
 * Date:     2018年3月14日 下午2:59:55 <br/>       
 */
public interface StandardService {

    void save(Standard standard);

    Page<Standard> findAll(PageRequest pageRequest);

   

}
  
