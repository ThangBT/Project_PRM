package com.example.buith.project_prm.activity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buith.project_prm.R;
import com.example.buith.project_prm.adapter.ImageAdapter;
import com.example.buith.project_prm.constant.Constant;
import com.example.buith.project_prm.model.Account;
import com.example.buith.project_prm.model.AccountResponse;
import com.example.buith.project_prm.model.FragmentCommunication;
import com.example.buith.project_prm.model.Image;
import com.example.buith.project_prm.model.ImageListResponse;
import com.example.buith.project_prm.model.OnImagesLoaded;
import com.example.buith.project_prm.model.Product;
import com.example.buith.project_prm.network.RetrofitInstance;
import com.example.buith.project_prm.service.ApiClient;
import com.example.buith.project_prm.utils.DateUtils;
import com.example.buith.project_prm.utils.ImageUtils;
import com.example.buith.project_prm.utils.StringUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SellProductActivity extends BaseActivity implements OnImagesLoaded {


    @BindView(R.id.product_name_detail)
    TextView productName;
    @BindView(R.id.product_price_detail)
    TextView productPrice;
    @BindView(R.id.product_date_created)
    TextView dateCreate;
    @BindView(R.id.profile_image)
    ImageView avatar;
    @BindView(R.id.seller_name)
    TextView sellerName;
    @BindView(R.id.seller_address)
    TextView sellerAddress;
    @BindView(R.id.seller_date_join)
    TextView selleDateJoin;
    @BindView(R.id.product_description_detail)
    TextView description;

    private Toolbar toolbar;
    private RecyclerView.LayoutManager layoutManager;
    private Account seller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_product);
        ButterKnife.bind(this);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        Intent intent = getIntent();
        if(intent != null) {
            Product p = (Product) intent.getSerializableExtra(Constant.Intent.PRODUCT_KEY);
            productName.setText(p.getProductName());
            productPrice.setText(StringUtils.formatMoney(p.getPrice()));
            dateCreate.setText(DateUtils.getTimeByFormat(p.getCreatedDate(), Constant.Pattern.FORMAT_DATE_TIME));
            description.setText(p.getDescription());
            loadSeller(this, p.getUserName());
            loadImage(this, p.getProductID());

        }
    }

    @OnClick(R.id.call_button)
    public void call(View view){
        String uri = "tel:"+ seller.getPhoneNumber().trim();
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(uri));
        startActivity(intent);
    }

    @OnClick(R.id.mess_button)
    public void mess(View view){
        Intent smsIntent = new Intent(Intent.ACTION_ANSWER);
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("address", seller.getPhoneNumber().trim());
        smsIntent.putExtra("sms_body","");
        startActivity(smsIntent);
    }

    public void loadImage(OnImagesLoaded onImagesLoaded, int productId){
        ApiClient apiClient = RetrofitInstance.getRetrofitInstance(this);
        Call<ImageListResponse> call = apiClient.getListImage(productId);
        if(isNetworkAvailable()){
            showLoading();
            call.enqueue(new Callback<ImageListResponse>() {
                @Override
                public void onResponse(Call<ImageListResponse> call, Response<ImageListResponse> response) {
                    hideLoading();
                    ImageListResponse imageListResponse = response.body();
                    if(imageListResponse != null){
                        if(imageListResponse.getStatus() == 1){
                            List<Image> list = imageListResponse.getList();
                            onImagesLoaded.onImageLoaded(list);
                        }else{
                            Toast.makeText(SellProductActivity.this, "status = 0", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(SellProductActivity.this, "response = null", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ImageListResponse> call, Throwable t) {
                    hideLoading();
                    Toast.makeText(SellProductActivity.this, Constant.Message.ERROR_LOGIN_RESPONSE_API, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void loadSeller(OnImagesLoaded onImagesLoaded, String username){
        ApiClient apiClient = RetrofitInstance.getRetrofitInstance(this);
        Call<AccountResponse> call = apiClient.getSeller(username);
        if(isNetworkAvailable()){
            showLoading();
            call.enqueue(new Callback<AccountResponse>() {
                @Override
                public void onResponse(Call<AccountResponse> call, Response<AccountResponse> response) {
                    hideLoading();
                    AccountResponse accountResponse = response.body();
                    if(accountResponse != null){
                        if(accountResponse.getStatus() == 1){
                            Account ac = accountResponse.getData();
                            onImagesLoaded.onAccountLoaded(ac);
                        }
                    }else{

                    }
                }

                @Override
                public void onFailure(Call<AccountResponse> call, Throwable t) {
                    hideLoading();
                    Toast.makeText(SellProductActivity.this, Constant.Message.ERROR_LOGIN_RESPONSE_API, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    FragmentCommunication communication = new FragmentCommunication() {
        @Override
        public void onClickImage(int position, Object args) {

        }
    };

    @Override
    public void onImageLoaded(List<Image> list) {
        RecyclerView recyclerView = findViewById(R.id.image_recycle_view);
        ImageAdapter adapter = new ImageAdapter(this,list);
        adapter.setOnItemClickListener(communication);
        layoutManager = new LinearLayoutManager(this);
        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayout.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onAccountLoaded(Account account) {
        this.seller = account;
        sellerName.setText(account.getFullname());
        avatar.setImageBitmap(ImageUtils.base64ToBitmap(account.getAvatar()));
        sellerAddress.setText(account.getAddress());
        selleDateJoin.setText(DateUtils.getTimeByFormat(account.getCreatedDate(), Constant.Pattern.FORMAT_DATE_TIME));
    }
}
