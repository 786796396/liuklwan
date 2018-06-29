package common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 对file文件的I/O操作
 * Created by wangwl on 2017/10/18.
 */
public class FileUtil {
    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 向文件中写入内容
     *
     * @param filepath 文件路径与名称
     * @param newstr   写入的内容
     * @return
     * @throws IOException
     */
    public static boolean writeFileContent(String filepath, String newstr) throws IOException {
        Boolean bool = false;
        String filein = newstr + "\r\n";//新写入的行，换行
        String temp = "";

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        FileOutputStream fos = null;
        PrintWriter pw = null;
        try {
            File file = new File(filepath);//文件路径(包括文件名称)
            //将文件读入输入流
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            StringBuffer buffer = new StringBuffer();

            //文件原有内容
            for (int i = 0; (temp = br.readLine()) != null; i++) {
                buffer.append(temp);
                // 行与行之间的分隔符 相当于“\n”
                buffer = buffer.append(System.getProperty("line.separator"));
            }
            buffer.append(filein);

            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            pw.write(buffer.toString().toCharArray());
            pw.flush();
            bool = true;
        } catch (Exception e) {
            logger.error("向文件中写入内容异常：" + e.toString());
        } finally {
            if (pw != null) {
                pw.close();
            }
            if (fos != null) {
                fos.close();
            }
            if (br != null) {
                br.close();
            }
            if (isr != null) {
                isr.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
        return bool;
    }

    /**
     * 向文件中以追加的方式写数据
     *
     * @param file
     * @param conent
     */
    public static void writeFile(File file, String conent) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
            out.write(conent);
        } catch (Exception e) {
            logger.error("向文件中写入内容异常：" + e.toString());
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将文件内容写入文件（使用UTF-8编码）
     *
     * @param content  文件内容
     * @param filePath 输出文件路径
     * @throws Exception
     */
    public static void writeFileUTF8(String content, String filePath) throws Exception {
        createDir(filePath);
        File file = new File(filePath);
        FileOutputStream fos = new FileOutputStream(file);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"));
        bw.write(content);
        bw.flush();
        bw.close();
        fos.close();
    }

    /**
     * 如果欲写入的文件所在目录不存在，需先创建
     *
     * @param outputPath 输出文件路径
     */
    public static void createDir(String outputPath) {
        File outputFile = new File(outputPath);
        File outputDir = outputFile.getParentFile();
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }
    }

    public static void main(String[] args) {


        File csv = new File("D:\\data_2017-11-08.cvs");  // CSV文件路径
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(csv));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line = "";
        String everyLine = "";
        try {
            Map<String, String[]> fenxi = new HashMap<>();
            int i = 0;
            while ((line = br.readLine()) != null)  //读取到的内容给line变量
            {
                String[] lines = line.split(",");

                if (fenxi.containsKey(lines[0])) {

                    System.out.println(line);
                    i++;
                } else {
                    fenxi.put(lines[0], lines);
                }
            }
            System.out.println("共有：" + i + "条重复数据");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
