package com.example.luckychuan.myzhihudaily.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.luckychuan.myzhihudaily.R;
import com.example.luckychuan.myzhihudaily.bean.StoryContent;
import com.example.luckychuan.myzhihudaily.presenter.GetStoryContentPresenter;
import com.example.luckychuan.myzhihudaily.view.StoryContentView;

/**
 * StoryDetailActivity中ViewPager的Fragment
 */

public class ContentFragment extends Fragment implements StoryContentView {

    private View mView;
    private GetStoryContentPresenter mPresenter;
    private OnLoadFinishListener mListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_layout,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mView = view;

        mPresenter = new GetStoryContentPresenter(this);
        mPresenter.attach(this);
        String  id = getArguments().getString("id");
        mPresenter.requestStoryContent(id);

    }

    public void setOnLoadFinshListener(OnLoadFinishListener listener){
        mListener = listener;
    }

    @Override
    public void updateUI(StoryContent content) {
        RelativeLayout relativeLayout = (RelativeLayout) mView.findViewById(R.id.head_layout);
        relativeLayout.setVisibility(View.VISIBLE);
        ImageView imageView = (ImageView) mView.findViewById(R.id.image_detail);
        TextView title = (TextView) mView.findViewById(R.id.title_detail);
        TextView source = (TextView) mView.findViewById(R.id.source_detail);

        Glide.with(this)
                .load(content.getImageUrl())
                .placeholder(R.color.white)
                .error(R.color.white)
                .into(imageView);

        title.setText(content.getTitle());
        source.setText(content.getImageSource());

        WebView webView = (WebView) mView.findViewById(R.id.web_view);

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

        //将StoryContent回调给Activity
        if(mListener != null){
            mListener.onLoadFinish(content);
        }

    }

    @Override
    public void showErrorMsg(String error) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detach();
    }

    public  interface OnLoadFinishListener{
        void onLoadFinish(StoryContent content);
    }

}
