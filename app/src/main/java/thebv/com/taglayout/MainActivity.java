package thebv.com.taglayout;

import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        LinearLayout llViewTag = (LinearLayout) findViewById(R.id.llViewTag);
        llViewTag.removeAllViews();
        final ArrayList<TagLayout.ItemTag> arr = new ArrayList<>();

        arr.add(getDefaultTagItem().setText("Bach Van the 1asdasdasdasdasdasd asda asd asd asd asd asd ada sd ad asdas as").setTextStyle(Typeface.BOLD));
        arr.add(getDefaultTagItem().setText("Bach Van the 2"));
        arr.add(getDefaultTagItem().setText("Bach the 3"));
        arr.add(getDefaultTagItem().setText("the 4"));
        arr.add(getDefaultTagItem().setText("Bach Van arr.add(\"Bach Van the\"); 5"));
        arr.add(getDefaultTagItem().setText("Bach the 6"));
        arr.add(getDefaultTagItem().setText("Bac the 7"));
        arr.add(getDefaultTagItem().setText("Bach the 8"));
        arr.add(getDefaultTagItem().setText("add Van the 9"));
        arr.add(getDefaultTagItem().setText("Bach Van the add 10"));
        arr.add(getDefaultTagItem().setText("Bach Van the add 11"));
        arr.add(getDefaultTagItem().setText("Bach Van the 12"));
        arr.add(getDefaultTagItem().setText("Bach 13"));

        final TagLayout tagLayout = new TagLayout(this);
        tagLayout.setListTag(arr);
        tagLayout.setOnTagItemClick(new TagLayout.OnTagItemClickListener() {
            @Override
            public void onClick(TextView textView, int position) {
                Log.d("thebv", "text = " + textView.getText() + ", " + position);
                Toast.makeText(MainActivity.this, "text = " + textView.getText() + ", " + position, Toast.LENGTH_SHORT).show();
            }
        });
        tagLayout.attackToView(llViewTag);


        final EditText et = (EditText) findViewById(R.id.et);
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                arr.get(0).setText(charSequence + "");
                tagLayout.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public TagLayout.ItemTag getDefaultTagItem() {
        int textSize = (int) getResources().getDimension(R.dimen.text_size_normal);
        int textMarrgin = (int) getResources().getDimension(R.dimen.text_marrgin);
        Rect margin = new Rect(textMarrgin, textMarrgin, textMarrgin, textMarrgin);
        return new TagLayout.ItemTag("", textSize, Color.WHITE, Typeface.NORMAL, R.drawable.bg_tag, margin, null);
    }
}
