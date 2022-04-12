package cn.korostudio.koroworldbot.main;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.setting.Setting;
import cn.korostudio.koroworldbot.thread.BotThread;
import cn.korostudio.koroworldbot.thread.WSThread;
import org.java_websocket.client.WebSocketClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    static protected Logger logger = LoggerFactory.getLogger(Main.class);
    static public Setting setting;
    public static WebSocketClient serverWSClient;

    public static void main(String[] args) {
        setting = new Setting(FileUtil.touch(System.getProperty("user.dir")+"/bot/bot.setting"), CharsetUtil.CHARSET_UTF_8,true);
        logger.info("配置文件读取完毕.");
        showLogo();
        runThread();
    }
    static public void showLogo(){
        logger.info("""
                 
                 Power By :
                 _   __                  _____ _             _ _
                | | / /                 /  ___| |           | (_)
                | |/ /  ___  _ __ ___   \\ `--.| |_ _   _  __| |_  ___
                |    \\ / _ \\| '__/ _ \\   `--. \\ __| | | |/ _` | |/ _ \\
                | |\\  \\ (_) | | | (_) | /\\__/ / |_| |_| | (_| | | (_) |
                \\_| \\_/\\___/|_|  \\___/  \\____/ \\__|\\__,_|\\__,_|_|\\___/
                """);
    }
    static protected void runThread(){
        ThreadUtil.execute(BotThread::run);
        ThreadUtil.execute(WSThread::run);
    }
}
