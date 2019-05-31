package com.example.lab10application;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView listContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listContacts = findViewById(R.id.listContacts);
        ArrayList<Contact> cList = getAllContacts();
        AdapterContacts contactAdapter = new AdapterContacts(cList, getApplicationContext());
        listContacts.setAdapter(contactAdapter);

        String[] stockArr = new String[cList.size()];
        for(int i=0;i<cList.size();i++){
            stockArr[i] = cList.get(i).getContactName()+", "+cList.get(i).getContactPhone();
            System.out.println(stockArr[i]);
        }

        Intent intent = new Intent();
        intent.setAction("com.example.lab10application");
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        final String Second_MainActivity = "com.example.otherSecondApp.MainActivity";
        final String Second_Apps_Package = "com.example.otherSecondApp";
        intent.setComponent(new ComponentName(Second_Apps_Package, Second_MainActivity));
        intent.putExtra("data",stockArr);
        sendBroadcast(intent);
    }

    private ArrayList<Contact> getAllContacts() {
        ArrayList contactList = new ArrayList<Contact>();
        Contact contact;

        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");
        assert cursor != null;
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));
                if (hasPhoneNumber > 0) {
                    String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    contact = new Contact();
                    contact.setContactName(name);
                    Cursor phoneCursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}, null);
                    assert phoneCursor != null;
                    if (phoneCursor.moveToNext()) {
                        String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        contact.setContactPhone(phoneNumber);
                    }
                    phoneCursor.close();
                    contactList.add(contact);
                }
            }
        }
        listContacts.setLayoutManager(new LinearLayoutManager(this));
        return contactList;
    }

}