class Car {
    String name;
    int relasedYear;
    float speed;
    
    public Car(String name, int releasedYear, float speed) {
        this.name = name;
        this.relasedYear = releasedYear;
        this.speed = speed;
    }

    public float getSpeed() {
        return this.speed;
    }

    public int getRelasedYear() {
        return this.relasedYear;
    }

    public String getName() {
        return this.name;
    }
}


class Main {

    public static void main(String[] args) {
        // Car car1 = new Car();
        // car1.name = "Honda";
        // car1.relasedYear = 2001;
        // car1.speed = 180.75F;

        // Car car2 = new Car();
        // // car2 = car1;
        // // car2.name = "tEST";
        // // System.out.println(car2.getName());
        // System.out.println(car1.getName());

        Car car1 = new Car("Honda", 2001, 180.75f);

    }
}