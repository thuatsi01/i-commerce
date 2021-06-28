package com.icommerceapis.common;

public class Routes {

    public static final String HEATH_CHECK = "/heath-check";

    public static class User {

        public static final String BASE_URL = "/user";

        public static final String LOGIN = "/login";
    }

    public static class Product {

        public static final String BASE_URL = "/product";

        public static final String LIST = "/list";
    }

    public static class ShoppingCart {
        public static final String BASE_URL = "/shopping-cart";

        public static final String SAVE = "/save";

        public static final String LIST = "/list";
    }
}
