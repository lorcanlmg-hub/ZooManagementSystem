public class Lion extends Animal {
    private String maneSize;

    public Lion(String name, int age, String colour, double weight, String maneSize) {
        super(name, age, colour, weight);
        this.maneSize = maneSize;
    }

    @Override
    public String makeSound() {
        return "Roar! I am " + getName() + ", a " + getAge() + " year old lion.";
    }

    @Override
    public String getAnimalType() {
        return "Lion";
    }

    @Override
    public String getExtraInfo() {
        return "Mane size: " + maneSize;
    }

    public void setManeSize(String maneSize) {
        this.maneSize = maneSize;
    }
}
