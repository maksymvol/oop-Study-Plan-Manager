package educationPlanManager;

import java.util.ArrayList;

public class Activity{

    private ArrayList<TimeFrame> timeFrames;

    Activity(ArrayList<TimeFrame> timeFrames) {
        this.timeFrames = timeFrames;
    }

    public void teach(Student student) {

    }

    public ArrayList<TimeFrame> getTimeFrames() {
        return timeFrames;
    }
}
