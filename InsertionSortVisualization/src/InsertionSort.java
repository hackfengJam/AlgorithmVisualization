public class InsertionSort {

    // 我们算法类不允许产生任何实例
    private InsertionSort() {
    }

    public static void sort(Comparable[] arr) {
        int n = arr.length;

        for (int i = 0; i < n; i++) {

            // 寻找元素 arr[i]合适的插入位置
            Comparable e = arr[i];
            int j = i;
            for (; j > 0 && arr[j - 1].compareTo(e) > 0; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = e;
        }
    }

    public void swap(Object[] arr, int i, int j) {
        if (i < 0 || i >= arr.length || j < 0 || j >= arr.length)
            throw new IllegalArgumentException("Invalid index to access Sort Data.");

        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
