package educationPlanManager;

public class SelfEducationStrategy implements Activity {
    @Override
    public void teach(Student student) {
        student.addKnowledge(1, 1);
    }
}
