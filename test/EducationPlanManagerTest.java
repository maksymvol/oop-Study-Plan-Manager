import educationPlanManager.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

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
        student.setHavingLaptop(true);
        activities = new ArrayList<>();
        activities.add(new SelfEducationStrategy(new EveryDayTimeStrategy()));
        plan = new EducationPlan(activities, currentDate);
    }

    @Test
    void applyPlan__oneActivity__oneDay() {
        Knowledge result = plan.apply(student, currentDate);
        assertThat(result.getPractical(), is(2.0));
        assertThat(result.getTheoretical(), is(2.0));
    }

    @Test
    void applyPlan__studentLearningCoefficient() {
        student = new Student(0.5, new Knowledge(0, 0));
        Knowledge result = plan.apply(student, currentDate);
        assertThat(result.getPractical(), is(1.0));
        assertThat(result.getTheoretical(), is(1.0));
    }

    @Test
    void applyPlan__manyActivities__oneDay() {
        activities.add(new InternshipStrategy(new EveryDayTimeStrategy()));
        activities.add(new UniversityStrategy(new EveryDayTimeStrategy()));
        activities.add(new MeetUpStrategy(new EveryDayTimeStrategy()));
        plan = new EducationPlan(activities, currentDate);

        Knowledge result = plan.apply(student, currentDate);
        assertThat(result.getPractical(), is(15.0));
        assertThat(result.getTheoretical(), is(10.0));
    }

    @Test
    void applyPlan__threeDays() {
        activities.add(new InternshipStrategy(new EveryDayTimeStrategy()));
        activities.add(new UniversityStrategy(new EveryDayTimeStrategy()));
        activities.add(new MeetUpStrategy(new EveryDayTimeStrategy()));
        plan = new EducationPlan(activities, currentDate.plusDays(2));

        Knowledge result = plan.apply(student, currentDate);
        assertThat(result.getPractical(), is(45.0));
        assertThat(result.getTheoretical(), is(30.0));
    }

    @Test
    void TimeFrames__oneDayTimeStrategy() {
        activities = new ArrayList<>();
        timeFrames = new ArrayList<>();
        timeFrames.add(new OneDayTimeStrategy(currentDate.plusDays(1)));
        activities.add(new MeetUpStrategy(timeFrames));
        plan = new EducationPlan(activities, currentDate.plusDays(6));

        Knowledge result = plan.apply(student, currentDate);
        assertThat(result.getPractical(), is(3.0));
        assertThat(result.getTheoretical(), is(3.0));
    }

    @Test
    void TimeFrames__workingDaysTimeStrategy() {
        activities = new ArrayList<>();
        timeFrames = new ArrayList<>();
        timeFrames.add(new WorkingDaysTimeStrategy());
        activities.add(new UniversityStrategy(timeFrames));
        plan = new EducationPlan(activities, currentDate.plusDays(6));

        Knowledge result = plan.apply(student, currentDate);
        assertThat(result.getPractical(), is(0.5));
        assertThat(result.getTheoretical(), is(0.5));
    }

    @Test
    void TimeFrames__IntervalTimeStrategy() {
        activities = new ArrayList<>();
        timeFrames = new ArrayList<>();
        timeFrames.add(new IntervalTimeStrategy(currentDate, currentDate.plusDays(4)));
        activities.add(new SelfEducationStrategy(timeFrames));
        plan = new EducationPlan(activities, currentDate.plusDays(7));

        Knowledge result = plan.apply(student, currentDate);
        assertThat(result.getPractical(), is(10.0));
        assertThat(result.getTheoretical(), is(10.0));
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
        assertThat(result.getPractical(), is(10.0));
        assertThat(result.getTheoretical(), is(10.0));
    }

    @Test
    void planBuilder__activityPriorities() {
        plan = new PlanBuilder()
                .setLimit(currentDate, 7)
                .setTimeInterval(currentDate, currentDate.plusDays(6))
                .internship()
                .selfEducation(TimeStrategyType.EVERY_DAY)
                .meetUp(currentDate.plusDays(1))
                .university()
                .build();
        Knowledge result = plan.apply(student, currentDate);
        assertThat(result.getPractical(), is(67.0));
        assertThat(result.getTheoretical(), is(42.0));
    }

    @Test
    void planBuilder__timeStrategyTypes__timeInterval() {
        plan = new PlanBuilder()
                .setLimit(currentDate, 7)
                .setTimeInterval(currentDate, currentDate.plusDays(4))
                .selfEducation(TimeStrategyType.TIME_INTERVAL)
                .build();
        Knowledge result = plan.apply(student, currentDate);
        assertThat(result.getPractical(), is(10.0));
        assertThat(result.getTheoretical(), is(10.0));
    }

    @Test
    void planBuilder__timeStrategyTypes__oneDayMeetUp() {
        plan = new PlanBuilder()
                .setLimit(currentDate, 7)
                .setDate(currentDate)
                .selfEducation(TimeStrategyType.ONE_DAY_MEETUP)
                .build();
        Knowledge result = plan.apply(student, currentDate);
        assertThat(result.getPractical(), is(2.0));
        assertThat(result.getTheoretical(), is(2.0));
    }

    @Test
    void planBuilder__timeStrategyTypes__oneDayPerMonth() {
        plan = new PlanBuilder()
                .setLimit(currentDate, 35)
                .setDate(currentDate)
                .university(TimeStrategyType.ONE_DAY_PER_MONTH)
                .meetUp(currentDate.plusDays(1))
                .build();
        Knowledge result = plan.apply(student, currentDate);
        assertThat(result.getPractical(), is(6.2));
        assertThat(result.getTheoretical(), is(6.2));
    }

    @Test
    void activities__meetUp__StudentWithoutLaptop() {
        student.setHavingLaptop(false);
        plan = new PlanBuilder()
                .setLimit(currentDate, 1)
                .meetUp(currentDate)
                .build();
        Knowledge result = plan.apply(student, currentDate);
        assertThat(result.getPractical(), is(0.0));
        assertThat(result.getTheoretical(), is(4.0));
    }

    @Test
    void planBuilder__timeStrategyTypes__noneActivityInterval() {
        plan = new PlanBuilder()
                .setLimit(currentDate, 7)
                .setTimeInterval(currentDate, currentDate.plusDays(2))
                .selfEducation(TimeStrategyType.NONE_ACTIVITY_INTERVAL)
                .build();
        Knowledge result = plan.apply(student, currentDate);
        assertThat(result.getPractical(), is(8.0));
        assertThat(result.getTheoretical(), is(8.0));
    }

    @Test
    void planBuilder__planTemplates() {
        System.out.println("=======Pacifist plan=======");
        student.setLearningCoefficient(0.2);
        Knowledge result = new PlanBuilder()
                .setLimitInYears(currentDate, 5)
                .pacifistPlan()
                .apply(student, currentDate);
        System.out.println("Practical points:" + result.getPractical() + "\nTheoretical points: " + result.getTheoretical());

        System.out.println("\n====Self education plan====");
        student.setLearningCoefficient(2.0);
        result = new PlanBuilder()
                .setLimitInYears(currentDate, 5)
                .selfEducationPlan()
                .apply(student, currentDate);
        System.out.println("Practical points:" + result.getPractical() + "\nTheoretical points: " + result.getTheoretical());

        System.out.println("\n==Teach me completely plan==");
        student.setLearningCoefficient(0.5);
        result = new PlanBuilder()
                .setLimitInYears(currentDate, 5)
                .teachMeCompletelyPlan()
                .apply(student, currentDate);
        System.out.println("Practical points:" + result.getPractical() + "\nTheoretical points: " + result.getTheoretical());

        System.out.println("\n==Responsible Student plan==");
        student.setLearningCoefficient(4.0);
        result = new PlanBuilder()
                .setLimitInYears(currentDate, 5)
                .responsibleStudentPlan()
                .apply(student, currentDate);
        System.out.println("Practical points:" + result.getPractical() + "\nTheoretical points: " + result.getTheoretical());
    }
}
