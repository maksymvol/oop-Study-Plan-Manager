package educationPlanManager;

import java.util.ArrayList;

public class Activity{

    private ArrayList<TimeFrame> timeFrames;
    private int intensivityCoefficient;

    Activity(ArrayList<TimeFrame> timeFrames, int intensivityCoefficient) {
        this.timeFrames = timeFrames;
        this.intensivityCoefficient = intensivityCoefficient;
    }

    public Activity(TimeFrame timeFrame, int intensivityCoefficient) {
        timeFrames = new ArrayList<>();
        timeFrames.add(timeFrame);
        this.intensivityCoefficient = intensivityCoefficient;
    }

    public void teach(Student student) {

    }

    int getDayIntensivityCoefficient() {
        return intensivityCoefficient;
    }

    ArrayList<TimeFrame> getTimeFrames() {
        return timeFrames;
    }
}
