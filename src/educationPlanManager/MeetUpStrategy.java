package educationPlanManager;

import java.util.ArrayList;

public class MeetUpStrategy extends Activity {
    public MeetUpStrategy(ArrayList<TimeFrame> timeFrames) {
        super(timeFrames, 1);
    }

    public MeetUpStrategy(TimeFrame timeFrame) {
        super(timeFrame, 1);
    }

    @Override
    public void teach(Student student) {
        if (student.hasLaptop())
            student.addKnowledge(3, 3);
        else
            student.addKnowledge(0, 4);
    }
}
