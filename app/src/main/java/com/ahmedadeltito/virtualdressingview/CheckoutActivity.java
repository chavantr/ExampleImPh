package com.ahmedadeltito.virtualdressingview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CheckoutActivity extends AppCompatActivity {


    private ImageView imgSelectedImage;

    private NumberPicker npItems;
    private TextView lblItemsValue;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        imgSelectedImage = (ImageView) findViewById(R.id.imgImage);
        npItems = (NumberPicker) findViewById(R.id.npItems);
        lblItemsValue = (TextView) findViewById(R.id.lblInstanValue);
        btnNext = (Button) findViewById(R.id.btnEbookOrder);

        imgSelectedImage.setImageBitmap(PhotoEditorActivity.selectedImage);

        npItems.setMinValue(1);
        npItems.setMaxValue(9);


        npItems.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                if (oldVal != newVal) {

                    int value = newVal * 500;

                    String valueText = "Your amount is : " + value;

                    lblItemsValue.setText(valueText);

                }

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CheckoutActivity.this, ThankYouScreen.class);
                startActivity(intent);
            }
        });


    }


}
