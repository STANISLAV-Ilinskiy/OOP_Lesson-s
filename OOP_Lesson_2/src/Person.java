import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Person implements Serializable {
    private static final long serialVersionUID = 1L;

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

    public List<Person> getAllChildren() {
        List<Person> children = new ArrayList<>();
        Person current = this.leftChild;
        while (current != null) {
            children.add(current);
            current = current.rightChild;
        }
        return children;
    }
}