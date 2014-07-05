package com.lafengmaker.mp3player;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.lafengmaker.model.Mp3Info;

public class MainActivity extends FragmentActivity {
	List<Mp3Info> infos=null;
	Mp3Info info=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.mp3player_main, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}else if(id==R.id.action_updatelist){
					System.out.println("update list");
					RemoteMp3ListFragment fragment=
							(RemoteMp3ListFragment)getSupportFragmentManager().findFragmentById(R.id.remote_frag_map3_list);
					fragment.updateList();
					
		}
		return super.onOptionsItemSelected(item);
	}
//	class UpdateList extends Thread{
//		@Override
//		public void run() {
//			try {
//				ConfigInfo configInfo=new ConfigInfo(MainActivity.this);
//				HttpDownLoader httpDownLoader=new HttpDownLoader(configInfo);
//				String xmlstr= httpDownLoader.download("mp3/mp3.xml");
//				infos=new ArrayList<Mp3Info>();
//				Mp3ListContentHandler handler=new Mp3ListContentHandler(infos);
//				XMLReader xmlReader=FileUtil.getXMLReader();
//				xmlReader.setContentHandler(handler);
//				xmlReader.parse(new InputSource(new  StringReader(xmlstr)));
//				List<Map<String, String>>list=new ArrayList<Map<String,String>>();
//				for(Mp3Info mp3Info:infos){
//					list.add(mp3Info.toValueMap());
//				}
//				SimpleAdapter simpleAdapter=new SimpleAdapter(MainActivity.this, list, R.layout.mp3info_item, new String[]{"mp3Name","mp3size"}, new int[]{R.id.mp3_name,R.id.mp3_size});
//				RemoteMp3ListFragment fragment=(RemoteMp3ListFragment)getFragmentManager().findFragmentById(R.id.remote_frag_map3_list);
//				fragment.setListAda(simpleAdapter);
////				Message message = Message.obtain();
////				message.obj=simpleAdapter;
////				messagehandler.sendMessage(message);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
		
//	}
}
