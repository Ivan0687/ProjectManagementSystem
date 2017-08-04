package homework.controllers;

import homework.dao.basicDao.DAO;
import homework.dao.basicDao.SkillDAO;
import homework.dao.hibernateDaoImpl.HibernateSkillDaoImpl;
import homework.model.entities.Skill;
import homework.dao.mySqlDaoImpl.SkillDAOImpl;

import static homework.model.utils.Utils.*;

public class SkillsController {

   // private SkillDAO<Integer, Skill> skillDAO = new SkillDAOImpl();
    private DAO<Integer, Skill> skillDAO = new HibernateSkillDaoImpl();

    public void showMenu() {

        while (true) {
            printBorder();
            System.out.println("Skills menu");
            System.out.println("1) Create skill");
            System.out.println("2) Find skill");
            System.out.println("3) Show all skills");
            System.out.println("4) Update skill");
            System.out.println("5) Delete Skill");
            System.out.println("6) Back to main menu");
            printBorder();

            Integer choice = provideIntInputStream();
            if (choice == null) {
                System.err.println("Not correct entered data, try again");
                break;
            } else {
                switch (choice) {
                    case 1:
                        createSkill();
                        break;
                    case 2:
                        findSkill();
                        break;
                    case 3:
                        showAllSkills();
                        break;
                    case 4:
                        updateSkill();
                        break;
                    case 5:
                        deleteSkill();
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("Enter correct choice.");
                }
            }
        }
    }

    private void createSkill() {
        Skill skill = new Skill();

        String skillName = provideStringInputStream("Enter skill name or press 'enter' to return to menu: ");
        if (isValidString(skillName))
            skill.setName(skillName);
        else return;

        String skillLevel = provideStringInputStream("Enter skill level or press 'enter' to return to menu: ");
        if (isValidString(skillLevel))
            skill.setLevel(skillLevel);
        else return;

        Integer tax = provideIntInputStreamWithMessage("Enter skill tax or press 'enter' to return to menu: ");

        if (tax != null)
            skill.setTax(tax);
        else return;

        skillDAO.create(skill);

    }

    private void findSkill() {
        Integer skillId = provideIntInputStreamWithMessage("Enter skill id or press 'enter' to return to menu: ");
        findSkill(skillId);
    }

    private Skill findSkill(Integer idSkill) {
        Skill skill = null;
        if (idSkill != null) {
            skill = skillDAO.read(idSkill);
            if (skill != null) {
                System.out.print("Skill with id = " + idSkill + " is: ");
                System.out.println(skill);
            } else
                System.out.println("Skill with id = " + idSkill + " doesn't exist");
        }
        return skill;
    }

    private void showAllSkills() {
        System.out.println("All skills in database: ");
        for (Skill skill : skillDAO.read()) {
            System.out.println(skill);
        }

    }

    private void updateSkill() {
        Integer idSkill = provideIntInputStreamWithMessage("Enter skill id you want to update or press 'enter' to return to menu: ");

        Skill skill = null;

        if (idSkill != null)
            skill = findSkill(idSkill);

        if (skill == null)
            return;

        String skillName = provideStringInputStream("Enter new skill name or press 'enter': ");
        if (isValidString(skillName))
            skill.setName(skillName);

        String skillLevel = provideStringInputStream("Enter new skill level or press 'enter': ");
        if (isValidString(skillLevel))
            skill.setLevel(skillLevel);

        Integer tax = provideIntInputStreamWithMessage("Enter new skill tax or press 'enter': ");
        if (tax != null)
            skill.setTax(tax);

        skillDAO.update(idSkill, skill);
    }

    private void deleteSkill() {
        Integer idSkill = provideIntInputStreamWithMessage("Enter skill id you want to delete or press 'enter' to return to menu: ");

        Skill skill = null;

        if (idSkill != null)
        skill = findSkill(idSkill);

        if (skill == null)
            return;

        skillDAO.delete(idSkill);

        System.out.println("Skill with id = " + idSkill + " deleted.");
    }
}
