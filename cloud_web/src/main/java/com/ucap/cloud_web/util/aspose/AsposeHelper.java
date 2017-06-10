package com.ucap.cloud_web.util.aspose;

import java.awt.Color;
import java.util.List;
import java.util.Map.Entry;

import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.Underline;

/**
 * <p>Description: </p>
 * <p>@Package：com.demo.util </p>
 * <p>Title: AsposeUtil </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：masl@ucap.com.cn </p>
 * <p>@date：2016-7-15上午9:30:34 </p>
 */
public class AsposeHelper {
	private Document oDoc; //  
	private DocumentBuilder oWordApplic;
	private long old = 0;

	static {
		//getLicense();
	}

	/**
	 * 获取license
	 * 
	 * @return
	 */
	public static boolean getLicense() {
		boolean result = false;

		try {
			/*InputStream is = Test.class.getClassLoader().getResourceAsStream("\\license.xml");
			License aposeLic = new License();
			aposeLic.setLicense(is);
			result = true;*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
		//return result;
	}

	/**
	 * @Title: Open 
	 * @Description: 打开文件
	 * @author: masl@ucap.com.cn 2016-7-15上午10:00:21
	 * @param @param path
	 * @param @throws Exception 
	 * @return void
	 * @throws
	 */
	public void Open(String path) throws Exception {
		System.out.println("######### 加载文档开始  #######");
		old = System.currentTimeMillis();
		oDoc = new Document(path);
		oWordApplic = null;
	}

	/**
	 * @Title: saveAs 
	 * @Description: 保存文件
	 * @author: masl@ucap.com.cn 2016-7-15下午3:49:03
	 * @param @param output
	 * @param @param format
	 * @param @param catalog：生成目录方式（0：文档首页，1：指定书签处 -- 文档中要添加 TAG_CATALOG 书签）
	 * @param @throws Exception 
	 * @return void
	 * @throws
	 */
	public void saveAs(String output, int format, int catalog) throws Exception {
		setCatalog(catalog);
		oDoc.save(output, format);

		long now = System.currentTimeMillis();
		System.out.println("######### 保存文件结束  #######");
		System.out.println("共耗时：" + ((now - old) / 1000.0) + "秒");
	}

	/**
	 * @Title: saveAs 
	 * @Description: 保存文件(不生成目录)
	 * @author: masl@ucap.com.cn 2016-7-15下午3:49:03
	 * @param @param output
	 * @param @param format
	 * @param @throws Exception 
	 * @return void
	 * @throws
	 */
	public void saveAs(String output, int format) throws Exception {
		saveAs(output, format, -1);
	}

	/**
	 * @throws Exception 
	 * @Title: setCatalog 
	 * @Description: 目录生成
	 * @author: masl@ucap.com.cn 2016-7-15下午3:51:53
	 * @param  catalog：生成目录方式（0：文档首页，1：指定书签处 -- 文档中要添加 TAG_CATALOG 书签）
	 * @return void
	 * @throws
	 */
	private void setCatalog(int catalog) throws Exception {
		if (catalog != 1 && catalog != 0)
			return;
		if (oWordApplic == null)
			oWordApplic = new DocumentBuilder(oDoc);

		if (catalog == 1)
			oWordApplic.moveToBookmark("TAG_CATALOG");

		//这行也可以生成目录
		oWordApplic.insertTableOfContents("\\o \"1-3\" \\h \\z \\u");
		//生成目录
		//oWordApplic.insertBreak(BreakType.PAGE_BREAK);
		oDoc.updateFields();
	}

	/**
	 * @Title: replaceAndSave 
	 * @Description: 替换标签、书签并保存文件（注：图片、超链接、表格、html 必须使用书签）
	 * @author: masl@ucap.com.cn 2016-7-15下午2:59:22
	 * @param @param maps
	 * @return void
	 * @throws
	 */
	public void replace(List<JMap> maps) {
		if (maps == null)
			return;

		String key = "";
		String value = "";
		try {
			oWordApplic = new DocumentBuilder(oDoc);
			for (JMap jMap : maps) {
				switch (jMap.getType()) {
				case AsposeType.Txt:
					for (Entry<String, Object> entry : jMap.getMap().entrySet()) {
						key = entry.getKey();
						value = String.valueOf(entry.getValue());
						if (null == value) {
							value = "";
						}
						value = value.replace("\r\n", " ");
						oDoc.getRange().replace("${" + key + "}", value, false, false);
					}
					break;
				case AsposeType.HyperLink:
					for (Entry<String, Object> entry : jMap.getMap().entrySet()) {
						key = entry.getKey();
						AsposeLink link = (AsposeLink) entry.getValue();
						if (null == link || null == link.getText()) {
							continue;
						}
						oWordApplic.moveToBookmark(key);
						oWordApplic.getFont().setColor(Color.BLUE);//蓝色字体
						oWordApplic.getFont().setUnderline(Underline.SINGLE);//下划线
						oWordApplic.insertHyperlink(link.getText(), link.getHref(), false);
					}
					break;
				case AsposeType.Image:
					for (Entry<String, Object> entry : jMap.getMap().entrySet()) {
						key = entry.getKey();
						AsposeImage img = (AsposeImage) entry.getValue();
						if (null == img || null == img.getPath()) {
							continue;
						}
						oWordApplic.moveToBookmark(key);
						oWordApplic.insertImage(img.getPath(), img.getWidth(), img.getHeight());
					}
					break;
				case AsposeType.Html:
					for (Entry<String, Object> entry : jMap.getMap().entrySet()) {
						key = entry.getKey();
						value = String.valueOf(entry.getValue());
						if (null == value) {
							value = "";
						}
						oWordApplic.moveToBookmark(key);
						oWordApplic.insertHtml(value, true);
					}
					break;
				default:
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			return;
		}
	}
}
