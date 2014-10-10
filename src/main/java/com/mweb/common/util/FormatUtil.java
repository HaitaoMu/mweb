/***********************************************************************
 *
 * @file         FormatUtil.java
 *
 * @copyright    Copyright: 2014-2016 Usee Co. Ltd.
 * @author       JetQin 
 * @create-time  2014年10月9日
 *
 *
 ***********************************************************************/
package com.mweb.common.util;

import java.text.NumberFormat;

/**
 * @author jet
 *
 */
public class FormatUtil
{
	public static String formatDouble(double value)
	{
		NumberFormat format = NumberFormat.getInstance();
		format.setMinimumFractionDigits(0);
		format.setMaximumFractionDigits(2);
		return format.format(value);
	}
}
