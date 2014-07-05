package com.lafengmaker.mp3player;

import java.io.StringReader;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import com.lafengmaker.download.HttpDownLoader;
import com.lafengmaker.model.ConfigInfo;
import com.lafengmaker.model.Mp3Info;
import com.lafengmaker.util.FileUtil;
import com.lafengmaker.xml.Mp3ListContentHandler;

public  class RemoteMp3ListFragment extends ListFragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		System.out.println("onCreate");
		super.onCreate(savedInstanceState);
		updateList();
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		System.out.println("onCreateView");
		return inflater.inflate(R.layout.remote_mp3_list,container,false);
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		System.out.println("onActivityCreated");
		//new UpdateList().start();
		super.onActivityCreated(savedInstanceState);
	}
//	@Override
//	protected void onListItemClick(ListView l, View v, int position, long id) {
//		info=infos.get(position);
//		Intent intent =new Intent();
//		intent.putExtra("mp3Info", info);
//		intent.setClass(this, DownloadService.class);
//		startService(intent);
//		super.onListItemClick(l, v, position, id);
//	}
//	List<Mp3Info> infos=null;
//	Mp3Info info=null;
//	private Handler messagehandler =new Handler(){
//		@Override
//		public void handleMessage(Message msg) {
//			SimpleAdapter simpleAdapter=(SimpleAdapter)msg.obj;
//			setListAdapter(simpleAdapter);
//		}
//	};

	public void updateList(){
		new UpdateList().start();
	}
	static class MyHandler extends Handler {
        WeakReference<RemoteMp3ListFragment> mActivity;

        MyHandler(RemoteMp3ListFragment activity) {
                mActivity = new WeakReference<RemoteMp3ListFragment>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
        	RemoteMp3ListFragment theActivity = mActivity.get();
			SimpleAdapter simpleAdapter=(SimpleAdapter)msg.obj;
			theActivity.setListAdapter(simpleAdapter);
        }
};
 MyHandler myHandler=new MyHandler(this);
class UpdateList extends Thread{
	@Override
	public void run() {
		try {
			ConfigInfo configInfo=new ConfigInfo(getActivity());
			HttpDownLoader httpDownLoader=new HttpDownLoader(configInfo);
			String xmlstr= httpDownLoader.download("mp3/mp3.xml");
			List<Mp3Info> infos=new ArrayList<Mp3Info>();
			Mp3ListContentHandler handler=new Mp3ListContentHandler(infos);
			XMLReader xmlReader=FileUtil.getXMLReader();
			xmlReader.setContentHandler(handler);
			xmlReader.parse(new InputSource(new  StringReader(xmlstr)));
			List<Map<String, String>>list=new ArrayList<Map<String,String>>();
			System.out.println("mp3 total:"+list.size());
			for(Mp3Info mp3Info:infos){
				list.add(mp3Info.toValueMap());
			}
			SimpleAdapter simpleAdapter=new SimpleAdapter(getActivity(), list, R.layout.mp3info_item, new String[]{"mp3Name","mp3size"}, new int[]{R.id.mp3_name,R.id.mp3_size});
			Message msg=new Message();
			msg.obj=simpleAdapter;
			RemoteMp3ListFragment.this.myHandler.sendMessage(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}
	

}
