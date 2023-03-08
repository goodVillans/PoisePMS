import java.util.Scanner;

public class ObjectCreators {
    private static final Scanner sc = new Scanner(System.in);

    // set status used to set a fixed input type.
    private static String setStatus(){
        String status = null;

        System.out.println("Has this project been completed?(type yes/no");
        String input = sc.nextLine();

        if (input.equalsIgnoreCase("yes")){
            status = "completed";
        } else if (input.equalsIgnoreCase("no")){
            status = "in progress";
        } else {
            System.out.println("Invalid Input");
            setStatus();
        }
        return status;
    }
    public static Project createProjectObject(){
        // for creation of new project
        System.out.print("Input ProjectID: ");
        String projId = sc.nextLine();

        System.out.print("Input Project Name: ");
        String projName = sc.nextLine();

        System.out.print("Input Site City: ");
        String city = sc.nextLine();

        System.out.print("Input Street Name: ");
        String street = sc.nextLine();

        System.out.print("Input Postal Code: ");
        int postalCode = sc.nextInt();

        System.out.print("Input ERF Number: ");
        int erfNumber = sc.nextInt();

        System.out.print("Input Building type: ");
        String buildingType = sc.nextLine();

        System.out.print("Input name of lead architect: ");
        String arcName = sc.nextLine();

        System.out.print("Input contractor Name: ");
        String contractorName = sc.nextLine();

        String projectStatus = setStatus();

        System.out.print("Input Client Name: ");
        String clientName = sc.nextLine();

        return new Project(
                projId, projName, city,
                street, postalCode, erfNumber,
                buildingType, arcName, contractorName,
                projectStatus, clientName
        );
    };
    public static Architect createArchitectObject(String arcName, String projectId){
        System.out.print("enter the following details for" + arcName + ": ");

        System.out.print("Email Address: ");
        String email = sc.nextLine();

        System.out.print("Contact Number: ");
        String tel = sc.nextLine();

        System.out.print("Physical Address: ");
        String address = sc.nextLine();

        return new Architect(arcName, projectId, email, tel, address);
    }
    public static Contractor createContractorObject(String contractorName, String projectId){
        System.out.print("enter the following details for" + contractorName + ": ");

        System.out.print("Email Address: ");
        String email = sc.nextLine();

        System.out.print("Contact Number: ");
        String tel = sc.nextLine();

        System.out.print("Physical Address: ");
        String address = sc.nextLine();

        return new Contractor(contractorName, projectId, email, tel, address);
    }
    public static Client createClientObject(String clientName, String projectId){
        System.out.print("enter the following details for" + clientName + ": ");

        System.out.print("Email Address: ");
        String email = sc.nextLine();

        System.out.print("Contact Number: ");
        String tel = sc.nextLine();

        System.out.print("Physical Address: ");
        String address = sc.nextLine();

        return new Client(clientName, projectId, email, tel, address);
    }
    public static Payments createPaymentObject(String projectId){
        System.out.println("Enter the following payment information for " + projectId + ": \n" );
        System.out.print("Quoted Amount: ");
        int quotedAmount = sc.nextInt();

        System.out.print("Amount Payed to date: ");
        int amountPayed = sc.nextInt();

        int remainingAmount = 0;
        if (amountPayed > quotedAmount){
            remainingAmount = amountPayed - quotedAmount;
        } else if (quotedAmount > amountPayed){
            remainingAmount = quotedAmount - amountPayed;
        }

        return new Payments(projectId, quotedAmount, amountPayed, remainingAmount);
    }
}
