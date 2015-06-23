package chileayuda.personfinder.service;

/**
 * Created by teo on 22/06/15.
 */
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.tools.remoteapi.RemoteApiInstaller;
import com.google.appengine.tools.remoteapi.RemoteApiOptions;
import java.io.IOException;

public class RemoteApiExample {
    public static void main(String[] args) throws IOException {
        String username = "antonyepiss@gmail.com";
        String password = "19820529##";
        RemoteApiOptions options = new RemoteApiOptions()
                .server("<i>agildcc</i>.appspot.com", 443)
                .credentials(username, password);
        RemoteApiInstaller installer = new RemoteApiInstaller();
        installer.install(options);
        try {
            DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
            System.out.println("Key of new entity is " +
                    ds.put(new Entity("Hello Remote API!")));
        } finally {
            installer.uninstall();
        }
    }
}