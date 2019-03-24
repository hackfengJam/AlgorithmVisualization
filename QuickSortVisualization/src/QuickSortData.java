import java.util.Arrays;

public class QuickSortData {
    public enum Type {
        Default,
        NearlyOrdered,
        Identical
    }

    private int[] numbers;
    public int l, r; // 当前正在处理的区间
    public boolean[] fixedPivots; // 当前已经处在正确位置的元素
    public int curPivot; // 当前正在处理标定点
    public int curElement; // 当前正在处理的元素

    public QuickSortData(int N, int randomBound, Type dataType) {

        numbers = new int[N];
        fixedPivots = new boolean[N];

        int lBound = 1;
        int rBound = randomBound;
        if (dataType == Type.Identical) {
            int avgNumber = (lBound + rBound) / 2;
            lBound = avgNumber;
            rBound = avgNumber;
        }

        for (int i = 0; i < N; i++) {
            numbers[i] = (int) (Math.random() * (rBound - lBound + 1)) + lBound;
            fixedPivots[i] = false; // 初始都为 false
        }

        if (dataType == Type.NearlyOrdered) {
            Arrays.sort(numbers);

            // 交换部分数据
            int swapTime = (int) (0.01 * N);
            for (int i = 0; i < swapTime; i++) {
                int a = (int) (Math.random() * N);
                int b = (int) (Math.random() * N);
                swap(a, b);
            }
        }

    }

    public QuickSortData(int N, int randomBound) {
        this(N, randomBound, Type.Default);
    }


    public int N() {
        return numbers.length;
    }

    public int get(int index) {
        if (index < 0 || index >= numbers.length)
            throw new IllegalArgumentException("Invalid index to access Sort Data.");

        return numbers[index];
    }

    public void swap(int i, int j) {

        if (i < 0 || i >= numbers.length || j < 0 || j >= numbers.length)
            throw new IllegalArgumentException("Invalid index to access Sort Data.");

        int t = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = t;
    }
}