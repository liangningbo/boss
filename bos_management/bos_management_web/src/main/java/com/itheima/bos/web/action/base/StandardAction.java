package com.itheima.bos.web.action.base;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.formula.functions.T;
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
import com.itheima.bos.web.action.CommonAction;
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
@Scope(value = "prototype")
public class StandardAction extends CommonAction<Standard> {

    public StandardAction() {

        super(Standard.class);
        // TODO Auto-generated constructor stub
    }

    @Autowired
    private StandardService standardService;

    @Action(value = "standardAction_save", results = {
            @Result(name = "success", location = "/pages/base/standard.html", type = "redirect")})

    public String save() {

        standardService.save(getModel());

        return SUCCESS;
    }

    @Action(value = "StandardAction_pageQuery")
    public String pageQuery() throws IOException {

        PageRequest pageRequest = new PageRequest(page - 1, rows);

        Page<Standard> page = standardService.findAll(pageRequest);

        page2json(page, null);

        return NONE;
    }

    @Action(value = "standard_findAll")
    public String standard_findAll() throws IOException {

        Page<Standard> page2 = standardService.findAll(null);

        List<Standard> list = page2.getContent();

        list2json(list, null);

        return NONE;
    }
}
