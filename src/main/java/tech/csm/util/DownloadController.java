package tech.csm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class DownloadController {

	@Value("${upload.dir}")
	private String filePath;
	
	@GetMapping("/download")
	public void downloadfile( @RequestParam("idproofname") String fileName, HttpServletResponse resp) throws IOException {
		
        File file = new File(filePath+fileName);
        resp.setContentType("application/octet-stream");
        resp.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
        FileInputStream inputStream = new FileInputStream(file);
        OutputStream outputStream = resp.getOutputStream();
        byte[] data=inputStream.readAllBytes();
        outputStream.write(data);
        
//        byte[] buffer = new byte[4096];
//        int bytesRead;
//        while ((bytesRead = inputStream.read(buffer)) != -1) {
//            outputStream.write(buffer, 0, bytesRead);
//        }
        inputStream.close();
        outputStream.close();
	}
	
	
}
