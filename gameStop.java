import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
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

        JButton allStores = new JButton("Display All Stores");
        JButton addStore = new JButton("Add A Store");
        JButton removeStore = new JButton("Remove A Store");
        JButton editStore = new JButton("Edit A Store");
        JButton exit = new JButton("Exit");
        JPanel panel = new JPanel();

        // add listeners to each button
        allStores.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.removeAll();
                displayAllStores();
            }
        });
        editStore.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.removeAll();
                editStore();
            }

        });
        addStore.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.removeAll();
                addStore();
            }
        });
        removeStore.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.removeAll();
                removeStore();
            }
        });

        exit.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                writeStores();
                System.exit(0);
            }
        });

        // add buttons to the frame
        panel.add(addStore);
        panel.add(allStores);
        panel.add(removeStore);
        panel.add(editStore);
        panel.add(exit);
        mainFrame.add(panel);
        // set the frame properties
        mainFrame.setSize(500, 500);
        mainFrame.setLayout(new GridLayout(5, 6));
        mainFrame.setVisible(true);
    }

    /**
     * Displays all stores in the hashmap
     */

    public void displayAllStores() {
        JPanel panel = new JPanel();
        panel.removeAll();
        // gridLayout the panel
        panel.setLayout(new GridLayout(1, 1));

        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.removeAll();
                displayFirstOptions();
            }
        });

        // create jtextarea that displays all of the stores in the stores HashMap
        JTextArea textArea = new JTextArea(100000, 1);
        var fullString = "";
        for (Store store : stores.values()) {
            fullString += store.toString() + "\n";
        }

        JScrollPane scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        textArea.setEditable(false);
        textArea.setText(fullString);
        scroll.setSize(400, 250);
        panel.add(scroll);

        mainFrame.setVisible(false);
        mainFrame.add(panel);
        mainFrame.add(back);
        mainFrame.setSize(500, 500);
        mainFrame.setLayout(new GridLayout(5, 6));
        mainFrame.setVisible(true);
    }

    public void addStore() {

        JPanel panel = new JPanel();
        panel.removeAll();
        // gridLayout the panel
        panel.setLayout(new GridLayout(5, 1));
        // textbox for id and income with a submit button
        JTextField id = new JTextField();
        JTextField income = new JTextField();
        // add sample text to id and income textbox
        id.setText("Enter store id");
        income.setText("Enter store income");

        // set minimum width and height of the text fields
        id.setMinimumSize(new Dimension(100, 20));
        income.setMinimumSize(new Dimension(100, 20));
        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.removeAll();
                // create a new store with the id and income
                try {
                    Store store = new Store(Integer.parseInt(id.getText()), (int)(Double.parseDouble(income.getText()) * 100));
                    // add the store to the hashmap
                    stores.put(store.getID(), store);
                    // display the stores
                } catch (Exception E) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number");
                }
                displayFirstOptions();
            }
        });
        // add id and income and submit button
        panel.add(id);
        panel.add(income);
        panel.add(submit);
        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.removeAll();
                displayFirstOptions();
            }
        });

        mainFrame.setVisible(false);
        panel.add(back);
        mainFrame.add(panel);
        mainFrame.setVisible(true);
    }

    public void removeStore() {

        JPanel panel = new JPanel();
        panel.removeAll();
        // set panel to gridLayout
        panel.setLayout(new GridLayout(5, 1));
        JButton back = new JButton("Back");
        // add one text box for storeID and submit button to the panel
        JTextField storeID = new JTextField();
        JButton submit = new JButton("Submit");

        // set minimum width and height of the text fields
        storeID.setMinimumSize(new Dimension(100, 20));

        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // if the storeID is in the stores hashmap, remove it from the hashmap
                // if the storeID is not a number, then display a warning
                try {
                    Integer.parseInt(storeID.getText());
                } catch (Exception E) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number");

                    mainFrame.removeAll();
                    displayFirstOptions();
                }

                if (stores.containsKey(Integer.parseInt(storeID.getText()))) {

                    stores.remove(Integer.parseInt(storeID.getText()));
                    JOptionPane.showMessageDialog(null, "Store Removed");
                } else {
                    JOptionPane.showMessageDialog(null, "Store Does Not Exist");
                }
                mainFrame.removeAll();
                displayFirstOptions();
            }
        });
        panel.add(storeID);
        panel.add(submit);

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.removeAll();
                displayFirstOptions();
            }
        });
        mainFrame.setVisible(false);

        panel.add(back);
        mainFrame.add(panel);
        mainFrame.setVisible(true);
    }

    public void editStore() {
        JPanel panel = new JPanel();
        panel.removeAll();
        panel.setLayout(new GridLayout(5, 1));
        // add two text boxes for storeID and income and submit button to the panel
        JTextField storeID = new JTextField();
        JTextField income = new JTextField();
        
        // set minimum width and height of the text fields
        storeID.setMinimumSize(new Dimension(100, 20));
        income.setMinimumSize(new Dimension(100, 20));

        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                //validate storeID is a number
                try {
                    Integer.parseInt(storeID.getText());
                } catch (Exception E) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number");
        
                    mainFrame.removeAll();

                    displayFirstOptions();
                }
                //validate the store income is a number
                try {
                    Double.parseDouble(income.getText());
                } catch (Exception E) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number");

                    mainFrame.removeAll();
                    displayFirstOptions();
                }
                
                // if the storeID is in the stores hashmap, edit the store
                if (stores.containsKey(Integer.parseInt(storeID.getText()))) {
                    Store store = stores.get(Integer.parseInt(storeID.getText()));
                    store.setSales(Double.parseDouble(income.getText()));
                    JOptionPane.showMessageDialog(null, "Store Edited");
                } else {
                    JOptionPane.showMessageDialog(null, "Store Does Not Exist");
                }
                mainFrame.removeAll();
                displayFirstOptions();
            }
        });
        panel.add(storeID);
        panel.add(income);
        panel.add(submit);
        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.removeAll();
                displayFirstOptions();
            }
        });

        mainFrame.setVisible(false);

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

                // System.out.println(store.toCSV());
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
