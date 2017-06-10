package com.ucap.cloud_web.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.publics.util.utils.DateUtils;
import com.publics.util.utils.StringUtils;

/**
 * 文件工具类 FileUtils
 * 
 * @author LXY
 * @version 1.0
 */
public class FileUtils {
	
	protected static HttpServletResponse response;

	/**
	 * 从文件路径得到文件名。
	 * 
	 * @param filePath
	 *            文件的路径，可以是相对路径也可以是绝对路径
	 * @return 对应的文件名
	 * @since 0.4
	 */
	public static String getFileName(String filePath) {
		File file = new File(filePath);
		return file.getName();
	}
	/**
	 * @Description: 文件是否存在
	 * @author: masl@ucap.com.cn --- 2016-7-22下午2:08:59
	 * @param @param path : 文件路径 
	 * @return void
	 * @throws
	 */
	public static boolean fileExists(String path) {
		if (StringUtils.isEmpty(path))
			return false;
		try {
			File file = new File(path);
			return file.exists();
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * 从文件名得到文件绝对路径。
	 * 
	 * @param fileName
	 *            文件名
	 * @return 对应的文件路径
	 * @since 0.4
	 */
	public static String getFilePath(String fileName) {
		File file = new File(fileName);
		return file.getAbsolutePath();
	}

	public static String trimBlank(String str){
		Pattern p = Pattern.compile("\\s*|\t|\r|\n");
        java.util.regex.Matcher m = p.matcher(str);
        return m.replaceAll("");
	}
	
	/**
	 * 获取文件名称
	 * @param aliasName
	 * @param fileUrl
	 * @return
	 */
	public static String getAliasName(String aliasName,String fileUrl){
		if (StringUtils.isNotEmpty(aliasName)) {
			// 真实文件后缀，如excel==>xlsx
			String suffixReal = getHeadOrEndStr(fileUrl,
					".", true);
			// 修改名字后文件的后缀
//			String suffixAliasName = FileUtils.getHeadOrEndStr(
//					aliasName, ".", true);
			// 修改名字后文件的前缀
			String prefixAliaName = FileUtils.getHeadOrEndStr(
					aliasName, ".", false);
			if(StringUtils.isEmpty(prefixAliaName)){
				prefixAliaName = aliasName;
			}
			
			//重命名后的前缀+“.” +真实文件后缀
			if(StringUtils.isNotEmpty(suffixReal)){
				aliasName =prefixAliaName + "." + suffixReal;
			}
			
			if(StringUtils.isEmpty(aliasName)){
				aliasName = FileUtils.genFileName();
			}

		} else {
			aliasName = FileUtils.genFileName();
		}
		
		return aliasName;
	}
	
	public static void outPutFile(HttpServletResponse response,String filePath, String fileName) {
		InputStream ins = null;
		try {
			ins = new FileInputStream(new File(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			fileName =trimBlank(fileName);
			fileName = new String(fileName.getBytes("gbk"), "iso8859-1");
	      
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		BufferedInputStream bins = new BufferedInputStream(ins);// 放到缓冲流里面
		OutputStream outs = null;
		try {
			outs = response.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}// 获取文件输出IO流
		BufferedOutputStream bouts = new BufferedOutputStream(outs);
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/x-download");// 设置response内容的类型
		response.setHeader("Content-disposition", "attachment;filename="
				+ fileName.replaceAll(" ", "%20")); // 设置头部信息
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
	 * 得到文件名中的父路径部分。 对两种路径分隔符都有效。 不存在时返回""。
	 * 如果文件名是以路径分隔符结尾的则不考虑该分隔符，例如"/path/"返回""。
	 * 
	 * @param fileName
	 *            文件名
	 * @return 父路径，不存在或者已经是父目录时返回""
	 * @since 0.5
	 */
	public static String getPathPart(String fileName) {
		fileName = FileUtils.replaceFileSeparator(fileName);
		int point = getPathLsatIndex(fileName);
		int length = fileName.length();
		if (point == -1) {
			return "";
		} else if (point == length - 1) {
			int secondPoint = getPathLsatIndex(fileName, point - 1);
			if (secondPoint == -1) {
				return "";
			} else {
				return fileName.substring(0, secondPoint);
			}
		} else {
			return fileName.substring(0, point);
		}
	}

	/**
	 * 得到路径分隔符在文件路径中首次出现的位置。 对于DOS或者UNIX风格的分隔符都可以。
	 * 
	 * @param fileName
	 *            文件路径
	 * @return 路径分隔符在路径中首次出现的位置，没有出现时返回-1。
	 * @since 0.5
	 */
	public static int getPathIndex(String fileName) {
		int point = fileName.indexOf('/');
		if (point == -1) {
			point = fileName.indexOf('\\');
		}
		return point;
	}

	/**
	 * 得到路径分隔符在文件路径中指定位置后首次出现的位置。 对于DOS或者UNIX风格的分隔符都可以。
	 * 
	 * @param fileName
	 *            文件路径
	 * @param fromIndex
	 *            开始查找的位置
	 * @return 路径分隔符在路径中指定位置后首次出现的位置，没有出现时返回-1。
	 * @since 0.5
	 */
	public static int getPathIndex(String fileName, int fromIndex) {
		int point = fileName.indexOf('/', fromIndex);
		if (point == -1) {
			point = fileName.indexOf('\\', fromIndex);
		}
		return point;
	}

	/**
	 * 得到路径分隔符在文件路径中最后出现的位置。 对于DOS或者UNIX风格的分隔符都可以。
	 * 
	 * @param fileName
	 *            文件路径
	 * @return 路径分隔符在路径中最后出现的位置，没有出现时返回-1。
	 * @since 0.5
	 */
	public static int getPathLsatIndex(String fileName) {

		int point = fileName.lastIndexOf('/');
		int point1 = fileName.lastIndexOf('\\');
		if (point1 >= point) {
			return point1;
		} else {
			return point;
		}
	}

	/**
	 * 得到路径分隔符在文件路径中指定位置前最后出现的位置。 对于DOS或者UNIX风格的分隔符都可以。
	 * 
	 * @param fileName
	 *            文件路径
	 * @param fromIndex
	 *            开始查找的位置
	 * @return 路径分隔符在路径中指定位置前最后出现的位置，没有出现时返回-1。
	 * @since 0.5
	 */
	public static int getPathLsatIndex(String fileName, int fromIndex) {
		int point = fileName.lastIndexOf("/", fromIndex);

		if (point == -1) {
			point = fileName.lastIndexOf('\\', fromIndex);
		}
		return point;
	}

	public static String replaceFileSeparator(String filename) {
		filename.replace("/", File.separator);
		filename.replace("\\", File.separator);
		return filename;
	}

	/**
	 * 读取文件内容（使用UTF-8编码）
	 * 
	 * @param filePath
	 *            输出文件路径
	 * @return
	 * @throws Exception
	 */
	public static String readFileUTF8(String filePath) throws Exception {
		File file = new File(filePath);
		FileInputStream fis = new FileInputStream(file);
		BufferedReader br = new BufferedReader(new InputStreamReader(fis,
				"UTF-8"));

		String fileContent = "";
		String temp = "";
		while ((temp = br.readLine()) != null) {
			fileContent = fileContent + temp;
		}
		br.close();
		fis.close();
		return fileContent;
	}

	/**
	 * 将文件内容写入文件（使用UTF-8编码）
	 * 
	 * @param content
	 *            文件内容
	 * @param filePath
	 *            输出文件路径
	 * @throws Exception
	 */
	public static void writeFileUTF8(String content, String filePath)
			throws Exception {
		createDir(filePath);
		File file = new File(filePath);
		FileOutputStream fos = new FileOutputStream(file);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos,
				"UTF-8"));
		bw.write(content);
		bw.flush();
		bw.close();
		fos.close();
	}

	/**
	 * 写文件
	 * 
	 * @param outputPath
	 *            输出文件路径
	 * @param is
	 *            输入流
	 * @param isApend
	 *            是否追加
	 * @throws IOException
	 */
	public static void writeFile(InputStream is, String outputPath,
			boolean isApend) throws IOException {
		FileInputStream fis = (FileInputStream) is;
		createDir(outputPath);
		FileOutputStream fos = new FileOutputStream(outputPath, isApend);
		byte[] bs = new byte[1024 * 16];
		int len = -1;
		while ((len = fis.read(bs)) != -1) {
			fos.write(bs, 0, len);
		}
		fos.close();
		fis.close();
	}

	/**
	 * copy文件
	 * 
	 * @param is
	 *            输入流
	 * @param outputPath
	 *            输出文件路径
	 * @throws Exception
	 */
	public static void writeFile(InputStream is, String outputPath)
			throws Exception {
		InputStream bis = null;
		OutputStream bos = null;
		createDir(outputPath);
		bis = new BufferedInputStream(is);
		bos = new BufferedOutputStream(new FileOutputStream(outputPath));
		byte[] bs = new byte[1024 * 10];
		int len = -1;
		while ((len = bis.read(bs)) != -1) {
			bos.write(bs, 0, len);
		}
		bos.flush();
		bis.close();
		bos.close();
	}

	/**
	 * 写文件
	 * 
	 * @param outputPath
	 *            输出文件路径
	 * @param inPath
	 *            输入文件路径
	 * @throws IOException
	 */
	public static void writeFile(String inPath, String outputPath,
			boolean isApend) throws IOException {
		if (new File(inPath).exists()) {
			FileInputStream fis = new FileInputStream(inPath);
			writeFile(fis, outputPath, isApend);
		} else {
			System.out.println("文件copy失败，由于源文件不存在!");
		}
	}

	/**
	 * 将字符串写到文件内
	 * 
	 * @param outputPath
	 *            输出文件路径
	 * @param msg
	 *            字符串
	 * @param isApend
	 *            是否追加
	 * @throws IOException
	 */
	public static void writeContent(String msg, String outputPath,
			boolean isApend) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath,
				isApend));
		bw.write(msg);
		bw.flush();
		bw.close();
	}

	/**
	 * 删除文件夹下的所有内容,包括本文件夹
	 * 
	 * @param path
	 *            删除文件路径
	 * @throws IOException
	 */
	public static void delFileOrDerectory(String path) throws IOException {
		File file = new File(path);
		if (file.exists()) {
			if (file.isDirectory()) {
				File[] files = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					File subFile = files[i];
					delFileOrDerectory(subFile.getAbsolutePath());
				}
				file.delete();
			} else {
				file.delete();
			}
		}
	}

