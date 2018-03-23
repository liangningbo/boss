package com.itheima.crm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itheima.crm.domain.Customer;

/**  
 * ClassName:CustomerRepository <br/>  
 * Function:  <br/>  
 * Date:     2018年3月21日 下午4:11:28 <br/>       
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    
    List<Customer> findByFixedAreaIdIsNull();

    

    List<Customer> findByFixedAreaId(String fixedAreaId);

}
  
