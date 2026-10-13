public class Parrot extends Animal implements Flyable {
    private int wordsKnown;

    public Parrot(String name, int age, String colour, double weight, int wordsKnown) {
        super(name, age, colour, weight);
        this.wordsKnown = wordsKnown;
    }

    @Override
    public String makeSound() {
        return "Squawk! I am " + getName() + ", a " + getAge() + " year old parrot.";
    }

    @Override
    public String getAnimalType() {
        return "Parrot";
    }

    @Override
    public String getExtraInfo() {
        return "Words known: " + wordsKnown;
    }

    @Override
    public void fly() {
        System.out.println(getName() + " is flying around the enclosure.");
    }

    @Override
    public void wingHealthCheck() {
        System.out.println(getName() + " has had a wing health check.");
    }

    public void setWordsKnown(int wordsKnown) {
        this.wordsKnown = wordsKnown;
    }
}
