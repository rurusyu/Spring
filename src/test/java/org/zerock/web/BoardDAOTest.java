package org.zerock.web;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.PageVO;
import org.zerock.persistence.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class BoardDAOTest {

	@Inject
	BoardDAO dao;
	
	@Test
	public void testDummy(){
		for(int i=201; i<202; i++){
			BoardVO vo = new BoardVO();
			vo.setTitle("테스트" + i);
			vo.setContent("내용..."+i);
			
			dao.create(vo);
		}
		
	}
	
	@Test
	public void testList(){
		
		List<BoardVO> list = dao.list(new PageVO());
		
		list.forEach(board -> System.out.println(board));
		
	}
	
}
