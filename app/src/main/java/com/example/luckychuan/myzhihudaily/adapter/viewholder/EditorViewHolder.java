package com.example.luckychuan.myzhihudaily.adapter.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.luckychuan.myzhihudaily.widget.GlideCircleTransform;
import com.example.luckychuan.myzhihudaily.R;
import com.example.luckychuan.myzhihudaily.bean.Editor;

import java.util.List;

/**
 * Created by Luckychuan on 2017/5/7.
 */
public class EditorViewHolder extends BaseViewHolder<List<Editor>> {

    private Context context;
    private LinearLayout editorLayout;

    public EditorViewHolder(View itemView) {
        super(itemView);
        editorLayout = (LinearLayout) itemView.findViewById(R.id.editor_layout);
        context = itemView.getContext();
    }

    @Override
    public void bindViewHolder(List<Editor> bean) {

        editorLayout.removeAllViews();
        for (Editor editor : bean) {
            View view = LayoutInflater.from(context).inflate(R.layout.editor_item,editorLayout,false);
            ImageView imageView = (ImageView) view.findViewById(R.id.avatar);
            Glide.with(context)
                    .load(editor.getAvatar())
                    .placeholder(R.color.white)
                    .error(R.color.white)
                    .transform(new GlideCircleTransform(context))
                    .into(imageView);
            editorLayout.addView(view);
        }
    }

    @Override
    public void bindViewHolder(List<Editor> bean, int position) {

    }


}
