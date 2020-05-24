package ru.eracom.persist;

import javax.persistence.*;

import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name="products")
@NamedQueries({
        @NamedQuery(name = "findByTitle", query = "from Product p where p.title = :title"),
        @NamedQuery(name = "findByCost", query = "from Product p where p.cost = :cost")
})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private float cost;

    @ManyToMany
    @JoinTable(name = "users_products",
            joinColumns = @JoinColumn(name = "products_id"),
            inverseJoinColumns = @JoinColumn(name = "users_id"))
    private List<User> buyers = new LinkedList<>();

    public Product() {
    }

    public Product(Long id, String title, float cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public List<User> getBuyers() {
        return buyers;
    }

    public void setBuyers(List<User> buyers) {
        this.buyers = buyers;
    }

    @Override
    public String toString() {
        StringBuilder productInfo = new StringBuilder("Product: \nid= ").append(id);
        productInfo.append(", title= ").append(title);
        productInfo.append(", cost= ").append(cost);
        productInfo.append("\nbuyers: ");

        if ((buyers != null) && (buyers.size() > 0)) {
            for (int i = 0; i < buyers.size(); i++) {
                if (i > 0)
                    productInfo.append(", ");
                productInfo.append(buyers.get(i).getName());
            }
        }
        return productInfo.toString();
    }
}

