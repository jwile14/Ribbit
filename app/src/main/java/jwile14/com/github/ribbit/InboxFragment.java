package jwile14.com.github.ribbit;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

/**
 * Created by wilejd on 6/15/2015.
 */
public class InboxFragment extends ListFragment{


    protected ProgressBar mProgressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_inbox, container, false);

        mProgressBar = (ProgressBar) rootView.findViewById(R.id.inboxFragmentProgressBar);
        mProgressBar.setVisibility(View.INVISIBLE);

        return rootView;
    }
}
