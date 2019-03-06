package educationPlanManager;

public class MeetUpStrategy implements Activity {
    @Override
    public void teach(Student student) {
        student.addKnowledge(2, 2);
    }
}
