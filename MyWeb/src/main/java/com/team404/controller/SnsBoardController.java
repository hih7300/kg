package com.team404.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpSession;
import javax.xml.ws.ResponseWrapper;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.team404.command.SnsBoardVO;
import com.team404.snsboard.service.SnsBoardService;

@Controller
@RequestMapping("/snsBoard")
public class SnsBoardController {

	@Autowired
	SnsBoardService SnsBoardService;
	
	@RequestMapping("/snsList")
	public String snsList(Model model) {
		
		
		

		return "snsBoard/snsList";
	}
	
	
	// 파일 등록메서드
	@RequestMapping("/upload")
	@ResponseBody
	public String upload(@RequestParam("file") MultipartFile file,
						 @RequestParam("content") String content,
						 HttpSession session
			) {		
		// 1. 날짜별로 20191211 형식으로 upload아래에 폴더를 생성
		// 2. uploadPath 는 날짜폴더를 포함한 경로
		// 3. fileRealName은 변경하기전 파일이름
		// 4. fileName은 변경해서 저장할 파일
		// 5. fileLoca 20191211형식으로 만들어진 폴더 이름
		// 6. transferTo()메서드를 이용해서 전송되온 파일을 해당날짜에 업로드
		// 7. DB에 insert메서드를 통해서 값들을 저장
		// 8. 성공시에 화면에 success반환, 실패 시 fail반환

		// 파일명변경 ex)6a2e4a8211414fc3b78a24cba7895c57.jpg
		UUID uuid = UUID.randomUUID();
		String uuids = uuid.toString().replaceAll("-", "");
		int pos = file.getOriginalFilename().lastIndexOf(".");
		
		String fileRealName = file.getOriginalFilename().substring(0, pos);
		String extentis = FilenameUtils.getExtension(file.getOriginalFilename());
		String filename = uuids + "." + extentis;
		SimpleDateFormat ds = new SimpleDateFormat("yyyyMMdd");
		String date = ds.format(new Date());
		String uploadpath = "D:\\dev\\spring\\upload\\" + date;
		String fileloca = date;
		File folder = new File(uploadpath);
		
		File saveFile = new File(uploadpath + "\\" + filename);

		if (!folder.exists()) {
			try {
				folder.mkdir(); // 폴더 생성합니다.
				System.out.println("폴더가 생성되었습니다.");
			} catch (Exception e) {
				e.getStackTrace();
			}
		} else {
			System.out.println("이미 폴더가 생성되어 있습니다.");
		}
		
		try {
			file.transferTo(saveFile);			
		} catch (Exception e2) {
			e2.printStackTrace();
		}		
		
		SnsBoardVO vo = new SnsBoardVO();
		vo.setWriter("홍길동");
		vo.setUploadPath(uploadpath);
		vo.setFileLoca(fileloca);
		vo.setFileName(filename);
		vo.setFileRealName(fileRealName);
		vo.setContent(content);

		return SnsBoardService.insert(vo) == true ? "success" : "fail";
	}
	
	@RequestMapping("/view")
	@ResponseBody
	public byte[] view(@RequestParam("fileLoca") String fileLoca,
					   @RequestParam("fileName") String fileName
			) {	
		
		File file = new File("D:\\dev\\spring\\upload\\" + fileLoca + "\\" + fileName);
		System.out.println("adf"+fileLoca);
		System.out.println("fdf"+fileName);
		byte[] result = null;
		try {
			// 스프링의 파일데이터를 읽어서 바이트배열형으로 리턴하는 메서드(매개값으로 file타입을 받음)
			result = FileCopyUtils.copyToByteArray(file);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@RequestMapping("/getList")
	@ResponseBody
	public ArrayList<SnsBoardVO> getList(){

		return SnsBoardService.getList();
	}
}
