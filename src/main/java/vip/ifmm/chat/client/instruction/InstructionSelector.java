package vip.ifmm.chat.client.instruction;

import io.netty.channel.Channel;
import vip.ifmm.chat.client.instruction.impl.*;
import vip.ifmm.chat.server.util.SessionCheck;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author: mackyhuang
 * <p>email: mackyhuang@163.com <p>
 * <p>date: 2019/5/9 </p>
 */
public class InstructionSelector implements CommonInstruction{

    private Map<String, CommonInstruction> instructionMap = null;

    public InstructionSelector(){
        instructionMap = new HashMap<>();
        //instructionMap.put("login", new LoginInstruction());
        instructionMap.put("logout", new LogoutInstruction());
        instructionMap.put("private", new MessageInstruction());
        instructionMap.put("group", new GroupInstruction());
        instructionMap.put("join", new JoinInstruction());
        instructionMap.put("quit", new QuitInstruction());
        instructionMap.put("list", new ListInstruction());
    }

    @Override
    public void exec(Scanner scanner, Channel channel) {
        String instruction = scanner.nextLine();

        if (!SessionCheck.checkLogin(channel)){
            return;
        }

        CommonInstruction commonInstruction = instructionMap.get(instruction);

        if (commonInstruction != null){
            commonInstruction.exec(scanner, channel);
        } else {
            System.err.println("无法识别[" + instruction + "]指令，请重新输入!");
        }
    }
}
