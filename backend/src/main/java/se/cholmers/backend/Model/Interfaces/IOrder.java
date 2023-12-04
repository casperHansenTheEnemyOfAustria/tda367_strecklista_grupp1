package se.cholmers.backend.Model.Interfaces;

import java.util.List;

import se.cholmers.backend.Model.Product;

public interface IOrder {

    String getTimeString();

    List<Product> getProducts();

}