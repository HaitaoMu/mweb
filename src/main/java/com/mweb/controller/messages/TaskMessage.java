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

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.mweb.model.ProgressRateResult;

/**
 * @author jet
 *
 */
@Controller
public class TaskMessage
{
	@Autowired
	private SimpMessagingTemplate template;
	
	private AtomicInteger progressRate = new AtomicInteger();
	
	private ProgressRateResult result ;

	@MessageMapping("/tasknotification")
	@Scheduled(fixedDelay = 3000)
	public void sendMessage()
	{
		ProgressRateResult rateResult = new ProgressRateResult();
		int currentValue = getProgressRate();
		rateResult.setCurrentValue(currentValue);
		rateResult.setMaxValue(100);
		rateResult.setMinValue(0);
		rateResult.setTaskId("SAP IMPORT");
		rateResult.setMesssage(getProgressMessage(currentValue));
		template.convertAndSend("/topic/tasknotification", rateResult);
	}
	
	public String getProgressMessage(int process)
	{
//		<li>
//        <a href="#">
//            <div>
//                <p>
//                    <strong>Task 1</strong>
//                    <span class="pull-right text-muted">40% Complete</span>
//                </p>
//                <div class="progress progress-striped active">
//                    <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: 80%">
//                        <span class="sr-only">40% Complete (success)</span>
//                    </div>
//                </div>
//            </div>
//        </a>
//    </li>
//    <li class="divider"></li>
//    <li>
//        <a class="text-center" href="#">
//            <strong>See All Tasks</strong>
//            <i class="fa fa-angle-right"></i>
//        </a>
//    </li>
		StringBuilder builder = new StringBuilder();
		builder.append("");
		return String.format("%d%%( complete success )",process);
	}
	
	private String getProgressItem()
	{
//		<li>
//      <a href="#">
//          <div>
//              <p>
//                  <strong>Task 1</strong>
//                  <span class="pull-right text-muted">40% Complete</span>
//              </p>
//              <div class="progress progress-striped active">
//                  <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: 80%">
//                      <span class="sr-only">40% Complete (success)</span>
//                  </div>
//              </div>
//          </div>
//      </a>
//  </li>
		StringBuffer builder = new StringBuffer();
		return  builder.toString();
	}
	
	public int getProgressRate()
	{
		return progressRate.getAndIncrement();
	}

	public void setProgressRate(AtomicInteger progressRate)
	{
		this.progressRate = progressRate;
	}
	
	
}
