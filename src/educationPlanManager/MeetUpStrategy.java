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
        student.addKnowledge(2, 2);
    }
}
