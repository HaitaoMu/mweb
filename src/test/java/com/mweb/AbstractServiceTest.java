package com.mweb;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mweb.config.AppConfiguration;
import com.mweb.config.common.ApplicationConfiguration;
import com.mweb.config.common.DatabaseConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DatabaseConfiguration.class)
public class AbstractServiceTest
{

}
