import java.util.ArrayList;
import java.util.List;

public class Person {
    private String id;
    private String name;
    private String gender;
    private Person leftChild;
    private Person rightChild;
    private Person spouse;

    public Person(String id, String name, String gender) {
        this.id = id;
        this.name = name;
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public Person getLeftChild() {
        return leftChild;
    }

    public Person getRightChild() {
        return rightChild;
    }

    public Person getSpouse() {
        return spouse;
    }

    public void setSpouse(Person spouse) {
        this.spouse = spouse;
    }

    // Метод для добавления ребенка
    public void addChild(Person child) {
        if (this.leftChild == null) {
            this.leftChild = child;
        } else {
            Person current = this.leftChild;
            while (current.rightChild != null) {
                current = current.rightChild;
            }
            current.rightChild = child;
        }
    }

    // Метод для получения всех детей
    public List<Person> getAllChildren() {
        List<Person> children = new ArrayList<>();
        if (this.leftChild != null) {
            children.add(this.leftChild);
            Person current = this.leftChild;
            while (current.rightChild != null) {
                children.add(current.rightChild);
                current = current.rightChild;
            }
        }
        return children;
    }
}