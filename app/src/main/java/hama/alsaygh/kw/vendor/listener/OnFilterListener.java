package hama.alsaygh.kw.vendor.listener;

public interface OnFilterListener {
    void onFilterClick(String type_of_price, int category_level_1, int category_level_2, int category_level_3, String range_price_from, String range_price_to);

    void onSortClickClick(String sortBy);
}
