package Items.Items;
import Items.Abstract.Item;
import java.lang.reflect.Field;

/**
 * User: Jokubas
 * Date: 13.9.23
 * Time: 17.36
 */
public class DbItem extends Item  {

    /**
     * Database table name
     */
    private String _table;

    /**
     * Initialize DbItem class
     * @param table database table name
     */
    public void Init(String table) {
        this._table = table;
    }

    /**
     * Saves given data to database
     * @param data  associative array. Where index defines collumn name.
     * @param table database table name
     * @param rowId if given, then this row will be updated
     * @return TRUE - if everything went fine, FALSE - if something got fucked up
     */
    public boolean saveRow(String[] data, String table, int rowId) {
        // TODO Implement saving to row method here

        return true;
    }


    public void toArray() {
        Field[] fields = this.getClass().getDeclaredFields();

        String[] data;

        for (Field field : fields) {

        }
    }

}
