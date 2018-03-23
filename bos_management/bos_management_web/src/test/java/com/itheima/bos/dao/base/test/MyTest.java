package com.itheima.bos.dao.base.test;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.config.CustomEditorConfigurer;

import com.itheima.bos.domain.base.Customer;

/**  
 * ClassName:MyTest <br/>  
 * Function:  <br/>  
 * Date:     2018年3月21日 下午5:25:28 <br/>       
 */
public class MyTest {

    public static void main(String[] args) {

       java.util.Collection<? extends Customer> collection = WebClient.create("http://localhost:8080/crm/service/customerService/findAll")
       .type(MediaType.APPLICATION_JSON) 
       .accept(MediaType.APPLICATION_JSON)
      .getCollection(Customer.class);
       
      for(Customer customer :collection) {
          System.out.println(customer);
      }
    }

}
  
