package ir.maktabsharif.base.web.rest;

import io.swagger.annotations.ApiOperation;
import ir.maktabsharif.base.BaseDTO;
import ir.maktabsharif.base.BaseEntity;
import ir.maktabsharif.base.mapper.BaseMapper;
import ir.maktabsharif.base.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@CrossOrigin
public class BaseRestFul<E extends BaseEntity<PK>, D extends BaseDTO<PK>, PK extends Serializable,
        S extends BaseService<E, PK>, M extends BaseMapper<E, D, PK>> {

    protected final S service;
    protected final M mapper;

    public BaseRestFul(S service, M mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    @ApiOperation(value = "get all entities")
    public ResponseEntity<List<D>> getAll() {
        return ResponseEntity.ok(mapper.convertListEntityToDTO(
                service.findAllNotSecure()
        ));
    }

    @GetMapping("/pageable")
    @ApiOperation(value = "get all entities pageable")
    public ResponseEntity<Page<D>> getAll(Pageable pageable) {
        Page<E> page = service.findAllNotSecure(pageable);
        return ResponseEntity.ok(page.map(mapper::convertEntityToDTO));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "find by id")
    public ResponseEntity<D> findById(@PathVariable("id") PK id) {
        Optional<E> optionalE = service.findByIdNotSecure(id);
        return optionalE.map(e -> ResponseEntity.ok(
                mapper.convertEntityToDTO(e)
        )).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "delete by id")
    public ResponseEntity<Void> deleteById(@PathVariable("id") PK id) {
        service.deleteByIdNotSecure(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    @ApiOperation(value = "save new entity")
    public ResponseEntity<D> save(@RequestBody D d) {
        if(d.getId() != null) return ResponseEntity.badRequest().build();
        E entity = service.saveNotSecure(
                mapper.convertDTOToEntity(d)
        );
        return ResponseEntity.ok(mapper.convertEntityToDTO(entity));
    }

    @PutMapping
    @ApiOperation(value = "update entity")
    public ResponseEntity<D> update(@RequestBody D d) {
        if(d.getId() == null) return ResponseEntity.badRequest().build();
        E e = mapper.convertDTOToEntity(d);
        e.setId(d.getId());
        E entity = service.saveNotSecure(e);
        return ResponseEntity.ok(mapper.convertEntityToDTO(entity));
    }
}
