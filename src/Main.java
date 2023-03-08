import java.sql.*;
import java.util.*;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    public static Connection connection;
    static {
        try {
            connection = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/poisepms?useSSL=false",
                    "otheruser", "hammertime");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        System.out.println("""
                What would you like to do(enter corresponding number):
                1: create a new project
                2: Update an existing project
                3: Delete a project and relevant personnel data
                4: Finalise existing projects
                5: Show all incomplete projects
                6: Show all overdue Projects
                7: find a specific project
                """);
        int pathChoice = sc.nextInt();
        switch (pathChoice) {
            case 1 -> DatabaseActions.createNewProject(connection);
            case 2 -> DatabaseActions.updateDatabase(connection);
            case 3 -> DatabaseActions.deleteProjectObjects(connection);
            case 4 -> DatabaseActions.finaliseExistingProjects(connection);
            case 5 -> DatabaseActions.incompleteProjects(connection);
            case 6 -> DatabaseActions.overdueProjects(connection);
            case 7 -> DatabaseActions.findAndSelectProject(connection);
        }
    }
}