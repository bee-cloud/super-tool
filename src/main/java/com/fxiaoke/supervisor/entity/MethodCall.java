package com.fxiaoke.supervisor.entity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by wangyuebin on 15/12/15.
 */
@XmlRootElement
public class MethodCall {
  private String methodName;
  private Params params;

  public MethodCall(){
    super();
  }

  public String getMethodName() {
    return methodName;
  }

  public void setMethodName(String methodName) {
    this.methodName = methodName;
  }

  public Params getParams() {
    return params;
  }

  public void setParams(Params params) {
    this.params = params;
  }
}
