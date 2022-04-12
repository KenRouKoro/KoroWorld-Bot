package cn.korostudio.koroworldbot.ws;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.json.JSONUtil;
import cn.korostudio.koroworldbot.data.PlayerMessageData;
import cn.korostudio.koroworldbot.thread.BotThread;
import lombok.Getter;
import net.mamoe.mirai.contact.Group;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.Map;

import static cn.korostudio.koroworldbot.thread.BotThread.botGroupID;

public class WSClient extends WebSocketClient {

    protected static Logger logger = LoggerFactory.getLogger(WSClient.class);
    @Getter
    protected static boolean close = false;

    public WSClient(URI serverUri) {
        super(serverUri);
    }

    public WSClient(URI serverUri, Draft protocolDraft) {
        super(serverUri, protocolDraft);
    }

    public WSClient(URI serverUri, Map<String, String> httpHeaders) {
        super(serverUri, httpHeaders);
    }

    public WSClient(URI serverUri, Draft protocolDraft, Map<String, String> httpHeaders) {
        super(serverUri, protocolDraft, httpHeaders);
    }

    public WSClient(URI serverUri, Draft protocolDraft, Map<String, String> httpHeaders, int connectTimeout) {
        super(serverUri, protocolDraft, httpHeaders, connectTimeout);
    }

    /**
     * Called after an opening handshake has been performed and the given websocket is ready to be
     * written on.
     *
     * @param handshakedata The handshake of the websocket instance
     */
    @Override
    public void onOpen(ServerHandshake handshakedata) {
        logger.info("WS Connect!");
        ThreadUtil.execute(()->{
            for(;;){
                if(BotThread.getMainBot()!=null){
                    Group group = BotThread.getMainBot().getGroup(botGroupID);
                    if(group!=null){
                        group.sendMessage("与数据核心连接成功！QWQ");
                    }
                    break;
                }
            }
        });
    }

    /**
     * Callback for string messages received from the remote host
     *
     * @param message The UTF-8 decoded message that was received.
     **/
    @Override
    public void onMessage(String message) {
        PlayerMessageData playerMessageData = JSONUtil.toBean(message, PlayerMessageData.class);
        logger.info("getServerMSG:"+message);
        if(BotThread.getMainBot()!=null){
            Group group = BotThread.getMainBot().getGroup(botGroupID);
            if(group!=null){
                group.sendMessage("["+playerMessageData.getServer()+"]"+"["+playerMessageData.getName()+"]"+playerMessageData.getMessage());
            }
        }
    }

    /**
     * Called after the websocket connection has been closed.
     *
     * @param reason Additional information string
     * @param remote Returns whether or not the closing of the connection was initiated by the remote
     **/
    @Override
    public void onClose(int code, String reason, boolean remote) {
        logger.info("WS Connect Close.");
        close=true;
        ThreadUtil.execute(()->{
            for(;;){
                if(BotThread.getMainBot()!=null){
                    Group group = BotThread.getMainBot().getGroup(botGroupID);
                    if(group!=null){
                        group.sendMessage("与数据核心的连接断开了QAQ");
                    }
                    break;
                }
            }
        });
    }

    /**
     * Called when errors occurs. If an error causes the websocket connection to fail {@link
     * #onClose(int, String, boolean)} will be called additionally.<br> This method will be called
     * primarily because of IO or protocol errors.<br> If the given exception is an RuntimeException
     * that probably means that you encountered a bug.<br>
     *
     * @param ex The exception causing this error
     **/
    @Override
    public void onError(Exception ex) {
        close=true;
        ex.printStackTrace();

    }
}
