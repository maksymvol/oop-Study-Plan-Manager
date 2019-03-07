package educationPlanManager;

import java.time.LocalDate;
import java.util.ArrayList;

public class PlanBuilder {
    private EducationPlan plan;
    private LocalDate expirationDate;
    private ArrayList<Activity> activities;


    /**
     * Gives priority for the activities in addition flow
     * E.x: new .selfEducation(...)
     *          .university(...)
     * Mean that selfEducation - 1st priority and university - 2nd
     */
    public PlanBuilder() {
        activities = new ArrayList<>();
    }

    public EducationPlan build() {
        plan = new EducationPlan(activities, expirationDate);
        return plan;
    }

    public PlanBuilder selfEducation(TimeStrategyType type) {
        activities.add(new SelfEducationStrategy(getTimeFrameByType(type)));
        return this;
    }

    public PlanBuilder setLimit(LocalDate startDate, int days) {
        this.expirationDate = startDate.plusDays(days - 1);
        return this;
    }

    private TimeFrame getTimeFrameByType(TimeStrategyType type) {
        switch (type) {
            case EVERY_DAY: return new EveryDayTimeStrategy();
            //case ONE_DAY_MEETUP: return new OneDayTimeStrategy();
        }
        return new EveryDayTimeStrategy();
    }

    public PlanBuilder university() {
        activities.add(new UniversityStrategy(new WorkingDaysTimeStrategy()));
        return this;
    }

    public PlanBuilder meetUp(LocalDate date) {
        activities.add(new MeetUpStrategy(new OneDayTimeStrategy(date)));
        return this;
    }

    public PlanBuilder internship() {
        activities.add(new InternshipStrategy(new WorkingDaysTimeStrategy()));
        return this;
    }
}
