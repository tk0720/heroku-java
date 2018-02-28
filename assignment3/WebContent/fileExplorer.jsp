<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#file {
		color: black;
		text-decoration: none;
	}
	#dir {
		color: teal;
		text-decoration: none;
	}
</style>
<script>
function dir_click() {
	alert("디렉토리 출력");
	<%
	%>
}
</script>
</head>
<body>
<a>탐색기당</a>
<input type="text" name="rootDir" value="<%=request.getAttribute("path")%>" readonly="readonly"><br>
<table border="1">
	<tr>
		<th>디렉토리</th>
		<th>파일</th>
	</tr>
<%
String[] fileList = (String[])request.getAttribute("content");
String path = (String)request.getAttribute("path");
String fileName = (String)request.getAttribute("fileName");

for(int i=0; i<fileList.length; i++) {
	File file = new File(path, fileList[i]);
	
	if(file.isFile()) {
%>
	<tr>
		<td><a href="#" id="file"><%=file.getName() %> : 파일</a></td>
<%
	} else if(file.isDirectory()) {
		String dir = file.getName();
%>
		<td><a href="#" id="dir" onclick="dir_click();"><%=dir %> : 디렉토리</a></td>
		
		<td>&nbsp;&nbsp;&nbsp;&nbsp; 여따가 하위 파일 출력해야 하는데??<%=fileName %></td>
<%
	} // 디렉토리 if문 end
%>
	</tr>
<%
} // 배열 end
%>

</table>
</body>
</html>