package abe.appsfactory.nanodegree.bakingapp.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;

import abe.appsfactory.nanodegree.bakingapp.R;

public class ThumbNailHelper {
    private static Bitmap retrieveVideoFrameFromVideo(String videoPath) throws Throwable {
        Bitmap bitmap = null;
        MediaMetadataRetriever mediaMetadataRetriever = null;
        try {
            mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(videoPath, new HashMap<>());

            bitmap = mediaMetadataRetriever.getFrameAtTime();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Throwable("Exception in retrieveVideoFrameFromVideo(String videoPath)" + e.getMessage());

        } finally {
            if (mediaMetadataRetriever != null) {
                mediaMetadataRetriever.release();
            }
        }
        return bitmap;
    }

    public static void loadThumbnailFromUrlOrCache(final ImageView view, String url){
        new AsyncTask<Void, Void, File>() {
            Context context;
            @Override
            protected void onPreExecute() {
                view.setImageResource(R.drawable.item_recipe_card_placeholder);
                context = view.getContext();

                super.onPreExecute();
            }

            @Override
            protected File doInBackground(Void... params) {
                try {
                    context.getCacheDir();
                    File jpg = new File(context.getCacheDir(), url.hashCode() +".jpg");
                    if(!jpg.exists()){
                        Bitmap bitmap = ThumbNailHelper.retrieveVideoFrameFromVideo(url);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 75, new FileOutputStream(jpg));
                    }

                    return jpg;
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(File jpg) {
                if(jpg != null) {
                    Drawable d = Drawable.createFromPath(jpg.getAbsolutePath());
                    view.setImageDrawable(d);
                }
            }
        }.execute();
    }
}
