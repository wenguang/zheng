
现实的应用很多都依赖于系统时间，ID发号器中要用系统时间来确定唯一的ID，如SnowFlake算法，它用分布式系统产生唯一的ID，分布式系统的压力一般是很大的，可能会一毫秒内同时产生成千上百的订单ID，每调用一次System.currentTimeMillis()方法要与系统打一次交道，它是性能可能是不够用的。

在我的机器上分别调用System.currentTimeMillis、优化后的SystemClock各一亿次，测试结果以下
System.currentTimeMillis：1亿次耗时：3175毫秒, 一次耗时：3.175E-5毫秒
SystemClock.now：1亿次耗时：50毫秒, 一次耗时：5.0E-7毫秒

SystemClock是用缓存的方法对System.currentTimeMillis进行了优化，从结果上看，即便不优化，一般应用也是够用的。

下面说下SystemClock的优化思想：
```
ExecutorService executor = Executors.newSingleThreadExecutor();

...

private void scheduleClockUpdating() {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable, "System Clock");
                thread.setDaemon(true);
                return thread;
            }
        });
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                now.set(System.currentTimeMillis());
            }
        }, period, period, TimeUnit.MILLISECONDS);
    }
```
ExecutorService相当于一个线程池，newSingleThreadScheduledExecutor建立一个只有一个线程的线程池，这个线程被设置为守卫线程，scheduleAtFixedRate定时调用System.currentTimeMillis获取系统时间缓存在now变量中，now变量是AtomicLong类，保证了原子访问。