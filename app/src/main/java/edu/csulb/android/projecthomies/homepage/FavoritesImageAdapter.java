package edu.csulb.android.projecthomies.homepage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import edu.csulb.android.projecthomies.R;

public class FavoritesImageAdapter extends BaseAdapter {
    private Context mContext;

    public FavoritesImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(150, 150));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);

        } else {
            imageView = (ImageView) convertView;
        }

        Bitmap bm = BitmapFactory.decodeResource(mContext.getResources(), mThumbIds[position]);
        RoundImage roundedImage = new RoundImage(bm);
        imageView.setImageDrawable(roundedImage);
        return imageView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.fav_friend_5, R.drawable.fav_friend_1,
            R.drawable.fav_friend_2, R.drawable.fav_friend_3,
            R.drawable.fav_friend_4
    };
}