package com.ahmedadeltito.virtualdressingview;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class EmojiFragment extends Fragment implements EmojiAdapter.OnEmojiClickListener,ImageAdapter.OnImageClickListener  {

    private ArrayList<String> emojiIds;
    private PhotoEditorActivity photoEditorActivity;
    RecyclerView emojiRecyclerView;
    private ArrayList<Bitmap> stickerBitmaps;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        photoEditorActivity = (PhotoEditorActivity) getActivity();

        String[] emojis = photoEditorActivity.getResources().getStringArray(R.array.photo_editor_emoji);

        TypedArray images = getResources().obtainTypedArray(R.array.photo_editor_photos);

        stickerBitmaps = new ArrayList<>();
        for (int i = 0; i < images.length(); i++) {
            stickerBitmaps.add(decodeSampledBitmapFromResource(photoEditorActivity.getResources(), images.getResourceId(new Random().nextInt(images.length()), -1), 120, 120));
        }

        emojiIds = new ArrayList<>();
        Collections.addAll(emojiIds, emojis);
    }

    public Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_photo_edit_emoji, container, false);

        emojiRecyclerView = (RecyclerView) rootView.findViewById(R.id.fragment_main_photo_edit_emoji_rv);
        emojiRecyclerView.setHasFixedSize(true);
        ImageAdapter adapter9 = new ImageAdapter(photoEditorActivity, stickerBitmaps);
        adapter9.setOnImageClickListener(this);
        emojiRecyclerView.setLayoutManager(new GridLayoutManager(photoEditorActivity, 4));
        //EmojiAdapter adapter = new EmojiAdapter(photoEditorActivity, emojiIds);
        //adapter.setOnEmojiClickListener(this);
        emojiRecyclerView.setAdapter(adapter9);

        return rootView;
    }

    @Override
    public void onImageClickListener(Bitmap image) {
        PhotoEditorActivity.selectedImage = image;
        photoEditorActivity.addImage(image);
    }

    @Override
    public void onEmojiClickListener(String emojiName) {
        photoEditorActivity.addEmoji(emojiName);
    }
}
