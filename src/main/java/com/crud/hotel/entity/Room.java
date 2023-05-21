package com.crud.hotel.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@Table(name = "ROOM")
@NoArgsConstructor
@AllArgsConstructor
public class Room implements RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;
    private Integer number;
    private Integer floor;
    private Double price;
    private Integer bed;
    private Boolean isVip;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Person> personList;

    @ManyToOne
    @JoinColumn(name = "hotel_id", referencedColumnName = "id")
    private Hotel hotel;

    public void addHotel(Hotel hotel) {
        setHotel(hotel);
        if (this.getPrice() > 2000) {
            this.setIsVip(true);
        } else {
            this.setIsVip(false);
        }
        hotel.setQtRooms(hotel.getRoomList().size() + 1);
        hotel.getRoomList().add(this);
    }

    @Override
    public Boolean vipAcess() {
        return isVip;
    }
}
