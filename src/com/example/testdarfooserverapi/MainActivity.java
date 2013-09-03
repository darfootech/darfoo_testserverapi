package com.example.testdarfooserverapi;

import java.util.ArrayList;

import serverAPI.PluginAPI;
import serverAPI.SyncAPI;

import model.CustomModel;
import model.PluginModel;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button postcustomButton = null;
	private Button getpluginlistButton = null;
	private Button getcustomlistButton = null;

	private ArrayList<PluginModel> plugins = new ArrayList<PluginModel>();
	private ArrayList<CustomModel> customs = new ArrayList<CustomModel>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		this.postcustomButton = (Button) this.findViewById(R.id.postcustom);
		this.getpluginlistButton = (Button) this
				.findViewById(R.id.getpluginlist);
		this.getcustomlistButton = (Button) this
				.findViewById(R.id.getcustomlist);

		this.getpluginlistButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				System.out.println("getplugin clicked");
				new Thread(getPluginListRunnable).start();
			}
		});
		
		this.getcustomlistButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.out.println("getcustom clicked");
				new Thread(getCustomListRunnable).start();
			}
		});
		
		this.postcustomButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.out.println("postcustom clicked");
				new Thread(postCustomRunnable).start();
			}
		});
	}

	Runnable getPluginListRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			plugins = (ArrayList<PluginModel>) PluginAPI.getPluginList();
			System.out.println("cleantha===>" + plugins.size());
			for (PluginModel pluginModel : plugins) {
				System.out.println("packagename===>" + pluginModel.packageName);
				System.out.println("classname===>" + pluginModel.className);
				System.out.println("packageurl===>" + pluginModel.url);
				System.out.println("picurl===>" + pluginModel.picurl);
			}

		}
	};
	
	Runnable getCustomListRunnable = new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			customs = (ArrayList<CustomModel>) SyncAPI.getSettings();
			System.out.println("cleantha===>" + customs.size());
			for(CustomModel customModel : customs){
				System.out.println("packagename===>" + customModel.packageName);
				System.out.println("width===>" + customModel.width);
				System.out.println("height===>" + customModel.height);
				System.out.println("leftmargin===>" + customModel.leftMargin);
				System.out.println("topmargin===>" + customModel.topMargin);
			}
		}
	};
	
	Runnable postCustomRunnable = new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			SyncAPI.uploadSetting("darfoocleantha", 3, 3, 3, 3);
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
