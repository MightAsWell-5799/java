import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import classes.Store;

public class gameStop {
    // hashmap to store the stores by integer ID
    private static HashMap<Integer, Store> stores = new HashMap<Integer, Store>();
    private JFrame mainFrame = new JFrame();

    /**
     * execution args, not used
     * 
     * @param args
     */
    gameStop() {
        readStores();
        displayFirstOptions();
    }
    public static void main(String[] args) {
        new gameStop();
    }

    public void displayFirstOptions() {
        mainFrame.dispose();
        mainFrame = new JFrame();
        mainFrame.setTitle("Game Stop");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton allStores = new JButton("Display All Stores");
        JButton addStore = new JButton("Add A Store");
        JButton removeStore = new JButton("Remove A Store");
        JButton editStore = new JButton("Edit A Store");
        JButton exit = new JButton("Exit");
        JPanel panel = new JPanel();

        //add listeners to each button
        allStores.addActionListener((ActionListener)new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.removeAll();
                displayAllStores();
            }
        });
        addStore.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.removeAll();
                addStore();
            }
        });
        removeStore.addActionListener((ActionListener)new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.removeAll();
                removeStore();
            }
        });
        editStore.addActionListener((ActionListener)new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.removeAll();
                editStore();
            }
        });
        
        exit.addActionListener((ActionListener)new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //add buttons to the frame
        panel.add(allStores);
        panel.add(addStore);
        panel.add(removeStore);
        panel.add(editStore);
        panel.add(exit);
        mainFrame.add(panel);
        //set the frame properties
        mainFrame.setSize(500, 500);
        mainFrame.setLayout(new GridLayout(5, 6));
        mainFrame.setVisible(true);
    }

    /**
     * Displays all stores in the hashmap
     */

    public void displayAllStores() {
        JPanel panel = new JPanel();
        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.removeAll();
                displayFirstOptions();
            }
        });
        mainFrame.setVisible(false);
        panel.add(back);
        mainFrame.add(panel);
        mainFrame.setVisible(true);
    }

    public void addStore() {
        JPanel panel = new JPanel();
        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.removeAll();
                displayFirstOptions();
            }
        });
        mainFrame.setVisible(false);
        panel.removeAll();
        panel.add(back);
        mainFrame.add(panel);
        mainFrame.setVisible(true);
    }

    public void removeStore() {
        JPanel panel = new JPanel();
        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.removeAll();
                displayFirstOptions();
            }
        });
        mainFrame.setVisible(false);
        panel.removeAll();
        panel.add(back);
        mainFrame.add(panel);
        mainFrame.setVisible(true);
    }

    public void editStore() {
        JPanel panel = new JPanel();
        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.removeAll();
                displayFirstOptions();
            }
        });
        mainFrame.setVisible(false);
        panel.removeAll();
        panel.add(back);
        mainFrame.add(panel);
        mainFrame.setVisible(true);
    }

    public void exit() {
        System.exit(0);
    }

    // read in csv store info file with the first value of each row beinh the store
    // ID and the second being the store sales
    public static void readStores() {
        try {
            File file = new File("stores.csv");
            Scanner fis = new Scanner(file);

            int storeID;
            int sales;

            while (fis.hasNextLine()) {
                String temp = fis.nextLine();
                String[] tempValues = temp.split(",");
                storeID = Integer.parseInt(tempValues[0]);
                sales = Integer.parseInt(tempValues[1]);

                stores.put(storeID, new Store(storeID, sales));
            }
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // forloop over every store in stores and print out the value.toString()
        for (Store store : stores.values()) {
            System.out.println(store.toString());
        }
    }

    /**
     * write stores map to csv file with key as the first value and the value as the
     * second on every row
     */
    public static void writeStores() {
        try {
            File file = new File("stores.csv");
            FileWriter fos = new FileWriter(file);

            for (Store store : stores.values()) {

                System.out.println(store.toCSV());
                fos.write(store.toCSV() + "\n");
            }
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // add a store to the stores map
    /**
     * add a store to the stores map
     * 
     * @param store
     */
    public static void addStore(Store store) {
        stores.put(store.getID(), store);
    }

    // remove a store from the stores map
    /**
     * remove a store from the stores map
     * 
     * @param storeID
     */
    public static void removeStore(int storeID) {
        stores.remove(storeID);
    }
}
