//前置知识
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.net.http.WebSocket;
import java.util.Vector;


//
//class HelloWorldSwing {
//    /**{
//     * 创建并显示GUI。出于线程安全的考虑，
//     * 这个方法在事件调用线程中调用。
//     */
//    private static void createAndShowGUI() {
//        // 确保一个漂亮的外观风格
//        JFrame.setDefaultLookAndFeelDecorated(false);
//
//        // 创建及设置窗口
//        JFrame frame = new JFrame("HelloWorldSwing");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        // 添加 "Hello World" 标签
//        JLabel label = new JLabel("Hello World");
//        frame.getContentPane().add(label);
//
//        // 显示窗口
//        frame.pack();
//        frame.setVisible(true);
//    }
//
//    public static void main(String[] args) {
//        // 显示应用 GUI
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                createAndShowGUI();
//            }
//        });
//    }
//}




public class MyCalculator {


    String str1 = "0";
    String str2 = "0";
    String signal = "+";
    String result = "";

    int k1 = 1;
    int k2 = 1;
    int k3 = 1;
    int k4 = 1;
    int k5 = 1;

    JButton store;

    Vector vt = new Vector(20,10);

    // 图形化各个组件
    JFrame frame = new JFrame("MyCalculator");
    JTextField resultFrame = new JTextField(result,20);
    JButton clear_result = new JButton("Clear");
    JButton button_zero = new JButton("0");
    JButton button_one = new JButton("1");
    JButton button_two = new JButton("2");
    JButton button_three = new JButton("3");
    JButton button_four = new JButton("4");
    JButton button_five = new JButton("5");
    JButton button_six = new JButton("6");
    JButton button_seven = new JButton("7");
    JButton button_eight = new JButton("8");
    JButton button_nine = new JButton("9");

    JButton button_point = new JButton(".");
    JButton button_addition = new JButton("+");
    JButton button_subtraction = new JButton("-");
    JButton button_multiplication = new JButton("*");
    JButton button_division = new JButton("/");
    JButton button_equal = new JButton("=");


