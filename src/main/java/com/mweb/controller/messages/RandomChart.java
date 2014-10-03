/***********************************************************************
 *
 * @file         RandomChart.java
 *
 * @copyright    Copyright: 2014-2016 Usee Co. Ltd.
 * @author       JetQin 
 * @create-time  2014年10月3日
 *
 *
 ***********************************************************************/
package com.mweb.controller.messages;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author jet
 *
 */
@Controller
public class RandomChart
{
	@Autowired
	private SimpMessagingTemplate template;

	@RequestMapping("/chart")
	public String randomChart()
	{

		return "data";
	}

	@MessageMapping("/random")
	@Scheduled(fixedDelay = 1000)
	public void sendMessage()
	{
		System.out.println("[send message]");
		template.convertAndSend("/topic/data", new Random().nextInt(100));
	}

	// @RequestMapping(value = "/random", method = {RequestMethod.POST,RequestMethod.GET})
	// public void greet(String greeting)
	// {
	// String text = "[" + System.currentTimeMillis() + "]:" + greeting;
	// System.out.println("[send message]");
	// this.template.convertAndSend("/topic/data", text);
	// }
}
