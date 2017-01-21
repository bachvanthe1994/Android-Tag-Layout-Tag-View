package ncb.vnpay.com.taglayout;

import android.app.Activity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by thebv on 27/12/2016.
 */

public class TagLayout {
    private Activity context;
    private OnTagItemClickListener onTagItemClickListener;
    private ArrayList<ItemTag> arrOldTag = new ArrayList<>();
    private ArrayList<ItemTag> arrTag = new ArrayList<>();
    private LinearLayout llViewTag;
    private HorizontalScrollView hsview;
    private LinearLayout llParent;

    public void notifyDataSetChanged() {
        attackToView(llViewTag);
    }

    private TagLayout() {

    }

    public TagLayout(Activity context) {
        this.context = context;
    }

    public void setListTag(ArrayList<ItemTag> arr) {
        this.arrTag = arr;
        for (ItemTag obj : arr) {
            arrOldTag.add(new ItemTag(obj.text, obj.textSize, obj.textColor, obj.textStyle, obj.backgroundRes, obj.textMarrgin, obj.textPadding));
        }
    }

    private boolean equalsTag(ItemTag a, ItemTag b) {
        if (a.text.compareTo(b.text) == 0
                && a.textSize == b.textSize
                && a.textColor == b.textColor
                && a.textStyle == b.textStyle
                && a.backgroundRes == b.backgroundRes
                && a.textMarrgin == b.textMarrgin
                && a.textPadding == b.textPadding)
            return true;
        else
            return false;
    }

    public void setOnTagItemClick(OnTagItemClickListener onTagItemClickListener) {
        this.onTagItemClickListener = onTagItemClickListener;
    }

    public void attackToView(LinearLayout llViewTag) {
        this.llViewTag = llViewTag;

        if (hsview == null) {
            hsview = new HorizontalScrollView(context);
            hsview.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            llParent = new LinearLayout(context);
            llParent.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            llParent.setOrientation(LinearLayout.VERTICAL);
            hsview.addView(llParent);
            llViewTag.addView(hsview);

            hsview.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    hsview.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    drawTag();
                }
            });
        } else {
            this.llParent.removeAllViews();
            drawTag();
        }
    }

    public void drawTag() {
        for (int i = 0; i < arrTag.size(); i++) {
            ItemTag obj = arrTag.get(i);
            if (llParent.getChildCount() == 0) {
                LinearLayout llLine = new LinearLayout(context);
                llLine.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                llLine.setGravity(Gravity.CENTER);
                llParent.addView(llLine);
            }

            LinearLayout llLine = (LinearLayout) llParent.getChildAt(llParent.getChildCount() - 1);

            TextView tvTag = new TextView(context);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            if (obj.textMarrgin != -1) {
                layoutParams.setMargins(obj.textMarrgin, obj.textMarrgin, obj.textMarrgin, obj.textMarrgin);
            }
            tvTag.setLayoutParams(layoutParams);

            if (obj.textPadding != -1) {
                tvTag.setPadding(obj.textPadding, obj.textPadding, obj.textPadding, obj.textPadding);
            }

            tvTag.setText(obj.text);

            tvTag.setTextColor(obj.textColor);

            if (obj.textSize != -1) {
                tvTag.setTextSize(TypedValue.COMPLEX_UNIT_PX, obj.textSize);
            }

            if (obj.textStyle != -1) {
                tvTag.setTypeface(tvTag.getTypeface(), obj.textStyle);
            }

            if (obj.backgroundRes != -1) {
                tvTag.setBackgroundResource(obj.backgroundRes);
            }

            final int finalI = i;
            tvTag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onTagItemClickListener != null)
                        onTagItemClickListener.onClick((TextView) view, finalI);
                }
            });

            llLine.addView(tvTag);
            llLine.measure(0, 0);

            if (llLine.getMeasuredWidth() > (hsview.getMeasuredWidth())) {
                if (llLine.getChildCount() == 1) {
                    llLine.setLayoutParams(new LinearLayout.LayoutParams(hsview.getMeasuredWidth(), ViewGroup.LayoutParams.WRAP_CONTENT));
                    layoutParams.weight = 1;
                    llLine.measure(0, 0);
                } else {
                    llLine.removeView(tvTag);
                    i--;
                }
                LinearLayout llNewLine = new LinearLayout(context);
                llNewLine.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                llLine.setGravity(Gravity.CENTER);
                llParent.addView(llNewLine);
            }
        }
    }

    public interface OnTagItemClickListener {
        void onClick(TextView textView, int position);
    }

    public static class ItemTag {
        private String text = "";
        private int textSize = -1;
        private int textColor = -1;
        private int textStyle = -1;
        private int backgroundRes = -1;
        private int textMarrgin = -1;
        private int textPadding = -1;

        public ItemTag(String text, int textSize, int textColor, int textStyle, int backgroundRes, int textMarrgin, int textPadding) {
            this.backgroundRes = backgroundRes;
            this.textColor = textColor;
            this.textStyle = textStyle;
            this.text = text;
            this.textSize = textSize;
            this.textMarrgin = textMarrgin;
            this.textPadding = textPadding;
        }

        public int getBackgroundRes() {
            return backgroundRes;
        }

        public ItemTag setBackgroundRes(int backgroundRes) {
            this.backgroundRes = backgroundRes;
            return this;
        }

        public String getText() {
            return text;
        }

        public ItemTag setText(String text) {
            this.text = text;
            return this;
        }

        public int getTextColor() {
            return textColor;
        }

        public ItemTag setTextColor(int textColor) {
            this.textColor = textColor;
            return this;
        }

        public int getTextMarrgin() {
            return textMarrgin;
        }

        public ItemTag setTextMarrgin(int textMarrgin) {
            this.textMarrgin = textMarrgin;
            return this;
        }

        public int getTextPadding() {
            return textPadding;
        }

        public ItemTag setTextPadding(int textPadding) {
            this.textPadding = textPadding;
            return this;
        }

        public int getTextSize() {
            return textSize;
        }

        public ItemTag setTextSize(int textSize) {
            this.textSize = textSize;
            return this;
        }

        public int getTextStyle() {
            return textStyle;
        }

        public ItemTag setTextStyle(int textStyle) {
            this.textStyle = textStyle;
            return this;
        }
    }
}
