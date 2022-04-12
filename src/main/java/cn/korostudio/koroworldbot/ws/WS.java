package cn.korostudio.koroworldbot.ws;

import cn.hutool.core.codec.Base62;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.json.JSONUtil;
import cn.korostudio.koroworldbot.data.PlayerMessageData;
import cn.korostudio.koroworldbot.main.Main;
import cn.korostudio.koroworldbot.thread.BotThread;
import net.mamoe.mirai.contact.Group;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;


public class WS {
    protected static Logger logger = LoggerFactory.getLogger(WS.class);
    static public void connect(){
        try {
            String wsURL = Main.setting.getStr("WSServer", "ws://127.0.0.1:18620")+"/message/ws"+"?token="+ Base62.encode(Main.setting.getStr("BotName","QQGroup"));
            URI uri = new URI(wsURL);
            Main.serverWSClient = new WSClient(uri);
        } catch (URISyntaxException e) {
            logger.error("WS Connect FAIL!");
        }
        //serverWSClient.addHeader("token",serverNameStr);
        Main.serverWSClient.connect();
    }
    static public void run(){
            connect();

            ThreadUtil.execute(()->{
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException ignored) {}
                if(WSClient.isClose()){
                    connect();
                }

            });

    }
}
