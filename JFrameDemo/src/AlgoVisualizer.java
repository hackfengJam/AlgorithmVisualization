import java.awt.*;
import java.awt.event.*;

public class AlgoVisualizer {

    private Circle[] circles;
    private AlgoFrame frame;
    private boolean isAnimated = true;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N) {

        circles = new Circle[N];
        int R = 50;
        for (int i = 0; i < N; i++) {
            int x = (int) (Math.random() * (sceneWidth - 2 * R) + R);
            int y = (int) (Math.random() * (sceneHeight - 2 * R) + R);
            int vx = (int) (Math.random() * 11 - 5);
            int vy = (int) (Math.random() * 11 - 5);
            circles[i] = new Circle(x, y, R, vx, vy);
        }

        EventQueue.invokeLater(() -> {
//            AlgoFrame frame = new AlgoFrame("Welcome", 500, 500);
            frame = new AlgoFrame("Welcome", sceneWidth, sceneHeight);
            frame.addKeyListener(new AlgoKeyListener());
            frame.addMouseListener(new AlgoMouseListener());

            new Thread(() -> {
                run();
            }).start();

        });
    }

    // 动画逻辑
    private void run() {
        // 编写自己的动画逻辑

        while (true) {
            // 绘制数据
            frame.render(circles);
            AlgoVisHelper.pause(20);

            // 更新数据
            if (isAnimated)
                for (Circle circle : circles)
                    circle.move(0, 0, frame.getCanvasWidth(), frame.getCanvasHeight());

        }
    }

    private class AlgoKeyListener extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent event) {
            if (event.getKeyChar() == ' ') {
                isAnimated = !isAnimated;
            }
        }

    }

    private class AlgoMouseListener extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent event) {
            int menuBarHeight = frame.getBounds().height - frame.getCanvasHeight();
            event.translatePoint(0, -menuBarHeight);
//            System.out.println(event.getPoint());
            for (Circle circle : circles) {
                if (circle.contain(event.getPoint())) {
                    circle.isFilled = !circle.isFilled;
                }
            }
        }

    }


    public static void main(String[] args) {
        int sceneWidth = 800;
        int sceneHeight = 800;

        int N = 20;
        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight, N);

        System.out.println(1111);
    }
}
