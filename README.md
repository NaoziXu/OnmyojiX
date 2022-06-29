OnmyojiX1.0
<a href="https://996.icu"><img src="https://img.shields.io/badge/link-996.icu-red.svg" alt="996.icu" /></a>
===
`OnmyojiX1.0`是一个基于`java`和`sikuliX1.1.1`实现的，针对阴阳师手游桌面版的进程控制辅助工具。主要功能是根据用户的配置完成阴阳师游戏中完全重复的游戏内容(目前支持八岐大蛇、业原火、觉醒和御灵)，解放玩家的双手去做更有意思的事情。至于为什么要叫这个名字，大概只是因为看起来比较帅气……  

之所以要写这个项目是因为一些很无奈的原因……  

自打开始玩阴阳师这个游戏，我每天都深切的体会到时间的宝贵，在欺骗自己中苟且的日子让人感到沮丧，有人说世事的煎熬在对自己还存有期待的时候，就会体现在生活的各个细节中，这句话我万分认同。为了改变自己，从很久前我就一直在和阴阳师的客服经理反应iOS跨Android转移账号的急切需求，但始终都没能等到那天。直到阴阳师桌面版推出的那天，我感到也许是时候了。  

由于每天的码农工作中总需要和测试同学打交道，久而久之我深切的感受到了测试工作的疾苦，是切身的感受到那种无助的痛苦。为什么看不懂程序的人要去测试代码？还谈什么鲁棒性可维护性？为了解救测试同学，从而迂回的解救我自己。在2017年8月某一天早晨阅读了一封令人崩溃的测试报告后，我决定跟这样的工作状态做个了断！在给所有接口开发测试用例、开发远程调用接口的反射测试平台全都失败之后，必须再加上自动化测试工具来解决测试工作的困难。就这样在公司产品姐姐的帮助下，我找到了sikuliX，比心。  

在通过文档基本了解了sikuliX并体验了阴阳师桌面版后，我就开始了OnmyojiX作为对UI级自动化测试的一次练习。

配置
---

由于sikuliX基于图像，所以对系统分辨率、阴阳师桌面版窗口大小、素材图片大小都有要求。这里推荐使用1920*1080的桌面分辨率，原始的阴阳师桌面版窗口大小以及\OnmyojiX\image\onmyoji\目录下的图片作为素材，若因显示设备造成图像识别异常，请自行截图替换目录下的图片素材。  

对于控制任务，\OnmyojiX\properties\config.properties中的custom配置有一些属性可供使用者自行控制任务的内容。  
>1.onmyoji.mode：任务模式，0为单人，1为队长，2为队员   
 2.onmyoji.member.number：队伍人数，在队长模式下生效，在2和3中选择  
 3.onmyoji.cycle.time：循环次数，以阴阳师桌面版中的进度配置作为标准决定  
 4.onmyoji.sleep.time：线程休眠时间，即为保留计算机性能，自动战斗期间不进行轮询图像匹配的时间    
 5.onmyoji.action.time：动作时间，即每一个游戏操作步骤的超时时间，超时则停止进程  
 6.onmyoji.lock：是否锁定阵容，true锁定，不执行准备步骤，false不锁定，跳过准备步骤  
 7.onmyoji.auto.refuse：是否自动拒绝，true拒绝，拒绝一切任务过程中的邀请，false不处理  

使用方法
---
即便OnmyojiX目前已经号称是1.0-RELEASE版本，但仍然只能通过控制台运行可执行jar包达到脚本控制的目的，在有生之年可能只有小概率会出现依赖Swing开发进程可视化窗口的版本，请大家不要抱有期待。  
由于sikuliX是基于openCV实现的基于图像的桌面控制工具，所以需要用户在运行OnmyojiX后将阴阳师桌面版窗口置于屏幕最前方。

前置工作：  
>1.安装java运行环境，推荐1.7及以上版本  
 2.安装sikuliX1.1.1  
 3.根据需要配置\OnmyojiX\properties\config.properties中的custom配置

开始任务：  
>1.启动阴阳师桌面版并将游戏界面调整到八岐大蛇、业原火、觉醒或御灵的战斗准备界面  
 2.将可以自动挂机通关的阵容配置在队伍中  
 3.使用管理员权限运行OnmyojiX.jar，推荐命令行模式  
 4.将阴阳师桌面版窗口置于当前桌面最前方  
 5.睡一觉起来收菜  
 
命令示例：
```sh
$ java -jar E:\OnmyojiX\OnmyojiX.jar  
```

声明
---

OnmyojiX是外部工具，使用外部工具是不被阴阳师官方认可的用户行为，所有因OnmyojiX导致的后果由使用者自行承担。  
OnmyojiX唯一的目的是使玩家从重复中解脱，去做些更有趣的事情，所以请自觉抵制与其相关的任何其他行为。 
 
感谢
---

谢谢一年以来在平安京一起砍大蛇凑碎片的各位。  
祝你在新一年的冒险中，乘风破浪，变成欧皇。
