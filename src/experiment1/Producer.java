package experiment1;

import java.util.LinkedList;

public class Producer implements Runnable {
    private LinkedList<String> buffer = new LinkedList<>();
    private Integer capacity = 0;
    private static Integer serial_num = 0; // 商品序列号
    private String name = "";

    public Producer(String name, LinkedList<String> buffer, Integer capacity) {
        this.name = name;
        this.buffer = buffer;
        this.capacity = capacity;
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (buffer) {
                    // 如果生产的苹果已满则等待
                    if (buffer.size() == capacity) {
                        buffer.wait();
                    }
                    String apple = "苹果" + (++serial_num) + "号";
                    buffer.add(apple);
                    System.out.println(name + "生产：" + "[" + apple + "]");
                    // 释放锁，通知消费者可以吃了
                    buffer.notifyAll();
                }
                // 模拟生产时间
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
