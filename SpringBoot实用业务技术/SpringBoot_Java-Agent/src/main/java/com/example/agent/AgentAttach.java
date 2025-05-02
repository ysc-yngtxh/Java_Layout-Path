package com.example.agent;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import java.io.IOException;

import static java.lang.Thread.sleep;

public class AgentAttach {

    public static void main(String[] args) throws IOException, AttachNotSupportedException, InterruptedException {
        // 85355 表示目标进程的PID
        VirtualMachine virtualMachine = VirtualMachine.attach("85355");
        // 指定Java Agent的jar包路径
        try {
            while (true) {
                virtualMachine.loadAgent("/Users/jack/youzan/java-agent-demo/target/java-agent-demo-1.0.jar", "Agent");
                sleep(3000);
            }
        } catch (AgentLoadException | AgentInitializationException e) {
            e.printStackTrace();
        } finally {
            virtualMachine.detach();
        }
    }
}
