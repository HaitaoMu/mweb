package com.mweb.controller.export;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class UserExcelPage extends AbstractExcelView
{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		HSSFSheet sheet = workbook.createSheet("Spring");
		
		 // Create a row and put some cells in it. Rows are 0 based.
        HSSFRow row = sheet.createRow(1);
        // Create a cell and put a value in it.

		List words = (List) model.get("words");
		for (int i = 0; i < words.size(); i++)
		{
	        row.createCell(i).setCellValue(words.get(i).toString());
		}
	}

}
