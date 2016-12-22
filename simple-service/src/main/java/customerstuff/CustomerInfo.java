package customerstuff;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CustomerInfo {

    private String name;
    private long creditLimit;

    private static List<CustomerInfo> mydb = new ArrayList<>(Arrays.asList(
            new CustomerInfo("Fred", 1000),
            new CustomerInfo("Jim", 500)
    ));
    public CustomerInfo() {
    }

    public CustomerInfo(String name, long creditLimit) {
        this.name = name;
        this.creditLimit = creditLimit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(long creditLimit) {
        this.creditLimit = creditLimit;
    }

    @Override
    public String toString() {
        return "CustomerInfo{" + "name=" + name + ", creditLimit=" + creditLimit + '}';
    }

    public static List<CustomerInfo> getAllCustomer() {
        return mydb;
    }
    
    public static CustomerInfo getByPK(int pk) {
        return mydb.get(pk);
    }
    
    public static int insertNew(CustomerInfo newOne) {
        int rv = mydb.size();
        mydb.add(newOne);
        return rv;
    }
}
