package org.jae.controller;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class )
@WebAppConfiguration
@ContextConfiguration(
		{"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		 "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"}
) // 이걸로 빈이 있는 위치를 잡아놓고
public class BoardControllerTests {
	@Autowired
	private WebApplicationContext ctx;
	
	private MockMvc mocMvc;
	
	@Before
	public void setup() {
		this.mocMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void testList() throws Exception {
		log.info(mocMvc.perform(MockMvcRequestBuilders.get("/board/list"))
				 .andReturn()
				 .getModelAndView()
				 .getModelMap()
				);
		
	}

}
