
public class SortAlgorithm {

    public static void main(String[] args) {
        int[] array = randomGenerateIntArray();
//        bubbleSort(array, array.length);
//        cocktailSort(array, array.length);
//        selectionSort(array, array.length);
        insertionSort(array, array.length);
    }

    static int[] randomGenerateIntArray() {
        int[] randomArray = new int[20];
        for(int i = 0; i < randomArray.length; i++) {
            randomArray[i] = 1+(int) (Math.random()*100);
        }
        printArray(randomArray);
        return randomArray;
    }
    
    static void printArray(int[] A) {
        for (int i = 0; i < A.length; i++) {
            System.out.printf("% 3d%s", A[i], (i == A.length - 1 ? "" : ", "));
        }
        System.out.println();
    }
    
    static void swap(int A[], int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
    
    /* 冒泡排序 */
    static void bubbleSort(int A[], int n) {
        for(int j = 0; j < n -1; j++) {
            for(int i = 0; i < n - 1 - j; i++) {
                if(A[i] > A[i + 1]) {
                    swap(A, i, i + 1);
                }
            }
            printArray(A);
        }
    }
    
    /* 鸡尾酒排序 */
    static void cocktailSort(int A[], int n) {
        int left = 0;
        int right = n - 1;
        while (left < right) {
            for (int i = left; i < right; i++) {
                if (A[i] > A[i + 1]) {
                    swap(A, i, i + 1);
                }
            }
            right --;
            printArray(A);
            for (int i = right; i > left; i--) {
                if (A[i - 1] > A[i]) {
                    swap(A, i - 1, i);
                }
            }
            left++;
            
            printArray(A);
        }
    }
    
    /* 选择排序 */
    static void selectionSort(int A[], int n) {
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (A[j] < A[min]) {
                    min = j;
                }
            }
            if (min != i) {
                swap(A, min, i);
            }
            printArray(A);
        }
    }
    
    /* 插入排序 */
    static void insertionSort(int A[], int n) {
        for (int i = 1; i < n; i++) {
            int get = A[i];
            int j = i - 1;
            while (j >= 0 && A[j] > get) {
                A[j + 1] = A[j];
                j--;
            }
            A[j + 1] = get;
            printArray(A);
        }
    }
    
    /* 二分插入排序 */
    static void insertionSortDichotomy(int A[], int n) {
        for (int i = 1; i < n; i++) {
            int get = A[i];
            int left = 0;
            int right = i - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (A[mid] > get) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            for (int j = i - 1; j >= left; j--) {
                A[j + 1] = A[j];
            }
            A[left] = get;
        }
    }
}
