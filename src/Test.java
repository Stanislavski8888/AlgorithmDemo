
public class Test {

    public static void main(String[] args) {
        new Test().print(args == null || new Test() {{
            Test.main(null);
        }}.equals(null));
    }

    public void print(boolean flag) {
        if (flag) {
            System.out.println("我是if语句的分支");
        } else {
            System.out.println("我是else语句的分支");
        }
    }
}