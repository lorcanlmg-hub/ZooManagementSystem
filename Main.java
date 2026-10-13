import java.util.Scanner;

public class Main {
    private static final Scanner input = new Scanner(System.in);
    private static final ZooManager zoo = new ZooManager();

    public static void main(String[] args) {
        zoo.loadFromFiles();

        boolean running = true;

        while (running) {
            printMenu();

            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1:
                    addAnimalMenu();
                    break;
                case 2:
                    zoo.viewAllAnimals();
                    break;
                case 3:
                    removeAnimalMenu();
                    break;
                case 4:
                    modifyAnimalMenu();
                    break;
                case 5:
                    searchMenu();
                    break;
                case 6:
                    zoo.dailyCare();
                    break;
                case 7:
                    zoo.zooReport();
                    break;
                case 8:
                    zoo.sortByName();
                    break;
                case 9:
                    zoo.sortByAge();
                    break;
                case 10:
                    clearZooMenu();
                    break;
                case 11:
                    zoo.saveToFiles();
                    running = false;
                    System.out.println("Program closed.");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("===== Zoo Management System =====");
        System.out.println("1. Add animal");
        System.out.println("2. View all animals");
        System.out.println("3. Remove animal");
        System.out.println("4. Modify animal");
        System.out.println("5. Search animal");
        System.out.println("6. Perform daily care");
        System.out.println("7. Zoo report");
        System.out.println("8. Sort animals by name");
        System.out.println("9. Sort animals by age");
        System.out.println("10. Clear zoo");
        System.out.println("11. Save and exit");
    }

    private static void addAnimalMenu() {
        System.out.println();
        System.out.println("Choose animal type:");
        System.out.println("1. Lion");
        System.out.println("2. Parrot");
        System.out.println("3. Dolphin");
        System.out.println("4. Penguin");

        int type = readInt("Animal type: ");

        System.out.print("Enter name: ");
        String name = input.nextLine();

        int age = readInt("Enter age: ");

        System.out.print("Enter colour: ");
        String colour = input.nextLine();

        double weight = readDouble("Enter weight in kg: ");

        switch (type) {
            case 1:
                System.out.print("Enter mane size: ");
                String maneSize = input.nextLine();
                zoo.addAnimal(new Lion(name, age, colour, weight, maneSize));
                break;
            case 2:
                int wordsKnown = readInt("Enter number of words known: ");
                zoo.addAnimal(new Parrot(name, age, colour, weight, wordsKnown));
                break;
            case 3:
                double swimSpeed = readDouble("Enter swim speed in mph: ");
                zoo.addAnimal(new Dolphin(name, age, colour, weight, swimSpeed));
                break;
            case 4:
                System.out.print("Enter favourite fish: ");
                String fish = input.nextLine();
                zoo.addAnimal(new Penguin(name, age, colour, weight, fish));
                break;
            default:
                System.out.println("Invalid animal type.");
        }
    }

    private static void removeAnimalMenu() {
        System.out.print("Enter animal name to remove: ");
        String name = input.nextLine();
        zoo.removeAnimal(name);
    }

    private static void modifyAnimalMenu() {
        System.out.print("Enter animal name to modify: ");
        String name = input.nextLine();
        zoo.modifyAnimal(name, input);
    }

    private static void searchMenu() {
        System.out.println("1. Search by name");
        System.out.println("2. Search by colour");

        int choice = readInt("Choose search type: ");

        if (choice == 1) {
            System.out.print("Enter name: ");
            String name = input.nextLine();
            zoo.searchByName(name);
        } else if (choice == 2) {
            System.out.print("Enter colour: ");
            String colour = input.nextLine();
            zoo.searchByColour(colour);
        } else {
            System.out.println("Invalid search option.");
        }
    }

    private static void clearZooMenu() {
        System.out.print("Are you sure you want to clear all animals? Enter yes to confirm: ");
        String confirm = input.nextLine();

        if (confirm.equalsIgnoreCase("yes")) {
            zoo.clearZoo();
        } else {
            System.out.println("Clear zoo cancelled.");
        }
    }

    private static int readInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                int number = Integer.parseInt(input.nextLine());

                if (number >= 0) {
                    return number;
                }

                System.out.println("Number must not be negative.");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private static double readDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                double number = Double.parseDouble(input.nextLine());

                if (number > 0) {
                    return number;
                }

                System.out.println("Number must be greater than 0.");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}