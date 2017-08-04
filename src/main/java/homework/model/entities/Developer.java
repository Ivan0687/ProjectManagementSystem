package homework.model.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "developers")
public class Developer extends Model {

    @Column
    private String name;

    @Column
    private String surname;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Company developerCompany;

    @Column
    private int salary;


     // line for jdbc
    private int companyId;
    /*
    // line for jdbc
    private List<Integer> skillIds;
    // line for jdbc
    private List<Integer> projectIds;
    */

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinTable(name = "developer_skills",
            joinColumns = @JoinColumn(name = "developer_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private List<Skill> developerSkills;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinTable(name = "project_developers",
            joinColumns = @JoinColumn(name = "developer_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    private List<Project> developerProjects;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }


    //jdbc
    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

/*
    public List<Integer> getSkillIds() {
        return skillIds;
    }

    public void setSkillIds(List<Integer> skillIds) {
        this.skillIds = skillIds;
    }

    public List<Integer> getProjectIds() {
        return projectIds;
    }

    public void setProjectIds(List<Integer> projectIds) {
        this.projectIds = projectIds;
    }
    */

    //hibernate
    public Company getDeveloperCompany() {
        return developerCompany;
    }

    public void setDeveloperCompany(Company company) {
        this.developerCompany = company;
    }

    public List<Skill> getDeveloperSkills() {
        return developerSkills;
    }

    public void setDeveloperSkills(List<Skill> developerSkills) {
        this.developerSkills = developerSkills;
    }

    public List<Project> getDeveloperProjects() {
        return developerProjects;
    }

    public void setDeveloperProjects(List<Project> developerProjects) {
        this.developerProjects = developerProjects;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", company=" + developerCompany.getId() +
                ", salary=" + salary +
                ", skills count=" + developerSkills.size() +
                ", projects count=" + developerProjects.size() +
                '}';
    }
}
