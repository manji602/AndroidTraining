package jp.mixi.practice.network.networkpractice1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;

import android.os.Bundle;
import android.util.Log;
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
            	EditText urlEditText = (EditText)findViewById(R.id.accessUrl);
            	String urlString = urlEditText.getText().toString();
            	MyAsyncTask asyncTask = new MyAsyncTask();
            	//asyncTask.execute(urlString, "GET", "HTTPURLConnection", "");
            	asyncTask.execute(urlString, "GET", "HTTPClient", "");
            }
        });
        View buttonPost = findViewById(R.id.buttonPost);
        buttonPost.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                // http postの処理を書く
            	EditText urlEditText = (EditText)findViewById(R.id.accessUrl);
            	EditText postEditText = (EditText)findViewById(R.id.httpBody);
            	String urlString = urlEditText.getText().toString();
            	String postData = postEditText.getText().toString();
            	MyAsyncTask asyncTask = new MyAsyncTask();
            	//asyncTask.execute(urlString, "POST", "HTTPURLConnection", postData);
            	asyncTask.execute(urlString, "POST", "HTTPClient", postData);
            }
        });
    }
    
    String getHTTPURLConnection(String urlString){
    	HttpURLConnection connection = null;
    	String responseBody = new String();
     	try {
        	 URL url = new URL(urlString);
        	 connection = (HttpURLConnection) url.openConnection();
             connection.connect();
             InputStream is = connection.getInputStream();
             
             StringBuilder src = new StringBuilder();
             while (true) {
                 byte[] line = new byte[1024];
                 int size = is.read(line);
                 if (size <= 0)
                     break;
                 src.append(new String(line, "euc-jp"));
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
    
    String getHTTPClient(String urlString){
    	HttpClient client = new DefaultHttpClient();
    	String responseBody = new String();
        try {
            responseBody = client.execute(new HttpGet(urlString),
                    new ResponseHandler<String>() {
                        public String handleResponse(HttpResponse response)
                                throws ClientProtocolException, IOException {
                            return EntityUtils.toString(response.getEntity());
                        }
                    });
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseBody;
    }

	String postHTTPURLConnection(String urlString, String postData){
        HttpURLConnection connection = null;
        String responseBody = new String();
        try {
        	URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            OutputStream os = connection.getOutputStream();
            os.write(postData.getBytes());
            os.flush();
            os.close();

            InputStream is = connection.getInputStream();

            StringBuilder src = new StringBuilder();
            while (true) {
                byte[] line = new byte[1024];
                int size = is.read(line);
                if (size <= 0)
                    break;
                src.append(new String(line, "euc-jp"));
            }
            responseBody = src.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            connection.disconnect();
        }
        return responseBody;
    }
	
	String postHTTPClient(String urlString, String postData){
		HttpPost request = new HttpPost(urlString);
		String responseBody = new String();
		try {
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			request.setEntity(new UrlEncodedFormEntity(params));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		HttpClient client = new DefaultHttpClient();
	    try {
	      responseBody = client.execute(request,
	    		  new ResponseHandler<String>() {
	    	          public String handleResponse(HttpResponse response)
	    	        		  throws IOException {
	    	        	  	return EntityUtils.toString(response.getEntity());
	    	          }
	      });
	    } catch (IOException e) {
	    	e.printStackTrace();
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

    public class MyAsyncTask extends AsyncTask<String, Integer, Void> {
    	String responseBody = new String();
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
        //params
        //第1引数:URL
        //第2引数:GET or POST
        //第3引数:HTTPURLConnection or HTTPClient
        //第4引数:postData
        protected Void doInBackground(String... params) {
        	String urlString = params[0];
        	String HTTPMethod = params[1];
        	String connectMethod = params[2];
        	String postData = params[3];
        	if (HTTPMethod == "GET") {
        		if (connectMethod == "HTTPURLConnection"){
        			responseBody = getHTTPURLConnection(urlString);
        		}
        		if (connectMethod == "HTTPClient"){
        			responseBody = getHTTPClient(urlString);
        		}
        	}
        	if (HTTPMethod == "POST") {
        		if (connectMethod == "HTTPURLConnection"){
        			responseBody = postHTTPURLConnection(urlString, postData);
        		}
        		if (connectMethod == "HTTPClient"){
        			responseBody = postHTTPClient(urlString, postData);
        		}    		
        	}
            return null;
        }

        /**
         * 非同期処理の実行後に、UI スレッドで実行する処理。
         * 引数は {@link AsyncTask#execute(Object...)} の返り値。
         */
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            TextView text = (TextView)findViewById(R.id.response);
            text.setText(responseBody);
        }
    }

}
