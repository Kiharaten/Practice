package beans;

public class Rentals {

    private int id;
    private int number;
    private String date;
    private String diskName;
    private String friendName;
    private String statusName;
    private int count;

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public int getNumber() {
        return number;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getDate() {
        return date;
    }
    public void setDiskName(String diskName) {
        this.diskName = diskName;
    }
    public String getDiskName() {
        return diskName;
    }
    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }
    public String getFriendName() {
        return friendName;
    }
    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
    public String getStatusName() {
        return statusName;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public int getCount() {
        return count;
    }
}