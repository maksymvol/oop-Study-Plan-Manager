package educationPlanManager;

import java.util.ArrayList;

public class EducationPlan {
    private ArrayList<Activity> activities;

    public EducationPlan(ArrayList<Activity> activities) {
        this.activities = activities;
    }

    public Knowledge apply(Student student) {
        for (Activity activity : activities) {
            activity.teach(student);
        }
        return student.getKnowledge();
    }
}
