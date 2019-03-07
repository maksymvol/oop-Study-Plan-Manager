package educationPlanManager;

import java.util.ArrayList;

public class UniversityStrategy extends Activity {
    public UniversityStrategy(ArrayList<TimeFrame> timeFrames) {
        super(timeFrames, 5);
    }

    public UniversityStrategy(TimeFrame timeFrame) {
        super(timeFrame, 5);
    }

    @Override
    public void teach(Student student) {
        student.addKnowledge(0.1, 0.1);
    }
}
