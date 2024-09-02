package com.br.web.member.model.service;

import static com.br.web.common.template.JDBCTemplate.close;
import static com.br.web.common.template.JDBCTemplate.commit;
import static com.br.web.common.template.JDBCTemplate.getConnection;
import static com.br.web.common.template.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.Map;

import com.br.web.member.model.dao.MemberDao;
import com.br.web.member.model.vo.Member;

public class MemberService {
	
	private MemberDao mDao = new MemberDao();
	
	public Member loginMember(String userId, String userPwd) {
		Connection conn = getConnection();
		Member loginUser = mDao.loginMember(conn, userId, userPwd);
		close(conn);
		return loginUser;
	}
	
	public int insertMember(Member m) {
		Connection conn = getConnection();
		int result = mDao.insertMember(conn, m);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
		
	}
	
	// 정보 변경 
	public Member updateMember(Member m) {
		
		Connection conn = getConnection();
		
		// 1. 회원정보 변경 (update)
		int result = mDao.updateMember(conn, m);
		Member updateMem = null;
		if(result > 0) {
			commit(conn);
			// 2. 갱신된 회원 조회 (select)
			updateMem = mDao.selectMemberById(conn, m.getUserId());
			
			
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return updateMem; // null(정보변경실패) | 갱신된회원객체(정보변경성공)
		
	}
	
	public Member updateMemberPwd(Map<String, String> map) {
		Connection conn = getConnection();
		
		// 1. 비밀번호 변경 (update)
		int result = mDao.updateMemberPwd(conn, map);
		
		Member updateMem = null;
		if(result > 0) {
			commit(conn);
			// 2. 갱신된 회원 조회 (select)
			updateMem = mDao.selectMemberById(conn, map.get("userId"));
		}else {
			rollback(conn);
		}
		close(conn);
		return updateMem;
		
		
	}
	
	public int deleteMember(String userId, String userPwd) {
		Connection conn = getConnection();
		int result = mDao.deleteMember(conn, userId, userPwd);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	

}
