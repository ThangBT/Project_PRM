package com.example.buith.project_prm.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.buith.project_prm.R;
import com.example.buith.project_prm.adapter.ProductListAdapter;
import com.example.buith.project_prm.constant.Constant;
import com.example.buith.project_prm.model.Address;
import com.example.buith.project_prm.model.AddressResponse;
import com.example.buith.project_prm.model.FragmentCommunication;
import com.example.buith.project_prm.model.OnProductLoaded;
import com.example.buith.project_prm.model.Product;
import com.example.buith.project_prm.model.ProductResponse;
import com.example.buith.project_prm.network.RetrofitInstance;
import com.example.buith.project_prm.service.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsFragment extends BaseFragment implements OnProductLoaded {

    private View view;

    private HomeActivity home;

    private Spinner addressDrop ;

    RecyclerView.LayoutManager layoutManager;

    public ProductsFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        home = (HomeActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.products_fragment, container, false);

        int typeId = getArguments().getInt(Constant.PRODUCT_TYPE_ID, 0);
        loadProduct(this, typeId);
        loadAddress(this);
        // set action for back arrow
        ImageView imageView = view.findViewById(R.id.back_arrow);
        imageView.setOnClickListener(v -> {
            HomeActivity homeActivity = (HomeActivity) getActivity();
            if (homeActivity != null) {
                homeActivity.replaceFragment(new HomeContainerFragment(), null);
            }
        });

        return view;
    }

    public void loadAddress(OnProductLoaded onProductLoaded){
        ApiClient apiClient = RetrofitInstance.getRetrofitInstance(view.getContext());
        Call<AddressResponse> call = apiClient.getAllAddress();
        if(home.isNetworkAvailable()){
            home.showLoading();
            call.enqueue(new Callback<AddressResponse>() {
                @Override
                public void onResponse(Call<AddressResponse> call, Response<AddressResponse> response) {
                    home.hideLoading();
                    AddressResponse res = response.body();
                    if(res != null){
                        if(res.getStatus() == 1){
                            onAddressLoaded(res.getList());
                        }
                    }else{

                    }

                }

                @Override
                public void onFailure(Call<AddressResponse> call, Throwable t) {
                    home.hideLoading();
                }
            });
        }
    }

    public void loadProduct(OnProductLoaded onProductLoaded ,int typeId){
        ApiClient apiClient = RetrofitInstance.getRetrofitInstance(view.getContext());
        Call<ProductResponse> call = apiClient.getProductInType(typeId);
        if(home.isNetworkAvailable()){
            home.showLoading();
            call.enqueue(new Callback<ProductResponse>() {
                @Override
                public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                    home.hideLoading();
                    ProductResponse res = response.body();
                   // Toast.makeText(view.getContext(), response.code(), Toast.LENGTH_SHORT).show();
                    if(res.getStatus() == 1){
                        List<Product> list = res.getProductList();
                        onProductLoaded(list);
                    }
                }

                @Override
                public void onFailure(Call<ProductResponse> call, Throwable t) {
                    home.hideLoading();
                    Toast.makeText(view.getContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    FragmentCommunication communication = new FragmentCommunication() {
        @Override
        public void onClickImage(int position, Object args) {
            HomeActivity homeActivity = (HomeActivity) getActivity();
            homeActivity.moveToActivity((Product) args);
        }
    };

    @Override
    public void onProductLoaded(List<Product> list) {
        if(list.isEmpty()){
            list = new ArrayList<>();
        }
        RecyclerView recyclerView = view.findViewById(R.id.recycle_list_product);
        ProductListAdapter adapter = new ProductListAdapter(this.getContext(), list);
        adapter.setOnItemClickListener(communication);
        layoutManager = new LinearLayoutManager(this.getContext());
        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayout.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onAddressLoaded(List<Address> list) {
        addressDrop = view.findViewById(R.id.spinner_city);
        ArrayAdapter<Address> adapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        addressDrop.setAdapter(adapter);
//        addressDrop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });
    }


    @Override
    public boolean onBackPressed() {
        return false;
    }
}
