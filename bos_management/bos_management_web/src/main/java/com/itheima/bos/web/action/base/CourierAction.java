package com.itheima.bos.web.action.base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.HTMLDocument.HTMLReader.SpecialAction;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.fasterxml.jackson.core.sym.Name;
import com.itheima.bos.domain.base.Courier;
import com.itheima.bos.domain.base.Standard;
import com.itheima.bos.service.CourierService;
import com.itheima.bos.web.action.CommonAction;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * ClassName:CourierAction <br/>
 * Function: <br/>
 * Date: 2018年3月14日 下午9:43:59 <br/>
 */
@Namespace("/")
@ParentPackage("struts-default")
@Controller
@Scope(value = "prototype")
public class CourierAction extends CommonAction<Courier> {
   
   

    public CourierAction() {
          
        super(Courier.class);  
        // TODO Auto-generated constructor stub  
        
    }

    @Autowired
    private CourierService courierService;

    @Action(value = "courier_save", results = {
            @Result(name = "success", location = "/pages/base/courier.html", type = "redirect")})
    public String save() {

        courierService.save(getModel());

        return SUCCESS;
    }



    @Action(value = "CourierAction_pageQuery")

    public String CourierAction_pageQuery() throws IOException {

        Specification<Courier> specification = new Specification<Courier>() {

            @Override
            public Predicate toPredicate(Root<Courier> root, CriteriaQuery<?> query,
                    CriteriaBuilder cb) {

                List<Predicate> list = new ArrayList<>();

                String courierNum = getModel().getCourierNum();

                String company = getModel().getCompany();

                String type = getModel().getType();

                Standard standard = getModel().getStandard();

                if (StringUtils.isNotEmpty(courierNum)) {

                    Predicate p1 = cb.equal(root.get("courierNum").as(String.class), courierNum);

                    list.add(p1);
                }
                if (StringUtils.isNotEmpty(company)) {

                    Predicate p2 =
                            cb.like(root.get("company").as(String.class), "%" + company + "%");

                    list.add(p2);
                }
                if (StringUtils.isNotEmpty(type)) {

                    Predicate p3 = cb.like(root.get("type").as(String.class), "%" + type + "%");

                    list.add(p3);
                }

                if (standard != null) {

                    String name = standard.getName();

                    if (StringUtils.isNotEmpty(name)) {

                        Join<Object, Object> join = root.join("standard");

                        Predicate p4 = cb.like(join.get("name").as(String.class), "%" + name + "%");

                        list.add(p4);
                    }
                }

                if (list.size() == 0) {

                    return null;
                }

                Predicate[] arr = new Predicate[list.size()];

                list.toArray(arr);

                Predicate predicate = cb.and(arr);

                return predicate;

            }
        };

        PageRequest pageRequest = new PageRequest(page - 1, rows);

        Page<Courier> page = courierService.findAll(specification, pageRequest);
        
        JsonConfig jsonConfig = new JsonConfig();
        
        jsonConfig.setExcludes(new String[] {"fixedAreas", "takeTime"});
        
        page2json(page, jsonConfig);

        return NONE;
    }

    private String ids;

    public void setIds(String ids) {
        this.ids = ids;
    }

    @Action(value = "CourierAction_batchDel", results = {
            @Result(name = "success", location = "/pages/base/courier.html", type = "redirect")})
    public String batchDel() {

        courierService.updateById(ids);

        return SUCCESS;
    }

}
