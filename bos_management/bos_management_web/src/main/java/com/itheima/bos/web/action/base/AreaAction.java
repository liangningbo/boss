package com.itheima.bos.web.action.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.HTMLDocument.HTMLReader.SpecialAction;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
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

import com.itheima.bos.domain.base.Area;
import com.itheima.bos.service.AreaService;
import com.itheima.bos.utils.PinYin4jUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**  
 * ClassName:AreaAction <br/>  
 * Function:  <br/>  
 * Date:     2018年3月15日 下午10:22:21 <br/>       
 */

@Namespace("/")
@ParentPackage("struts-default")
@Controller
@Scope("prototype")
public class AreaAction extends ActionSupport implements ModelDriven<Area> {
    private Area model  = new Area();
    
    @Override
    public Area getModel() {
          
        // TODO Auto-generated method stub  
        return model;
    }
    @Autowired
    private AreaService areaService ;
    
    private File file;
    
    public void setFile(File file) {
        this.file = file;
    }

    @Action(value="areaAction_inportXls", results= { 
            @Result(name="success",location="/pages/base/area.html",type="redirect")
    })
    
    public String inportXls() {
        try {
           
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream (file));
              
        HSSFSheet sheet = workbook.getSheetAt(0);
        List<Area>list = new ArrayList<>();
        for(Row row : sheet) {
            
            // 跳过第一行
            if (row.getRowNum() == 0) {
                continue;
            }
            
            String province = row.getCell(1).getStringCellValue();
            
            String city = row.getCell(2).getStringCellValue();
            
            String district = row.getCell(3).getStringCellValue();
            
            String postcode = row.getCell(4).getStringCellValue();
            
            province = province.substring(0,province.length()-1);
            
            city = city.substring(0,city.length()-1);
            
            district = district.substring(0,district.length()-1);
            
            postcode = postcode.substring(0,postcode.length()-1);
            
            String citycode = PinYin4jUtils.hanziToPinyin(city, "");
            
            String[] headByString = PinYin4jUtils.getHeadByString(province+city+district);
            
            String shortcode = PinYin4jUtils.stringArrayToString(headByString);
            
            Area area = new Area();
            
            area.setProvince(province);
            
            area.setCity(city);
            
            area.setDistrict(district);
            
            area.setPostcode(postcode);
            
            area.setCitycode(citycode);
            
            area.setShortcode(shortcode);
            
            list.add(area);
            
        }
        
        areaService.save(list);
       
            workbook.close();
        } catch (IOException e) {
              
            // TODO Auto-generated catch block  
            e.printStackTrace();  
            
        }
        
        return SUCCESS;
    }
    
    private int page;
    
    private int rows;
    
  public void setPage(int page) {
        this.page = page;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

@Action ("AreaAction_pageQuery")
   public String pageQuery() throws IOException {
        
  PageRequest pageRequest = new PageRequest(page-1,rows);
  
  Page<Area> page = areaService.findAll(pageRequest);
    
    long totalElements = page.getTotalElements();
    
    List<Area> list = page.getContent();
    
    Map<String, Object>map = new HashMap<>();
    
    map.put("total", totalElements);
    
    map.put("rows", list);
    
    JsonConfig jsonConfig = new JsonConfig();
    
    jsonConfig.setExcludes(new String[] {"subareas"});
    
     String json = JSONObject.fromObject(map,jsonConfig).toString();
     
     HttpServletResponse response = ServletActionContext.getResponse();
     
     response.setContentType("application/action;charset=UTF-8");
     
     response.getWriter().write(json);
    
        return NONE;
    }
}

  
