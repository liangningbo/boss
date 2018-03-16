package com.itheima.bos.dao.base;

import java.util.List;

import org.apache.cxf.common.jaxb.JAXBUtils.JPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.domain.base.Standard;



/**  
 * ClassName:StandardRepository <br/>  
 * Function:  <br/>  
 * Date:     2018年3月13日 下午5:02:16 <br/>       
 */
public interface StandardRepository extends JpaRepository<Standard, Long>{

  
   
  List<Standard>  findByName(String name);
  List<Standard>  findByNameLike(String name);
  List<Standard>  findByNameAndMaxWeight(String name,Integer MaxWeight);
  @Query(" from Standard where name = ? and maxWeight = ?")
  List<Standard>  findByNameAndMaxWeightall(String name,Integer MaxWeight);
  
  @Query("update Standard set minWeight = ? where name = ?")
  @Modifying
  @Transactional
  void updateMinWeightByName(int minWeight, String name);
  
  @Query(value="delete from T_STANDARD where C_NAME = ?",nativeQuery=true)
  @Modifying
  @Transactional
  void deleteByName( String name);
}
  
