package hama.alsaygh.kw.vendor.model.product;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import hama.alsaygh.kw.vendor.model.category.Category;
import hama.alsaygh.kw.vendor.model.category.MainCategory;
import hama.alsaygh.kw.vendor.model.product.caliber.Caliber;
import hama.alsaygh.kw.vendor.model.product.review.Review;


public class Product implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("translations")
    private Translations translations;

    @SerializedName("main_category")
    private MainCategory main_category;

    @SerializedName("category")
    private Category category;

    @SerializedName("parent_category")
    private Category sub_category;

    @SerializedName("quantity")
    private int quantity;

    @SerializedName("bind_to_market")
    private boolean bind_to_market;

    @SerializedName("has_caliber_data")
    private boolean has_caliber_data;

    @SerializedName("caliber")
    private Caliber caliber;

    @SerializedName("manufacture_price")
    private double manufacture_price;

    @SerializedName("discount_type")
    private String discount_type;

    @SerializedName("discount_value")
    private double discount_value;

    @SerializedName("new_price")
    private double new_price;

    @SerializedName("weight")
    private String weight;

    @SerializedName("cate_code")
    private String cate_code;

    @SerializedName("total_metal_weight")
    private double total_metal_weight;

    @SerializedName("metal_weight")
    private double metal_weight;

    @SerializedName("gem_stone_weight")
    private double gem_stone_weight;

    @SerializedName("diamond_weight")
    private double diamond_weight;

    @SerializedName("purity")
    private String purity;

    @SerializedName("dimond")
    private String dimond;

    @SerializedName("color")
    private String color;

    @SerializedName("gram_price")
    private double gram_price;

    @SerializedName("ston_type")
    private String ston_type;

    @SerializedName("rate")
    private float rate;

    @SerializedName("price")
    private double price;

    @SerializedName("in_stock")
    private boolean in_stock;

    @SerializedName("in_stock_str")
    private String in_stock_str;

    @SerializedName("order_count")
    private int order_count;

    @SerializedName("media")
    private List<Media> media;

    @SerializedName("options")
    private List<Option> options;

    @SerializedName("reviews")
    private List<Review> reviews;

    public Translations getTranslations() {
        return translations;
    }

    public void setTranslations(Translations translations) {
        this.translations = translations;
    }

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


    public String getWeight() {
        if (weight == null || weight.isEmpty())
            weight = "0.0";
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

    public MainCategory getMain_category() {
        return main_category;
    }

    public void setMain_category(MainCategory main_category) {
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

    public Category getSub_category() {
        return sub_category;
    }

    public void setSub_category(Category sub_category) {
        this.sub_category = sub_category;
    }

    public boolean isHas_caliber_data() {
        return has_caliber_data;
    }

    public void setHas_caliber_data(boolean has_caliber_data) {
        this.has_caliber_data = has_caliber_data;
    }

    public String getDiscount_type() {
        return discount_type;
    }

    public void setDiscount_type(String discount_type) {
        this.discount_type = discount_type;
    }

    public double getDiscount_value() {
        return discount_value;
    }

    public void setDiscount_value(double discount_value) {
        this.discount_value = discount_value;
    }

    public double getNew_price() {
        return new_price;
    }

    public void setNew_price(double new_price) {
        this.new_price = new_price;
    }

    public String getCate_code() {
        return cate_code;
    }

    public void setCate_code(String cate_code) {
        this.cate_code = cate_code;
    }

    public double getTotal_metal_weight() {
        return total_metal_weight;
    }

    public void setTotal_metal_weight(double total_metal_weight) {
        this.total_metal_weight = total_metal_weight;
    }

    public double getMetal_weight() {
        return metal_weight;
    }

    public void setMetal_weight(double metal_weight) {
        this.metal_weight = metal_weight;
    }

    public double getGem_stone_weight() {
        return gem_stone_weight;
    }

    public void setGem_stone_weight(double gem_stone_weight) {
        this.gem_stone_weight = gem_stone_weight;
    }

    public double getDiamond_weight() {
        return diamond_weight;
    }

    public void setDiamond_weight(double diamond_weight) {
        this.diamond_weight = diamond_weight;
    }

    public String getPurity() {
        return purity;
    }

    public void setPurity(String purity) {
        this.purity = purity;
    }

    public String getDimond() {
        return dimond;
    }

    public void setDimond(String dimond) {
        this.dimond = dimond;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getGram_price() {
        return gram_price;
    }

    public void setGram_price(double gram_price) {
        this.gram_price = gram_price;
    }

    public String getSton_type() {
        return ston_type;
    }

    public void setSton_type(String ston_type) {
        this.ston_type = ston_type;
    }

}
