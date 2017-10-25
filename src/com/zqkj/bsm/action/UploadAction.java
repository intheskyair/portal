package com.zqkj.bsm.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;
import java.util.Random;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ExceptionMapping;
import org.apache.struts2.convention.annotation.ExceptionMappings;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.dispatcher.multipart.MultiPartRequest;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.json.JSONObject;
import org.json.simple.JSONValue;

import com.zqkj.bsm.downmodel.beanfactory.ModelFactory;
import com.zqkj.bsm.system.action.SysPara;
import com.zqkj.bsm.system.action.util.StringHelper;
import com.zqkj.bsm.util.CommonUtil;
import com.zqkj.bsm.util.ExcelReader;
import com.zqkj.bsm.util.ExcelWriter;
import com.zqkj.bsm.util.IOCloseHelper;

/**
 * ajax文件上传
 * 
 * @author 0242
 * @date Jul 17, 2014 使用注解来配置Action
 * 
 */
@ParentPackage("struts2")
@InterceptorRef("secureStack")
@Namespace("")
@Results({@Result(name = "error", location = "/failure.jsp"), @Result(name = "logout", location = "/index.jsp")})
@ExceptionMappings({@ExceptionMapping(exception = "java.lange.RuntimeException", result = "error")})
@Action(value = "upload")
public class UploadAction extends ClientBaseAction
{
    
    private File plann_file;
    
    private String fileFileName;
    
    private String fileFileContentType;
    
    public File getPlann_file()
    {
        return plann_file;
    }
    
    public void setPlann_file(File plann_file)
    {
        this.plann_file = plann_file;
    }
    
    public String getFileFileName()
    {
        return fileFileName;
    }
    
    public void setFileFileName(String fileFileName)
    {
        this.fileFileName = fileFileName;
    }
    
    public String getFileFileContentType()
    {
        return fileFileContentType;
    }
    
    public void setFileFileContentType(String fileFileContentType)
    {
        this.fileFileContentType = fileFileContentType;
    }
    
