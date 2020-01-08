package com.firstpage.user.Common;



import java.util.ArrayList;
import java.util.List;

/**
 * Created by senthil on 23-Feb-18.
 */

public class CONST {
    public static final String HELP_URL = "http://demo.eatzilla.info/help.html";
    public static final String HASHMAP_VALUES = "hashmap_values";
    public static final String CURRENT_ADDRESS = "current_address";
    public static final String CURRENT_LATITUDE = "lat";
    public static final String CURRENT_LONGITUDE = "lng";
    public static final String REQUEST_ID = "request_id";
    public static final int CATEGORY = 0;
    public static final int FOOD_ITEMS = 1;
    public static final int RESTAURANT_ITEMS = 2;
    public static final String EXPANDED = "EXPANDED";
    public static final String COLLAPSED = "COLLAPSED";
    public static final String IDLE = "IDLE";
    public static final String SEARCHED_RESTAURANT = "searched_restaurant";
    public static final String SEARCHED_FOOD = "searched_food";
    public static final String SEARCHED_TYPE = "searched_type";
    public static final String RESTAURANT = "restaurant";
    public static final String FOOD = "food";
    public static final String PAST_ORDER_DETAIL = "past_order_detail";
    public static final String PAST_ORDER_ITEMS = "past_order_items";
    public static final String VEG = "1";
    public static final String PAYMENT_REQUEST = "payment_request";
    public static final String ADDON_DATA = "add_onData";
    public static final String DIFFERENT_ADDON_GROUP = "different_addon";
    public static final String DIFFERENT_RESTAURANT = "different_restaurant";
    public static final String REDUCE_DATA = "reduce_data";
    public static final String ADD_DATA = "add_data";
    public static final String N0_ADDON = "No Addons";
    public static final String NO_QUANTITY_ID = "0";
    public static final String QUANTITY_DATA = "quantity_data";

    public static final int EMIRATE_ID = 1;
    public static final int RESTAURANT_LICENSE = 2;
    public static final int OWNER_ID = 3;
    public static final int ADDRESS_CODE = 2023;


    //    http://18.217.220.83/superior-cleaning-app-web/api/check_postal_code/{postal_code}
    public static String IMAGE_BASE_URL = "http://68.183.232.66/eatzilla/public/uploads/";

//    public static String BASE_URL = "http://54.218.62.130/eatzilla/api/";
    public static String BASE_URL = "http://3.13.34.41:8080/api/";
    public static String Websocket_URL=  "ws://3.13.34.41:8081";
    public static String websocket_provider_url="ws://3.13.34.41:8080/api/v1/provider/order";


    public static String APIKEY = "AIzaSyAXzee2mJ89gYCXy4jNYCm3GWEFFS7DJHY";
    public static String GEOFIRE = "https://foodieuser-212211.firebaseio.com/";
    public static String FIREBASE_URL = "https://foodieuser-212211.firebaseio.com/";
    public static String STRIPE_KEY = "pk_test_SX2qwU595p4aBsbX4uCAmSj7";

    public static String currentAddress = "";

//    public static List<FilterPojo.Data> filterPojo = new ArrayList<>();
//    public static List<FilterPojo.Data> relevancePojo = new ArrayList<>();
    public static String popularBaseStr = "";
    public static String pureVegStr = "0";
    public static String offerStr = "0";
    public static String restaren_id = "0";
    public static String request_id_str = "0";
    public static String status_value_str = "-1";

    public static final String ORDER_CREATED = "0";
    public static final String RESTAURANT_ACCEPTED = "1";
    public static final String FOOD_PREPARED = "2";
    public static final String DELIVERY_REQUEST_ACCEPTED = "3";
    public static final String REACHED_RESTAURANT = "4";
    public static final String FOOD_COLLECTED_ONWAY = "5";
    public static final String FOOD_DELIVERED = "6";
    public static final String ORDER_COMPLETE = "7";
    public static final String ORDER_CANCELLED = "10";
    public static final String NO_ORDER = "-1";

    public static final String ORDER_UNAVAIL = "NA";
    public static int WIFI_ON = 3;

    public static class Params {
        public static String restaurant_id = "restaurant_id";
        public static String veg_only = "veg_only";
        public static String food_id = "food_id";
        public static String quantity = "quantity";
        public static String category_id = "category_id";
        public static String force_insert = "force_insert";
        public static String coupon_code = "coupon_code";
        public static String payment_str = "Amount";

        public static String item_total = "item_total";
        public static String offer_discount = "offer_discount";
        public static String restaurant_packaging_charge = "restaurant_packaging_charge";
        public static String gst = "gst";
        public static String delivery_charge = "delivery_charge";
        public static String bill_amount = "bill_amount";
        public static String paid_type = "paid_type";

        public static String phone = "phone";
        public static String login_type = "login_type";
        public static String device_token = "device_token";
        public static String password = "password";
        public static String email = "email";

        //Firebase
        public static String address = "address";
        public static String current_address = "current_address";
        public static String lng = "lng";
        public static String lat = "lat";
        public static String type = "type";
        public static String new_user_request = "new_user_request";
        public static String current_request = "current_request";
        public static String request_id = "request_id";
        public static String status = "status";

        public static String LatLng = "latLng";
        public static String longitude = "longitude";
        public static String latitude = "latitude";
        public static String prov_location = "prov_location";
        public static String device_type = "device_type";
        public static String landmark = "landmark";
        public static String flat_no = "flat_no";
        public static String is_veg = "is_veg";
        public static String id = "id";
        public static String name = "name";
        public static String profile_image = "profile_image";
        public static String promocode = "promocode";
        public static String referal_code = "referal_code";

        public static String first_name = "first_name";
        public static String last_name = "last_name";
        public static String business_type = "business_type";
        public static String business_name = "business_name";
        public static String company_name = "company_name";
        public static String emirates_id = "emirates_id";
        public static String trade_license = "trade_license";
        public static String trade_license_no = "trade_license_no";
        public static String trade_license_exp_date = "trade_license_exp_date";
        public static String vat_registered_no = "vat_registered_no";
        public static String restuarant_license_exp_date = "restuarant_license_exp_date";
        public static String restaurant_license = "restaurant_license";
    }

    public static class Keywords {

        //these keywords used in login page to make different visibility of linear inside card
        public static final String sign_in = "signIn";//To display Signin page
        public static final String sign_up = "signUp";//To display Register page
        public static final String verification = "verification";//To show otp verification
        public static final String forgot_pswd = "forgotPswd";// To show forgot password geting phone number screen
        public static final String new_pswd = "newPswd";//To show reset password page


    }
}
