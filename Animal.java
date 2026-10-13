//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public abstract class Animal {
    private String name;
    private int age;
    private String colour;
    private double weight;

    public Animal(String name, int age, String colour, double weight) {
        this.name = name;
        this.age = age;
        this.colour = colour;
        this.weight = weight;
    }

    public abstract String makeSound();

    public abstract String getAnimalType();

    public abstract String getExtraInfo();

    public boolean isValid() {
        return name != null && !name.trim().isEmpty()
                && colour != null && !colour.trim().isEmpty()
                && age >= 0
                && weight > 0;
    }

    public String getDetails() {
        return getAnimalType() + " | Name: " + name
                + " | Age: " + age
                + " | Colour: " + colour
                + " | Weight: " + weight + "kg"
                + " | " + getExtraInfo();
    }

    public String toFileString() {
        return getAnimalType() + "," + name + "," + age + "," + colour + "," + weight + "," + getExtraInfo();
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getColour() {
        return colour;
    }

    public double getWeight() {
        return weight;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
