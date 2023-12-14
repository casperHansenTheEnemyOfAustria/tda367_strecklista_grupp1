package se.cholmers.backend.Model;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import se.cholmers.backend.Model.Interfaces.IProduct;
public class ProductIntMap extends HashMap<IProduct, Integer>{
    Map<IProduct, Integer> map;
  
    public ProductIntMap() {
        this.map = new HashMap<IProduct, Integer>();
    }
    public Integer put(IProduct product, Integer integer) {
        for(IProduct p : map.keySet()) {
            if(p.getID().equals(product.getID())) {
                Integer prev = map.get(p);
                map.put(p, integer);
                return integer;
            }
        }
        return map.put(product, integer);
    }
    public Integer get(Object obj) {
        IProduct product = (IProduct) obj;

        for(IProduct p : map.keySet()) {
            if(p.getID().equals(product.getID())) {
   
                return map.get(p);
            }
        }
        return null;
    }

    public Set<IProduct> keySet() {
        return map.keySet();
    }

    public String toString() {
        String output = "";
        for(IProduct p : map.keySet()) {
            output += p.getName() + " " + map.get(p) + "\n";
        }
        return output;
    }
}
