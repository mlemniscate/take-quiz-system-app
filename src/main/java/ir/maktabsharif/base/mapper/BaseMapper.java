package ir.maktabsharif.base.mapper;

import ir.maktabsharif.base.BaseDTO;
import ir.maktabsharif.base.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public interface BaseMapper<E extends BaseEntity<PK>, D extends BaseDTO<PK>, PK extends Serializable> {

    E convertDTOToEntity(D d);

    D convertEntityToDTO(E e);

    List<E> convertListDTOToEntity(List<D> d);

    List<D> convertListEntityToDTO(List<E> e);

    Set<E> convertSetDTOToEntity(Set<D> d);

    Set<D> convertSetEntityToDTO(Set<E> e);

}
