class Vehicle {
    int speed;

    void start() {
        System.out.println("Vehicle started");
    }
}

class Car extends Vehicle {
    String model;

    void honk() {
        System.out.println("Car honks");
    }
}


class Inheritance {

    public static void main(String[] args) {
        Car myCar = new Car();
        myCar.speed = 100;
        myCar.start();
        myCar.model = "BMW";
        myCar.honk(); 
    }
}