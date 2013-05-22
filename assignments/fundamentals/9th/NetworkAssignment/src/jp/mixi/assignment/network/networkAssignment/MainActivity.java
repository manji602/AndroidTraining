package jp.mixi.assignment.network.networkAssignment;

<<<<<<< Updated upstream
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
=======
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.xmlpull.v1.XmlPullParser;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.util.Xml;
>>>>>>> Stashed changes
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            StrictMode.setThreadPolicy(
                    new StrictMode.ThreadPolicy.Builder()
                    .detectNetwork()
                    .penaltyDeath()
                    .build());
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View buttonGet = findViewById(R.id.buttonGet);
        
        buttonGet.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                // http getの処理を書く
            	MyAsyncTask asyncTask = new MyAsyncTask();
            	asyncTask.execute();
            }
        });
    }
    
    String getYoutubeURL(){
    	HttpURLConnection connection = null;
    	String responseBody = new String();
    	try {
    		String YOUTUBE_URL = "http://gdata.youtube.com/feeds/api/standardfeeds/top_rated";
    		URL url = new URL(YOUTUBE_URL);
    		connection = (HttpURLConnection) url.openConnection();
    		connection.connect();
    		InputStream is = connection.getInputStream();
    		
    		StringBuilder src = new StringBuilder();
    		while (true) {
    			byte[] line = new byte[1024];
    			int size = is.read(line);
    				if (size <= 0)
    					break;
    			src.append(new String(line));
             }
             responseBody = src.toString();
         } catch (IOException e) {
        	 Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
             e.printStackTrace();
         } finally{
             connection.disconnect();
         }
     	return responseBody;
    }

    private void disableConnectionReuseIfNecessary() {
        // HTTP connection reuse which was buggy pre-froyo
        if (Integer.parseInt(Build.VERSION.SDK) < Build.VERSION_CODES.FROYO) {
            System.setProperty("http.keepAlive", "false");
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    /**
     * 非同期処理を実行するためのネストクラス。
     * Activity などのライフサイクルに合わせた管理は自分でする必要があるが、
     * この例では特にしていないので、Activity が GC されると良くないことが起こる。
     *
     * ジェネリクスの仕組みを用いて、非同期処理に渡す引数の型、進捗を監視するコールバック用の型、非同期処理の結果を表す型を指定する。
     *
     * @author keishin.yokomaku
     */

    public class MyAsyncTask extends AsyncTask<Void, Integer, Void> {
    	String responseBody = new String();
    	String youtubeResponse = new String();
        /**
         * 非同期処理を実行する前に UI スレッドで実行する処理を書く
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /**
         * 非同期処理の進捗を受け取るコールバック。
         */
        @Override
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
        }

        /**
         * 非同期処理の本体で、UI スレッドではない別のスレッドで処理する内容。
         * 引数は非同期処理内容に渡すためのパラメータの配列。
         */
        @Override
        protected Void doInBackground(Void... params) {
        	youtubeResponse = getYoutubeURL();        	
            return null;
        }

        /**
         * 非同期処理の実行後に、UI スレッドで実行する処理。
         * 引数は {@link AsyncTask#execute(Object...)} の返り値。
         */
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            
        	XmlPullParser xmlPullParser = Xml.newPullParser();
        	try {
        		InputStream bais = new ByteArrayInputStream(youtubeResponse.getBytes("utf-8"));
        		xmlPullParser.setInput(bais, "UTF-8");
        		int eventType = xmlPullParser.getEventType();
        		int counter = 0;
        		while (eventType != XmlPullParser.END_DOCUMENT) {
        			if (eventType == XmlPullParser.START_TAG){// && xmlPullParser.getName().equals("name")){
        				counter ++;
        			}
        			eventType = xmlPullParser.next();
        		}
        		//responseBody = XmlPullParser.END_DOCUMENT + " ";
        		responseBody = Integer.toString(counter) + "";
        	} catch (Exception e) {
        		Toast.makeText(MainActivity.this, e + " ", Toast.LENGTH_LONG).show();
        		Toast.makeText(MainActivity.this, e + " ", Toast.LENGTH_LONG).show();
        		e.printStackTrace();
                TextView text = (TextView)findViewById(R.id.response);
                text.setText(e + "");
        	}
            
            
            Toast.makeText(MainActivity.this, responseBody + " ", Toast.LENGTH_SHORT).show();
            TextView text = (TextView)findViewById(R.id.response);
            //text.setText(youtubeResponse);
        }
    }
}
