package br.com.catalogolivros.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class BookResult {

    @JsonProperty("title")
    private String title;

    @JsonProperty("authors")
    private List<AuthorResult> authors;

    @JsonProperty("languages")
    private List<String> languages;

    @JsonProperty("download_count")
    private Integer downloadCount;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public List<AuthorResult> getAuthors() { return authors; }
    public void setAuthors(List<AuthorResult> authors) { this.authors = authors; }

    public List<String> getLanguages() { return languages; }
    public void setLanguages(List<String> languages) { this.languages = languages; }

    public int getDownloadCount() { return downloadCount; }
    public void setDownloadCount(int downloadCount) { this.downloadCount = downloadCount; }
}