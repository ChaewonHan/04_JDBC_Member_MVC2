package com.kh.member.service;

import java.sql.Connection;

import com.kh.member.common.JDBCTemplate1;
import com.kh.member.common.JDBCTemplate2;
import com.kh.member.common.JDBCTemplate3;
import com.kh.member.model.dao.MemberDAO;
import com.kh.member.model.vo.Member;

public class MemberService {
	// 회원 등록
	public int insertMember(Member mb) {
//	// 방법 1,2 : JDBCTemplate 객체를 생성해서 사용
//		// 방법 1 : JDBCTemplate1 : 템플릿 클래스 내 Connection 참조변수를 필드로 선언
//		JDBCTemplate1 jt1 = new JDBCTemplate1();	// 공통 모듈 객체 생성
//		Connection conn = jt1.getConnection();	// 공통 모듈에서 연결정보 받아와서 저장
//		MemberDAO mdao = new MemberDAO();	// 모델(DAO) 객체 생성
//		int result = mdao.insertMember(conn, mb);	// 모델(DAO)에 연결정보와 데이터 전달해서 기능 수행 한 뒤 결과값 받아오기
//		// 필드에 연결 정보가 남아있기 때문에 close() 메소드에서 바로 사용 가능
//		jt1.close();	// 연결 정보 닫기		
//		// 방법 2 : JDBCTemplate2 : 템플릿 클래스 내 Connection 참조변수를 getConnection()메소드의 지역변수로 선언
//		JDBCTemplate2 jt2 = new JDBCTemplate2();
//		Connection conn = jt2.getConnection();
//		MemberDAO mdao = new MemberDAO();
//		int result = mdao.insertMember(conn, mb);
//		// 연결 정보가 지역변수라서 close()메소드에서 사용할 수 없기 때문에 호출 시 연결 정보를 전달해 줘야 함
//		jt2.close(conn);		
		// 방법 3 : 객체를 생성하지 않고 각 메소드를 호출해서 사용(정적메소드로 구현 - static)
		Connection conn = JDBCTemplate3.getConnection();
		MemberDAO mdao = new MemberDAO();
		int result = mdao.insertMember(conn, mb);
		JDBCTemplate3.close(conn);
		
		// 최종 결과값 컨트롤러에 전달
		return result;
		
	}
}


