public class ZooUnitTests {
    public static void main(String[] args) {
        testAddAnimal();
        testMakeSound();
        System.out.println("All unit tests completed.");
    }

    public static void testAddAnimal() {
        ZooManager zoo = new ZooManager();
        Lion lion = new Lion("Leo", 5, "gold", 180.5, "large");

        zoo.addAnimal(lion);

        if (zoo.getAnimalCount() == 1) {
            System.out.println("testAddAnimal passed.");
        } else {
            System.out.println("testAddAnimal failed.");
        }
    }

    public static void testMakeSound() {
        Parrot parrot = new Parrot("Polly", 2, "green", 1.2, 20);

        String expected = "Squawk! I am Polly, a 2 year old parrot.";

        if (parrot.makeSound().equals(expected)) {
            System.out.println("testMakeSound passed.");
        } else {
            System.out.println("testMakeSound failed.");
        }
    }
}