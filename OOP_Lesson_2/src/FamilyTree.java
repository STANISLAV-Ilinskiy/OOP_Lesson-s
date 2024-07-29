import java.util.ArrayList;
import java.util.List;

public class FamilyTree {
    private Person root;

    public FamilyTree(Person root) {
        this.root = root;
    }

    public Person getRoot() {
        return root;
    }

    // Метод для добавления человека к дереву
    public void addPerson(Person parent, Person child) {
        parent.addChild(child);
    }

    // Метод для добавления супруга
    public void addSpouse(Person person, Person spouse) {
        person.setSpouse(spouse);
        spouse.setSpouse(person);
    }

    // Метод для поиска человека по ID
    public Person findPersonById(String id) {
        return findPersonById(root, id);
    }

    private Person findPersonById(Person current, String id) {
        if (current == null) {
            return null;
        }
        if (current.getId().equals(id)) {
            return current;
        }
        Person foundPerson = findPersonById(current.getLeftChild(), id);
        if (foundPerson == null) {
            foundPerson = findPersonById(current.getRightChild(), id);
        }
        return foundPerson;
    }

    // Метод для получения всех детей человека по его ID
    public List<Person> getAllChildren(String id) {
        Person person = findPersonById(id);
        if (person != null) {
            return person.getAllChildren();
        }
        return new ArrayList<>();
    }

    // Метод для вывода всего древа
    public void printTree() {
        printTree(root, 0);
    }

    private void printTree(Person person, int level) {
        if (person == null) {
            return;
        }
        for (int i = 0; i < level; i++) {
            System.out.print("  ");
        }
        System.out.println(person.getName() + " (" + person.getId() + ")");
        if (person.getSpouse() != null) {
            for (int i = 0; i < level; i++) {
                System.out.print("  ");
            }
            System.out.println("  Супруг: " + person.getSpouse().getName() + " (" + person.getSpouse().getId() + ")");
        }
        printTree(person.getLeftChild(), level + 1);
        Person sibling = person.getLeftChild();
        while (sibling != null) {
            sibling = sibling.getRightChild();
            printTree(sibling, level + 1);
        }
    }
}
