/***********************************************************************
 *
 * @file         EncoderUtil.java
 *
 * @copyright    Copyright: 2014-2016 Usee Co. Ltd.
 * @author       JetQin 
 * @create-time  2014年10月1日
 *
 *
 ***********************************************************************/
package com.mweb.common.util;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

/**
 * @author jet
 *
 */
public class EncoderUtil
{
	public static final ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder();
}
