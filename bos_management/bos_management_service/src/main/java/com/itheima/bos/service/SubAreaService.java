package com.itheima.bos.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.itheima.bos.domain.base.SubArea;

/**  
 * ClassName:SubAreaService <br/>  
 * Function:  <br/>  
 * Date:     2018年3月18日 下午3:44:24 <br/>       
 */

public interface SubAreaService {

    Page<SubArea> findAll(PageRequest pageRequest);

  

    void save(SubArea subArea);

}
  
