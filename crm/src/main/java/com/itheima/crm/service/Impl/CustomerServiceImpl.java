package com.itheima.crm.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.crm.dao.CustomerRepository;
import com.itheima.crm.domain.Customer;
import com.itheima.crm.service.CustomerService;

/**  
 * ClassName:CustomerServiceImpl <br/>  
 * Function:  <br/>  
 * Date:     2018年3月21日 下午4:17:45 <br/>       
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public List<Customer> findAll() {
          
        // TODO Auto-generated method stub  
        return customerRepository.findAll();
    }
  
    @Override
    public List<Customer> findCustomersUnAssociated() {
          
        // TODO Auto-generated method stub  
        return customerRepository.findByFixedAreaIdIsNull();
    }
  

    @Override
    public List<Customer> findCustomersAssociated2FixedArea(String fixedAreaId) {
          
        // TODO Auto-generated method stub  
        return customerRepository.findByFixedAreaId(fixedAreaId);
    }
  

}
  
