package com.velox.userservice.repository.repo;

import com.velox.userservice.models.entity.BaseModel;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


public class BaseRepositoryImpl<T extends BaseModel, ID> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

    private final JpaEntityInformation<T, ?> jpaEntityInformation;
    private final EntityManager entityManager;

    public BaseRepositoryImpl(JpaEntityInformation<T, ?> jpaEntityInformation, EntityManager entityManager) {
        super(jpaEntityInformation, entityManager);
        this.jpaEntityInformation = jpaEntityInformation;
        this.entityManager = entityManager;
    }

    @Override
    public Optional<T> findById(ID id) {
        return super.findById(id).filter(entity -> !entity.isDeleted());
    }

    @Override
    @Transactional
    public void softDeleteById(ID id) {
        T entity = super.findById(id).orElseThrow(() -> new IllegalArgumentException("Enitty is Not found"));
        entity.setDeleted(true);
        super.save(entity);
    }
}
