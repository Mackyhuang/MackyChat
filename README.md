# MackyChat
Instant Messaging Chat Based on Netty

基于Netty实现的即时通信服务端和客户端

实现了私聊和群聊功能，以及心跳检测、空闲检测，pipeline和同级handler优化


#### 软件架构
- server
    - handler
    - util
- client
    - handler
    - instruction
- protocol
    - package
        - request
        - response
     - packageProcess
        - decoder
        - encoder
 - serializer
 - enums
 - commonHandler
    


#### 使用说明
##### 将项目Pull下来以后，依次启动服务端和客户端
- login 

此操作于客户端连接时自动开启登录，此时输入用户名即可
```
Sun May 12 15:35:41 CST 2019: 连接成功，启动控制台线程……
输入用户名登录: macky
Sun May 12 15:36:29 CST 2019: 登录成功, 您的用户标识为：5ef6e530
```
此时，服务端返回一个用户标识，持有此标识才能进行下一步操作

- online user list

```
Sun May 12 15:38:17 CST 2019- 在线用户：
[5ef6e530] - 
[868e8e1f] - 
```
服务端的定时任务定时打印当前在线人数，可以看到这时候时2个人

- private 

我们尝试一次私聊
```
private
发送消息给某个某个用户：868e8e1f
Hello huang, I am macky
```
先输入 private 开启客户端的私聊指令，然后输入对方的用户标识，再输入需要发送的信息

让我们来看看对方接收到的信息
```
[5ef6e530] - [macky] : 
 Hello huang, I am macky
```

接下来是群聊
- group

这个指令创建一个群组
```
group
【拉人群聊】输入 userId 列表，userId 之间英文逗号隔开：868e8e1f
群创建成功，id 为[069efbd7], 群里面有：[huang]
```
输入group以后，输入需要邀请进入群组的人的用户标识即可，这里为了演示后续
的加入群组功能，所以 `huang` 只把自己拉入群组

可以看到服务器返回了一个群组的id，这是群组的操作表示

- join

现在我们让macky加入到这个群组
```
join
请输入您要加入的群聊ID：
069efbd7
加入群[069efbd7]成功!
```
依然是指令加操作的输入方式

- share

现在可以给群组发消息啦
```
share
输入需要发送的群组的groupId :
069efbd7
I am new
```
这里给群组发送到了一句话

与此同时，`macky` 和 `huang` 都收到了消息
```
收到群[069efbd7]中[{[5ef6e530]- macky}]发来的消息：I am new
```

- list

我想了解一下这个群组都有谁
```
list
请输入 groupId 以获取群组成员：
069efbd7
群[069efbd7]中的人包括：[{[868e8e1f]- huang}, {[5ef6e530]- macky}]
```

- quit

退出群组原来是这样做的
```
quit
请输入您要退出的群聊ID：
069efbd7
退出群聊[069efbd7]成功！
```

- logout

我要下线了朋友
```
logout
用户注销成功！
```



