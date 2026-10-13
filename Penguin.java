public class Penguin extends Animal implements Swimmable {
    private String favouriteFish;

    public Penguin(String name, int age, String colour, double weight, String favouriteFish) {
        super(name, age, colour, weight);
        this.favouriteFish = favouriteFish;
    }

    @Override
    public String makeSound() {
        return "Honk! I am " + getName() + ", a " + getAge() + " year old penguin.";
    }

    @Override
    public String getAnimalType() {
        return "Penguin";
    }

    @Override
    public String getExtraInfo() {
        return "Favourite fish: " + favouriteFish;
    }

    @Override
    public void swim() {
        System.out.println(getName() + " is swimming quickly.");
    }

    @Override
    public void waterHealthCheck() {
        System.out.println(getName() + " has had a water health check.");
    }

    public void setFavouriteFish(String favouriteFish) {
        this.favouriteFish = favouriteFish;
    }
}
