package com.entity;

import java.math.BigDecimal;

public class Cartitem {
	private String cartItemId;
	private int quantity;
	private Book book;
	private User owner; 
	private String bid;
	private String uid;
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	/**
	 * 返回小计
	 * @return
	 */
	public double getSubtotal() {
		BigDecimal v1 = new BigDecimal(Double.toString(book.getCurrPrice()));
		BigDecimal v2 = new BigDecimal(Double.toString(quantity));
		return v1.multiply(v2).doubleValue();
	}
	public String getCartItemId() {
		return cartItemId;
	}
	public void setCartItemId(String cartItemId) {
		this.cartItemId = cartItemId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
}
