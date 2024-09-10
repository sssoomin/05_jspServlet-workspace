package com.br.web.board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.br.web.board.model.service.BoardService;
import com.br.web.board.model.vo.Attachment;
import com.br.web.board.model.vo.Board;
import com.br.web.common.utils.MyFileRenamePolicy;
import com.br.web.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class BoardInsertController
 */
@WebServlet("/insert.bo")
public class BoardInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");
		
		// multipart/form-data로 전송할 경우
		// request로부터 파라미터를 바로 뽑을 수 없음! 
		
		// 1. 전달된 파일 업로드(서버에 저장)
		/*
		 * * 파일 업로드 처리를 위한 라이브러리 : cos.jar
		 * 
		 *   HttpServletRequest => MultipartRequest 변환 
		 *   MulpartRequest multiRequest 
		 *   	= new MultipartRequest(request, 저장시킬폴더의물리적인경로, 용량제한값, "UTF-8", 파일명수정해주는FileRenamePolicy객체);
		 * 
		 */
		
		// 1_1) 저장시킬 폴더의 물리적인 경로 (String savePath)
		String savePath = request.getServletContext().getRealPath("/resources/board_upfiles/");
		
		// 1_2) 파일 용량 제한값 : 10mbyte (int maxSize => byte단위)
		//      byte => kbyte => mbyte => gbyte => tbyte => ...
		int maxSize = 10 * 1024 * 1024;
		
		/*
		 * 1_3) 파일명 수정처리해주는 FileRenamePolicy 객체 세팅
		 * 
		 * 	    * 파일명을 수정해서 저장해야되는 이유
		 *  	  (1) 중복된 파일명이 존재할 수 있음 
		 *  	  (2) 원본명에 한글/특수문자/공백 이 포함되어있을 수 있음 => 서버에 문제 생김
		 *  
		 *  	* 기본적으로 파일명 수정해주는 DefaultFileRenamePolicy 객체 제공
		 *  	  => rename 작업 내용
		 *  		 ㄴ 기존에 동일한 파일명이 존재할 경우
		 *     			파일명 뒤에 카운팅된 숫자를 붙여주기만 함 
		 *     			ex) aaa.jpg, aaa1.jpg, aaa2.jpg
		 *     
		 *      * 나만의 com.br.web.common.utils.MyFileRenamePolicy 객체 만들기
		 *        => rename 작업 내용
		 *        	 ㄴ 전달된 파일명을 "업로드된시간_랜덤숫자5자리.기존확장자"
		 */
		
		MultipartRequest multiRequest 
			= new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
		
		// 2. DB에 데이터 기록 
		//    카테고리번호,제목,내용,작성자회원번호 => Board에 Insert
		//    첨부파일원본명,수정명(실제업로드된파일명),저장폴더경로 => Attachment에 Insert (넘어온 첨부파일이 있었을 경우)
		
		// 게시글 데이터 => Board 에 담기
		String boardTitle = multiRequest.getParameter("title");
		String boardContent = multiRequest.getParameter("content");
		String category = multiRequest.getParameter("category"); // "20"
		
		HttpSession session = request.getSession();
		int userNo = ((Member)session.getAttribute("loginUser")).getUserNo();
		
		Board b = new Board();
		b.setBoardTitle(boardTitle);
		b.setBoardContent(boardContent);
		b.setCategory(category);
		b.setBoardWriter(String.valueOf(userNo));
		
		// 첨부파일 데이터 => Attachment 담기
		Attachment at = null; // 넘어온 첨부파일이 있을 경우 => 생성
		
		// * multiRequest.getOriginalFileName("키") : 첨부파일이 있었을 경우 "원본명" | 없을 경우 null
		if(multiRequest.getOriginalFileName("upfile") != null) {
			at = new Attachment();
			at.setOriginName(multiRequest.getOriginalFileName("upfile"));
			at.setChangeName(multiRequest.getFilesystemName("upfile"));
			at.setFilePath("/resources/board_upfiles/");
		}
		
		// 서비스 요청 
		int result = new BoardService().insertBoard(b, at);
		
		// 3. 응답
		if(result > 0) { // 성공 => 다시목록페이지 => alert메세지
			session.setAttribute("alertMsg", "성공적으로 게시글이 등록되었습니다.");
			response.sendRedirect(request.getContextPath() + "/list.bo");
		}else { // 실패 => 에러페이지 => 에러메세지
			
			// 첨부파일이 있었을 경우 
			// 이미 업로드된 파일 => 더이상 쓸모없음 => 파일제거
			if(at != null) {
				new File(savePath + at.getChangeName()).delete();
			}
			
			request.setAttribute("msg", "게시글 등록 실패");
			request.getRequestDispatcher("/views/common/errorPage.jsp").forward(request, response);
			
		}
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
