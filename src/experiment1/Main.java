package experiment1;

import java.util.LinkedList;

public class Main {
    private static final Integer CAPACITY = 10; // 容量
    private static Integer number = 0;

    public static void main(String[] args) {
        // 缓冲区
        LinkedList<String> buffer = new LinkedList<>();
        for (int i = 0; i < 2; ++i) {
            Thread producerProcess = new Thread(new Producer("生产者1号", buffer, CAPACITY));
            Thread producer2Process = new Thread(new Producer("生产者2号", buffer, CAPACITY));
            producerProcess.start();
            producer2Process.start();
        }
        Thread consumerProcess = new Thread(new Consumer("消费者1号", buffer, CAPACITY));
        consumerProcess.start();
    }
}