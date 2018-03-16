package com.itheima.bos.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.itheima.bos.domain.base.Area;

/**  
 * ClassName:AreaService <br/>  
 * Function:  <br/>  
 * Date:     2018年3月15日 下午10:30:58 <br/>       
 */
public interface AreaService {

    

    void save(List<Area> list);

    Page<Area> findAll(PageRequest pageRequest);

}
  
