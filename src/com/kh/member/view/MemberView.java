package com.kh.member.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.member.model.vo.Member;

public class MemberView {
	private Scanner sc = new Scanner(System.in);
	// 메인 메뉴
	public int mainMenu() {
		int select = 0;
		while(true) {
			System.out.println("##### 회원 관리 프로그램 #####");
			System.out.println("1. 회원 등록");
			System.out.println("2. 회원 정보 수정");
			System.out.println("3. 회원 삭제");
			System.out.println("4. 회원 목록 조회");
			System.out.println("5. 회원 정보 조회(ID)");
			System.out.println("6. 회원 정보 조회(이름)");
			System.out.println("7. 프로그램 종료");
			System.out.print("메뉴 선택 : ");
			select = sc.nextInt();
			if(select >= 1 && select <= 6) { 
				break;
			}else if(select == 7) {
				System.out.println("프로그램이 종료됩니다.");
				break;
			}
			System.out.println("잘못된입력! 다시 선택해주세요!");
		}
		return select;
	}

	// 회원 등록 - 입력
	public Member insertMember() {
		System.out.println("----- 회원 등록 -----");
		// 사용자에게 입력받은 데이터를 Member객체에 저장
		Member mb = new Member();		
		System.out.print("아이디 : ");
		mb.setMemberId(sc.next());
		System.out.print("비밀번호 : ");
		mb.setMemberPwd(sc.next());
		System.out.print("이름 : ");
		mb.setMemberName(sc.next());
		System.out.print("성별(M/F) : ");
		mb.setGender(sc.next().charAt(0));
		System.out.print("나이 : ");
		mb.setAge(sc.nextInt());		
		System.out.print("이메일 : ");
		mb.setEmail(sc.next());
		System.out.print("휴대폰 : ");
		mb.setPhone(sc.next());
		sc.nextLine();
		System.out.print("주소 : ");
		mb.setAddress(sc.nextLine());
		System.out.print("취미(여러 개일 경우 ,로 구분) : ");
		mb.setHobby(sc.nextLine());
		mb.setEnrollDate(new java.sql.Date(new java.util.Date().getTime()));
		return mb;
	}

	// 회원 정보 수정 - 입력
	public Member updateMember(Member mb) {
		System.out.println("----- 회원 정보 수정 -----");
		if(mb == null) {
			System.out.println("회원정보가 없습니다.");
			return mb;
		}
		int select = 0;
		while(select != 8) {
			System.out.println("[ "+mb.getMemberId()+" 회원 정보 ]");
			System.out.println("이름 : "+mb.getMemberName());
			System.out.println("등록일자 : "+mb.getEnrollDate());
			System.out.println("1.비밀번호 : ****");
			System.out.println("2.성별 : "+mb.getGender());
			System.out.println("3.나이 : "+mb.getAge());
			System.out.println("4.이메일 : "+mb.getEmail());
			System.out.println("5.휴대폰 : "+mb.getPhone());
			System.out.println("6.주소 : "+mb.getAddress());
			System.out.println("7.취미 : "+mb.getHobby());
			System.out.println("8.수정완료");
			System.out.print("수정할 항목 번호 : ");
			select = sc.nextInt(); 
			switch(select) {
			case 1 : 
				System.out.print("새로운 비밀번호 : ");
				mb.setMemberPwd(sc.next());
				break;
			case 2 : 
				System.out.print("새로운 성별 : ");
				mb.setGender(sc.next().charAt(0));
				break;
			case 3 : 
				System.out.print("새로운 나이 : ");
				mb.setAge(sc.nextInt());
				break;
			case 4 : 
				System.out.print("새로운 이메일 : ");
				mb.setEmail(sc.next());
				break;
			case 5 : 
				System.out.print("새로운 휴대폰 : ");
				mb.setPhone(sc.next());
				break;
			case 6 : 
				System.out.print("새로운 주소 : ");
				if(sc.hasNextLine()) {
					sc.nextLine();
				}
				mb.setAddress(sc.nextLine());
				break;
			case 7 : 
				System.out.print("새로운 취미 : ");
				if(sc.hasNextLine()) {
					sc.nextLine();
				}
				mb.setHobby(sc.nextLine());
				break;
			case 8 : break;
			default : 
				System.out.println("잘못된 번호를 입력하셨습니다.");
			}
		}	
		return mb;
	}

