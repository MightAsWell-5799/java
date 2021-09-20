
//imports, all are used
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

    //general frame to add objects to throughout the program
    private JFrame mainFrame = new JFrame();

    /**
     * Constructor for the gameStop class
     */
    gameStop() {
        //read in the stores off of the CSV file 
        readStores();
        //open up the main menu
        displayFirstOptions();
    }
    /**
     * execution args, not used
     * @param args
     */
    public static void main(String[] args) {
        //create the first instance of a gameStop, the entire program will exist in that contexty
        new gameStop();
    }

    public void displayFirstOptions() {
        //clear mainFrame entirely and reset it to a new frame with the Title of gameStop
        mainFrame.dispose();
        mainFrame = new JFrame();
        mainFrame.setTitle("Game Stop");

        //create all of the required buttons
        JButton allStores = new JButton("Display All Stores");
        JButton addStore = new JButton("Add A Store");
        JButton removeStore = new JButton("Remove A Store");
        JButton editStore = new JButton("Edit A Store");
        JButton exit = new JButton("Exit");
        JPanel panel = new JPanel();

        // add listeners to each button
        allStores.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //clear panel and open display all stores method
                panel.removeAll();
                displayAllStores();
            }
        });
        editStore.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //clear panel and open edit store method
                panel.removeAll();
                editStore();
            }
        });
        addStore.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //clear panel and open add store method
                panel.removeAll();
                addStore();
            }
        });
        removeStore.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //clear panel and open remove store method
                panel.removeAll();
                removeStore();
            }
        });
        exit.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //write the stores back to the CSV file and close the program
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
     * Method for the all stores button
     */
    public void displayAllStores() {
        JPanel panel = new JPanel();
        panel.removeAll();
        // gridLayout the panel
        panel.setLayout(new GridLayout(1, 1));
        //add the back button
        JButton back = new JButton("Back");
        //add a listener to the back button
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //clear the panel and open the first options
                panel.removeAll();
                displayFirstOptions();
            }
        });

        // create jtextarea that displays all of the stores in the stores HashMap
        //massive empty area for text
        JTextArea textArea = new JTextArea(100000, 1);
        //define fullString and then interate over all of the stores and add them to the fullString
        var fullString = "";
        for (Store store : stores.values()) {
            fullString += store.toString() + "\n";
        }

        //Make the textArea scrollable
        JScrollPane scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        //make the text area read only
        textArea.setEditable(false);
        //set the text area text to the full string
        textArea.setText(fullString);
        //set the text area size
        scroll.setSize(400, 250);
        //add the scroll panel to the panel
        panel.add(scroll);

        //make the frame not visible to allow a reload
        mainFrame.setVisible(false);
        //add panel and back button to the frame
        mainFrame.add(panel);
        mainFrame.add(back);
        //set the main frame size to 500px square
        mainFrame.setSize(500, 500);
        //gridLayout
        mainFrame.setLayout(new GridLayout(5, 6));
        //make visible again
        mainFrame.setVisible(true);
        //end display all stores
    }

    /**
     * method for the add store button
     */
    public void addStore() {
        //new panel for the buttons and stuff
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
        //submit button and add a listener to it
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
        // add id and income and submit button to panel
        panel.add(id);
        panel.add(income);
        panel.add(submit);

        //create back button and add listener to send back to the main menu
        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //send back to the main menue on press
                mainFrame.removeAll();
                displayFirstOptions();
            }
        });
        

        //make frame invisible to the user
        //add button to panel and add panel to frame to allow the frame to update
        mainFrame.setVisible(false);
        panel.add(back);
        mainFrame.add(panel);
        //make the frame visible again
        mainFrame.setVisible(true);
        //add store ends
    }

    public void removeStore() {

        JPanel panel = new JPanel();
        panel.removeAll();
        // set panel to gridLayout
        panel.setLayout(new GridLayout(5, 1));
        JButton back = new JButton("Back");
        // add one text box for storeID and submit button to the panel
        JTextField storeID = new JTextField();
        
        // add sample text to storeID textbox
        storeID.setText("Enter store id");
        //submit button and add a listener to it
        JButton submit = new JButton("Submit");
        
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //validate the inputs
                try {
                    Integer.parseInt(storeID.getText());
                } catch (Exception E) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number");
                    //send back to main menu
                    mainFrame.removeAll();
                    displayFirstOptions();
                }

                // if the storeID is in the stores hashmap, remove it from the hashmap
                // if the storeID does not exist in the hashmap, display an error message
                if (stores.containsKey(Integer.parseInt(storeID.getText()))) {
                    //
                    stores.remove(Integer.parseInt(storeID.getText()));
                    JOptionPane.showMessageDialog(null, "Store Removed");
                } else {
                    JOptionPane.showMessageDialog(null, "Store Does Not Exist");
                }
                //send backto the main menu
                mainFrame.removeAll();
                displayFirstOptions();
            }
        });
        //add storeID and submit button to panel
        panel.add(storeID);
        panel.add(submit);
        //add listener to the back button 
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //send back to the main menu
                mainFrame.removeAll();
                displayFirstOptions();
            }
        });
        //make frame invisible to allow for reload
        mainFrame.setVisible(false);
        //add back to panel and panel to frame
        panel.add(back);
        mainFrame.add(panel);

        //make frame visible again
        mainFrame.setVisible(true);

        //remove store end
    }

    public void editStore() {
        //create a new panel
        JPanel panel = new JPanel();
        
        //remove all the components from the panel
        panel.removeAll();
        //set the panel to a gridLayout
        panel.setLayout(new GridLayout(5, 1));
        // add two text boxes for storeID and income and submit button to the panel
        JTextField storeID = new JTextField();
        JTextField income = new JTextField();

        // set minimum width and height of the text fields
        storeID.setMinimumSize(new Dimension(100, 20));
        income.setMinimumSize(new Dimension(100, 20));
        //create a submit button and add a listener to it
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
                    //display error message
                    JOptionPane.showMessageDialog(null, "Please enter a valid number");
                    //send to the main menu
                    mainFrame.removeAll();
                    displayFirstOptions();
                }

                // if the storeID is in the stores hashmap, edit the store
                if (stores.containsKey(Integer.parseInt(storeID.getText()))) {
                    //get the store with the ID
                    Store store = stores.get(Integer.parseInt(storeID.getText()));
                    //set the income of the store
                    store.setSales(Double.parseDouble(income.getText()));
                    // display success message
                    JOptionPane.showMessageDialog(null, "Store Edited");
                } else {
                    //display error message
                    JOptionPane.showMessageDialog(null, "Store Does Not Exist");
                }
                //remove all components from the main frame
                mainFrame.removeAll();
                //send back to the main menu
                displayFirstOptions();
            }
        });
        //add all buttons and text boxes to the panel
        panel.add(storeID);
        panel.add(income);
        panel.add(submit);
        //create a back button and add a listener to it
        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //clear frame and send to main menu
                mainFrame.removeAll();
                displayFirstOptions();
            }
        });

        //make invisible to allow reload
        mainFrame.setVisible(false);

        //add back to panel and panel to frame
        panel.add(back);
        mainFrame.add(panel);
        //make frame visible
        mainFrame.setVisible(true);
        //end editStore
    }
    
    //Just a quit method
    public void exit() {
        System.exit(0);
    }
    /**
     * Reads in the store info file and stores the information in the stores hashmap
     */
    public static void readStores() {
        try {
            //read in the stores.csv file
            File file = new File("stores.csv");
            Scanner fis = new Scanner(file);
            //used in while because java hates redefining variables and doesn't allow anonymous function passing
            int storeID;
            int sales;
            //read each line in the file
            while (fis.hasNextLine()) {
                //split the line at the comma as per csv spec, no eol commas
                String temp = fis.nextLine();
                String[] tempValues = temp.split(",");
                //set a temporary storeID and sales variables
                storeID = Integer.parseInt(tempValues[0]);
                sales = Integer.parseInt(tempValues[1]);

                //add the store to the hashmap
                stores.put(storeID, new Store(storeID, sales));
            }

            //end file reader because memory leaks and java doesn't auto close on a lack of use
            fis.close();
            
        } catch (IOException e) {
            //catch input output exception and then display error message

            e.printStackTrace();
        }
        // forloop over every store in stores and print out the value.toString() to the console, mostly a debugging tool
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
            //find the file and create a writer on it
            File file = new File("stores.csv");
            FileWriter fos = new FileWriter(file);

            //for every store in stores write the toCSV()
            for (Store store : stores.values()) {

                //write a line to the file
                fos.write(store.toCSV() + "\n");
            }
            //close the file writer
            fos.close();
        } catch (IOException e) {
            //catch the IO exception and display error message
            e.printStackTrace();
        }
    }

    /**
     * add a store to the stores map
     * 
     * @param store
     */
    public static void addStore(Store store) {
        stores.put(store.getID(), store);
    }

    /**
     * remove a store from the stores map
     * 
     * @param storeID
     */
    public static void removeStore(int storeID) {
        stores.remove(storeID);
    }
}

