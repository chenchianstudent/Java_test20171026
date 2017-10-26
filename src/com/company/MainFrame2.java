package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class MainFrame2 extends JFrame {
    private int screenW = Toolkit.getDefaultToolkit().getScreenSize().width, screenH = Toolkit.getDefaultToolkit().getScreenSize().height;
    private int frmW = 500, frmH = 450;
    private JMenuBar jmb = new JMenuBar();
    private JMenu jm1 = new JMenu("File");
    private JMenu jm2 = new JMenu("Set");
    private JMenu jm3 = new JMenu("Game");
    private JMenu jm4 = new JMenu("About");
    private JMenuItem jmife = new JMenuItem("Exit");
    private JMenuItem jmigl = new JMenuItem("計算機");
    private JDesktopPane jdp = new JDesktopPane();
    private JTextField jtf= new JTextField();
    private Container cp;
    private JPanel jpn=new JPanel(new GridLayout(3,3));
    private JButton jbtns[]=new JButton[9];
    private String jbtns2[]= { "7","8","4","5","6","1","2","3","0"};
    private String[] number=new String[jbtns2.length];
    private JButton jbt=new JButton("exit");

    private JInternalFrame jInternalFrame = new JInternalFrame();
    private Container jifcp;
    private Random rnd = new Random(System.currentTimeMillis());
    public MainFrame mframe = new MainFrame();

    public MainFrame2(MainFrame mf) {
        mframe = mf;
        init();
    }

    private void init() {
        this.setBounds(screenW / 2 - frmW / 2, screenH / 2 - frmH / 2, frmW, frmH);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(jdp);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mframe.reset();
                mframe.setVisible(true);
            }
        });
        this.setJMenuBar(jmb);
        jmb.add(jm1);
        jmb.add(jm2);
        jmb.add(jm3);
        jmb.add(jm4);
        jm1.add(jmife);
        jm3.add(jmigl);
        jmife.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        jmife.setAccelerator(KeyStroke.getKeyStroke('X', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        jmigl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jdp.add(jInternalFrame);
                jInternalFrame.setVisible(true);
            }
        });
        jInternalFrame.setBounds(0, 0, 300, 200);
        jifcp = jInternalFrame.getContentPane();
        jifcp.setLayout(new BorderLayout(5, 5));
        jifcp.add(jtf,BorderLayout.NORTH);
        jifcp.add(jpn,BorderLayout.CENTER);
        jifcp.add(jbt,BorderLayout.SOUTH);
        jtf.setEditable(false);
        for (int i = 0; i < 9; i++) {
            int p=rnd.nextInt(9);
            jbtns[i] = new JButton(jbtns2[p]);
            number[i]=jbtns2[p];
            System.out.println("更正前"+i+"="+number[i]);
            System.out.println(p);
            for (int j = 0; j < i; j++) {
                while(number[i]==number[j]) {
                    p=rnd.nextInt(9);
                    number[i]=jbtns2[p];
                    jbtns[i]=new JButton(jbtns2[p]);
                    System.out.println("更正後"+i+"="+number[i]);
                    j=0;
                }

            }

        }
        for (int i = 0; i < 9; i++) {
            jpn.add(jbtns[i]);
        }
        for(int i=0;i<9;i++) {
            jbtns[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton tempButton = (JButton) e.getSource();
                    jtf.setText(jtf.getText() + tempButton.getText());
                }
            });
        }
        jbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jInternalFrame.dispose();
            }
        });

    }


}