package cn.korostudio.koroworldbot.command;

import cn.korostudio.koroworldbot.main.Main;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.event.events.GroupMessageEvent;

public class StringRunner {

    static public void run(String str, GroupMessageEvent event){
        String [] strings = str.split(" ");
        Group group = event.getGroup();
        switch (strings[0]){
            case "蹭蹭","蹭蹭~"->{
                group.sendMessage("呜~~");
            }
            case "摸摸","摸摸~"->{
                group.sendMessage("呜喵~~");
            }
            case "?","？","??","???","？？","？？？","????","？？？？"->{
                group.sendMessage("呜喵?");
            }
            case "(舔", "舔", "透","草" ->{
                group.sendMessage("呜///////");
            }
            case "你好"->{
                group.sendMessage("汝好喵~~");
            }
            case "早"->{
                group.sendMessage("早喵~~");
            }
            default -> {}
        }
        if(strings[0].equals("@"+ Main.setting.getLong("QQAccount",-1L)))
        switch (strings[1]){
            case "蹭蹭","蹭蹭~"->{
                group.sendMessage("呜~~");
            }
            case "摸摸","摸摸~"->{
                group.sendMessage("呜喵~~");
            }
            case "?","？","??","???","？？","？？？","????","？？？？"->{
                group.sendMessage("呜喵?");
            }
            case "(舔", "舔", "透","草" ->{
                group.sendMessage("呜///////");
            }
            case "你好"->{
                group.sendMessage("汝好喵~~");
            }
            case "早"->{
                group.sendMessage("早喵~~");
            }
            default -> {}
        }
    }
}
