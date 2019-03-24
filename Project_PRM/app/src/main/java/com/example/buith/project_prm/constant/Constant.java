package com.example.buith.project_prm.constant;

public class Constant {
    public static class KeySharedPreference{
        public static final String USER_LOGIN = "user_login";
        public static final String USER_KEY_LOGIN = "user_key_login";
        public static final String ACCESS_TOKEN = "access_token";
    }

    public static class API{
        public static final String LIST_PRODUCT_TYPE = "list_product_type";
    }

    public static class Message {
        public static final String USER_NAME_REQUIRE = "Tên đăng nhập không được để trống";
        public static final String FULL_NAME_REQUIRE = "Họ tên không được để trống";
        public static final String PASSWORD_REQUIRE = "Mật khẩu không được để trống";
        public static final String EMAIL_REQUIRE = "Email không được để trống";
        public static final String PHONE_REQUIRE = "Số điện thoại không được để trống";

        public static final String ERROR_REGISTER_STATUS_API = "Đăng ký thất bại";
        public static final String SUCCESS_REGISTER_STATUS_API = "Đăng ký thành công";
        public static final String ERROR_LOGIN_RESPONSE_API = "Đăng nhập thất bại! Kiểm tra tên đăng nhập và mật khẩu";

    }
    public static final int RESULT_LOAD_IMAGE = 1;
    public static final String PRODUCT_TYPE_ID = "product_type_id";
}
