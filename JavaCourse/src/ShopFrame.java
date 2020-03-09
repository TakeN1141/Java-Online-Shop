import javax.management.loading.MLet;
import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

public class ShopFrame {

    public ShopFrame() throws FileNotFoundException {
        FileTrace paths = new FileTrace();
        Frame frame = new JFrame();
        paths.getInfo();
        paths.tableTreeMethod();

        ((JFrame) frame).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(110, 110, 730, 355);
        frame.setLayout(null);
        paths.tableT.setBounds(0, 0, 176, 324);
        frame.add(paths.tableT);
        JPanel mainP = new JPanel();
        JPanel subPanel = new JPanel();

        mainP.setLayout(new GridLayout(2,2));
        mainP.setBounds(157,1, 630,340);

        JLabel Jlabel = new JLabel("Welcome User !" + "\nPlease select a category !");
        Jlabel.setHorizontalAlignment(SwingConstants.CENTER);

        Jlabel.setBounds(302, 20, 250, 39);
        frame.add(Jlabel);
        frame.setVisible(true);

        paths.tableT.addTreeSelectionListener(new TreeSelectionListener() {

            public void valueChanged(TreeSelectionEvent e) {

                DefaultMutableTreeNode child = (DefaultMutableTreeNode)

                        paths.tableT.getLastSelectedPathComponent();
                if (child == null) return;
                Object childInfo = child.getUserObject();
                paths.shopList(childInfo.toString());
                StringBuilder productName;
                StringBuilder cost;
                StringBuilder pathImg ;

                for (int i = 0;  i< paths.shoplist.size(); i++) {
                     productName = new StringBuilder();
                     cost = new StringBuilder();
                     pathImg = new StringBuilder();
                    int one = 0;
                    int l = 0;

                    String data = paths.shoplist.get(i);

                    for (int j = 0; j <= paths.shoplist.size(); j++) {
                        if (one == 0) {

                            while (data.charAt(l) != ',') {
                                productName.append(data.charAt(l));
                                l++;
                            }
                            one++;
                        } else if (one == 1) {
                            l++;
                            while (data.charAt(l) != ',') {
                                l++;
                            }
                            one++;
                        } else if (one == 2) {
                            l++;
                            while (data.charAt(l) != '.') {
                                pathImg.append(data.charAt(l));
                                l++;

                            }
                            one++;
                        }
                    }

                    JLabel productL = new JLabel(String.valueOf(productName));
                    JLabel costL= new JLabel(String.valueOf(cost));
                    subPanel.setBounds(0,0, 630, 340);
                    subPanel.setLayout(new GridLayout(2, 1));
                    subPanel.add(productL);
                    subPanel.add(costL);

                }
                frame.remove(Jlabel);
                subPanel.setBackground(Color.WHITE);
                mainP.add(subPanel);
                mainP.repaint();
                frame.add(mainP);
                frame.repaint();
            }
        });
    }
    public static void main(String[] args) throws FileNotFoundException {
        new ShopFrame();
    }

}