package homework.model.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "skills")
public class Skill extends Model {

    @Column
    private String name;

    @Column
    private String level;

    @Column
    private int tax;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "developerSkills")
    private List<Developer> developers;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public List<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<Developer> developers) {
        this.developers = developers;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", level='" + level + '\'' +
                ", tax=" + tax +
                '}';
    }
}
