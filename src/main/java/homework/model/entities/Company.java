package homework.model.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "companies")
public class Company extends Model {

        @Column
    private String name;

    @Column
    private String city;

    @OneToMany (orphanRemoval = true, fetch = FetchType.EAGER, cascade = CascadeType.REFRESH, mappedBy = "developerCompany")
    private List<Developer> companyDevelopers;


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

    public List<Developer> getCompanyDevelopers() {
        return companyDevelopers;
    }

    public void setCompanyDevelopers(List<Developer> developers) {
        this.companyDevelopers = developers;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", developers count=" + companyDevelopers.size() +
                '}';
    }
}
