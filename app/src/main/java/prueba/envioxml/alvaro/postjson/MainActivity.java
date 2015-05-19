package prueba.envioxml.alvaro.postjson;

import java.io.File;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.security.KeyStore;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;

import android.widget.ProgressBar;
import android.support.v7.app.ActionBarActivity;


public class MainActivity extends ActionBarActivity implements OnClickListener
{
    private EditText value, etid;
    private TextView etmd5, tv_files;
    private Button btn;
    private ProgressBar pb;
    private static final String LOGTAG = "LogsAndroid";
    private ImageView img, img2;
    String strBase64;
    String strBase642;
    String strBase643;
    String strBase644, strBaseVideo, path;
    int num_img = 0, itemCount;
    String s, recibido,nameItem, address, youFilePathVideo, youFilePath, youFilePath2, youFilePath3, youFilePath4;

    String responseBody;

    //First We Declare Titles And Icons For Our Navigation Drawer List View
    //This Icons And Titles Are holded in an Array as you can see


    @Override
    public void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        value = (EditText) findViewById(R.id.editText1);
        etid = (EditText) findViewById(R.id.etid);
        etmd5 = (TextView) findViewById(R.id.etmd5);
        tv_files = (TextView) findViewById(R.id.tv_files);
        btn = (Button) findViewById(R.id.button1);
        pb = (ProgressBar) findViewById(R.id.progressBar1);
        pb.setVisibility(View.GONE);
        btn.setOnClickListener(this);
        img = (ImageView) findViewById(R.id.img);
        img2 = (ImageView) findViewById(R.id.img2);

        //get id
        WifiManager manager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        address = info.getMacAddress();

