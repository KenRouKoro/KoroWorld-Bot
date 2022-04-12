package cn.korostudio.koroworldbot.thread;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.setting.Setting;
import cn.korostudio.koroworldbot.command.Command;
import cn.korostudio.koroworldbot.command.StringRunner;
import cn.korostudio.koroworldbot.main.Main;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.event.events.NudgeEvent;
import net.mamoe.mirai.utils.BotConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class BotThread {
    @Setter
    @Getter
    protected static boolean messageForAll=true;

    public static boolean wsConnect=false;

    public static long botGroupID;

    protected static Logger logger = LoggerFactory.getLogger(BotThread.class);
    @Getter
    protected static Bot mainBot = null;
    @Getter
    protected static long botAccount;

    protected static Setting setting;

    public static void run() {
        loadSetting();
        BotConfiguration botConfiguration = new BotConfiguration();
        botConfiguration.setWorkingDir(new File("./bot/running"));
        botConfiguration.setCacheDir(new File("./cache"));
        //ClassPathResource resource = new ClassPathResource("device.json");
        //InputStreamReader inputStreamReader = new InputStreamReader(resource.getStream());

        /*
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder stringBuffer = new StringBuilder();
        String str = null;
        try {
            while ((str = bufferedReader.readLine()) != null) {
                stringBuffer.append(str);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        */
        FileReader reader = new FileReader(System.getProperty("user.dir")+"/bot/device.json");

        botConfiguration.loadDeviceInfoJson(reader.readString());
        botAccount = Main.setting.getLong("QQAccount",-1L);
        String passWorld = Main.setting.getStr("QQPassWorld",null);
        mainBot = BotFactory.INSTANCE.newBot(botAccount,passWorld,botConfiguration);
        mainBot.login();
        logger.info("机器人启动完毕.");
        logger.info("正在添加监听.");
        addListener();


    }
    static protected void addListener(){
        mainBot.getEventChannel().subscribeAlways(NudgeEvent.class,event ->{
            if(event.getTarget().getId()==getMainBot().getId()) {
                event.getSubject().sendMessage(setting.getStr("NudgeText", "呜！不准戳吾！！！"));
            }
        });
        mainBot.getEventChannel().subscribeAlways(GroupMessageEvent.class,event->{
            long groupID = event.getGroup().getId();
            if(groupID!=botGroupID){
                return;
            }
            logger.info("msg:"+event.getMessage().contentToString());
            if (event.getMessage().contentToString().charAt(0)=='#'){
                Command.run(event.getMessage().contentToString(),event);
            }else{
                StringRunner.run(event.getMessage().contentToString(),event);
            }
        });
    }
    static protected void loadSetting(){
        setting = new Setting(FileUtil.touch(System.getProperty("user.dir")+"/bot/bot.setting"), CharsetUtil.CHARSET_UTF_8,true);
        botGroupID = setting.getLong("GroupID");
    }
}
