package com.fxiaoke.supervisor.intern;

import com.google.common.base.Charsets;
import org.apache.http.HttpStatus;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.NTCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by wangyuebin on 15/12/16.
 */
public class HttpUtil {

  public static String sendCommandNoParams(String username, String password, String ip, int port, String command) {
    CloseableHttpClient httpClient = getCloseableHttpClient(username, password, ip, port);
    HttpPost post = getHttpPost(ip, port, new StringEntity(SupervisorUtil.buildCommandNoParams(command), Charsets.UTF_8));
    return getResponseMsg(httpClient, post);
  }

  public static String sendCommandWithParamsAndNoWait(String username,
                                                      String password,
                                                      String ip,
                                                      int port,
                                                      String command,
                                                      String name) {
    CloseableHttpClient httpClient = getCloseableHttpClient(username, password, ip, port);
    HttpPost post = getHttpPost(ip, port, new StringEntity(SupervisorUtil.buildCommandWithParamsAndNoWait(command, name), Charsets.UTF_8));
    return getResponseMsg(httpClient, post);
  }

  public static String sendCommandWithParamsAndWait(String username,
                                                    String password,
                                                    String ip,
                                                    int port,
                                                    String command,
                                                    String name) {
    CloseableHttpClient httpClient = getCloseableHttpClient(username, password, ip, port);
    HttpPost post = getHttpPost(ip, port, new StringEntity(SupervisorUtil.buildCommandWithParamsAndWait(command, name), Charsets.UTF_8));
    return getResponseMsg(httpClient, post);
  }

  private static HttpPost getHttpPost(String ip, int port, StringEntity entity) {
    HttpPost post = new HttpPost("http://" + ip + ":" + port + "/RPC2");
    post.setEntity(entity);
    return post;
  }

  private static CloseableHttpClient getCloseableHttpClient(String username, String password, String ip, int port) {
    AuthScope authscope = new AuthScope(ip, port);
    Credentials credentials = new NTCredentials(username, password, null, null);
    CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
    credentialsProvider.setCredentials(authscope, credentials);
    return HttpClients.custom().setDefaultCredentialsProvider(credentialsProvider).build();
  }

  private static String getResponseMsg(CloseableHttpClient httpClient, HttpPost post) {
    CloseableHttpResponse response = null;
    try {
      response = httpClient.execute(post);
      int code = response.getStatusLine().getStatusCode();
      if (code != HttpStatus.SC_OK) {
        throw new RuntimeException("request supervisor rpc service error");
      }
      return EntityUtils.toString(response.getEntity());
    } catch (IOException e) {
      throw new RuntimeException("", e);
    } finally {
      closeQuietly(response);
      closeQuietly(httpClient);
    }
  }


  private static void closeQuietly(Closeable c) {
    if (c != null) {
      try {
        c.close();
      } catch (IOException ignored) {
      }
    }
  }
}
