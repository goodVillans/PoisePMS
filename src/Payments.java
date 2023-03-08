public class Payments {

    String projectId;
    int amountQuoted;
    int amountPayed;
    int amountOwed;

    public Payments(String projectId, int amountQuoted, int amountPayed, int amountOwed) {
        this.projectId = projectId;
        this.amountQuoted = amountQuoted;
        this.amountPayed = amountPayed;
        this.amountOwed = amountOwed;
    }

    public String getProjectId() {
        return projectId;
    }
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
    public int getAmountQuoted() {
        return amountQuoted;
    }
    public void setAmountQuoted(int amountQuoted) {
        this.amountQuoted = amountQuoted;
    }
    public int getAmountPayed() {
        return amountPayed;
    }
    public void setAmountPayed(int amountPayed) {
        this.amountPayed = amountPayed;
    }
    public int getAmountOwed() {
        return amountOwed;
    }
    public void setAmountOwed(int amountOwed) {
        this.amountOwed = amountOwed;
    }
}