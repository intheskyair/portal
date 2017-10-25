package com.zqkj.bsm.client.action;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

import javax.imageio.ImageIO;

import sun.awt.image.ToolkitImage;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.zqkj.bsm.action.BaseAction;
import com.zqkj.bsm.system.action.SysPara;

public class AutoUpdateAction extends BaseAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8759024612854709224L;

	
    
    public void getVersion() 
    {

    		response.addHeader("Cache-Control", "no-cache");
    		response.addHeader("Pragma", "no-cache");
    		response.addHeader("Expires", "-1");
    		response.setContentType("application/json; charset=UTF-8");
    		try {
    				response.getWriter().print(SysPara.CLIENT_VESION);

    		} catch (Exception e) 
    		{
    			log.error("查询出错！", e);
    			response.setContentType("text/plain;charset=UTF-8");
    			response.setStatus(300);
    			response.setHeader("Error-Json",
    					"{\"code\":\"Exception\",\"msg\":\"系统内部错误！\"}");
    		}
            
    }	
    
    
    @SuppressWarnings("unused")
	private String createXml(ArrayList<Map<String, Object>> list,String version)
    {
    	StringBuffer buffer = new StringBuffer();
    	buffer.append("<root version='");
    	buffer.append(version);
    	buffer.append("'>");
    	StringBuffer buffer_file = new StringBuffer();
    	buffer_file.append("<files>");
    	StringBuffer buffer_plugins = new StringBuffer();
    	buffer_plugins.append("<plugins>");
    	StringBuffer buffer_regs = new StringBuffer();
    	buffer_regs.append("<regs>");
    	StringBuffer buffer_bats= new StringBuffer();
    	buffer_bats.append("<bats>");
    	for(Map<String, Object> map:list)
    	{
    		int type = ((BigDecimal)map.get("TYPE")).intValue();
    		String path = String.valueOf(map.get("PATH"));
    		String name = String.valueOf(map.get("NAME"));
    		//'0:插件1:注册表2:普通文件 3:bat类型文件
    		if(type==0)
    		{
    			int offset = path.indexOf("plugins");
    			int offend = path.lastIndexOf(name);
    			if(offset> -1)
    			{
    				path = path.substring(offset+7, offend);
    			}
    			buffer_plugins.append("<plugin name='");
    			buffer_plugins.append(name);
    			buffer_plugins.append("' path='");
    			buffer_plugins.append(path);
    			buffer_plugins.append("' hashCode='");
    			buffer_plugins.append(map.get("HASH_CODE"));
    			buffer_plugins.append("' ></plugin>");
    		}
    		else if(type==1)
    		{
    			buffer_regs.append("<reg name='");
    			buffer_regs.append(name);
    			buffer_regs.append("' />");
    		}
    		else if(type==2)
    		{
    			int offset = path.indexOf("files");
    			int offend = path.lastIndexOf(name);
    			if(offset> -1)
    			{
    				path = path.substring(offset+5, offend);
    			}
    			buffer_file.append("<file name='");
    			buffer_file.append(name);
    			buffer_file.append("' path='");
    			buffer_file.append(path);
    			buffer_file.append("' hashCode='");
    			buffer_file.append(map.get("HASH_CODE"));
    			buffer_file.append("' ></file>");
    		}
    		else if(type==3)
    		{
    			buffer_bats.append("<bat name='");
    			buffer_bats.append(name);
    			buffer_bats.append("' />");
    		}
    	}
    	buffer_file.append("</files>");
    	buffer_plugins.append("</plugins>");
    	buffer_regs.append("</regs>");
    	buffer_bats.append("</bats>");
    	buffer.append(buffer_file);
    	buffer.append(buffer_plugins);
    	buffer.append(buffer_regs);
    	buffer.append(buffer_bats);
    	buffer.append("</root>");
    	return buffer.toString();
    }
    
    
    
    public void ImagePreview() throws IOException
    {
//    	String job_id = request.getParameter("job_id").trim();
//    	String trainsId = request.getParameter("trainsId").trim();
//    	String job_type = CommonUtil.nullToDefault(request.getParameter("job_type"), "");//区分线上线下 2为线下
//    	String subjob_id = request.getParameter("subjob_id").trim();
//    	Admin admin = (Admin)session.getAttribute("admin");
//    	long last_time = admin.getOper_time();
//    	String last_subjob_id = admin.getSubjob_id();
//		int fid =  RenderDao.getInstance().getFidByJobID(trainsId,job_type);
//	    Map<String, Object> fram_map =  Multi_Fram_Tab.getInstance().getFramInfo(fid);
//	    if(fram_map!=null)
//	    {
//	    	String ip = String.valueOf(fram_map.get("CLUSTER_IP"));
//	    	String port = String.valueOf(fram_map.get("CLUSTER_PORT"));
//	    	ClusterResponse res = null;
//	    	boolean zx_bz = false;
//	    	//不同的子任务，需要立即给查看
//	    	if(!last_subjob_id.equals(subjob_id))
//	    	{
//	    		res = HttpSender.getPicByte(job_id,subjob_id,ip,port);
//	    		zx_bz = true;
//	    	}
//	    	//相同的子任务，需要判断时间间隔
//	    	else
//	    	{
//	    		long current_time = System.currentTimeMillis();
//	    		//间隔超过20秒
//	    		if((current_time-last_time)>20*1000)
//	    		{
//	    			res = HttpSender.getPicByte(job_id,subjob_id,ip,port);
//	    			zx_bz = true;
//	    		}
//	    	}
//	    	//byte[] bs = null;
//	    	InputStream inputStream = null;
//	    	ByteArrayOutputStream op = null;
//	    	BASE64Encoder encoder = new BASE64Encoder();
//	    	String imgpath ="";
//	    	try
//	    	{
//	    		if(zx_bz)
//	    		{
//	    			if(res.getResponseCode()!=200)
//	    			{
//	    				log.info("子任务图片截屏预览失败job_id="+job_id+",subjob_id="+subjob_id+"原因集群接口返回失败！");
//	    				imgpath = "data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==";
//	    			}
//	    			else
//	    			{
//	    				
//	    				byte[] ma = res.getBody_by();
//	    				//	     		int width = 1024;
//	    				//	    		int height = 768;
//	    				//	    		inputStream = new ByteArrayInputStream(ma);
//	    				//				op = ResizeImg(inputStream, width, height);				
//	    				//				bs = op.toByteArray();				
//	    				imgpath = "data:image/jpeg;base64," ;
//	    				//String img = encoder.encode(bs);	
//	    				String img = encoder.encode(ma);	
//	    				imgpath += img;
//	    				admin.setOper_time(System.currentTimeMillis());
//	    				admin.setSubjob_id(subjob_id);
//	    			}
//	    		}
//	    		else
//	    		{
//	    			log.info("子任务图片截屏刷新太频繁job_id="+job_id+",subjob_id="+subjob_id);
//	    			imgpath = "data:image/jpeg;base64," ;
//	    			String img = encoder.encode(ConfigServlet.gj_pic);	
//	    			imgpath += img;
//	    		}
//	    	}
//	    	catch (Exception e) 
//	    	{
//	    		e.printStackTrace();
//	    	}
//	    	finally
//	    	{
//	    		if(inputStream != null)
//	    		{
//	    			inputStream.close();
//	    		}
//	    		if(op!= null)
//	    		{
//	    			op.close();
//	    		}
//	    	}
//	    	writeJson(response, imgpath);
//	    }else{
//	    	log.info("农场信息为空，fram_map="+fram_map+",subjob_id="+subjob_id);
//	    }
     }
    
    
    
	//只对jpg缩放
	private ByteArrayOutputStream ResizeImg(InputStream inputStream,int maxWidth,int maxHeight) throws IOException{
		
		BufferedImage bufferimage = ImageIO.read(inputStream);
		int curWidth = bufferimage.getWidth();
		int curHeight = bufferimage.getHeight();
		
//		double desiredRatio = Math.min((double)(maxWidth)/(double)curWidth,(double)maxHeight/(double)curHeight);
//		curWidth =  (int) (curWidth*desiredRatio);
//		curHeight = (int)(curHeight*desiredRatio);
		double ratio = 0; 
		Image itemp = bufferimage.getScaledInstance(maxWidth, maxHeight, bufferimage.SCALE_SMOOTH);      
        // 计算比例      
        if ((bufferimage.getHeight() > maxHeight) || (bufferimage.getWidth() > maxWidth)) {      
            if (bufferimage.getHeight() > bufferimage.getWidth()) {      
                ratio = (new Integer(maxHeight)).doubleValue() / bufferimage.getHeight();      
            } else {      
                ratio = (new Integer(maxWidth)).doubleValue() / bufferimage.getWidth();      
            }      
            AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);      
            itemp = op.filter(bufferimage, null);      
        }
        
		
		BufferedImage bufftmp = new BufferedImage(curWidth,curHeight,BufferedImage.TYPE_INT_RGB);
		Graphics2D g2D = bufftmp.createGraphics();
		
		g2D.setColor(Color.white);
		g2D.fillRect(0, 0, curWidth, curHeight);
//		g2D.drawImage(bufferimage, null, 0, 0);
		
		if (maxWidth == itemp.getWidth(null))      
			g2D.drawImage(itemp, 0, (maxHeight - itemp.getHeight(null)) / 2, itemp.getWidth(null), itemp.getHeight(null), Color.white, null);      
        else     
        	g2D.drawImage(itemp, (maxWidth - itemp.getWidth(null)) / 2, 0, itemp.getWidth(null), itemp.getHeight(null), Color.white, null);      
		g2D.dispose();      
//        itemp = bufftmp;		
		ByteArrayOutputStream op = new ByteArrayOutputStream();
		
		JPEGImageEncoder imageEncoder = JPEGCodec.createJPEGEncoder(op);
		
		//imageEncoder.encode((BufferedImage)itemp);
		imageEncoder.encode(((ToolkitImage)itemp).getBufferedImage());
		return op;
	}
    
}
