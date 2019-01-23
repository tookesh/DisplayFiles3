package com.dissert.cs.displayfiles;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyCustomAdapter extends BaseAdapter {

    private Context mContext;
    private List<FileDetails> mFileDetails;

    public MyCustomAdapter(Context context, List<FileDetails> fileDetails) {
        mContext = context;
        mFileDetails = fileDetails;
    }

    @Override
    public int getCount() {
        return mFileDetails.size();
    }

    @Override
    public Object getItem(int position) {
        return mFileDetails.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View myView = View.inflate(mContext, R.layout.custom_file_view, null);
        TextView txtName = myView.findViewById(R.id.txtName);
        TextView txtPath = myView.findViewById(R.id.txtPath);
        ImageView myImageView = myView.findViewById(R.id.myImageView);

        txtName.setText(mFileDetails.get(position).getName());
//        txtPath.setText(mFileDetails.get(position).getFilePath());

        //myView.setTag(mFileDetails.get(position).getName());

        return myView;
    }
}
