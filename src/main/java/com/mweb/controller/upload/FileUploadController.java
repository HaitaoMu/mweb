package com.mweb.controller.upload;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mweb.controller.ajax.AjaxUtils;

@Controller
public class FileUploadController
{
	private static final String TMP_DIR = "/";
	@ModelAttribute
	public void ajaxAttribute(WebRequest request, Model model)
	{
		model.addAttribute("ajaxRequest", AjaxUtils.isAjaxRequest(request));
	}
	
	@RequestMapping(value = "/fileupload", method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView uploadFile(@RequestParam(value = "file")
	MultipartFile file, Model model)
	{

		if (!file.isEmpty())
		{
			FileOutputStream fos = null;
			try
			{
				byte[] bytes = file.getBytes();
				fos = new FileOutputStream(TMP_DIR + file.getOriginalFilename());
				fos.write(bytes);
			} catch (IOException e)
			{
				e.printStackTrace();
			} finally
			{
				try
				{
					fos.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}

		}
		Map map = new HashMap();
		map.put("messages", "Welcome to here");
		return new ModelAndView("showMessage", map);

	}

}
