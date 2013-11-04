package Items.Abstract;

import Items.Interface.IDbItem;
import Library.Db.Manager;

import java.util.*;

public abstract class ItemAbstract extends Manager implements IDbItem{

    public ItemAbstract(String name) {
        super(name);
    }

    /**
     * Updates row with given data
     *
     * @param data  data array
     * @param table table name
     * @param rowId row id witch will be updated
     *
     * @return row id
     */
    @java.lang.Override
    public abstract int saveRow(HashMap<String, Object> data, String table, int rowId);

    /**
     * Inserts row with given data
     *
     * @param data  data array
     * @param table table name
     *
     * @return row id
     */
    @java.lang.Override
    public abstract int saveRow(HashMap<String, Object> data, String table);

    /**
     * Fetches the row with given filter
     *
     * @param filter
     * @param table
     *
     * @return fetched row
     */
    @java.lang.Override
    public HashMap<String, Object> fetchRow(HashMap<String, Object> filter, String table) {
        super.setTable(table);

        return super.fetchRow(filter);
    }

    /**
     * Fetches the row with given filter
     *
     * @param filter
     *
     * @return
     */
    @java.lang.Override
    public abstract HashMap<String, Object> fetchRow(HashMap<String, Object> filter);
}
