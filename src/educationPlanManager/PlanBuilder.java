package educationPlanManager;

import java.time.LocalDate;
import java.util.ArrayList;

public class PlanBuilder {
    private LocalDate expirationDate;
    private ArrayList<Activity> activities;

    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate date;


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
        return new EducationPlan(activities, expirationDate);
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
            case ONE_DAY_MEETUP: return new OneDayTimeStrategy(date);
            case ONE_DAY_PER_MONTH: return new OneDayTimeStrategy(date);
            case WORKS_DAY: return new WorkingDaysTimeStrategy();
            case TIME_INTERVAL: return new IntervalTimeStrategy(startDate, endDate);
            case YEARS_INTERVAL: return new IntervalTimeStrategy(startDate, endDate);
        }
        return new EveryDayTimeStrategy();
    }

    public PlanBuilder university() {
        activities.add(new UniversityStrategy(new WorkingDaysTimeStrategy()));
        return this;
    }

    public PlanBuilder university(TimeStrategyType type) {
        activities.add(new UniversityStrategy(getTimeFrameByType(type)));
        return this;
    }

    public PlanBuilder meetUp(LocalDate date) {
        activities.add(new MeetUpStrategy(new OneDayTimeStrategy(date)));
        return this;
    }

    public PlanBuilder internship() {
        ArrayList<TimeFrame> frames = new ArrayList<>();
        frames.add(new WorkingDaysTimeStrategy());
        frames.add(new IntervalTimeStrategy(startDate, endDate));

        activities.add(new InternshipStrategy(frames));
        return this;
    }

    public PlanBuilder setTimeInterval(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        return this;
    }

    public PlanBuilder setDate(LocalDate date) {
        this.date = date;
        return this;
    }
}
