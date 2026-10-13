import java.io.*;
import java.util.*;

public class ZooManager {
    private final String zooName = "Belfast City Zoo";
    private final ArrayList<Animal> animals = new ArrayList<>();

    public void addAnimal(Animal animal) {
        if (animal == null || !animal.isValid()) {
            System.out.println("Invalid animal. Details were not saved.");
            return;
        }

        if (findByName(animal.getName()) != null) {
            System.out.println("Animal with this name already exists. Details were not saved.");
            return;
        }

        animals.add(animal);
        System.out.println("Animal added successfully.");
    }

    public void viewAllAnimals() {
        if (animals.isEmpty()) {
            System.out.println("No animals currently registered.");
            return;
        }

        for (Animal animal : animals) {
            System.out.println(animal.getDetails());
            System.out.println(animal.makeSound());
            System.out.println();
        }
    }

    public void removeAnimal(String name) {
        Animal animal = findByName(name);

        if (animal != null) {
            animals.remove(animal);
            System.out.println("Animal removed successfully.");
        } else {
            System.out.println("Animal not found.");
        }
    }

    public void modifyAnimal(String name, Scanner input) {
        Animal animal = findByName(name);

        if (animal == null) {
            System.out.println("Animal not found.");
            return;
        }

        System.out.print("Enter new name: ");
        animal.setName(input.nextLine());

        animal.setAge(readInt(input, "Enter new age: "));

        System.out.print("Enter new colour: ");
        animal.setColour(input.nextLine());

        animal.setWeight(readDouble(input, "Enter new weight: "));

        System.out.println("Animal updated successfully.");
    }

    public void searchByName(String name) {
        Animal animal = findByName(name);

        if (animal != null) {
            System.out.println(animal.getDetails());
            System.out.println(animal.makeSound());
        } else {
            System.out.println("No animal found with that name.");
        }
    }

    public void searchByColour(String colour) {
        boolean found = false;

        for (Animal animal : animals) {
            if (animal.getColour().equalsIgnoreCase(colour)) {
                System.out.println(animal.getDetails());
                System.out.println(animal.makeSound());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No animals found with that colour.");
        }
    }

    public void dailyCare() {
        if (animals.isEmpty()) {
            System.out.println("No animals to care for.");
            return;
        }

        for (Animal animal : animals) {
            System.out.println("Feeding " + animal.getName() + ".");
            System.out.println("Cleaning enclosure for " + animal.getName() + ".");

            if (animal instanceof Flyable) {
                Flyable flyableAnimal = (Flyable) animal;
                flyableAnimal.wingHealthCheck();
                flyableAnimal.fly();
            }

            if (animal instanceof Swimmable) {
                Swimmable swimmableAnimal = (Swimmable) animal;
                swimmableAnimal.waterHealthCheck();
                swimmableAnimal.swim();
            }

            System.out.println();
        }
    }

    public void zooReport() {
        System.out.println("Zoo name: " + zooName);
        System.out.println("Total animals: " + animals.size());

        int lions = 0;
        int parrots = 0;
        int dolphins = 0;
        int penguins = 0;

        HashMap<String, Integer> colourCount = new HashMap<>();

        for (Animal animal : animals) {
            if (animal instanceof Lion) {
                lions++;
            } else if (animal instanceof Parrot) {
                parrots++;
            } else if (animal instanceof Dolphin) {
                dolphins++;
            } else if (animal instanceof Penguin) {
                penguins++;
            }

            String colour = animal.getColour().toLowerCase();
            colourCount.put(colour, colourCount.getOrDefault(colour, 0) + 1);
        }

        System.out.println("Lions: " + lions);
        System.out.println("Parrots: " + parrots);
        System.out.println("Dolphins: " + dolphins);
        System.out.println("Penguins: " + penguins);
        System.out.println("Dominant colour: " + getDominantColour(colourCount));
    }

    public void sortByName() {
        animals.sort(Comparator.comparing(Animal::getName));
        System.out.println("Animals sorted by name.");
    }

    public void sortByAge() {
        animals.sort(Comparator.comparingInt(Animal::getAge));
        System.out.println("Animals sorted by age.");
    }

    public void clearZoo() {
        animals.clear();
        System.out.println("All animals have been removed from the zoo.");
    }

    private String getDominantColour(HashMap<String, Integer> colourCount) {
        if (colourCount.isEmpty()) {
            return "None";
        }

        String dominantColour = "";
        int highestCount = 0;

        for (String colour : colourCount.keySet()) {
            if (colourCount.get(colour) > highestCount) {
                highestCount = colourCount.get(colour);
                dominantColour = colour;
            }
        }

        return dominantColour;
    }

    private Animal findByName(String name) {
        for (Animal animal : animals) {
            if (animal.getName().equalsIgnoreCase(name)) {
                return animal;
            }
        }

        return null;
    }

    public void saveToFiles() {
        try {
            PrintWriter zooWriter = new PrintWriter(new FileWriter("ZooDetails.txt"));
            zooWriter.println("Zoo name: " + zooName);
            zooWriter.println("Total animals: " + animals.size());
            zooWriter.close();

            PrintWriter animalWriter = new PrintWriter(new FileWriter("AnimalDetails.txt"));

            for (Animal animal : animals) {
                if (animal.isValid()) {
                    animalWriter.println(animal.toFileString());
                }
            }

            animalWriter.close();

            System.out.println("Zoo data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }

    public void loadFromFiles() {
        File file = new File("AnimalDetails.txt");

        if (!file.exists()) {
            return;
        }

        try {
            Scanner fileReader = new Scanner(file);

            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] parts = line.split(",");

                if (parts.length >= 6) {
                    String type = parts[0];
                    String name = parts[1];
                    int age = Integer.parseInt(parts[2]);
                    String colour = parts[3];
                    double weight = Double.parseDouble(parts[4]);
                    String extra = parts[5];

                    if (type.equalsIgnoreCase("Lion")) {
                        animals.add(new Lion(name, age, colour, weight, extra.replace("Mane size: ", "")));
                    } else if (type.equalsIgnoreCase("Parrot")) {
                        int words = Integer.parseInt(extra.replace("Words known: ", ""));
                        animals.add(new Parrot(name, age, colour, weight, words));
                    } else if (type.equalsIgnoreCase("Dolphin")) {
                        double speed = Double.parseDouble(extra.replace("Swim speed: ", "").replace(" mph", ""));
                        animals.add(new Dolphin(name, age, colour, weight, speed));
                    } else if (type.equalsIgnoreCase("Penguin")) {
                        animals.add(new Penguin(name, age, colour, weight, extra.replace("Favourite fish: ", "")));
                    }
                }
            }

            fileReader.close();
            System.out.println("Previous zoo data loaded.");
        } catch (Exception e) {
            System.out.println("Could not load previous data.");
        }
    }

    public int getAnimalCount() {
        return animals.size();
    }

    private int readInt(Scanner input, String message) {
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

    private double readDouble(Scanner input, String message) {
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