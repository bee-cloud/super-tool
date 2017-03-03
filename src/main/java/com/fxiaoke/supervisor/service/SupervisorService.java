package com.fxiaoke.supervisor.service;

import com.fxiaoke.supervisor.api.Supervisor;
import com.fxiaoke.supervisor.intern.HttpUtil;

/**
 * Created by wangyuebin on 15/12/16.
 */
public class SupervisorService implements Supervisor {

  public String executeCommandNoParams(String username, String password, int port, String url, String command) {
    return HttpUtil.sendCommandNoParams(username, password, url, port, command);
  }

  public boolean executeCommandWithParamsAndWait(String username,
                                                 String password,
                                                 int port,
                                                 String url,
                                                 String command,
                                                 String name) {
    String result = HttpUtil.sendCommandWithParamsAndWait(username, password, url, port, command, name);
    if (result.indexOf(">1<") > 0) {
      return true;
    }
    return false;
  }

  public boolean executeCommandWithParamsAndNoWait(String username,
                                                   String password,
                                                   int port,
                                                   String url,
                                                   String command,
                                                   String name) {
    String result = HttpUtil.sendCommandWithParamsAndNoWait(username, password, url, port, command, name);
    if (result.indexOf(">1<") > 0) {
      return true;
    }
    return false;
  }

}
