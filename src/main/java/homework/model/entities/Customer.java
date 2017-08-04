package homework.model.entities;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer extends Model {

    @Column
    private String name;

    @Column
    private String city;

    @OneToMany(orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, mappedBy = "projectCustomer")
    private List<Project> customerProjects;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Project> getCustomerProjects() {
        return customerProjects;
    }

    public void setCustomerProjects(List<Project> customerProjects) {
        this.customerProjects = customerProjects;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
