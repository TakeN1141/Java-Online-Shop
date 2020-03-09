import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileTrace extends JFrame{
    ArrayList<String> parentC = new ArrayList<>();
    ArrayList<String> childC = new ArrayList<>();
    ArrayList<String> shoplist = new ArrayList<>();
    DefaultMutableTreeNode mutableNode;
    JTree tableT = new JTree();
    public void getInfo() {
        try {

            File source = new File("src/database.file");
            Scanner scanner = new Scanner(source);
            while (scanner.hasNextLine()) {

                String trace = scanner.nextLine();

                if (trace.contains("</")) {
                    trace = trace.replace("</", "");
                    trace = trace.replace(">", "");
                    parentC.add(trace);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("There's an error!");
            e.printStackTrace();
        }

    }
    public void tableTreeMethod() throws FileNotFoundException {

        File source = new File("src/database.file");

        Scanner reader = new Scanner(source);
        tableT.setModel(new DefaultTreeModel(
                new DefaultMutableTreeNode("Cars") {
                    {
                        int categoryNumber = 0;

                        String linePr = null;
                        String trace;

                        for (int i = 0; i < parentC.size(); i++) {
                            mutableNode = new DefaultMutableTreeNode(parentC.get(i));
                            add(mutableNode);
                            while (reader.hasNextLine()) {
                                if (linePr!=null){
                                    trace = linePr;
                                } else {
                                    trace = reader.nextLine();
                                }
                                if (trace.contains("<" + parentC.get(i) + ">")){

                                    trace = reader.nextLine();

                                    while(!trace.contains("</" + parentC.get(i) + ">")){

                                        if (trace.contains("_")){
                                            trace= trace.replace("_", "");
                                            childC.add(trace);

                                            mutableNode.add(new DefaultMutableTreeNode(childC.get(categoryNumber)));
                                            add(mutableNode);

                                            categoryNumber++;
                                        }
                                        trace = reader.nextLine();

                                        linePr = null;
                                    }
                                } else{
                                    linePr = trace;

                                    break;
                                }
                            }
                        }
                    }
                }
        ));
        reader.close();
    }


    public void shopList(String choose) {
        try {
            shoplist.clear();

            File path = new File("src/database.file");
            Scanner reader = new Scanner(path);
            String change = choose.trim();

            while (reader.hasNextLine()) {

                String locate = reader.nextLine();

                if (locate.contains("_" + change)) {

                    locate = reader.nextLine();
                    while (locate.isEmpty()) {

                        locate = reader.nextLine();

                    }
                    while (locate.contains("*")) {

                        locate = locate.replace("*", "");
                        shoplist.add(locate);

                        locate = reader.nextLine();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("There is an error !");
            e.printStackTrace();
        }
    }
}
