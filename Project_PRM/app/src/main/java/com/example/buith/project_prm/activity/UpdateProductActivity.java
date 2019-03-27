package com.example.buith.project_prm.activity;

import android.content.ClipData;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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
import com.example.buith.project_prm.model.Address;
import com.example.buith.project_prm.model.AddressResponse;
import com.example.buith.project_prm.model.Image;
import com.example.buith.project_prm.model.Product;
import com.example.buith.project_prm.model.ProductResponse;
import com.example.buith.project_prm.model.ProductType;
import com.example.buith.project_prm.model.ProductTypeResponse;
import com.example.buith.project_prm.network.RetrofitInstance;
import com.example.buith.project_prm.service.ApiClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProductActivity extends AppCompatActivity {


    private EditText productName;
    private EditText productDescription;
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
        Intent intent = getIntent();
        Product p = (Product) intent.getSerializableExtra("product");

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

        productName.setText(p.getProductName());
        getProductType();
        getAddress();

        imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + getApplicationContext().getResources().getResourcePackageName(R.drawable.user_avatar)
                + '/' + getApplicationContext().getResources().getResourceTypeName(R.drawable.user_avatar)
                + '/' + getApplicationContext().getResources().getResourceEntryName(R.drawable.user_avatar));

        mArrayUri = new ArrayList<>(Arrays.asList(imageUri));

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter
        mAdapter = new MyRecyclerAdapter(this, mArrayUri);
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
            Product p = new Product();
            p.setProductName(this.productName.getText().toString());
            p.setDescription(this.productDescription.getText().toString());
            p.setTypeID(Integer.parseInt(String.valueOf(((ProductType) this.spProductType.getSelectedItem()).getTypeId())));
            p.setAddressID(Integer.parseInt(String.valueOf(((Address) this.spAddress.getSelectedItem()).getAddressID())));
            p.setDescription(this.productDescription.getText().toString());

            List<Image> listImg = new ArrayList<>();
            for (Uri item : mArrayUri) {
                listImg.add(new Image(0, item.toString()));
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
        return productName.length() == 0 || productDescription.length() == 0
                || mArrayUri.get(0).toString().equals(imageUri.toString());
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
}
