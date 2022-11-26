package com.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2021000118648200";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCNct8hd0DRd949wmSK4ADnW10hLIDHBqThskAHjoAifhmKS/QklzKJyJDiCrGPjB5HzduiT+W95Nl+uQtE7pZSxNQRuphaLgWxszTdPypLD7wD7erUt7SWM6zW15Hrv4eGkrzA6xjXPrBFNew9a7CfQbtfPGemgaKk5cb2cx5hhz/Ws9svWo9hcW3J3C/OsUlncGBNl1lKDDWkK9q5QqR4uor0UmCGA6RZ5KoHjRBqSKih5cJeOsTnkWmlzmwH/mtyiR6DOsajAf1x/GikbML/Aec3nT2v8qrrBhioP2UoMnMk05Xj/Hz7bduwtFj04/9Sxm2v1YKthrWB+qCCBtf7AgMBAAECggEAcaU8jxfQVTvYrIg13bpyH3AxmAi4gq/c45Uiiq+8VXo5LPsvcgKk0Tz64xIybSHn5ksITxWggXyQHpev7TdAQcnwlTn1VGcrngVc0PICGgLPTdzm0veyOQfVs1vJ2r6stbvJubpp3+53E3RqlUKjyqC2WaMfqBD5y0DuJW6Cl1oupT0Pol9HbztNtNb5H8ApaDgc0/Go9C2FvWDVdfsST1Wixz/YHlzWxhDL5eQgICifoO8HGip8pkbU1x8g9VwZd5ndnrl+OSHWWLAL68MNWlgT0vFjJpnECKP4NN5CG47H9p8V49pLst75IIc65HFSlzsLe2Fs5dZ9vdqbNfTLSQKBgQDUFLDlAOluWzwVt6sfW+pqiX7Jdv8p6iEfgv70TyIHkb0N/Mn+rj4c70CBCKSbrlTuEQKhhKhY6TvEqKVcMy/0Pj0rZNJApBI6SeOo9smAKxMy9rJVbQwYsfnr9ClD+bBKyXmqldr8Cfd+32lDt2NSeuiir4w23/pLR8v7TOYkHwKBgQCqvavfR7HYiWHKjpBYwUKUSNfer6sRpTT2YxvcL6gHOU0k2MEZPXPGyoOOjSGXm1SSnk/61FK7kX2Cz3gQ+K/DlJB07UxfpwFOblzmJWPHjJj0gK3381ZNhesEhCz3PVAx45Hn1EwV+OcOGht2zyXK7jGWjy0XW2VCf+E64ZxwpQKBgFkU99i5MVPxbqOQqohZi3WEm4T0PunUNmWOGJl2Q8z6Incln5MijWSJ+oY4oyvhZjs5WemOV0hBb6GDVTINLi9hlUAM8WPFhwKE1uO6BZmYflAB35aSX2pgF6JMxKiQ0MQZLMnCeGE1rofUP9r5xPZ4Og9bvYGvQussFTpRarnxAoGAUEX0dHlkqAWPMIhuXQwfnU/SDiRRLW6aw+w8wPy25w6NsnEl+BPoRuIXkAHSdVDWJkjAGZGRhBYXCFU6geAp4q4KbK7UZXRDLPi+PIX/r2Xu06VcJWMGhFQd9nWeHm95MLtpcbFiREJNf1fO6cDiZ9ItBjgyXvTdWrJ81g4UWvECgYEAspefnFm3ewDVEqsDhEiCZxnedSsvQbSyPUKcj91xQaOdpFkWuZZmfuebvzq6XnqtvAkrcUflGA7bx3hcQUSeLclRE6V0EZwaKYu19Go9iUIAhcmUiDHKI8Rrnd57R13xmhW8kWjyRIvzpU3cC7m+49SES1oYMfZ5cA5sjkl2VH8=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAh5MTFoDwPOpu9cb44cpSBwFBhhi1GI9XNlDTfVVaHZBr608wvX5aVP/pA10hbRn7DVjCfcy4gxvW9docBHnfiuWRjJ9aJrdGVQVsgKa6MyNKJr+R52E8jba0kYkDQ3imPmNFsFlxOUWbDD1wdryYxHEXlFQ71bL/5m1m9l8pI9ZB+xiaYT0ImTR44rZc7tysQ3fVmgKmivwno18Xy+zcRJC7EOIhGIJ1Euk1eMNDayyDO+C9jghUU7sh5pqkQ/gCj9A4KbC3J3qRmndgctQNp8s0pGou/nPq8Yij5Pqj2izTkVO1sv64f0s0zqj+1jPySpLGXKWFiuwHziw+cgzyUwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8880/web11/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8880/web11/userSkipOrders.action";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "D:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

