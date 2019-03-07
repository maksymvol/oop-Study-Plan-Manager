package educationPlanManager;

import java.util.ArrayList;

public class MeetUpStrategy extends Activity {
    public MeetUpStrategy(ArrayList<TimeFrame> timeFrames) {
        super(timeFrames);
    }

    @Override
    public void teach(Student student) {
        student.addKnowledge(2, 2);
    }
}
