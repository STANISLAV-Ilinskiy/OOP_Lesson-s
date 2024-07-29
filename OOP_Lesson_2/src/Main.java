import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FamilyTree familyTree = null;
        FamilyTreeFileHandler fileHandler = new FamilyTreeFileHandlerImpl();

        boolean running = true;
        while (running) {
            // Меню на русском языке
            System.out.println("\nМеню:");
            System.out.println("1. Создать новое дерево");
            System.out.println("2. Добавить человека");
            System.out.println("3. Получить всех детей человека");
            System.out.println("4. Вывести все древо");
            System.out.println("5. Сохранить дерево в файл");
            System.out.println("6. Загрузить дерево из файла");
            System.out.println("7. Выйти");
            System.out.print("Выберите опцию: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // считывание символа новой строки

            switch (option) {
                case 1:
                    // Создание нового древа
                    System.out.print("Введите ID корневого человека: ");
                    String rootId = scanner.nextLine();
                    System.out.print("Введите имя корневого человека: ");
                    String rootName = scanner.nextLine();
                    System.out.print("Введите пол корневого человека: ");
                    String rootGender = scanner.nextLine();

                    Person root = new Person(rootId, rootName, rootGender);
                    familyTree = new FamilyTree(root);

                    // Вопрос о наличии супруга у корневого человека
                    System.out.print("У корневого человека есть супруг(а)? (да/нет): ");
                    String hasSpouse = scanner.nextLine();
                    if (hasSpouse.equalsIgnoreCase("да")) {
                        System.out.print("Введите ID супруга: ");
                        String spouseId = scanner.nextLine();
                        System.out.print("Введите имя супруга: ");
                        String spouseName = scanner.nextLine();
                        System.out.print("Введите пол супруга: ");
                        String spouseGender = scanner.nextLine();

                        Person spouse = new Person(spouseId, spouseName, spouseGender);
                        familyTree.addSpouse(root, spouse);
                        System.out.println("Супруг(а) успешно добавлен(а).");
                    }
                    break;
                case 2:
                    // Добавление нового человека
                    if (familyTree == null) {
                        System.out.println("Пожалуйста, сначала создайте или загрузите дерево.");
                        break;
                    }
                    System.out.print("Введите ID родителя: ");
                    String parentId = scanner.nextLine();
                    Person parent = familyTree.findPersonById(parentId);

                    if (parent != null) {
                        System.out.print("Введите ID ребенка: ");
                        String childId = scanner.nextLine();
                        System.out.print("Введите имя ребенка: ");
                        String childName = scanner.nextLine();
                        System.out.print("Введите пол ребенка: ");
                        String childGender = scanner.nextLine();

                        Person child = new Person(childId, childName, childGender);
                        familyTree.addPerson(parent, child);
                        System.out.println("Ребенок успешно добавлен.");

                        // Вопрос о наличии супруга у ребенка
                        System.out.print("У ребенка есть супруг(а)? (да/нет): ");
                        String childHasSpouse = scanner.nextLine();
                        if (childHasSpouse.equalsIgnoreCase("да")) {
                            System.out.print("Введите ID супруга: ");
                            String spouseId = scanner.nextLine();
                            System.out.print("Введите имя супруга: ");
                            String spouseName = scanner.nextLine();
                            System.out.print("Введите пол супруга: ");
                            String spouseGender = scanner.nextLine();

                            Person spouse = new Person(spouseId, spouseName, spouseGender);
                            familyTree.addSpouse(child, spouse);
                            System.out.println("Супруг(а) успешно добавлен(а).");
                        }
                    } else {
                        System.out.println("Родитель не найден.");
                    }
                    break;
                case 3:
                    // Получение всех детей выбранного человека
                    if (familyTree == null) {
                        System.out.println("Пожалуйста, сначала создайте или загрузите дерево.");
                        break;
                    }
                    System.out.print("Введите ID человека: ");
                    String personId = scanner.nextLine();
                    List<Person> children = familyTree.getAllChildren(personId);

                    if (children.isEmpty()) {
                        System.out.println("Дети не найдены.");
                    } else {
                        System.out.println("Дети:");
                        for (Person child : children) {
                            System.out.println("ID: " + child.getId() + ", Имя: " + child.getName() + ", Пол: " + child.getGender());
                        }
                    }
                    break;
                case 4:
                    // Вывод всего древа
                    if (familyTree == null) {
                        System.out.println("Пожалуйста, сначала создайте или загрузите дерево.");
                        break;
                    }
                    System.out.println("Генеалогическое древо:");
                    familyTree.printTree();
                    break;
                case 5:
                    // Сохранение дерева в файл
                    if (familyTree == null) {
                        System.out.println("Пожалуйста, сначала создайте или загрузите дерево.");
                        break;
                    }
                    System.out.print("Введите имя файла для сохранения: ");
                    String saveFilename = scanner.nextLine();
                    try {
                        fileHandler.saveToFile(familyTree, saveFilename);
                        System.out.println("Дерево успешно сохранено в файл.");
                    } catch (IOException e) {
                        System.out.println("Ошибка при сохранении дерева: " + e.getMessage());
                    }
                    break;
                case 6:
                    // Загрузка дерева из файла
                    System.out.print("Введите имя файла для загрузки: ");
                    String loadFilename = scanner.nextLine();
                    try {
                        familyTree = fileHandler.loadFromFile(loadFilename);
                        System.out.println("Дерево успешно загружено из файла.");
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println("Ошибка при загрузке дерева: " + e.getMessage());
                    }
                    break;
                case 7:
                    running = false;
                    break;
                default:
                    System.out.println("Недопустимая опция. Пожалуйста, попробуйте снова.");
                    break;
            }
        }

        scanner.close();
    }
}
