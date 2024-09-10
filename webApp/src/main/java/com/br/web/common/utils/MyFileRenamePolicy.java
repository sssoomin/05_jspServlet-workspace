package com.br.web.common.utils;

import java.io.File;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File originFile) { // 내부적으로 해당 rename 메소드 호출시 원본파일 객체가 전달됨
		
		// 원본파일명("aaa.jpg") => 수정파일명("1702938174_12371.jpg")
		//							업로드시간(밀리세컨)_랜덤숫자5자리.확장자
		
		String originalFilename = originFile.getName(); // 원본파일명("aaa.jpg")
		
		// 파일명 수정 작업
		// 1. 파일업로드한 시간 (long currentTime)
		long currentTime = System.currentTimeMillis(); // 1970/01/01 부터 현재시간까지 경과한 시간을 밀리세컨초로 반환
		// 2. 랜덤숫자 5자리 (10000~99999) (int ranNum)
		int ranNum = (int)(Math.random() * 90000 + 10000);
		// 3. 원본파일의 확장자 (String ext)
		String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
		
		String filesystemName = currentTime + "_" + ranNum + ext;
		
		return new File(originFile.getParent(), filesystemName);
	}

}
