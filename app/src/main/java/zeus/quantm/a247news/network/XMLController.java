package zeus.quantm.a247news.network;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import zeus.quantm.a247news.models.New;

/**
 * Created by thean on 3/21/2018.
 */

public class XMLController extends AsyncTask<String,Void, ArrayList<New> > {

    ProgressDialog progressDialog;
    Context context;
    RecyclerView recyclerView;


    public XMLController(Context context, RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView = recyclerView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        // Hiển thị Dialog khi bắt đầu xử lý.
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Thể Thao 24h");
        progressDialog.setMessage("Dang xu ly...");
        progressDialog.show();
    }

    @Override
    protected ArrayList<New> doInBackground(String... strings) {

        List<New> mFeedModelList = null;
        String urlLink = strings[0];
        try {
            if(!urlLink.startsWith("http://") && !urlLink.startsWith("https://"))
                urlLink = "http://" + urlLink;



            URL url = new URL(urlLink);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                mFeedModelList = parseFeed(inputStream);
            }else {
                // display error message
            }


        } catch (IOException e) {
            Log.e("RssFeed", "Error", e);
        } catch (XmlPullParserException e) {
            Log.e("RssFeed", "Error", e);
        }

        return (ArrayList<New>) mFeedModelList;

    }


    @Override
    protected void onPostExecute(ArrayList<New> listItem) {
        super.onPostExecute(listItem);
        // Hủy dialog đi.
        progressDialog.dismiss();
        // Hiển thị IP lên TextView.

        if (listItem != null) {


            for (New item : listItem
                    ) {
                Log.e("Test Clone", item + "");
            }
            //recyclerView.setAdapter();
        }
        //textView.setText(aString);
    }

    public List<New> parseFeed(InputStream inputStream) throws XmlPullParserException,
            IOException {
        String title = null;
        String link = null;
        String description = null;
        String pubDate = null;
        String image = null;
        boolean isItem = false;
        List<New> items = new ArrayList<>();

        try {
            XmlPullParser xmlPullParser = Xml.newPullParser();
            xmlPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            xmlPullParser.setInput(inputStream, null);

            xmlPullParser.nextTag();
            while (xmlPullParser.next() != XmlPullParser.END_DOCUMENT) {
                int eventType = xmlPullParser.getEventType();

                String name = xmlPullParser.getName();
                if(name == null)
                    continue;
                if(eventType == XmlPullParser.END_TAG) {
                    if(name.equalsIgnoreCase("item")) {
                        isItem = false;
                    }
                    continue;
                }

                if (eventType == XmlPullParser.START_TAG) {
                    if(name.equalsIgnoreCase("item")) {
                        isItem = true;
                        continue;
                    }
                }

                Log.d("MyXmlParser", "Parsing name ==> " + name);
                String result = "";
                if (xmlPullParser.next() == XmlPullParser.TEXT) {
                    result = xmlPullParser.getText();
                    xmlPullParser.nextTag();
                }
                if (name.equalsIgnoreCase("title")) {
                    title = result;
                } else if (name.equalsIgnoreCase("link")) {
                    link = result;
                } else if (name.equalsIgnoreCase("description")) {
                    //  description = handleStringDescription(result);
                    description = handleStringDescription(result.toString());
                    image = hanleStringImage(result.toString());
                } else if (name.equalsIgnoreCase("pubDate")){
                    pubDate = result;
                }

                if (title != null && link != null && description != null && pubDate != null) {
                    if(isItem) {
                        New item = new New(title, link, description , pubDate, image);
                        items.add(item);
                    }

                    title = null;
                    link = null;
                    description = null;
                    pubDate = null;
                    isItem = false;
                }
            }

            return items;
        } finally {
            inputStream.close();
        }
    }

    private String handleStringDescription(String theStrDes){
        int startString;
        if(theStrDes.contains("</br>")){
            startString = theStrDes.lastIndexOf("</br>");

            return theStrDes.substring(startString+5);
        }
        return  theStrDes;
    }

    private String hanleStringImage(String theStrImage){
        int startString;
        int endString;
        if(theStrImage.contains("<img")){
            if(theStrImage.contains("data-original=")){
                startString = theStrImage.lastIndexOf("data-original=");

                endString = theStrImage.lastIndexOf(".jpg");
                return theStrImage.substring(startString+15 , endString+4);
            }else if(theStrImage.contains("src=")){
                startString = theStrImage.lastIndexOf("src=");

                endString = theStrImage.lastIndexOf(".jpg");

                return theStrImage.substring(startString+4 , endString+4);
            }
        }
        return theStrImage;
    }
}

