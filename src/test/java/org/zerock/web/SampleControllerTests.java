package org.zerock.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

//컨트롤러 테스트는 웹에 url입력해서 하면 됨.
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*-context.xml"})
@WebAppConfiguration //AppContext 부분을 로딩시켜오는 듯함.
public class SampleControllerTests {

	@Inject
	WebApplicationContext ctx;
	
	MockMvc mockMvc; //가짜 웹 appcontext
	
	@Before
	public void setup(){
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build(); //가짜 MVC
	}
	
	@Test
	public void testLoad()throws Exception{
		//가짜 MVC에 가짜 request날릴꺼임.
		mockMvc.perform(get("/sample/doA"));
		
	}
	@Test
	public void testDoB()throws Exception{
		
		mockMvc.perform(post("/sample/doA").param("title", "한글")
				.param("content", "asdasdasd용")); //빌드업 패턴임. j쿼리로 체이닝
		
	}
	@Test //정상실행되는지 확인
	public void testDoB2()throws Exception{
		
		MvcResult result = mockMvc.perform(get("/sample/doA")).andReturn();	 //빌드업 패턴임. j쿼리로 체이닝
		//화면 어느페이지로 가는지 조사 가능			
		ModelAndView mav = result.getModelAndView();
		
		System.out.println(mav.getModel());
		System.out.println(mav.getViewName());
		int status = result.getResponse().getStatus();
		
		System.out.println("STATUS:" + status);
	}
	
}