        Log.i(LOGTAG, "dispositivito2 " + address);



    }

    public void onClick (View v)
    {
        // TODO Auto-generated method stub
        if (value.getText().toString().length() < 1)
        {
            //out of range
            Toast.makeText(this, "Please enter something", Toast.LENGTH_LONG).show();
        }
        else
        {
            pb.setVisibility(View.VISIBLE);
            new MyAsyncTask().execute(value.getText().toString());
        }
    }

    private class MyAsyncTask extends AsyncTask<String, Integer, Double>
    {
        @Override
        protected Double doInBackground (String... params)
        {
            //String datos = value.getText().toString();
            // Create a new HttpClient and Post Header
            HttpClient httpClient = getNewHttpClient();

            HttpPost httppost = new HttpPost("https://3isgestion.com/caronte/admin/scripts/recibirinfracciontabletPruebas/4c74032c271a/12");

            try
            {
                //base64 image
                //convertimos byteArray
                /*img.buildDrawingCache();
                Bitmap image2 = img.getDrawingCache();

                ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
                image2.compress(Bitmap.CompressFormat.PNG, 100, stream2);
                byte[] food2 = stream2.toByteArray();

                String str = new String(food2, "UTF-8");*/

                //pasamos byteArray to Base64 String
                youFilePath = Environment.getExternalStorageDirectory()
                        + "/proyectoCaronte/foto1.png";

               /* Bitmap selectedImage = BitmapFactory.decodeFile(youFilePath);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                selectedImage.compress(Bitmap.CompressFormat.JPEG, 85, stream);
                byte[] byteArray = stream.toByteArray();
                strBase64 = Base64.encodeToString(byteArray, 0);
                Log.i(LOGTAG, "pasamos b64 img1");*/

                youFilePath2 = Environment.getExternalStorageDirectory()
                        + "/proyectoCaronte/foto2.png";
                /*Bitmap selectedImage2 = BitmapFactory.decodeFile(youFilePath2);
                ByteArrayOutputStream strea = new ByteArrayOutputStream();
                selectedImage2.compress(Bitmap.CompressFormat.JPEG, 85, strea);
                byte[] byteArray2 = strea.toByteArray();
                strBase642 = Base64.encodeToString(byteArray2, 0);
                Log.i(LOGTAG, "pasamos b64 img2");*/

                youFilePath3 = Environment.getExternalStorageDirectory()
                        + "/proyectoCaronte/foto3.png";

                /*Bitmap selectedImage3 = BitmapFactory.decodeFile(youFilePath3);
                ByteArrayOutputStream stream3 = new ByteArrayOutputStream();
                selectedImage3.compress(Bitmap.CompressFormat.JPEG, 85, stream3);
                byte[] byteArray3 = stream3.toByteArray();
                strBase643 = Base64.encodeToString(byteArray3, 0);
                Log.i(LOGTAG, "pasamos b64 img3");
*/
                youFilePath4 = Environment.getExternalStorageDirectory()
                        + "/proyectoCaronte/foto4.png";

                //get Image4
                /*Bitmap selectedImage4 = BitmapFactory.decodeFile(youFilePath4);
                ByteArrayOutputStream stream4 = new ByteArrayOutputStream();
                selectedImage4.compress(Bitmap.CompressFormat.JPEG, 80, stream4);
                byte[] byteArray4 = stream4.toByteArray();
                strBase644 = Base64.encodeToString(byteArray4, 0);
                Log.i(LOGTAG, "pasamos b64 img4");*/

                //get Video
                youFilePathVideo = Environment.getExternalStorageDirectory()
                        + "/proyectoCaronte/video.mp4";

                //File file = new File(youFilePathVideo);
                //byte[] fileData = new byte[(int) file.length()];
                /*DataInputStream dis = new DataInputStream(new FileInputStream(file));
                dis.readFully(fileData);
                dis.close();*/

                //strBaseVideo=Base64.encodeToString(buffer, 0);

                /*File file = new File(youFilePathVideo);

                FileInputStream fis = new FileInputStream(file);
                //System.out.println(file.exists() + "!!");
                //InputStream in = resource.openStream();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] buf = new byte[1000000];
                try {
                    for (int readNum; (readNum = fis.read(buf)) != -1;) {
                        bos.write(buf, 0, readNum); //no doubt here is 0
                        //Writes len bytes from the specified byte array starting at offset off to this byte array output stream.
                    }
                } catch (IOException ex) {

                }
                byte[] bytes = bos.toByteArray();*/

                //below is the different part
                /*File someFile = new File("video2_"+youFilePathVideo);
                FileOutputStream fos = new FileOutputStream(someFile);
                fos.write(bytes);
                fos.flush();
                fos.close();*/

                //strBaseVideo=Base64.encodeToString(bytes, 0);

                /*JSONObject imagenes = new JSONObject();
                try
                {
                    imagenes.put("img1.png", strBase64);
                    imagenes.put("img2.png", strBase642);
                    imagenes.put("img3.png", strBase643);
                    imagenes.put("img4.png", strBase644);
                    imagenes.put("video.mp4", strBaseVideo);

                    Log.i(LOGTAG, "Guardamos Array imagenes y video");


                } catch (JSONException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                JSONArray jsonArray = new JSONArray();

                jsonArray.put(imagenes);*/

                /*JSONObject ImagenesObj = new JSONObject();

                try
                {
                    ImagenesObj.put("Imagenes", jsonArray);

                } catch (JSONException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }*/

                //String jsonStr = ImagenesObj.toString();
                Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+1:00"));
                Date currentLocalTime = cal.getTime();
                DateFormat date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                date.setTimeZone(TimeZone.getTimeZone("GMT+1:00"));
                String localTime = date.format(currentLocalTime);
                Log.i(LOGTAG, "folio " + localTime);

                // Add your data
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                //nameValuePairs.add(new BasicNameValuePair("imagenes", imagenes.toString()));
                nameValuePairs.add(new BasicNameValuePair("hora_infra", "15/02/2015 13:06:23"));
                nameValuePairs.add(new BasicNameValuePair("tipo_infra", "2"));
                nameValuePairs.add(new BasicNameValuePair("dni_infra", "76038672T"));
                nameValuePairs.add(new BasicNameValuePair("nombre_infra", "Antonio"));
                nameValuePairs.add(new BasicNameValuePair("apellido_infra", "García Pérez"));
                nameValuePairs.add(new BasicNameValuePair("sexo", "M"));
                nameValuePairs.add(new BasicNameValuePair("fecha_nacimiento", "15/02/1992"));
                nameValuePairs.add(new BasicNameValuePair("nacionalidad", "Española"));
                nameValuePairs.add(new BasicNameValuePair("validez_dni", "27/02/2019"));
                nameValuePairs.add(new BasicNameValuePair("provincia_infra", "Cáceres"));
                nameValuePairs.add(new BasicNameValuePair("matricula", "1478BBF"));
                nameValuePairs.add(new BasicNameValuePair("tipo_vehiculo", "1"));
                nameValuePairs.add(new BasicNameValuePair("marca", "Toyota"));
                nameValuePairs.add(new BasicNameValuePair("color", "Rojo"));
                nameValuePairs.add(new BasicNameValuePair("modelo", "Avensis"));
                nameValuePairs.add(new BasicNameValuePair("observaciones", "El usuario se nego a pagar la multa."));
                nameValuePairs.add(new BasicNameValuePair("fecha_envio", localTime));

                //UrlEncodedFormEntity uefe=new UrlEncodedFormEntity(nameValuePairs);

                //httppost.setEntity(uefe);

                //arrayList con los ficheros


                MultipartEntityBuilder builder = MultipartEntityBuilder.create();
                builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

                path = Environment.getExternalStorageDirectory().toString() + "/proyectoCaronte";
                File f = new File(path);
                File file[] = f.listFiles();
                ArrayList<File> files = new ArrayList<File>();
                ArrayList<File> fileordenado = new ArrayList<File>();

                for (int i=0; i<file.length; i++){
                    files.add(file[i]);
                }

                while(!files.isEmpty())
                {
                    File filemenor = files.get(0);
                    for (File archivo : files)
                    {
                        if( archivo.getName().compareTo(filemenor.getName()) < 0)
                        {
                            filemenor = archivo;
                        }
                    }
                    fileordenado.add(filemenor);
                    files.remove(filemenor);
                    Log.i(LOGTAG,"FileMenor" + filemenor);
                }

                Log.i(LOGTAG,"FileOrdenado" + fileordenado);

                for (int i = 0; i < file.length; i++)
                {

                    String nameItem = fileordenado.get(i).getName();

                    switch (nameItem){
                        case "foto1.png":
                            builder.addPart("imagenes[]", new FileBody(new File(youFilePath)));
                            break;
                        case "foto2.png":
                            builder.addPart("imagenes[]", new FileBody(new File(youFilePath2)));
                            break;
                        case "foto3.png":
                            builder.addPart("imagenes[]", new FileBody(new File(youFilePath3)));
                            break;
                        case "foto4.png":
                            builder.addPart("imagenes[]", new FileBody(new File(youFilePath4)));
                            break;
                        case "video.mp4":
                            builder.addPart("imagenes[]", new FileBody(new File(youFilePathVideo)));
                            break;

                        default:
                    }
                }

                builder.addTextBody("response", "prueba");

                HttpEntity entity = builder.build();

                httppost.setEntity(entity);

                // Execute HTTP Post Request
                HttpResponse response = httpClient.execute(httppost);
                responseBody = EntityUtils.toString(response.getEntity());

                try
                {
                    JSONObject jresponse = new JSONObject(responseBody);
                    recibido = jresponse.getString("1");
                    Log.i(LOGTAG, "folio " + recibido);

                /*public void onResponse(JSONArray response) {
                List<Contact> result = new ArrayList<Contact>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        result.add(convertContact(response
                                .getJSONObject(i)));
                    } catch (JSONException e) {
                    }
                }*/

                } catch (JSONException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                Log.i(LOGTAG, "pruebita");
                Log.i(LOGTAG, responseBody);

            } catch (ClientProtocolException e)
            {
                // TODO Auto-generated catch block
            } catch (IOException e)
            {
                Log.i(LOGTAG, "boligrafo" + e.getMessage());
            }
            return null;
        }

        protected void onPostExecute (Double result)
        {

            //arrayList con ficheros
            /*path = Environment.getExternalStorageDirectory().toString() + "/proyectoCaronte";
            File fileArray = new File(path);
            ArrayList<File> files = new ArrayList<File>(Arrays.asList(fileArray.listFiles()));

            itemCount = files.size();
            tv_files.setText("Valor: " + itemCount + "\n");*/

            File f = new File(path);
            File file[] = f.listFiles();

            for (int i = 0; i < file.length; i++)
            {
                nameItem = file[i].getName();
                etmd5.setText(etmd5.getText() + file[i].getName() + "\n");
            }

            etid.setText(responseBody);
            pb.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Command sent", Toast.LENGTH_LONG).show();

            //decode base64 string
            /*byte[] decodedString = Base64.decode(strBase64, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            img2.setImageBitmap(decodedByte);

            //decode base64 string
            byte[] decodedString1 = Base64.decode(strBase642, Base64.DEFAULT);
            Bitmap decodedByte1 = BitmapFactory.decodeByteArray(decodedString1, 0, decodedString1.length);
            img.setImageBitmap(decodedByte1);*/
        }

        protected void onProgressUpdate (Integer... progress)
        {
            pb.setProgress(progress[0]);
        }

        public HttpClient getNewHttpClient ()
        {
            try
            {
                KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
                trustStore.load(null, null);

                SSLSocketFactory sf = new MySSLSocketFactory(trustStore);
                sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

                HttpParams params = new BasicHttpParams();
                HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
                HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);

                SchemeRegistry registry = new SchemeRegistry();
                registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
                registry.register(new Scheme("https", sf, 443));

                ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);

                return new DefaultHttpClient(ccm, params);
            } catch (Exception e)
            {
                return new DefaultHttpClient();
            }
        }
    }


}