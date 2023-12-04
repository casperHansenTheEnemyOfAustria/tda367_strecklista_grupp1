package se.cholmers.backend.Model.Interfaces;

import java.util.List;

public interface IOrder {

    String getTimeString();

    List<IProduct> getProducts();

}