package com.itheima.bos.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.itheima.bos.domain.base.FixedArea;

/**  
 * ClassName:FixedAreaService <br/>  
 * Function:  <br/>  
 * Date:     2018年3月21日 下午7:50:55 <br/>       
 */
public interface FixedAreaService {

    Page<FixedArea> findall(PageRequest pageRequest);

    void save(FixedArea fixedArea);

}
  
