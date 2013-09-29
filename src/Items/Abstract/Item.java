package Items.Abstract;

/**
 * User: Jokubas
 * Date: 13.9.23
 * Time: 17.25
 */
public abstract class Item {

    /**
     * Updates row with given data
     * @param data  data array
     * @param table table name
     * @param rowId row id witch will be updated
     * @return row id
     */
    public abstract int saveRow(String[] data, String table, int rowId);

    /**
     * Inserts row with given data
     * @param data  data array
     * @param table table name
     * @return row id
     */
    public abstract int saveRow(String[] data, String table);
}
