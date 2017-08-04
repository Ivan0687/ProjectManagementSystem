package homework.model.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project extends Model {

    @Column
    private String name;

    @Column
    private String description;

    //jdbc
    private int customerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer projectCustomer;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH, mappedBy = "developerProjects")
    private List<Developer> projectDevelopers;

    @Column
    private int cost;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Customer getProjectCustomer() {
        return projectCustomer;
    }

    public void setProjectCustomer(Customer projectCustomer) {
        this.projectCustomer = projectCustomer;
    }

    public List<Developer> getProjectDevelopers() {
        return projectDevelopers;
    }

    public void setProjectDevelopers(List<Developer> projectDevelopers) {
        this.projectDevelopers = projectDevelopers;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", projectCustomer=" + projectCustomer.getName() +
                ", projectDevelopers count =" + projectDevelopers.size() +
                ", cost=" + cost +
                '}';
    }
}
