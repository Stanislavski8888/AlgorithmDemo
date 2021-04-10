public class StringDemo {

    static String STR = "黄浩ABCabc";

    public static void main(String[] args) {
        StringDemo instance = new StringDemo();
        String result = instance.strToBinary(STR);
        System.out.println("result = " + result);
        System.out.println((char)(0x00D7));
    }

    private String strToBinary(String str) {
        char[] strChars = str.toCharArray();
        String result = "";
        for (int i = 0; i < strChars.length; i++) {
            System.out.println(strChars[i]);
            System.out.println(Integer.toBinaryString(strChars[i]));
            System.out.println(Integer.toHexString(strChars[i]));
            result += Integer.toBinaryString(strChars[i]);
        }

        return result;
    }
}
