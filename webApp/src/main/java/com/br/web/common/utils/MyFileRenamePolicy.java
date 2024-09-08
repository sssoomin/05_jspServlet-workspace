package com.br.web.common.utils;

import java.io.File;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy {
	
	public File rename(File originFile) {
		
		// 원본파일명("aaa.jpg") => 수정파일명 ("1702938174_12371.jpg")
		//									    업로드시간(밀리세컨)_랜덤숫자5자리.확장자
		
		String originalFilename = originFile.getName(); // 원본파일명("aaa.jpg")
		
		// 파일명 수정 작업
		// 1. 파일 업로드한 시간 (long currentTime)
		long currentTime = System.currentTimeMillis();
		// 2. 랜덤숫자 5자리 (10000~9999) (int ranNum)
		int ranNum = (int)(Math.random() * 90000 + 10000);
		// 3. 원본파일의 확장자 (String ext)
		String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
		
		String filesystemName = currentTime + "_" + ranNum + ext;
		
		
		return new File(originFile.getParent(), filesystemName);
	}

}
