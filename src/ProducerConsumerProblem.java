public class ProducerConsumerProblem implements Runnable {
    private final Integer CAPACITY = 10; // 容量
    private Integer mutex = 1; // 信号量
    private Integer empty = CAPACITY; // 空缓冲区数量
    private Integer full = 0; // 满缓冲区数量
    private String[] buffer = new String[CAPACITY]; // 缓冲区
    private Integer in = 0;
    private Integer out = 0;

    public static void main(String[] args) {
        Thread thread = new Thread(new ProducerConsumerProblem());
        thread.run();
    }

    public static void Swait(Integer... Si) {
        boolean flag = true;
        while (flag) {
            flag = false;
            // 遍历所有信号量有一个小于1表示资源被占用则阻塞
            for (Integer si : Si) {
                if (si < 1) flag = true;
            }
            // 如果所有信号量都是大于1表示资源都未被占用则进行分配
            if (flag == false) {
                for (Integer si : Si) si--;
            }
        }
    }

    public void Ssignal(Integer... Si) {
        for (Integer si : Si) {
            si++;
        }
    }

    public void producer() {
        do {
            String apple = "apple" + (in + 1) + "号";
            System.out.println("已生产:" + apple);
            Swait(empty, mutex);
            buffer[in] = apple;
            in = (in + 1) % CAPACITY;
            Ssignal(full, mutex);
        } while (true);
    }

    public void consumer() {
        do {
            Swait(full, mutex);
            String apple = buffer[out];
            System.out.println("已消费:" + apple);
            out = (out + 1) % CAPACITY;
            Ssignal(empty, mutex);
        } while (true);
    }

    @Override
    public void run() {
        ProducerConsumerProblem producerConsumerProblem = new ProducerConsumerProblem();
        producerConsumerProblem.producer();
        producerConsumerProblem.consumer();
    }
}
