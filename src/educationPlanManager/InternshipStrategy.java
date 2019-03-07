package educationPlanManager;

import java.util.ArrayList;

public class InternshipStrategy extends Activity {
    public InternshipStrategy(ArrayList<TimeFrame> timeFrames) {
        super(timeFrames);
    }

    @Override
    public void teach(Student student) {
        student.addKnowledge(3, 3);
    }
}
