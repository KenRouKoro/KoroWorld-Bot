package cn.korostudio.koroworldbot.thread;

import cn.hutool.core.thread.ThreadUtil;
import cn.korostudio.koroworldbot.ws.WS;

public class WSThread {
    static public void run(){
        ThreadUtil.execute(WS::run);
    }
}
