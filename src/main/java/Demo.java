import java.util.concurrent.*;

/**
 * 测试类
 * @author Stanley
 *
 */
public class Demo {
    public static void main(String[] args) {
        BlockingQueue<Runnable> bq = new ArrayBlockingQueue<Runnable>(1000);
        ThreadPoolExecutor po = new ThreadPoolExecutor(1,2,60,TimeUnit.MILLISECONDS,bq);
//        po.setRejectedExecutionHandler(ThreadPoolExecutor.CallerRunsPolicy);
//        ExecutorService pool = Executors.newFixedThreadPool(10);
//        final CountDownLatch count = new CountDownLatch(2);
        KeyFrame kf = new KeyFrame();
        kf.showPanel();
        for (int i = 0; i < 1000; i++) {
            po.submit(kf);
        }






//            try {
//                count.await();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            //关闭
//            if (!pool.isShutdown()){
//                pool.shutdown();
//            }
    }
}
