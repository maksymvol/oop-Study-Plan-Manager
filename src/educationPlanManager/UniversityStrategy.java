package educationPlanManager;

import java.util.ArrayList;

public class UniversityStrategy extends Activity {
    public UniversityStrategy(ArrayList<TimeFrame> timeFrames) {
        super(timeFrames);
    }

    @Override
    public void teach(Student student) {
        student.addKnowledge(0.5, 0.5);
    }
}
