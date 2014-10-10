/***********************************************************************
 *
 * @file         TaskMessage.java
 *
 * @copyright    Copyright: 2014-2016 Usee Co. Ltd.
 * @author       JetQin 
 * @create-time  2014年10月7日
 *
 *
 ***********************************************************************/
package com.mweb.controller.messages;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.mweb.model.ProgressRateResult;
import com.mweb.service.WatchService;

/**
 * @author jet
 *
 */
@Controller
public class TaskMessage
{
	@Autowired
	private SimpMessagingTemplate template;
	
	private ProgressRateResult rateResult;
	
	private static AtomicInteger progressRate = new AtomicInteger();
	

	@MessageMapping("/tasknotification")
	@Scheduled(fixedDelay = 500)
	public synchronized void sendMessage()
	{
		List<ProgressRateResult> results = WatchService.getTaskList();
		for (ProgressRateResult progressRateResult : results)
		{
			progressRateResult.setMesssage(getProgressMessage(progressRateResult));
			template.convertAndSend("/topic/tasknotification", progressRateResult);
		}
	}
	
	public synchronized String getProgressMessage(ProgressRateResult result)
	{
		StringBuilder builder = new StringBuilder();
		builder.append(getProgressItem(rateResult));
		builder.append(getDetails());
		return builder.toString();
	}
	
	public synchronized String getDetails()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("<li>");
		builder.append("<a class='text-center' href='#'>");
		builder.append(" <strong>See All Tasks</strong>");
		builder.append("  <i class='fa fa-angle-right'></i>");
		builder.append("</a>");
		builder.append("</li>");
		return builder.toString();
	}
	
	private synchronized String getProgressItem( ProgressRateResult result)
	{
		String message = String.format("%d%% Complete", result.getCurrentValue());
		StringBuilder builder = new StringBuilder();
		builder.append("<li>");
		builder.append(" <a href='#'>");
		builder.append(" <div> ");
		builder.append(" <p> ");
		builder.append(" <strong>"+result.getTaskId()+"</strong>");
		builder.append(" <span class='pull-right text-muted'>"+message+"</span>");
		builder.append(" <div class='progress progress-striped active'>");
		builder.append(" <div class='progress-bar progress-bar-success' role='progressbar' aria-valuenow='"+result.getCurrentValue()+"' aria-valuemin='0' aria-valuemax='"+result.getMaxValue()+"' style='width:"+result.getCurrentValue()+"%'>");
		builder.append(" <span class='sr-only'>"+message+"</span>");
		builder.append(" </div>");
		builder.append(" </div>");
		builder.append(" </div>");
		builder.append(" </a>");
		builder.append(" </li>");
		builder.append(" <li class='divider'></li>");
		return builder.toString();
	}
	
	public int getProgressRate()
	{
		return progressRate.getAndIncrement();
	}

	
	
}
