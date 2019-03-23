import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

public class AlgoVisualizer {

    private static int DELAY = 10;
    private SelectionSortData data;        // 数据
    private AlgoFrame frame;    // 视图

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N) {

        // 初始化数据
        data = new SelectionSortData(N, sceneHeight);

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Selection Sort Visualization", sceneWidth, sceneHeight);
            frame.addKeyListener(new AlgoKeyListener());
            frame.addMouseListener(new AlgoMouseListener());
            new Thread(() -> {
                run();
            }).start();
        });
    }

    // 动画逻辑
    private void run() {
        // 渲染 初始状态
        setData(0, -1, -1);

        // 选择排序逻辑
        for (int i = 0; i < data.N(); i++) {
            // 寻找[i,n)区间里的最小值的索引
            int minIndex = i;
            setData(i, -1, minIndex);
            for (int j = i + 1; j < data.N(); j++) {
                setData(i, j, minIndex);
                if (data.get(j) < data.get(minIndex)) {
                    minIndex = j;
                    setData(i, j, minIndex);
                }
            }
            data.swap(i, minIndex);
            // 渲染
            setData(i + 1, -1, -1);
        }

        // 渲染 终了状态
        setData(data.N(), -1, -1);

    }

    // TODO: 根据情况决定是否实现键盘鼠标等交互事件监听器类
    private class AlgoKeyListener extends KeyAdapter {
    }

    private class AlgoMouseListener extends MouseAdapter {
    }

    private void setData(int orderedIndex, int currentCompareIndex, int currentMinIndex) {
        data.orderedIndex = orderedIndex;
        data.currentMinIndex = currentMinIndex;
        data.currentCompareIndex = currentCompareIndex;

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 100;

        // TODO: 根据需要设置其他参数，初始化visualizer
        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight, N);
    }
}
