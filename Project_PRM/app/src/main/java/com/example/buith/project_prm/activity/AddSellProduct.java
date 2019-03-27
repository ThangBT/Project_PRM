package com.example.buith.project_prm.activity;

import android.content.ClipData;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.util.Base64;
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
import com.example.buith.project_prm.model.Account;
import com.example.buith.project_prm.model.Address;
import com.example.buith.project_prm.model.AddressResponse;
import com.example.buith.project_prm.model.Image;
import com.example.buith.project_prm.model.Product;
import com.example.buith.project_prm.model.ProductResponse;
import com.example.buith.project_prm.model.ProductType;
import com.example.buith.project_prm.model.ProductTypeResponse;
import com.example.buith.project_prm.network.RetrofitInstance;
import com.example.buith.project_prm.service.ApiClient;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSellProduct extends AppCompatActivity {

    private final String img = "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";

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

    private List<ProductType> listType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sell_product);
        Toolbar toolbar = findViewById(R.id.toolbar_add);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        productName = findViewById(R.id.input_product_name);
        productDescription = findViewById(R.id.input_product_description);
        spProductType = findViewById(R.id.spinnerProductType);
        spAddress = findViewById(R.id.spinnerAddress);
        btnAdd = findViewById(R.id.btnAdd);
        productPrice = findViewById(R.id.input_product_price);
        getProductType();
        getAddress();

//        imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
//                "://" + getApplicationContext().getResources().getResourcePackageName(R.drawable.user_avatar)
//                + '/' + getApplicationContext().getResources().getResourceTypeName(R.drawable.user_avatar)
//                + '/' + getApplicationContext().getResources().getResourceEntryName(R.drawable.user_avatar));
//
//        mArrayUri = new ArrayList<>(Arrays.asList(imageUri));

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        //recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<String> listImg = new ArrayList<>();
        listImg.add(img);
        // specify an adapter
        mAdapter = new MyRecyclerAdapter(this, listImg);
        recyclerView.setAdapter(mAdapter);
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

                    mArrayUri = new ArrayList<Uri>();
                    mArrayUri.add(mImageUri);

                    // specify an adapter
                    ArrayList<String> listImg = new ArrayList<>();
                    InputStream imageStream;
                    imageStream = getContentResolver().openInputStream(mImageUri);
                    Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    String encodedImage = encodeImage(selectedImage);
                    listImg.add(encodedImage);
                    mAdapter = new MyRecyclerAdapter(this, listImg);
                    recyclerView.setAdapter(mAdapter);
                } else {
                    if (data.getClipData() != null) {
                        ClipData mClipData = data.getClipData();
                        mArrayUri = new ArrayList<Uri>();
                        ArrayList<String> listImg = new ArrayList<>();
                        for (int i = 0; i < mClipData.getItemCount(); i++) {
                            ClipData.Item item = mClipData.getItemAt(i);
                            Uri uri = item.getUri();
                            mArrayUri.add(uri);

                            InputStream imageStream;
                            imageStream = getContentResolver().openInputStream(uri);
                            Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                            String encodedImage = encodeImage(selectedImage);
                            listImg.add(encodedImage);

                        }
                        // specify an adapter
                        mAdapter = new MyRecyclerAdapter(this, listImg);
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
            Product p = new Product();
            p.setProductName(this.productName.getText().toString());
            p.setDescription(this.productDescription.getText().toString());
            p.setTypeID(Integer.parseInt(String.valueOf(((ProductType) this.spProductType.getSelectedItem()).getTypeId())));
            p.setAddressID(Integer.parseInt(String.valueOf(((Address) this.spAddress.getSelectedItem()).getAddressID())));
            p.setDescription(this.productDescription.getText().toString());
            String priceStr = productPrice.getText().toString();
            p.setPrice(Long.parseLong(priceStr));
            ArrayList<Image> listImg = new ArrayList<>();
            for (Uri item : mArrayUri) {
                try {
                    InputStream imageStream;
                    imageStream = getContentResolver().openInputStream(item);
                    Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    String encodedImage = encodeImage(selectedImage);
                    listImg.add(new Image(0, encodedImage));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            p.setImages(listImg);
            p.setPrice((long)150000);
            ApiClient apiClient = RetrofitInstance.getRetrofitInstance(getApplicationContext());

            SharedPreferences pref = getSharedPreferences(Constant.KeySharedPreference.USER_LOGIN, MODE_PRIVATE);
            String token = pref.getString(Constant.KeySharedPreference.ACCESS_TOKEN, null);
            Call<ProductResponse> call = apiClient.addProduct(p, "bearer " + token);
            call.enqueue(new Callback<ProductResponse>() {
                @Override
                public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                    ProductResponse response1 = response.body();
                    if (response1 == null) {
                        Toast.makeText(getApplicationContext(), "Xảy ra lỗi khi thêm mới sản phẩm", Toast.LENGTH_SHORT).show();
                    } else {
                        if (response1.getStatus() == 1) {
                            Toast.makeText(getApplicationContext(), "Thêm mới sản phẩm thành công", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Xảy ra lỗi khi thêm mới sản phẩm", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ProductResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                }
            });
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
        String priceStr = productPrice.getText().toString();
        try {
            long price = Long.parseLong(priceStr);
        } catch ( Exception e){
            return true;
        }
        return productName.length() == 0 || productDescription.length() == 0
                || productPrice.length() == 0
                || mArrayUri.get(0).toString().equals(imageUri.toString()) || productPrice.length() == 0;
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

    public void getProductType() {
        ApiClient apiClient = RetrofitInstance.getRetrofitInstance(getApplicationContext());
        Call<ProductTypeResponse> call = apiClient.getProductTypes();
        call.enqueue(new Callback<ProductTypeResponse>() {
            @Override
            public void onResponse(Call<ProductTypeResponse> call, Response<ProductTypeResponse> response) {
                ProductTypeResponse response1 = response.body();
                if (response1 == null) {
                    Toast.makeText(getApplicationContext(), "Không load được dữ liệu loại sản phẩm", Toast.LENGTH_SHORT).show();
                } else {
                    List<ProductType> list = response1.getListProductType();
                    loadType(list);
                }
            }

            @Override
            public void onFailure(Call<ProductTypeResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void loadType(List<ProductType> list) {
        ArrayAdapter<ProductType> adapter =
                new ArrayAdapter<ProductType>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spProductType.setAdapter(adapter);
        spProductType.setSelection(0);
    }

    public void getAddress() {
        ApiClient apiClient = RetrofitInstance.getRetrofitInstance(getApplicationContext());
        Call<AddressResponse> call = apiClient.getAllAddress();
        call.enqueue(new Callback<AddressResponse>() {
            @Override
            public void onResponse(Call<AddressResponse> call, Response<AddressResponse> response) {
                AddressResponse response1 = response.body();
                if (response1 == null) {
                    Toast.makeText(getApplicationContext(), "Không load được dữ liệu loại địa chỉ", Toast.LENGTH_SHORT).show();
                } else {
                    List<Address> list = response1.getList();
                    loadAddress(list);
                }
            }

            @Override
            public void onFailure(Call<AddressResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void loadAddress(List<Address> list) {
        ArrayAdapter<Address> adapter =
                new ArrayAdapter<Address>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spAddress.setAdapter(adapter);
        spAddress.setSelection(0);
    }

    private String encodeImage(Bitmap bm)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG,100,baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);
        return encImage;
    }
}
