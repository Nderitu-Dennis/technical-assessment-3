/*
 * package tech.csm.util;
 * 
 * import java.io.File; import java.io.FileOutputStream; import
 * java.io.InputStream; import java.io.OutputStream;
 * 
 * import org.springframework.web.multipart.MultipartFile;
 * 
 * public class FileUploadUtil {
 * 
 * public static String uploadFile(MultipartFile m, String uploadFilePath) {
 * File fn=new File(uploadFilePath); if(!fn.exists()) fn.mkdir();
 * 
 * try(InputStream is=m.getInputStream()){ File f=new
 * File(uploadFilePath+m.getOriginalFilename()); OutputStream outputStream=new
 * FileOutputStream(f); byte[] buffer = new byte[4096]; int bytesRead; while
 * ((bytesRead = is.read(buffer)) != -1) { outputStream.write(buffer, 0,
 * bytesRead); }
 * 
 * is.close(); outputStream.close();
 * 
 * 
 * }catch(Exception e) { e.printStackTrace(); }
 * 
 * return m.getOriginalFilename(); }
 * 
 * 
 * }
 */