package cn.korostudio.koroworldbot.command;

import cn.hutool.json.JSONUtil;
import cn.korostudio.koroworldbot.data.PlayerMessageData;
import cn.korostudio.koroworldbot.main.Main;
import cn.korostudio.koroworldbot.ws.WSClient;
import net.mamoe.mirai.event.events.GroupMessageEvent;

public class Command {
    static public void run(String command, GroupMessageEvent event){
        String cmdStr = command.substring(0,command.indexOf(' ')+1).trim();
        System.out.println(cmdStr);
        switch (cmdStr){
            case "#say"->{
                if(WSClient.isClose()){
                    return;
                }
                PlayerMessageData playerMessageData = new PlayerMessageData();
                //playerMessageData.setServer("QQGroup");
                playerMessageData.setHighestAuthority(true);
                playerMessageData.setGroup("System");
                playerMessageData.setServer(Main.setting.getStr("BotName","QQGroup"));
                playerMessageData.setName(event.getSender().getNameCard().isEmpty()?event.getSender().getNick():event.getSender().getNameCard());
                playerMessageData.setMessage(command.substring(command.indexOf(' ')));
                Main.serverWSClient.send(JSONUtil.toJsonStr(playerMessageData));
            }

            default -> {}
        }
    }
}
