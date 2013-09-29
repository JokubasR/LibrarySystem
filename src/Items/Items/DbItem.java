package Items.Items;
import Items.Interface.Item;
import java.lang.reflect.Field;

/**
 * User: Jokubas
 * Date: 13.9.23
 * Time: 17.36
 */
public class DbItem implements Item  {

    /**
     * Database table name
     */
    private String _table;

    /**
     * DbItem constructor
     * @param name database table name
     */
    public DbItem(String name) {
        this._table = name;
    }

    /**
     * Sets database table name
     * @param name database table name
     */
    public void setTable(String name) {
        this._table = name;
    }

    /**
     * Updates given data to database
     * @param data  associative array. Where index defines collumn name.
     * @param table database table name
     * @param rowId if given, then this row will be updated
     * @return row id
     */
    public int saveRow(String[] data, String table, int rowId) {
        // TODO Implement saving to row method here

        return 0;
    }

    /**
     * Inserts given data to database
     * @param data  associative array. Where index defines collumn name.
     * @param table database table name
     * @return row id
     */
    public int saveRow(String[] data, String table) {
        return this.saveRow(data, table, 0);
    }

    /**
     * Inserts given data to database
     * @param data  associative array. Where index defines collumn name.
     * @return row id
     */
    public int saveRow(String[] data) {
        return this.saveRow(data, this._table);
    }

    /**
     * Probably I ain't gonna use it
     */
    public void toArray() {
        Field[] fields = this.getClass().getDeclaredFields();

        String[] data;

        for (Field field : fields) {

        }
    }

}
