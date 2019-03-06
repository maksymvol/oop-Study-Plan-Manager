package educationPlanManager;

import java.time.LocalDate;

public interface TimeFrame {
    public boolean checkForThisDay(LocalDate currentDate);
}
