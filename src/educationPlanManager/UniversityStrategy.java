package educationPlanManager;

public class UniversityStrategy extends Activity {
    public UniversityStrategy(TimeFrame timeFrame) {
        super(timeFrame);
    }

    @Override
    public void teach(Student student) {
        student.addKnowledge(0.5, 0.5);
    }
}
