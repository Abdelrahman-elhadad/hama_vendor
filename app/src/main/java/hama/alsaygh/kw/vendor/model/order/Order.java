package hama.alsaygh.kw.vendor.model.order;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import hama.alsaygh.kw.vendor.model.address.Address;
import hama.alsaygh.kw.vendor.model.cart.CartItem;
import hama.alsaygh.kw.vendor.model.store.Store;
import hama.alsaygh.kw.vendor.model.user.User;


public class Order implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("order_number")
    private String order_number;

    @SerializedName("created_at")
    private String created_at;

    @SerializedName("currency")
    private String currency;

    @SerializedName("delivery_fees")
    private double delivery_fees;

    @SerializedName("gift_note")
    private String gift_note;

    @SerializedName("items_count")
    private int items_count;

    @SerializedName("status")
    private String status;

    @SerializedName("status_trans")
    private String status_trans;


    @SerializedName("delivery_type")
    private String delivery_type;

    @SerializedName("delivery_type_str")
    private String delivery_type_str;


    @SerializedName("receipt_at")
    private String receipt_at;

    @SerializedName("subtotal")
    private double subtotal;

    @SerializedName("title")
    private String title;

    @SerializedName("total")
    private String total;

    @SerializedName("voucher_pdf_link")
    private String voucher_pdf_link;

    @SerializedName("voucher_image_link")
    private String voucher_image_link;

    @SerializedName("delivery")
    private Address delivery;

    @SerializedName("items")
    private List<CartItem> items;

    @SerializedName("vendor_info")
    private List<Store> vendor_info;

    @SerializedName("packaging_store_info")
    private List<Store> packaging_store_info;

    @SerializedName(value = "user_info", alternate = "user")
    private User user_info;

    public String getDelivery_type() {
        return delivery_type;
    }

    public void setDelivery_type(String delivery_type) {
        this.delivery_type = delivery_type;
    }

    public String getDelivery_type_str() {
        return delivery_type_str;
    }

    public void setDelivery_type_str(String delivery_type_str) {
        this.delivery_type_str = delivery_type_str;
    }

    public String getReceipt_at() {
        return receipt_at;
    }

    public void setReceipt_at(String receipt_at) {
        this.receipt_at = receipt_at;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public List<Store> getVendor_info() {
        return vendor_info;
    }

    public void setVendor_info(List<Store> vendor_info) {
        this.vendor_info = vendor_info;
    }

    public List<Store> getPackaging_store_info() {
        if(packaging_store_info==null)
            packaging_store_info=new ArrayList<>();
        return packaging_store_info;
    }

    public void setPackaging_store_info(List<Store> packaging_store_info) {
        this.packaging_store_info = packaging_store_info;
    }

    public User getUser_info() {
        return user_info;
    }

    public void setUser_info(User user_info) {
        this.user_info = user_info;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }


   public Address getDelivery() {
        return delivery;
    }

    public void setDelivery(Address delivery) {
        this.delivery = delivery;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }


    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus_trans() {
        return status_trans;
    }

    public void setStatus_trans(String status_trans) {
        this.status_trans = status_trans;
    }

    public int getItems_count() {
        return items_count;
    }

    public void setItems_count(int items_count) {
        this.items_count = items_count;
    }

    public double getDelivery_fees() {
        return delivery_fees;
    }

    public void setDelivery_fees(double delivery_fees) {
        this.delivery_fees = delivery_fees;
    }

    public String getGift_note() {
        return gift_note;
    }

    public void setGift_note(String gift_note) {
        this.gift_note = gift_note;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateDate() {

//        if (created_at != null && !created_at.isEmpty()) {
//
//            String[] date = created_at.split(" ");
//            if (date.length > 0)
//                return date[0];
//            else
//                return "";
//        }
        return created_at;
    }

    public String getVoucher_pdf_link() {
        return voucher_pdf_link;
    }

    public void setVoucher_pdf_link(String voucher_pdf_link) {
        this.voucher_pdf_link = voucher_pdf_link;
    }

    public String getVoucher_image_link() {
        return voucher_image_link;
    }

    public void setVoucher_image_link(String voucher_image_link) {
        this.voucher_image_link = voucher_image_link;
    }
}
