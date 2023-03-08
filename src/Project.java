public class Project {
    String projectId;
    String projectName;
    String city;
    String street;
    int postalCode;
    int erfNumber;
    String buildingType;
    String archName;
    String contractorName;
    String projectStatus;
    String clientName;
    public Project(String projectId, String projectName, String city,
                   String street, int postalCode, int erfNumber,
                   String buildingType, String archName, String contractorName,
                   String projectStatus, String clientName) {

        this.projectId = projectId;
        this.projectName = projectName;
        this.city = city;
        this.street = street;
        this.postalCode = postalCode;
        this.erfNumber = erfNumber;
        this.buildingType = buildingType;
        this.archName = archName;
        this.contractorName = contractorName;
        this.projectStatus = projectStatus;
        this.clientName = clientName;
    }
    public String getProjectId() {
        return projectId;
    }
    public String getProjectName() {
        return projectName;
    }
    public String getCity() {
        return city;
    }
    public String getStreet() {
        return street;
    }
    public int getPostalCode() {
        return postalCode;
    }
    public int getErfNumber() {
        return erfNumber;
    }
    public String getBuildingType() {
        return buildingType;
    }
    public String getArchName() {
        return archName;
    }
    public String getContractorName() {
        return contractorName;
    }
    public String getProjectStatus() {
        return projectStatus;
    }
    public String getClientName() {
        return clientName;
    }
}
