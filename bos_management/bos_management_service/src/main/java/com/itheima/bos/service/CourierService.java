package com.itheima.bos.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import com.itheima.bos.domain.base.Courier;

/**  
 * ClassName:CourierService <br/>  
 * Function:  <br/>  
 * Date:     2018年3月14日 下午9:51:47 <br/>       
 */
public interface CourierService {

    void save(Courier courier);

    Page<Courier> findAll(PageRequest pageRequest);

    void updateById(String ids);

    Page<Courier> findAll(Specification<Courier> specification, PageRequest pageRequest);

}
  
