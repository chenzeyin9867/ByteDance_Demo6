package com.byted.camp.todolist.db;

import android.provider.BaseColumns;

/**
 * Created on 2019/1/22.
 *
 * @author xuyingyi@bytedance.com (Yingyi Xu)
 */
public final class TodoContract {

    // TODO 定义表结构和 SQL 语句常量

    private TodoContract() {
    }


    public static class FeedEntry implements BaseColumns{
        public static final String TABLE_NAME = "entry";
        public static final String CONTEXT = "context";
        public static final String STATE = "state";
        public static final String DATE = "date";
        public static final String PRIORITY = "priority";

    }

    public static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
            FeedEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            FeedEntry.DATE + " TEXT,"+
            FeedEntry.CONTEXT +" TEXT,"+
            FeedEntry.PRIORITY + " INTEGER," +
            FeedEntry.STATE + " INTEGER)";
    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXSITS " + FeedEntry.TABLE_NAME;


}
