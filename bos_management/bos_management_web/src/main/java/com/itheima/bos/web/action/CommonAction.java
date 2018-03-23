package com.itheima.bos.web.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.formula.functions.T;
import org.apache.struts2.ServletActionContext;
import org.springframework.data.domain.Page;

import com.itheima.bos.domain.base.Standard;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * ClassName:commonAction <br/>
 * Function: <br/>
 * Date: 2018年3月16日 下午4:34:40 <br/>
 */
public class CommonAction<T> extends ActionSupport implements ModelDriven<T> {

    private T model;
    private Class<T> clazz;

    public CommonAction(Class<T> clazz) {

        this.clazz = clazz;
    }

    @Override
    public T getModel() {

        try {
            if (model == null) {

                model = clazz.newInstance();
            }

        } catch (Exception e) {

            e.printStackTrace();

        }
        return model;
    }

    protected int rows;
    protected int page;

    public void setRows(int rows) {

        this.rows = rows;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void page2json(Page<T> page, JsonConfig jsonConfig) {
        try {
            long totalElements = page.getTotalElements();

            List<T> list = page.getContent();

            Map<String, Object> map = new HashMap<>();

            map.put("total", totalElements);

            map.put("rows", list);

            String json;

            if (jsonConfig == null) {

                json = JSONObject.fromObject(map).toString();

            } else {

                json = JSONObject.fromObject(map, jsonConfig).toString();

            }

            HttpServletResponse response = ServletActionContext.getResponse();

            response.setContentType("application/json;charset=utf-8");

            response.getWriter().write(json);

        } catch (IOException e) {

            // TODO Auto-generated catch block
            e.printStackTrace();

        }
    }

    public void list2json(List list, JsonConfig jsonConfig) {
        try {
            
            String json;
            
            if(jsonConfig!= null ) {
                
                 json = JSONArray.fromObject(list,jsonConfig).toString();
            }else {
                
                json = JSONArray.fromObject(list).toString();
            }

            HttpServletResponse response = ServletActionContext.getResponse();

            response.setContentType("application/json;charset=utf-8");

            response.getWriter().write(json);
            
        } catch (IOException e) {

            // TODO Auto-generated catch block
            e.printStackTrace();

        }

    }
}
