package net.tuxun.customer.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tuxun.core.util.PropertyUtil;

import org.apache.commons.io.IOUtils;

/**
 * 虚拟目录过虑器
 * 主要针对附件下载，因为附件可能放在项目之外的目录中。
 * @author Administrator
 *
 */
public class VirtualDirectoryFilter implements Filter {

  

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
      HttpServletRequest req=(HttpServletRequest)request;
      HttpServletResponse res=(HttpServletResponse)response;
      
      String url=req.getRequestURI();
      File file=new File(PropertyUtil.get("rootPath"),url);
      if(!file.isFile()){
        return;
      }
      InputStream in=null;
      OutputStream out=null;
      try{
        response.setContentLength((int)file.length());
        if(!canOpen(file.getName())){
          // 下载标识
          res.setHeader("Content-disposition", "attachment; filename="  
              + new String(file.getName().getBytes("utf-8"), "ISO8859-1"));
        }
        in=new FileInputStream(file);
        out=response.getOutputStream();
        IOUtils.copy(in,out);
      }catch(Exception e){
         e.printStackTrace();
      }finally{
        if(in!=null){
          IOUtils.closeQuietly(in);
        }
      }
      
  }
  
  /**
   * 是否可以直接打开
   * @param filename
   * @return
   */
  private boolean canOpen(String filename){
    String ext=filename.substring(filename.lastIndexOf(".")+1);
    if(ext.equalsIgnoreCase("jpg") || ext.equalsIgnoreCase("png") 
        || ext.equalsIgnoreCase("gif") || ext.equalsIgnoreCase("jpeg")){
      return true;
    }
    return false;
  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    
  }

  @Override
  public void destroy() {
    
  }

 

}
