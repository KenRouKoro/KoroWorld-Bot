package cn.korostudio.koroworldbot.data;

import lombok.Data;

@Data
public class PlayerMessageData {
    protected String server;
    protected String name;
    protected String message;
    protected String group;
    protected boolean HighestAuthority = false;
    protected boolean system = false;
}

