**Snowflake算法为分布式系统生成唯一ID**

![](https://github.com/wenguang/lrc-util/blob/master/snowbflake-64bit.png?raw=true)

10bit的工作机器id分为 5位数据中心id + 5位机器id

![](https://github.com/wenguang/lrc-util/blob/master/snowflake.png?raw=true)

（1）1位：标识部分。正数是 0，负数是1，一般生成的ID为正数，所以为0；

（2）41位：时间戳部分。这个是毫秒级的时间。一般实现上不会存储当前的时间戳，而是时间戳的差值（当前时间-固定的开始时间），这样可以使产生的 ID 从更小值开始；41 位的时间戳可以使用 69 年，(1L << 41) / (1000L *60* 60 *24* 365) = 69年；

（3）10位：数据中心节点部分。Twitter 实现中使用前 5 位作为数据中心标识，后 5 位作为机器标识，可以部署 1024 个节点；

（4）12位：序列号部分。支持同一毫秒内同一个节点可以生成 4096 个ID；

SnowFlake 算法生成的 ID 大致上是按照时间递增的。用在分布式系统中时，需要注意数据中心标识和机器标识必须唯一。这样就能保证每个节点生成的 ID 都是唯一的！



**snowflake算法的调整** 

![](https://github.com/wenguang/lrc-util/blob/master/snowbflake-mod.png?raw=true)







**时间回拨问题**

在分布式系统中，为了保证业务的一致性，需要各个机器用统一时间，会出现服务器时间同步的操作，这是免不了会把某些机器的系统时间回拨，那snowflake产生的ID就可能会出现重复。



**时间回拨问题的解决方法**

1. 当回拨时间小于15ms，就等时间追上来之后继续生成。
2. 当时间大于15ms时间我们通过**更换workid**来产生之前都没有产生过的来解决回拨问题。



**百度关于回拨问题的解决方案** 

https://github.com/wenguang/uid-generator/blob/master/README.zh_cn.md





[谈一谈 ID 发号器原理及期使用场景](https://juejin.im/entry/5c39e38fe51d4503834d4df0)

[分布式唯一id：snowflake算法思考](https://juejin.im/post/5a7f9176f265da4e721c73a8)

