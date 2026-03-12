import java.util.*;

class Notice {
    String title;
    String description;
    String priority;
    String expiry;
    String channel;

    Notice(String t, String d, String p, String e, String c) {
        title = t;
        description = d;
        priority = p;
        expiry = e;
        channel = c;
    }

    void display() {
        System.out.println("\nTitle: " + title);
        System.out.println("Description: " + description);
        System.out.println("Priority: " + priority);
        System.out.println("Expiry Date: " + expiry);
        System.out.println("Channel: " + channel);
    }
}

public class DigitalNoticeBoard {

    static ArrayList<Notice> notices = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {

            removeExpired();

            System.out.println("\n===== DIGITAL NOTICE BOARD =====");
            System.out.println("1. Add Notice");
            System.out.println("2. View Notices");
            System.out.println("3. Delete Notice");
            System.out.println("4. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addNotice();
                    break;

                case 2:
                    viewNotices();
                    break;

                case 3:
                    deleteNotice();
                    break;

                case 4:
                    System.out.println("System Closed");
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    static void addNotice() {

        System.out.print("Enter Title: ");
        String title = sc.nextLine();

        System.out.print("Enter Description: ");
        String desc = sc.nextLine();

        System.out.print("Enter Priority (High/Medium/Low): ");
        String priority = sc.nextLine();

        System.out.print("Enter Expiry Date (YYYYMMDD): ");
        String expiry = sc.nextLine();

        System.out.print("Enter Channel (College/Office/Apartment): ");
        String channel = sc.nextLine();

        Notice n = new Notice(title, desc, priority, expiry, channel);
        notices.add(n);

        sortNotices();

        System.out.println("Notice Added Successfully!");
    }

    static void viewNotices() {

        if (notices.isEmpty()) {
            System.out.println("No notices available.");
            return;
        }

        int i = 1;

        for (Notice n : notices) {
            System.out.println("\nNotice " + i);
            n.display();
            i++;
        }
    }

    static void deleteNotice() {

        viewNotices();

        if (notices.isEmpty())
            return;

        System.out.print("\nEnter notice number to delete: ");
        int num = sc.nextInt();

        if (num > 0 && num <= notices.size()) {
            notices.remove(num - 1);
            System.out.println("Notice Deleted Successfully!");
        } else {
            System.out.println("Invalid notice number.");
        }
    }

    static void sortNotices() {

        Collections.sort(notices, (a, b) -> {
            return priorityValue(a.priority) - priorityValue(b.priority);
        });
    }

    static int priorityValue(String p) {

        if (p.equalsIgnoreCase("High"))
            return 1;
        if (p.equalsIgnoreCase("Medium"))
            return 2;
        return 3;
    }

    static void removeExpired() {

        int today = Integer.parseInt(new java.text.SimpleDateFormat("yyyyMMdd").format(new Date()));

        notices.removeIf(n -> Integer.parseInt(n.expiry) < today);
    }
}