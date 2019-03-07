package educationPlanManager;

import java.util.ArrayList;

public class SelfEducationStrategy extends Activity {
    public SelfEducationStrategy(ArrayList<TimeFrame> timeFrames) {
        super(timeFrames);
    }

    @Override
    public void teach(Student student) {
        student.addKnowledge(1, 1);
    }
}
