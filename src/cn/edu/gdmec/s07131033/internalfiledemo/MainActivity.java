package cn.edu.gdmec.s07131033.internalfiledemo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	TextView myTv,myTv1;
	EditText myEt;
	CheckBox myCb;
	private final String NAME = "internal";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myEt = (EditText) findViewById(R.id.editText1);
        myCb = (CheckBox) findViewById(R.id.checkBox1);
        myTv= (TextView) findViewById(R.id.textView1);
        myTv1=(TextView) findViewById(R.id.textView2);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public void READFILE(View v)
    {
    	FileInputStream fis = null;
		String str = "";
		try {
			fis = openFileInput(NAME);
			if (fis.available() == 0) {
				return;
			}
			byte buffer[] = new byte[fis.available()];
			while(fis.read(buffer)!=-1){
			str += new String(buffer);
			}
			myTv.setText(str);
			myTv1.setText("�ļ���ȡ�ɹ�,�ļ�����"+str.length());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				if(fis!=null){
					fis.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

    	
    }
    public void WRITEFILE(View v)
    {
    FileOutputStream fos = null;
	try {
		if (myCb.isChecked()) {
			fos = openFileOutput(NAME, Context.MODE_APPEND);
		} else {
			fos = openFileOutput(NAME, Context.MODE_PRIVATE);
		}
		String str = myEt.getText().toString();
		fos.write(str.getBytes());
		myTv1.setText("�ļ�д��ɹ�,д�볤��"+str.length());
	    myEt.setText("");
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally{
		try {
			if(fos!=null){
			fos.flush();
			fos.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}}
    
}
