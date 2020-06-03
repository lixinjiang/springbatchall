package cn.bonusli.batch.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author lxj
 */
@Entity
@Table
@Getter
@Setter
public class Access {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String username;
    private String shopName;
    private String categoryName;
    private String brandName;
    private String shopId;
    private String omit;
    private String updateTime;
    private boolean deleteStatus;
    private String createTime;
    private String description;

    @Override
    public String toString() {
        return "Access{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", shopName='" + shopName + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", brandName='" + brandName + '\'' +
                ", shopId='" + shopId + '\'' +
                ", omit='" + omit + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", deleteStatus=" + deleteStatus +
                ", createTime='" + createTime + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
