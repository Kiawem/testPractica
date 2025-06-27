package com.test.dto;

import java.util.Objects;

/**
 * DTO for {@link com.test.model.Book}
 */
public class BookDto {
    private String title;
    private int year;
    private String genre;
    private String authorName;

    public BookDto(String title, int year, String genre, String authorName) {
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.authorName = authorName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDto entity = (BookDto) o;
        return Objects.equals(this.title, entity.title) &&
                Objects.equals(this.year, entity.year) &&
                Objects.equals(this.genre, entity.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, year, genre);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "title = " + title + ", " +
                "year = " + year + ", " +
                "genre = " + genre + ")";
    }
}