//Abstract for all human entities in poise
abstract class Person implements IPerson {
    private final String name;
    private final String address;
    private final String email;
    private final String tel;
    private final String projectId;
    public Person(String name, String address, String email, String tel, String projectId) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.tel = tel;
        this.projectId = projectId;
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public String getAddress() {
        return address;
    }
    @Override
    public String getEmail() {
        return email;
    }
    @Override
    public String getTel() {
        return tel;
    }
    public String getProjectId() {
        return projectId;
    }
}