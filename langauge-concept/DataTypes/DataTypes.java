class DataTypes {

    public static void main(String[] args) {
        short number1 = 1;
        byte number2 = 12;
        int number3 = 32;
        float number4 = 3.4F;
        double number5 = 4.234D;
        char character = 'N';
        boolean key = true;
        long number6 = 142323543123123L;

        final String test = "Test";
        // test = "Nihad";

        // Type Casting
        int numberTest = (int) number4;
        float numberTest2 = (float) number1;
        // System.out.println(numberTest);
        // System.out.println(Short.SIZE);
        // System.out.println(Float.SIZE);

        // int decreasedNumber = (int) number6;
        // System.out.println(decreasedNumber);

        String s1 = "Java";
        String s2 = s1;
        s1 = s1 + " Programming";
        System.out.println(s2);
    }
}