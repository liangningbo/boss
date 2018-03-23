package com.itheima.crm.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.itheima.crm.domain.Customer;

/**  
 * ClassName:CustomerService <br/>  
 * Function:  <br/>  
 * Date:     2018年3月21日 下午4:16:50 <br/>       
 */

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface CustomerService {
    @GET
    @Path("/findAll")
    List<Customer>findAll();
    
    @GET
    @Path("/")
    List<Customer>findCustomersUnAssociated();
    
    @GET
    @Path("/")
    List<Customer>findCustomersAssociated2FixedArea(@QueryParam("fixedAreaId")String fixedAreaId);
    
}
  
