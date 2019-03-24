package com.example.buith.project_prm.model;

import java.util.List;

public interface OnProductLoaded {
    void onProductLoaded(List<Product> list);
    void onAddressLoaded(List<Address> list);
}
