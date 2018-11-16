
public class OverloadTest {

    public static void main(String[] args) {
        A a1 = new A();
        A a2 = new B();
        B b = new B();
        C c = new C();
        D d = new D();

        a1.show(b);
        a1.show(c);
        a1.show(d);

        a2.show(b);
        a2.show(c);
        a2.show(d);

        b.show(b);
        b.show(c);
        b.show(d);
    }

}

class A {
    public void show(D obj) {
        System.out.println("A & D");
    }

    public void show(A obj) {
        System.out.println("A & A");
    }
}

class B extends A {
    public void show(C obj) {
        System.out.println("B & C");
    }

    public void show(B obj) {
        System.out.println("B & B");
    }
}

class C extends B {}

class D extends B {}