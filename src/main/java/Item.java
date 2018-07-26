
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "ITEM")
public class Item {

    private Long id;
    private String name;
    private Date dateCreated;
    private Date lastUpdateDate;
    private String description;

    public Item() {
    }

    Item(String name, Date dateCreated, Date lastUpdateDate, String description) {
        this.name = name;
        this.dateCreated = dateCreated;
        this.lastUpdateDate = lastUpdateDate;
        this.description = description;
    }

    @Id
    @SequenceGenerator(name = "IT_SQ", sequenceName = "ITEM_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IT_SQ")
    public Long getId() {
        return id;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    @JsonFormat(pattern = "dd.MM.yyyy")
    @Column(name = "DATE_CREATED")
    public Date getDateCreated() {
        return dateCreated;
    }

    @JsonFormat(pattern = "dd.MM.yyyy")
    @Column(name = "LAST_UPDATE_DATE")
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    @JsonCreator
    public static Item createFromJson(String jsonString){

        ObjectMapper objectMapper = new ObjectMapper();

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        objectMapper.setDateFormat(format);

        Item item = null;
        try {
            item = objectMapper.readValue(jsonString, Item.class);
        }catch (IOException e){
            e.printStackTrace();
        }
        return item;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateCreated=" + dateCreated +
                ", lastUpdateDate=" + lastUpdateDate +
                ", description='" + description + '\'' +
                '}';
    }
}
