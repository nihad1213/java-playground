import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> cars = new ArrayList<>();
        cars.add("BMW");
        cars.add("Ford");
        cars.add("Toyota");
        cars.add("Audi");
        cars.add("BMW");
        cars.add("Ford");
        cars.add(0, "Mercedes");

        String car = cars.get(3);
        cars.remove(2);

        int count = cars.size();
        boolean empty = cars.isEmpty();

//        System.out.println(cars);
//        System.out.println(car);
//        System.out.println(count);
//        System.out.println(empty);

        HashSet<Integer> set = new HashSet<>();
        set.add(0);
        set.add(1);
        set.add(2);
        set.add(3);
        set.remove(0);
        int size = set.size();
        set.clear();
//        System.out.println(size);
//        System.out.println(set);

        HashMap<String, String> capitalCities = new HashMap<String, String>();

        capitalCities.put("England", "London");
        capitalCities.put("Germany", "Berlin");
        capitalCities.put("Norway", "Oslo");
        capitalCities.put("USA", "Washington DC");
        System.out.println(capitalCities);
    }
}