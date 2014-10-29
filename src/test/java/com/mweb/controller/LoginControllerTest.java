/***********************************************************************
 *
 * @file         LoginControllerTest.java
 *
 * @copyright    Copyright: 2014-2016 Usee Co. Ltd.
 * @author       JetQin 
 * @create-time  2014年10月29日
 *
 *
 ***********************************************************************/
package com.mweb.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author jet
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes={RootConfiguration.class, WebMvcConfiguration.class})
@WebAppConfiguration
public class LoginControllerTest
{
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup()
    {
        mvc = webAppContextSetup(context).build(); 
    }

    @Test
    public void composeMessage() throws Exception {
        MockHttpServletRequestBuilder composeMessage = 
        		 post("/")
        		.param("summary", "New Message")
                .param("text", "This is a new message");

        mvc.perform(composeMessage).andExpect(redirectedUrlPattern("/*"));
    }
}
