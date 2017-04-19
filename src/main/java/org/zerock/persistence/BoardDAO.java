package org.zerock.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.zerock.domain.BoardVO;
import org.zerock.domain.PageVO;
//원하는 최종 코드 목표
@Repository
public class BoardDAO extends GenericDAO<BoardVO, Integer> {
	

	public List<BoardVO> list(PageVO param){
		return sess.selectList(mapper + ".list", param);
		
	}
	
}
