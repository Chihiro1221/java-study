package experiment2;

import com.sun.xml.internal.ws.util.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OS {
    private ArrayList<Process> processArrayList = new ArrayList<>(); //进程队列
    private ArrayList<Area> areaArrayList = new ArrayList<>(); //分区数组（队列）

    /**
     * 分配
     *
     * @param p
     */
    public void distribution(Process p) {
        BestFit(p);//最佳适应算法
    }

    /**
     * 统计成为碎片的分区数量
     */
    public Long countFragment() {
        return areaArrayList.stream().filter(area -> area.getState() == 0).count();
    }

    /**
     * 最佳适应算法(Best  Fit)
     * 从全部空闲区中找出能满足作业要求的、且大小最小的空闲分区
     *
     * @param p
     */
    private void BestFit(Process p) {
        List<Area> areaList = areaArrayList.stream()
                .sorted(Comparator.comparingInt(Area::getSize))
                .collect(Collectors.toList());
        int size = p.getAreaSize();// 进程所需内存大小
        for (Area area : areaList) {
            if (area.getState() == 1 && area.commpare(size)) {
                p.setArea(new Area(area.getId(), size, area.getStartAddress(), 2));
                area.drawOut(size);
                break;
            }
        }
    }

    public ArrayList<Process> getProcessArrayList() {
        return processArrayList;
    }

    public ArrayList<Area> getAreaArrayList() {
        return areaArrayList;
    }

    @Override
    public String toString() {
//        return "OS [processArrayList=" + processArrayList + "\r\n areArrayList=" + areaArrayList + "\r\n next=" + "]\r\n";
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("----------------进程队列----------------\n");
        stringBuilder.append("进程名\t\t所需分区大小\t\t已分配分区\n");
        for (Process process : processArrayList) {
            stringBuilder.append(process.getName() + "\t\t");
            stringBuilder.append(process.getAreaSize() + "\t\t\t\t");
            stringBuilder.append((process.getArea() == null ? "暂无分配" : process.getArea()) + "\t\t\n");
        }

        stringBuilder.append("----------------分区队列----------------\n");
        stringBuilder.append("分区id\t\t分区大小\t\t分区始址\t\t分区状态(1表示空闲、2表示忙、0表示碎片)\n");
        List<Area> temp = areaArrayList.stream()
                .sorted(Comparator.comparingInt(Area::getSize))
                .collect(Collectors.toList());
        for (Area area : temp) {
            stringBuilder.append(area.getId() + "\t\t");
            int ads = area.getStartAddress();
            String adsString = new String(String.valueOf(ads));

            stringBuilder.append(area.getSize() + "\t\t\t");
            stringBuilder.append(area.getStartAddress() + "\t\t\t");
            if(adsString.length() < 4)      stringBuilder.append("\t");
            stringBuilder.append(area.getState() + "\t\t\n");
        }
        return stringBuilder.toString();
    }


}