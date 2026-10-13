public class Dolphin extends Animal implements Swimmable {
    private double swimSpeed;

    public Dolphin(String name, int age, String colour, double weight, double swimSpeed) {
        super(name, age, colour, weight);
        this.swimSpeed = swimSpeed;
    }

    @Override
    public String makeSound() {
        return "Click-click! I am " + getName() + ", a " + getAge() + " year old dolphin.";
    }

    @Override
    public String getAnimalType() {
        return "Dolphin";
    }

    @Override
    public String getExtraInfo() {
        return "Swim speed: " + swimSpeed + " mph";
    }

    @Override
    public void swim() {
        System.out.println(getName() + " is swimming in the pool.");
    }

    @Override
    public void waterHealthCheck() {
        System.out.println(getName() + " has had a water health check.");
    }

    public void setSwimSpeed(double swimSpeed) {
        this.swimSpeed = swimSpeed;
    }
}
