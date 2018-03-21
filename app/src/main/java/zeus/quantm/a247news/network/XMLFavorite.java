package zeus.quantm.a247news.network;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import zeus.quantm.a247news.models.New;

/**
 * Created by thean on 3/22/2018.
 */

public class XMLFavorite {
    Context context;
    InputStream inputStream;

    public XMLFavorite(Context context) {
        this.context = context;
    }

    public ArrayList<New> ReadXMLFavorite() {

        final String xmlFile = "list_favorite";

        try {

            inputStream = context.openFileInput(xmlFile);

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
                    if (name == null)
                        continue;
                    if (eventType == XmlPullParser.END_TAG) {
                        if (name.equalsIgnoreCase("item")) {
                            isItem = false;
                        }
                        continue;
                    }

                    if (eventType == XmlPullParser.START_TAG) {
                        if (name.equalsIgnoreCase("item")) {
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
                        if (!result.contains("rss")) {
                            link = result;
                        }

                    } else if (name.equalsIgnoreCase("description")) {
                        //  description = handleStringDescription(result);
                        description = result;

                    } else if (name.equalsIgnoreCase("image")) {
                        image = result;
                    } else if (name.equalsIgnoreCase("pubDate")) {
                        pubDate = result;
                    }

                    if (title != null && link != null && description != null && image != null && pubDate != null) {
                        if (isItem) {
                            New item = new New(title, link, description, pubDate, image);
                            items.add(item);
                        }

                        title = null;
                        link = null;
                        description = null;
                        pubDate = null;
                        image = null;
                        isItem = false;
                    }
                }

                return (ArrayList<New>) items;
            } catch (Exception e) {

            } finally {
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void WriteXMLFavorite(ArrayList<New> listNews) {
        final String xmlFile = "list_favorite";
        try {

            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES), "/" + xmlFile);
            Log.e("Path A", file.getAbsolutePath());
            // FileOutputStream fos = new  FileOutputStream(path,"/"+"list_favorite.xml");
            FileOutputStream fileos = context.openFileOutput(xmlFile, Context.MODE_PRIVATE);
            XmlSerializer xmlSerializer = Xml.newSerializer();
            StringWriter writer = new StringWriter();
            xmlSerializer.setOutput(writer);
            xmlSerializer.startDocument("UTF-8", true);
            xmlSerializer.startTag(null, "News");
            if (listNews != null) {
                for (New n : listNews
                        ) {
                    xmlSerializer.startTag(null, "item");
                    xmlSerializer.startTag(null, "title");
                    xmlSerializer.text(n.title);
                    xmlSerializer.endTag(null, "title");
                    xmlSerializer.startTag(null, "link");
                    xmlSerializer.text(n.link);
                    xmlSerializer.endTag(null, "link");
                    xmlSerializer.startTag(null, "description");
                    xmlSerializer.text(n.description);
                    xmlSerializer.endTag(null, "description");
                    xmlSerializer.startTag(null, "pubDate");
                    xmlSerializer.text(n.pubDate);
                    xmlSerializer.endTag(null, "pubDate");
                    xmlSerializer.startTag(null, "image");
                    xmlSerializer.text(n.image);
                    xmlSerializer.endTag(null, "image");
                    xmlSerializer.endTag(null, "item");
                }
            }
            xmlSerializer.endDocument();
            xmlSerializer.flush();
            String dataWrite = writer.toString();
            fileos.write(dataWrite.getBytes());
            fileos.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
