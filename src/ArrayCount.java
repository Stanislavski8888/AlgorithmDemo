
public class ArrayCount<E> extends Array<E> {
    private int count = 0;

    @Override
    public void add(E element) {
        // TODO Auto-generated method stub
        super.add(element);
        ++count;
    }

    @Override
    public void addAll(E[] elements) {
        // TODO Auto-generated method stub
        super.addAll(elements);
        count += elements.length;
    }
    
    public void printCount() {
        System.out.println("count = "+count);
    }
    
    public static void main(String[] args) {
        ArrayCount<String> arrayCount = new ArrayCount<>();
        String[] strings = new String[]{"aaa", "bbb"};
        arrayCount.addAll(strings);
        arrayCount.printCount();
    }
}
