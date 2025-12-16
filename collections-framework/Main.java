import java.util.*;

class Main {

    public static void main(String[] args) {
        // List
        List<String> list = new ArrayList<>(50);
        list.add("Nihad");
        list.add("Ali");
        list.add("User");
        list.add("Leyla");
        list.add("Test");
        list.add(1, "Veli");


        String name = list.get(2);
        list.remove("User");
        list.remove(4);

        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(5);
        integers.add(6);
        integers.add(7);

        System.out.println(integers.isEmpty());

        // System.out.println(list);
        // System.out.println(name);
    }
}