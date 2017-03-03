package com.fxiaoke.supervisor.api;

import com.fxiaoke.supervisor.common.SupervisorCommand;
import com.fxiaoke.supervisor.service.SupervisorService;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by wangyuebin on 15/12/16.
 */
public class SupervisorTest {

  private Supervisor supervisor;

  @Before
  public void init() {
    supervisor = new SupervisorService();
  }

  @Test
  public void testExecuteCommandNoParams() {
    String result = supervisor.executeCommandNoParams("fs", "fsxiaoke", 9001, "172.31.101.12", SupervisorCommand.GET_ALL_PROCESS_INFO);
    System.out.println(result);
  }

  @Test
  public void testExecuteCommandWithParamsAndWait() {
    boolean result = supervisor.executeCommandWithParamsAndWait("fs", "fsxiaoke", 9001, "172.31.101.12", SupervisorCommand.STOP_PROCESS, "jagent");
    System.out.println(result);
  }

  @Test
  public void testExecuteCommandWithParamsAndNoWait() {
    boolean result = supervisor.executeCommandWithParamsAndNoWait("fs", "fsxiaoke", 9001, "172.31.101.12", SupervisorCommand.START_PROCESS, "jagent");
    System.out.println(result);
  }
}
