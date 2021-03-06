package com.codingdie.rwsdatabase.connection;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.codingdie.rwsdatabase.orm.util.RWSCursorResultReflectUtil;

import java.util.List;

/**
 * Created by xupeng on 2016/8/26.
 */
public class ReadableConnection extends SQLiteConnection {

    protected static ReadableConnection createReadableConnection(String dbPath, int index) {
        ReadableConnection readableConnection = new ReadableConnection();
        readableConnection.setInUsing(false);
        readableConnection.setWritable(false);
        readableConnection.setIndex(index);
        SQLiteDatabase sqLiteDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY | SQLiteDatabase.CREATE_IF_NECESSARY);
        sqLiteDatabase.enableWriteAheadLogging();
        readableConnection.setSqLiteDatabase(sqLiteDatabase);
        return readableConnection;
    }

    public Cursor execReadSQL(String sql, String[] param) {
        return this.sqLiteDatabase.rawQuery(sql, param);
    }

    @Deprecated
    public Cursor query(String table, String[] columns, String selection,
                        String[] selectionArgs, String groupBy, String having,
                        String orderBy, String limit) {
        return this.sqLiteDatabase.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
    }

    public <T> T queryObject(String table, String[] columns, String selection,
                             String[] selectionArgs, String groupBy, String having,
                             String orderBy, String limit, Class<T> tClass, String[]... ignoreProps) {
        return RWSCursorResultReflectUtil.toObject(this.sqLiteDatabase.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit), tClass, ignoreProps);
    }

    public <T> T queryObject(String sql, String[] param, Class<T> tClass, String[]... ignoreProps) {
        return RWSCursorResultReflectUtil.toObject(this.execReadSQL(sql, param), tClass, ignoreProps);
    }

    public <E> List<E> queryObjectList(String sql, String[] param, Class<E> tClass, String[]... ignoreProps) {
        return RWSCursorResultReflectUtil.toList(this.execReadSQL(sql, param), tClass, ignoreProps);
    }

    public <E> List<E> queryObjectList(String table, String[] columns, String selection,
                                       String[] selectionArgs, String groupBy, String having,
                                       String orderBy, String limit, Class<E> tClass, String[]... ignoreProps) {
        return RWSCursorResultReflectUtil.toList(this.sqLiteDatabase.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit), tClass, ignoreProps);
    }

    protected RWSTableInfo getTableInfo(String tableName) {
        RWSTableInfo rwsTableInfo = new RWSTableInfo();
        rwsTableInfo.setName(tableName);
        List<RWSColumInfo> columList = this.queryObjectList("pragma table_info([" + tableName + "])", new String[]{}, RWSColumInfo.class);
        rwsTableInfo.setColums(columList);
        return rwsTableInfo;
    }

}
