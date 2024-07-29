import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FamilyTreeFileHandlerImpl implements FamilyTreeFileHandler {

    @Override
    public void saveToFile(FamilyTree familyTree, String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            Map<String, Person> personMap = new HashMap<>();
            serializeTree(familyTree.getRoot(), personMap);
            oos.writeObject(personMap);
        }
    }

    @Override
    public FamilyTree loadFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            Map<String, Person> personMap = (Map<String, Person>) ois.readObject();
            Person root = personMap.values().stream().findFirst().orElse(null); // Assuming the first person is the root
            return new FamilyTree(root);
        }
    }

    private void serializeTree(Person person, Map<String, Person> personMap) {
        if (person == null) {
            return;
        }
        personMap.put(person.getId(), person);
        serializeTree(person.getLeftChild(), personMap);
        serializeTree(person.getRightChild(), personMap);
        if (person.getSpouse() != null) {
            personMap.put(person.getSpouse().getId(), person.getSpouse());
        }
    }
}
