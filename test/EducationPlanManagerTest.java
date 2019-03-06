import educationPlanManager.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class EducationPlanManagerTest {

    private Student student;
    private EducationPlan plan;
    private ArrayList<Activity> activities;

    @BeforeEach
    void setUp() {
        student = new Student(1, new Knowledge(0, 0));
        activities = new ArrayList<>();
        activities.add(new SelfEducationStrategy());
        plan = new EducationPlan(activities);
    }

    @Test
    void applyPlan__oneActivity__oneDay() {
        Knowledge result = plan.apply(student);
        assertThat(result.getPractical(), is(1.0));
        assertThat(result.getTheoretical(), is(1.0));
    }

    @Test
    void applyPlan__studentLearningCoefficient() {
        student = new Student(0.5, new Knowledge(0, 0));
        Knowledge result = plan.apply(student);
        assertThat(result.getPractical(), is(0.5));
        assertThat(result.getTheoretical(), is(0.5));
    }

    @Test
    void applyPlan__manyActivities__oneDay() {
        activities.add(new InternshipStrategy());
        activities.add(new UniversityStrategy());
        activities.add(new MeetUpStrategy());
        plan = new EducationPlan(activities);

        Knowledge result = plan.apply(student);
        assertThat(result.getPractical(), is(6.5));
        assertThat(result.getTheoretical(), is(6.5));
    }


}
