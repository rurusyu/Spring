package org.zerock.web;

import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.persistence.TimeDAO;

//이거 두개만 걸면 테스트 돌아감. 스프링을 로딩시킴. 어플리케이션 context가 로딩됨. 스프링 테스트 라이브러이있어야함.
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})//root-context.xml 경로가 안맞으면 이상하게 뜸..** 하위경로 어떤거 들어가도 상관없다는 것임.
public class ConnectionTest2 {

	private static Logger logger
	= Logger.getLogger(ConnectionTest2.class);
	//커넥션 테스트니깐 데이터소스 가져와야함.
	
	@Autowired
	private DataSource ds;
	
	
	@Inject
	ApplicationContext ctx;//원시적으로 mybatis 끌어옴 sqlSessionFactory 이거 가져옴
	
	@Inject
	SqlSessionFactory sqlFactory;
	
	@Inject
	TimeDAO dao;
	
	
	@Test
	public void testGetTime(){
		logger.info("=========================================================");
		logger.info(dao.getTime());
		logger.info("=========================================================");
	}
	
	//진짜 DB연결되는지 확인
	@Test
	public void testSession(){
	
		SqlSession sess = sqlFactory.openSession();
	
		logger.info(sess+"XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		
		String time = sess.selectOne("org.zerock.TimeMapper.getTime");
		
		logger.info("=======================");
		logger.info(time);
		logger.info("=======================");
		
		sess.close();
	}
	
	
	@Test
	public void testCtx(){
		
		logger.info(ctx+"!!!!!!!!!!!!!!!!!!!");
		
		Object obj = ctx.getBean("sqlSessionFactory"); // getBean이 오브젝트를 줌
		
		logger.info(obj+"++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		
	}
	
	
	@Test
	public void testLoading(){
		
		logger.info("test loading");
		
		logger.info(ds);
		
		//logger 테스트끝나면 커넥션 연결해줄 것.
		try(Connection con = ds.getConnection()){
			logger.info(con);
		}catch(Exception e){
			
		};
		
	}
	
	
}
//설정파일 위치지정안해서 뜨는 것임.locations={"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"} 지정해주어야함.
//NFO : org.springframework.test.context.support.AbstractContextLoader - Could not detect default resource locations for test class [org.zerock.web.ConnectionTest2]: no resource found for suffixes {-context.xml}.
//INFO : org.springframework.test.context.support.AnnotationConfigContextLoaderUtils - Could not detect default configuration classes for test class [org.zerock.web.ConnectionTest2]: ConnectionTest2 does not declare any static, non-private, non-final, nested classes annotated with @Configuration.

