package com.example.myknitshop.models.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity{
    @Column(nullable = false, unique = true)
    private String username;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    private String address;

    @OneToOne(fetch = FetchType.EAGER)
    private UserRoleEntity role;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private Set<ProductEntity> products;

    @OneToMany(mappedBy = "buyer")
    private Set<ProductEntity> buyProduct;

    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public UserEntity setAddress(String address) {
        this.address = address;
        return this;
    }

    public UserRoleEntity getRole() {
        return role;
    }

    public UserEntity setRole(UserRoleEntity role) {
        this.role = role;
        return this;
    }

    public Set<ProductEntity> getProducts() {
        return products;
    }

    public UserEntity setProducts(Set<ProductEntity> products) {
        this.products = products;
        return this;
    }

    public Set<ProductEntity> getBuyProduct() {
        return buyProduct;
    }

    public UserEntity setBuyProduct(Set<ProductEntity> buyProduct) {
        this.buyProduct = buyProduct;
        return this;
    }

}
