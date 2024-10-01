import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sportlog {
    static class Activity {
        private String name;
        private int duration;
        private LocalDate date;

        Activity(String name, int duration, LocalDate date) {
            this.name = name;
            this.duration = duration;
            this.date = date;
        }

        String getName() {
            return name;
        }

        int getDuration() {
            return duration;
        }

        LocalDate getDate() {
            return date;
        }

        @Override
        public String toString() {
            return "Activity{" +
                    "name='" + name + " " +
                    ", duration=" + duration +
                    ", date=" + date +
                    '}';
        }
    }


    List<Activity> activities = new ArrayList<>();

    public void logActivity(String name, int duration, LocalDate date) {
        activities.add(new Activity(name, duration, date));
    }

    public void viewActivities() {
        for (Activity activity : activities) {
            System.out.println(activity);
        }
    }

    public int calculateTotalTimeForWeek() {
        LocalDate now = LocalDate.now();
        LocalDate startOfWeek = now.minusDays(now.getDayOfWeek().getValue() - 1);
        LocalDate endOfWeek = startOfWeek.plusDays(6);

        return activities.stream()
                .filter(activity -> !activity.date.isBefore(startOfWeek) && !activity.date.isAfter(endOfWeek))
                .mapToInt(activity -> activity.duration)
                .sum();
    }

    public static void main(String[] args) {
        Sportlog tracker = new Sportlog();
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        while (true) {
            System.out.println("1. Log Activity");
            System.out.println("2. View Activities");
            System.out.println("3. Calculate Total Time for Week");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (option == 1) {
                System.out.print("Enter activity name: ");
                String name = scanner.nextLine();
                System.out.print("Enter duration (in minutes): ");
                int duration = scanner.nextInt();
                System.out.print("Enter date (DD-MM-YYYY): ");
                String dateString = scanner.next();
                try {
                    LocalDate date = LocalDate.parse(dateString, formatter);
                    tracker.logActivity(name, duration, date);
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date format. Please use DD-MM-YYYY.");
                }
            } else if (option == 2) {
                tracker.viewActivities();
            } else if (option == 3) {
                int totalTime = tracker.calculateTotalTimeForWeek();
                System.out.println("Total time spent on sports this week: " + totalTime + " minutes");
            } else if (option == 4) {
                break;
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}