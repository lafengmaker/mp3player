package com.lafengmaker.mp3player.serivce;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import com.lafengmaker.download.HttpDownLoader;
import com.lafengmaker.model.ConfigInfo;
import com.lafengmaker.model.Mp3Info;
import com.lafengmaker.mp3player.MainActivity;
import com.lafengmaker.mp3player.R;

public class DownloadService extends Service {
	private static final int ID = 1;  
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Mp3Info mp3Info=(Mp3Info)intent.getSerializableExtra("mp3Info");
		ConfigInfo configInfo=new ConfigInfo(this);
		new DownLoadMp3(mp3Info,configInfo).start();
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
class DownLoadMp3 extends Thread{
		private Mp3Info mp3Info;
		private ConfigInfo configInfo;
		public DownLoadMp3(Mp3Info mp3Info,ConfigInfo configInfo) {
			super();
			this.mp3Info = mp3Info;
			this.configInfo=configInfo;
		}

		@Override
		public void run() {
			if(null!=mp3Info){
					HttpDownLoader httpDownLoader=new HttpDownLoader(configInfo);
					int result= httpDownLoader.downLoadFile(mp3Info.getMp3Name());
					String message=mp3Info.getMp3Name();
					if(result==1){
						message+="已经存在";
					}else if(result==-1){
						message+="下载失败";
					}else if(result==0){
						message+="下载成功";
					}else{
						message+="未知错误";
					}
					System.out.println(result);
					System.out.println(message);
					NotificationCompat.Builder mBuilder =
					        new NotificationCompat.Builder(DownloadService.this)
					        .setSmallIcon(R.drawable.icball)
					        .setContentTitle("mp3player 下载")
					        .setContentText(message);
					NotificationManager nm = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
					Intent resultIntent = new Intent(DownloadService.this, MainActivity.class);
					TaskStackBuilder stackBuilder = TaskStackBuilder.create(DownloadService.this);
					stackBuilder.addNextIntent(resultIntent);
					stackBuilder.addParentStack(MainActivity.class);
					PendingIntent resultPendingIntent = stackBuilder.getPendingIntent( 0, PendingIntent.FLAG_UPDATE_CURRENT);
					mBuilder.setContentIntent(resultPendingIntent);
			        nm.notify(ID, mBuilder.build());
			}
		}
		
	}
}
