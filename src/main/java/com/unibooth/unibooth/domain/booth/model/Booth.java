package com.unibooth.unibooth.domain.booth.model;



import com.unibooth.unibooth.domain.user.model.Entertainer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Booth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Entertainer entertainer;

    private String university;
    private String location;
    private String type;
    private String date;
    private String stampCode;
    private double latitude;
    private double longitude;

    public Booth(Entertainer entertainer, String university, String location, String type, String date, double latitude, double longitude) {
        this.entertainer = entertainer;
        this.university = university;
        this.location = location;
        this.type = type;
        this.date = date;
        this.stampCode = Integer.toString((int)(Math.random() * (99999 - 10000 + 1)) + 10000);
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static Booth of(
            Entertainer entertainer,
            String university,
            String location,
            String type,
            String date,
            double latitude,
            double longitude
        ){
        return new Booth(
          entertainer, university, location, type, date, latitude, longitude
        );
    }
}
