/**
 * (��򵥵ķ�ʽ)ʵ�� Runnable �ӿڷ�ʽ
 *
 * @overwrite run()
 */
public class RunnableDemo implements Runnable {
    private Thread t;
    private String threadName;

    public RunnableDemo(String name) {
        threadName = name;
        System.out.println("Creating " + name);
    }

    @Override
    public void run() {
        System.out.println("Running " + threadName);
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println("Thread: " + threadName + ", " + i);
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread " + threadName + " interrupted.");
        }
    }

    public void start() {
        System.out.println("Starting " + threadName);
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }

    public static void main(String[] args) {
        RunnableDemo r1 = new RunnableDemo("�߳�1");
        r1.start();

        RunnableDemo r2 = new RunnableDemo("�߳�2");
        r2.start();
    }
}

