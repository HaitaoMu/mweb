/***********************************************************************
 *
 * 
 *
 * @file        Constants.java
 *
 * @copyright   Copyright: 2014-2016 Usee Co. Ltd.
 * @creator     JetQin <br/>
 * @create-time 2014年9月13日
 *
 *
 ***********************************************************************/
package com.mweb.common.constats;

/**
 * @author jet
 *
 */
public class Constants
{
	public static final String SOURCE_DB = "SOURCE_DATABASE";

	public static final String DESTINATION_DB = "DESTINATION_DATABASE";

	public static final String PACKAGE_NAME = "com.mweb";

	public static final String SCAN_ENTITY_PACKAGE_NAME = "com.mweb.model";

	public static final String SCAN_CONFIG_SECURITY_PACKAGE_NAME = "com.mweb.config.security";

	public static final String SCAN_CONFIG_SOCKET_PACKAGE_NAME = "com.mweb.config.socket";
	
	public static final String SCAN_CONFIG_WEB_PACKAGE_NAME = "com.mweb.config.web";

	public static final String SCAN_CONTROLLER_PACKAGE_NAME = "com.mweb.controller";

	public static final String SCAN_BATCH_PACKAGE_NAME = "com.mweb.batchservice";

	public static final String SCAN_REPOSITORY_PACKAGE_NAME = "com.mweb.repository";

	public static final String SCAN_SERVICE_PACKAGE_NAME = "com.mweb.service";

	public static final String DATABASE_PROPERTIES_FILE = "classpath:jdbc.properties";

	public static final String NOT_FOUND = "404";

	public static final String SUCCESS = "success";

	public static final String FAIL = "fail";
	
	public static final String ROLE_PREFIX = "ROLE_";

	/**
	 * Batch job parameters
	 */

	public static final String DATA_TRANSFER_JOB = "dataTransferJob";

	public static final String AUTO_DATA_TRANSFER_JOB = "autoDataTransferJob";

	public static final String LOAD_DATA_STEP = "loadDataStep";

	public static final String COPY_DATA_STEP = "copyDataStep";

	public static final String RELEASE_DATA_STEP = "releaseDataStep";

	public static final String CURRENT_TASK_NO = "CURRENT_NO";

	public static final long MAX_PROCESS_VALUE = 100;

	public static final String PLUGIN_LOCK_TYPE = "TYPE";

	public static final String RUN_AGAIN = "Run";
	
	public static final String WEB_ROOT = "/mweb/";

	public static final String ROOT_MENU = "Dashboard";
	
	public static final String DEFAULT_MENU = "<li> <a class='active' href='"+WEB_ROOT+"home'><i class='fa fa-dashboard fa-fw'></i> Dashboard</a></li>";

}
