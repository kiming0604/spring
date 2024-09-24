package org.kys.persistence;


import static org.junit.Assert.fail;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class )
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml") // 이걸로 빈이 있는 위치를 잡아놓고
public class DataSourceTests {
    
	@Autowired // 오토와이어드를 부르면 해당 위치에서 같은 이름을 가진 빈을 가져옴
	private DataSource dataSource;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Test
	public void testConnection() {
		try(Connection conn = dataSource.getConnection()) {
			log.info(conn);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	@Test
	public void testMyBatis() {
		try(SqlSession session = sqlSessionFactory.openSession()) {
			log.info(session);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
}
