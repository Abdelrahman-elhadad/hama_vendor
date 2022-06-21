package hama.alsaygh.kw.vendor.model.addProduct;

import java.io.Serializable;
import java.util.List;

import hama.alsaygh.kw.vendor.model.category.Category;
import hama.alsaygh.kw.vendor.model.category.MainCategory;
import hama.alsaygh.kw.vendor.model.product.Media;
import hama.alsaygh.kw.vendor.model.product.Option;
import hama.alsaygh.kw.vendor.model.product.caliber.Caliber;


public class AddProduct implements Serializable {

    private int id;
    private String name;
    private String description;
    private String quantity;
    private boolean bind_to_market;
    private String fixed_price;
    private String weight;
    private String manufacture_price;
    private Category sub_category;
    private MainCategory main_category;
    private Category child_sub_category;
    private Caliber caliber;
    private List<Media> media;
    private String color;
    private String code;
    private String name_ar;
    private String description_ar;
    private String netWeight;
    private String stoneWeight;
    private String stoneType;
    private String diamond;
    private String diamondWeight;
    private String purity;
    private String gmPrice;
    private String totalWeightMetal;
    private String discount;
    private List<Option> options;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiscount() {
        if (discount == null || discount.isEmpty())
            discount = "0.0";
        else {
            double dis = Double.parseDouble(discount);
            if (dis < 0)
                discount = "0.0";
        }
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getStoneWeight() {
        return stoneWeight;
    }

    public void setStoneWeight(String stoneWeight) {
        this.stoneWeight = stoneWeight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName_ar() {
        return name_ar;
    }

    public void setName_ar(String name_ar) {
        this.name_ar = name_ar;
    }

    public String getDescription_ar() {
        return description_ar;
    }

    public void setDescription_ar(String description_ar) {
        this.description_ar = description_ar;
    }

    public String getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(String netWeight) {
        this.netWeight = netWeight;
    }

    public String getStoneType() {
        return stoneType;
    }

    public void setStoneType(String stoneType) {
        this.stoneType = stoneType;
    }

    public String getDiamond() {
        return diamond;
    }

    public void setDiamond(String diamond) {
        this.diamond = diamond;
    }

    public String getDiamondWeight() {
        return diamondWeight;
    }

    public void setDiamondWeight(String diamondWeight) {
        this.diamondWeight = diamondWeight;
    }

    public String getPurity() {
        return purity;
    }

    public void setPurity(String purity) {
        this.purity = purity;
    }

    public String getGmPrice() {
        return gmPrice;
    }

    public void setGmPrice(String gmPrice) {
        this.gmPrice = gmPrice;
    }

    public String getTotalWeightMetal() {
        return totalWeightMetal;
    }

    public void setTotalWeightMetal(String totalWeightMetal) {
        this.totalWeightMetal = totalWeightMetal;
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

    public String getQuantity() {
        if (quantity == null || quantity.isEmpty())
            quantity = "0.0";
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public boolean isBind_to_market() {
        return bind_to_market;
    }

    public void setBind_to_market(boolean bind_to_market) {
        this.bind_to_market = bind_to_market;
    }

    public String getFixed_price() {
        return fixed_price;
    }

    public void setFixed_price(String fixed_price) {
        this.fixed_price = fixed_price;
    }

    public String getWeight() {
        if (weight == null || weight.isEmpty())
            weight = "0.0";
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getManufacture_price() {
        return manufacture_price;
    }

    public void setManufacture_price(String manufacture_price) {
        this.manufacture_price = manufacture_price;
    }

    public Category getSub_category() {
        return sub_category;
    }

    public void setSub_category(Category sub_category) {
        this.sub_category = sub_category;
    }

    public MainCategory getMain_category() {
        return main_category;
    }

    public void setMain_category(MainCategory main_category) {
        this.main_category = main_category;
    }

    public Category getChild_sub_category() {
        return child_sub_category;
    }

    public void setChild_sub_category(Category child_sub_category) {
        this.child_sub_category = child_sub_category;
    }

    public Caliber getCaliber() {
        return caliber;
    }

    public void setCaliber(Caliber caliber) {
        this.caliber = caliber;
    }

    public List<Media> getMedia() {
        return media;
    }

    public void setMedia(List<Media> media) {
        this.media = media;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "AddProduct{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", quantity='" + quantity + '\'' +
                ", bind_to_market=" + bind_to_market +
                ", fixed_price='" + fixed_price + '\'' +
                ", weight='" + weight + '\'' +
                ", manufacture_price='" + manufacture_price + '\'' +
                ", sub_category=" + sub_category +
                ", main_category=" + main_category +
                ", child_sub_category=" + child_sub_category +
                ", caliber=" + caliber +
                ", media=" + media +
                ", color='" + color + '\'' +
                ", code='" + code + '\'' +
                ", name_ar='" + name_ar + '\'' +
                ", description_ar='" + description_ar + '\'' +
                ", netWeight='" + netWeight + '\'' +
                ", stoneWeight='" + stoneWeight + '\'' +
                ", stoneType='" + stoneType + '\'' +
                ", diamond='" + diamond + '\'' +
                ", diamondWeight='" + diamondWeight + '\'' +
                ", purity='" + purity + '\'' +
                ", gmPrice='" + gmPrice + '\'' +
                ", totalWeightMetal='" + totalWeightMetal + '\'' +
                ", discount='" + discount + '\'' +
                ", options=" + options +
                '}';
    }
}
