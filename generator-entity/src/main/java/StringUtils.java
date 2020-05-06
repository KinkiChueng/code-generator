/**
 *
 */

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringEscapeUtils;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static cn.hutool.core.util.StrUtil.toCamelCase;

/**
 * 字符串工具类, 继承org.apache.commons.lang3.StringUtils类
 *
 * @author Michael
 * @version 2013-05-22
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    private static final char SEPARATOR = '_';
    private static final String CHARSET_NAME = "UTF-8";

    /**
     * 生成指定位数的随机字符串（数字+英文） 方法名：getStringRandom 功能说明：TODO
     *
     * @param length
     * @return
     * @Title: getStringRandom
     * @author 李庆
     * @date 2015-9-21 下午03:49:42
     */
    public static String getStringRandom(int length) {

        String val = "";
        Random random = new Random();

        // 参数length，表示生成几位随机数
        for (int i = 0; i < length; i++) {

            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            // 输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) {
                // 输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (random.nextInt(26) + temp);
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

    /**
     * 转换为字节数组
     *
     * @param str
     * @return
     */
    public static byte[] getBytes(String str) {
        if (str != null) {
            try {
                return str.getBytes(CHARSET_NAME);
            } catch (UnsupportedEncodingException e) {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 转换为字节数组
     *
     * @return
     */
    public static String toString(byte[] bytes) {
        try {
            return new String(bytes, CHARSET_NAME);
        } catch (UnsupportedEncodingException e) {
            return StringUtils.EMPTY;
        }
    }

    /**
     * 是否包含字符串
     *
     * @param str  验证字符串
     * @param strs 字符串组
     * @return 包含返回true
     */
    public static boolean inString(String str, String... strs) {
        if (str != null) {
            for (String s : strs) {
                if (str.equals(StringUtils.trim(s))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 替换掉HTML标签方法
     */
    public static String replaceHtml(String html) {
        if (StringUtils.isBlank(html)) {
            return "";
        }
        String regEx = "<.+?>";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(html);
        String s = m.replaceAll("");
        return s;
    }

    /**
     * 替换为手机识别的HTML，去掉样式及属性，保留回车。
     *
     * @param html
     * @return
     */
    public static String replaceMobileHtml(String html) {
        if (html == null) {
            return "";
        }
        return html.replaceAll("<([a-z]+?)\\s+?.*?>", "<$1>");
    }

    /**
     * 缩略字符串（不区分中英文字符）
     *
     * @param str    目标字符串
     * @param length 截取长度
     * @return
     */
    public static String abbr(String str, int length) {
        if (str == null) {
            return "";
        }
        try {
            StringBuilder sb = new StringBuilder();
            int currentLength = 0;
            for (char c : replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray()) {
                currentLength += String.valueOf(c).getBytes("GBK").length;
                if (currentLength <= length - 3) {
                    sb.append(c);
                } else {
                    sb.append("...");
                    break;
                }
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String abbr2(String param, int length) {
        if (param == null) {
            return "";
        }
        StringBuffer result = new StringBuffer();
        int n = 0;
        char temp;
        boolean isCode = false; // 是不是HTML代码
        boolean isHTML = false; // 是不是HTML特殊字符,如&nbsp;
        for (int i = 0; i < param.length(); i++) {
            temp = param.charAt(i);
            if (temp == '<') {
                isCode = true;
            } else if (temp == '&') {
                isHTML = true;
            } else if (temp == '>' && isCode) {
                n = n - 1;
                isCode = false;
            } else if (temp == ';' && isHTML) {
                isHTML = false;
            }
            try {
                if (!isCode && !isHTML) {
                    n += String.valueOf(temp).getBytes("GBK").length;
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            if (n <= length - 3) {
                result.append(temp);
            } else {
                result.append("...");
                break;
            }
        }
        // 取出截取字符串中的HTML标记
        String temp_result = result.toString().replaceAll("(>)[^<>]*(<?)", "$1$2");
        // 去掉不需要结素标记的HTML标记
        temp_result = temp_result.replaceAll(
                "</?(AREA|BASE|BASEFONT|BODY|BR|COL|COLGROUP|DD|DT|FRAME|HEAD|HR|HTML|IMG|INPUT|ISINDEX|LI|LINK|META|OPTION|P|PARAM|TBODY|TD|TFOOT|TH|THEAD|TR|area|base|basefont|body|br|col|colgroup|dd|dt|frame|head|hr|html|img|input|isindex|li|link|meta|option|p|param|tbody|td|tfoot|th|thead|tr)[^<>]*/?>",
                "");
        // 去掉成对的HTML标记
        temp_result = temp_result.replaceAll("<([a-zA-Z]+)[^<>]*>(.*?)</\\1>", "$2");
        // 用正则表达式取出标记
        Pattern p = Pattern.compile("<([a-zA-Z]+)[^<>]*>");
        Matcher m = p.matcher(temp_result);
        List<String> endHTML = Lists.newArrayList();
        while (m.find()) {
            endHTML.add(m.group(1));
        }
        // 补全不成对的HTML标记
        for (int i = endHTML.size() - 1; i >= 0; i--) {
            result.append("</");
            result.append(endHTML.get(i));
            result.append(">");
        }
        return result.toString();
    }

    /**
     * 转换为Double类型
     */
    public static Double toDouble(Object val) {
        if (val == null) {
            return 0D;
        }
        try {
            return Double.valueOf(StringUtils.trim(val.toString()));
        } catch (Exception e) {
            return 0D;
        }
    }

    /**
     * 转换为Float类型
     */
    public static Float toFloat(Object val) {
        return toDouble(val).floatValue();
    }

    /**
     * 转换为Long类型
     */
    public static Long toLong(Object val) {
        return toDouble(val).longValue();
    }

    /**
     * 转换为Integer类型
     */
    public static Integer toInteger(Object val) {
        return toLong(val).intValue();
    }

    /**
     * 获得i18n字符串
     */
//    public static String getMessage(String code, Object[] args) {
//        LocaleResolver localLocaleResolver = (LocaleResolver) SpringContextHolder.getBean(LocaleResolver.class);
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
//                .getRequest();
//        Locale localLocale = localLocaleResolver.resolveLocale(request);
//        return SpringContextHolder.getApplicationContext().getMessage(code, args, localLocale);
//    }

    /**
     * 获得用户远程地址
     */
    public static String getRemoteAddr(HttpServletRequest request) {
        String remoteAddr = request.getHeader("X-Real-IP");
        if (StringUtils.isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("X-Forwarded-For");
        } else if (StringUtils.isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("Proxy-Client-IP");
        } else if (StringUtils.isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("WL-Proxy-Client-IP");
        }
        return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
    }



    /**
     * 驼峰命名法工具
     *
     * @return toCamelCase(" hello_world ") == "helloWorld"
     * toCapitalizeCamelCase("hello_world") == "HelloWorld"
     * toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toCapitalizeCamelCase(String s) {
        if (s == null) {
            return null;
        }
        s = toCamelCase(s);
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    /**
     * 驼峰命名法工具
     *
     * @return toCamelCase(" hello_world ") == "helloWorld"
     * toCapitalizeCamelCase("hello_world") == "HelloWorld"
     * toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toUnderScoreCase(String s) {
        if (s == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            boolean nextUpperCase = true;

            if (i < (s.length() - 1)) {
                nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
            }

            if ((i > 0) && Character.isUpperCase(c)) {
                if (!upperCase || !nextUpperCase) {
                    sb.append(SEPARATOR);
                }
                upperCase = true;
            } else {
                upperCase = false;
            }

            sb.append(Character.toLowerCase(c));
        }

        return sb.toString();
    }

    /**
     * 如果不为空，则设置值
     *
     * @param target
     * @param source
     */
    public static void setValueIfNotBlank(String target, String source) {
        if (StringUtils.isNotBlank(source)) {
            target = source;
        }
    }

    /**
     * 转换为JS获取对象值，生成三目运算返回结果
     *
     * @param objectString 对象串 例如：row.user.id
     *                     返回：!row?'':!row.user?'':!row.user.id?'':row.user.id
     */
    public static String jsGetVal(String objectString) {
        StringBuilder result = new StringBuilder();
        StringBuilder val = new StringBuilder();
        String[] vals = StringUtils.split(objectString, ".");
        for (int i = 0; i < vals.length; i++) {
            val.append("." + vals[i]);
            result.append("!" + (val.substring(1)) + "?'':");
        }
        result.append(val.substring(1));
        return result.toString();
    }

    public static void writeToWeb(String message, String type, HttpServletResponse response) throws IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("text/" + type + "; charset=utf-8");
        response.getWriter().write(message);
        response.getWriter().close();
    }


    /**
     * <b>方法名 formatJson
     * <b>功能说明  htmlJson 反转义 成json
     *
     * @param @param  json
     * @param @return
     * @return String
     * @author 张兴
     * @date 2017年6月13日
     */
    public static String htmlJsonToJson(String json) {
        json = json.replace("&gt;", ">");
        json = json.replace("&lt;", "<");
        json = json.replace("&amp;", "&");
        json = json.replace("&nbsp;", " ");
        json = json.replace("&quot;", "\"");
        json = json.replace("&#39;", "\'");
        json = json.replace("\\\\", "\\");//对斜线的转义
        json = json.replace("\\n", "\n");  //注意php中替换的时候只能用双引号"\n"
        json = json.replace("\\r", "\r");
        return json;
    }

    public static String jsonToHtmlJson(String json) {
        json = json.replace(">", "&gt;");
        json = json.replace("<", "&lt;");
        json = json.replace(" ", "&nbsp;");
        json = json.replace("\"", "&quot;");
        json = json.replace("\'", "&#39;");
        json = json.replace("\\", "\\\\");//对斜线的转义  
        json = json.replace("\n", "\\n");  //注意php中替换的时候只能用双引号"\n"
        json = json.replace("\r", "\\r");
        return json;
    }

    /**
     * 时间字符串转为date类型
     *
     * @param dateStr 时间字符串
     * @param format  转换后的时间格式，如yyyy-MM-dd hh:mm:ss(注意：MM是月份，mm是分钟)
     * @return
     */
    public static Date stringToDate(String dateStr, String format) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.parse(dateStr);
    }

    public static String createLinkString(Map<String, String> map) {
        ArrayList<String> list = new ArrayList<String>();
        for (Entry<String, String> entry : map.entrySet()) {
            if (entry.getValue() != null && entry.getValue() != "") {
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        int size = list.size();
        String[] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(arrayToSort[i]);
        }
        return sb.toString();
    }


    public static String map2Xml(Map<String, Object> map) {
        StringBuffer xml = new StringBuffer("<xml>");
        for (Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            // if(value instanceof Number){
            // xml.append("<"+key+">"+value+"</"+key+">");
            // }else{
            xml.append("<" + key + ">" + value + "</" + key + ">");
            // }
        }
        xml.append("</xml>");
        return xml.toString();
    }

    /**
     * 转换{wmShopId=588371990763536384, phone=1}类型的string为map
     * @return
     */
    public static Map<String, Object> strToMap(String strMsg) {
        if (StringUtils.isNotEmpty(strMsg)) {
            strMsg = strMsg.replace("{", "");
            strMsg = strMsg.replace("}", "");

            String[] strGroup = strMsg.split(",");
            //创建Map对象
            Map<String, Object> map = new HashMap<>();
            //循环加入map集合
            for (int i = 0; i < strGroup.length; i++) {
                //根据":"截取字符串数组
                String[] strkv = strGroup[i].split("=");
                //str2[0]为KEY,str2[1]为值
                map.put(strkv[0].trim(), strkv[1].trim());
            }
            return map;
        }
        return null;
    }
}
