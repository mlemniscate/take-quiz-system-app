package ir.maktabsharif.base;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity<PK extends Serializable> implements Serializable {

    @Id
    @GeneratedValue
    private PK id;

}
