package experiment2;

public class Process {

    private String name;//进程名称
    private int areaSize;//进程所需内存大小
    private Area area;//所获得的分区

    public Process(String name, int areaSize, Area area) {
        super();
        this.name = name;
        this.areaSize = areaSize;
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAreaSize(int areaSize) {
        this.areaSize = areaSize;
    }

    public Area getArea() {
        return area;
    }

    public int getAreaSize() {
        return areaSize;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "Process [name=" + name + ", areaSize=" + areaSize + ", area=" + area + "]\r\n";
    }
}