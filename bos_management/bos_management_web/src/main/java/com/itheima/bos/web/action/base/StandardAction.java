package com.itheima.bos.web.action.base;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.base.Standard;
import com.itheima.bos.service.StandardService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * ClassName:StandardAction <br/>
 * Function: <br/>
 * Date: 2018年3月14日 下午2:53:01 <br/>
 */

@Namespace("/")
@ParentPackage("struts-default")
@Controller
@Scope("prototype")
public class StandardAction extends ActionSupport implements ModelDriven<Standard> {
    private Standard model = new Standard();

    @Override
    public Standard getModel() {

        // TODO Auto-generated method stub
        return model;
    }

    @Autowired
    private StandardService standardService;

    @Action(value = "standardAction_save", results = {
            @Result(name = "success", location = "/pages/base/standard.html", type = "redirect")})

    public String save() {
        
        standardService.save(model);
        return SUCCESS;
    }

    private int rows;
    private int page;

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Action(value = "StandardAction_pageQuery")
    public String StandardAction_pageQuery() throws IOException {
        PageRequest pageRequest = new PageRequest(page - 1, rows);
        
        Page<Standard> page = standardService.findAll(pageRequest);
        
        long totalElements = page.getTotalElements();
        
        List<Standard> list = page.getContent();
        
        Map<String, Object> map = new HashMap<>();
        
        map.put("total", totalElements);
        
        map.put("rows", list);
        
        String json = JSONObject.fromObject(map).toString();
        
        HttpServletResponse response = ServletActionContext.getResponse();
        
        response.setContentType("application/json;charset=utf-8");
        
        response.getWriter().write(json);
        
        return NONE;
    }
    
    @Action(value = "standard_findAll")
    public String standard_findAll() throws IOException {
        
        Page<Standard> page2 = standardService.findAll(null);
        
        long elements = page2.getTotalElements();
        
        List<Standard> list = page2.getContent();
        
      String json = JSONArray.fromObject(list).toString();
      
      HttpServletResponse response = ServletActionContext.getResponse();
      
      response.setContentType("application/json;charset=utf-8");
      
      response.getWriter().write(json);
      
        return NONE;
    }
}
