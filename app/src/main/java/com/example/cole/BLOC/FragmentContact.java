package com.example.cole.BLOC;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by Cole on 10/16/17.
 */

public class FragmentContact extends Fragment implements LinearLayout.OnClickListener{


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.contact_fragment, container, false);

        LinearLayout website= (LinearLayout) rootView.findViewById(R.id.linearLayout_website);
        //wire any widgets -- must use rootView.findViewById
        website.setOnClickListener(this);

        //get any other initial set up done
        //return the view that we inflated
        return rootView;
    }

    public void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View view) {
        openWebPage("http://vei-bloc.com");

    }


}
