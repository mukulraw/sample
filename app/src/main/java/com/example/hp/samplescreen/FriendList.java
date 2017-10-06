package com.example.hp.samplescreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;



import java.util.ArrayList;
import java.util.HashMap;

public class FriendList extends Activity {

	private ListView lvFriendList;

	private ArrayList<HashMap<String, String>> friendList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.friend_list);

		lvFriendList = (ListView) findViewById(R.id.lvFriendList);

		displayFriendList();
	}

	@SuppressWarnings("unchecked")
	private void displayFriendList() {
		friendList = (ArrayList<HashMap<String, String>>) getIntent()
				.getSerializableExtra(MainActivity.FRIEND_LIST);

		FriendListAdapter adapter = new FriendListAdapter(this, friendList);
		lvFriendList.setAdapter(adapter);


	}




}
