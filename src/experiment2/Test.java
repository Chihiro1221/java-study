package experiment2;

public class Test {
    public static void main(String[] args) {
        int[] sizes = {10, 5, 9, 7, 4, 6, 7, 12, 50, 25}; // 进程所需大小
        OS os = new OS();
        for (int i = 0; i < sizes.length; i++) {
            // 进程名称 进程所需内存大小 所获得的分区
            Process p = new Process(new String((i + 1) + "号进程"), sizes[i], null);
            os.getProcessArrayList().add(p);
        }

        int[] areaSize = {100, 50, 40, 75, 200, 30, 90, 10, 27, 5, 80};// 分区大小
        int[] address = {10, 200, 270, 400, 500, 790, 850, 1000, 1500, 1800, 2000};// 分区开始地址
        for (int i = 0; i < address.length; i++) {
            //分区号 分区大小 分区开始地址 状态（1表示空闲、2表示忙、0表示碎片）
            Area a = new Area(new String((i + 1) + "号分区"), areaSize[i], address[i], 1);
            os.getAreaArrayList().add(a);
        }
        System.out.println(os);
        for (int i = 0; i < os.getProcessArrayList().size(); i++) {
            Process p = os.getProcessArrayList().get(i);
            os.distribution(p);
        }
        System.out.println(os);
        System.out.println("碎片分区数量: " + os.countFragment());
    }
}