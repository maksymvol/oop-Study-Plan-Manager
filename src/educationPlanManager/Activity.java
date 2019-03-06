package educationPlanManager;

public class Activity{

    private TimeFrame timeFrame;

    Activity(TimeFrame timeFrame) {
        this.timeFrame = timeFrame;
    }

    public void teach(Student student) {

    }

    public TimeFrame getTimeFrame() {
        return timeFrame;
    }
}
