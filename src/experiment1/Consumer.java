package experiment1;

import java.util.LinkedList;

public class Consumer implements Runnable {
    private LinkedList<String> buffer = new LinkedList<>();
    private Integer capacity = 0;
    private String name = "";

    public Consumer(String name, LinkedList<String> buffer, Integer capacity) {
        this.buffer = buffer;
        this.capacity = capacity;
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (buffer) {
                try {
                    // 此时没有苹果则不能消费
                    if (buffer.size() == 0) buffer.wait();
                    // 消费链表中第一个苹果
                    String apple = buffer.removeFirst();
                    System.out.println(name + "消费：" + "<" + apple + ">" + "，仓库库存：" + buffer.size());
                    // 消费之后通知生产者可以生产了
                    buffer.notifyAll();
                    // 模拟消费时间
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