    public String getFilePath()
    {
        return filePath;
    }
    
    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }
    
    public int getMess_state()
    {
        return mess_state;
    }
    
    public void setMess_state(int mess_state)
    {
        this.mess_state = mess_state;
    }
    
    private int mess_state = 1; // 0格式错误 1成功(文件路径) 2失败
    
    private String filePath;
    
    /**
	 * 
	 */
    private static final long serialVersionUID = -5271032028180889470L;
    
    @SuppressWarnings({"deprecation", "rawtypes"})
    public void upl()
        throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
    {
        String id = request.getParameter("id");
        String show = request.getParameter("show");
        JSONObject result = new JSONObject();
        String path = SysPara.fileSavaPath;
        File file = new File(path); // 判断文件夹是否存在,如果不存在则创建文件夹
        if (!file.exists())
        {
            file.mkdir();
        }
        File f = null;
        String[] fileSuffix = new String[] {".exe"};// 禁止文件
        Field requestField = request.getClass().getDeclaredField("multi");
        if (requestField != null)
        {
            requestField.setAccessible(true);
            // org.apache.struts2.dispatcher.multipart.JakartaMultiPartRequest
            MultiPartRequest multiPartRequest = (MultiPartRequest)requestField.get(request);
            Enumeration files = multiPartRequest.getFileParameterNames();
            while (files.hasMoreElements())
            {
                String fileName = (String)files.nextElement();
                if (fileName != null && !fileName.equals(""))
                {
                    String[] values = multiPartRequest.getFileNames(fileName);
                    File[] fs = multiPartRequest.getFile(fileName);
                    for (int i = 0, len = values.length; i < len; i++)
                    {
                        fileFileName = values[i];
                        f = fs[i];
                    }
                }
                
            }
        }
        try
        {
            // File f = this.getPlann_file();
            // 判断文件格式
            for (int i = 0; i < fileSuffix.length; i++)
            {
                if (fileFileName.endsWith(fileSuffix[i]))
                {
                    this.mess_state = 0;
                    String message = "对不起,你上传的文件格式不允许!!!";
                    result.put("message", message);
                    result.put("mess_state", this.mess_state);
                }
            }
            if (this.mess_state != 0)
            {
                String fileExt = fileFileName.substring(fileFileName.lastIndexOf(".") + 1).toLowerCase();
                String filename = fileFileName.substring(0, fileFileName.lastIndexOf(".")).toLowerCase();
                log.info("filename=" + filename);
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                String newFileName =
                    filename + df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
                try
                {
                    SaveFileFormInputStream(new FileInputStream(f), path, newFileName);
                    this.mess_state = 1;
                    String message = "上传成功";
                    String rs_path = path + "\\" + newFileName;
                    result.put("message", message);
                    result.put("rs_path", rs_path);
                    result.put("newFileName", newFileName);
                    result.put("mess_state", this.mess_state);
                    result.put("id", id);
                    result.put("show", show);
                    
                }
                catch (Exception e)
                {
                    log.error(e.getMessage() + e.getCause());
                    this.mess_state = 2;
                    String message = "对不起,文件上传失败了!!!!";
                    result.put("message", message);
                    result.put("mess_state", this.mess_state);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            this.mess_state = 2;
            String message = "对不起,文件上传失败了!!!!";
            result.put("message", message);
            result.put("mess_state", this.mess_state);
        }
        writeJson(response, result);
        String json = JSONValue.toJSONString(result);
        PrintWriter pw = null;
        try
        {
            pw = response.getWriter();
            pw.write("<script>window.parent.get_upfile_result(" + json + ")</script>");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            pw.close();
        }
        
    }
    
    public void download()
        throws IOException
    {
        String path = request.getParameter("path") != null ? request.getParameter("path") : "";
        // System.out.println("解密前："+path);
        path = URLDecoder.decode(path, "utf-8");
        // System.out.println("解密后："+path);
        File packageZip = new File(path);
        log.info("开始下载订单的源文件path=" + path);
        // 渲染的源文件名
        String fileName = "";
        long file_length = 0;
        if (packageZip.exists())
        {
            // 文件的大小
            file_length = packageZip.length();
            fileName = packageZip.getName();
            // 将获取的数据当成文件下载
            getSrcStrem(packageZip, fileName, file_length);
        }else{
        	response.setContentType("text/html;charset=UTF-8"); 
        	PrintWriter out = response.getWriter();
        	out.write("<script>alert('文件不存在')</script>");
        	out.flush();
        	out.close();
        }
    }
    
    private void getSrcStrem(File path, String fileName, long file_length)
        throws IOException
    {
        // 清空response
        response.reset();
        
        // 设置response的头部
        response.addHeader("Content-Disposition", "attachment;filename="
            + new String(fileName.getBytes("gb2312"), "ISO8859-1"));
        response.addHeader("Content-Length", "" + file_length);
        response.setContentType("appication/octet-stream");
        BufferedOutputStream output = null;
        BufferedInputStream bfs = null;
        try
        {
            bfs = new BufferedInputStream(new FileInputStream(path));
            // 新建文件输入输出流对象
            output = new BufferedOutputStream(response.getOutputStream());
            // 设置每次写入缓存大小
            byte[] b = new byte[2048];
            int bytesindex;
            while (-1 != (bytesindex = bfs.read(b, 0, b.length)))
            {
                output.write(b, 0, bytesindex);
            }
            output.flush();
        }
        catch (IOException e)
        {
            // e.printStackTrace();
            log.info("下载发生异常" + e.getMessage());
        }
        finally
        {
            if (output != null)
            {
                try
                {
                    output.close();
                }
                catch (IOException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                output = null;
            }
            if (bfs != null)
            {
                try
                {
                    bfs.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                bfs = null;
            }
            
        }
        response.flushBuffer();// 强行将响应缓存中的内容发送到目的地
    }
    
    /**
     * 
     * @Title: importExcel
     * @Description: TODO(根据模板导入对应表的数据)
     * @param @throws Exception 设定文件
     * @return void 返回类型
     * @author wangwj
     */
    public void importExcel()
        throws Exception
    {
        String tableName = CommonUtil.nullToDefault(request.getParameter("nameType"), "");
        String suffix = CommonUtil.nullToDefault(request.getParameter("suffix"), "");
        if (!"".equals(tableName) && !"".equals(suffix))
        {
            MultiPartRequestWrapper multi = (MultiPartRequestWrapper)request;
            Enumeration<String> en = multi.getFileParameterNames();
            boolean flag = true;
            InputStream in = null;
            try
            {
                while (en.hasMoreElements())
                {
                    String fileName = en.nextElement();
                    File[] fs = multi.getFiles(fileName);
                    for (File f : fs)
                    {
                        in = new FileInputStream(f);
                        ExcelReader er = new ExcelReader();// excel模板导入帮助类
                        Map<String, String> mf = ModelFactory.getModelBean(tableName);
                        if (!er.read(suffix, in, mf.get(ModelFactory.stringFirstUpper(tableName))))
                        {
                            flag = false;
                            break;
                        }
                    }
                    if (!flag)
                    {
                        break;
                    }
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                writeJson_Str(response, flag ? "{\"result\":\"00\"}" : "{\"result\":\"09\"}");
                IOCloseHelper.closeIO(in, null);
            }
        }
        else
            writeJson_Str(response, "{\"result\":\"09\"}");
    }
    
    /**
     * 
     * @Title: exportExcelModelForTable
     * @Description: TODO(导出批量导入的excel模板)
     * @param 设定文件
     * @return void 返回类型
     * @author wangwj
     */
    public void exportExcelModelForTable()
    {
        String tableName = CommonUtil.nullToDefault(request.getParameter("nameType"), "");
        String suffix = CommonUtil.nullToDefault(request.getParameter("suffix"), "");
        Map<String, String> mf = ModelFactory.getModelBean(tableName);
        if (!"".equals(tableName) && !"".equals(suffix))
        {
            OutputStream out = null;
            try
            {
                // 清空输出流
                response.reset();
                // 设置响应头和下载保存的文件名
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
                response.setHeader("content-disposition",
                    "attachment;filename="
                        + StringHelper.processFileName(request,
                            mf.get(mf.get(ModelFactory.stringFirstUpper(tableName))) + "-" + df.format(new Date())
                                + "." + suffix.toLowerCase()));
                // 定义输出类型
                response.setContentType("APPLICATION/msexcel");
                out = response.getOutputStream();
                ExcelWriter write = new ExcelWriter();
                write.write(suffix, out, tableName);
                out.flush();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                IOCloseHelper.closeIO(null, out);
            }
        }
    }
    
    /**
     * 远程下载别人的文件
     * 
     * @throws IOException
     */
    public void download_yc()
        throws IOException
    {
        String path = request.getParameter("path") != null ? request.getParameter("path") : "";
        System.out.println("解密前：" + path);
        path = URLDecoder.decode(path, "utf-8");
        System.out.println("解密后：" + path);
        int dex = path.lastIndexOf("/") + 1;
        String fileName = path.substring(dex);
        path = SysPara.szxc + path;
        // 构造URL
        URL url = new URL(path);
        // 打开连接
        URLConnection con = url.openConnection();
        // 设置请求超时为5s
        con.setConnectTimeout(5 * 1000);
        // 输入流
        InputStream is = con.getInputStream();
        // 将获取的数据当成文件下载
        getSrcStrem(is, fileName);
    }
    
    /**
     * 从输入流中获取数据
     * 
     * @param inStream 输入流
     * @return
     * @throws Exception
     */
    // public static byte[] readInputStream(InputStream inStream) throws Exception{
    // ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    // byte[] buffer = new byte[1024];
    // int len = 0;
    // while( (len=inStream.read(buffer)) != -1 ){
    // outStream.write(buffer, 0, len);
    // }
    // inStream.close();
    // return outStream.toByteArray();
    // }
    // }
    
    private void getSrcStrem(InputStream inStream, String fileName)
        throws IOException
    {
        // 清空response
        response.reset();
        
        // 设置response的头部
        response.addHeader("Content-Disposition", "attachment;filename="
            + new String(fileName.getBytes("gb2312"), "ISO8859-1"));
        // response.addHeader("Content-Length", "" + file_length);
        response.setContentType("appication/octet-stream");
        BufferedOutputStream output = null;
        BufferedInputStream bfs = null;
        try
        {
            bfs = new BufferedInputStream(inStream);
            // 新建文件输入输出流对象
            output = new BufferedOutputStream(response.getOutputStream());
            // 设置每次写入缓存大小
            byte[] b = new byte[2048];
            int bytesindex;
            while (-1 != (bytesindex = bfs.read(b, 0, b.length)))
            {
                output.write(b, 0, bytesindex);
            }
            output.flush();
        }
        catch (IOException e)
        {
            // e.printStackTrace();
            log.info("下载发生异常" + e.getMessage());
        }
        finally
        {
            if (output != null)
            {
                try
                {
                    output.close();
                }
                catch (IOException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                output = null;
            }
            if (bfs != null)
            {
                try
                {
                    bfs.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                bfs = null;
            }
            
        }
        response.flushBuffer();// 强行将响应缓存中的内容发送到目的地
    }
    
}
