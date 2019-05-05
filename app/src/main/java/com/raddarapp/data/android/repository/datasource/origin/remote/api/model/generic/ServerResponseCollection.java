package com.raddarapp.data.android.repository.datasource.origin.remote.api.model.generic;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ServerResponseCollection<T> {
    @SerializedName("content") private List<T> response;
    @SerializedName("metadata") private Metadata metadata;

    public class Metadata {
        @SerializedName("page_number") private int pageNumber;
        @SerializedName("elements_per_page") private int elementsPerPage;
        @SerializedName("total_pages") private int totalPages;
        @SerializedName("total_elements") private int totalElements;

        public int getPageNumber() {
            return pageNumber;
        }

        public int getElementsPerPage() {
            return elementsPerPage;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public int getTotalElements() {
            return totalElements;
        }
    }

    public List<T> getResponse() {
        return response;
    }

    public Metadata getMetadata() {
        return metadata;
    }
}
