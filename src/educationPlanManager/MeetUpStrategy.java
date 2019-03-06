package educationPlanManager;

public class MeetUpStrategy extends Activity {
    public MeetUpStrategy(TimeFrame timeFrame) {
        super(timeFrame);
    }

    @Override
    public void teach(Student student) {
        student.addKnowledge(2, 2);
    }
}
