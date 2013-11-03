package Items.Interface;

import java.util.*;

/**
 * User: Jokubas
 * Date: 13.9.23
 * Time: 17.25
 */
public interface IItem {

    /**
     * Updates row with given data
     * @param data  data array
     * @param table table name
     * @param rowId row id witch will be updated
     * @return row id
     */
    public int saveRow(HashMap<String, Object> data, String table, int rowId);

    /**
     * Inserts row with given data
     * @param data  data array
     * @param table table name
     * @return row id
     */
    public int saveRow(HashMap<String, Object> data, String table);

    /**
     * Fetches the row with given filter
     * @param filter
     * @param table
     * @return fetched row
     */
    public HashMap<String, Object> fetchRow(HashMap<String, Object> filter, String table);

    /**
     * Fetches the row with given filter
     * @param filter
     * @return
     */
    public HashMap<String, Object> fetchRow(HashMap<String, Object> filter);
}
