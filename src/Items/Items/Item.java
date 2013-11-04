package Items.Items;

import Items.Abstract.ItemAbstract;
import Items.Interface.IItem;
import Library.Db.Manager;

import java.lang.reflect.Field;
import java.util.*;

/**
 * User: Jokubas
 * Date: 13.9.23
 * Time: 17.36
 */
public class Item extends ItemAbstract implements IItem {
    /**
     * Item constructor
     * @param name database table name
     */
    public Item(String name) {
        super(name);
    }

     /**
     * Updates given data to database
     * @param data  associative array. Where index defines column name.
     * @param table database table name
     * @param rowId if given, then this row will be updated
     * @return row id
     */
    public int saveRow(HashMap<String, Object> data, String table, int rowId) {
        if (null != table) {
            setTable(table);
        }

        return insert(data);
    }

    /**
     * Inserts given data to database
     * @param data  associative array. Where index defines column name.
     * @param table database table name
     * @return row id
     */
    public int saveRow(HashMap<String, Object> data, String table) {
        return this.saveRow(data, table, 0);
    }

    /**
     * Inserts given data to database
     * @param data  associative array. Where index defines column name.
     * @return row id
     */
    public int saveRow(HashMap<String, Object> data) {
        return this.saveRow(data, this.getTable());
    }

    /**
     * Fetches the row from database
     * @param filter key is column name, value is value
     * @param table database table name
     * @return HashMap<String, Object>
     */
    @Override
    public HashMap<String, Object> fetchRow(HashMap<String, Object> filter, String table) {
        return super.fetchRow(filter, table);
    }
    /**
     * Fetches the row from database
     * @param filter key is column name, value is value
     * @return HashMap<String, Object>
     */
    @Override
    public HashMap<String, Object> fetchRow(HashMap<String, Object> filter) {
        return this.fetchRow(filter, getTable());
    }
}
