package classes;

import javax.swing.table.*;
import java.util.*;

public class storeTableModel extends AbstractTableModel {

    
    public HashMap<Integer, Store> storeList;
    @Override
    public int getRowCount() {
        
        return storeList.size();
    }

    @Override
    public int getColumnCount() {
        // TODO Auto-generated method stub
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex == 0){
            return storeList.get(storeList.keySet().toArray()[rowIndex]).getID();
        } else {
            return storeList.get(storeList.keySet().toArray()[rowIndex]).getSales();
        }
    }
   

}
