package educationPlanManager;

public class UniversityStrategy implements Activity {
    @Override
    public void teach(Student student) {
        student.addKnowledge(0.5, 0.5);
    }
}
