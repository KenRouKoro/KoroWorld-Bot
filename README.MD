# KoroWorld-Bot 科洛的服务器支持-群服互联机器人
**用于在使用KoroWorld核心进行群组消息互联的服务器提供qq群与服务器之间的消息互传。**
### 功能
1. 基本机器人可操作性。
2. 转发服务核心内的消息。
3. 向服务核心发送消息（使用#say指令）
### 配置文件
**本应用本地文件均位于运行目录下的bot目录内**
bot.setting
```properties
#机器人QQ号
QQAccount = 
#机器人QQ密码
QQPassWorld = 
#QQ群群号
GroupID = 
#戳一戳反馈
NudgeText = 
#在群组中QQ群的服务器名
BotName =
#服务核心WS地址
WSServer = 
```
dervice.json

**这个是设备信息文件，详情请前往 https://github.com/mamoe/mirai/blob/dev/docs/Bots.md**

**在线生成自定义设备信息的 device.json: https://ryoii.github.io/mirai-devicejs-generator/**
### 特别鸣谢
**本项目使用mirai框架与QQ通信，在此特别感谢。https://github.com/mamoe/mirai**