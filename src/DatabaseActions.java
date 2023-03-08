import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
public class DatabaseActions {
    private static final Scanner sc = new Scanner(System.in);
    // NEW PROJECT
    public static void createNewProject(Connection connection){

        Project project = ObjectCreators.createProjectObject();

        String arcName = project.getArchName();
        String projId = project.getProjectId();
        String contractorName = project.getContractorName();
        String clientName = project.getClientName();

        Payments payments = ObjectCreators.createPaymentObject(projId);
        Architect architect = ObjectCreators.createArchitectObject(arcName, projId);
        Contractor contractor = ObjectCreators.createContractorObject(contractorName, projId);
        Client client = ObjectCreators.createClientObject(clientName, projId);
        try {
            Statement statement = connection.createStatement();
            // insert project object attributes
            statement.executeUpdate
                    ("INSERT INTO projects VALUES('" + project.getProjectId() + "', '"
                            + project.getProjectName() + "', '" + project.getCity() + "', '"
                            + project.getStreet() + "', '" + project.getPostalCode() + "', '"
                            + project.getErfNumber() + "', '" + project.getBuildingType() + "', '"
                            + project.getArchName() + "', '" + project.getContractorName() + "', '"
                            + project.getClientName() + "', '" + project.getProjectStatus() + "'");
            // insert arc object attributes
            statement.executeUpdate
                    ("INSERT INTO architects VALUES('" + architect.getName() + "', '"
                            + architect.getProjectId()  + "', '" + architect.getEmail()  + "', '"
                            + architect.getTel()  + "', '" + architect.getAddress() + "'");
            // insert contractor object attributes
            statement.executeUpdate
                    ("INSERT INTO contractors VALUES('" + contractor.getName() + "', '"
                            + contractor.getProjectId()  + "', '" + contractor.getEmail()  + "', '"
                            + contractor.getTel()  + "', '" + contractor.getAddress() + "'");
            // Insert client object attributes
            statement.executeUpdate
                    ("INSERT INTO clients VALUES('" + client.getName() + "', '"
                            + client.getProjectId()  + "', '" + client.getEmail()  + "', '"
                            + client.getTel()  + "', '" + client.getAddress() + "'");
            // insert payment object attributes
            statement.executeUpdate
                    ("INSERT INTO payments VALUES('" + payments.getProjectId() + "', '"
                            + payments.getAmountQuoted()  + "', '" + payments.getAmountPayed()  + "', '"
                            + payments.getAmountOwed() + "'");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Information successfully added to Poise Database!");
    }
    //UPDATE DATABASE FIELDS
    public static void updateDatabase (Connection connection) {
        try{
            Statement statement = connection.createStatement();
            System.out.println("Select a Table where you would like to make an update (enter corresponding number):" + "\n" +
                    "1: Project Details" + "\n" +
                    "2: Architect Details" + "\n" +
                    "3: Contractor Details" + "\n" +
                    "4: Payment Details" +
                    "5: Client Details");
            int userInput = sc.nextInt();
            String tableSearch = null;
            if (userInput > 5){
                System.out.println("invalid input");
                updateDatabase(connection);
            }
            switch (userInput) {
                case 1 -> tableSearch = "projects";
                case 2 -> tableSearch = "architects";
                case 3 -> tableSearch = "contractors";
                case 4 -> tableSearch = "payments";
                case 5 -> tableSearch = "clients";
            }
            System.out.println("Please enter the projectId");
            String projId = sc.nextLine();
            String sqlProjectAtTable = "SELECT FROM " + tableSearch + " WHERE project_id = " + projId + " LIMIT 1";
            ResultSet rs = statement.executeQuery(sqlProjectAtTable);
            ResultSetMetaData rsmd = rs.getMetaData();
            int colCount = rsmd.getColumnCount();
            // add column names to an arraylist
            List<String> colNames = new ArrayList<>();
            for(int i = 1; i <= colCount; i++){
                colNames.add(rsmd.getColumnName(i));
            }
            // prints out the column names of selected table
            System.out.println("Which field value would you like to update: ");
            for (String colName: colNames) {
                System.out.println(colName + "\n");
            }
            String selectedColumn = sc.nextLine();
            System.out.print("Please enter your update here: ");
            String updatedInfo = sc.nextLine();
            statement.executeQuery
                    ("UPDATE " + tableSearch +
                         "SET " + selectedColumn + " = " + updatedInfo +
                         "WHERE project_id = " + projId);

            connection.close();
            statement.close();
            rs.close();
            System.out.print("Info Successfully updated");

        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    // DELETE PROJECTS AND RELATED OBJECTS
    public static void deleteProjectObjects(Connection connection){
        try{
            Statement statement = connection.createStatement();
            System.out.println("Please enter the projectId of the project you would like to remove from Poise: ");
            String projectId = sc.nextLine();
            //Delete from projects
            statement.executeQuery("DELETE FROM projects WHERE project_id = " + projectId);
            //Delete from architects
            statement.executeQuery("DELETE FROM architects WHERE project_id = " + projectId);
            //Delete from contractors
            statement.executeQuery("DELETE FROM contractors WHERE project_id = " + projectId);
            //Delete from payments
            statement.executeQuery("DELETE FROM payments WHERE project_id = " + projectId);
            // Delete from Clients
            statement.executeQuery("DELETE FROM clients WHERE project_id = " + projectId);

            connection.close();
            statement.close();
            System.out.println("project and associated persons have been successfully deleted.");

        } catch (SQLException e){
            e.printStackTrace();
        }

    }
    //FINALIZE PROJECTS
    public static void finaliseExistingProjects (Connection connection) {
        System.out.println("Please Enter the Project ID for the project you wish to finalise:");
        String projectId = sc.nextLine();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE projects SET status = 'completed' WHERE project_id = " + projectId);

            connection.close();
            statement.close();
            System.out.println("project successfully finalised");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    //GET ALL OVERDUE PROJECTS
    public static void overdueProjects(Connection connection) {
        String sqlGetOverdueProjects = "SELECT * FROM projects WHERE deadline < NOW()";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sqlGetOverdueProjects);
            while(rs.next()){
                String projectId = rs.getString("project_id");
                String projectName = rs.getString("project_name");
                java.util.Date deadline = rs.getDate("deadline");

                System.out.println(
                        "ID: " + projectId +
                                "\nName: " + projectName +
                                "\nDeadline: " + deadline +
                                "\n____________\n");
            }

            connection.close();
            statement.close();
            rs.close();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    // GET ALL INCOMPLETE PROJECTS
    public static void incompleteProjects(Connection connection){
        String sqlGetIncompleteProjects = "SELECT * FROM projects WHERE status != 'completed'";
        try{
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sqlGetIncompleteProjects);

            while (rs.next()){
                String projectId = rs.getString("project_id");
                String projectName = rs.getString("project_name");
                String projectStatus = rs.getString("status");
                Date deadline = rs.getDate("deadline");

                System.out.println(
                        "ID: " + projectId +
                        "\nName: " + projectName +
                        "\nDeadline: " + deadline +
                        "\nStatus: "+ projectStatus +
                        "\n____________\n");
            }

            connection.close();
            statement.close();
            rs.close();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    // FIND AND SELECT PROJECTS
    public static void findAndSelectProject(Connection connection){
        System.out.println("""
                Would you like to search by (enter corresponding number to select an option)
                1: project name
                2: ID""");
        int userOption = sc.nextInt();
        String sqlSearchAndSelect = null;

        if(userOption > 2){
            System.out.println("invalid option");
            findAndSelectProject(connection);
        } else if (userOption == 1) {
            System.out.print("Please enter the name of the project");
            String projName = sc.nextLine();
            sqlSearchAndSelect = "SELECT * FROM projects WHERE project_name = " + projName;
        } else if (userOption == 2) {
            System.out.print("Please enter the ID of the project");
            String projId= sc.nextLine();
            sqlSearchAndSelect = "SELECT * FROM projects WHERE project_name = " + projId;
        }

        try(connection){
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sqlSearchAndSelect);

            connection.close();
            statement.close();
            rs.close();
            System.out.println(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
