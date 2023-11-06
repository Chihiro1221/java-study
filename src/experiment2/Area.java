package experiment2;

public class Area {

    private String id;//分区号
    private int size;//分区大小
    private int startAddress;//分区开始地址
    /**
     * 状态=（空闲、忙、碎片（2K））
     * 1表示空闲、2表示忙、0表示碎片
     */
    private int state;

    //判断进程与内存分区是否合适
    public Area(String id, int size, int startAddress, int state) {
        super();
        this.id = id;
        this.size = size;
        this.startAddress = startAddress;
        this.state = state;
    }

    public boolean commpare(int size) {
        return this.size >= size;
    }

    //划出合适的内存
    public void drawOut(int size) {
        this.size -= size;
        this.startAddress += size;
        if (this.size <= 2 && this.size > 0) {
            this.state = 0;//碎片
        }
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setStartAddress(int startAddress) {
        this.startAddress = startAddress;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public int getSize() {
        return size;
    }


    public int getStartAddress() {
        return startAddress;
    }

    public int getState() {
        return state;
    }

    @Override
    public String toString() {
        return "Area [id=" + id + ", size=" + size + ", startAddress=" + startAddress + ", state=" + state + "]\r\n";
    }

}