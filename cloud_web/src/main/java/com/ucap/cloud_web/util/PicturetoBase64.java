/**
 * 
 */
package com.ucap.cloud_web.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.bouncycastle.util.encoders.Base64;

import com.publics.util.utils.StringUtils;
import com.thoughtworks.xstream.core.util.Base64Encoder;

/**
 * @author sunjq
 * 将图片转换为base64编码
 *
 */
public class PicturetoBase64 {

	/**
	 * @param file 需要转换的文件路径
	 * @return 返回转换后的base64编码
	 * @throws Exception
	 */
	public static String pictobase(File file) throws Exception {
		FileInputStream inputFile = new FileInputStream(file);
		byte[] buffer = new byte[(int) file.length()];
		inputFile.read(buffer);
		inputFile.close();
		return new String(Base64.encode(buffer));
	}

	public static String getImageString(String fileName) throws IOException {
		if (StringUtils.isEmpty(fileName))
			return "";
		
		InputStream in = null;
		byte[] data = null;
		try {
			in = new FileInputStream(fileName);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				in.close();
			}
		}
		Base64Encoder encoder = new Base64Encoder();
		return data != null ? encoder.encode(data) : "";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		File f = new File("E:/3149367629.pdf");
		try {
			System.out.println(PicturetoBase64.pictobase(f));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
