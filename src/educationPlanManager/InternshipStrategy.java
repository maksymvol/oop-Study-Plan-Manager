package educationPlanManager;

public class InternshipStrategy implements Activity {
    @Override
    public void teach(Student student) {
        student.addKnowledge(3, 3);
    }
}
