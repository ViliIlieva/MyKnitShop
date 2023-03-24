package com.example.myknitshop.models.entity;

import jakarta.persistence.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

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
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id",
                    referencedColumnName = "id"))
    private List<Role> userRoles = new ArrayList<> ();

    @ManyToMany
    private List<PurchasedProducts> purchaseProduct;

    @ManyToMany
    private List<ChoseProducts> choseProduct;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Order> orders;

    @OneToMany
    private List<Message> messages;

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

    public List<PurchasedProducts> getPurchaseProduct() {
        return purchaseProduct;
    }

    public User setPurchaseProduct(List<PurchasedProducts> purchaseProduct) {
        this.purchaseProduct = purchaseProduct;
        return this;
    }

    public List<ChoseProducts> getChoseProduct() {
        return choseProduct;
    }

    public User setChoseProduct(List<ChoseProducts> choseProduct) {
        this.choseProduct = choseProduct;
        return this;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public User setOrders(List<Order> orders) {
        this.orders = orders;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public User setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public User setMessages(List<Message> messages) {
        this.messages = messages;
        return this;
    }

    public void removeProductFromChoseList(Long productId) {
        this.choseProduct.removeIf(p-> p.getId().equals(productId));
    }

    public ChoseProducts findByImg(String img){
       return this.choseProduct.stream().filter(p-> p.getImg().equals(img)).findFirst().get();
    }
    public PurchasedProducts findPurchaseProductByImg(String img){
        return this.purchaseProduct.stream().filter(p-> p.getImg().equals(img)).findFirst().get();
    }

    public void addProductToPurchaseList(PurchasedProducts product){
        if(this.purchaseProduct.contains (product)){
            PurchasedProducts products = findPurchaseProductByImg(product.getImg());
            products.setQuantity(product.getQuantity() + product.getQuantity());
        }else {
            this.purchaseProduct.add (product);
        }
    }

    public Order findOrderById(Long orderId){
       return this.orders.stream().filter(o -> o.getId().equals(orderId)).findFirst().get();
    }

    public String getUserFullName(Long id){
       return this.firstName.concat(this.lastName);
    }

}
