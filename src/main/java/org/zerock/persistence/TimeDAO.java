package org.zerock.persistence;
import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class TimeDAO {
	
	@Inject
	private SqlSessionTemplate session; // 이것도 귀찮으면 상속구조를 써랏.

//	public void setSession(SqlSessionTemplate session) {
//		this.session = session;
//	}
	
	//밥먹기전에 xml이용할 것임
	public String getTime(){
		return session.selectOne("org.zerock.TimeMapper.getTime");
		
		
	}
	
	
}
