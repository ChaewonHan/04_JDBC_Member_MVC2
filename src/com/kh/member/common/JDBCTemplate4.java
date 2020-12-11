package com.kh.member.common;
/* Common 패키지
 * - 공통적으로 사용되는 코드를 모아놓은 패키지
 * - 공통적인 코드들을 Template Class로 만들어서 중복을 제거
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/* SingleTon 방식
 * - 클래스의 객체가 프로그램을 구동하는 내내 1개만 만들어져서 사용하는 디자인패턴
 * - 동일한 형태의 객체는 new를 통해서 많이 생성하게 되면 오버헤드가 발생할 수 있음
 * - 1개만 선언해도 문제가 없을 경우 사용하는 방식
 
 *	[SingleTon 적용]
 *	1. SingleTon을 적용하고자 하는 클래스의 필드부에 자기 자신을 참조하는 참조변수 생성
 *	2. 생성자를 private으로 만들어서 new를 통한 객체 생성이 불가능하게 만들어줌
 *	3. 클래스 내 자기 자신의 객체를 생성하는 메소드를 만들어서 생성된 객체의 주소를 반환해 줌
 *		- 자기참조변수의 값이 null인 경우에만 객체 생성
 *		
 */	

// SingleTon 적용 클래스
public class JDBCTemplate4 {
// 필드부	
	// 1. SingleTon을 적용하고자 하는 클래스의 필드부에 자기 자신을 참조하는 참조변수 생성
	private static JDBCTemplate4 instance = null;
// 생성자부
	// 2. 생성자를 private으로 만들어서 new를 통한 객체 생성이 불가능하게 만들어 줌
	private JDBCTemplate4() {}
// 메소드부
	// 3. 클래스 내 자기 자신의 객체를 생성하는 메소드를 만들어서 생성된 객체의 주소를 반환해줌
		// 외부에서 객체 생성을 위해 접근해야 하기 때문에 static 메소드로 만들어줌
	public static JDBCTemplate4 getInstance() {
		if(instance == null) {
			instance = new JDBCTemplate4();
		}
		return instance;
	}
	// 객체 생성 후 사용할 메소드들은 static 메서드로 만들 필요 없음
	// DBMS 연결
	public Connection getConnection() {		
		Connection conn = null;
		try {
			// 드라이버 등록 - 생략가능(JDBC 하위버전 호환성을 위해 포함해 줌)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// DBMS 연결
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:XE","STUDENT","STUDENTPASS");
			// 템플릿을 이용해서 DB연결 시 Autocommit 안하도록 설정
			conn.setAutoCommit(false);
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("예기치 못한 오류 발생 : 데이터베이스 연결 정보를 확인하세요");
			e.printStackTrace();
		}
		return conn;
	}
	// DBMS 연결 닫기
	public void close(Connection conn) {
		try {
			// 연결 정보가 있고, 닫혀있지 않은 경우에만 닫아주기
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}
	// 트랜젝션 제어(commit)
	public void commit(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// 트랜젝션 제어(rollback)
	public void rollback(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// Statement, PreparedStatement 닫기
	public static void close(Statement stmt) {
		try {
			if(stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// ResultSet 닫기
	public static void close(ResultSet rset) {
		try {
			if(rset != null && !rset.isClosed()) {
				rset.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}	
