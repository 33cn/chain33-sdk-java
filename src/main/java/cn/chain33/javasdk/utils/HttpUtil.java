package cn.chain33.javasdk.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;

public class HttpUtil {
	
	private static CloseableHttpClient client = null;
	
	private static CloseableHttpClient httpsClient = null;

	private static RequestConfig requestConfig = null;
	
	private static final String DEFAULT_CHARSET = "UTF-8";
	
	private static final int DEFAULT_TIME_OUT = 400000;
	static{
		client = HttpClientBuilder.create().build();
		Builder configBuilder = RequestConfig.custom();
		configBuilder.setConnectionRequestTimeout(DEFAULT_TIME_OUT);
		configBuilder.setConnectTimeout(DEFAULT_TIME_OUT);
		configBuilder.setSocketTimeout(DEFAULT_TIME_OUT);
		requestConfig = configBuilder.build();
		httpsClient = createSSLInsecureClient();
	}
	
    @SuppressWarnings("deprecation")
	public static CloseableHttpClient createSSLInsecureClient() {  
        try {  
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {  
                // 默认信任所有证书  
                public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {  
                    return true;  
                }  
            }).build();  
            // AllowAllHostnameVerifier: 这种方式不对主机名进行验证，验证功能被关闭，是个空操作(域名验证)  
            SSLConnectionSocketFactory sslcsf = new SSLConnectionSocketFactory(sslContext,  
                    SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);  
            return HttpClients.custom().setSSLSocketFactory(sslcsf).build();  
        } catch (KeyManagementException e) {  
            e.printStackTrace();  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        } catch (KeyStoreException e) {  
            e.printStackTrace();  
        }  
        return HttpClients.createDefault();  
    }  
    
	private static String getContent(HttpEntity entity,String charset) throws IOException {
		String content = null;
		if(entity != null){
			InputStream in = entity.getContent();
			InputStreamReader inputStreamReader = new InputStreamReader(in,charset);
			BufferedReader reader = new BufferedReader(inputStreamReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line = null;
			while((line = reader.readLine()) != null){
				stringBuffer.append(line);
				stringBuffer.append("\r\n");
			}
			content = stringBuffer.toString();
		}
		return content;
	}
	
	
	public static String httpPostBody(String url, String jsonString) throws IOException {
		String content = null;
		HttpPost post = new HttpPost(url);
		post.setConfig(requestConfig);
		post.addHeader("Content-Type", "application/json");
		post.setEntity(new StringEntity(jsonString,DEFAULT_CHARSET));
		HttpResponse response = client.execute(post);
		HttpEntity entity = response.getEntity();
		content = getContent(entity,DEFAULT_CHARSET);
		post.releaseConnection();

		return content;
	}
	
	public static String httpsPostBody(String url, String jsonString) {
		String content = null;
		HttpPost post = new HttpPost(url);
		try {
			post.setConfig(requestConfig);
			post.addHeader("Content-Type", "application/json");
			post.setEntity(new StringEntity(jsonString,DEFAULT_CHARSET));
			HttpResponse response = httpsClient.execute(post);
			HttpEntity entity = response.getEntity();
			content = getContent(entity,DEFAULT_CHARSET);
			post.releaseConnection();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return content;
	}

}
