import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import classes.Store;

public class gameStop {
    // hashmap to store the stores by integer ID
    private static HashMap<Integer, Store> stores = new HashMap<Integer, Store>();

    /**
     * execution args, not used
     * 
     * @param args
     */
    public static void main(String[] args) {
        readStores();
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

                stores.put(storeID, new Store(storeID, (double) sales / 100));
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


    
}
