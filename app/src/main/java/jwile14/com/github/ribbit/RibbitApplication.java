package jwile14.com.github.ribbit;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by wilejd on 6/11/2015.
 */
public class RibbitApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "5f2lLMNIL4rH04fC4uSJ5fg5T9QB3CKWpkRRukuc", "FCmQOtIISjcnoe3S2Z0NooHjQrIIBJn8jHFYmY3y");
    }
}
