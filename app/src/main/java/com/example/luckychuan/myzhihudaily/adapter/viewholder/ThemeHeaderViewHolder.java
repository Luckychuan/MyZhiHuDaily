package com.example.luckychuan.myzhihudaily.adapter.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.luckychuan.myzhihudaily.R;

/**
 * Created by Luckychuan on 2017/5/5.
 */
public class ThemeHeaderViewHolder extends BaseViewHolder<ThemeHeaderViewHolder.HeaderBean>{

    private ImageView imageView;
    private TextView textView;
    private Context context;

    public ThemeHeaderViewHolder(View itemView) {
        super(itemView);

        context = itemView.getContext();
        imageView = (ImageView) itemView.findViewById(R.id.background);
        textView = (TextView) itemView.findViewById(R.id.description);

    }

    @Override
    public void bindViewHolder(HeaderBean bean) {
        Glide.with(context)
                .load(bean.getBackgroundUrl())
                .placeholder(R.color.white)
                .error(R.color.white)
                .into(imageView);

        textView.setText(bean.getDescription());

    }


    public static class HeaderBean{

        private String backgroundUrl;
        private String description;

        public HeaderBean(String backgroundUrl, String description) {
            this.backgroundUrl = backgroundUrl;
            this.description = description;
        }

        public String getBackgroundUrl() {
            return backgroundUrl;
        }

        public void setBackgroundUrl(String backgroundUrl) {
            this.backgroundUrl = backgroundUrl;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

}
