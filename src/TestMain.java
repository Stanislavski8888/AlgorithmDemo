
public class TestMain {
    static String str = "我是中国人";

    public static void main(String[] args) {
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("java.class.path"));

        System.out.println(ClassLoader.getSystemClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader().getParent());
        System.out.println(ClassLoader.getSystemClassLoader().getParent().getParent());

        System.out.println(false ^ false);
        System.out.println(true & true);

        System.out.println(Math.ceil(2.999999999999999999999999999999999));
        System.out.println(Math.floor(2.9999999999));
        char[] chars = str.toCharArray();
        for (char c : chars) {
            System.out.println(c);
        }
    }

}