package kr.co.icanman.explorer;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FileExplorer")
public class Explorer extends HttpServlet {

	String[] list = null;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("서블릿 안타냐..?");
		request.setCharacterEncoding("UTF-8");
		
		String filePath = "C:\\javaTest\\";
		File f1 = new File(filePath);
		
		if(f1.exists()) {
			request.setAttribute("path", filePath);
			list = f1.list();
		}
		subDirList(filePath,request);
		request.setAttribute("content", list);
		
		request.getRequestDispatcher("/fileExplorer.jsp").forward(request, response);;

	}
	
	
	public void subDirList(String source,HttpServletRequest request){
		File dir = new File(source); 
		File[] fileList = dir.listFiles(); 

		try{
			for(int i = 0 ; i < fileList.length; i++){
				File file = fileList[i]; 
				if(file.exists() && file.isFile()){
    // 파일이 있다면 파일 이름 출력
					System.out.println("\t 파일 이름 = " + file.getName());
					String fileNameList = "\t 파일 이름 = " + file.getName();
					request.setAttribute("fileName", fileNameList);
					
				}else if(file.exists() && file.isDirectory()){
					System.out.println("디렉토리 이름 = " + file.getName());
    // 서브디렉토리가 존재하면 재귀적 방법으로 다시 탐색
					subDirList(file.getCanonicalPath().toString(),request); 
				} else if(!file.exists()) {
					System.out.println("비었음");
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}


}
