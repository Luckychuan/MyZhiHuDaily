package com.example.luckychuan.myzhihudaily.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.luckychuan.myzhihudaily.R;
import com.example.luckychuan.myzhihudaily.bean.StoryContent;

/**
 * StoryDetailActivity中ViewPager的Fragment
 */

public class ContentFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_layout,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        StoryContent content = (StoryContent) getArguments().getSerializable("StoryContent");
        updateUI(view,content);
    }


    private void updateUI(View view,StoryContent content) {



        ImageView imageView = (ImageView) view.findViewById(R.id.image_detail);
        TextView title = (TextView) view.findViewById(R.id.title_detail);
        TextView source = (TextView) view.findViewById(R.id.source_detail);

        Glide.with(this)
                .load(content.getImageUrl())
                .placeholder(R.color.white)
                .error(R.color.white)
                .into(imageView);

        title.setText(content.getTitle());
        source.setText(content.getImageSource());

        WebView webView = (WebView) view.findViewById(R.id.web_view);

        //初始化WebView
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(false);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });

        //设置数据
        String formatCss = String.format("<link href=\"%s\" rel=\"stylesheet\" type=\"text/css\"/>", content.getCss().get(0));
        String formatHtml = content.getBody().replace("class=\"img-place-holder\"", "class=\"img-place-holder-ignored\"");
        webView.loadData(formatCss + formatHtml, "text/html; charset=UTF-8", null);

    }

}
