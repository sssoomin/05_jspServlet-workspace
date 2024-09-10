package com.br.web.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
 * Servlet implementation class ThumbnailInsertController
 */
@WebServlet("/insert.th")
public class ThumbnailInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThumbnailInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");
		
		// 1. 파일 업로드 (서버 저장)
		String savePath = request.getServletContext().getRealPath("/resources/thumbnail_upfiles/");
		int maxSize = 10*1024*1024;
		MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
		
		// 2. db에 기록
		
		// 게시글 정보 => Board 한행 insert
		Board b = new Board();
		b.setBoardTitle(multiRequest.getParameter("title"));
		b.setBoardContent(multiRequest.getParameter("content"));
		HttpSession session = request.getSession();
		int userNo = ((Member)session.getAttribute("loginUser")).getUserNo();
		b.setBoardWriter(String.valueOf(userNo));
		
		// 첨부파일 정보 => Attachment 여러행 insert
		List<Attachment> list = new ArrayList<>();
		for(int i=1; i<=4; i++) {
			String key = "upfile" + i;
			if(multiRequest.getOriginalFileName(key) != null) { 
				// 해당 key값의 첨부파일이 존재할 경우
				// Attachment 객체 생성 + 원본명, 수정명, 저장경로, 파일레벨 담아서 => list에 추가
				Attachment at = new Attachment();
				at.setOriginName(multiRequest.getOriginalFileName(key));
				at.setChangeName(multiRequest.getFilesystemName(key));
				at.setFilePath("/resources/thumbnail_upfiles/");
				at.setFileLevel(i == 1 ? 1 : 2);
				list.add(at);
			}
		}
		
		int result = new BoardService().insertThumbnailBoard(b, list);
		
		if(result == list.size()) { // 성공
			session.setAttribute("alertMsg", "성공적으로 게시글이 등록되었습니다.");
			response.sendRedirect(request.getContextPath() + "/list.th");
		}else { // 실패
			
			// 이미 저장된 파일 삭제
			for(Attachment at : list) {
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
