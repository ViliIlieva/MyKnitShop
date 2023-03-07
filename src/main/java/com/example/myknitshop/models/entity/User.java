package com.example.myknitshop.models.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
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

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id",
                    referencedColumnName = "id"))
    private List<Role> userRoles = new ArrayList<> ();

    @OneToMany
    private Set<Product> purchaseProduct;

    @OneToMany
    private Set<Product> allBuyProduct;

    @OneToMany
    private Set<Order> orders;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public User setAddress(String address) {
        this.address = address;
        return this;
    }

    public List<Role> getUserRoles() {
        return userRoles;
    }

    public User setUserRoles(List<Role> userRoles) {
        this.userRoles = userRoles;
        return this;
    }

    public Set<Product> getPurchaseProduct() {
        return purchaseProduct;
    }

    public User setPurchaseProduct(Set<Product> purchaseProduct) {
        this.purchaseProduct = purchaseProduct;
        return this;
    }

    public Set<Product> getAllBuyProduct() {
        return allBuyProduct;
    }

    public User setAllBuyProduct(Set<Product> allBuyProduct) {
        this.allBuyProduct = allBuyProduct;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public User setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public User setOrders(Set<Order> orders) {
        this.orders = orders;
        return this;
    }
}
