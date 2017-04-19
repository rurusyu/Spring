package org.zerock.persistence;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
//VO타입, Key타입
abstract class GenericDAO<E, K> {

//사실인터페이스로 하는게 좋음 이따 뺼것임. 어제까지 했던 코드는 인터페이스로 갈아 엎어야함.	
	
	//1번째 상속
	@Inject
	protected SqlSessionTemplate sess;
	
	//2번째 네임스페이스와 클래스명 맞춤
	protected String mapper;
	
	GenericDAO(){
	
		//패키지 명과 클래스 네임
		mapper = this.getClass().getName();
				
	}

	public void create(E vo){
		sess.insert(mapper+".create", vo);
	}
	
	public E read(K key){
		return sess.selectOne(mapper+".read", key);
	}
	
	public void update(E vo){
		sess.update(mapper+".update",vo);
	}
	
	public void delete(K key){
		sess.delete(mapper +".delete", key);
	}
}
