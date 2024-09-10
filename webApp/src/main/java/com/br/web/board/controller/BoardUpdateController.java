package com.br.web.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.br.web.board.model.service.BoardService;
import com.br.web.board.model.vo.Attachment;
import com.br.web.board.model.vo.Board;
import com.br.web.common.utils.MyFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class BoardUpdateController
 */
@WebServlet("/update.bo")
public class BoardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		// HttpServletRequest request => MultipartRequest multiRequest
		
		// 1. 전달된 파일 업로드
		String savePath = request.getServletContext().getRealPath("/resources/board_upfiles/");
		int maxSize = 10 * 1024 * 1024;
		MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy()); 
		
		// 2. DB에 기록
		
		// >> 공통적으로 수행 : Update Board (카테고리번호,제목,내용,글번호)
		int boardNo = Integer.parseInt(multiRequest.getParameter("no"));
		String category = multiRequest.getParameter("category");
		String boardTitle = multiRequest.getParameter("title");
		String boardContent = multiRequest.getParameter("content");
		
		Board b = new Board();
		b.setBoardNo(boardNo);
		b.setCategory(category);
		b.setBoardTitle(boardTitle);
		b.setBoardContent(boardContent);
		
		// 새로 넘어온 첨부파일이 있을 경우 => Attachment에 Insert 또는 Update 
		Attachment at = null;
		if(multiRequest.getOriginalFileName("upfile") != null) { // 새로넘어온 첨부파일 있을 경우
			at = new Attachment();
			at.setOriginName(multiRequest.getOriginalFileName("upfile"));
			at.setChangeName(multiRequest.getFilesystemName("upfile"));
			at.setFilePath("/resources/board_upfiles/");
			
			if(multiRequest.getParameter("originFileNo") != null) { 
				// 기존에 첨부파일이 있었을 경우 => Update
				at.setFileNo(Integer.parseInt(multiRequest.getParameter("originFileNo")));
			}else { 
				// 기존에 첨부파일이 없었을 경우 => Insert
				at.setRefBoardNo(boardNo);
			}
		}
		
		int result = new BoardService().updateBoard(b, at);
		
		if(result > 0) { // 성공
			// alertMsg와 함께 다시 상세페이지 이동
			request.getSession().setAttribute("alertMsg", "성공적으로 게시글이 수정되었습니다.");
			response.sendRedirect(request.getContextPath() + "/detail.bo?no=" + boardNo);
			
		}else { // 실패
			// 에러메세지와 함께 에러페이지로 이동
			request.setAttribute("msg", "게시글 수정 실패");
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
