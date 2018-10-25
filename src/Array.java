import java.util.ArrayList;

public class Array<E> {
    private ArrayList<E> arrayList = new ArrayList<>();
    
    public void add(E element) {
        arrayList.add(element);
    }
    
    public void addAll(E[] elements) {
        for(int i = 0; i < elements.length; i++) {
            this.add(elements[i]);
        }
    }
}
