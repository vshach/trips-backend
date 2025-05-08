package com.vica.trips.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class PhotoId implements Serializable {

	private LocalDate dateTaken;
    private String filename;

    // Constructors, equals, and hashCode (same as before)

    public PhotoId() {}

    public PhotoId(LocalDate dateTaken, String fileName) {
        this.dateTaken = dateTaken;
        this.filename = fileName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhotoId)) return false;
        PhotoId that = (PhotoId) o;
        return Objects.equals(dateTaken, that.dateTaken) &&
               Objects.equals(filename, that.filename);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTaken, filename);
    }
}

