import java.util.EnumSet;

enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

class Main {
    public static void main(String[] args) {
        for (Day day : EnumSet.allOf(Day.class)) {
            System.out.println(day);
        }
    }    
}
