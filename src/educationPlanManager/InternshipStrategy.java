package educationPlanManager;

import java.util.ArrayList;

public class InternshipStrategy extends Activity {
    public InternshipStrategy(ArrayList<TimeFrame> timeFrames) {
        super(timeFrames, 6);
    }

    public InternshipStrategy(TimeFrame timeFrame) {
        super(timeFrame, 6);
    }

    @Override
    public void teach(Student student) {
        student.addKnowledge(10, 5);
    }
}
