package educationPlanManager;

public class InternshipStrategy extends Activity {
    public InternshipStrategy(TimeFrame timeFrame) {
        super(timeFrame);
    }

    @Override
    public void teach(Student student) {
        student.addKnowledge(3, 3);
    }
}
