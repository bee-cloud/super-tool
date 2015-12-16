package com.fxiaoke.supervisor.api;

/**
 * Created by wangyuebin on 15/12/16.
 */
public interface Supervisor {


  String executeCommandNoParams(String username, String password, int port, String url, String command);

  boolean executeCommandwWithParamsAndWait(String username, String password, int port, String url, String command, String name);

  boolean executeCommandwWithParamsAndNoWait(String username, String password, int port, String url, String command, String name);

}
