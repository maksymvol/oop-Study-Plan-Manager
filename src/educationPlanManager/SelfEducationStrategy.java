package educationPlanManager;

public class SelfEducationStrategy extends Activity {
    public SelfEducationStrategy(TimeFrame timeFrame) {
        super(timeFrame);
    }

    @Override
    public void teach(Student student) {
        student.addKnowledge(1, 1);
    }
}
