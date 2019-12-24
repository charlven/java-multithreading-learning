import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Callable �� Future �����߳�
 * 1. ���� Callable �ӿڵ�ʵ���࣬override call() �������������߳�ִ���壬�����з��أ�
 * 2. ����ʵ����ʹ�� FutureTask ������װ Callable ���󣬸� FutureTask �����װ�˸� Callable ����� call() �����ķ���ֵ
 * 3. ʹ�� FutureTask ������Ϊ Thread ����� target �������������߳�
 * 4. ���� FutureTask ����� get() ��������ȡ���߳�ִ�н����ķ���ֵ
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
            System.out.println(Thread.currentThread().getName() + " ��ѭ������i��ֵ" + i);
            if (i == 20) {
                new Thread(futureTask, "�з��ص��߳�").start();
            }
        }
        try {
            System.out.println("���̵߳ķ���" + futureTask.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
