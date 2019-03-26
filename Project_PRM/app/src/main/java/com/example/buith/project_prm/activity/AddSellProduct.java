package com.example.buith.project_prm.activity;

import android.content.ClipData;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.buith.project_prm.R;
import com.example.buith.project_prm.adapter.MyRecyclerAdapter;
import com.example.buith.project_prm.constant.Constant;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddSellProduct extends AppCompatActivity {

    private EditText productName;
    private EditText productDescription;
    private EditText productPrice;
    private Spinner spProductType;
    private Spinner spAddress;
    private Button btnAdd;

    String imageEncoded;
    List<String> imagesEncodedList;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    Uri imageUri;
    ArrayList<Uri> mArrayUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sell_product);
//        Toolbar toolbar = findViewById(R.id.toolbar_add);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);

//        productName = findViewById(R.id.input_product_name);
//        productDescription = findViewById(R.id.input_product_description);
//        productPrice = findViewById(R.id.input_product_price);
//        spProductType = findViewById(R.id.spinnerProductType);
//        spAddress = findViewById(R.id.spinnerAddress);
//        btnAdd = findViewById(R.id.btnAdd);
//
//        //  assign type product to adapter
//        ArrayAdapter<CharSequence> adapterProType = ArrayAdapter.createFromResource(this,
//                R.array.typeProduct, android.R.layout.simple_spinner_item);
//        //  set list type to spinner
//        adapterProType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spProductType.setAdapter(adapterProType);
////        spProductType.setOnItemSelectedListener(this);
//
//        //  assign address to adapter
//        ArrayAdapter<CharSequence> adapterAdress = ArrayAdapter.createFromResource(this,
//                R.array.address, android.R.layout.simple_spinner_item);
//        //  set list address to spinner
//        adapterAdress.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spAddress.setAdapter(adapterAdress);
////        spAddress.setOnItemSelectedListener(this);
//
//        imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
//                "://" + getApplicationContext().getResources().getResourcePackageName(R.drawable.user_avatar)
//                + '/' + getApplicationContext().getResources().getResourceTypeName(R.drawable.user_avatar)
//                + '/' + getApplicationContext().getResources().getResourceEntryName(R.drawable.user_avatar));
//
//        mArrayUri = new ArrayList<>(Arrays.asList(imageUri));
//
//        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//
//        // use this setting to improve performance if you know that changes
//        // in content do not change the layout size of the RecyclerView
//        //recyclerView.setHasFixedSize(true);
//
//        // use a grid layout manager
//        layoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
//        recyclerView.setLayoutManager(layoutManager);
//
//        // specify an adapter
//        mAdapter = new MyRecyclerAdapter(this, mArrayUri);
//        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            // When an Image is picked
            if (requestCode == Constant.RESULT_LOAD_IMAGE && resultCode == RESULT_OK
                    && null != data) {

                // Get the Image from data
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                imagesEncodedList = new ArrayList<String>();
                if (data.getData() != null) {
                    Uri mImageUri = data.getData();

                    /*InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    selectedImage = resizedBitmap(selectedImage, 75);*/

                    // Get the cursor
                    Cursor cursor = getContentResolver().query(mImageUri,
                            filePathColumn, null, null, null);
                    // Move to first row
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    imageEncoded = cursor.getString(columnIndex);
                    cursor.close();

                    mArrayUri = new ArrayList<Uri>();
                    mArrayUri.add(mImageUri);

                    // specify an adapter
                    mAdapter = new MyRecyclerAdapter(this, mArrayUri);
                    recyclerView.setAdapter(mAdapter);
                } else {
                    if (data.getClipData() != null) {
                        ClipData mClipData = data.getClipData();
                        mArrayUri = new ArrayList<Uri>();
                        for (int i = 0; i < mClipData.getItemCount(); i++) {
                            ClipData.Item item = mClipData.getItemAt(i);
                            Uri uri = item.getUri();
                            mArrayUri.add(uri);
                        }
                        // specify an adapter
                        mAdapter = new MyRecyclerAdapter(this, mArrayUri);
                        recyclerView.setAdapter(mAdapter);
                    }
                }
            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void addProduct(View view) {
        if (!checkValidData()) {

        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Alert");
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.setMessage("Please fill full data!");
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

    public boolean checkValidData() {
        return productName.length() == 0 || productDescription.length() == 0
                || productPrice.length() == 0
                || mArrayUri.get(0).toString().equals(imageUri.toString());
    }

    public Bitmap resizedBitmap(Bitmap image, int maxSize) {
        return Bitmap.createScaledBitmap(image, maxSize, maxSize, true);
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
}
