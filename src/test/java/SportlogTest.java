import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SportlogTest {

    private Sportlog sportlog;

    @BeforeEach
    public void setUp() {
        sportlog = new Sportlog();
    }

    @Test
    public void testLogActivity() {

        String activityName = "Running";
        int duration = 30;
        LocalDate date = LocalDate.now();


        sportlog.logActivity(activityName, duration, date);


        assertEquals(1, sportlog.activities.size());
        assertEquals(activityName, sportlog.activities.get(0).getName());
        assertEquals(duration, sportlog.activities.get(0).getDuration());
        assertEquals(date, sportlog.activities.get(0).getDate());
    }

    @Test
    public void testCalculateTotalTimeForWeek() {
        // Arrange
        LocalDate today = LocalDate.now();
        sportlog.logActivity("Running", 30, today);
        sportlog.logActivity("Cycling", 60, today.minusDays(1));
        sportlog.logActivity("Swimming", 45, today.minusWeeks(1));


        int totalTime = sportlog.calculateTotalTimeForWeek();


        assertEquals(90, totalTime);
    }

    @Test
    public void testViewActivities() {

        LocalDate today = LocalDate.now();
        sportlog.logActivity("Running", 30, today);


        sportlog.viewActivities();
    }
}