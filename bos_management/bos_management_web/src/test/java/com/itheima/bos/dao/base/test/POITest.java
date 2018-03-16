package com.itheima.bos.dao.base.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;



/**
 * ClassName:POITest <br/>
 * Function: <br/>
 * Date: 2018年3月15日 下午9:26:20 <br/>
 */
public class POITest {

    public static void main(String[] args) throws Exception {

        HSSFWorkbook workbook = new HSSFWorkbook

        (new FileInputStream(
                "C:\\Program Files\\feiq\\Recv Files\\BOS课前资料\\Day04\\资料\\03_区域测试数据\\区域导入测试数据.xls"));

        HSSFSheet sheet = workbook.getSheetAt(0);
        
        for(Row row : sheet) {
            
            for(Cell cell : row) {
                
                int rowNum = row.getRowNum();
                
                if(rowNum == 0) {
                    continue;
                }
                
                String cellValue = cell.getStringCellValue();
                
                System.out.print(cellValue+"\t");
            }
            System.out.println();
        }

        workbook.close();
    }

}
