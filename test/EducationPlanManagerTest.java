import educationPlanManager.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class EducationPlanManagerTest {

    private Student student;
    private EducationPlan plan;
    private ArrayList<Activity> activities;
    private ArrayList<TimeFrame> timeFrames;

    private LocalDate currentDate;

    @BeforeEach
    void setUp() {
        currentDate = LocalDate.of(2019, 3, 5);

        student = new Student(1, new Knowledge(0, 0));
        activities = new ArrayList<>();
        timeFrames = new ArrayList<>();
        timeFrames.add(new EveryDayTimeStrategy());
        activities.add(new SelfEducationStrategy(timeFrames));
        plan = new EducationPlan(activities, currentDate);
    }

    @Test
    void applyPlan__oneActivity__oneDay() {
        Knowledge result = plan.apply(student, currentDate);
        assertThat(result.getPractical(), is(1.0));
        assertThat(result.getTheoretical(), is(1.0));
    }

    @Test
    void applyPlan__studentLearningCoefficient() {
        student = new Student(0.5, new Knowledge(0, 0));
        Knowledge result = plan.apply(student, currentDate);
        assertThat(result.getPractical(), is(0.5));
        assertThat(result.getTheoretical(), is(0.5));
    }

    @Test
    void applyPlan__manyActivities__oneDay() {
        activities.add(new InternshipStrategy(timeFrames));
        activities.add(new UniversityStrategy(timeFrames));
        activities.add(new MeetUpStrategy(timeFrames));
        plan = new EducationPlan(activities, currentDate);

        Knowledge result = plan.apply(student, currentDate);
        assertThat(result.getPractical(), is(6.5));
        assertThat(result.getTheoretical(), is(6.5));
    }

    @Test
    void applyPlan__threeDays() {
        activities.add(new InternshipStrategy(timeFrames));
        activities.add(new UniversityStrategy(timeFrames));
        activities.add(new MeetUpStrategy(timeFrames));
        plan = new EducationPlan(activities, currentDate.plusDays(2));

        Knowledge result = plan.apply(student, currentDate);
        assertThat(result.getPractical(), is(19.5));
        assertThat(result.getTheoretical(), is(19.5));
    }

    @Test
    void TimeFrames__oneDayTimeStrategy() {
        activities = new ArrayList<>();
        timeFrames = new ArrayList<>();
        timeFrames.add(new OneDayTimeStrategy(currentDate.plusDays(1)));
        activities.add(new MeetUpStrategy(timeFrames));
        plan = new EducationPlan(activities, currentDate.plusDays(6));

        Knowledge result = plan.apply(student, currentDate);
        assertThat(result.getPractical(), is(2.0));
        assertThat(result.getTheoretical(), is(2.0));
    }

    @Test
    void TimeFrames__workingDaysTimeStrategy() {
        activities = new ArrayList<>();
        timeFrames = new ArrayList<>();
        timeFrames.add(new WorkingDaysTimeStrategy());
        activities.add(new UniversityStrategy(timeFrames));
        plan = new EducationPlan(activities, currentDate.plusDays(6));

        Knowledge result = plan.apply(student, currentDate);
        assertThat(result.getPractical(), is(2.5));
        assertThat(result.getTheoretical(), is(2.5));
    }

    @Test
    void TimeFrames__IntervalTimeStrategy() {
        activities = new ArrayList<>();
        timeFrames = new ArrayList<>();
        timeFrames.add(new IntervalTimeStrategy(currentDate, currentDate.plusDays(4)));
        activities.add(new SelfEducationStrategy(timeFrames));
        plan = new EducationPlan(activities, currentDate.plusDays(7));

        Knowledge result = plan.apply(student, currentDate);
        assertThat(result.getPractical(), is(5.0));
        assertThat(result.getTheoretical(), is(5.0));
    }

    @Test
    void TimeFrames__multipleTimeStrategies() {
        //10 days,
        //IntervalStrategy: cur + 6 = 7 days
        //Working days Strategy: 7 - 2 = 5 days
        activities = new ArrayList<>();
        timeFrames = new ArrayList<>();
        timeFrames.add(new IntervalTimeStrategy(currentDate, currentDate.plusDays(6)));
        timeFrames.add(new WorkingDaysTimeStrategy());
        activities.add(new SelfEducationStrategy(timeFrames));
        plan = new EducationPlan(activities, currentDate.plusDays(9));

        Knowledge result = plan.apply(student, currentDate);
        assertThat(result.getPractical(), is(5.0));
        assertThat(result.getTheoretical(), is(5.0));
    }
}
