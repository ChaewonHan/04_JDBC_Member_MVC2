package com.kh.member.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.member.common.JDBCTemplate1;
import com.kh.member.common.JDBCTemplate2;
import com.kh.member.common.JDBCTemplate3;
import com.kh.member.common.JDBCTemplate4;
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
//		// 방법 3 : 객체를 생성하지 않고 각 메소드를 호출해서 사용(정적메소드로 구현 - static)
//		Connection conn = JDBCTemplate3.getConnection();
////		MemberDAO mdao = new MemberDAO();
////		int result = mdao.insertMember(conn, mb);
//		// 위 두 코드 줄여서 사용(객체를 한번만 사용할 경우)
//		int result = new MemberDAO().insertMember(conn, mb);
//		JDBCTemplate3.close(conn);
		
		// SingleTon 사용
			//1. Template3의 단점 
				// - 모든 메서드를 static으로 구현해야함
				// - 사용할 때마다 클래스명 전체를 불러서 사용해야해서 불편함
				// - Template3으로 실제 객체도 생성 가능함
			// 싱글톤 구현시 객체 생성 불가능
			// 외부에서는 객체 생성 메소드를 이용해서 객체의 주소를 받아서 사용 가능
				// 클래스 내부에서 객체가 하나만 생성
			JDBCTemplate4 jt4 = JDBCTemplate4.getInstance();
			Connection conn = jt4.getConnection();
			int result = new MemberDAO().insertMember(conn, mb);
			if(result > 0) {
				jt4.commit(conn);
			}else {
				jt4.close(conn);
			}
		
		// 최종 결과값 컨트롤러에 전달
		return result;	
	}
	
	public int updateMember(Member mb) {
		Connection conn = JDBCTemplate3.getConnection();
		int result = new MemberDAO().updateMember(conn, mb);
		JDBCTemplate3.close(conn);
		return result;
	}
	// 회원 삭제
	public int deleteMember(String id) {
		Connection conn = JDBCTemplate3.getConnection();
		int result = new MemberDAO().deleteMember(conn, id);
		// 트랜젝션 사용 시 작업이 모두 완료 된 후 조건에 따라 한번에 모두 전용할지 원복할지 결정
		if(result > 0) {
			JDBCTemplate3.commit(conn);
		}else {
			JDBCTemplate3.rollback(conn);
		}
		JDBCTemplate3.close(conn);	// close하면 autocommit
		return result;
	}
	public ArrayList<Member> allListMember() {
		Connection conn = JDBCTemplate3.getConnection();
		ArrayList<Member> list = new MemberDAO().allListMember(conn);
		JDBCTemplate3.close(conn);
		return list;
	}
	public Member searchMemberId(String id) {
		Connection conn = JDBCTemplate3.getConnection();
		Member mb = new MemberDAO().searchMemberId(conn, id);
		JDBCTemplate3.close(conn);
		return mb;
	}
	public ArrayList<Member> searchMemberName(String name) {
		Connection conn = JDBCTemplate3.getConnection();
		ArrayList<Member> list = new MemberDAO().searchMemberName(conn, name);
		JDBCTemplate3.close(conn);
		return list;
	}
}


