package com.ucap.cloud_web.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.publics.util.utils.StringUtils;


public class OssUtil {
	private static String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
	//变量 accessKeyId 与 accessKeySecret 是由系统分配给用户的，称为ID对，用于标识用户，为访问OSS做签名验证。
	private static String accessKeyId = "LTAIvf8Ie2oo4yv4";
	private static String accessKeySecret = "uXCDpD6VAi5d7vmrovZuLC7ao681WM";
	protected static HttpServletResponse response;
	
	/** 
     * 获取阿里云OSS客户端对象 
     * */  
    public static final OSSClient getOSSClient(){  
        return new OSSClient(endpoint,accessKeyId, accessKeySecret);  
    }  
    /** 
     * 新建Bucket  --Bucket权限:私有 
     * @param bucketName bucket名称 
     * Bucket的命名有以下规范：
		    只能包括小写字母，数字，短横线（-）
		    必须以小写字母或者数字开头
		    长度必须在3-63字节之间
     * @return true 新建Bucket成功 
     * */  
    public static final boolean createBucket(OSSClient client, String bucketName){  
        Bucket bucket = client.createBucket(bucketName);   
        return bucketName.equals(bucket.getName());  
    }
    /** 
     * 删除Bucket  
     * @param bucketName bucket名称 
     * */  
    public static final void deleteBucket(OSSClient client, String bucketName){  
        client.deleteBucket(bucketName);   
    }  
    /** 
     * 判断Bucket  是否存在
     * @param bucketName bucket名称 
     * @param client OSS客户端 
     * */
    public static final boolean isBucketExist(OSSClient ossClient,String bucketName){
		boolean exists = ossClient.doesBucketExist(bucketName);
		return exists;
	}
	/** 
     * 向阿里云的OSS存储中存储文件  --同名将会覆盖
     * @param client OSS客户端 
     * @param file 文件流
     * @param bucketName bucket名称 
     * @param diskName 上传文件的目录  --object下文件的路径  格式为:123/
     * @return String 唯一MD5数字签名 
     * */  
	public static  String putObject(OSSClient ossClient,String bucketName, File file, String diskName) {
		String resultStr ="";
		 try {
			 if(StringUtils.isEmpty(diskName)){
		      	diskName = "";
		     }
			// 获取指定文件的输入流
			InputStream content = new FileInputStream(file);
			String fileName = file.getName();
		    ObjectMetadata meta = new ObjectMetadata();  // 创建上传Object的          Metadata对object的描述
		    meta.setCacheControl("no-cache");      // 被下载时网页的缓存行为
		    meta.setContentLength(file.length()); //识别上传Object的大小。
		    meta.setContentEncoding("utf-8"); 
		    // 上传Object.
		    PutObjectResult result = ossClient.putObject(bucketName, diskName+fileName, content, meta);
		    resultStr =result.getETag();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return resultStr;
	}
	/**  
     * 根据key获取OSS服务器上的文件流 
     * @param client OSS客户端 
     * @param bucketName bucket名称 
     * @param diskName 服务器文件路径 格式为:123/
     * @param key 文件名 
     */    
     public static InputStream getOSSInputStream(OSSClient client, String bucketName, String diskName, String key){ 
    	if(StringUtils.isEmpty(diskName)){
    		diskName = "";
    	}
    	if(StringUtils.isEmpty(key)){
    		key = "";
    	}
        OSSObject ossObj = client.getObject(bucketName, diskName + key);  
        return ossObj.getObjectContent();     
     }     
     
     /**  
      * 下载Object到本地文件 
      * @param client OSS客户端 
      * @param bucketName bucket名称 
      * @param diskName 服务器的文件路径  格式为:123/
      * @param key 文件名 
      * @param filePath 下载地址的路径 如"D:/DTLDownLoads/123.jpeg"
      */  
     public static void dowLoadObject(OSSClient ossClient,String bucketName,String diskName,String key,String filePath){
    	 try {
    		GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, diskName+key);
    	 		// 下载Object到文件
    	 	ObjectMetadata objectMetadata = ossClient.getObject(getObjectRequest, new File(filePath));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
 		
 	 }
     /**  
      * 根据文件名直接输出文件
      * @param client OSS客户端 
      * @param bucketName bucket名称 
      * @param diskName 服务器的文件路径 格式为:123/
      * @param key 文件名 
      */  
     public static void dowLoadIns(OSSClient client, String bucketName, String diskName, String key,HttpServletResponse response){
    	 if(StringUtils.isEmpty(diskName)){
     		diskName = "";
     	}
     	if(StringUtils.isEmpty(key)){
     		key = "";
     	}
         OSSObject ossObj = client.getObject(bucketName, diskName + key);  
         InputStream ins = ossObj.getObjectContent();
    	 BufferedInputStream bins = new BufferedInputStream(ins);// 放到缓冲流里面
         OutputStream outs = null;
         try {
 			 outs = response.getOutputStream();
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
         BufferedOutputStream bouts = new BufferedOutputStream(outs);
         response.setCharacterEncoding("utf-8");
 		response.setContentType("application/x-download");// 设置response内容的类型
 		response.setHeader("Content-disposition", "attachment;filename="
 				+ key.replaceAll(" ", "%20")); // 设置头部信息
 		
 		int bytesRead = 0;
 		byte[] buffer = new byte[8192];
 		// 开始向网络传输文件流
 		try {
 			while ((bytesRead = bins.read(buffer, 0, 8192)) != -1) {
 				bouts.write(buffer, 0, bytesRead);
 			}
 			bouts.flush();
 			ins.close();
 			bins.close();
 			outs.close();
 			bouts.close();
 		} catch (IOException e) {
 			e.printStackTrace();
 		}finally {

 		}
     }
     
     
     
     /**  
      * 根据key删除OSS服务器上的文件  
      * @param client OSS客户端 
      * @param bucketName bucket名称 
      * @param diskName 文件路径  格式为:123/
      * @param key 文件名 
      */    
      public static void deleteFile(OSSClient client, String bucketName, String diskName, String key){  
    	if(StringUtils.isEmpty(diskName)){
      		diskName = "";
      	}
      	if(StringUtils.isEmpty(key)){
      		key = "";
      	}
          client.deleteObject(bucketName, diskName + key);   
      }    
      /**  
       * 根据key 获取文件url链接 
       * @param client OSS客户端 
       * @param bucketName bucket名称 
       * @param diskName 文件路径 格式为:123/
       * @param key 文件名 
       * 
       */   
      public static String getUrl(OSSClient client,String bucketName,String diskName,String key) {
    	if(StringUtils.isEmpty(diskName)){
        	diskName = "";
        }
        if(StringUtils.isEmpty(key)){
        	key = "";
        }
  	    // 设置URL过期时间为10年  3600l* 1000*24*365*10
  	    Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
  	    // 生成URL
  	    URL url = client.generatePresignedUrl(bucketName, diskName + key, expiration);
  	    if (url != null) {
  	      return url.toString();
  	    }
  	    return null;
  	  }
      
      /**
  	 * 列出bucket中所有object信息
  	 * 
  	 * @param ossClient
  	 * @param bucketName
  	 */
  	public static List<OSSObjectSummary> listObjects(OSSClient ossClient, String bucketName) {
  		ObjectListing objectListing = ossClient.listObjects(bucketName);
  		return objectListing.getObjectSummaries();
  	}
  	
}
