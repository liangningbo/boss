package com.itheima.bos.dao.base.test;

import com.itheima.bos.utils.PinYin4jUtils;

/**  
 * ClassName:PinyinTest <br/>  
 * Function:  <br/>  
 * Date:     2018年3月15日 下午9:46:04 <br/>       
 */
public class PinyinTest {

    public static void main(String[] args) {

        String province ="广东省";
        
        String city ="深圳市";
        
        String district = "宝安区";
        
        province = province.substring(0, province.length()-1);
        
        city = city.substring(0, city.length()-1);
        
        district = district.substring(0, district.length()-1);
        
        String pinyin = PinYin4jUtils.hanziToPinyin(city);
        
       
        
        System.out.println(pinyin);
    }

}
  
