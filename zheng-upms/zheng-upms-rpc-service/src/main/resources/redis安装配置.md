Redis: /usr/local/redis-4.0.10（命令在/usr/local/bin目录下，端口6379）

tar xzf redis-4.0.10.tar.gz
cd redis-4.0.10
sudo make
sudo make install

src目录下的mkreleasehdr.sh，redis-benchmark， redis-check-rdb， redis-cli， redis-server拷贝到bin目录

redis-server    命令启动服务
redis-server redis.conf     指定配置启动
redis-cli客户端程序，连接与redis服务交互

启动server后，终端会提示端口和PID

关闭redis服务有时会报错：

SIGTERM received but errors trying to shut down the server, check the logs for more information

目前只能用 kill -9 PID







参考：[mac下redis安装、设置、启动停止](https://www.cnblogs.com/shoren/p/redis.html)