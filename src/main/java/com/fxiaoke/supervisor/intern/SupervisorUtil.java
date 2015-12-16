package com.fxiaoke.supervisor.intern;

import com.fxiaoke.supervisor.entity.MethodCall;
import com.fxiaoke.supervisor.entity.Params;
import com.google.common.base.Strings;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

/**
 * Created by wangyuebin on 15/12/16.
 */
public class SupervisorUtil {

  public static String buildCommandWithParamsAndWait(String command, String name) {
    return buildCommand(command, name, "true");
  }

  public static String buildCommandWithParamsAndNoWait(String command, String name) {
    return buildCommand(command, name, "false");
  }

  public static String buildCommandNoParams(String command) {
    return buildCommand(command, null, null);
  }

  private static String buildCommand(String command, String name, String wait) {
    try {
      MethodCall call = new MethodCall();
      call.setMethodName(command);
      Params params = new Params();
      if (!Strings.isNullOrEmpty(name)) {
        params.setName(name);
        params.setWait(wait);
      }
      call.setParams(params);
      JAXBContext context = JAXBContext.newInstance(MethodCall.class);
      StringWriter writer = new StringWriter();
      Marshaller marshaller = context.createMarshaller();
      marshaller.marshal(call, writer);
      return writer.toString();
    } catch (JAXBException e) {
    }
    return null;
  }
}
