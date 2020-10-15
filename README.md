# QuartzDemo
a demo for QuartzJob with real time management
前言：如果遇到quartz加载两次任务，可以参考是tomcat加载了两次你的项目
```xml
<!--配置context后。项目不要放在webapps目录下，否则会加载两次  -->
      <Host name="localhost" appBase="webapps" 
            unpackWARs="true" autoDeploy="true">
	<Context path="" docBase="/**/tomcat7-backstage/backweb/cardappbackstage" debug="0"/>

        <Valve className="org.apache.catalina.valves.AccessLogValve" directory="logs"
               prefix="localhost_access_log." suffix=".txt"
               pattern="%h %l %u %t "%r" %s %b" />
```
![](https://img2020.cnblogs.com/blog/1162521/202005/1162521-20200514212003574-1443671196.png)
![](https://img2020.cnblogs.com/blog/1162521/202005/1162521-20200514212049321-903446198.png)
![](https://img2020.cnblogs.com/blog/1162521/202005/1162521-20200514212109360-408337931.png)
