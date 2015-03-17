public class Employee implements java.io.Serializable {
    private String name;
    private String address;
    private String empid;

    public Employee(String name, String address, String empid) {
        this.name = name;
        this.address = address;
        this.empid = empid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public setAddress(String address) {
        this.address = address
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }
}
