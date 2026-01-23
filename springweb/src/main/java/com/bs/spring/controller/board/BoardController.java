package com.bs.spring.controller.board;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bs.spring.common.PageBarFactory;
import com.bs.spring.model.dto.Attachment;
import com.bs.spring.model.dto.Board;
import com.bs.spring.model.service.board.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {

	private final BoardService service;

	@RequestMapping("/boardlist.do")
	public String boardlist(@RequestParam(value = "cPage", required = false, defaultValue = "1") int cPage,
			@RequestParam(value = "numPerpage", required = false, defaultValue = "5") int numPerpage, Model m) {
		// 페이징처리!
		List<Board> boards = service.searchBoardAll(Map.of("cPage", cPage, "numPerpage", numPerpage));
		int totalData = service.searchBoardCount();

		m.addAttribute("boards", boards);
		m.addAttribute("totalContents", totalData);
		m.addAttribute("pageBar", PageBarFactory.createPageBar(cPage, numPerpage, totalData, "boardlist.do"));

		return "board/boardlist";
	}

	@RequestMapping("/boardview.do")
	public String boardView(int boardNo, Model m, HttpServletResponse response,
			@CookieValue(value = "readBoard", required = false, defaultValue = "") String readBoard) {

		boolean readFlag = true;// 읽음

		if (!readBoard.contains("||" + boardNo + "||")) {
			Cookie c = new Cookie("readBoard", readBoard + "||" + boardNo + "||");
			c.setMaxAge(60 * 60 * 24);
			response.addCookie(c);
			readFlag = false;
		}

		Board b = service.searchBoardByNo(boardNo, readFlag);
		m.addAttribute("board", b);

		return "board/boardView";
	}

	@RequestMapping("/boardwrite.do")
	public void boardWrite() {
	}

	@RequestMapping("/insertboardend.do")
	public String insertBoard(Board b, MultipartFile[] upFile, HttpSession session) {
//		log.debug(upFile.getName());
//		log.debug(upFile.getOriginalFilename());
//		log.debug("{}", upFile.getSize());

		// 파일 업로드 할 폴더 가져오기 -> 절대경로
		String path = session.getServletContext().getRealPath("/resources/upload/board");
//		log.debug(path);

		for (MultipartFile mf : upFile) {
			// 파일 이름 재생성하기
			String oriName = mf.getOriginalFilename();
			String ext = oriName.substring(oriName.indexOf("."));
			int rnd = (int) (Math.random() * 10000 + 1);
			Date d = new Date(System.currentTimeMillis());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HHmmssSSS");
			String rename = "BSLOVE_" + sdf.format(d) + "_" + rnd + ext;

			File dir = new File(path);
			if (!dir.exists())
				dir.mkdir();

			try {
				// transferTo() 메소드 이용
				mf.transferTo(new File(dir, rename));
				// 저장된 파일을 DTO에 저장
				Attachment a = Attachment.builder().originalFilename(oriName).renamedFilename(rename).build();
				b.getFiles().add(a);
			} catch (IOException e) {
				// TODO: handle exception
				log.error("파일 업로드 실패 " + oriName);
				e.printStackTrace();
			}
		}
		try {
			service.insertBoard(b);
		} catch (RuntimeException e) {
			// 실패
			b.getFiles().forEach(file -> {
				File delFile = new File(path, file.getRenamedFilename());
				if (delFile.exists())
					delFile.delete();
			});
			return "redirect:/board/boardwrite.do";
		}
		return "redirect:/board/boardlist.do";
	}

	@RequestMapping("/download")
	public void filedownload(String ori, String re, HttpSession session, OutputStream out, HttpServletResponse response,
			@RequestHeader("user-agent") String header) {
		String path = session.getServletContext().getRealPath("/resources/upload/board");
		File f = new File(path, re);
		if (!f.exists()) {
			throw new IllegalArgumentException("파일이 존재하지 않습니다");
		}

		try (FileInputStream fis = new FileInputStream(f);
				BufferedInputStream bis = new BufferedInputStream(fis);
				BufferedOutputStream bos = new BufferedOutputStream(out);) {
			// 원본 파일명 인코딩 처리 => 한글파일명 깨짐방지
			String encodeName = "";
			if (header.contains("Trident") || header.contains("MSIE")) {
				// MS 브라우저
				encodeName = URLEncoder.encode(ori, "UTF-8");
				encodeName = encodeName.replace("\\+", "%20");
			} else {
				encodeName = new String(ori.getBytes("UTF-8"), "ISO-8859-1");
			}

			response.setContentType("application/octet-stream;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + encodeName);

			int data = -1;
			while ((data = bis.read()) != -1) {
				bos.write(data);
			}
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