	// 회원 삭제 - 입력
	public String deleteMember(Member mb) {
		System.out.println("----- 회원 삭제 -----");
		if(mb == null) {
			System.out.println("회원정보가 없습니다.");			
		}else {
			System.out.println("[ "+mb.getMemberId()+" 회원 정보 ]");
			System.out.println("이름 : "+mb.getMemberName());
			System.out.println("등록일자 : "+mb.getEnrollDate());
			System.out.print("삭제하시겠습니까?(Y/N) : ");
			if(sc.next().charAt(0) == 'Y') {
				return mb.getMemberId();
			}
		}
		return null;
	}
	
	// 회원 등록,수정,삭제 - 결과 출력
	public void printResult(int result, String mode) {
		if(result > 0) {
			System.out.println("회원 "+mode+" 완료");
		}else {
			System.out.println("회원 "+mode+" 실패");
		}		
	}

	// 회원 목록 조회
	public void allListMember(ArrayList<Member> list) {
		if(list.size() > 0) {
			System.out.println("총 "+list.size()+"명이 조회되었습니다.");
			System.out.println("-----------------------------------");
			System.out.println("아이디		이름	성별	나이");			
			System.out.println("-----------------------------------");
			for(Member mb : list) {
				System.out.println(mb.getMemberId()+"		"+mb.getMemberName()+"	"+mb.getGender()+"	"+mb.getAge());
			}
			System.out.println("-----------------------------------");
		}else {
			System.out.println("회원정보가 없습니다.");
		}
	}

	// 회원 아이디 입력
	public String inputId(String mode) {
		System.out.print(mode+"할 회원 아이디 입력 : ");
		String id = sc.next();
		return id;
	}
	
	// 회원 정보 조회(ID) - 결과 출력
	public void searchMemberId(Member mb) {
		if(mb != null) {
			System.out.println("[ "+mb.getMemberId()+" 회원 정보 ]");
			System.out.println("이름 : "+mb.getMemberName());
			System.out.println("등록일자 : "+mb.getEnrollDate());
			System.out.println("성별 : "+mb.getGender());
			System.out.println("나이 : "+mb.getAge());
			System.out.println("이메일 : "+mb.getEmail());
			System.out.println("휴대폰 : "+mb.getPhone());
			System.out.println("주소 : "+mb.getAddress());
			System.out.println("취미 : "+mb.getHobby());
		}else {
			System.out.println("회원 정보가 없습니다.");
		}
	}

	// 회원 이름 입력
	public String inputName(String mode) {
		System.out.print(mode+"할 회원 이름 입력 : ");
		String name = sc.next();
		return name;
	}
	
	// 회원 정보 조회(이름) - 결과 출력
	public void searchMemberName(ArrayList<Member> list) {
		if(list.size() > 0) {
			System.out.println("총 "+list.size()+"명의 회원이 조회되었습니다.");
			for(Member mb : list) {
				System.out.println("[ "+mb.getMemberId()+" 회원 정보 ]");
				System.out.println("이름 : "+mb.getMemberName());
				System.out.println("등록일자 : "+mb.getEnrollDate());
				System.out.println("성별 : "+mb.getGender());
				System.out.println("나이 : "+mb.getAge());
				System.out.println("이메일 : "+mb.getEmail());
				System.out.println("휴대폰 : "+mb.getPhone());
				System.out.println("주소 : "+mb.getAddress());
				System.out.println("취미 : "+mb.getHobby());
			}
		}else {
			System.out.println("회원정보가 없습니다.");
		}
	}

	// 회원 정보 없음 출력
	public void printNotFoundMember() {
		System.out.println("회원정보가 없습니다.");
	}
}
