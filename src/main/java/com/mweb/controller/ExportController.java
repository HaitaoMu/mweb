package com.mweb.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mweb.controller.export.UserExcelPage;
import com.mweb.controller.export.UserPdfPage;

@Controller
public class ExportController 
{
	@RequestMapping(value = "/exportExcel")
	public ModelAndView exportExcel(Model model)
	{
		List<String> list = new ArrayList<String>();   
        list.add("test1");   
        list.add("test2");   
        Map<String,List> data = new HashMap<String,List>();
        data.put("words", list);
        
        UserExcelPage viewExcel = new UserExcelPage();   
        return new ModelAndView(viewExcel,data); 
	}
	
	@RequestMapping(value = "/exportPdf")
	public ModelAndView exportPdf(Model model)
	{
		List list = new ArrayList();   
        list.add("test1");   
        list.add("test2");   
        Map data = new HashMap();
        data.put("words", list);
        
        UserPdfPage viewPdf = new UserPdfPage();   
        return new ModelAndView(viewPdf,data); 
	}
}
