import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Callable 和 Future 创建线程
 * 1. 创建 Callable 接口的实现类，override call() 方法（方法的线程执行体，并且有返回）
 * 2. 创建实例，使用 FutureTask 类来包装 Callable 对象，该 FutureTask 对象封装了该 Callable 对象的 call() 方法的返回值
 * 3. 使用 FutureTask 对象作为 Thread 对象的 target 创建并启动新线程
 * 4. 调用 FutureTask 对象的 get() 方法来获取子线程执行结果后的返回值
 */
public class CallableThreadDemo implements Callable<Integer> {
    @Override
    public Integer call() {
        int i = 0;
        for (; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
        return i;
    }

    public static void main(String[] args) {
        CallableThreadDemo callableThreadDemo = new CallableThreadDemo();
        FutureTask<Integer> futureTask = new FutureTask<>(callableThreadDemo);
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " 的循环变量i的值" + i);
            if (i == 20) {
                new Thread(futureTask, "有返回的线程").start();
            }
        }
        try {
            System.out.println("子线程的返回" + futureTask.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