    public void Calculator(){
        button_zero.setMnemonic(KeyEvent.VK_0);
        button_one.setMnemonic(KeyEvent.VK_1);
        button_two.setMnemonic(KeyEvent.VK_2);
        button_three.setMnemonic(KeyEvent.VK_3);
        button_four.setMnemonic(KeyEvent.VK_4);
        button_five.setMnemonic(KeyEvent.VK_5);
        button_six.setMnemonic(KeyEvent.VK_6);
        button_seven.setMnemonic(KeyEvent.VK_7);
        button_eight.setMnemonic(KeyEvent.VK_8);
        button_nine.setMnemonic(KeyEvent.VK_9);

        resultFrame.setHorizontalAlignment(JTextField.RIGHT);



        JPanel container = new JPanel();
        container.setLayout(new GridLayout(4,4,5,5));
        container.add(button_zero);
        container.add(button_one);
        container.add(button_two);
        container.add(button_three);
        container.add(button_four);
        container.add(button_five);
        container.add(button_six);
        container.add(button_seven);
        container.add(button_eight);
        container.add(button_nine);
        container.add(button_addition);
        container.add(button_division);
        container.add(button_multiplication);
        container.add(button_subtraction);
        container.add(button_point);
        container.add(button_equal);


        container.setBorder((BorderFactory.createEmptyBorder(5,5,5,5)));
        JPanel container_up = new JPanel();
        container_up.setLayout(new BorderLayout());
        container_up.add(resultFrame,BorderLayout.WEST);
        container_up.add(clear_result,BorderLayout.EAST);

        frame.setLocation(600,300);
        frame.setResizable(false);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(container_up,BorderLayout.NORTH);
        frame.getContentPane().add(container,BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);



        class Listener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                String ss = ((JButton)e.getSource()).getText();

                store = (JButton) e.getSource();
                vt.add(store);

                if (k1 == 1) {
                    if (k3 == 1){
                        k5 = 1;
                    }
                    str1 = str1 + ss;

                    k3 = k3 + 1;

                    resultFrame.setText(str1);
                } else if (k1 == 2){
                    if(k4 == 1) {
                        str2 = "";

                        k5 = 1;
                    }
                    str2 =str2 + ss;
                    k4 = k4 + 1;
                    resultFrame.setText(str2);
                }
            }
        }

        class Listener_signal implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ss2 = ((JButton)e.getSource()).getText();
                store = (JButton) e.getSource();
                vt.add(store);

                if (k2 == 1) {
                    k1 = 2;
                    k5 = 1;
                    signal = ss2;
                    k2 = k2 + 1;
                }else {
                    int a =vt.size();
                    JButton c = (JButton) vt.get(a - 2);

                    if (!(c.getText().equals("+"))
                            && !(c.getText().equals("-"))
                            && !(c.getText().equals("*"))
                            && !(c.getText().equals("/"))) {
                        cal();
                        str1 = result;
                        k1 = 2;
                        k5 = 1;
                        k4 = 1;
                        signal = ss2;
                    }
                    k2 = k2 + 1;
                }

            }
        }


        class Listener_clear implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                store = (JButton) e.getSource();
                vt.add(store);
                k1 = 1;
                k2 = 1;
                k3 = 1;
                k4 = 1;
                k5 = 1;
                str1 = "0";
                str2 = "0";
                signal = "";
                result = "";
                resultFrame.setText(result);
                vt.clear();
            }
        }


        class Listener_dy implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                store = (JButton) e.getSource();
                vt.add(store);
                cal();

                k1 = 1;
                k2 = 1;
                k3 = 1;
                k4 = 1;

                str1 = result;
            }
        }

        class Listener_poit implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                store = (JButton) e.getSource();
                vt.add(store);
                if (k5 == 1) {
                    String ss2 = ((JButton) e.getSource()).getText();
                    if (k1 == 1) {
                        if (k3 == 1) {
                            str1 = "";
                            k5 = 1;
                        }
                        str1 = str1 + ss2;

                        k3 = k3 + 1;

                        resultFrame.setText(str1);
                    } else if (k1 == 2) {
                        if (k4 == 1) {
                            str2 = "";
                            k5 = 1;
                        }
                        str2 = str2 + ss2;
                        k4 = k4 + 1;
                        resultFrame.setText(str2);
                    }
                }
            }
        }

        // 注册各个监听器，即绑定事件响应逻辑到各个UI组件上
        Listener_dy jt_dy = new Listener_dy();

        // 监听数字键
        Listener jt = new Listener();
        // 监听符号键
        Listener_signal jt_signal = new Listener_signal();
        // 监听清除键
        Listener_clear jt_c = new Listener_clear();
        // 监听小数点键
        Listener_poit jt_xs = new Listener_poit();

        button_seven.addActionListener(jt);
        button_eight.addActionListener(jt);
        button_nine.addActionListener(jt);
        button_division.addActionListener(jt_signal);
        button_four.addActionListener(jt);
        button_five.addActionListener(jt);
        button_six.addActionListener(jt);
        button_multiplication.addActionListener(jt_signal);
        button_one.addActionListener(jt);
        button_two.addActionListener(jt);
        button_three.addActionListener(jt);
        button_subtraction.addActionListener(jt_signal);
        button_zero.addActionListener(jt);
        button_point.addActionListener(jt_xs);
        button_equal.addActionListener(jt_dy);
        button_addition.addActionListener(jt_signal);
        clear_result.addActionListener(jt_c);
    }


    private void cal() {
        // 操作数1
        double a2;
        // 操作数2
        double b2;
        // 运算符
        String c = signal;
        // 运算结果
        double result2 = 0;

        if (c.equals("")) {
            resultFrame.setText("Please input operator");

        } else {
            // 手动处理小数点的问题
            if (str1.equals("."))
                str1 = "0.0";
            if (str2.equals("."))
                str2 = "0.0";
            a2 = Double.valueOf(str1).doubleValue();
            b2 = Double.valueOf(str2).doubleValue();

            if (c.equals("+")) {
                result2 = a2 + b2;
            }
            if (c.equals("-")) {
                result2 = a2 - b2;
            }
            if (c.equals("*")) {
                BigDecimal m1 = new BigDecimal(Double.toString(a2));
                BigDecimal m2 = new BigDecimal(Double.toString(b2));
                result2 = m1.multiply(m2).doubleValue();
            }
            if (c.equals("/")) {
                if (b2 == 0) {
                    result2 = 0;
                } else {
                    result2 = a2 / b2;
                }

            }

            result = ((new Double(result2)).toString());

            resultFrame.setText(result);
        }
    }

    public static void main(String[] args) {
        // 设置程序显示的界面风格，可以去除
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        MyCalculator cal = new MyCalculator();
        cal.Calculator();
    }
}
