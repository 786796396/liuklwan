package common.util;


import java.util.regex.Pattern;

/**
 * Created by pangsx
 * Date: 2018/1/8 14:06
 */
public class CharUtils {

    // 根据Unicode编码完美的判断中文汉字和符号
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
            || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
            || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
            || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符是否为日文字。
     * 因为日文中有很多是汉字，所以除了对汉字的统计，还要对日文字统计--2018-06-07
     * @param c
     * @return
     */
    public static boolean isJapanese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.HIRAGANA || ub == Character.UnicodeBlock.KATAKANA) {
            return true;
        }
        return false;
    }

    // 完整的判断中文汉字和符号
    public static boolean hasChinese(String strName) {
        char[] ch = strName.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (isChinese(c)) {
                return true;
            }
        }
        return false;
    }

    // 只能判断部分CJK字符（CJK统一汉字）
    public static boolean isChineseByREG(String str) {
        if (str == null) {
            return false;
        }
        Pattern pattern = Pattern.compile("[\\u4E00-\\u9FBF]+");
        return pattern.matcher(str.trim()).find();
    }

    // 只能判断部分CJK字符（CJK统一汉字）
    public static boolean isChineseByName(String str) {
        if (str == null) {
            return false;
        }
        // 大小写不同：\\p 表示包含，\\P 表示不包含
        // \\p{Cn} 的意思为 Unicode 中未被定义字符的编码，\\P{Cn} 就表示 Unicode中已经被定义字符的编码
        String reg = "\\p{InCJK Unified Ideographs}&&\\P{Cn}";
        Pattern pattern = Pattern.compile(reg);
        return pattern.matcher(str.trim()).find();
    }

    /**
     * @desc:判断是不是中文网页
     * @createBy:pangsx
     * @Date:2018/1/8 14:26
     **/
    public static boolean isChinese(String plainTxt){
        boolean b = true;
        if(StringUtils.isNotEmpty(plainTxt)){
            int trueCount = 0;
            int japanCount = 0;//日文字的数量--2018-06-07
            char[] ch = plainTxt.toCharArray();
            int allCount = ch.length;
            for (int i = 0; i<allCount;i++){
                if(CharUtils.isChinese(ch[i])){
                    trueCount++;
                }else if(Character.isDigit(ch[i])){
                    trueCount++;
                } else if (CharUtils.isJapanese(ch[i])) {
                    japanCount++;
                }

            }
            //中文字符占比
            float bl = Float.parseFloat(String.format("%.2f", (double) trueCount / (double) allCount));
            //日文字符占比--2018-06-07
            float japanBl = Float.parseFloat(String.format("%.2f", (double) japanCount / (double) allCount));
            if(bl<0.1 || japanBl>0.1){
                b = false;
            }
        }
        return b;
    }

    /**
     * @desc:判断文本中是否没有繁体字
     * @createBy:pangsx
     * @Date:2018/1/8 15:03
     **/
    public static boolean isNotTraditional(String planTxt){
        boolean b = true;
        char[] chars = planTxt.toCharArray();
        int allCount = chars.length;
        int ftCount = 0;
        for (char c:chars
             ) {
            if(!ZHConverter.convert(String.valueOf(c),1).equals(String.valueOf(c))){
                ftCount++;
            }
        }
        float bl = Float.parseFloat(String.format("%.2f", (double) ftCount / (double) allCount));
        if(bl>0.1){
            b = false;
        }
        return b;
    }

    public static void main(String[] args) throws Exception {
    }

}
