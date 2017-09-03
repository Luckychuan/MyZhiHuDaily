package com.example.luckychuan.myzhihudaily.adapter.viewholder;

import android.content.Context;
import android.text.Html;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.luckychuan.myzhihudaily.R;
import com.example.luckychuan.myzhihudaily.bean.Comment;
import com.example.luckychuan.myzhihudaily.widget.GlideCircleTransform;

import static com.example.luckychuan.myzhihudaily.R.id.comment_avatar;

/**
 * Created by Luckychuan on 2017/9/3.
 */

public class CommentViewHolder extends BaseViewHolder<Comment> {

    private Context context;
    private TextView mAuthor;
    private TextView mLike;
    private TextView mContent;
    private TextView mReplyTo;
    private TextView mTime;
    private ImageView mAvatar;
    private Button mButton;


    public CommentViewHolder(View itemView) {
        super(itemView);

        context = itemView.getContext();
        mAuthor = (TextView) itemView.findViewById(R.id.comment_author);
        mLike = (TextView) itemView.findViewById(R.id.comment_like);
        mContent = (TextView) itemView.findViewById(R.id.comment_content);
        mReplyTo = (TextView) itemView.findViewById(R.id.comment_replyTo);
        mTime = (TextView) itemView.findViewById(R.id.comment_time);
        mAvatar = (ImageView) itemView.findViewById(comment_avatar);

        //设置作者名字加粗
        TextPaint paint = mAuthor.getPaint();
        paint.setFakeBoldText(true);

        mButton = (Button) itemView.findViewById(R.id.comment_showMore);
        mButton.setVisibility(View.INVISIBLE);


    }


    @Override
    public void bindViewHolder(Comment bean) {
        mAuthor.setText(bean.getAuthor());
        mLike.setText(bean.getLikes() + "");
        mContent.setText(bean.getContent());
        if (bean.getReplyTo() != null) {
            mReplyTo.setText(Html.fromHtml("<b>//" + bean.getReplyTo().getAuthor() + "：</b>" + bean.getReplyTo().getContent()));

            mReplyTo.post(new Runnable() {
                @Override
                public void run() {
                    if(mReplyTo.getLineCount() >2){
                        mButton.setVisibility(View.VISIBLE);
                        
                    }else{
                        mButton.setVisibility(View.INVISIBLE);
                    }
                }
            });

            if(bean.getReplyTo().getAuthor() == null){
                mReplyTo.setText("抱歉！原点评已被删除");
                mButton.setVisibility(View.INVISIBLE);
            }
        }
        //// TODO: 2017/9/3 format
        mTime.setText(bean.getTime() + "");

        Glide.with(context)
                .load(bean.getAvatar())
                .placeholder(R.color.white)
                .error(R.color.white)
                .transform(new GlideCircleTransform(context))
                .into(mAvatar);

    }
}
