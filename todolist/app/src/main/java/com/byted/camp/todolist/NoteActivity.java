package com.byted.camp.todolist;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.byted.camp.todolist.beans.State;
import com.byted.camp.todolist.db.TodoContract;
import com.byted.camp.todolist.db.TodoContract.FeedEntry;
import com.byted.camp.todolist.db.TodoDbHelper;

import java.util.Date;

public class NoteActivity extends AppCompatActivity {
    private int priority = 0;
    private EditText editText;
    private Button addBtn;
    private SQLiteDatabase db;
    private TodoDbHelper todoDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        setTitle(R.string.take_a_note);
        todoDbHelper = new TodoDbHelper(this);
        db = todoDbHelper.getWritableDatabase();
        editText = findViewById(R.id.edit_text);
        editText.setFocusable(true);
        editText.requestFocus();



        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null) {
            inputManager.showSoftInput(editText, 0);
        }

        final CheckBox checkBox1= findViewById(R.id.checkbox1);
        final CheckBox checkBox2= findViewById(R.id.checkbox2);
        final CheckBox checkBox3 = findViewById(R.id.checkbox3);

        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //checkBox1.setChecked(true);
                System.out.println("set here 3");
                priority = 3;
            }
        });
        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
               //checkBox2.setChecked(true);
                System.out.println("set here 2");
                priority = 2;
            }
        });
        checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //checkBox3.setChecked(true);
                System.out.println("set here 1");
                priority = 1;
            }
        });


        addBtn = findViewById(R.id.btn_add);


        //System.out.println("priority is :" + priority);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence content = editText.getText();
                if (TextUtils.isEmpty(content)) {
                    Toast.makeText(NoteActivity.this,
                            "No content to add", Toast.LENGTH_SHORT).show();
                    return;
                }
                System.out.println("HERE PRI IS " + priority);
                boolean succeed = saveNote2Database(content.toString().trim(), priority);

                if (succeed) {
                    Toast.makeText(NoteActivity.this,
                            "Note added", Toast.LENGTH_SHORT).show();
                    setResult(Activity.RESULT_OK);
                } else {
                    Toast.makeText(NoteActivity.this,
                            "Error", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private boolean saveNote2Database(String content,int priority) {
        // TODO 插入一条新数据，返回是否插入成功
        ContentValues contentValues = new ContentValues();
        contentValues.put(FeedEntry.CONTEXT,content);
        contentValues.put(FeedEntry.STATE, 0);
        contentValues.put(FeedEntry.DATE,new Date().getTime());
        contentValues.put(FeedEntry.PRIORITY,priority);
        long newId = db.insert(FeedEntry.TABLE_NAME,null,contentValues);
        if(newId == -1L){
            return false;
        }
        db.close();
        todoDbHelper.close();
        return true;
    }
}
