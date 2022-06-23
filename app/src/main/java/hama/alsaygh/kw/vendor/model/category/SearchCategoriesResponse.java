package hama.alsaygh.kw.vendor.model.category;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import hama.alsaygh.kw.vendor.model.searchLog.SearchLog;


public class SearchCategoriesResponse {

    @SerializedName("status")
    private boolean status;

    @SerializedName("code")
    private String code;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private SearchCategoriesResult data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCode() {
        if (code == null)
            code = "";
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SearchCategoriesResult getData() {
        return data;
    }

    public void setData(SearchCategoriesResult data) {
        this.data = data;
    }

    public List<Category> getCategories() {
        return getData().getData();
    }

}

class SearchCategoriesResult {
    @SerializedName("result")
    private List<Category> data;

    @SerializedName("search_logs")
    private List<SearchLog> search_logs;

    public List<Category> getData() {
        if (data == null)
            data = new ArrayList<>();
        return data;
    }

    public void setData(List<Category> data) {
        this.data = data;
    }

    public List<SearchLog> getSearch_logs() {
        return search_logs;
    }

    public void setSearch_logs(List<SearchLog> search_logs) {
        this.search_logs = search_logs;
    }
}