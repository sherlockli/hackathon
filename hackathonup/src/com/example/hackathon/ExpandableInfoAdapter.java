package com.example.hackathon;



import com.example.hackathon.MainActivity.ChildData;
import com.example.hackathon.MainActivity.ExpandableListData;
import com.example.hackathon.MainActivity.GroupData;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ExpandableInfoAdapter extends BaseExpandableListAdapter{
	private ExpandableListData ed;			
	private Context		mContext  = null;	
	
	class ExpandableListChildView
	{
		TextView name;						
	}
	
	class ExpandableListGroupView
	{										
		TextView name;						
		TextView total;						
	}
	public ExpandableInfoAdapter(Context context,ExpandableListData ed)
	{
		this.ed = ed;
		mContext  = context;
	}

	public ChildData getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return ed.groupData.get(groupPosition).CHILDDATA.get(childPosition);
	}
	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}
	@Override
	public View getChildView(int groupPosition, 				
			int childPosition,				
			boolean isLastChild,			
			View convertView, 				
			ViewGroup parent)				 {
		// TODO Auto-generated method stub
		ExpandableListChildView elcv = null;
		if(convertView == null)
		{
		    LayoutInflater inflater = LayoutInflater.from(mContext);
		    convertView = (LinearLayout) inflater.inflate(ed.childLayoutId, null, false);
			elcv = new ExpandableListChildView();			
			elcv.name = (TextView) convertView.findViewById(ed.childTo[0]);
			convertView.setTag(elcv);						
		} else   
            elcv = (ExpandableListChildView) convertView.getTag();  
		
		ChildData cd = getChild(groupPosition, childPosition);
		if(cd != null)
		{
			elcv.name.setText(cd.NAME);						
		}
		return convertView;									
	}
	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return ed.groupData.get(groupPosition).CHILDDATA.size();
	}
	@Override
	public GroupData getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return ed.groupData.get(groupPosition);
	}
	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return ed.groupData.size();		
	}
	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;	
	}
	@Override
	public View getGroupView(int position, boolean flag, View view, ViewGroup parent) {
		// TODO Auto-generated method stub
		ExpandableListGroupView elgv = null;  
	    if (view == null) {  
		    LayoutInflater inflater = LayoutInflater.from(mContext);
		    view =  inflater.inflate(ed.groupLayoutId, null, false);
	        elgv = new ExpandableListGroupView();  
	        elgv.name = (TextView)view.findViewById(ed.groupTo[0]);	
	        elgv.total = (TextView)view.findViewById(ed.groupTo[1]);
	        view.setTag(elgv);  
	    } 
	    else 
	        elgv = (ExpandableListGroupView) view.getTag();  
	    GroupData gd = getGroup(position); 							
	    if (gd != null) 
	    {
			elgv.name.setText(gd.NAME);								
			
	    }
	    return view; 
	}
	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;	
	}
	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;	
	}
	public String getChildName(int groupPosition, int childPosition)
	{
		return getChild(groupPosition, childPosition).NAME;
	}

}
