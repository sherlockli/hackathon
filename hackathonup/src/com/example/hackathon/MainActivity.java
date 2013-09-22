package com.example.hackathon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;




import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;

public class MainActivity extends Activity {
	 private ArrayList<GroupData> 	groupData;			
	    private ArrayList<ChildData> 	childData;			
	    private List<String>   			groupName;			
	    public  ExpandableInfoAdapter 	sela;				
	    private final String 			NAME  = "NAME";		
	    private final String 			TOTAL = "TOTAL";
	    private HashMap<String,String>	oriData;			
	    public  ExpandableListView      mListView = null;	
	    class ExpandableListData 
	    {													
	         public List<GroupData> groupData;				
	         public int groupLayoutId;						
	         public int childLayoutId;						
	         public String[] groupFrom;						
	         public String[] childFrom;						
	         public int[] groupTo;							
	         public int[] childTo;						

	    public ExpandableListData(							
						List<GroupData> groupData,
	 					int groupLayoutId, 
	 					String[] groupFrom, 
	 					int[] groupTo,
	                    int childLayoutId, 
	                    String[] childFrom, 
	                    int[] childTo)
	     	 {
	          this.groupData = groupData;
	          this.groupLayoutId = groupLayoutId;
	          this.groupFrom = groupFrom;
	          this.groupTo = groupTo;
	          this.childLayoutId = childLayoutId;
	          this.childFrom = childFrom;
	          this.childTo = childTo;
	          }
	      }

		class GroupData 
		{													
			public String NAME;
			public ArrayList<ChildData> CHILDDATA;
		}

		class ChildData 
		{													
			public String NAME;
		}
	    
	    
	    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.friend);
		loadData();										
        initCtrl();	
        mListView.setOnChildClickListener(				
        		new OnChildClickListener()
					{
						@Override
						public boolean onChildClick(
								ExpandableListView parent, 		
								View view, 						
								int groupPosition, 				
								int childPosition, 				
								long id)						
						{
							String name = sela.getChildName(groupPosition, childPosition);
							Intent i0 = new Intent();
							if(name=="what is hackathon?"){
								i0.setClass(getBaseContext(), aboutHa.class);
								startActivity(i0);
							}
							else if(name=="the 1st GZhackathon"){
								i0.setClass(getBaseContext(), firstReport.class);
								startActivity(i0);
							}
							else if(name=="the 2nd GZhackathon"){
								i0.setClass(getBaseContext(), secondReport.class);
								startActivity(i0);
							}
							else if(name=="the 3rd GZhackathon"){
								i0.setClass(getBaseContext(), thirdReport.class);
								startActivity(i0);
							}
							else if(name=="the 1st time"){
								i0.setClass(getBaseContext(), firstDid.class);
								startActivity(i0);
							}
							else if(name=="the 2nd time"){
								i0.setClass(getBaseContext(), secondDid.class);
								startActivity(i0);
							}
							else if(name=="the 3rd time"){
								i0.setClass(getBaseContext(), thirdDid.class);
								startActivity(i0);
							}
							else if(name=="about us"){
								i0.setClass(getBaseContext(), aboutUs.class);
								startActivity(i0);
							}
							else{
								Toast.makeText(getBaseContext(), "you can get in", Toast.LENGTH_LONG).show();
							}
							return true;
						}
					});
    }
	    protected void initCtrl()
		{
			mListView =(ExpandableListView) findViewById(R.id.expandable_list_view); 
			ExpandableListData ed = new ExpandableListData(							 
					groupData, 														 
					R.layout.friend_group, 											 
					new String[] {NAME, TOTAL}, 									 
					new int[] { R.id.friend_group_name, R.id.friend_group_total}, 	 
					R.layout.friend_child,											 
					new String[] {NAME}, 											 
					new int[] { R.id.friend_child_name});							
			
			sela = new ExpandableInfoAdapter(this, ed);								 
			mListView.setAdapter(sela);												
		}
	    protected void loadData()
		{
			oriData = new HashMap<String, String>();			
			oriData.put( "add text","what do you want to say?");
			oriData.put( "about us","our tream");
			oriData.put( "the 3rd time",  "what did we do?");
			oriData.put( "the 2nd time",  "what did we do?");
			oriData.put( "the 1st time",  "what did we do?");
			oriData.put( "the 3rd GZhackathon",  "activity report");
			oriData.put( "the 2nd GZhackathon",  "activity report");
			oriData.put( "the 1st GZhackathon",  "activity report");
			oriData.put( "what is hackathon?",  "hackathon");
			
			groupData = new ArrayList<GroupData>();				
			groupName = new ArrayList<String>();				
			ChildData cd = null;
		
			Iterator<Entry<String,String>> iter = oriData.entrySet().iterator();

			while(iter != null && iter.hasNext())				
			{
				Entry<String,String> entry = iter.next();
				String group = entry.getValue();
				if(!groupName.contains(group))
				{
					groupName.add(group);						
				}
			}

			Map<String, ArrayList<ChildData>> map = new HashMap<String, ArrayList<ChildData>>();
																
			for(String str : groupName)							
			{
				childData = new ArrayList<ChildData>();
				map.put(str, childData);						
			}
			Iterator<Entry<String,String>> iter1 = oriData.entrySet().iterator();
			while(iter1 != null && iter1.hasNext())								
			{
				cd = new ChildData();
				Entry<String,String> entry = iter1.next();
				String name = entry.getKey();									
				String group = entry.getValue();								
				cd.NAME = name;
				map.get(group).add(cd);											
			}

			for(String str : map.keySet())										
			{
				GroupData gd = new GroupData();									
				gd.NAME = str;
				gd.CHILDDATA = map.get(str);									
				groupData.add(gd);												
			}
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
