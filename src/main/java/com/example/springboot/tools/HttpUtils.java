package com.example.springboot.tools;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Description: 后台发送HTTP请求
 *
 * @author yangtao
 * @version 0.0.1
 * @date 2019/3/20
 */
public class HttpUtils {

    /**
     * 发送POST请求
     *
     * @param address 请求地址
     * @param params  请求参数（map集合）
     * @return 返回结果（json字符串）
     */
    public static String sendPost(String address, Map<String, Object> params) {

        HttpURLConnection connection = null;
        try {
            // 建立连接
            URL url = new URL(address);
            connection = (HttpURLConnection) url.openConnection();

            // 请求方式POST
            connection.setRequestMethod("POST");

            // 设置超时时间，单位毫秒
            connection.setConnectTimeout(160000);

            // 是否向HttpURLConnection输出，因为POST请求参数要放在http正文
            // 因此要设置为true，模式是false
            connection.setDoOutput(true);

            // 是否从HTTPURLConnection输入，默认是true
            connection.setDoInput(true);

            // POST请求不能使用缓存
            connection.setUseCaches(false);

            // 设置文件字符集
            connection.setRequestProperty("Charset", "UTF-8");

            // 设置请求数据类型为json格式数据
            // 如果是form编码方式，则使用application/x-www-form-urlencoded
            connection.setRequestProperty("Content-Type", "application/json");

            // 开始连接请求
            connection.connect();
            OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream(), StandardCharsets.UTF_8);

            // 请求参数转为json字符串发送
            osw.write(new Gson().toJson(params));
            osw.flush();
            osw.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        // 读取返回内容
        return getStringBuffer(connection);
    }

    /**
     * 发送GET请求
     *
     * @param address 请求地址 + ? + 请求参数
     * @return 返回结果（json字符串）
     */
    public static String sendGet(String address) {

        HttpURLConnection connection = null;
        try {
            URL url = new URL(address);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);

            // 开始连接请求
            connection.connect();
            OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream(), StandardCharsets.UTF_8);
            osw.flush();
            osw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        // 读取返回内容
        return getStringBuffer(connection);
    }

    /**
     * 读取返回内容
     *
     * @param connection 连接
     * @return 返回结果（json字符串）
     */
    private static String getStringBuffer(HttpURLConnection connection) {

        StringBuffer stringBuffer = new StringBuffer();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            String temp;
            while ((temp = br.readLine()) != null) {
                stringBuffer.append(temp).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stringBuffer.toString();
    }
}