package com.itheima.bos.web.action.base;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.base.Customer;
import com.itheima.bos.domain.base.FixedArea;
import com.itheima.bos.service.FixedAreaService;
import com.itheima.bos.web.action.CommonAction;

import net.sf.json.JsonConfig;

/**
 * ClassName:FixedAreaAction <br/>
 * Function: <br/>
 * Date: 2018年3月21日 下午7:55:14 <br/>
 */
@Namespace("/")
@ParentPackage(value = "struts-default")
@Controller
//@Scope("prototype")
public class FixedAreaAction extends CommonAction<FixedArea> {

    public FixedAreaAction() {

        super(FixedArea.class);
        // TODO Auto-generated constructor stub

    }

    @Autowired
    private FixedAreaService fixedAreaService;

    @Action("fixedAreaAction_pageQuery")
    public String pageQuery() {

        PageRequest pageRequest = new PageRequest(page - 1, rows);

        Page<FixedArea> page = fixedAreaService.findall(pageRequest);
        
        JsonConfig jsonConfig = new JsonConfig();
        
        jsonConfig.setExcludes(new String[] {"subareas", "couriers"});

        page2json(page, jsonConfig);

        return NONE;
    }

    @Action(value = "fixedAreaAction_save", results = {
            @Result(name = "success", location = "/pages/base/fixed_area.html", type = "redirect")})
    public String save() {

        fixedAreaService.save(getModel());

        return SUCCESS;
    }
    
    // 向CRM系统发起请求,查询未关联定区的客户
    @Action(value = "fixedAreaAction_findUnAssociatedCustomers")
    public String findUnAssociatedCustomers()  {

        List<Customer> list = (List<Customer>) WebClient.create(
                
                "http://localhost:8080/crm/webService/customerService/findCustomersUnAssociated")
                .type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .getCollection(Customer.class);

        list2json(list, null);
        return NONE;
    }
    
    // 向CRM系统发起请求,查询已关联指定定区的客户
    @Action(value = "fixedAreaAction_findAssociatedCustomers")
    public String findAssociatedCustomers()  {

        List<Customer> list = (List<Customer>) WebClient.create(
                "http://localhost:8180/crm/webService/customerService/findCustomersAssociated2FixedArea")
                .query("fixedAreaId", getModel().getId())
                .type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .getCollection(Customer.class);

        list2json(list, null);
        return NONE;
    }
    
    // 使用属性驱动获取要关联到指定定区的客户ID
    private Long[] customerIds;

    public void setCustomerIds(Long[] customerIds) {
        this.customerIds = customerIds;
    }

    // 向CRM系统发起请求,关联客户
    @Action(value = "fixedAreaAction_assignCustomers2FixedArea",
            results = {@Result(name = "success",
                    location = "/pages/base/fixed_area.html",
                    type = "redirect")})
    public String assignCustomers2FixedArea()  {
        WebClient.create(
                "http://localhost:8180/crm/webService/customerService/assignCustomers2FixedArea")
                .query("fixedAreaId", getModel().getId())
                .query("customerIds",customerIds)
                .type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .put(null);
        return SUCCESS;
    }
}
