/***********************************************************************
 *
 * @file         SecurityWebInitializer.java
 *
 * @copyright    Copyright: 2014-2016 Usee Co. Ltd.
 * @author       JetQin 
 * @create-time  2014年9月27日
 *
 *
 ***********************************************************************/
package com.mweb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * @author jet
 *
 */
@Order(2)
public class SecurityWebInitializer extends AbstractSecurityWebApplicationInitializer
{

}