	/**
	 * 如果欲写入的文件所在目录不存在，需先创建
	 * 
	 * @param outputPath
	 *            输出文件路径
	 */
	public static void createDir(String outputPath) {
		File outputFile = new File(outputPath);
		File outputDir = outputFile.getParentFile();
		if (!outputDir.exists()) {
			outputDir.mkdirs();
		}
	}

	/**
	 * 得到UUID文件名
	 * 
	 * @return
	 */
	public static String genFileName() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/**
	 * 获取分割前或者后部分
	 * @param fileName
	 * @param seg 分割符号（.）
	 * @param isLast
	 * @return
	 */
	public static String getHeadOrEndStr(String srcStr,String seg,boolean isLast) {

		try {
			
			if(isLast){
				return srcStr.substring(srcStr.lastIndexOf(seg) + 1);
			}else{
				return srcStr.substring(0,srcStr.lastIndexOf(seg));
			}
			
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 得到上传文件的路径
	 * 
	 * @param type
	 *            文件类型 0抽查，1：正常合同
	 * @return
	 */
	public static String genFilePath(String siteCode) {
	/*	String filePath = File.separator + DateUtils.getTodayYearStr()
				+ File.separator + DateUtils.getTodayMonthStr()
				+ File.separator + siteCode + File.separator;*/
		
		String filePath = "/" + DateUtils.getTodayYearStr()
				+ "/" + DateUtils.getTodayMonthStr()
				+ "/" + siteCode + "/";
		return filePath;
	}
	
	/**
	 * @Description: 返回文件的编码格式
	 * @author sunjiang --- 2016-3-28上午10:59:27     
	 * @param sourceFile  文件
	 * @return
	 */
	public static String getFilecharset(File sourceFile) {
		String charset = "GBK";
		byte[] first3Bytes = new byte[3];
		try {
			boolean checked = false;
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(sourceFile));
			bis.mark(0);
			int read = bis.read(first3Bytes, 0, 3);
			if (read == -1) {
				return charset; // 文件编码为 ANSI
			} else if (first3Bytes[0] == (byte) 0xFF
					&& first3Bytes[1] == (byte) 0xFE) {
				charset = "UTF-16LE"; // 文件编码为 Unicode
				checked = true;
			} else if (first3Bytes[0] == (byte) 0xFE
					&& first3Bytes[1] == (byte) 0xFF) {
				charset = "UTF-16BE"; // 文件编码为 Unicode big endian
				checked = true;
			} else if (first3Bytes[0] == (byte) 0xEF
					&& first3Bytes[1] == (byte) 0xBB
					&& first3Bytes[2] == (byte) 0xBF) {
				charset = "UTF-8"; // 文件编码为 UTF-8
				checked = true;
			}
			bis.reset();
			if (!checked) {
				//int loc = 0;
				while ((read = bis.read()) != -1) {
					//loc++;
					if (read >= 0xF0)
						break;
					if (0x80 <= read && read <= 0xBF) // 单独出现BF以下的，也算是GBK
						break;
					if (0xC0 <= read && read <= 0xDF) {
						read = bis.read();
						if (0x80 <= read && read <= 0xBF) // 双字节 (0xC0 - 0xDF)
							// (0x80
							// - 0xBF),也可能在GB编码内
							continue;
						else
							break;
					} else if (0xE0 <= read && read <= 0xEF) {// 也有可能出错，但是几率较小
						read = bis.read();
						if (0x80 <= read && read <= 0xBF) {
							read = bis.read();
							if (0x80 <= read && read <= 0xBF) {
								charset = "UTF-8";
								break;
							} else
								break;
						} else
							break;
					}
				}
			}
			bis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return charset;
	}
	/**
	 * @Description: 读取文件的每一行  返回一个list
	 * @author sunjiang --- 2016-3-24下午3:53:48     
	 * @param fileName  文件的路径
	 * @return
	 */
	public static List<String> readFileByLines(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		ArrayList<String> list = new ArrayList<String>();
		try {
			// System.out.println("以行为单位读取文件内容，一次读一整行：");
			//reader = new BufferedReader(new FileReader(file));
			InputStreamReader isr = new InputStreamReader(new FileInputStream(file), getFilecharset(file));
			reader = new BufferedReader(isr);
			
			String tempString = null;
			// int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				// System.out.println("line " + line + ": " + tempString);
				if (tempString.length() > 0) {
					// System.out.println("line " + line + ": " + tempString);
					list.add(tempString);
				}
				// line++;
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return list;
	}
	/**
	 * @Description: 去取数组中的空字符串 并删除索引
	 * @author sunjiang --- 2016-3-24下午4:15:34     
	 * @param strings
	 * @return
	 */
	public static Object[] arraychange(String[] strings) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < strings.length; i++) {
			if (!"".equals(strings[i].trim())) {
				list.add(strings[i]);
			}
			//被过滤的行
//			else{
//				System.out.println(strings[i]);
//			}
		}
		return list.toArray();
	}
	
	public static void writeToJson(String filePath, JSONArray object) {
		File file = new File(filePath);
		char[] stack = new char[1024];
		int top = -1;

		String string = object.toString();

		StringBuffer sb = new StringBuffer();
		char[] charArray = string.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			char c = charArray[i];
			if ('{' == c || '[' == c) {
				stack[++top] = c;
				sb.append("\n" + charArray[i] + "\n");
				for (int j = 0; j <= top; j++) {
					sb.append("\t");
				}
				continue;
			}
			if ((i + 1) <= (charArray.length - 1)) {
				char d = charArray[i + 1];
				if ('}' == d || ']' == d) {
					top--;
					sb.append(charArray[i] + "\n");
					for (int j = 0; j <= top; j++) {
						sb.append("\t");
					}
					continue;
				}
			}
			if (',' == c) {
				sb.append(charArray[i] + "");
				for (int j = 0; j <= top; j++) {
					sb.append("");
				}
				continue;
			}
			sb.append(c);
		}

		try {
			writeFileUTF8(sb.toString(), filePath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Writer write = new FileWriter(file);
//		write.write(sb.toString());
//		write.flush();
//		write.close();
	}
	public static void main(String[] args) {
		// String s1 =
		// CommonVar.localFilepath+File.separator+FileUtils.genFilePath("1")+File.separator+FileUtils.genFileName()+".jpg";
		// System.out.println("s1="+s1);
		// System.out.println("path="+FileUtils.getPathPart(s1));
		// System.out.println("filename="+FileUtils.getFileName(s1));

		// System.out.println(CommonVar.webFilePath+File.separator+FileUtils.genFilePath("1")+File.separator+FileUtils.genFileName());

		// System.out.println(UUID.randomUUID().toString().replaceAll("-",""));
		
		
		String s="a.aax.aa.xml"; 
	
		System.out.println(getHeadOrEndStr(s, ".", false));

	}

}
