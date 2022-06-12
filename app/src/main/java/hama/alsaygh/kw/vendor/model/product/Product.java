package hama.alsaygh.kw.vendor.model.product;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import hama.alsaygh.kw.vendor.model.product.caliber.Caliber;
import hama.alsaygh.kw.vendor.model.product.review.Review;


public class Product implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("quantity")
    private int quantity;

    @SerializedName("bind_to_market")
    private boolean bind_to_market;

    @SerializedName("i_fav")
    private boolean i_fav;

    @SerializedName("fixed_price")
    private double fixed_price;

    @SerializedName("price")
    private double price;

    @SerializedName("weight")
    private String weight;

    @SerializedName("rate")
    private float rate;

    @SerializedName("in_stock")
    private boolean in_stock;

    @SerializedName("in_stock_str")
    private String in_stock_str;

    @SerializedName("manufacture_price")
    private double manufacture_price;

    @SerializedName("category")
    private Category category;

    @SerializedName("main_category")
    private Category main_category;

    @SerializedName("caliber")
    private Caliber caliber;

    @SerializedName("order_count")
    private int order_count;

    @SerializedName("media")
    private List<Media> media;

    @SerializedName("options")
    private List<Option> options;

    @SerializedName("reviews")
    private List<Review> reviews;

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public boolean isIn_stock() {
        return in_stock;
    }

    public void setIn_stock(boolean in_stock) {
        this.in_stock = in_stock;
    }

    public String getIn_stock_str() {
        return in_stock_str;
    }

    public void setIn_stock_str(String in_stock_str) {
        this.in_stock_str = in_stock_str;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isBind_to_market() {
        return bind_to_market;
    }

    public void setBind_to_market(boolean bind_to_market) {
        this.bind_to_market = bind_to_market;
    }

    public boolean isI_fav() {
        return i_fav;
    }

    public void setI_fav(boolean i_fav) {
        this.i_fav = i_fav;
    }

    public double getFixed_price() {
        return fixed_price;
    }

    public void setFixed_price(double fixed_price) {
        this.fixed_price = fixed_price;
    }

    public String getWeight() {
        if(weight==null || weight.isEmpty())
            weight="0.0";
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Media> getMedia() {
        if (media == null)
            media = new ArrayList<>();
        return media;
    }

    public void setMedia(List<Media> media) {
        this.media = media;
    }

    public double getManufacture_price() {
        return manufacture_price;
    }

    public void setManufacture_price(double manufacture_price) {
        this.manufacture_price = manufacture_price;
    }

    public Category getMain_category() {
        return main_category;
    }

    public void setMain_category(Category main_category) {
        this.main_category = main_category;
    }

    public Caliber getCaliber() {
        return caliber;
    }

    public void setCaliber(Caliber caliber) {
        this.caliber = caliber;
    }

    public int getOrder_count() {
        return order_count;
    }

    public void setOrder_count(int order_count) {
        this.order_count = order_count;
    }
}
