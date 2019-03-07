package educationPlanManager;

import java.util.ArrayList;

public class SelfEducationStrategy extends Activity {
    public SelfEducationStrategy(ArrayList<TimeFrame> timeFrames) {
        super(timeFrames, 3);
    }

    public SelfEducationStrategy(TimeFrame timeFrame) {
        super(timeFrame, 3);
    }

    @Override
    public void teach(Student student) {
        student.addKnowledge(2, 2);
    }
}
